# JavaCatalogApp

A starter desktop item catalog application created using JavaFX and a MySQL database backend. Includes basic authentication as well as CRUD operations on the items in the catalog.

# How to run

1. Install MySQL Connector/Java from https://dev.mysql.com/downloads/connector/j/
2. Start the MySQL database, using CLI or MySQL workbench. 
3. Install the JavaFX 12 SDK and libraries from https://www.cs.rit.edu/~csci142/Resources/Java+Fx-12/Java+FX-12.html

```
git clone https://github.com/seraphimstreets/JavaCatalogApp.git
```

4. Navigate to the local directory

```
javac --module-path PATH-TO-JAVAFX12-SDK-LIB --add-modules javafx.controls,javafx.fxml *.java
java --module-path PATH-TO-JAVAFX12-SDK-LIB --add-modules javafx.controls,javafx.fxml MyGUI
```

5. The application should now be running.
