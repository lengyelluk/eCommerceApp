IN PROGRESS

#eCommerce Application

Rest API for an e-commerce application which enables the user to login, add/remove items to/from the cart, submit an order or get history of user orders.

The project contains 5 packages:

demo - this package contains the main method which runs the application

model.persistence - this package contains the data models that Hibernate persists to H2. There are 4 models: Cart, for holding a User's items; Item , for defining new items; User, to hold user account information; and UserOrder, to hold information about submitted orders. 

model.persistence.repositories - these contain a JpaRepository interface for each of models. This allows Hibernate to connect them with database.

model.requests - this package contains the request models. The request models will be transformed by Jackson from JSON to these models as requests are made. Note the Json annotations, telling Jackson to include and ignore certain fields of the requests.

controllers - these contain the api endpoints for the app, 1 per model. 

We need to add proper authentication and authorization controls so users can only access their data, and that data can only be accessed in a secure way. We will do this using a combination of usernames and passwords for authentication, as well as JSON Web Tokens (JWT) to handle the authorization.

As stated prior, we will implement a password based authentication scheme. To do this, we need to store the users' passwords in a secure way. This needs to be done with hashing, and it's this hash which should be stored. Additionally when viewing their user information, the user's hash should not be returned to them in the response, You should also add some requirements and validation, as well as a confirm field in the request, to make sure they didn't make a typo.

Add spring security dependencies:
Spring-boot-starter-security
JWT does not ship as a part of spring security, so you will have to add the
java-jwt dependency to your project.
Spring Boot ships with an automatically configured security module that must be disabled, as we will be implementing our own. This must be done in the Application class.
Create password for the user
Once that is disabled, you will need to implement 4 classes (at minimum, you can break it down however you like):
a subclass of UsernamePasswordAuthenticationFilter for taking the username and password from a login request and logging in. This, upon successful authentication, should hand back a valid JWT in the Authorization header
a subclass of BasicAuthenticationFilter.
an implementation of the UserDetailsService interface. This should take a username and return a userdetails User instance with the user's username and hashed password.
a subclass of WebSecurityConfigurerAdapter. This should attach your user details service implementation to Spring's AuthenticationManager. It also handles session management and what endpoints are secured. For us, we manage the session so session management should be disabled. Your filters should be added to the authentication chain and every endpoint but 1 should have security required. The one that should not is the one responsible for creating new users.
Once all this is setup, you can use Spring's default /login endpoint to login like so
