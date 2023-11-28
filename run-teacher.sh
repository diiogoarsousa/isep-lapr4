#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=ecourse.app.teacher.console/target/ecourse.app.teacher.console-1.4.0-SNAPSHOT.jar:ecourse.app.teacher.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP ecourse.app.teacher.console.BaseTeacher
