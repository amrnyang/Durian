# run shell in test environment
cd ..
mvn clean package -Dmaven.test.skip=true
cd sky-center/sky-center-api/target
nohup java -jar sky-center-api.jar --spring.profiles.active=prod  > /root/log-center.file  2>&1 &
echo 'sky-center start successfully!'