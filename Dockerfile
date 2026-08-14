FROM maven:3-eclipse-temurin-26 AS build
WORKDIR /build

COPY pom.xml ./
RUN mvn dependency:resolve

COPY src ./src
RUN mvn clean package -DskipTests

RUN ls -lh target

FROM eclipse-temurin:26
WORKDIR /app

COPY --from=build /build/target/site-generation*.jar site-generation.jar

EXPOSE 40400

CMD ["java", "-jar", "site-generation.jar"]
