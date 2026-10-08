# Lab 1 – Servlets

## Cerințe implementate
- `page1.html` și `page2.html` în `src/main/webapp/`
- `WelcomeServlet`: pagină de întâmpinare generată dinamic, cu formular (select 1 / 2)
- `ControllerServlet`: face forward către page1 sau page2 în funcție de valoare
- Logare în server log: metoda HTTP, IP client, user-agent, limbi, parametru
- `Client.java`: aplicație desktop care apelează servlet-ul; primește text simplu cu valoarea parametrului

## Cum se rulează
1. Se construiește aplicația (`mvn package`) și se da deploy pe Tomcat
2. Browser: `http://localhost:8081/demo/`
3. Client desktop: `java Client 2`

## Rezultate teste
- Browser, valoarea 1 → se afișează page1.html
- Browser, valoarea 2 → se afișează page2.html
- Client desktop, valoarea 2 → `Status: 200`, `Raspuns: 2`
