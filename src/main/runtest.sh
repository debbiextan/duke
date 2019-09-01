#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
# NOTE: All below paths are relative to the location of runtest.sh (duke/src/main/runtest.sh)
# Arguments:
# -cp or classpath: java/ (Contains all the java classes)                       - FULLPATH: duke/src/main/java/
# -d or directory: ../bin/ (Location for all the "bin"/.class/compiled files)   - FULLPATH: duke/src/bin/
# <source file>: java/Main.java                                                 - FULLPATH: duke/src/main/java/Main.java
if ! javac -cp java/ -Xlint:none -d ../bin/ java/Main.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# empty duke.csv (savefile)
rm duke.csv
touch duke.csv

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Main < input.txt > ACTUAL.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi