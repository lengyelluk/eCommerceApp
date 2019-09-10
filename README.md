# eCommerce Application

Rest API for an e-commerce application which enables the user to login, add/remove items to/from the cart, submit an order or get history of user orders.

The project contains 5 packages:

**demo** - this package contains the main method which runs the application

**model.persistence** - this package contains the data models that Hibernate persists to H2. There are 4 models: Cart, for holding a User's items; Item , for defining new items; User, to hold user account information; and UserOrder, to hold information about submitted orders. 

**model.persistence.repositories** - these contain a JpaRepository interface for each of models. This allows Hibernate to connect them with database.

**model.requests** - this package contains the request models. The request models will be transformed by Jackson from JSON to these models as requests are made. Note the Json annotations, telling Jackson to include and ignore certain fields of the requests.

**controllers** - these contain the api endpoints for the app, 1 per model. 

**security** - proper authentication and authorization using a combination of usernames and passwords for authentication, as well as JSON Web Tokens (JWT) to handle the authorization. Passwords are hashed and save only afterwards. When creating a new user, password is requested twice, to make sure user set the desired password.

User can used the standard endpoint to login to the app.

POST /login 

``json
{
    "username": "test",
    "password": "somepassword"
}
``

After successful validation of credentials, 200 OK is returned with an Authorization header which is a JWT. JWT must be sent as a Authorization header for all other rqeuests. If it's not present, endpoints return 401 Unauthorized. If it's present and valid, the endpoints function as normal.

### Instructions

1. Clone the repository
2. Start the app with **mvn spring-boot:run**
3. Test Api executing any of the endpoints below:

Create a new user

POST  /api/user/create

``json
{
	"username": "Alena",
	"password": "secretOne",
	"confirmPassword": "secretOne"
}
``

Login with an existing user

POST  /login

``json
{
	"username": "Alena",
	"password": "secretOne"
}
``

Authorization header is returned: e.g. ``json
Authorization →Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMdWthcyIsImV4cCI6MTU2OTAxMTc0N30.GLFoiypNLFaHEMXHIJCo9Nq7d5Y5Cu6mwNHCVBrwr-fwvhHZiyPj4nMnZKxBf4ILolqHqMTN3KvBRNCJ-6duBA
``
The authorization header must be used with each of the following requests:

Add an item to the cart

POST  /api/cart/addToCart

``json
{
	"username": "Alena",
	"itemId": 1,
	"quantity": 1	
}
``

Remove an item from the cart

POST  /cart/removeFromCart

``json
{
	"username": "Alena",
	"itemId": 1,
	"quantity": 1	
}
``

Submit an order

POST  /api/order/submit/{username}

Get order history of the user

GET  /api/order/history/{username}
