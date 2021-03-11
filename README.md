# JSONValidation

## What it does
This program  will read in the Json file sent in and output any
   records:
   1. That are a duplicate of another record
   2. `name` field is null, missing, or blank
   3. `address` field is null, missing, or blank
   4. `zip` is null, missing, or an invalid U.S. zipcode

## Build Instructions
First run
```
mvn clean package
```
Then to test manually:
```
java -cp target/JSONValidation-1.0-SNAPSHOT-jar-with-dependencies.jar validator.JSONVerify
```
