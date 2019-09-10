# Hybrid Library REST API web application

Implement Hybrid Library sample application with following features:

* Application users should be populated automatically on application startup
* Application users can have different role
* User password should be hashed
* Admins can fetch registered user(s) (password should not be exposed)
* Admins can manage books (create, update, delete), whereas both admins and regular users can fetch books
* Book cannot be deleted if there are rented copies
* Admins and regular users can rent/return books if there are available copies
* Book rent period should be configurable
* Admins can fetch most rented book
* Admins can fetch overdue book returns
* All implemented endpoints should be secured
* Use Swagger to document endpoints
* Use Logback to define configurable log files location and time & size based rolling policy

### Resources

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [Maven](https://maven.apache.org/)
* [H2 database](https://www.h2database.com/html/main.html)
* [Lombok](https://projectlombok.org/)
* [Logback](https://logback.qos.ch/)
* [Swagger](https://swagger.io/)