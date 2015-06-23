
Project Overview
================
This is a sample project that provides the neccessary osgi bundles to create a multi-tiers bundles (DAO, Business Services, etc). Project consists of four components, 1) database-service that contains the data layer connection to the underlying database - mysql in this case - 2) fuse-blueprint-hibernate contains the business logic and the domain entities. 3) A RESTful web service that utilizes apache cxf to expose an endpoint to interact with backend entities. 4) and finally the features bundle that aggregates all the inter-dependent bundles into a larger unit of deployment which can then be deployed in to JBoss Fuse directly.

Installation
============
Run the following command from the project root:

    mvn clean install

Install the features file. This gets pulled out from your local Maven repository, and defines which bundles you mean to install for the example project.

    JBossFuse:karaf@root> features:addurl mvn:fuse-examples/features/1.0-SNAPSHOT/xml/features
  
then install all the necessary OSGi bundles that we have defined from the feature project:

    JBossFuse:karaf@root> features:install project-features
    JBossFuse:karaf@root> features:install database-features
    JBossFuse:karaf@root> features:install jackson
    JBossFuse:karaf@root> features:install application-bundles

Sending Data to the API
=======================

The RESTful endpoint consumes json as a media type and can be hit by the following command: 

    curl -H "Accept: application/json" -H "Content-type: application/json" -X PUT -d '{"branchCode":"br-0123", "clientCode":"cl-0123","userCode":"us-0123","userName":"hoomanb","uuid":"uu-0123"}' http://localhost:9002/rest/billing/create
