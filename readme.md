### Usage

- Run the application and go on http://localhost:8080/
- Use the following urls to invoke controllers methods and see the interactions
  with the database:
    * `/Azubi/save?email=[email]&name=[name]`: create a new Azubi with an 
      auto-generated id and email and name as passed values.
    * `/Azubi/delete?id=[id]`: delete the Azubi with the passed id.
    * `/Azubi/get-by-email?email=[email]`: retrieve the id for the Azubi with the
      passed email address.

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations for the
database connection.

#### Prerequisites

- Java 7
- Maven 3

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run
