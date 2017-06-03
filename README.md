# Internship assignment
As a part of my internship assignment, I started working on this demo application to use it in a Continuous Delivery pipeline I built using the following tools:

* <b>Kubernetes</b> - an open-source container cluster manager (default on Google Cloud Platform), works great with Docker
* <b>Google Cloud Platform</b> - where Kubernetes master and workers live! 
* <b>Travis CI</b>- to test and build the app, create Docker images and push to a Docker registry on cloud 
* <b>Docker</b> - an open-source application container engine: manages containers, where StudentApp should live in!
* <b>FF4J</b> -  a Feature Toggles implementation 

## Demo application: Student App
This simple Spring Boot web application contains different if-statements for visibility or availability of specific internal functionalities (features). Code related to each feature will only get executed if the feature status is 'enabled'. Feature objects (containing status of each feature) are provided by a service called <i>Features Service</i>. 

## Avoiding database conflicts 
<i>(not configured as default)</i>


After a rolling-update on a Kubernetes cluster there might occur database schema conflicts if the new release changes or removes any existing table of column which basically are belonged to the old/existing release. At the end and after any rolling-update, there must be only one shared storage used by all nodes in the cluster. 


That's the reason why there's a Python application available on this project too. The Python script compares the old/existing database schema to the Hibernate mapping file in the current release to see if there are any unused or empty tables. It also checks the new database file for occurrence of any SQL commands which remove or change anything from the existing database structure (except unused or empty tables).