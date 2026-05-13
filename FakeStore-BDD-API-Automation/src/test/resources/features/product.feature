Feature: Get All Products

@getProduct
Scenario: Get all products
Given the API base url is set
When I call "getProductAPI" with "GET" request
Then the response "status" should be 200
And the response should contain products

@addProduct
Scenario: Add products
Given the API base url is set
When I call "addProductAPI" with "POST" request with "addProductPayload"
Then the response "status" should be 201
And the response should add new product

@deleteProduct
Scenario: delete product
Given the API base url is set
When I call "deleteProductAPI" with "DELETE" request
Then the response "status" should be 200
And the response should be deleted

@updateProduct
Scenario: update a product
Given the API base url is set
When I call "addProductAPI" with "POST" request with "updateProductPayload"
Then the response should add new product 
When I call "updateProductAPI" with "PUT" request
Then the response "status" should be 200
And the response should be updated

@addNewUser
Scenario: add a new user
Given the API base url is set
When I call "addUserAPI" with "POST" request with "addUserPayload"
Then the response "status" should be 201
And the response should add new user


@authLogin
Scenario: autheticate a user
Given the API base url is set
When I call "addUserAPI" with "POST" request with "addUserPayload"
Then the response "status" should be 201
And the response should add new user
When I login with created user
Then the response "status" should be 200