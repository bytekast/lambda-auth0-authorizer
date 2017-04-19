# lambda-auth0-authorizer
AWS Lambda Auth0 Authorizer for API Gateway

Uses [Auth0](https://auth0.com/) for verification. This custom authorizer simply validates the JWT token passed via the `Authorization: Bearer xxxxxxxxxxxxxxxx` API request header.

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

See [serverless.yml](serverless.yml)

- `AUTH0_CLIENT_SECRET`

---

### Build and Publish

> To build the project

`gradle clean build`
<br/>

> To publish the artifacts to the S3 Maven Repository

`gradle publish`
<br/>

> To cut a release, run the command below and follow the instructions.

`gradle release`
<br/>


> To deploy the function to AWS Lambda

`serverless deploy`
<br/>

---