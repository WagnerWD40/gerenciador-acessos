FROM eclipse-temurin:21

ARG VERSION

WORKDIR /app

COPY build/libs/gerenciador-acessos-${VERSION}.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
