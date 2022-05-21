
Retail-Discount-Store is an online store where discount will be provided to the user(Employee, Affiliate and Customer) based on the different terms and condition.

There are two controllers available for driving the functionalities.

1. DiscountController : This class is responsible for the discount functionality.
The endpoint url is http://localhost:8080/discount/user which is a POST request and the example payload is given below :
{
    "userName":"Arya231",
    "orderPrice":"2000"
}
The above endpoint will return the final bill value which has to be paid by the user.

2. UserController : This controller is useful for the user creation in the database.
The endpoint url is http://localhost:8080/user/create which is a POST request and the example payload is given below :
{
    "userName":"Arya23",
    "userType":"Employee",
    "numberOfYears":"10"
}
The above endpoint will return the user details.

All of the above functionalities are following the MVC flow. Kindly find the below flow :

Controller -> Service -> Dao Layer(Mongo DB)

I have used the spring security to authenticate the user before the API call.
SecurityConfiguration is the class responsible for the complete Security Configuration.

Docker :- 

The entire application can be easily deployed in the Docker container by using the docker compose file which is residing in the src/main/resources path.
There are two different services created in the Docker Compose file i.e one is Mongo Service and another is retails discount store service.
Image, Container_Name & Ports are defined for each of the services.

By simply going to src/main/resources and execute the command docker-compose up will spin up the Mongo container first and then it will spin up the 
retail-discount-store container.

By executing the command : docker exec -it mongodiscountdb bash
you can easily verify the collection in Mongo.

Here you will find the database name is "discount"

By running the below command, you can verify the records in the collection :
docker.{collectionname}.find().pretty() // collectionname will be usertype, userdetails & db_sequence

JACOCO Plugin :- 

I have configured the Jacoco plugin for generating the test coverage as well.
You just need to execute mvn clean test and then reload the project and find the coverage report under target/site/jacoco/index.html



