#!/bin/bash
TOMCAT_HOME="/home/web/apache-tomcat-8.5.28-ssm2api"
JAVA_HOME="/usr/lib/jvm/java-8-oracle"
MAVEN_HOME="/usr/maven/apache-maven-3.5.2"
PROJECT_HOME="/home/web/sourcecode/ssm2api"
DEPLOY_HOME="/home/web/deployment"
TIME=$(date +%Y%m%d_%H%M%S)

# user should be web
if [ "$USER" != "web" ];then
   echo "error:please use web login!"
   exit 1
else
   echo "current user is web,please go..."
fi

echo ">> pull source code from origin master branch..."
cd $PROJECT_HOME
git pull origin master

cd $PROJECT_HOME
echo ">> mvn war..."
$MAVEN_HOME/bin/mvn clean install -P production

echo ">> stop tomcat..."
# sh $TOMCAT_HOME/bin/shutdown.sh
kill -9 `ps -ef | grep java | grep web | grep tomcat | grep ssm2api | awk ' BEGIN { FS = " " } { print $2 } '`

echo ">> move work copy.."
cd $DEPLOY_HOME
rm -rf $DEPLOY_HOME/*
mkdir ssm2api
cd ssm2api
$JAVA_HOME/bin/jar -xvf $PROJECT_HOME/ssm2api-api/target/ssm2api.war
rm -f WEB-INF/lib/jsp-api-*.jar
rm -f WEB-INF/lib/servlet-api-*.jar

#beckup logs and clean
echo ">> beckup & clean all logs..."
cp -r  $TOMCAT_HOME/ssmLogs $TOMCAT_HOME/ssmLogs_$TIME
cp -r  $TOMCAT_HOME/logs      $TOMCAT_HOME/logs_$TIME
rm -rf $TOMCAT_HOME/ssmLogs/*
rm -rf $TOMCAT_HOME/logs/*

echo ">> start tomcat..."
sh $TOMCAT_HOME/bin/startup.sh
tail -f $TOMCAT_HOME/logs/catalina.out
