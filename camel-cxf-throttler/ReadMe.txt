Camel CXF Throttler Project for Blueprint (OSGi)
====================================================
To build run the bellow command on the project's root directory 

    	mvn clean install

Run JBoss Fuse 6.2 instance, and then install the following bundles:

	JBossFuse:karaf@root> install -s mvn:org.codehaus.jackson/jackson-core-asl/1.9.12
	JBossFuse:karaf@root> install -s mvn:org.codehaus.jackson/jackson-mapper-asl/1.9.12

and then install camel-jackson feature:

	JBossFuse:karaf@root> features:install camel-jackson

To deploy the project in fuse. 

    osgi:install -s mvn:org.redhat.com/camel-cxf-throttler/1.0-SNAPSHOT

To test use the script in the root directory of the project to hit the web service endpoint:

    	./smash.sh

