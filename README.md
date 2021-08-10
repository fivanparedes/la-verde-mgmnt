# La Verde Management
Java app for control and management of Yerba for La Verde S.A. Part of the course of Object-Oriented Programming I for Universidad Nacional de Misiones.
Course approved!

## Requierements

* Java/OpenJDK 11.
* JavaFX 11.
* Eclipselink 3.0.
* PostgreSQL 12.

## Installation
No installation required, just download the zip and run it via commandline.

For other operating systems than linux, download the corresponding [JavaFX libs](https://gluonhq.com/products/javafx/) and point to its `lib` folder using the `--module-path` argument. Downloads for the JPA are [here](https://www.eclipse.org/eclipselink/#download), and the PostgreSQL JDBC42.2 [here](https://jdbc.postgresql.org/download.html).


## Running
Run it from the command line using this command:
```bash
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -jar la-verde-mgmnt.jar 
```
In the lib folder there are Linux libs, so be sure to download the ones matching your operating system before running the app.

## Compiling from source
Visual Studio Code is recommended, with the Java Extension Pack installed. Clone the repository and open it using VS Code.

The Classpath should include the folders `src` and `lib` and the settings.json file should include the same vmArgs to run the jar file:

```json
"java.debug.settings.vmArgs": "--module-path ./lib --add-modules javafx.controls,javafx.fxml"
```

## Dev Team

* Paredes Fernando (me)
* Rodriguez Facundo
* Antoniak Rodrigo

## License
[LGPL](https://www.gnu.org/licenses/lgpl-3.0.en.html)

