Maven conversion of minuteProject: http://minuteproject.wikispaces.com/

==================================================================================
TODO s

fix project: jetty

<groupId>org.mortbay.jetty</groupId>
<artifactId>jetty-maven-plugin</artifactId>
<version>8.1.7.v20120910</version>   --> 7.4.5.v20110725

<groupId>org.mortbay.jetty</groupId>
<artifactId>jetty-maven-plugin</artifactId>
<version>7.6.3.v20120416</version>

H2 Java Database integration
    -> demo project (extra)
    http://stackoverflow.com/questions/2205126/starting-an-h2-database-server-from-maven
    http://stackoverflow.com/questions/3905953/how-can-i-provide-a-relative-path-for-h2-database-file-using-hibernate-under-tom
    20120914.txt

    org.h2.Driver
    jdbc:h2:~/test
    sa
    petshop-oracle.sql
    MinuteProject console:
    > Locked by another process". Possible solutions: close all other connection(s);
    http://stackoverflow.com/questions/8158969/h2-database-error-database-may-be-already-in-use-locked-by-another-process
    jdbc:h2:tcp://localhost/~/test

    Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
    	at org.apache.ddlutils.PlatformFactory.createNewPlatformInstance(PlatformFactory.java:88)
    ORACLE

    ====
    jdbc:h2:mem:mp;MODE=Oracle;INIT=CREATE SCHEMA IF NOT EXISTS MP\\;SET SCHEMA MP\\;RUNSCRIPT FROM 'minuteProject/minuteProject/sample/schema/petshop-oracle.sql'

    ==
     (1) start db example: start-petshop-database.sh
     missing file minuteProject-0.8.2/sample/server.properties -> minuteProject-0.8.2/sample/hsql/petshop.properties nope
     db credentials?

     org.hsqldb.jdbc.JDBCDriver
     jdbc:hsqldb:mem:.
     SA:

     --> double click hsql 2.2.9

     org.hsqldb.jdbc.JDBCDriver
     jdbc:hsqldb:mem:.
     SA:

     reserved 'sequence' table name for hsql
     > http://old.nabble.com/user-lacks-privilege-or-object-not-found-td25967882.html
     SET DATABASE UNIQUE NAME HSQLDB2467A82B33
     SET DATABASE GC 0
     SET DATABASE DEFAULT RESULT MEMORY ROWS 0
     SET DATABASE EVENT LOG LEVEL 0
     SET DATABASE SQL SIZE TRUE
     SET DATABASE SQL NAMES FALSE
     SET DATABASE TRANSACTION CONTROL LOCKS
     SET FILES WRITE DELAY 20
     SET FILES BACKUP INCREMENT TRUE
     SET FILES CACHE SIZE 10000
     SET FILES CACHE ROWS 50000
     SET FILES SCALE 1
     SET FILES DEFRAG 20
     SET FILES NIO TRUE
     SET FILES LOG SIZE 200
     SET DATABASE TEXT TABLE DEFAULTS ''
     CREATE USER SA PASSWORD ''
     CREATE SCHEMA PUBLIC AUTHORIZATION DBA
     SET SCHEMA PUBLIC

     ==> inject petshop.sql

Tapestry Integration + JPA2

Autolaunch application after build

Create maven root pom with real modules

Test build with Oracle database export with JPA2

ReSync with http://minuteproject.svn.sourceforge.net/viewvc/minuteproject/trunk/?sortby=rev&view=log

License?
 http://minuteproject.wikispaces.com/Licence ASLv2
 http://sourceforge.net/projects/minuteproject/ GPLv2
