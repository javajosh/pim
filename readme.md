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
 1. Add the asset bundle and play with that.
 2. Go deeper into JDBI. Have only scratched the surface.
 3. Go deeper into Logging, particularly with practical tail problems.
 4. Go deeper into Jersey; can you generate swagger?
 5. Go deeper into metrics.
 6. Go deeper into testing
    1. Unit testing with junit and assertj
    2. Functional testing with ... ?
    3. Load testing with ... ab?
 7. Go wider into alternative databases: H2, Derby; SQLite
 8. Build out the front-end with traditional server-side rendering
 9. Build out the front-end with a modern SPA (Angular & React)
 10. Build out some CI triggered by Github.
 11. Build out OAuth2 support for Google, Facebook, Apple, Microsoft, Github
