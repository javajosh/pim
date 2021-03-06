# PIM - The Personal Inventory Manager

Track your stuff. A full-stack tech demo.

  * demo:  [javajosh.com/pim](https://javajosh.pim), 
  * project: [dev.azure.com/javajosh/pim](https://dev.azure.com/javajosh/pim)
  * repo : [github.com/javajosh/pim](https://github.com/javajosh/pim),
  * author: [jay-oh-ess-atch@javajosh.com](mailto:$#%$@javajosh.com) &mdash;
       [@javajosh](https://twitter.com/javajosh)
  * legal: ISC license &mdash; Copyright &copy; 2021 &mdash; USA

The core of the demo is a Java webapp talking to a relational database, with some [improvements](pim-server-java/docs/tech-stack-discussion.md).

[install log](./install_log.md)

[notes on intellij's http support](./pim-server-java/docs/intellij/readme.md)

This software expects you to put sensitive secrets into the process shell, which will then be interpolated into the conf file. Currently this is:
1. `DB_HOST` default localhost
2. `DB_PORT` default 5432
3. `DB_USER` default postgres
4. `DB_PASSWORD` default '' (empty string)

Environment variables are set in various ways in various environments. 
IntelliJ has good ad hoc support in its run configurations.

TODO:
 1. [ ] Add the asset bundle and play with it.
 2. [ ] Add DW "migration" support and play with it.
 3. [ ] Go deeper into JDBI. Have only scratched the surface.
 4. [ ] Go deeper into Logging, particularly with practical tail problems.
 5. [ ] Go deeper into Java 17 and all it's new features, especially gc options.
 6. [ ] Go deeper into Postgres 14, especially it's stored procedures, replication, backups.
 7. [x] ~~Generate swagger from jax rs~~
 8. [ ] Go deeper into Jersey
 9. [ ] Go deeper into DW.metrics.
 10. [ ] Go deeper into JVM.metrics
 11. [ ] Go deeper into testing
     1. [ ] Unit testing with junit and assertj
     2. [ ] Functional testing with ... ?
     3. [ ] Load testing with ... ab? Something custom?
 12. [ ] Go wider into alternative databases: H2, Derby; SQLite
 13. [ ] Go wider into alternative build tools: gradle, Play
 14. [ ] Go wider into alternative JVM-hosted languages: Kotlin, Clojure, Groovy
 15. [ ] Integrate JRebel into the server to avoid restarting on code changes.
 16. [ ] Build out Docker support
 17. [ ] Build out k8s (OCP?) support  - local and hosted
 18. [ ] Build out the front-end with traditional server-side rendering
 19. [ ] Build out the front-end with a modern SPA (Angular & React)
 20. [ ] Build out some CI triggered by GitHub polling.
 21. [ ] Build out OAuth2 support for Google, Facebook, Apple, Microsoft, Github.
 22. [ ] Demo a cluster of DW nodes behind an HAProxy node and measure performance/failure modes.
 23. [ ] Write up a comprehensive threat model, standard OWASP and also supply chain and local physical attack.
 24. [ ] Add TLS with Let's Encrypt.
 25. Is there a version of Maven that downloads source and builds all deps, transitively?
 26. How do we know that the binaries in Maven Central are safe? (Not made by or vuln to malicious actor) Any process that can write to ~/.m2
 27. "Pure Java" projects can build from only Java source plus jar files produced by other Pure Java projects.
 28. An Application is almost never Pure Java in practice, as it includes non-Java components, or itself requires a platform specific environment.
 29. The best stuff is often written in other languages, for other runtimes. (Postgres, Redis, nginx, something-in-Rust)
 30. Docker lets you define a well-known starting point (binary UBI) and then range out to a more convenient starting point with a Dockerfile
 31. The running process can believe it is the only thing in the world, can install anything anywhere, attach to any port, without fear of conflict.

# Docker

Home>Dev box:
> docker run simpatico

Org>VPS:
> docker run simpatico-decay admin-secret
> cron secret 10s curl get stats 

start: alpine-ubi
mount: SimpaticoServer.java, cert
apt-get install wget, jdk-17
wget lib1
wget lib2
javac -cp lib1;lib2 SimpaticoServer.java
SET secret = java -cp lib1;lib2 SimpaticoServer {a:1, b:2}
# add secret admin token to these calls
curl -HAuthorization:secret set log level FOO
curl add tls cert
curl get stats {mem, threads, ports, file handles}
curl get log tail 50 grep ^WARN # Or do something with docker log # or do a websocket tail
curl set condition GO

If the java code changes, we will suffer a brief outage (equal to the time from `javac` to the last `curl`).
If the Dockerfile changes, we will suffer a longer outage (the time from `docker run` to the last `curl`).
We can output logs to the host VPS, and ideally these logs would be easy to combine, filter and sort.
Rather than request logs, I'd like to store a record of invocations and uptime. Start, stop and admin messages only.

# Podman 

Taking the opportunity to check out Podman as a daemonless Docker alternative. 
Install WSL 2 (had to turn on viritualization support in the bios, start an admin ps shell, and wsl2 --install)
In Ubuntu 20.04 you have to do some pretty specific steps to add another repo and get everything working.
sudo apt update
sudo apt upgrade
sudo apt install podman
