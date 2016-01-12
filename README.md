MMU Mini Project Website
==========================

## Setup

You'll need Netbeans, Apache Tomcat and MySQL. Tomcat and MySQL is bundled with XAMPP. When connecting Tomcat with Netbeans, just point Netbeans to the "tomcat" folder in your XAMPP installation. Be sure to turn off Tomcat when doing this as Netbeans writes a username and password to *tomcat/conf/tomcat-users.xml*.

With PhpMyAdmin, create a database called **mmuminiproject** and the site should populate it with tables and initial data and start running.

## Important Dev Notes

* Project relies on Struts2 (https://struts.apache.org/) to utilize MVC. Controller (called actions in Struts2) and model files are located in source packages under com.mmuminiproject. View files (*.jsp) are under *Web Pages*, in **WEB-INF/content**.
* Migrations (a series of SQL files) are handled by FlywayDb (http://flywaydb.org/), and are located under source package **src/main/resources/db/migrations**. In Netbeans, you find this under "Other Sources". Each time a new migration is added, restart Tomcat and your database will be migrated.
* Dependencies are handled by Maven; to add new libraries, look for them in http://mvnrepository.com/ and add the dependency into **pom.xml**. 

## Tips

* To make the project structure look tidy, view your java project as a reduced tree: right click a blank area near your project, select "View Java Package As...", and select "Reduced Tree".

## Note-So-Important Dev Notes 

* Perhaps you wonder why actions need to be located under com.mmuminiproject package. The *action* package was initially a base package, but somehow it affected the template mapping (action map to a jsp file) when involving subdirectories (mywebsite/subdir/subdir/action).
* Automatic migration is handled by a ServletContextListener *MigrateDb* inside listener package.
