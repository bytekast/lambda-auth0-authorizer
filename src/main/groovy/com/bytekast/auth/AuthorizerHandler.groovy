package com.stedi.auth

import com.amazonaws.services.lambda.runtime.Context
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import groovy.json.JsonOutput
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j

@Log4j
@CompileStatic
class AuthorizerHandler {


  Map handleRequest(final Map<String, Object> input, final Context context) {

    log.debug JsonOutput.toJson(input)
    String token = input.authorizationToken?.toString()?.minus('Bearer')?.trim()
    String resource = input.methodArn

    // authenticate
    Map claims = verifyToken(token)

    // return policy
    return createPolicy(claims, resource)
  }

  Map<?, ?> verifyToken(final String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(System.getenv('AUTH0_CLIENT_SECRET'))
      JWTVerifier verifier = JWT.require(algorithm).build()
      DecodedJWT jwt = verifier.verify(token)
      jwt.getClaims().collectEntries { k, v -> [k, v.asString()] }
    } catch (e) {
      log.error("Unable to validate jwt: ${token}", e)
      null
    }
  }

  Map createPolicy(final Map claims, final String resource) {
    [
        principalId   : claims?.user_id,
        policyDocument: [
            Version  : '2012-10-17',
            Statement: [
                Action  : 'execute-api:Invoke',
                Effect  : claims ? 'Allow' : 'Deny',
                Resource: resource
            ]
        ],
        context       : claims
    ]
  }
}
