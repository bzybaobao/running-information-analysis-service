# Running Information Analysis Service

## Steps of demo

### Step1 - Use Docker to run the MySQL Server
```
cd running-information-analysis-service
docker-compose up -d
```

### Step2 - Create MySQL database
#### 1. connect MySQL server
```mysql
mysql -h localhost -P 3306 --protocol=tcp -u root -ppassword
```
#### 2. create database
```
mysql> show databases;
mysql> create database running_information_analysis_service_db;
```

### Step3 - Run your project
#### 1. maven build
```
cd running-information-analysis-service
mvn clean install
```
#### 2. run fat .jar file
```
cd target
java -jar running-Information-analysis-service-1.0.0.BUILD-SNAPSHOT.jar
```

### Step4 - Test REST APIs
| REST Endpoints                     |  HTTP Method  |Pagination Parameters                                                             |                          Description                             |
|:-----------------------------------|:-------------:|:--------------------------------------------------------------------------------:|:----------------------------------------------------------------:|
|  /upload                           |      POST     |none                                                                              |upload raw json format user data to the database                  |
| /fetchAll/info                     |      GET      |page(required = false, defaultValue = 0); size(required = false, defaultValue = 2)|fetch running information (result is sorted by healthWarningLevel)|
| /check/level/{`healthWarningLevel`}|      GET      |page(required = false, defaultValue = 0); size(required = false, defaultValue = 7)|obtain running information of certain healthWarningLevel          |
| /delete/{`runningID`}              |     DELETE    |page(required = false, defaultValue = 0); size(required = false, defaultValue = 7)|delete the running information of inputted user's running id      |