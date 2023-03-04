# TimeSheet
My final project for thr Java course. It is a web application which employees can use for time tracking. Information about employees, clients, projects etc. is stored in a MySQL database and displayed in the app. There are two types of users: admin and a regular employee. An employee can only add and modify their own entries, while an admin can modify every table in the database (add, update, delete). In order to achieve all of this, every page in the web app is associated with a servlet which handles all the actions using GET and POST methods. The connection to the database was made with JDBC. The design was made with bootstrap and features the concept internalization.

Tools and skills used: JAVA, Servlets, JDBC, Maven, Apache Tomcat, IntelliJ, JSP, Windows 11, MySQL Workbench, Internationalization, Bootstrap