# Supplemental specification

## Features

(Audit)
* Data like the address, the map points and the paths between the points are registered by the system during
it's execution.

(Reporting)
* Some invoices can be printed and presented to the client in the act of the delivery.

(Security)
* When a credit card is used, an authentication process with the user and communications with the bank must be ensured 
  in security.

(Language)
* English was the idiome adopted as being considered the universal language around the world. However, portuguese
might also be an alternative.

(Eletronic Mail)
* Mails are used as part of the informative process utilized by the system, in case any anomally or business
process has occured.

(Workflow)
* Deliveries are ordered and their status is viewed.
* The product basket is watched and checked-out.
* The majority of the data base elements are checked and update, in accordance to the interactions of the system.

## Usability

 (Documentation)
* Software engineering diagrams were made to provide a guide through the system.

(Error prevention)
* In case that any data is invalid or incorret, the system was made to warn about those errors, like if a product was
updated with a negative number.

(Aesthetics and design)
* Normal java console was used as main source of interface, with interactions by text files previously configurated.

(Consistency)
* According to the patterns utilized, the system can be very modular to be easily modified and provide good results.

(Patterns)
* Model View Controller, User Interface, Information Expert, High Coupling-Low Cohesion and the pattern Adapter were
  used.

## Reliability/Reliability

* The frequency of failure was diminuished to almost none due to the tests made.
* The possibilty of recovery is high.
* The possibility of prediction is very high.
* The accuracy is almost close to the reality in physical calculations.
* The average time between failures is reduced.

## Performance

* The response time is quick, however may take a few seconds to initialize the system.
* The memory consumption is not high.
* The CPU utilization is not high, in accordance to the memory consumption.
* The load capacity is big, with a data base to support a lot of data.
* The application is very available to be used.

## Supportability

* The system is very stable, can be easily adaptable, easy to maintain, compatible with Windows, configurated in Java,
  C, SQL Oracle and Assembley, easy to install, as it's space in disk isn't high and it can be applied to a large scale.

## +

### Design restrictions

- **Adopt good requirements identification practices and OO software analysis and design**
- **Programmed in SQL ORACLE, C, Assembley and Java**
- **API from Google to generate a QRCODE was used and integrated to the system**
- **Intellij Idea was used to developement, as well as Atom, SQL Developer and Visual Paradigm**

### Implementation restrictions

- **Implement the main core of the software in Java**
- **Saving all data utilized in a data base to ensure their integrity**
- **Adopt recognised coding standards**
- **The operating system can be Windows and Linux**
- **The resources were limited in the charging stations of the scooters, with a low level implementation in Assembley
adopted**

### Interface restrictions

- **Several useres at a time can interact with the platform, unless the system is overloaded**
- **The system can interact with the internet to send mails or process transactions**

### Physical restrictions

- **There are no physical restrictions to the hardware used, it only needs to support the software memory, CPU and
 operative system needs**
  
- **A scooter has at his peak 100 kilograms, including the courier, with an estimate of 75 kilograms**
- **A drone has 3,5 Kilograms, with a payload some totalizing not more than 10 kilograms**


