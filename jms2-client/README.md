# JMS2 test application

Before you try to deploy jms2-client.war to deployment directory, you should configure as the following queue settings.

```
$JBOSS_HOME/bin/standalone.sh -c standalone-full.xml
$JBOSS_HOME/bin/jboss-cli.sh -c
jms-queue add --queue-address=HelloQueue --entries=java:/jms/queue/HelloQueue
```
