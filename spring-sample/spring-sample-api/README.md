# Spring Sample APi

```bash
docker build -t noka/spring-sample-api .

docker run -d -p 8080:8080 noka/spring-sample-api --server.port=8080
```


```bash
# build file docker
mvn package docker:build
```