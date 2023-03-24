# Getting Started
### Introduction
This is a simple Spring Boot application that simulates 
purchases. This is based on the code of [another repository](https://github.com/Ruriel/AssemblyDecisions), also from me.
### Environment Variables
You can change the values of the .env file to run the application in a different context or port.
The variables are:

    MYSQL_HOST: Address where the Postgres instance is running. Defaults to localhost.
    MYSQL_DATABASE: Name of the database. Defaults to assemblydb.
    MYSQL_ROOT_PASSWORD: Postgres password. Defaults to postgres.
    MYSQL_INTERNAL_PORT: Port to be used within the container. Defaults to 5432.
    MYSQL_EXTERNAL_PORT: Port that will be used outside the container. Defaults to 5432.
    SERVER_EXTERNAL_PORT: Port where the application will run. Defaults to 8081.
    SERVER_INTERNAL_PORT: Port where the application runs internally. Defaults to 8081.
    CONTEXT_PATH: Base URL for each endpoint. Defaults to /api.
    TZ: Timezone to be used by the application. Defaults to America/Sao_Paulo.
    LOG_FOLDER: The folder where the Logs will be saved.
    VOLUME_FOLDER: The volume folder where the Database will save the data.
    RABBIT_MQ_HOST: RabbitMQ host.
    RABBIT_MQ_USERNAME: RabbitMQ User. Defaults to guest.
    RABBIT_MQ_PASSWORD: RabbitMQ password. Defaults to guest.
    RABBIT_MQ_PORT: RabbitMQ port. Defaults to 15672.
    RABBIT_MQ_CONCURRENT_CONSUMERS: Number of concurrent consumers. Defaults to 10.
    RABBIT_MQ_MAX_CONCURRENT_CONSUMERS: Max number of concurrent consumers. Defaults to 30.
### Requirements
    Java 17.0.6
    Docker 20.10.22 (for production only)
    MySQL 8.0 or compatible
### How to run
To run the application in production mode, you have to run the following command:
```
docker compose up -d
```
Be aware it will create a data folder in the root of the project. If you're using Windows, be sure to run docker-compose 
using Windows Powershell.


### Reference Documentation
Details about the REST API can be found on the swagger.json file.

### Uploading to AWS
#### Setting up MySQL
First, we need to setup a MySQL database on RDS.

1- Select RDS from the AWS Management Console.

2- Select MySQL as the Database.

3- Choose Dev/Test since we are going to test our application.

4- Specify database details such as instance name, username, password, etc...

5- Specify the Advanced Settings, including Database Name and port.

6- Click in Launch DB Instance.

7- Replace the MYSQL variables values in the .env file with the ones provided by RDS.

#### Setting up RabbitMQ
After that, we should launch a RabbitMQ instance using Amazon MQ.

1- Login to the Amazon MQ console.

2- Select RabbitMQ in the Select broker engine page and click next.

3- In the Select deployment mode page, select Cluster deployment as Deployment mode and click next.

4- Define the Broker name and the type of agent instance.

5- In the Configure settings, set a username and password.

6- In the next page, review your configuration and click on create agent.

7- Copy the host, username, password and port 
of the broker instance in the .env file.

#### Setting up ECS
1- Make sure your credentials are stored at the $HOME/.aws/credentials folder.

2- Create a new docker context
```
docker context create ecs newcontext
```
3- Run the following command:
```
docker compose -f docker-compose.aws.yaml up
```
