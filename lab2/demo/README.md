# Lab 2 – Spring Boot

## Cerințe implementate
- Proiect generat cu Spring Initializr (Spring Web, DevTools, Actuator)
- Tomcat rulează pe portul 8081
- Endpoint `/hello` care returnează mesajul din `application.properties`
- Actuator adăugat în `pom.xml` (`spring-boot-starter-actuator`)

## Cum se rulează
`mvn spring-boot:run`

## Rezultate teste
- `GET http://localhost:8081/hello` → `Hello from Spring Boot!`
- `GET http://localhost:8081/actuator` → lista endpoint-urilor
- `GET http://localhost:8081/actuator/health` → `{"status":"UP"}`
