# 🏊🏼‍♀️ Mineral House Spa - A Spring Project for DP1
This project has been programmed as an assignment for DP1, a course of the Software Engineering degree from the University of Seville

Mineral House is a spa located in Newcastle, UK. This project is a website programmed in Java, which, using Pet Clinic as a template, implements all the functionalities that this business requires for its daily operation, including:

### No logged
* Room and Circuits access, in order to learn more about the services Mineral House provides

### As a client
* Personal editable profile listing all the appointments booked
* Can redeem tokens, which allows you to access appointments in time frames your subscription wouldn´t allow you to book
* Receive mails from the owner of the spa

### As an employee
* Personal profile listing your daily schedule
* Charge subscriptions to clients

### As an admin
* Enable, Edit and Delete clients and employees
* Create, Edit and Delete rooms and circuits
* Send email to employees and/or clients
* Create token codes
* Assign work to each employee, creating schedules and sessions in their profiles
* Check the earnings the bussines makes by consulting auto-generated income statements, breaking down the money in different categories. These statements contain charts for an easier reading

<img width="1042" alt="petclinic-screenshot" src="https://i.postimg.cc/Y9qnPPZv/Captura-de-pantalla-2021-01-20-121400.png">

## 📓 External libraries
This project uses the [Canvas JS - JSP Charts library](https://canvasjs.com/jsp-charts/), which at the same times requires [Google´s GSon library](https://github.com/google/gson). These both libraries are used in the Income Statements.
By default, this file is added in the build path of the project, but commonly it unsets by itself when the project is downloaded. In case this happens, follow this steps
### Steps (Using Eclipse):
1) Right click on the project
2) Build Path -> Configure Build Path
3) Add JARs
4) Open the Mineral House project and select gson-2.6.2.jar

## 📥 Database configuration
In its default configuration, Mineral House Spa uses an in-memory database (H2) which gets populated at startup with data. 

## 👨🏻‍💻 Working with Petclinic in your IDE
### Prerequisites
The following items should be installed in your system:
* Java 8 or newer
* git command line tool (https://git-scm.com/downloads)
* Your preferred IDE 
  * Eclipse with the m2e plugin
  * IntelliJ IDEA

## 🧑🏼‍💻 Running Mineral House Spa locally
### Steps:
1) On the command line
```
git clone https://github.com/gii-is-DP1/dp1-2020-g2-07.git
```
2) Navigate to Mineral House
Visit [http://localhost:80](http://localhost:80) in your browser.

3) Introduce the Login Credentials
* Admin:
  * User: admin1
  * Pass: 4dm1n
* Employee:
  * User: miguel
  * Pass: hola
* Client
  * User: juanma
  * Pass: hola
  
## 📝 License
The Spring PetClinic sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
