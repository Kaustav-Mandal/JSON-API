## JSON-API Using Spring Boot

## Project Description
1. A source API of blogs is given which sends data in JSON Format. 
2. In order to access data from this API, different query parameters have been used in our API URL. 
3. There are some constraints on query parameters (e.g required, valid type of the parameters etc.)

## Technologies Used:
1. Spring Boot 2.6.2
2. Java
3. Postman
5. Maven
6. Spring Tool Suite IDE. 

## How to run the Project:
1. Import the project in STS/ Eclipse IDE and do a Maven update. 
2. The default port is 8080 and base url is : http://localhost:8080/api/posts/
3.  Run the below URLs from Postman client: 
    http://localhost:8080/api/posts/api/ping --> [which returns sucess message]
    http://localhost:8080/api/posts?tags=health&sortBy=likes&direction=asc --> [returns all the posts in ascending order by their likes and they are tagged to 'health']
 
## Different Constraints on URL

One Sample Post:
  "posts": [{
  "id": 1 ,
  "author": "Rylee Paul" ,
  "authorId": 9 ,
  "likes": 960 ,
  "popularity": 0.13 ,
  "reads": 50361 ,
  "tags": [ "tech" , "health" ]
  },
  ...
  ]
1. If 'tags' is missing then "error": "Tags parameter is required" is thrown. 
2. 'sortBy' parameter can have "id", "popularity", "likes", "read" these four values and if there is any other value passed then "error": "sortBy parameter is invalid" is shown. 
3. 'direction' parameter can have "asc" or "desc" values and if there is any other value passed then "error": "direction parameter is invalid" is shown.
 
