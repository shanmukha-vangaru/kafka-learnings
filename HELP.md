# Getting Started

### Install Docker
For reference, please consider the following sections:

* [Ubuntu](https://docs.docker.com/engine/install/ubuntu/)
* [Windows](https://docs.docker.com/desktop/install/windows-install/)

### Up the kafka cluster and its components

* Open terminal in project directory and execute the below command. [INFO: -d will up the docker containers in detached mode so that you can execute other commands in the same terminal]

```shell script
sudo docker-compose up -d
```

* You should be seeing the below output

<pre>Starting kafka_postgres_1 ... <font color="#26A269">done</font>
Starting zookeeper        ... <font color="#26A269">done</font>
Starting broker           ... <font color="#26A269">done</font>
Starting schema-registry  ... <font color="#26A269">done</font>
Starting connect          ... <font color="#26A269">done</font>
Starting rest-proxy       ... <font color="#26A269">done</font>
Starting ksqldb-server    ... <font color="#26A269">done</font>
Starting ksql-datagen     ... <font color="#26A269">done</font>
Starting control-center   ... <font color="#26A269">done</font>
Starting ksqldb-cli       ... <font color="#26A269">done</font>
</pre>

* Make sure all the containers are up and running as shown below by using the command  ```sudo docker-compose ps```. If not restart the docker containers using ```sudo docker-compose restart```
<pre>      
Name                    Command               State                       Ports                     
broker             /etc/confluent/docker/run        Up      0.0.0.0:9092-&gt;9092/tcp, 0.0.0.0:9101-&gt;9101/tcp
connect            /etc/confluent/docker/run        Up      0.0.0.0:8083-&gt;8083/tcp, 9092/tcp              
control-center     /etc/confluent/docker/run        Up      0.0.0.0:9021-&gt;9021/tcp                        
kafka_postgres_1   docker-entrypoint.sh postgres    Up      0.0.0.0:5436-&gt;5432/tcp                        
ksql-datagen       bash -c echo Waiting for K ...   Up                                                    
ksqldb-cli         /bin/sh                          Up                                                    
ksqldb-server      /etc/confluent/docker/run        Up      0.0.0.0:8088-&gt;8088/tcp                        
rest-proxy         /etc/confluent/docker/run        Up      0.0.0.0:8082-&gt;8082/tcp                        
schema-registry    /etc/confluent/docker/run        Up      0.0.0.0:8081-&gt;8081/tcp                        
zookeeper          /etc/confluent/docker/run        Up      0.0.0.0:2181-&gt;2181/tcp, 2888/tcp, 3888/tcp    
</pre>

### Start producing messages to the below topics
* user-clicks
* user-purchases
* user-searches
* user-reviews
* user-logins
```
http://localhost:8080/api/produce/<topic-name>?requestSize=<number-of-messages-to-be-produced>
```
