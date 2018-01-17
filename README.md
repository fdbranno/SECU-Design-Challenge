# Spring Boot, MySQL, JPA, Hibernate Rest API

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

## Steps to Setup

**1. Clone the application**

```bash
git@github.com/fdbranno/SECU-Design-Challenge.git
```

**2. Create Mysql database**
```bash
create database secu
```

Run script 'setup.sql' to create tables and test data.

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**2. Build and run the app using maven**

```bash
mvn package
java -jar target/waiter-rater-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Wire Frames

[Wire Frames and Flow included in the Wiki](https://github.ncsu.edu/fdbranno/Design-Challenge/wiki/Wire-Frames-&-Flow)

