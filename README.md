iwasthere
=========

RESTX demo app

## Structure

### Server

The server side with REST API is located in the `srv` directory, and follows standard maven structure with Java sources in `src/main/java` directory.


### UI

The user interface is located in the `ui` directory, and is an AngularJS / Bootstrap application, following usual conventions especially for yeoman users, with sources in the `app` directory.

## Building

### Server

You can build it with maven with `mvn install`

### UI

You can build it with npm + bower + grunt:
```
npm install
bower install
grunt build
```

### App

You can use maven to build the whole app as a war with `mvn install` at the root level (this directory).

## Running

### Server

Server side is located in `srv` directory, and is a standard restx app.

You can run it with `restx` shell:

`restx deps install + app run`

You can also run it with a simple java command after building it:

```
mvn package
mvn dependency:copy-dependencies
java  -cp "target/classes:target/dependency/*" iwasthere.AppServer
```

### UI

You can run the UI with:
```
npm install
bower install
grunt serve
```

Or simply `grunt serve` if your bower dependencies are already up to date.


Running the UI launches a web server to serve the static assets on port `9000`, configured with a reverse proxy for the REST API calls targetted at a local server, so you need to run the `server` side too.

