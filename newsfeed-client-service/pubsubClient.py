from http.server import BaseHTTPRequestHandler, HTTPServer
from time import sleep
from jsonrpclib import Server
import socket
from pyrsistent import l
import requests
import threading
import json
import redis

HOST = "localhost"
PORT = 8000
data = {}

r = redis.Redis(
    host='localhost',
    port=6379,
    password=''
)


def utf8len(s):
    return len(s.encode('utf-8'))

# Listener Func -> to poll the redis cache periodically at a given interval of time.
# It gets string data from the redis cache
# Key -> User_id
# Value -> Topic Name
# threading.Thread


class Listener():
    def __init__(self, topic_name, user_id):
        self.topic_name = topic_name
        self.user_id = user_id
        # GET call to Broker -> params(user_id) and topic_name
        print("\nSending GET call to broker for subscription to channel")
        print("Channel Name ->", self.topic_name, "User ID -> ", self.user_id)
        print("Making call")
        self.response = requests.get(
            "http://localhost:8080/"+self.topic_name+'/subscribe', params={'userId': self.user_id})
        print("Call made")
        print("Status Code -> ", self.response)


class ThreadedServer(object):
    def __init__(self):
        print("Client started, waiting for messages")

    def listen(self):
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
            sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
            sock.setsockopt(socket.SOL_SOCKET, socket.SO_KEEPALIVE, 1)
            sock.bind((HOST, PORT))

            while True:
                sock.listen()
                conn, addr = sock.accept()
                t = threading.Thread(target=self.keepalive,
                                     args=(conn, addr))
                t.start()
                t.join()

    def keepalive(self, client, addr):
        size = 1024
        with client:
            client.settimeout(5)
            while True:
                try:
                    request = client.recv(size).decode()
                    headers = request.split('\r\n')
                    REST = headers[0].split()
                    # print("Rest Api Url", REST)
                    if "/subscribe" in REST[1]:  # == "/":
                        Params = REST[1].split('?')
                        listener_params = Params[1].split("=")
                        listener_name = listener_params[1].split('&')
                        listener_name = listener_name[0]
                        topic_name = listener_params[2]
                        # print("User ", listener_name,
                        #       " Subscribing to ", topic_name)
                        listener_1 = Listener(topic_name, listener_name)
                    elif "/getData" in REST[1]:
                        # print("In getData Request", REST[1])
                        Params = REST[1].split('?')
                        listener_params = Params[1].split("=")
                        listener_params = listener_params[1].split('&')
                        user_id = listener_params[0]
                        topic_name = listener_params[1]
                        print("User_id -> ", user_id)
                        # Calling Redis Cache for data
                        user_data = r.lrange(
                            user_id, 0, -1)
                        # Parsing Byte array
                        convert_dataList = []
                        for d in user_data:
                            articles = d.decode('utf-8').replace("'", '"')
                            convert_dataList.append(articles)
                        dataDict = {"Data": []}
                        for article in convert_dataList:
                            dataDict['Data'].append(json.loads(article))
                        self.getData(client, dataDict["Data"])
                        print("Sending data to client")
                        # print(dataDict["Data"])
                    elif "/unsubscribe" in REST[1]:
                        print("In Unsubscribe Request")
                        Params = REST[1].split('?')
                        listener_params = Params[1].split("=")
                        listener_name = listener_params[1].split('&')
                        user_id = listener_name[0]
                        topic_name = listener_params[2]
                        print("User ", user_id,
                              " Unsubscribing to ", topic_name)
                        unsubscribe_response = requests.get(
                            "http://localhost:8080/"+topic_name+'/unsubscribe', params={'userId': user_id})
                        print("Unsubscribing status -> ",
                              unsubscribe_response)
                except Exception as e:
                    break

        client.close()

    def getData(self, client, user_data):
        json_data = json.dumps(user_data)
        client.sendall(str.encode("HTTP/1.1 200 OK\n", 'iso-8859-1'))
        client.sendall(str.encode(
            'Content-Type: application/json\n', 'iso-8859-1'))
        client.sendall(str.encode(
            'Access-Control-Allow-Origin: *\n', 'iso-8859-1'))
        client.sendall(str.encode('\r\n'))
        client.sendall(json_data.encode())


def main():
    ThreadedServer().listen()


if __name__ == '__main__':
    main()
