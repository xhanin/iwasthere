Angular - Bootstrap - Bower - Grunt seed

Initial commit generated with yo generator-angular 0.7.1, with all default answers.

Reviewed to be used more easily with a REST server, especially a [RESTX](http://restx.io/) one.

All changes made to this generator can be checked by looking at git history since initial commit.

## How to use

You can bootstrap your project with this seed in one of the following way:

* clone this repo (optionally fork it before), rename your app in package.json file, setup your backend REST server and
  configure its host and port in bower.json, and start hacking
* use restx to bootstrap your app, server and ui, and select `angular-bootstrap-grunt-bower` ui seed, and everything
  will be ready.

## Code organization

This app sources are located in the app directory, and test sources are in the test directory.

## Building / running

### Pre requisites:
Install npm, grunt, bower, sass and compass.

### Preparing

Run `npm install` once to install the components described in `package.json`, required to run `grunt`.

Run `bower install` to download your web app dependencies described in bower.json to `app/bower_components`.

### Running

Note: You need to have your backend server running.

To run the app:

`grunt serve`

It will:

 - build your sass assets,
 - run a server to serve your web app static assets on port 9000, with a reverse proxy to your backend server on port
  8000, a file watching mechanism to rebuild your sass assets, and live reload support so that your browser is
  refreshed whenever you make changes to your sources

### Testing

Run `grunt test` to run karma tests.

### Building

Run `grunt build` to prepare your web assets in the `dist` directory.
