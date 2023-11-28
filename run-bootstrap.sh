#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=ecourse.app.bootstrap/target/ecourse.app.bootstrap-1.4.0-SNAPSHOT.jar:ecourse.app.bootstrap/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP ecourse.app.bootstrap.BaseBootstrap
