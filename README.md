# Hazelcast client-server topology in AWS ECS Fargate Cluster using DNS service discovery
Hazelcast is set up using client-server topology. 
When Hazelcast server instance comes up, it registers itself in AWS private namespace using service discovery.
Hazelcast client has DNS lookup custom service discovery, which looks up the private namespace and identify running Hazelcast server instances.

Hazelcast doesn't support AWS ECS Fargate service discovery at this moment, and it is in development now, refer [GitHub](https://github.com/hazelcast/hazelcast-aws/issues/86)

### Build
###### Build application and create local image
```
mvn clean package dockerfile:build
```

###### Build application and push image to remote repository
```
mvn clean package dockerfile:build dockerfile:push
```

### Run
###### Local
Hazelcast Server Service
```
Server 1 - VM Options: -Dspring.profiles.active=local -Dserver.port=8000 -Dmanagement.server.port=8001 -Dhz.mgmt.host=localhost
Server 2 - VM Options: -Dspring.profiles.active=local -Dserver.port=8002 -Dmanagement.server.port=8003 -Dhz.mgmt.host=localhost
```

Hazelcast Client Service
```
VM Options: -Dspring.profiles.active=local -Dhz.server.address=127.0.0.1
```

###### docker compose
```
docker-compose up -d
```

### Test
##### Local
###### Hazelcast Server
Health Check
```
http://localhost:8001/actuator/health
http://localhost:8003/actuator/health
```
###### Hazelcast Client
Health Check
```
http://localhost:8011/actuator/health
```
Save Token
```
curl -X POST \
  http://localhost:8010/api/v1/hz/client/tokens \
  -H 'Content-Type: application/json' \
  -d '{
	"username": "Foo Bar",
	"token": "a129837-xcv2422-fdd943875"
}'
```
Get Token
```
curl -X GET \
  'http://localhost:8010/api/v1/hz/client/tokens?username=Foo%20Bar'
```