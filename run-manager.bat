REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=ecourse.app.manager.console\target\ecourse.app.manager.console-1.4.0-SNAPSHOT.jar;ecourse.app.manager.console\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% ecourse.app.manager.console.BaseManager
