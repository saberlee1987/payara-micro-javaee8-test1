#How to deploy on docker
#### After install the docker create Dockerfile on root your project
#### in first line write from image container from a docker registry for example docker hub
#### in this example we use zulu-alpine-payara-micro 
#### in second line copy jar file to payara server
#### for build and make image file use this command
##### docker build -t dockerImageName .

###show docker images
####docker image ls  ==> show all images on your machine

### pull a container from docker hub
#### docker pull dockerImageContainer for example docker pull qaware/zulu-alpine-payara-micro

####Show all process in docker
####docker ps 
##After build docker image for run docker image write this command
####docker run dockerImageName -p portHostMachine:portOnDocker
####example docker run -p 9000:9000 sample01

###docker login for docker hub
#####docker login --username=username
######This command will prompt you to enter the password, and the Docker client will
######store your credentials in the keychain. Next, use the following command to find the
######image ID of the Docker image we created before and copy the value of IMAGE ID field
######corresponding to the sample01 image; for example, 35188a2bfb00.

###docker images
######Now we need to tag our image with the Docker ID from the Docker Hub, as shown
######in the following command. Tagging helps create a more meaningful name for an image
######and since we plan to publish this image to the Docker Hub, make sure the tag follows
######the convention, where it starts with your Docker Hub account name. You would need to
######replace saber66 from your Docker Hub account name.
 
#Docker Compose

