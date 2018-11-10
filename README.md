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

## API
### Request structure
[TBD]
### Response structure
[TBD]

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