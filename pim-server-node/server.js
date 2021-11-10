import { createServer } from 'http';
import { WebSocketServer } from 'ws';
import { readFile } from 'fs';
import { extname } from  'path'


const port = 2000;

const server = createServer(function (request, response) {
  console.log('GET http://127.0.0.1:2000/');


  let filePath = '.' + request.url;
  if (filePath === './') filePath = './index.html';

  const contentTypes = {
    '.html':'text/html',
    '.js':'text/javascript',
    '.css':'text/css',
    '.json':'application/json',
    '.png':'image/png',
    '.jpg':'image/jpg',
    '.wav':'audio/wav',
  };
  const ext = extname(filePath);
  let contentType = contentTypes[ext] ? contentTypes[ext] : 'text/html';

  readFile(filePath, function (error, responseContent =  "something bad happened") {
    let responseCode;
    const responseCodesFromErrorCodes = {
      'ENOENT': 404
    };

    if (error) {
      responseCode = responseCodesFromErrorCodes[error.code] ? responseCodesFromErrorCodes[error.code]: 500;
      readFile(`./${responseCode}.html`, (err, content) => {
        responseContent = content
      });
    } else {
      responseCode = 200;
    }
    response.writeHead(responseCode, {'Content-Type': contentType});
    response.end(responseContent, 'utf-8');
  });

}).listen(port);


const wss = new WebSocketServer({server});
// Broadcast all inbound messages to all connections.
// Later we'll add PKI and point-to-point fanout
const conns = [];
wss.on('connection', conn => {
  console.log('CONNECTION %s %s', conn.url, conn.protocol);
  conns.push(conn);
  conn.on('message', msg => {
    console.log('MESSAGE %s', msg);
    conns.forEach(c => {
      if (c && c.readyState === 1){ // add && c !== conn to not send to self
        c.send(msg.toString());
      }
    })
  });
});


console.log(`Server running at http://127.0.0.1:${port}/`);
