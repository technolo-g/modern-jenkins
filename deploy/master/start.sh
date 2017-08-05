#!/bin/bash -el

echo "INFO: (re)Starting Jenkins"
docker-compose down -v
docker-compose up -d

echo "INFO: Use the following command to watch the logs: "
echo "docker-compose logs -f"

