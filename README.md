# Seat Arrangement Algorithm

Write a backend API with an endpoint that helps seat audiences in a flight based on the following input and rules. 
Code must be accompanied by tests.

## Rules for seating:

 - Always seat passengers starting from the front row to back, starting from the left to the right
 - Fill aisle seats first followed by window seats followed by center seats (any order in center seats)
 
### Input
 - a 2D array that represents the rows and columns e.g: [[3,4], [4,5], [2,3], [3,4]]
 - The number of passengers waiting in the queue.
 
### Output

It is up to you, keep in mind that you need to assume this backend endpoint will be used by a frontend developer.

## Do

 - Use any framework or tools, give reasons why do you think it is the perfect tools for this assignment
 - Follow coding best practices/conventions that you are familiar with
 - Think of it as a real project deliverable and that the API will be consumed by other developers
 - Code must be accompanied by tests
 - Please provide instructions on how to run and build the project on README.md
 - Make sure reviewers can easily set it up and review it

## What to check

I use Spring framework to develop the API as it's lightweight, serverless, organized, modular and very easy to test. 
It has a developer friendly module for REST API development too that can make things more easier for our project development.

I enable caching on seat arrangement request for faster execution.

This project has SonarLint integration, sadly no no compatible Springfox for Swagger documentation is available yet.
 
The API return a 2D array of seat arrangement wrap in a response object. Here is the output from the input array above:

```
{
   "seatArrangements":[
      [
         19, 25, 26, 1, 2, 27, 28, 29, 3, 4, 30, 5, 6, null, null, 20
      ],
      [
         21, null, null, 7, 8, null, null, null, 9, 10, null, 11, 12, null, null, 22
      ],
      [
         23, null, null, 13, 14, null, null, null, 15, null, null, null, 16, null, null, 24
      ],
      [
         null, null, null, null, 17, null, null, null, 18, null, null, null, null, null, null, null
      ],
      [
         null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null
      ]
   ]
}
```
 
## How to Run

You must have the following installed into your local machine.
 
 - Java 8
 - Spring STS 4 - https://spring.io/tools
 - Lombok - https://projectlombok.org/setup/eclipse
 - Postman

 1. Download and install Spring STS into your local machine.
 2. I'm using Lombok so it must be setup on the tester's machine. It removes some of the boilerplate codes like getters and setters.
 3. Import the zipped project into your Spring STS environment.
 4. Run the project as a Spring Boot App.
 5. Open/Import the file src/test/resources/armillary-test.postman_collection.json in postman.
 6. Run both the requests in the collection and observe the result.

 
# Authors

* **Edward P. Legaspi** - *Architect* - [czetsuya](https://bitbucket.com/czetsuya)

# URLs

 * https://github.com/czetsuya