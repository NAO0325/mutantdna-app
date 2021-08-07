# Examen Mercadolibre (mutantdna-app)

En cumplimiento a los requerimientos estipulados en el documento pdf prueba adjunto el código, en el cual esta el desarrollo de cada uno de los puntos siendo de la siguiente forma:

1. El algoritmo para los recorridos de matraiz con las cadenas de ADN, se encuentra en la ruta ***[mutant_dna_app/src/main/java/com/mutant/dna/service/impl/MutantDnaService.java](https://github.com/NAO0325/mutantdna-app/tree/main/src/main/java/com/mutant/dna/service/impl)***
2. Para el desarrollo de la aplicación se utilizo Spring Boot 2.X y para la persistencia se utilizó una base de datos de PostgreSQL 9.X aunque el script se puede correr en versiones posteriores sin ninguna dificultad, la ruta del mismo es ***[mutantdna-app/src/main/resources/schema.sql](https://github.com/NAO0325/mutantdna-app/blob/main/src/main/resources/schema.sql)*** 
3. Para correr el código fuente a nivel local se debe contar mínimo con la version de JDK 8 o superior, el desarrollo del proyeto se realizó en maven version 3.5 o superior, para correrlo basta con tener configurado tanto el JAVA_HOME y M2_HOME, una vez configurada la base de datos ver ***application.yml*** y desde la carpeta raíz ejecutar los comandos -mvn clean install -mvn spring-boot:run. La aplicación esta configurada para responder por el puerto 8090 siendo uno de los endpoint de los servicios en local ej: http://localhost:8090/mutant
4. Las pruebas unitarias y de cobertura se hicieron con ***SonarQube*** estan sincronizadas con este repositorio y se pueden validar en mi cuenta ***[https://sonarcloud.io/dashboard?id=NAO0325_mutantdna-app](https://sonarcloud.io/dashboard?id=NAO0325_mutantdna-app) Nota:*** las pruebas de carga se realizaron Jmeter, pero ya que son sensibles al ambiente y discrepan del equipo en donde desarrollé la app si la dejo a criterio y/o herramienta de la cual dispongan para realizarlas.
5. La imagen de docker en repositorio de docker hub  se encuentra en el siguiente enlace ***[mutant-app:latest](https://hub.docker.com/layers/161676733/nao0325/mutant-app/latest/images/sha256-f3b71ec157528a4da2b762c1a54efd92fdd54a57aa94604bf93d1f8a2b02ef95?context=repo)***, la misma se desplego en AWS para esta prueba
6. Se incluye proyecto de postman con os servicios de prueba tanto de ambiente local como para la apliación desplegada en la nube en el la ruta ***[ mutantdna-app/src/test/resources/Spring_Mutant_App_Services.postman_collection.json ](https://github.com/NAO0325/mutantdna-app/blob/main/src/test/resources/Spring_Mutant_App_Services.postman_collection.json)***

Para cualquier duda comentarió o sugerencia estaré disponible en cuaquier momento del dia vía email o teléfono celular.

***Gracias!***

