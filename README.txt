1) "cd LaIS/src/main/java"
    - change directory.
2) "find . -name "*.java" | xargs javac"
    - compile java files.
3) "java com.ilinykh.algo.App ../resources/data.csv 2 ../resources/out.csv"
    - run compiled classes.

    data.csv - source data file (required)
    2 - constant 'C' in algorithm (optional) default value = 2
    out.csv - file for writing results (optional) default value = 'output.csv'


If you have Maven installed on your machine you can follow instruction:
1) "mvn clean build"
    - to build runnable jar file ./target/LaIS-1.0-SNAPSHOT.jar
2) "java -jar target/LaIS-1.0-SNAPSHOT.jar data.csv 2 output.csv"
    - to run jar file.
    - data.csv - should contain a sequence of numbers separated by whitespaces or comma.

