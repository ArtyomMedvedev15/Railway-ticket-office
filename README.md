# Railway-ticket-office
[![Build Status](https://www.travis-ci.com/Tim01Bro/Railway-ticket-office.svg?branch=master)](https://www.travis-ci.com/Tim01Bro/Railway-ticket-office)
[![Coverage Status](https://coveralls.io/repos/github/Tim01Bro/Railway-ticket-office/badge.svg?branch=master)](https://coveralls.io/github/Tim01Bro/Railway-ticket-office?branch=master)

## Profiles
The project has the ability to change the configuration before starting. To do this, go to the settings of the file to run picture 1.
![pic1](https://github.com/Tim01Bro/Railway-ticket-office/blob/spring_profiles/documentation/pic1.PNG)


## Rest Client
The project has a rest client for getting data. 

### To work with the client, there are the following requests:

- GET: localhost:8181/api/clients/allClient - get all client 

- GET:  localhost:8181/api/clients/1 - get client with ID 1 

- GET: localhost:8181/api/clients/deleteClient/1 - delete client with ID 1 

- GET: localhost:8181/api/clients//findclientbyname/Tes - get client with name Like Tes;

- POST: localhost:8181/api/clients/saveClient Request body: {
     "id_train":24,
     "name_client":"Tim",
     "soname_client":"Medvedev",
     "date_purchase":"2021-01-30",
     "phone_client":"+375(33)3123123"
} - Save new client

- POST:  localhost:8181/api/clients/updateClient Request body: {
     "id_client:322,
     "id_train":24,
     "name_client":"Timmi",
     "soname_client":"Medvedev",
     "date_purchase":"2021-01-30",
     "phone_client":"+375(33)3123123"
} - Update client 

### For work with train service: 

- GET: localhost:8181/api/train/allTrain - get all train

- GET:  localhost:8181/api/train/1 - get train with ID 1 

- GET: localhost:8181/api/train/deleteTrain/1 - delete train with ID 1 

- POST: localhost:8181/api/train/saveTrain Request body:
{
    "name_train":"Test train",
    "typeTrain":"ECONOM",
    "departureStation":"MINSK",
    "arrivalStation":"BREST",
    "date_time_departure":"2021-01-23",
    "date_time_arrival":"2021-01-25",
    "available_ticket":123,
    "total_ticket":123,
    "price_ticket":23.3
} - Save new train

 - POST: localhost:8181/api/train/updateTrain Request body:
{
    "id_train":9,
    "name_train":"New test train",
    "typeTrain":"ECONOM",
    "departureStation":"MINSK",
    "arrivalStation":"BREST",
    "date_time_departure":"2021-01-23",
    "date_time_arrival":"2021-01-25",
    "available_ticket":123,
    "total_ticket":123,
    "price_ticket":23.3
} - Update train

- POST: localhost:8181/api/train/findtrainbydates Form data: 
{
  departure_date:2021-01-14
  arrival_date:2021-02-25
  departure_station_find:Brest
  arrival_station_find:Minsk
} - find train by dates and stations


## Generate rest api with swagger-codegen
###### To get started, before you generate it, go to the documentation site and get acquainted with how creating an api works https://swagger.io/docs/open-source-tools/swagger-codegen/ 

### So, after reading the documentation, we move on to the next stage of preparing the module.
To begin with, we add a dependency of the pom module:

    <dependency>
        <groupId>io.swagger</groupId>
        <artifactId>swagger-codegen-maven-plugin</artifactId>
        <version>${sw-gen-version}</version>
    </dependency>
        
This dependency adds a plugin to the module to create the api. The second step is to configure the plugin.

### So, first we insert the configuration into the pom, and then we analyze it.

    <plugin>
        <groupId>io.swagger</groupId>
        <artifactId>swagger-codegen-maven-plugin</artifactId>
        <version>2.3.1</version>
        <executions>
            <execution>
                <goals>
                    <goal>generate</goal>
                </goals>
                <configuration>
                    <inputSpec>${your-config}</inputSpec>
                    <language>java</language>
                    <library>resttemplate</library>
                    <apiPackage>${your-package}</apiPackage>
                    <generateApiTests>false</generateApiTests>
                      <output>${your-output}</output>
                    <configOptions>
                        <dateLibrary>java8</dateLibrary>
                        <java8>true</java8>
                    </configOptions>
                </configuration>
            </execution>
        </executions>
    </plugin>

- inputSpec - specifies the location of the configuration file that will be used to build the api .
- Next comes the language for generate, in our case java. 
- The library is a template that builds the api. In our case, this is a resttemplate, but there are many other templates that you can see here ( https://openapi-generator.tech/docs/generators/java/  check library )
- apipackage - package for generated classes.
- Then set the property that says you don't need to create tests.
- output - the path for generating classes.
- Then there are the additional properties, which can also be viewed here (https://openapi-generator.tech/docs/generators/java/).

**So after the plugin is set up, you need to build the project.**
Go to the terminal and run the following command: 
> **mvn clean compile.**

**After that, go to the path set in the configuration and use the generated code.**

## Angular module

#### First, let's install everything you need to work.

> - First you need to go to the site **https://nodejs.org/en/** download the recommended version. After installation, we check in the console. 
![check version](https://github.com/Tim01Bro/Railway-ticket-office/blob/master/documentation/nodescreen.png)
> - We also check the installed npm.
![check version](https://github.com/Tim01Bro/Railway-ticket-office/blob/master/documentation/npmscreen.png)
> - After you have installed node js, you need to install the angular cli to work with it. To install, go to the site **https://cli.angular.io** where the installation guideline is described. After installing angular, check the version in the console.
![check version](https://github.com/Tim01Bro/Railway-ticket-office/blob/master/documentation/angularscreen.png)
> - And also in the console we write the command to load the libraries 
> **npm install**

>  *Everything* is ready to **run**.

#### Starting angular module.
Before running the module with angular, we need to run our module with rest as they interact with each other. 
For run angular, you need to open the console and, through the cantilever directory change commands, go to the directory with the angular module.

Then enter the command into the conosli:

> **ng serve**

This command starts a server with a frontend.

![check version](https://github.com/Tim01Bro/Railway-ticket-office/blob/master/documentation/ngservescreen.png)

After that, in the browser, go to the address:
> **http://localhost:4200/**

![check version](https://github.com/Tim01Bro/Railway-ticket-office/blob/master/documentation/mainppagescreen.png)


## Java Faker Library
In test rest endpoint use java faker for generated fake data. You can change the localization for the data generator. To do this, set the java_faker_lang variable in the test configuration, look at picture. All locale you can check here https://github.com/DiUS/java-faker .

![java faker](https://github.com/Tim01Bro/Railway-ticket-office/blob/java_faker/documentation/javafaker.png)

If you do not set a value for this variable, then by default, the localization will be in English.


