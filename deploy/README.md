# Deployment Scripts

Jenkins is deployed via Docker Compose. In order to run the service locally, use
the following commands:

```
# Get into the deploy directory
cd deploy/master

# Start the service as a daemon
docker-compose up -d

# View logs
docker-compose logs -f

# Stop Jenkins
docker-compose down -v

# Pull new images
docker-compose pull
```

