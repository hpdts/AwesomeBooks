# Awesome books


### Getting Started

Create your project with Spring initializer.
http://start.spring.io/

Install Lombok plugin for compiling

Google Books Wrappers from
https://www.5balloons.info/consuming-rest-api-in-java-spring-through-resttemplate/

##Docker

./gradlew build docker -x test

docker-compose up -d --build


##Search by Author

http://localhost:8080/books?search=author:pablo

##Search by Editor

http://localhost:8080/books?search=editor:penguin

### Deployment

Just run gradle wrapper

```
./gradlew  bootRun

```

### Books Table

create schema awesomebooks;
CREATE TABLE BOOKS (BOOK_ID int(11) NOT NULL AUTO_INCREMENT,TITLE varchar(300) DEFAULT NULL,AUTHOR varchar(300) DEFAULT NULL,EDITOR varchar(300) DEFAULT NULL,PRIMARY KEY (BOOK_ID)) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=latin1;






