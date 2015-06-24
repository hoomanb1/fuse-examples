Project Overview
================

This project builds an integration component on top of the spring-hibernate-rest project which lives in fuse-example repository. In order to bring integration rational into the project the fuse-spring-rest-messaging is modifed where it sends a notification message with a simple piece of information in regards to the request that comes into the system, which then be placed into message queue. Additionally fuse-spring-rest-messaging routinely persist the request into mysql database as well.  Arriving the message into the queue triggers a camel route that listens on that particualr queue and porcesses the message based on a simple logic. The route simply checks the database based on the information provided into the queue and decides upon accordingly. 

Please note the intend of this example project is to demonstrate the capability and usage of camel sql component. 

Installation
============
Run the following command from the project root:

      mvn clean install

Install the features file. This gets pulled out from your local Maven repository, and defines which bundles you mean to install for the example project.

      JBossFuse:karaf@root> features:addurl mvn:fuse-examples/project-features/1.0-SNAPSHOT/xml/features
      
then install all the necessary features that we have defined from the feature project:

      JBossFuse:karaf@root> features:install project-features
      JBossFuse:karaf@root> features:install database-features
      JBossFuse:karaf@root> features:install business-features
      JBossFuse:karaf@root> features:install rest-features
      JBossFuse:karaf@root> features:install integration-bundle
      
Testing
=======
Hit the exposed REST endpoint (spring-hibernate-rest): 
      curl -H "Accept: application/json" -H "Content-type: application/json" -X PUT -d '{"branchCode":"br-0123", "clientCode":"cl-0123","userCode":"us-0123","userName":"hoomanb","uuid":"uu-0123"}' http://localhost:9002/rest/billing/create
      
      
