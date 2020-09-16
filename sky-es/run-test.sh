# run shell in test environment
cd ..
mvn clean package -Dmaven.test.skip=true
cd sky-es/sky-es-api/target
nohup java -jar sky-es-api.jar --spring.profiles.active=test  > /root/log-es.file  2>&1 &
echo 'sky-es start successfully!'