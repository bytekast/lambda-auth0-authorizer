# lambda-auth0-authorizer
AWS Lambda Auth0 Authorizer for API Gateway

Uses [Auth0](https://auth0.com/) for verification. This custom authorizer simply validates the JWT token passed via the `Authorization: Bearer xxxxxxxxxxxxxxxx` API request header.

The decoded JWT `claims` object is passed to the `context` object of the response. [API Gateway]() then forwards the claims/context to the Lambda function handler. 

For more information about [Custom Authorizers](http://docs.aws.amazon.com/apigateway/latest/developerguide/use-custom-authorizer.html#api-gateway-custom-authorizer-input), see the [AWS Docs](http://docs.aws.amazon.com/apigateway/latest/developerguide/use-custom-authorizer.html#api-gateway-custom-authorizer-input).

### Local Development Prerequisites

> Install Gradle

`brew install gradle`
<br/>


> Install NPM and the Serverless CLI tools

`brew install node`

`npm install serverless -g`
<br/>

---

### Required Environment Variables

See [serverless.yml](serverless.yml#L9)

- `AUTH0_CLIENT_SECRET`

---

### Build and Publish

> To build the project

`gradle clean build`
<br/>


> To deploy the function to AWS Lambda

`serverless deploy`
<br/>

---
