# Test for quering the redis cache which is hosted on the a web server mentioned below.
# When a user from the front end querys the /getData call in pubsubClient, the aggregator service published messages into the user's queue.
# To test pass the user Id, and if the data is None it means that either user has not subscribed yet or the cache data has expired.
import redis
import json
r = redis.Redis(
    host='54.210.166.203',
    port=6379,
    password=''
)
data = r.lrange("e6de6f0-a886-6c0-b4cc-ec077d11fccd", 0, -1)

print(data)
