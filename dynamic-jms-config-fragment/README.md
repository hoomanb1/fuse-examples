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

Fabric Deployment via Fabric8-Maven-Plugin - JBOss Fuse 6.2
===========================================================
To deploy the project into a fabric-managed enviroment, first we build the fabric environment with the following command:

            JBossFuse:karaf@root> fabric:create --wait-for-provisioning

We'll then create a child container with the follwoing command: 

            JBossFuse:karaf@root> fabric:container-create-child --jvm-opts "-Xms1024m -XX:MaxPermSize=512m -Xmx1024m -Xdebug" root app-container

The above command will spin up a fabric-managed child container, note I have passed the jvm arguments to ansure that the container has enough memmory to work with. So here how the fabric topology looks like so far: 

            JBossFuse:karaf@root> container-list 
            [id]             [version]  [type]  [connected]  [profiles]              [provision status]
            root*            1.0        karaf   yes          fabric                  success           
                                                             fabric-ensemble-0000-1                    
                                                             jboss-fuse-full                           
              app-container  1.0        karaf   yes          default                 success 

So at this stage we'll need to build our activemq container so that our camel bundle can produce messages into the testQueue. In fabric we can choose to define any broker topology, for the purpose of this demo we'll choose to use Master/Slave brokre pair for high availability. We'll create a master/slave pair with the following command: 

            fabric:mq-create --create-container broker-container --jvm-opts "-Xms1024m -XX:MaxPermSize=512m  -Xmx1024m -Xdebug" --kind MasterSlave broker-profile
            
Note-1: For more information on the arguments that have been passed to this command you can always use --help to see the command manual in details e.g.
      
            fabric:mq-create --help      

Note-2: For more information on the broker topology you can refer to [fabric8 documentation](http://fabric8.io/gitbook/brokerTopology.html)   
            
Once the containers are created our fabric topology will like this: 

            JBossFuse:karaf@root> container-list 
            [id]                 [version]  [type]  [connected]  [profiles]                        [provision                        status]
            root*                1.0        karaf   yes          fabric                            success           
                                                                 fabric-ensemble-0000-1                              
                                                                 jboss-fuse-full                                     
              app-container      1.0        karaf   yes          default                           success           
              broker-container   1.0        karaf   yes          mq-broker-default.broker-profile  success           
              broker-container2  1.0        karaf   yes          mq-broker-default.broker-profile  success 
  
Note-3: When creating the broker instance with the above command, broker's openwire transport listener listens on a random port, you can verify that by loging into the hawtio console and connect to master broker container by clicling on the arrow next to it. From the ActiveMQ tab look for "Open wire url". To set a fixed port for the openwire listener we add a config adming propety setting in the broker's profile: 

            JBossFuse:karaf@root> fabric:profile-edit --pid io.fabric8.mq.fabric.server-broker-profile/openwire-port=61618 mq-broker-default.broker-profile
            
This can be verified by inspecting the profile: 

            JBossFuse:karaf@root> fabric:profile-display mq-broker-default.broker-profile
            
To ensure our new port takes the effect we'll restart the broker containers: 

            JBossFuse:karaf@root> fabric:container-stop broker-container broker-container2
            The list of container names: [broker-container, broker-container2]
            Container 'broker-container' stopped successfully.
            
            JBossFuse:karaf@root> fabric:container-start broker-container broker-container2
            The list of container names: [broker-container, broker-container2]

Now we are ready to build our profile that contains the project bundles, for this purpose we'll use fabric8-maven-plugin to create the profile, so running the following command from the project root deploys our project artifacts into the local fabric server: 

            mvn fabric8:deploy

Note-4: To workaround [FABRIC-1253](https://issues.jboss.org/browse/FABRIC-1253) I have to manually specify one of the bundles in the configuration element of the fabric8-maven-plugin on the parent pom.xml

Once the build is successful we can verify the newly-created profile from Fuse console: 

            JBossFuse:karaf@root> fabric:profile-display amq-fragment-connection
            
Now we can provision this profile into our "app-container" container with the following command: 

            JBossFuse:karaf@root> fabric:container-add-profile app-container amq-fragment-connection       

Note-5:For more information on how fabric8-maven-plugin works please refer to the [fabric8-maven-plugin documentation](http://fabric8.io/gitbook/mavenPlugin.html) 







            

            
            


            

 

  

      
      

