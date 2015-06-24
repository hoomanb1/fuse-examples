Project Overview
================

This project builds an integration component on top of the spring-hibernate-rest project which lives in fuse-example repository. In order to bring integration rational into the project the fuse-spring-rest-messaging is modifed where it sends a notification message with a simple piece of information in regards to the request that comes into the system, which then be placed into message queue. Additionally fuse-spring-rest-messaging routinely persist the request into mysql database as well.  Arriving the message into the queue triggers a camel route that listens on that particualr queue and porcesses the message based on a simple logic. The route simply checks the database based on the information provided into the queue and decides upon accordingly. 

Please note the intend of this example project is to demonstrate the capability and usage of camel sql component. 
