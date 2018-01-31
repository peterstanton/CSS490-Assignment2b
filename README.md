CSS-490 Weather Application, console version
======
This program is a RESTful API based weather checking application.
 
#### Does not work on
* Invalid, gibberish locations.
* Locations not recognized as places by Google's locating API.
* Places that don't report weather data, like the middle of nowhere, or North Korea.
 
## How-to use this code
* Ensure you have a Java compiler, preferably Java 9.0.4, installed.
* Navigate to the source code directory which contains the package Hello
* Compile the code with "javac Hello/*.java
* Run the program with "java Hello.Main".
* The program will run in a loop until you type "exit" at the prompt.

* You can optionally compile your own executable jar by running 
"jar cfvm myWeather.jar META-INF/MANIFEST.MF Hello/*.class"

* You can then run the .jar by running "java -jar myWeather.jar"

## Known bugs
* JSON responses can very by location leading to all sorts of different responses that need handling, 
some areas do not report weather data. This program cannot be responsible for omitted data in such cases.

* For some reason, I am having difficulty running my .jar executable by double clicking on it. At present I can
only run the executable from the command line.

* I am limited in my API calls. Dark Sky is limited to 1000 API calls daily. Google is limited to 2500 calls
daily. If those limits are reached, no further usage is possible as I have not given Dark Sky my billing 
information and I am enforcing free usage quotas on my Google API usage. Please do not test excessively.
 
## Dependencies
* Java 9

## Sources
* I got help from https://stackoverflow.com/a/7467629/4864069 on learning how to read JSON responses
from HTTP connections.
