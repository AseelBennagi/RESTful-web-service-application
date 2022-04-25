I have built a simple RESTful web service application using spring boot with spring web ,spring data jpa, and mysql. In this application you will be able to send HTTP request to create, get specific user, update, and delete.
This project is linked in my github.

Before you run the application, make sure to add database connections details for spring data jpa to connect to the database. application.properties should contain the following:
spring.datasource.username=  //here goes your mysql username
spring.datasource.password=  //here goes your mysql password
spring.datasource.url=jdbc:mysql://localhost:3306/  //after the last forward slash you should provide your database name for example:jdbc:mysql://localhost:3306/app
server.servlet.context-path=/app  //context path of our application to help the server knows exactly which application we deal with
spring.jpa.hibernate.ddl-auto=update //"update" used to update the database we provided since we have it already in mysql.


after you run the application with the provided database connection, then you can use Postman to send http request:
for POST request: http://localhost:8080/app/users
for GET request: http://localhost:8080/app/users/     // after the forward slash you should provide the userId  the application created to get a specific user. You can find it in ur database.
for PUT request: http://localhost:8080/app/users/     // after the forward slash you should provide the userId  the application created to update a specific user. You can find it in ur database.
for DELETE request: http://localhost:8080/app/users/  // after the forward slash you should provide the userId  the application created to delete a specific user. You can find it in ur database.