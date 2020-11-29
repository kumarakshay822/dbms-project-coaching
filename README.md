# DBMS Project (CSE-361) - Spring Framework

This repository contains source code for the DBMS Project - Coaching Institute Management System.

- Deployed URL: https://dbms-coaching-ashish.herokuapp.com (MySQL Database)
- Tables: Normalized upto BCNF, 24 tables, 143 attributes in total.
- [Relational Schema](relational-schema.png)
- To run the project:
  - Login to mysql and create the database: `CREATE DATABASE coaching;`
  - Create all tables: `mysql -u root -p coaching < database.sql`
  - Set the value to the following environment variables:
    - `SPRING_DATASOURCE_URL`
    - `SPRING_DATASOURCE_USERNAME`
    - `SPRING_DATASOURCE_PASSWORD`
    - `GMAIL_EMAIL`
    - `GMAIL_PASSWORD`
    - `INSTAMOJO_CLIENT_ID`
    - `INSTAMOJO_CLIENT_SECRET`
  - Run the project: `./mvnw spring-boot:run`


## Roles

**Admin Role** \
Username - admin \
Password - admin

**Staff Role** \
Username - staff1, staff2, etc \
Password - staff

**Teacher Role** \
Username - teacher1, teacher2, etc \
Password - teacher

**Student Role** \
Username - student1, student2, etc \
Password - student
