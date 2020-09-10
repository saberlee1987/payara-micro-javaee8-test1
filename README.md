#How to deploy on  the docker and kubernetes
#### After install the docker create Dockerfile on root your project
#### in first line write from image container from a docker registry for example docker hub
#### in this example we use zulu-alpine-payara-micro 
#### in second line copy jar file to payara server
#### for build and make image file use this command
```
docker build -t dockerImageName .
```
###show docker images

```docker image ls   ```    ==> show all images on your machine

### pull a container from docker hub
#### docker pull dockerImageContainer for example docker pull qaware/zulu-alpine-payara-micro

####Show all process in docker
####docker ps 
##After build docker image for run docker image write this command
####docker run dockerImageName -p portHostMachine:portOnDocker
####example docker run -p 9000:9000 sample01

###docker login for docker hub
```#docker login --username=username ```
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
## for create docker-compose create docker-compose.yml

```
version: "3.0"

services:
  payara-micro-javaee8-test1:
    build:
      context: .
    image: payara-micro-javaee8-test1
    ports:
    - "8080:8080"
    networks:
    - jee8net
     
networks:
  jee8net:
    internal: true
```
Here we define one services, payara-micro-javaee8-test1.
The ports tag under each service defines the port forwarding rules (which we discussed). 
The image tag  points to the Docker image.You can define multiple services under the
depends_on tag. Finally, you may notice the value of the restart tag is set to always,
under both the services, which instructs the Docker engine to restart the services always, in case they are down

###Launching the Application with Docker Compose
#### use this command docker-compose up -f docker-compose.yml

#Introduction to Kubernetes

##After install the kubernetes must be start the kubernetes

###minikube start

Once Minikube starts, run the following command to verify the communication
between kubectl and the Kubernetes cluster. This prints the version of both the kubectl
client, and the Kubernetes cluster.

#####kubectl version

Also, with the following command, we can determine where the Kubernetes cluster
is running
####kubectl cluster-info
#####Kubernetes master is running at https://192.168.99.100:8443

###Now we can use following command to see all the nodes in our Kubernetes cluster.
###Since we are running Minikube, you will only find one node.

####kubectl get nodes

###To find more details about a given node, use the following command. This will return
###a lot of metadata related to the node

##Creating a Pod
```
kind: Pod
metadata:
  name: test-pod
  labels:
     app: testapp

spec:
   containers:
     - name: payara-micro-javaee8-test1
       image: saber66/payara-micro-javaee8-test1
       ports:
         - containerPort: 8080
```       
Once the application developer comes up with the deployment descriptor, he or she
will feed that into the Kubernetes control plane using kubectl, via the API server. Then
the scheduler will schedule the pods on worker nodes, and the corresponding container
runtime will pull the container images from the Docker registry.

##Creating a Pod with a YAML File
```kubectl create -f test-pod.yml ```

###If the pod is successfully created, the following command should return the status of the pod.

####kubectl get pods ===> Show all pods

#### kubectl describe pod test-pod 

###If you want to delete a pod, use the following command

```kubectl delete -f test-pod.yml```

##Creating a Service with a YAML File
```
apiVersion: v1
kind: Service
metadata:
  name: testapp
spec:
  selector:
    app: testapp
  ports:
    - port: 80
      targetPort: 8080
  type: NodePort
 ```
```kubectl create -f test-service.yml```

###kubectl get svc

####If you want to delete a service, use the following command. This will only remove the  service, not the corresponding pod.
###kubectl delete -f test-service.yml

##Deployments and Replica Sets
 
##The following commands first delete the service and then the pod

```
kubectl delete -f test-service.yml 

kubectl delete -f test-pod.yml 


apiVersion: apps/v1
kind: Deployment
metadata:
  name: test-deployment
  labels:
    app: test-deployment

spec:
  replicas: 4
  selector:
    matchLabels:
       app: test-deployment
  template:
    metadata:
      labels:
        app: test-deployment
    spec:
      containers:
        - name: payara-micro-javaee8-test1
          image: saber66/payara-micro-javaee8-test1
          ports:
            - containerPort: 8080


kubectl create -f test-deployment.yml

```
If the deployment is successfully created, the following command should return
the status of it. The output says that three replicas of the pod defined under the
corresponding deployment have been created and are ready to use.

```kubectl get deployments```

The following command shows all the pods created by this deployment.

```kubectl get pods```
 
#Scaling a Deployment
###You can scale a Deployment by using the following command:

```kubectl scale deployment.v1.apps/nginx-deployment --replicas=10``` 
####The output is similar to this: 
```deployment.apps/nginx-deployment scaled```
 
 ###Assuming horizontal Pod autoscaling is enabled in your cluster, you can setup an autoscaler for your Deployment and choose the minimum and maximum number of Pods you want to run based on the CPU utilization of your existing Pods.
 
 ```kubectl autoscale deployment.v1.apps/nginx-deployment --min=10 --max=15 --cpu-percent=80```

####The output is similar to this:
```deployment.apps/nginx-deployment scaled``` 
 
 
Visit this link for deploy kubernetes  (https://kubernetes.io/docs/concepts/workloads/controllers/deployment/)
 