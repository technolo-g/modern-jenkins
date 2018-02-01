#!/bin/bash -el
# images/jenkins-base/build.sh

# Define our image name
image_name=modernjenkins/jenkins-base:latest

# Accept any args passed and add them to the command
docker image build ${@} -t $image_name $(dirname -- "$0")

# If we add PUSH=true to the command, it will push to the hub
if [ "$PUSH" = true ] ; then
  docker image push $image_name
fi
