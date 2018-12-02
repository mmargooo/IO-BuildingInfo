# IO-BuildingInfo

This application allows clients to send requests containing information about building to the server
and receive desired data (i.e. area of building/floor/room) as a response.

## Basics
### IDE
We suggest using IntelliJ, all configuration will be shown on it.
If you prefer something else, remember to ensure that it supports following plugins:

* Editorconfig
* Save Actions

### EditorConfig

As it was mentioned before, we use EditorConfig.

We use:

* 4 spaces as indent
* UTF-8 as coding
* crlf as end-of-line

We don't:

* add whitespace at the end of line
* add empty line at the end of the file

IntelliJ has EditorConfig plugin included, but make sure it enables its support.
```
 File -> Settings -> Editor -> Code Style
 At the end of this window, check 'Enable EditorConfig Support
```

Configuration file (.editorconfig) is added automatically.

### Save Actions

#### Instalation

In IntelliJ
```
File -> Setting -> Plugin
```
Type Save Actions and install that plugin.

#### Configuration
In IntelliJ
```
File -> Setting -> Save Actions
```
Check 'Activate save actions on save' box in Gerenal section

Check 'Reformat file' box in Formatting actions section.

## Workflow
Work is devided into a few stages:
1. `To Do` - Beginning of every task's life cycle.
2. `Ready for development` - task is ready to start working on.
3. `In progress` - task is being developed. 
4. `Needs review` - task is ready and PR waits for review.
5. `Reviewer approved` - task was positively reviewed and is ready to be merged to develop.
6. `Done` - task was successfully merged to develop.

## Gitflow
* every task is another branch prefixed with 'feature' or 'bugfix'
* every commit message must be prefixed with branch name
* direct merges to `develop` branch are forbidden

## Commands
To make the work with Maven easier below there are some useful commands:
* `mvn clean` - clean a project
* `mvn compile` - compile a project
* `mvn package` - build a package
* `mvn test` - run unit tests
* `mvn install` - install a package to local repository
* `mvn site` - generate a site
* `mvn spring-boot:run` - starts spring-boot server

## API
### Request structure
* Send building information:
```
{
    id: string,
    name: string,
    heatingLimit: float,
    levels: [
        {
            id: string,
            name: string,
            rooms: [
                {
                    id: string,
                    name: string,
                    area: float,
                    cube: float,
                    heating: float,
                    lighting: float,
                },
                {...}
            ]
        },
        {...}
    ]
}
```
### Response structure
Response structure depends on request type
* sending building structure
```
{
    status: string,
    message: string
}
```

* requesting information about locations features
```
{
    status: string,
    value: float
}
```

* requesting list of rooms exceeding certain level of heating energy
```
{
    status: string,
    results: [
        {
            id: string,
            name: string,
            area: float,
            cube: float,
            heating: float,
            lighting: float,
        },
        {...}
    ]
}
```

### Request path
To retrieve data, requests need to have a specific path and an adequate
request method
#### Method - POST
* /api/new - create a new building
#### Method - GET
* /api/area/{id} - retrieve the area of location
* /api/cube/{id} - retrieve the cubage of location
* /api/lightingPerArea/{id} - retrieve the illumination level per m^2
* /api/heatingPerCube/{id} - retrieve the usage of energy used on heating per m^3
* /api/exceeding/{id} - retrieve the list of rooms exceeding the energy/m^3 limit

## Classes
Our data structures consists of class described below 

```java
// abstract class aggregating common features and describing interface of our data structures
public abstract class Location {
    private String id;
    private String name;
}

// building class gathering it's levels
public class Building extends Location {
    private List<Level> levels;
    private float heatingLimit;
}

// level class gathering it's rooms
public class Level extends Location {
    private List<Room> rooms;
}

// Room class containing information describing certain room
public class Room extends Location {
    private Float area;
    private Float cube;
    private Float heating;
    private Float lighting;
}
```

## Database configuration
* Install PostgreSQL locally
* Start PostgreSQL service if it is not running
* Set proper username, password and url to your database in `application.properties`
* Create database locally e.g. - `createdb -U postgres io_building_info` as `postgres` user