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
    'html':'text/html',
    'js':'text/javascript',
    'css':'text/css',
    'json':'application/json',
    'png':'image/png',
    'jpg':'image/jpg',
    'wav':'audio/wav',
  };
  const ext = extname(filePath);
  let contentType = contentTypes[ext] ? contentTypes[ext] : 'text/html';

  readFile(filePath, function(error, content) {
    if (error) {
      if(error.code === 'ENOENT'){
        readFile('./404.html', function(error, content) {
          response.writeHead(200, { 'Content-Type': contentType });
          response.end(content, 'utf-8');
        });
      }
      else {
        response.writeHead(500);
        response.end('Sorry, check with the site admin for error: '+error.code+' ..\n');
        response.end();
      }
    }
    else {
      response.writeHead(200, { 'Content-Type': contentType });
      response.end(content, 'utf-8');
    }
  });

}).listen(port);


const wss = new WebSocketServer({server});
// Broadcast all inbound messages to all connections.
// Later we'll add PKI and point-to-point fanout
const conns = [];
wss.on('connection', conn => {
  console.log ('CONNECTION %s', conn);
  conns.push(conn);
  conn.on('message', msg => {
    console.log ('MESSAGE %s', msg);
    conns.forEach(c => {
      if (c && c.readyState === 1 && c !== conn){
        c.send(msg.toString());
      }
    })
  });
});


console.log(`Server running at http://127.0.0.1:${port}/`);
