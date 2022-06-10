# Running

The canonical way is Docker from a fresh checkout:
```shell
$ apt install git google-chrome docker.io   # ~/bin
$ git clone https://github.com/javajosh/pim # .git
$ cd pim/pim-server-node
$ docker build -t pim-server-node .         # Dockerfile => image
$ docker run -dp 2000:2000 pim-server-node  # image => container => server.js
$ open http://127.0.0.1:2000                # index.html
```

## Bake the code into the image?
We want to bake the program into the image for production deployment. But this is, in general, too slow for a BTD loop.
The difference is, in general, one line of Dockerfile.                             

A developer will want to install `nodejs` directly and replace the `docker` lines with something like:

```shell
$ apt install nodejs 
...
$ npm install # package.json
$ node server.js  # server.js
..
```

(You'll want to install [nvm](https://github.com/nvm-sh/nvm) and then `nvm install 14` and then `nvm use 14`)
Note: This project is *generally* not sensitive to git, docker, node or browser versions. Or even library versions.
But it also doesn't go out of it's way to be compatible with unusual environments. 
These instructions are given for Linux, but they apply equally to Windows and macOS with some small modifications.

# Debugging

To debug node code follow the 
[Node inspector instructions](https://nodejs.org/en/docs/guides/debugging-getting-started/).
Alternatively, if you have IntelliJ, create a `node server.js` runtime config and then start it in debug mode.
This is convenient because it will add the --inspect flag to node, parse the secret GUID output, and then 
attach a debugger to the node process providing the GUID as an argument. Handy.

To debug front-end code, navigate to the local site and then browser developer tools. None of the code is compiled
or minimized - it's not even gzipped.
