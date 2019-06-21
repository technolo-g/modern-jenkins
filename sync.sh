#!/bin/bash

# This script is meant to run inside Vagrant, synchronizing its mapping to the host OS to a secondary path
# That secondary path is then Docker-mapped into the Jenkins Master volume and accessible from there
# There is a limitation if trying to directly map the root of the workspace into /vagrant then directly into Docker

# Delay just a bit - this gets triggered pretty early, may avoid some race conditions with the OS startup
sleep 10;

while true; do

  rsync -avu --delete "/vagrant/" "/var/vagrant";

  sleep 2;

done
