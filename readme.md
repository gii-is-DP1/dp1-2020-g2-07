# 🏊🏼‍♀️ Mineral House Spa - A Spring Project for DP1
Mineral House is a spa located in Newcastle, UK. This project is a website programmed in Java wich, using Pet Clinic as a template, implements all the functionalities that this bussines requires for its daily operation.

This project has been programmed as an assigment for DP1, a course from the ISW degree in the US


<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

## Running Mineral House Spa locally
Mineral House Spa is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:
```
https://github.com/gii-is-DP1/dp1-2020-g2-07.git
cd spring-petclinic
./mvnw package
java -jar target/*.jar
```
You can then access Mineral House Spa here: http://localhost:80/


Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):
```
./mvnw spring-boot:run
```
## Database configuration
In its default configuration, Mineral House Spa uses an in-memory database (H2) which gets populated at startup with data. 

## Working with Petclinic in your IDE
### Prerequisites
The following items should be installed in your system:
* Java 8 or newer
* git command line tool (https://git-scm.com/downloads)
* Your preferred IDE 
  * Eclipse with the m2e plugin
  * IntelliJ IDEA

### Steps:
1) On the command line
```
git clone https://github.com/gii-is-DP1/dp1-2020-g2-07.git
```
2) Navigate to Petclinic
Visit [http://localhost:80](http://localhost:80) in your browser.

# 📝 License
The Spring PetClinic sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
