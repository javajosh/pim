FROM node:14-alpine
# Adding build tools to make yarn install work on Apple silicon / arm64 machines
# To use, run
# docker build -t pim-server-node .
# docker run -dp 2000:2000 pim-server-node

RUN apk add --no-cache python3 g++ make
WORKDIR /app
COPY . .
RUN yarn install --production
CMD ["node", "server.js"]
