# Running

The canonical way is Docker from a fresh checkout:
```shell
$ apt install git google-chrome docker.io   # ~/bin
$ git clone https://github.com/javajosh/pim # .git
$ cd pim/pim-server-node
$ docker build -t pim-server-node .         # Dockerfile
$ docker run -dp 2000:2000 pim-server-node  # server.js
$ open http://127.0.0.1:2000                # index.html
```

A developer will want to install `nodejs` directly and replace the `docker` lines with:

```shell
$ apt install nodejs 
...
$ npm install # package.json
$ node server.js  # server.js
..
```
Note: This project is not sensitive to git, docker, node or browser versions. Or even library versions.
But it also doesn't go out of it's way to be compatible with unusual environments. 
These instructions are given for Linux, but they apply equally to Windows and macOS.

# Debugging

To debug node code follow the 
[Node inspector instructions](https://nodejs.org/en/docs/guides/debugging-getting-started/).
Alternatively, if you have IntelliJ, create a `node server.js` runtime config and then start it in debug mode.
This is convenient because it will add the --inspect flag to node, parse the secret GUID output, and then 
attach a debugger to the node process providing the GUID as an argument. Handy.

To debug front-end code, navigate to the local site and then browser developer tools. None of the code is compiled
or minimized - it's not even gzipped.
