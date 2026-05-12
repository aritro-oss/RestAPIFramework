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
When I call "addProductAPI" with "POST" request
Then the response "status" should be 201
And the response should add new product

@deleteProduct
Scenario: delete product
Given the API base url is set
When I call "deleteProductAPI" with "DELETE" request
Then the response "status" should be 200
And the response should be deleted