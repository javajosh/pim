import { createServer } from 'http';
import { WebSocketServer } from 'ws';
import { readFile } from 'fs';
import { extname } from  'path'
import { getProp } from "./simpatico/core.js";


const port = 2000;
const echo = true;

const server = createServer(function (request, response) {
  console.debug(`${request.method} http://127.0.0.1:${port}/${request.url}`);

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
const conns = [];
wss.on('connection', conn => {
  const connId = conns.length;
  console.log('server.onconnection => {conn:{url:%s, protocol:%s, _socket.remoteAddress: %s}}',
      conn.url, conn.protocol, conn._socket.remoteAddress);
  // Confusingly, url is always null. why? We get the remoteAddress, but everything else we know must come through as a message.
  // what we need is an identifier. at first a string, then a pubkey and a list of decaying GUIDs.
  conns.push(conn);
  conn.send(`server.onconnection hello ${connId}`);
  conn.on('message', e => {
    const msg = e.toString();
    console.log('server.conn.onmessage {connId: %s msg: %s}', connId, msg);
    conns.forEach(c => {
      if (c && c.readyState === 1){
        if (echo || (c !== conn))  // don't send back to originator unless echo is on
          c.send(msg);
      }
    })
  });
});


console.log(`Server running at http://127.0.0.1:${port}/`);
