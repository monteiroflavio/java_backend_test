# Java Backend Test for LuizaLabs Challenge

This README file helps one to setup the Backend Test environment and documents functionalities developed for this challenge.

## Summary

* Setup
* Usage
* Development Documentation

## Setup

### Third-part software needed
Prior to the setup, make sure you have at least Git, Java Development Kit 8 or superior and Maven 3.3.9  installed. If you don't, please install them.

#### Git installation

If you are using a Debian-like distro, execute the following command to install Git:
```sh
    $ sudo apt install git
```
or
```sh
    $ apt install git
```
if you are logged as root.

#### Java installation

Download Java Development Kit version 8 or superior from [Oracle's official download page](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) and install it. If you're using a Debian-like distro, simply move to the directory where the tarball file has been downloaded and execute the following commands:

```sh
    $ tar -xzvf jdk-<JDK_VERSION>-linux-<ARCHITECTURE>.tar.gz
```

to extract the JDK. After that, set your enviroment Java's variable to the extracted directory, by modifying `.bashrc` file:

```sh
    $ sudo vi .bashrc
```

On the end of the file, insert, the following lines. Don't forget to change `<JDK_PATH>` and `<JDK_VERSION>` to your context.

```sh
    export JAVA_HOME=/<JDK_PATH>/jdk-<JDK_VERSION>
    export PATH=$JAVA_HOME/bin:$PATH
```

After that, one can check wheter JDK is set by running the following command:

```sh
    $ java -version
```

where the result must be the downloaded and set version.

#### Maven

If you are under Windows operational system, simply download and install the latest stable version from [Apache Maven's official site](https://maven.apache.org/install.html). On Debian-like distros, you can run one of the following commands to install Maven:

```sh
    $ sudo apt install maven
    # apt install maven
```

By running

```sh
    $ mvn -v
```

it is possible to check wheter yout installation has succeeded. It is pivotal having JDK installed and `JAVA_HOME` enviroment variable set to Maven properly work.

### Clone the repository

Initially, create a directory under your preferred path:
```sh
    $ mkdir <FOLDER_NAME>
```

Move to the created directory:
```
    $ cd <FOLDER_NAME>
```

Under the directory, start git environment and clone the repository

```sh
    $ git init
    $ git clone https://bitbucket.org/sarabada/java_backend.git
```

At the actual version (1.0), your directory structure after the clone should be something like:

```
    ./
        .idea/
        src/
            main/
                java/com/sarabada/
                    controller/
                        WebServerController.java
                    core/
                        FileManipulator.java
                        Generator.java
                        Parser.java
                    entities/
                        ClientUserInfo.java
                    exceptions/
                        GameNotExistsException.java
                        WrongFileTypeException.java
                    responses/
                        ErrorResponse.java
                        GameResponse.java
                        LogGamesResponse.java
                        LogResponse.java
                        LogsResponse.java
                        Response.java
                    service/
                        WebServerService.java
                    App.java
                resources/logs/
                    games.log
                    test.txt
            test/java
                controller/
                    WebServerControllerTester.java
                core/
                    FileManipulatorTester.java
                    GeneratorTester.java
                    ParserTester.java
        JavaTest.iml
        pom.xml
```

After this step, you should be ready to go ;)

## Usage

Here, some examples of usage will be shown.

### Running the web server

To have the web server up and running, execute the following command on the terminal under the root project directory:

```sh
    $ mvn spring-boot:run
```

A list of events should be logged on the terminal. When they stop, the last line should be:

```sh
2018-03-12 20:25:44.656  INFO 5367 --- [           main] com.sarabada.App           : Started App in 8.866 seconds (JVM running for 23.67)
```

Date must be in accordance to the time when your are executing.

### Consuming the web server

With the web server running, the are several ways of consuming the web server in order to make tests.

Basically, you can access it from:

* Any terminal command which enables HTTP requests
* Directly from a browser
* Using a HTTP request enabler API in any programming language

#### Available URLs

Web server will respond to the following URLs, which can be tested from this document if the web server is up:

* http://localhost:8080/
* http://localhost:8080/logs
* http://localhost:8080/logs/:file
* http://localhost:8080/logs/:file/games
* http://localhost:8080/logs/:file/games/:game

Further information of their meaning will be given on the API usage section.

#### Consuming examples

Examples on how to consume these URL from terminal are (if you are on Linux and have [cURL](https://curl.haxx.se/) or [wget](https://www.gnu.org/software/wget/) installed):

```sh
    $ curl http://localhost:8080/logs
    $ wget http://localhost:8080/logs
```

If you are on your web broser, simply past the following URL on the search bar:

```
    http://localhost:8080/logs
```

An example on how to consume URLs will be given using JavaScript language. The package [request](https://github.com/request/request) is needed:

```js
var request = require('request');
request
  .get('http://localhost:logs/logs')
  .on('response', function(response) {
    //prints the status returned from the server
    console.log(response.statusCode);
    //prints the content-type of the response
    console.log(response.headers['content-type']);
    //prints the response itself
    console.log(response.body);
  })
```

## Development Documentation

In this section, descriptions are given for the way files are organized. For each developed `Java` file, there will be a description of its functions.

### Files organization

Files are organized as follow:

```
    ./
        .idea/
        src/
            main/
                java/com/sarabada/
                    controller/
                        WebServerController.java
                    core/
                        FileManipulator.java
                        Generator.java
                        Parser.java
                    entities/
                        ClientUserInfo.java
                    exceptions/
                        GameNotExistsException.java
                        WrongFileTypeException.java
                    responses/
                        ErrorResponse.java
                        GameResponse.java
                        LogGamesResponse.java
                        LogResponse.java
                        LogsResponse.java
                        Response.java
                    service/
                        WebServerService.java
                    App.java
                resources/logs/
                    games.log
                    test.txt
            test/java
                controller/
                    WebServerControllerTester.java
                core/
                    FileManipulatorTester.java
                    GeneratorTester.java
                    ParserTester.java
        JavaTest.iml
        pom.xml
```

#### /

The root project directory contains files auto-generated by used IDE, the IntelliJ. These files must be ignored if you are not running the project on this IDE. Furthermore, there is the `pom.xml`, which is the configuration file required by Maven to build the project.

#### src/

Within `src` directory, all directories and `Java` files related to the functioning of the web server are placed. Also, there is `resources` directory, which contains
`logs` directory. In this last, all logs files must be placed in order to be find by the software.

#### main/java/com/sarabada

Under these directories, all `Java` classes needed to the execution of the web server are placed. These classes are, on their turn, placed on sub-directories which are meaningful to each class. These sub-directories are:

##### controller/

Within this directory, all files related to the Spring Boot Controller component should be place. In other words, `class`es which actually exposes web services are placed at this directory.

##### core/

Despite `class`es in this directory are annotated as Service, which also is a Spring Boot component, these classes are the core of the software. That's why they are placed in here.

##### entities/

In this directory, `class`es which has no use but define some desired structure and be used by other `class`es are placed. `Class`es in here are supportive.

##### exceptions/

Within this directory are `class`es which are thrown as Java's `Exception` by other `class`es.

##### responses/

All kinds of responses generated by the `WebServerController` are placed in here.

##### service/

In this directory, all Spring Boot Service component (but those which are in `core` directory) are placed. The Service component is responsible for encapsulating all functionalities needed by the Controller component.

#### test/java

Under these directories, all unit tests' `class`es are placed. Also, there are sub-directories which defines the meaning to a set of tests. These sub-directories are organized as follow:

##### controller/

Unit tests related to the controller are placed in this directory.

##### core/

Unit tests related to the core are placed in this directory.

### Classes explanation

In general, 18 `class`es were generated to the challange. `Class`es are divided in testing `class`es, core `class`es, Spring Boot components' `class`es, entities `class`es, exceptions `class`es and response `class`es. Also, there is the `App.java` `class`, which starts the Spring Boot framework.

* Spring Boot components'
    * main/java/com/sarabada/controller/WebServerController.java
    * main/java/com/sarabada/service/WebServerService.java
* Core
    * main/java/com/sarabada/core/FileManipulator.java
    * main/java/com/sarabada/core/Generator.java
    * main/java/com/sarabada/core/Parser.java
* Entities
    * main/java/com/sarabada/entities/ClientUserInfo.java
* Exceptions
    * main/java/com/sarabada/exceptions/GameNotExistsException.java
    * main/java/com/sarabada/exceptions/WrongFileTypeException.java
* Responses
    * main/java/com/sarabada/responses/ErrorResponse.java
    * main/java/com/sarabada/responses/GameResponse.java
    * main/java/com/sarabada/responses/LogGamesResponse.java
    * main/java/com/sarabada/responses/LogResponse.java
    * main/java/com/sarabada/responses/LogsResponse.java
    * main/java/com/sarabada/responses/Response.java
* Testing Scripts:
    * test/java/core/FileManipulatorTester.java
    * test/java/core/GeneratorTester.java
    * test/java/core/ParserTester.java
    * test/java/controller/WebServerControllerTester.java

The next subsections will explain the functioning of each `class`.

#### /App.java

Main `class` which is executed when `mnv spring-boot:run` is called. It starts Spring Boot's application.

#### /main/java/com/sarabada/controller/WebServerController.java

Exposes a web service to the proposed challenge. The server responds to the following requests (it is important to note that Spring Boot converts the return of each called method to a JSON):

##### `/logs`

* HTTP verb: `GET`
* Executed method: `readLogsFolder():LogsResponse`
* Responds:
    * Success Case: A JSON-like array containing a list of logs.

##### `/logs/:file`

* HTTP verb: `GET`
* Executed method: `getLog(String logPath):Response`
* Parameters:
    * `file`: A string expected to be a log file name retrieved by `/logs` call.
* Responds:
    * Success Case: A JSON structure which summarizes information available in the log.
    * Fail Case #1: A JSON structure containing an I/O error message.
    * Fail Case #2: A JSON structure containing an error message saying that the asked file type is not expected.

##### `/logs/:file/games`

* HTTP verb: `GET`
* Executed method: `getLogGames(String logPath):Response`
* Parameters:
    * `file`: A string expected to be a log file name retrieved by `/logs` call.
* Responds: 
    * A JSON-like array which contains a list of game names existing in a log file.
    * Fail Case #1: A JSON structure containing an I/O error message.
    * Fail Case #2: A JSON structure containing an error message saying that the asked file type is not expected.

##### `/logs/:file/games/:game`

* HTTP verb: `GET`
* Executed method: `getLogGame(String logPath, String game):Response`
* Parameters:
    * `file`: A string expected to be a log file name retrieved by `/logs` call.
    * `game`: A string expected to be a game name retrieved by `/logs/:file/games` call.
* Responds:
    * A JSON summarizing information of a specific game from a given log.
    * Fail Case #1: A JSON structure containing an I/O error message.
    * Fail Case #2: A JSON structure containing an error message saying that the asked file type is not expected.
    * Fail Case #3: A JSON structure contaning an error message saying that the asked game is not present in that log.

#### /main/java/com/sarabada/service/WebServerService.java

Encapsulates the methods called used by `WebServerController.java`. Methods are:

##### getLogs()

Returns all logs found on `resources/logs` folder.

* Return:
    * `LogsResponse` instance.

##### getLog(String logPath)

Returns a `LogResponse` instance, contaning all games existing on `logPath`.

* Parameter:
    * `String` logPath: File name, expected to be existent on `resources/logs`.
* Throws:
    * `IOException`
    * `WrongFileTypeException`
* Returns:
    * `LogResponse` instance.

##### getLogGames(String logPath)

Returns a list of game names existing found in `logPath`.

* Parameter
    * `String` logPath: File name, expected to be existent on `resources/logs`.
* Throws:
    * `IOException`
    * `WrongFileTypeException`
* Returns:
    * `LogGamesResponse` instance.

##### getLogGame(String logPath, String games)

Return a `GameResponse` containing information of `game` found in `logPath`.

* Parameter
    * `String` logPath: File name, expected to be existent on `resources/logs`.
    * `String` game: Game identifier for a game found in `logPath`.
* Throws:
    * `IOException`
    * `WrongFileTypeException`
    * `GameNotExistsException`
* Returns:
    * `GameResponse` instance.

#### /main/java/com/sarabada/core/FileManipulator.java

Singleton `class` which operates at hard-drive level, reading files and directories, and processing them as needed.

##### getInstance()

Returns Singleton instance of this class.

* Returns:
    * `FileManipulator` instance.

##### readGameLog(String path)

Looks at `resorces/logs` in order to find file named with `path` variable. Returns `List<String>` containing all lines of that file when found. Throws `WrongFileTypeException` when asked file does not have `.log` extension. Throws `FileNotFoundException` when asked file is not found.

* Parameters:
    * `String` path: File name, expected to be existent on `resources/logs`.
* Throws:
    * `WrongFileTypeException`
    * `FileNotFoundException`
* Returns:
    * `List<String>` containing all lines read from the file.

##### readLogsFolder()

Looks at `resources/logs` in order to retrieve all `.log` files existing in there.

* Returns:
    * `List<String>` containing all `.log` file names found in `resources/logs`.

#### /main/java/com/sarabada/core/Generator.java

Responsible for generate the required JSON structure asked on the challenge. There are two functions: one to generate the JSON of a game and one to generate the JSON to a entire log.

##### getInstance()

Returns Singleton instance of this class.

* Returns:
    * `Generator` instance.

##### generateGame(List<String> gameArray)

Generates a `GameResponse` object which is the summarization of a given game.

* Parameters:
    * `List<String>` gameArray, which contains all lines of a game existing in a log.
* Returns:
    * `GameResponse` instance which summarizes the game.

##### generateLog(List<String> logArray)

Generates a `LogResponse` object which is the summarization of a given log.

* Parameters:
    * `List<String>` logArray, which contains all lines of a given log.
* Returns:
    * `LogResponse` instance, which is the summarization of the log.

#### /main/java/com/sarabada/core/Parser.java

Enables functions of log parsing, in different levels, such as game level, client level, kills of a client level and user information level. Returns `ClientUserInfo` instances to be manipulated by `Generator`.

##### getInstance()

Returns Singleton instance of this class.

* Returns:
    * `Parser` instance.

##### parseGames(List<String> logArray)

Takes a `List<String>` containing lines of a log. Returns a `List<List>`, which each element is a `List<String>` containing a game found in the log.

* Parameters
    * `List<String>` logArray: A `List` which each element is a `String`  corresponding to a line of a log.
* Returns:
    * A `List<List<String>>`, which each entry is a `List` contaning lines as `String` of a game.

##### parseClientIds(List<String> gameArray)

Takes an `List<String>` of lines of a games as entry. Returns different client ids found on that game.

* Parameters
    * `List<String>` gameArray: A `List` containing lines as `String` of a game.
* Returns
    * `List<Integer>`, which contains all ids found.

##### parseClientUserInfo (String gameInfoLine, int clientId, ClientUserInfo clientUserInfo)

Verifies if a `String` line contains chaging information of a client. Updates and returns `ClientUserInfo` latest information.

* Parameters
    * `String` gameInfoLine, which contains the line to be analysed
    * `int` clientId, which represents the client wanted to be found on the info line
    * `ClientUserInfo` instance, which will be updated when information is found
* Returns
    * `ClientUserInfo` unchangeded when information line is not relevant or updated otherwise.

##### parseClientKills (String gameInfoLine, int clientId, ClientUserInfo clientUserInfo)

Verifies if a `String` line contains kills caused by a client or kills of the `<world>` on that client. Updates and returns client's latest information.

* Parameters
    * `String` gameInfoLine, which contains the line to be analysed
    * `int` clientId, which represents the client wanted to be found on the info line
    * `ClientUserInfo` instance, which will be updated when information is found
* Returns
    * `ClientUserInfo` updated when the line corresponds to client kill information, or the same otherwise.

##### parseClientActivities (List<String> gameArray, int clientId)

Verifies client activies on a game. A client activity is interpreted as connections of users to that client, information changed by that user, kills caused by the user or user's deaths to the `<world>`, and a user disconnection. Each user connected to the client will have his or her activies stored and returned by this function.

* Parameters
    * `List<String>` gameArray: A `List` containing lines as `String` of a game.
    * `int` clientId, which represents the client wanted to be analyzed.
* Returns
    * A `List<ClientUserInfo>` whose each element correspond to a `ClientUserInfo` instance with user information, as well as its kills/deaths information gathered during his or her connection.

#### /main/java/com/sarabada/entities/ClientUserInfo.java

Conceptual `class` used to transport information of a client. Heavily used by the `Parser` and used by the `Generator` as basis to generate `Response`s. Its attributes
are:

* `int` kills
* `int` worldDeaths

which are generated by parsing lines containing kill information, and

* `int` t
* `int` c1
* `int` c2
* `int` hc
* `int` w
* `int` l
* `int` tt
* `int` tl
* `String` n
* `String` model
* `String` hModel
* `String` gRedTeam
* `String` gBlueTeam

which are found by parsing client user info change lines.

This class exposes two `Constructor`s:

* `ClientUserInfo(int kills, int worldDeaths, String n, int t, String model, String hModel, String gRedTeam, String gBlueTeam, int c1, int c2, int hc, int w, int l, int tt, int tl)`, used when all parameters are known
* `ClientUserInfo()`, when a all-null variables instantiation is desired.

Also, `Getters` and `Setters` are generated to encapsulate all variables.

Finally, an `equals(ClientUserInfo clienUserInfo)` has been overriden in order to enable an easy way to make comparisons.

#### /main/java/com/sarabada/exceptions/GameNotExistsException.java

This exception has been generated in order to deviate software's normal flux when a game is asked by the user and does not exist on a log. It is thrown by `WebServerService`'s method `getLogGame(String logPath, String game)`.

#### /main/java/com/sarabada/exceptions/WrongFileTypeException.java

This exception has been created in order to alert callers of the method `readGameLog(String path)` from `FileManipulator` class that the asked file is not of the type `.log`, which is the expected type.

#### /main/java/com/sarabada/responses/Response.java

It is the base `class` for all valid responses given by the `WebServerController`. It contains a `String` type which is commom for all inheritors, that describes if the response is normal or an error response. A `Getter` and `Setter` for this variable has been generated (needed by Spring Boot to form the JSON properly). Also, there is a `Constructor(String type)` which is called by inheritors to initialize the type variable.

#### /main/java/com/sarabada/responses/ErrorResponse.java

Inherits from `Response` and is used when an error JSON is wanted to be sent. Contains a `String` message which permits the generalization of the required message. This message will be set by `WebServerController` when needed. Has `Getter` and `Setter` for message variable and a `Constructor` `ErrorResponse(String message)`.

#### /main/java/com/sarabada/responses/GameResponse.java

This class inherits from `Response` and is used to summarize the desire response about a game which is inside a log. Attributes of this class are:

* `int` totalKills, which represents all kills on a game, even the ones caused by `<world>`
* `List<String>` players, which represents a list of players' name
* `Map<String, Integer>` kills, which contains kills information of each player.

The class contains the methods `Getters` and `Setters` for all attributes and a `Constructor` `GameResponse()`.

#### /main/java/com/sarabada/responses/LogGamesResponse.java

Inherits from `Response` and represents a list of games found in a log, which will be given as response by the `WebServerController`. Has an attribute `List<String>` that will compose the final list of games found in a log. Has a `Constructor` `LogGamesResponse(Set<String> games)`, which initializes the list. Also, has a `Getter` and a `Setter` to the list.

#### /main/java/com/sarabada/responses/LogResponse.java

This `class` represents a `Response` which is generated by `Generator`'s method `generateLog(List<String> logArray)` and will be sent by `WebServerController`'s `getLog(String logPath)`. It has an attribute `Map<String, GameResponse>`, which consists on a mapping of a name for a game and the game summarization itself. It has a `Constructor` `LogResponse()` and the `Getter` and `Setter` methods to the `Map<String, GameResponse>`.

#### /main/java/com/sarabada/responses/LogsResponse.java

It represents a list of logs' name found in `resources/logs` directory, and inherits `Response`, as it an expected response of `WebServerController`. It has an attribute `List<String>` which will contain all logs' names. There is the methods `Getter` and `Setter` for such attribute and a `Constructor` `LogsResponse(List<String> logs)`.

#### /test/java/core/FileManipulatorTester.java

This `class` tests functionalities of file manipulating, provided by `FileManipulator`. Here, it is going to be explained how each test were planned against its related function.

##### fileManipulator.read.gameLog(logPath)

Three tests were defined to this function:

* **Success case**, when the `String` `logPath` parameter is an actual path to a log file. In this case, it was tested the `size`, with respect to the number of lines, of a known log file, which must be `5307`.
* **First fail case**, when the `String` `logPath`'s file has a type other than `.log`. In this scenario, it must be returned a `WrongFileTypeException`.
* **Second fail case**, when the file provided on `String` `logPath` parameter does not exist. In this case, it must be returned an `IOException`.

##### fileManipulator.read.logsFolder()

To this function, only one unit test were developed. As the path to the logs' directory was defined manually (e.g. `/resources/logs`), it is verified if the `size` of the returned `List<String>` is the known value of `1`.

#### /test/java/core/GeneratorTester.java

This `class` tests the functionalities of generating `Response`s as defined on the challenge. Each function, one to generate a `GameResponse` and other to generate a `LogResponse`, has a test, as shown below:

##### generateGame(List<String> gameArray)

This function is tested against expected results for a manually verified game.

##### generator.generate.log.json(gameLog)

This function is tested against the verification of the name of each element existin in `LogResponse` `Map<String, GameResponse>`'s key set, which should start with `game_`, followed by a number which represents that game, as such `game_1`.

#### /test/java/core/ParserTester.java

This `class` were generated to test developed parsing functions. This is clearly the most sensible part of the development, since those functions should capture correct values to compose the final `Response`s.

##### parser.parse.games(logArray)

It is tested the the `size()` of the resultant array, which is expected to be `21`as previously verified in `/resources/logs/games.log`.

##### parser.parse.client.ids(gameArray)

Tests found ids. As manually verified, for `game_2`, ids must be `2` and `3`. Also, the `List<Interger>`'s `size()` is verified, which is expected to be `2`.

##### parser.parse.client.userInfo(gameArray, clientId, clientUserInfo)

In this tests, two scenarios were aimed to be verified:
* **Success case**, when the information line passed corresponds to the desired client. In this case, the return must be a `ClientUserInfo` instance updated with expected values, whose were verified manually.
* **Fail case**, when the information line does not correspond to the desire client. In this case, the returned `ClientUserInfo` must be an empty `ClientUserInfo` instance, which is passed as parameter to the method.

##### parser.parse.client.kills(gameArray, clientId, clientUserInfo)

In this test, several scenarios were tested:
* **Success case one**, when the kill information line given contains kill information of the desired client. In this case, `ClientUserInfo`'s attribute `kills` must be increased in one. The updated `ClientUserInfo` instance must then be returned.
* **Success case two**, when the kill information line given contains death informatin of the desired client to the `<world>`. In this case, `ClientUserInfo`'s attribute `world_deaths` must be increased in one. The updated `ClientUserInfo` instance must then be returned.
* **Fail case one**, when the kill information line given does not contain kill informatin of the desired client. In this case, `ClientUserInfo` instance must be returned with no alterations.
* **Fail case two**, when the kill information line given does not contain death informatin of the desired client to the `<world>`. In this case, `ClientUserInfo` instance must be returned with no alterations.

##### parser.parse.client.activities(gameArray, clientId)

This test verifies a known example of client activity, verified manually from `game_2`.

#### /test/java/controller/WebServerControllerTester.java

This `class` aims to test the web server functionalities. For each `URL` which the web server responds, it is related one or more tests.

##### `/logs`

Tests the desired return to the `GET` HTTP request to the `/logs` `URL`. It is verified whether the `status` is `200`, wheter the returned JSON has an array named `logs` with size 1 and if the first element of this array is `games.log`, as expected.

##### `/logs/:file`

To this request, two scenarios are tested:
* **Success case**, which must occurs when the `.log` file asked is found. It must return a JSON. To verify if the return is as expected, it is tested the known value of `67` to the `game_9`'s `totalKills` variable.
* **Fail case one**, when asked file is not found. It must return an JSON that within is an `type` variable, which should have the value `error`, and a `message` variable, which should have the value of `Some I/O error occurred.`.
* **Fail case two**, when asked file type is other than `.log`. It must return an JSON that within is an `type` variable, which should have the value `error`, and a `message` variable, which should have the value of `File requested has unappropriated type.`.

##### `/logs/:file/games`

To this request, two scenarios are tested:
* **Success case**, which must occurs when the `.log` file asked is found. It must return an JSON contaning an `array`, which corresponds to the games' names on that log. This `array`'s size is tested for a known log and its value should be `21`.
* **Fail case one**, when asked file is not found. It must return an JSON that within is an `type` variable, which should have the value `error`, and a `message` variable, which should have the value of `Some I/O error occurred.`.
* **Fail case two**, when asked file type is other than `.log`. It must return an JSON that within is an `type` variable, which should have the value `error`, and a `message` variable, which should have the value of `File requested has unappropriated type.`.

##### `/logs/:file/games/:game`

To this request, three scenarios are tested:
* **Success case**, which must occurs when the `.log` file asked is found as well as the required game. It must return an JSON contaning an `object`, which corresponds to the summarization of that game. It is tested the variable `totalKills` of a known game, which should be `67`.
* **Fail case one**, when asked file is not found. It must return an JSON that within is an `type` variable, which should have the value `error`, and a `message` variable, which should have the value of `Some I/O error occurred.`.
* **Fail case two**, when asked file type is other than `.log`. It must return an JSON that within is an `type` variable, which should have the value `error`, and a `message` variable, which should have the value of `File requested has unappropriated type.`.
* **Fail case three**, when asked game does not exist on the required log. It must return an JSON that within is an `type` variable, which should have the value `error`, and a `message` variable, which should have the value of `Game required not found on the log.`.