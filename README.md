# Overview

This is a sample experiment for the TU Vienna University course Data Stewardship, summer term 2020. 
The data.csv file contains data on corona virus downloaded from the european union open data platform.


# Build
run: `mvn clean package` in the root folder of the project. 

The resulting dsExperiment.jar file will be in the /target directory. 
When run, the data.csv file needs to be in the same directory as the jar file.


# Running the application
Make sure the data.csv file is in the same directory as the .jar file. 
Then run the application with: `java -jar dsExperiment.jar`
