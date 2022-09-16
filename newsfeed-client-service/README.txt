Author - Aashay Motiwala, Janan Gandhi, Karan Wagehla
Application Name - News App

There are three componenets in the application 
- Client (Subscriber)
- Client-side staging service (pubsubClient.py) 
- Broker 
- Server (Publisher).
- Aggregator

Install all the dependencies.
1) Run the react script by going into the directory and type:
		- npm install 
		- npm start
2) Run the client by going in the directory and type:
		- python3 .\pubsubClient.py

React should automatically a new browser tab at url (localhost:3000)


1. Subscribe to any topic of your choice and open postman or any API testing platform
Postman
	1) Create a Get request to http://34.239.121.32:8081/publish/<Topic name> ....{sports,technology,entertainment or politics}
	2) Fire the request
	3) After 25 secs you should see news popping up on your client side react page.

###########!!!!!           NOTE          !!!!!!!############################################################################################
Since we have hosted our Redis cache on a free server, the server always expires after 8 hrs.
If you dont reiceve news cards on your react page, it means the server's session has expired and need to fire it again.
Feel free to contact us in case the server does not respond, so that we can log in our account and rerun it.
############################################################################################################################################