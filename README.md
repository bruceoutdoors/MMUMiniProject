MMU Mini Project Website
==========================

## Setup

You'll need Netbeans, Apache Tomcat and MySQL. Tomcat and MySQL is bundled with XAMPP. When connecting Tomcat with Netbeans, just point Netbeans to the `tomcat` folder in your XAMPP installation. Be sure to turn off Tomcat (you can do this from XAMPP control panel) when doing this as Netbeans writes a username and password to `tomcat/conf/tomcat-users.xml`.

**Prior to launching the website in your localhost, you need to start MySQL server from XAMPP control panel.** Netbeans will launch Tomcat for you. A database called `mmuminiproject` will be created (if it doesn't exist) and be populated with tables and initial data before running.

## Important Dev Notes

* Project relies on Struts2 (https://struts.apache.org/) and Struts2 Convention Plugin to utilize MVC. Controller (called actions in Struts2) and model files are located in source packages under the `app` package. View files (*.jsp) are under _Web Pages_, in `WEB-INF/content`.
* Migrations (a series of SQL files) are handled by FlywayDb (http://flywaydb.org/), and are located under source package `src/main/resources/db/migrations`. In Netbeans, you find this under "Other Sources". Each time a new migration is added, restart Tomcat and your database will be migrated.
* ORM (Object Relational Mapping) is handled by JPA (Java Persistence API), implemented by Hibernate.
* Dependencies are handled by Maven; to add new libraries, look for them in http://mvnrepository.com/ and add the dependency into `pom.xml`. 
* **Important:** If entities/models are added/removed, Netbeans will add them to `persistence.xml` automatically, but you will need to update the `persistence.xml` in the deployment version inside `OPENSHIFT-ENV` as well. Modify only the `<class/>` nodes, and don't touch anything else. 
* If `persistence.xml`, `web.xml`, or the `MigrateDb.java`, is not set properly, OpenShift doesn't give much indication aside a `Failed deployments: ./ROOT.war` error. 

## Tips

* To make the project structure look tidy, view your java project as a reduced tree: right click a blank area near your project, select `View Java Package As...`, and select `Reduced Tree`.

## Note-So-Important Dev Notes 

* Automatic creation of the database, as well as auto-migrating is handled by a ServletContextListener `core.listener.MigrateDb`.
* (Only for localhost) If you decide to make changes to database name, user or password, you need to edit `core.listener.MigrateDb` and `persistence.xml` (located in `src/main/resources/persistence.xml` under "Other Sources"). 
* The `OPENSHIFT-ENV` folder contains a modified version of `MigrateDb.java` and `persistence.xml` that uses OpenShift's environment variables to access database settings.

## Deploy to OpenShift

1. Add a new application. Choose **JBoss Application Server 7**. Set a URL that you want, and leave the source code blank so that OpenShift creates a git repo for you. 
2. Add **MySQL** catridge.
3. (optional) Add **PhpMyAdmin** catridge. This will allow you to see and modify the contents of the database.
4. git clone the source code via the SSH URL they gave.
5. Copy paste this maven project (source project) to that directory (deployment project), excluding the target and .git folder.  
6. Copy the contents of the `OPENSHIFT-ENV` folder into the deployment project. Do this each time you copy over from your source project.
7. Now your project is ready to be pushed to the cloud!
