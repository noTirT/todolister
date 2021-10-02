# todolister
Backend for a todo-list project

## Used technology:

- Spring boot
- Couchbase as database

## Dependencies used so far:

```
<dependencies>

    <!-- couchbase plugins -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-couchbase</artifactId>
    </dependency>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>5.2.4.Final</version>
    </dependency>
    <dependency>
        <groupId>joda-time</groupId>
        <artifactId>joda-time</artifactId>
        <version>2.9.2</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Swagger-ui plugin -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-ui</artifactId>
        <version>1.5.2</version>
    </dependency>

    <dependency>
        <groupId>com.fasterxml.jackson.datatype</groupId>
        <artifactId>jackson-datatype-joda</artifactId>
        <version>2.13.0</version>
    </dependency>
</dependencies>
```

## Setup:

### Via Dockerfile:

```
docker build --tag=todolister:0.0.1-SNAPSHOT .

docker images

docker run -p 8080:8080 --name todoListBackend \
    -e 'CB_CONNECTION_STRING=couchbase://127.0.0.1' \
    -e 'CB_USERNAME=admin' \
    -e 'CB_PASSWORD=password' \
    -e 'CB_BUCKET_NAME=todoItems' \
    todolister:0.0.1-SNAPSHOT
```

### Via docker-compose

First set the environment variables in the env.list file

Then:

```
docker-compose up
```