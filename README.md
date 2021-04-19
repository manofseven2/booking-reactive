**Room Booking - Sample code for reactive programming**

**Problem description**

To make a successful booking transaction, the user needs to have enough balance in the account. User have initial credit limit. When the available limit exceeds the booking cost, the transaction cannot be successful. The transactions should be processed in the same order as they appear as part of the supplied input.


**Sample Input**

Amir,Azimi,amir.azimi.alasti@gmail.com,120,TR0005


Martin,Belak,martin.belak@gmail.com,300,TR0003

**Assumptions**

- To use the api user should be in our database.
- Initial limit of each user should be specified.
- The application logs all successful and failed reservation requests in database.
- The application should return all of failed transactions
- webFlux and R2DBC are used
- H2 database is used

**How to build**

This project is a maven based project and you can use the below command to build it:

`mvn clean install`

**Deployment**

You can create a docker file with this command:

`docker build -t booking-reactive:latest .`

**Running with docker composer**

This command is used to run with docker composer

`docker-compose up -d`

to stop running docker container:

`docker-compose down`


`Endpoints`
--------------------------------------------
`/resources/bookings/failed/dtos`   |  `GET`   | list of all failed transactions
--------------------------------------------
`/resources/bookings`  | `POST`  |   save a transaction 
`content-type: text/plain`
--------------------------------------------