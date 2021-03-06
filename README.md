# sese2015
Practical part of the course *Seminar aus Software Entwicklung*.

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
* Run ```npm install -g gulp``` 
* Run ```mvn clean install```
* In case of success: switch to ```/path/to/sese2015/``` and run ```mvn clean install```

###Start goldenlion (```java -jar```)
```bash
cd /path/to/sese2015
mvn clean install
java -jar goldenlion-server/target/goldenlion-server-0.0.1-SNAPSHOT.jar #this will run the server :8080
cd goldenlion-ui 
gulp serve #this will run the ui at :3000
```
Run [http://localhost:3000](http://localhost:3000) in your browser.

###Start goldenlion (IntelliJ)
Import project from existing source. Run ```GoldenlionAppication``` 
* If not present, create a run-configuration with the main class: ```sese2015.g3.goldenlion.GoldenlionApplication```).
* If IntelliJ produces compilation errors, run ```mvn clean install``` in the project's root directory
* If IntelliJ still produces compilation errors, go to *File > Settings > Build, Execution, Deployment > Build Tools > Maven > Importing* and set the checkmark "Import Maven projects automatically"

###Debugging with H2
If you want to have a look into goldenlion's h2 database, checkout the following:
```bash
cd ~/.m2/repository/com/h2database/h2/1.4.190/
java -jar h2-1.4.190.jar
```
Wait a few second and watch a browser window popup. The connectionstring + creds. can be found in ```goldenlion-server/src/main/resources/application.properties```

**Attention:** currently, H2 only allows one connection, so make sure to stop the goldenlion app before lanching the h2 viewer and vice versa.

###Test data
`cd /path/to/sese2015`


*Create a new user:*
```bash
# Register a user 
curl -X POST "http://localhost:8080/api/register" -H "Content-Type: application/json" -d '{ "firstname": "Max", "lastname": "Muster", "email": "max@muster.com" }'
# Returns {"password": "..." }
```
*Login (using curl):*
```bash
curl -X POST "http://localhost:8080/api/login" -H "Content-Type: application/json" -d '{ "username": "tester@goldenlion.tk", "password": "test" }'
```
*Login (using lua script):*

The login.lua script will store a authtoken to ./lua/authtoken, which makes it easier to run the authenticated curl-commands
```bash
cd ./lua
./login.lua [username password]    # uses default credentials, if no args given
cd ..
```
*Create a new Reservation*
```bash
curl -X POST "http://localhost:8080/api/reservation" -H "Content-Type: application/json;" -H "X-Auth-Token: $(cat lua/authtoken)" -d '{ "roomId": 1, "startDate": "2015-12-26", "endDate": "2015-12-29", "customerIds": [1,2] }'
```

*Check if a given room is free*
```bash
curl -X GET "http://localhost:8080/api/reservation?roomid=1&startdate=2015-12-12&enddate=2015-12-15" -H "X-Auth-Token: $(cat lua/authtoken)"
```

###Test User
We create a test user on startup - tester@goldenlion.tk
ask for the testuser password ;)
