# CORBA Hello, world!

This code is quoted from [Getting Started with Java™ IDL in JDK document](https://docs.oracle.com/javase/7/docs/technotes/guides/idl/GShome.html)

```
# IDL to java
$ idlj -fall Hello.idl

# start orbd
$ orbd -ORBInitialPort 1050 -ORBInitialHost localhost

# start server
$ cd corba-helloworld-server
$ mvn clean package
$ cd target/classes
$ java server.HelloServer -ORBInitialPort 1050 -ORBInitialHost localhost

# start client
$ cd corba-helloworld-client
$ mvn clean package
$ cd target/classes
$ java client.HelloClient -ORBInitialPort 1050 -ORBInitialHost localhost
```
