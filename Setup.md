# Galleria
Check [User Accounts](#login-user-accounts)
### Project Setup Guide

```shell script
git clone https://github.com/sijanmaharjan/Galleria.git
```

##### Dependencies `included` `all-set`
[view folder](lib)
```
- EJB
- JPA
- eclipse-jpa
- mysql-connector-java
- jstl
- gson
- bcrypt
- groupdoc-watermark
```

##### Database Setup <br/> `entities` `views` `triggers` `stored-procedures` `mock-data included`

[Galeria.sql](assets/Galeria.sql) 

* required database: MySql
* create database named: Galeria
* Import [sql](assets/Galeria.sql) to database (hints: using phpMyAdmin)
* replace directories to add mock images (or just copy images to):
    * replace [web/images](web/images) with [assets/images](assets/images)
    * replace web/thumbnails with [assets/thumbnails](assets/thumbnails)
    
#### Running Project
* Open Project with Intellij IDEA
* Install GlassFish server and create server profile in Intellij
* In run configuration choose GlassFish server and set url `http://localhost:8080/web_war_exploded/galleria.oh` which is the homepage for general user
* run project on GlassFish server

 "Browser shall automatically open the provided url once deploy finishes"
* If you see `512` error message, then please make sure if mysql server is up and running in port `3306`


#### Login User Accounts
`from-mock-data` `try-logging-in`

These are user accounts of general users added through mock data [sql](#database-setup--entities-views-triggers-stored-procedures-mock-data-included).

| Username  | Password  | accessibility
|-----------|-----------|---------------
| johnD     | john123   | 
| alyssa    | alyssa    |
| max       | max       |
| blocked   | blocked   | blocked

### Admin User Accounts
Create Admin User Account using Admin Console of GlassFish Server

[Learn More About User Types](Readme.md#type-of-users)