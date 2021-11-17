import { createServer } from 'http';
import { WebSocketServer } from 'ws';
import { readFile } from 'fs';
import { extname } from  'path'
import { getProp } from "./simpatico/core.js";


const port = 2000;

const server = createServer(function (request, response) {
  console.log(`${request.method} http://127.0.0.1:${port}/`);

  let filePath = '.' + request.url;
  if (filePath === './') filePath = './index.html';

  const contentTypes = {
    'html':'text/html',
    'js'  :'text/javascript',
    'css' :'text/css',
    'json':'application/json',
    'png' :'image/png',
    'jpg' :'image/jpg',
    'wav' :'audio/wav',
    'svg' :'image/svg+xml',
    'ico' :'image/x-icon',
  };
  const responseCodesFromErrorCodes = {
    'ENOENT': 404,
  };

  readFile(filePath, function (error, responseContent =  "something bad happened") {
    const responseCode = error ? getProp(responseCodesFromErrorCodes, error.code, 500) : 200;
    if (error) {
      readFile(`./${responseCode}.html`, (err, fileContent) => {
        if (err) throw `Unable to read ./${responseCode}.html`;
        responseContent = fileContent;
      });
    }
    const ext = extname(filePath).slice(1);
    const contentType = getProp(contentTypes, ext, 'text/html');
    response.writeHead(responseCode, {'Content-Type': contentType});
    response.end(responseContent, 'utf-8');
  });

}).listen(port);


const wss = new WebSocketServer({server});
// Broadcast all inbound messages to all connections.
// Later we'll add PKI and point-to-point fanout
const conns = [];
wss.on('connection', conn => {
  console.log('wss.onconnection => {conn:{url:%s, protocol:%s}}', conn.url, conn.protocol);
  conns.push(conn);
  conn.on('message', msg => {
    console.log('wss.conn.onmessage msg %s', msg.toString());
    conns.forEach(c => {
      if (c && c.readyState === 1){
        // if (c !== conn) // don't send to the sender.
          c.send(msg.toString());
      }
    })
  });
});


console.log(`Server running at http://127.0.0.1:${port}/`);
