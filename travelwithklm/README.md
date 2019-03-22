# Myfirstangularapp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.3.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).


## Connect to github from vscode

I assume you started to work in a directory and didn't use git there. The following should work with git bash:

cd "path to your repo"
`git init`
`git add .` # if you want to commit everything. Otherwise use .gitconfig files
`git commit -m "initial commit"` # If you change anything, you can add and commit again...

To add a remote, just do

`git remote add origin https://...`
`git remote show origin` # if everything is ok, you will see your remote
`git push -u origin master` # assuming your are on the master branch.


## Deploy App on Firebase
https://medium.com/@longboardcreator/deploying-angular-6-applications-to-firebase-hosting-b5dacde9c772

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
