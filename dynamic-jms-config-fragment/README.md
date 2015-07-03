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

      
      

