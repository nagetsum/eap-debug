# Test S2JDBC app for EAP 7

Create scaffold project:
```
mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate -DarchetypeRepository=https://www.seasar.org/maven/maven2/ -DarchetypeGroupId=org.seasar.sastruts -DarchetypeArtifactId=sa-struts-archetype -DarchetypeVersion=1.0.4-sp9.1 -DgroupId=org.example.sastruts.test -DartifactId=sastruts-test -Dversion=0.1
```

Seaser2 on EAP6 later requires `s2-jbossas7`.

For more details, see below:
http://wadahiro.hatenablog.com/entry/2012/07/22/103636

Note: `http://wadahiro.github.com/maven/release` has been already retired(404 not found), so we need to import local maven repository as follows: 
```
git clone https://github.com/wadahiro/maven.git
cd maven/release/org/seasar/jboss/s2-jbossas7/0.2
mvn install:install-file -Dfile=s2-jbossas7-0.2.jar -DgroupId=org.seasar.jboss -DartifactId=s2-jbossas7 -Dversion=0.2 -Dpackaging=jar -DgeneratePom=true
```

Datasource settings for S2JDBC is in `src/resources/jdbc.dicon`:
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE components PUBLIC "-//SEASAR2.1//DTD S2Container//EN"
	"http://www.seasar.org/dtd/components21.dtd">
<components namespace="jdbc">
	<include path="jta.dicon"/>
	<!-- from JNDI -->
	<component name="DataSource"
		class="javax.sql.DataSource">
		@org.seasar.extension.j2ee.JndiResourceLocator@lookup("java:jboss/datasources/PostgresDS")  <<<== need to modify your datasource JNDI name
	</component>
</components>
```

Step to deploy:
```
mvn package
cp target/sastruts-test.war $JBOSS_HOME/standalone/deployments
$JBOSS_HOME/bin/standalone.sh

curl http://localhost:8080/sastruts-test/index/

<html>
<head>
    <title>Book list</title>
</head>
<body>
    <ul>        
            <li>id = 1, title = java book</li>        
            <li>id = 2, title = python book</li>
            <li>id = 3, title = openshift book</li>
    </ul>
</body>
</html>
```



