= How to setup the integration of EAP7.2 and IBM MQ 9.1.z

= Setup IBM MQ9.1.z on Docker

```
# yum install docker-ce docker-ce-cli containerd.io
# systemctl start docker
# systemctl enable docker
# docker login

# docker pull ibmcom/mq:9.1.3.0-r3
# docker run --name ibm-mq -e LICENSE=accept -e MQ_QMGR_NAME=QM1 -p 1414:1414 -p 9443:9443 ibmcom/mq:9.1.3.0-r3
```

= Setup EAP resoruce adaptor subsystem
1. Get `wmq.jmsra.rar` from the container that is the resource adaptor implementation for IBM MQIBM MQ9.1.

```
# docker cp ibm-mq:/opt/mqm/java/lib/jca/wmq.jmsra.rar .
```

2. Deploy wmq.jmsra.rar to EAP

```
$ cp wmq.jmsra.rar $JBOSS_HOME/standalone/deployments/
```

3. Setup EAP for resource-adaptors subsystem

First, start EAP to connect jboss-cli.sh.

```
$ $JBOSS_HOME/bin/standalone.sh -c standalone-full.xml
```

Then, configure the resource-adaptor for IBM MQ by JBoss CLI as the following:

```
// The following configration depends on IBM MQ on Docker default settings that is automatically configured on IBM MQ container startup time.
// Queue Manager: MQ1
// Queue: DEV.QUEUE.1
// Channel: DEV.APP.SVRCONN

$ $JBOSS_HOME/bin/jboss-cli.sh
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar:add(archive=wmq.jmsra.rar, transaction-support=XATransaction)
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd:add(class-name=com.ibm.mq.connector.outbound.ManagedConnectionFactoryImpl, jndi-name=java:jboss/IBM_MQ_CONNECTION_FACTORY, enabled=true)
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/admin-objects=queue-ao:add(class-name=com.ibm.mq.connector.outbound.MQQueueProxy, jndi-name=java:jboss/DEV.QUEUE.1)
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/admin-objects=queue-ao/config-properties=baseQueueName:add(value=DEV.QUEUE.1)
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/admin-objects=queue-ao/config-properties=baseQueueManagerName:add(value=QM1)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=hostName:add(value=127.0.0.1)
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=port:add(value=1414)
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=channel:add(value=DEV.APP.SVRCONN)
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=transportType:add(value=CLIENT)
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=queueManager:add(value=QM1)

# Work In Progress: the below XA recovery setting does not work fine.
#/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd:write-attribute(name=recovery-username,value=app)

:reload
/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd:test-connection-in-pool()
{
    "outcome" => "success",
    "result" => [true]
}
```

You can confirm whether the message was added by IBM MQ console after sending a message from application.

```
https://localhost:9443/ibmmq/console/
id: admin
pass: passw0rd
```

= How to configure a Queue for IBM MQ
Some of queue are already created by default on IBM MQ for Docker, but if you want to add a queue for IBM MQ, you can create by `runmqsc` command.

```
# docker exec -it ibm-mq /bin/bash
bash-4.2$ /opt/mqm/bin
bash-4.2$ ./runmqsc

# Create local queue `lq01`
define qlocal('lq01')
    11 : define qlocal('lq01')
AMQ8006I: IBM MQ queue created.

# Confirm local queue ` lq01`
display qlocal('lq01')
    17 : display qlocal('lq01')
```

= References
Using the IBM MQ resource adapter
https://www.ibm.com/support/knowledgecenter/en/SSFKSJ_9.1.0/com.ibm.mq.dev.doc/q031610_.htm

JBoss EAP Configuring Messasing
31.5. Deploying the IBM MQ Resource Adapter
https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.2/html/configuring_messaging/resource_adapters#deploy_the_ibm_mq_resource_adapter
