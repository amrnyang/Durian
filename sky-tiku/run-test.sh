# run shell in test environment
cd ..
mvn clean package -Dmaven.test.skip=true
cd sky-tiku/sky-tiku-api/target
nohup java -jar sky-tiku-api.jar --spring.profiles.active=test  > /root/log-tiku.file  2>&1 &
echo 'sky-tiku start successfully!'