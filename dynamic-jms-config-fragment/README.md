Project Concept
===============
This project demonstrates how you can configure your bundle to use different JMS prodivers without having to repackage your bundle using osgi fragment bundle. 

Installation
============
Run 
      mvn clean install

on each project sub-folder and then install it into JBoss Fuse: 

      JBossFuse:karaf@root> install mvn:com.jboss.fuse/jms-connection-fragment/1.0.0
      JBossFuse:karaf@root> install -s mvn:com.jboss.fuse/camel-broker-client/1.0-SNAPSHOT

this should look like the following: 

      [ 253] [Resolved   ] [            ] [       ] [   60] JMS Fragment :: Broker Connection (1.0.0), Hosts: 254
      [ 254] [Active     ] [Created     ] [       ] [   60] Camel JMS Client (1.0.0.SNAPSHOT), Fragments: 253

Fabric Deployment via Fabric8-Maven-Plugin
==========================================
To deploy the project into a fabric-managed enviroment, first we build the fabric environment with the following command:

            JBossFuse:karaf@root> fabric:create --wait-for-provisioning

We'll then create a child container with the follwoing command: 

            JBossFuse:karaf@root> fabric:container-create-child --jvm-opts "-Xms1024m -XX:MaxPermSize=512m -Xmx1024m -Xdebug" root app-container

The above command will spin up a fabric-managed child container, not I have passed the jvm arguments to ansure that the container has enough memmory to work with. So here how the fabric topology looks like so far: 

            JBossFuse:karaf@root> container-list 
            [id]             [version]  [type]  [connected]  [profiles]              [provision status]
            root*            1.0        karaf   yes          fabric                  success           
                                                             fabric-ensemble-0000-1                    
                                                             jboss-fuse-full                           
              app-container  1.0        karaf   yes          default                 success 

So at this stage we'll need to build our activemq container so that our camel bundle can produce messages into the testQueue. In fabric we can choose to define any broker topology, for the purpose of this demo we'll choose to use Master/Slave brokre pair for high availability. We'll create a master/slave pair with the following command: 

            fabric:mq-create --create-container broker-container --jvm-opts "-Xms1024m -XX:MaxPermSize=512m       -Xmx1024m -Xdebug" --kind MasterSlave broker-profile
            
Once the containers are created our fabric topology looks like this: 

            JBossFuse:karaf@root> container-list 
            [id]                 [version]  [type]  [connected]  [profiles]                        [provision                        status]
            root*                1.0        karaf   yes          fabric                            success           
                                                                 fabric-ensemble-0000-1                              
                                                                 jboss-fuse-full                                     
              app-container      1.0        karaf   yes          default                           success           
              broker-container   1.0        karaf   yes          mq-broker-default.broker-profile  success           
              broker-container2  1.0        karaf   yes          mq-broker-default.broker-profile  success 
  


Please note: for more information on the arguments that have been passed to this command you can always use --help to see the command manual in details.  

Note: For more information on the broker topology you can refer to fabric8 documentation: http://fabric8.io/gitbook/brokerTopology.html    

      
      

