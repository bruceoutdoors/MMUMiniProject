MMU Mini Project Website
==========================

## Setup

You'll need Netbeans, Apache Tomcat and MySQL. Tomcat and MySQL is bundled with XAMPP. When connecting Tomcat with Netbeans, just point Netbeans to the `tomcat` folder in your XAMPP installation. Be sure to turn off Tomcat (you can do this from XAMPP control panel) when doing this as Netbeans writes a username and password to `tomcat/conf/tomcat-users.xml`.

**Prior to launching the website in your localhost, you need to start MySQL server from XAMPP control panel.** Netbeans will launch Tomcat for you. A database called `mmuminiproject` will be created (if it doesn't exist) and be populated with tables and initial data before running.

## Important Dev Notes

* `ERD.mwb` is a MySQL Workbench file (https://www.mysql.com/products/workbench/). We use this to generate our migrations via "Database > Synchronize Model...". Workbench generates SQL with database name inside, so to keep things simple make sure all databases have the same name (in OpenShift, it means project name = database name).
* Project relies on Struts2 (https://struts.apache.org/) and Struts2 Convention Plugin to utilize MVC. Controller (called actions in Struts2) and model files are located in source packages under the `app` package. View files (*.jsp) are under _Web Pages_, in `WEB-INF/content`.
* Migrations (a series of SQL files) are handled by FlywayDb (http://flywaydb.org/), and are located under source package `src/main/resources/db/migrations`. In Netbeans, you find this under "Other Sources". Each time a new migration is added, restart Tomcat and your database will be migrated.
* Try not to edit applied migration files, but if you have to, all databases that is created and modified by these migrations (yours, those that you are collaborating with, the database in OpenShift) must be wiped out before launching your application again, or your website and those collaborating with you might not even start from localhost (fail to launch at context error) and at openshift (it will just fail to deploy).
* ORM (Object Relational Mapping) is handled by JPA (Java Persistence API), implemented by Hibernate. 
* Each time you add/remove an entity, you need to add a class node in `persistence.xml` under the `OpenShift` persistence unit, like so: `<class>app.model.Toothbrush</class>`, should you forget to do this, you will be receive an error that only appears during deployment: `java.lang.IllegalArgumentException: Unknown entity`. Inconvenient, but I wasn't able to get `hibernate.archive.autodetection` to work in OpenShift despite my best efforts ): 
* Dependencies are handled by Maven; to add new libraries, look for them in http://mvnrepository.com/ and add the dependency into `pom.xml`. 
* If `persistence.xml`, `web.xml`, or the `MigrateDb.java`, is not set properly, OpenShift doesn't give much indication aside a `Failed deployments: ./ROOT.war` error. 

## Tips

* To make the project structure look tidy, view your java project as a reduced tree: right click a blank area near your project, select `View Java Package As...`, and select `Reduced Tree`.

## Note-So-Important Dev Notes 

* Automatic creation of the database, as well as auto-migrating is handled by a ServletContextListener `core.listener.MigrateDb`.
* (Only for localhost) If you decide to make changes to database name, user or password, you need to edit `core.listener.MigrateDb` and `persistence.xml` (located in `src/main/resources/persistence.xml` under "Other Sources"). 

## Deploy to OpenShift

1. Add a new application. Choose **JBoss Application Server 7**. Set a URL that you want, and leave the source code blank so that OpenShift creates a git repo for you. 
2. Add **MySQL** catridge.
3. (optional) Add **PhpMyAdmin** catridge. This will allow you to see and modify the contents of the database.
4. git clone the source code via the SSH URL they gave.
5. Copy paste this maven project (source project) to that directory (deployment project), excluding the target and .git folder.  
6. Now your project is ready to be pushed to the cloud!
