#!/bin/bash -el

echo "INFO: Copying plugins container contents"
cp -rpv /usr/share/jenkins/ref/* /master-jenkins-plugins/

echo "INFO: Copy complete!"
