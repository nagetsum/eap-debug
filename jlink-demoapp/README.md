# jlink demo-app
## How to build custom jre using jlink command

```
$ cd imageconverter
$ mvn clean package

$ mkdir target/modules
$ jmod create --class-path target/imageconverter.jar --main-class org.example.imageconverter.Main target/modules/org.example.imageconverter.jmod
$ jlink --module-path /usr/lib/jvm/java-11-openjdk/jmods:target/modules --add-modules com.example.imageconverter --output jdk-imageconverter --launcher run-imageconverter=com.example.imageconverter
```
