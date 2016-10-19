# cw

What you’ll need

JDK 1.8
Maven 3.0+


Instructions to run the app

1. Download and unzip the source repository for this app, or clone it using Git: git clone https://github.com/sujathaboddu/cw.git

2. cd to the dir the app is cloned to

3. Build app using the below command
	mvn clean install

4. Run the app using the below command
	java -jar target/cw-0.0.1-SNAPSHOT.jar

5. Access the application
		http://localhost:8080
		
		This will return a web page to create an offer.

6. The rest services can be tested using Rest client. 
	The api contracts can be found at swagger
	http://localhost:8080/swagger-ui.html


Assumptions	:

A simple domain model "Offer" is used with name as unique field, assuming this is just for one merchant. Hence merchant entity and parent/child relationships are not demonstrated.

