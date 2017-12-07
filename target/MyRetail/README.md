# MyRetail

This is sample java/Spring boot application that can be used as a starter for creating a microsservice complete with built-in product fetcting and updating the production information.

How to Run

1. Install the MongoDB in your system 
2. Install gradle 
3. Run the MongoDB - run mongod.exe ro start the mongodb database
4. clone the code from git repositry -https://github.com/srinivasalu83/MyRetail.git
5. Build the project using the command - gradle build
6. Run the command to start the application gradle bootRun
7. open the browser and visit swagger page http://localhost:8080/swagger-ui.html


Technology used
1. MongoDB 
2. Spring boot
3. Gradle
4. Swagger


My Retail Http methods
##Fetching the product information
Input: /product/{id} - The client receives the product information and it is valid, then return the product information such as product name, price

How it works

The APi receive the request and send to the url : redsky.target.com and retrive the production information but in order fetch the price, it will connect to that database and fetch the information.
The result displayed to the user  will consist combination of product id and price information.

# Update the price information based on product id
Input: /product/{id} - The client finds matching product and then update the price information

How it works
The APi receives the put request and will validate product exist and it it exist, then it will ensure , the JSON information is same, then it will update the product information.

####Sample Input: JSON Body - {"id":13860428,"name":"The LCD Blue Wide Screen TV - id","current_price":{"value": 13.49,"currency_code":"USD"}}


Success code for GET and PUT
code = 200 - Successfully retrieved list
code = 400 - You are not authorized to view the resource
code = 403 - Accessing the resource you were trying to reach is forbidden
code = 404 - The resource you were trying to reach is not found


Sample output screen shot:
GetList of products.png - displays collection of production
GetSuccess.png - displays GET Success
put success - successfully  updated the price information based on json

https://github.com/srinivasalu83/MyRetail/blob/master/target/MyRetail/Getlist%20of%20products.PNG
