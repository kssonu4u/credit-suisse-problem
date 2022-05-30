# Processing event from logfiles

## Pre-requisite
- Please make sure Java8 version is installed
- Please make sure maven is installed
- Please make sure hsqldb is installed
- create a database credit_suisse_db
- create a table EVENT with below sql script
- CREATE CACHED TABLE PUBLIC.EVENT
  (ID VARCHAR(64) NOT NULL,
  TYPE VARCHAR(32),
  HOST VARCHAR(32),
  DURATION BIGINT NOT NULL,
  IS_ALERT BOOLEAN NOT NULL,
  PRIMARY KEY (ID))


## How to run

- Clone project from git(git clone https://github.com/kssonu4u/credit-suisse-problem.git)
- Go inside credit-suisse-problem folder( cd credit-suisse-problem/ ) 
- Run below command and make sure it is successfull.This will update/create new jar file in target folder
- mvn clean install
- Run below command
- java -jar target/credit-suisse-1.0-SNAPSHOT.jar  src/main/resources/logfile.txt
- Note the logs printed on the console with event saved and displayed.






