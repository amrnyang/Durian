# run shell in test environment
cd ..
mvn clean package -Dmaven.test.skip=true
cd sky-system/sky-system-api/target
nohup java -jar sky-system-api.jar --spring.profiles.active=test  > /root/log-system.file  2>&1 &
echo 'sky-system start successfully!'