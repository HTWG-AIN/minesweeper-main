# Minesweeper
This is a project for our Software Engineering class at the University of Applied Sciences in Konstanz, Germany.

The goal of this project and the resulting application is to demonstrate competance in software development using an agile development methodology.

This project demonstrates:

* Control with git
* Tests preceeding the writing of code
* Layered architecture
* Metrics with sonar
* Three types of patterns
* Components and interfaces

The user interface is console-based and is a textual user interface. The TUI and GUI run in parallel. If the TUI is changed, then the GUI updates to match and vice-versa.

### Running Minesweeper

Execute JarFile to run the game without the use of Eclipse

One can also do the following if desired:

1. Download the project as a zip file
2. Unzip the folder
3. Open Eclipse and import the file as follows: File > import > Gradle Project > Unzipped Minesweeper Folder
4. Click Finish
5. Run the program

### create docker image 

```
gradle shadowJar

docker build -t minesweeper .

```