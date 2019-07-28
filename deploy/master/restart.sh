#!/bin/bash -el

echo "INFO: (re)Starting Jenkins & Gitea without volume wipe"
docker-compose stop
docker-compose up -d

echo "INFO: Use the following command to watch the logs: "
echo "docker-compose logs -f (or just ./logs for short)"

