# banking
Multi-threading money transfer application

# Problem Statement
Design and implement a RESTful API (including data model and the backing implementation) for
money transfers between accounts.
Explicit requirements:
1. You can use Java or Kotlin.
2. Keep it simple and to the point (e.g. no need to implement any authentication).
3. Assume the API is invoked by multiple systems and services on behalf of end users.
4. You can use frameworks/libraries if you like (except Spring), but don't forget about
requirement #2 and keep it simple and avoid heavy frameworks.
5. The datastore should run in-memory for the sake of this test.
6. The final result should be executable as a standalone program (should not require a
pre-installed container/server).
7. Demonstrate with tests that the API works as expected.
Implicit requirements:
1. The code produced by you is expected to be of high quality.
2. There are no detailed requirements, use common sense.

# Approach taken
To replicate the banking applicaton

## Getting Started

### Installing 
```
git clone https://github.com/dnagaraj84/banking.git
```

# Running the build and tests

Demo-able unit test to verify functionality & test the intregrity of the solution provided.

#### Unit Test
```
com.revolut.banking.AccountServiceTest

```

#### Build Artifact
```
mvn clean install
```

#### Deployment
```
java -jar target\banking.jar OR run the Application.java
```

## Libraries Used

Maven is configured to fetch these libraries.
```
log4j-1.2.17.jar for logging
jetty-server-9.4.19.v20190610.jar
junit-4.12.jar for JUnit test cases.

```

## Built With
* Java
* Junit
* Maven

Note - Output folder is kept to peek into the results. When you run the application these files will get generated as well.
