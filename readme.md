# PIM - The Personal Inventory Manager

Track all your stuff. *A full-stack tech demo from [@javajosh](https://twitter.com/javajosh) of [javajosh.com](https://javajosh.com); 
code at [github.com/javajosh/pim](https://github.com/javajosh/pim) live demo hosted at [javajosh.com/pim](https://javajosh.pim) (c) 2021*.

The core of the demo is a Java webapp talking to a relational database, with some [improvements](pim-server-java/docs/tech-stack-discussion.md)

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
