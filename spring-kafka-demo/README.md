# Spring Kafka demo application on EAP7.4
## Environment
- JBoss EAP 7.4
- OpenJDK8
- Spring Boot 2.6.3
- Spring for Apache Kafka 2.8.2
- Apache Kafka 3.1.0

## Steps to deploy

1. start zookeeper and kafka in localhost
```
cd kafka_2.13-3.1.0
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

2. add disable logging api dependencies to `JBOSS_HOME/standalone/configuration/standalone.xml`
```
diff -u standalone.xml.original standalone.xml
--- standalone.xml.original	2022-02-22 11:54:56.128715704 +0900
+++ standalone.xml	2022-02-22 10:55:20.586043928 +0900
@@ -93,6 +93,7 @@
     </management>
     <profile>
         <subsystem xmlns="urn:jboss:domain:logging:8.0">
+            <add-logging-api-dependencies value="false"/>
             <console-handler name="CONSOLE">
                 <level name="INFO"/>
                 <formatter>
```

3. build the application
```
mvnw clean package
...
[INFO] Building war: /home/nagetsum/work/spring-kafka-demo/target/spring-kafka-demo.war
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.6.3:repackage (repackage) @ spring-kafka-demo ---
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.547 s
[INFO] Finished at: 2022-02-22T11:58:19+09:00
[INFO] ------------------------------------------------------------------------
```

4. deploy spring-kafka-demo.war to `JBOSS_HOME/standalone/deployments/`
```
cp target/spring-kafka-demo.war JBOSS_HOME/standalone/deployments/
```

5. start JBoss EAP
You can see following message in the console:
```
JBOSS_HOME/bin/standalone.sh
...
12:01:06,728 INFO  [io.undertow.servlet] (ServerService Thread Pool -- 88) 2 Spring WebApplicationInitializers detected on classpath
12:01:07,024 INFO  [stdout] (ServerService Thread Pool -- 88) 
12:01:07,024 INFO  [stdout] (ServerService Thread Pool -- 88)   .   ____          _            __ _ _
12:01:07,024 INFO  [stdout] (ServerService Thread Pool -- 88)  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
12:01:07,024 INFO  [stdout] (ServerService Thread Pool -- 88) ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
12:01:07,024 INFO  [stdout] (ServerService Thread Pool -- 88)  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
12:01:07,024 INFO  [stdout] (ServerService Thread Pool -- 88)   '  |____| .__|_| |_|_| |_\__, | / / / /
12:01:07,024 INFO  [stdout] (ServerService Thread Pool -- 88)  =========|_|==============|___/=/_/_/_/
12:01:07,025 INFO  [stdout] (ServerService Thread Pool -- 88)  :: Spring Boot ::                (v2.6.3)
12:01:07,025 INFO  [stdout] (ServerService Thread Pool -- 88) 
12:01:07,031 INFO  [org.hibernate.validator.internal.util.Version] (background-preinit) HV000001: Hibernate Validator 6.0.22.Final-redhat-00002
12:01:07,075 INFO  [stdout] (ServerService Thread Pool -- 88) 2022-02-22 12:01:07.074  INFO 156739 --- [read Pool -- 88] o.e.s.k.kafkademo.ServletInitializer     : Starting ServletInitializer using Java 1.8.0_322 on nagetsum.nrt.csb with PID 156739 (started by nagetsum in /home/nagetsum/jboss/eap7/7.4.0/jboss-eap-7.4)
12:01:07,076 INFO  [stdout] (ServerService Thread Pool -- 88) 2022-02-22 12:01:07.076  INFO 156739 --- [read Pool -- 88] o.e.s.k.kafkademo.ServletInitializer     : No active profile set, falling back to default profiles: default
12:01:07,799 INFO  [io.undertow.servlet] (ServerService Thread Pool -- 88) Initializing Spring embedded WebApplicationContext
12:01:07,799 INFO  [stdout] (ServerService Thread Pool -- 88) 2022-02-22 12:01:07.799  INFO 156739 --- [read Pool -- 88] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 689 ms
...
12:01:09,659 INFO  [org.jboss.as.server] (ServerService Thread Pool -- 42) WFLYSRV0010: Deployed "spring-kafka-demo.war" (runtime-name : "spring-kafka-demo.war")
12:01:09,690 INFO  [org.jboss.as.server] (Controller Boot Thread) WFLYSRV0212: Resuming server
12:01:09,693 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0025: JBoss EAP 7.4.0.GA (WildFly Core 15.0.2.Final-redhat-00001) started in 7712ms - Started 534 of 736 services (348 services are lazy, passive or on-demand)
12:01:09,694 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0060: Http management interface listening on http://127.0.0.1:9990/management
12:01:09,694 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0051: Admin console listening on http://127.0.0.1:9990
```
