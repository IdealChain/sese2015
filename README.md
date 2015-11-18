# sese2015
Practical unit of the course *Seminar aus Software Entwicklung*.

###Setup issues with development tools
```bash
cd /path/to/sese2015
mvn clean install
```
If your development machine is setup nicely, the commando above should install all dependencies (node, bower, gulp...). However, if this does not work for you, here are some helpful tips. 

* Switch to ```/path/to/sese2015/goldenlion-ui``` and run ```npm install```
* Ignore warnings (WARN) for dependency issues
* If python is missing:
  * Install python (version 2)
* No python in env. ```PYTHON```?
  * add path to your python installation to your env. variables
    * Find path: ```whereis python```
    * Add ```export PYTHON=/usr/bin/python``` to your ```~/.bashrs``` (use *your* path to python)
    * ```source ~/.bashrc```
* If there are still python errors like ```gyp ERR! stack Error: spawn /usr/bin/python ENOEN```, ignore them for now. They don't seem to mess anything up.
* Run ```bower install```
  * If ```bower: command not found```, run ```npm install -g bower```
* Run ```mvn clean install```
* In case of success: switch to ```/path/to/sese2015/``` and run ```mvn clean install```

###Start goldenlion (```java -jar```)
```bash
cd /path/to/sese2015
mvn clean install
java -jar goldenlion-server/target/goldenlion-server-0.0.1-SNAPSHOT.jar
```

###Start goldenlion (IntelliJ)
Import project from existing source. Run ```GoldenlionAppication``` 
* If not present, create a run-configuration with the main class: ```sese2015.g3.goldenlion.GoldenlionApplication```).
* If IntelliJ produces compilation errors, run ```mvn clean install``` in the project's root directory
* If IntelliJ still produces compilation errors, go to *File > Settings > Build, Execution, Deployment > Build Tools > Maven > Importing* and set the checkmark "Import Maven projects automatically"
