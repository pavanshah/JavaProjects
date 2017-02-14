# CMPE202-CSUnplugged-PeruvianFlipCoin
GitHub Repository for CSUnplugged Project


This project repository contains the source code and design artifacts for CS-Unplugged Activity, Peruvian Flip Coin. 

The folder "Greenfoot" contains the GreenFoot client and "src" contains the code for Restlet based game Server. 

Deploy the Game in two parts : 1) Deploying Server and 2) Configuring Client. 


1) Deploying the Server. 
================

1. compile all the java files providing the jar dependencies from "src/Dist".

  javac -cp dist\json.jar;dist\restlet.jar;dist\restlet-json.jar -d build src\Peruvian\*.java src\api\*.java


2. Switch to directory "build"
  cd build
  
3. Create Executable jar for the server compiled code.

  jar -cvfe ..\dist\app.jar PeruvianServer .
  
4. To test on a localhost, change to parent directory and run the server

  cd ..
  
  java -cp build;dist\restlet.jar;dist\restlet-json.jar;dist\json.jar api.PeruvianServer


Once successfully tested on localhost, dockerize the server app. 

5. Create the docker configuration file. 

FROM openjdk
EXPOSE 8080
ADD ./dist/app.jar /srv/app.jar
ADD ./dist/restlet.jar /srv/restlet.jar
ADD ./dist/restlet-json.jar /srv/restlet-json.jar
ADD ./dist/json.jar /srv/json.jar
CMD java -cp build:/srv/restlet.jar:/srv/restlet-json.jar:/srv/json.jar:/srv/app.jar api.Peruvian 


6. Open the docker quick start terminal. 

a)docker login -u <username>

b)docker build -t peruvianServer .

c)docker push <username>/gumball

7. Log in to docker cloud, fetch the repo from docker hub. 

8. Create a new service and expose it on port 80. 

9. Note the docker container URL. 

10. Test the url by getting game status : url/Peru


=============================================================

2) Configuring the Greenfoot Client
===============================================

1. Open the project.greenfoot file. 

2. Open the driver Actor. 

3. Replace the restAPI url with the one noted in Step 10. for server configuration. 

4. Restart JVM

5. Start the game. Note the game key and communicate to Player 2. 

6. Enjoy :)











