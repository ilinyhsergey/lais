Run manually:

1) Change directory
    "cd LaIS/src/main/java"

2) Compile java files.
    "find . -name "*.java" | xargs javac"

3) Run compiled classes.
    Run application to process data.
    "java App ../resources/data.csv 2 ../resources/out.csv"
    Where:
        data.csv - source data file (required)
        2 - constant 'C' in algorithm (optional) default value = 2
        out.csv - file for writing results (optional) default value = 'output.csv'

    Run test application to verify algorithm complexity.
    "java Test <C> <N> <repeat> <bound>"
    Where:
        <C> - The 'C' constant for a LaIS.
        <N> - A length of each generated source sequence.
        <repeat> - The number of generated sequences.
        <bound> - An upper bound of elements in generated source sequences.


If you have Maven installed on your machine you can follow instruction:

1) Change directory
    "cd LaIS/src/main/java"

2) Build runnable jar file. (./target/LaIS-1.0-SNAPSHOT.jar)
    "mvn clean package"

2) Run jar file with
    "java -jar target/LaIS-1.0.jar <source> <C> <result>"
    Where:
        <source> - Relative path to csv file with source data (src/main/resources/data.csv).
        <C> - The 'C' constant for a LaIS.
        <result> - Relative path to output file contained LaIS (result.csv)
