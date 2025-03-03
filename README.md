# Spring Boot Microservices

## Configure Authentication for Docker HUB
1. **In pom.xml:** [not recommended]
```xml
            <plugin>
                <groupId>com.google.cloud.tools</groupId>
                <artifactId>jib-maven-plugin</artifactId>
                <version>3.2.1</version>
                <configuration>
                    <from>
                        <image>eclipse-temurin:17.0.4.1_1-jre</image>
                    </from>
                    <to>
                        <image>docker.io/babu103/${project.artifactId}</image>
                        <auth>
                            <username>docker-hub-username</username>
                            <password>docker-hub-password</password>
                        </auth>
                    </to>
                </configuration>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>dockerBuild</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

2. **In settings.xml:** [recommended]
```xml
<settings>
    <servers>
        <server>
            <id>registry.hub.docker.com</id>
            <username>docker-hub-username</username>
            <password>docker-hub-password</password>
        </server>
    </servers>
</settings>
```

### zipkin service
```shell
docker run -d -p 9411:9411 openzipkin/zipkin
```

### keycloak service
```shell
docker run -p 8181:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.1.2 start-dev
```

## How to run the application using Docker

1. Run `mvn clean package -DskipTests` to build the applications and create the docker image locally.
2. Run `docker-compose up -d` to start the applications.

## How to run the application without Docker

1. Run `mvn clean verify -DskipTests` by going inside each folder to build the applications.
2. After that run `mvn spring-boot:run` by going inside each folder to start the applications.
