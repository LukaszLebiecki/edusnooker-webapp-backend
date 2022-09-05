docker build -t edusnooker .
docker stop edusnooker
docker rm edusnooker
docker run -d -p 8080:8080