mvn clean package -Dmaven.test.skip=true
nohup java -jar sky-api-system/target/sky-api-system.jar --spring.profiles.active=prod  > log-system.file  2>&1 &
nohup java -jar sky-api-oss/target/sky-api-oss.jar --spring.profiles.active=prod  > log-oss.file  2>&1 &
nohup java -jar sky-api-solr/target/sky-api-solr.jar --spring.profiles.active=prod  > log-solr.file  2>&1 &
nohup java -jar sky-api-tiku/target/sky-api-tiku.jar --spring.profiles.active=prod  > log-tiku.file  2>&1 &
echo 'start successfully!'