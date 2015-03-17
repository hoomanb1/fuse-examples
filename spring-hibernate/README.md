Installation
============
Download this project and cd to each individual project: 

	cd database-services 
	cd fuse-spring-hibernate

and run: 

	mvn clean install

Install the following features: 

	JBossFuse:karaf@root> features:install jndi
	JBossFuse:karaf@root> features:install transaction
	JBossFuse:karaf@root> features:install jpa
	JBossFuse:karaf@root> features:install jpa-hibernate

install the following bundles:

	JBossFuse:karaf@root> install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.commons-dbcp/1.4_3
	JBossFuse:karaf@root> install -s wrap:mvn:mysql/mysql-connector-java/5.1.14 

install the application bundles: 

	JBossFuse:karaf@root> install -s mvn:org.redhat.support/da^Cbase-services/1.0.0-SNAPSHOT
	JBossFuse:karaf@root> install -s mvn:org.redhat.support/fuse-spring-hibernate/1.0-SNAPSHOT

after this the schema should be created in your database. 
