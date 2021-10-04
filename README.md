# CS Event Details App

## Prerequisites
* Java 8
* Maven

## Overview
This Spring Boot app reads the contents of a log file and parses it's json objects line by line,
treats the data and finally persists the result in the file based HSQLDB database using JPA and
Hibernate.

The generated tables are:
1. event_details
   * id 
   * duration
   * alert
2. server_event_details
   * id
   * duration
   * alert
   * type
   * host

## Start HSQLDB
Navigate to `hsqldb` folder and type the command:
```bash
java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/events_db --dbname.0 events_db
```

HSQLDB provides a simple DB Manager, to open the GUI, navigate to `hsqldb/lib` and type:
```bash
java -jar hsqldb.jar
```
> The necessary credentials to access the database can be found on the configuration section below.

## Running the App
By default, the app will read the `logfile.txt` present on `src/main/resources/` folder. To run
the app type the following command on the project's root folder:
```bash
mvn spring-boot:run
```

It's also possible to pass the `absolute path` of a different file as an argument:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--logfile.path=<file-absolute-path>
```

## Running the tests
Use the following command to run the tests:
```bash
mvn clean test
```

## Configuration
The default configuration can be found at `src/main/resources/application.properties`:
```properties
logfile.path=@logfile.path@

# LOGGING
logging.level.com.cs=DEBUG
logging.level.org.springframework=INFO
logging.level.org.hibernate.SQL=INFO
logging.level.org.hibernate.type=INFO

# DATASOURCE (to run the in-memory HSQLDB database, comment the following 3 lines)
spring.datasource.url=jdbc:hsqldb:hsql://localhost:9001/events_db
spring.datasource.username=SA
spring.datasource.password=

# JPA
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.HSQLDialect
spring.jpa.hibernate.ddl-auto=update
```