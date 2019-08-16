# Dockerized Jenkins Build System

This repo contains an example of a fully automated Jenkins & Docker build
system. It was originally developed as part of a tutorial on the following blog:
[The CI/CD Life](http://cicd.life)

It has been adopted by a new maintainer intending to take it further :-)

## Table of contents

Here are links to the _originally_ available tutorial sections:

* [Unit 1 / Part 1: Planning a Jenkins + Docker CI System](http://cicd.life/u1-p1-planning-jenkins-docker-ci-infrastructure/)
* [Unit 1 / Part 2: Architecting a Jenkins + Docker CI System](http://cicd.life/u1-p2-architecting-jenkins-docker-ci-system/)
* [Unit 2 / Part 1: GitHub + GitHub Flow](http://cicd.life/u2-p1-designing-the-code-repository/)
* [Unit 2 / Part 2: Building the Base Jenkins Image (and Intro to Docker)](http://cicd.life/u2-p2-building-base-jenkins-docker-image/)
* [Unit 2 / Part 3: Building the Jenkins Master Image](http://cicd.life/u2-p3-building-jenkins-master-image/)
* [Unit 2 / Part 4: The Jenkins Plugin Image](http://cicd.life/u2-p4-building-jenkins-plugin-image/)
* [Unit 2 / Part 5: Starting Jenkins with Docker Compose](http://cicd.life/u2-p5-writing-docker-compose-file/)
* [Unit 3 / Part 1: Intro to the Jenkins Groovy Init System](http://cicd.life/u3-p1-intro-jenkins-groovy-init-system/)
* [Unit 3 / Part 2: Configure Jenkins URL](http://cicd.life/u3-p2-configure-jenkins-url-with-groovy/)
* [Unit 3 / Part 3: Managing Secrets in GitHub](http://cicd.life/u3-p3-transcrypting-secrets-github-repo/)
* [Unit 3 / Part 4: Configuring the GitHub Jenkins Plugin](http://cicd.life/u3-p4-configuring-jenkins-github-groovy/)

Please follow along the original tutorial if you'd like to learn how to deploy a
a Modern Jenkins Infrastructure on Docker!

## New stuff

Beyond the original tutorial covering mostly base Jenkins and Docker topics some new topics have been added, with details in internal PRs:

* [PR #1](https://github.com/Cervator/modern-jenkins/pull/1) - Adding a Vagrant box to run Docker inside
* [PR #2](https://github.com/Cervator/modern-jenkins/pull/2) - Better Gradle and Groovy integration
* [PR #3](https://github.com/Cervator/modern-jenkins/pull/3) - Hooking up Job DSL including samples (later enhanced further)
* [PR #4](https://github.com/Cervator/modern-jenkins/pull/4) - Hobo GitOps! Expand control further to include external DSL and Manifest repos
* [PR #5](https://github.com/Cervator/modern-jenkins/pull/5) - Use Gitea for embedded Git repos + sync files from workspace into Docker

At this stage to see the new stuff in action from a fresh clone using Vagrant do the following:

* `vagrant up` to get your Vagrant box
* `vagrant ssh` to get _into_ your Vagrant box
* `cd /vagrant/images/jenkins-base` + `./build.sh` to build the first Docker image (the base for the other two images)
* `cd ../jenkins-master/` + `./build.sh` to build the second image - this contains the Jenkins master itself
* `cd ../jenkins-plugins/` + `./build.sh` to build the third and final image - this contains plugins, init scripts, and DSL stuff
* `cd ../../deploy/master/` + `chmod +x *.sh` +`./restart.sh` to then actually use Docker Compose to stand up everything involved
  * Jenkins will be available at http://localhost:8080
  * Gitea will be available at http://localhost:3000
  * If you want to *wipe* volumes for both then use `./wipe.sh` instead of the restart script

A presentation PDF is included with the repo that covers "Hobo GitOps" - eventually a webinar recording of some sort should become available :-)

A second presentation PDF is also included for a similar talk focusing on Incident Management

## Future stuff

Plans and ideas

* Convert from boring properties files and JSON to [Jenkins CasC compatible YAML](https://github.com/jenkinsci/configuration-as-code-plugin) but still be able to read it from utility jobs
* Rework the Docker image setup a bit - master war into master image, split apart plugin image from init scripts and DSL, etc
* Run several Hobo GitOps tiered masters at the same time to better showcase the promotion flow
* Prepare hooks for various GitOps operators, like [WeaveWorks Flux](https://github.com/weaveworks/flux)
* Improve the workspace to also support development work on setting up and configure things beyond Jenkins, such as Nexus or Sonarqube
* Include support for local manifests / environments where you can begin, then later push those into external repos (nested Git roots yay!)
