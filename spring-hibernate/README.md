Installation
============

Run the following command from the project root:

	mvn clean install

Install the features file. This gets pulled out from your local Maven repository, and defines which bundles you mean to install for the example project.

	JBossFuse:karaf@root> features:addurl mvn:fuse-examples/features/1.0-SNAPSHOT/xml/features

then install all the necessary OSGi bundles that we have defined from the feature project:

	JBossFuse:karaf@root> features:install project-features
	JBossFuse:karaf@root> features:install database-features
	JBossFuse:karaf@root> features:install application-bundles
	
after this the schema should be created in your database. 

Note: Make sure to change the datasource connection information in the following file accordingly:

	database-services-spring-context.xml
