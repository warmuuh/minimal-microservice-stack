microservice stack:

blog post: http://cubiccow.blogspot.de/2015/08/diy-microservice-stack-with-java-and.html

features:

* rest-service (embedded jetty) (using http://sparkjava.com/)
* environment-based configuration (using https://github.com/typesafehub/config, i could have used java.util.Properties, but i dont like the properties-format and i wanted to have "layered" configuration handling already built in)
* dependency injection (using https://github.com/warmuuh/hardwire)
* logging (using simple-slf4j, i could have used java.util.logging, but i prefer the Slf4j api)
* metrics (using dropwizards metrics-core)
* healthchecks (using dropwizards metrics-healthchecks)
* json (to/from redis, to/from web) (using gson)
* redis-integration (using jedis)
* packaged with apache shade (minimized, which does not work for reflection/classpath-scanning based DI like Spring)
* rest-assured for testing