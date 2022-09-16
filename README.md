# Newsfeed Application
• Designed and developed a distributed news application based on the publisher-subscriber model powered by ReactJS
frontend, Python middleware, Java-Spring Boot backend and Redis cache data store.

• Aggregated news articles from different open-source APIs and stored them in cache queues of subscribed users which
enables the broker to send news and highlights to the users based on their preferences.

## Application Startup Steps:

###Below are the steps to start the application in local machine

1. Start Aggregator service

⋅⋅1. cd newsfeed-aggregator-service
..2. mvn clean install
..3. cd target/
..4. java -jar aggregator-service-1.0-SNAPSHOT.jar

2. Start Redis

⋅⋅1. Install redis from https://redis.io/download/
..2. start redis-server on port 6379

3. Start Newsfeed broker service

⋅⋅1. cd newsfeed-broker-service
..2. mvn clean install
..3. cd target/
..4. java -jar newsfeed-broker-service-1.0-SNAPSHOT.jar

4. Start Newsfeed client service

⋅⋅1. cd newsfeed-client-service
..2. pip3 install redis
..3. python3 .\pubsubClient.py

5. Start Newsfeed client UI

⋅⋅1. cd newsfeed-client-UI
..2. npm install
..3. npm start
..4. Go to browser and open http://localhost:3000/

6. Use the application

..1. Subscribe to a topic For e.g. technology
..2. Run the command "curl http://localhost:8081/publish/sports" from another terminal
