RUN MANUALLY:
You have to install JDK on your machine

1) Change directory
    "cd LaIS/src/main/java"

2) Compile java files.
    "javac *.java"

3) Run compiled classes.
    Run application to process data.
    "java App <source> <C> <result-file>"
    Where:
        <source> - (required) Relative path to csv file with source data (for exzmple: src/main/resources/data.csv).
        <C> - (optional|default=2) The 'C' constant for a LaIS.
        <result-file> - (optional|default='LaIs-result.csv') Relative path to output file contained LaIS.

    Run test application to verify algorithm complexity.
    "java Test <C> <N> <repeat> <bound> <result-file>"
    Where:
        <C> - (optional|default=2) The 'C' constant for a LaIS.
        <N> - (optional|default=1000) A length of each generated source sequence.
        <repeat> - (optional|default=5000)The number of generated sequences.
        <bound> - (optional|default=1000)An upper bound of elements in generated source sequences.
        <result-file> - (optional|default='Test-result.csv') Relative path to output file with test results.


USING MAVEN:
If you have Maven installed on your machine you can follow instruction:

1) Change directory
    "cd LaIS/src/main/java"

2) Build runnable jar file. (./target/LaIS-1.0.jar)
    "mvn clean package"

2) Run jar file with
    "java -jar target/LaIS-1.0.jar <source> <C> <result-file>"
    Where:
        <source> - (required) Relative path to csv file with source data (for exzmple: src/main/resources/data.csv).
        <C> - (optional|default=2) The 'C' constant for a LaIS.
        <result-file> - (optional|default='LaIs-result.csv') Relative path to output file contained LaIS.
