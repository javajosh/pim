
Back-end improvements:

1. Instead of using Eclipse or Netbeans because they are free, we're paying for IntelliJ.
2. Instead of implementing user authentication again, we use SSO only.
3. Instead of Spring Boot or JEE, we're using Dropwizard (lots of good choices there)
4. Instead of Java 8, we're using Java 17.
5. Instead of Scala, Groovy, Clojure, Kotlin, we stick with Java.
6. Instead of gradle, Play, Ant, Thymeleaf or Make, we use Maven.
7. Instead of forcing contributors to setup their env manually, we provide a Dockerfile.
8. Instead of using NoSQL, column-store or graph database, we use Postgres, a great
9. Instead of using an ORM we embrace SQL

For the front-end:

5. Instead of supporting old browsers, focus only on "Modern Browsers": ES6, HTML5, CSS3.
6. Instead of fumbling around with CSS conventions we pick Material and BEM
7. Instead of fumbling with JavaScript frameworks, we pick Angular
8. Instead of weighing down the page, adding risk, and trading our users' privacy away for trivial runtime support, we allow no 3rd party sub-resources.
10. *Instead of using a front-end build process, we use ordinary JavaScript and Browser Modules, no transpiling or minification.*
11. *Instead of supporting all features of all platform languages, our front-end only uses a small subset of ES6 features, and relies heavily on SVG.*

Other improvements:

1. Client and server are sibling modules in a single project, ensuring they are in sync.
2. Zero-downtime upgrades requires two versions to run simultaneously the length of a request time-out (30s)
3. This implies a stable, stateless load balancer process. We can achieve all of this
4. Geographical failure 
