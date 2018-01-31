CSS-490 Weather Application, console version
======
This program is a RESTful API based weather checking application.
 
#### Does not work on
* Invalid, gibberish locations.
* Locations not recognized as places by Google's locating API.
* Places that don't report weather data, like the middle of nowhere, or North Korea.
 
## How-to use this code
* Ensure you have a Java compiler, preferably Java 9.0.4, installed.
* Navigate to the source code directory.
* Compile the code with "javac \*.java"
* Run the program with "Java Hello.Main".
* The program will run in a loop until you type "exit" at the prompt.

## Known bugs
* JSON responses can very by location leading to all sorts of different responses that need handling, 
some areas do not report weather data. This program cannot be responsible for omitted data in such cases.
 
## Dependencies
* Java 9
