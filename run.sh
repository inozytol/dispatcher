#!/usr/bin/env bash

# 20181201, based on: https://stackoverflow.com/questions/52373469/how-to-launch-junit-5-platform-from-the-command-line-without-maven-gradle

JUNIT_JAR_PATH=~/APPS/java/junit/junit-platform-console-standalone-1.3.2.jar



rm -fr target # remove directory, forcefully (with contents, and without asking. DO NOT TRY THIS AT HOME (or more importantly at /)
mkdir target
mkdir target/storage

##mkdir target/inozytol
#dir target/inozytol/fileDispatcher
# This is some text file for test purposes
echo "Hello and goodbye" > target/storage/foo


result=0 # contains sum of exit codes from various commands. Zero if everything successful

javac -d target src/main/inozytol/fileDispatcher/FetcherDispatcherFactory.java src/main/inozytol/fileDispatcher/FileFetcherDispatcherById.java src/main/inozytol/fileDispatcher/DiskFileFetcherDispatcherById.java
let "result=$result+$?"

echo "$JUNIT_JAR_PATH"
# Test compilation requires this classpath as it contains imports from junit jupiter
javac -d target -cp $JUNIT_JAR_PATH:.:target src/test/inozytol/fileDispatcher/DiskFileFetcherDispatcherByIdTest.java
let "result=$result+$?"

if [ "$result" -eq "0" ]
then
    echo "Compilation completed"
    echo "Running tests"
    java -jar "$JUNIT_JAR_PATH" --class-path target/inozytol/fileDispatcher:.:target --scan-class-path # it searches for tests in compiled classes from given directory
    
    #rm -rf doc
    #mkdir doc
    #cd doc
    #javadoc ../Cryptest.java
    
else
    echo "Compilation failed"
fi
