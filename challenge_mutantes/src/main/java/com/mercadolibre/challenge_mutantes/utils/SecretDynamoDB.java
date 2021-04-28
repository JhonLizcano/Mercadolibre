package com.mercadolibre.challenge_mutantes.utils;

import java.util.Base64;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;
import com.mercadolibre.challenge_mutantes.model.SecretDynamoModel;

public class SecretDynamoDB {
	
	@SuppressWarnings({ "finally" })
	public static SecretDynamoModel getSecret() {

	    String secretName = "connect-DynamoDB";
	    String region = "us-east-2";

	    // Create a Secrets Manager client
	    AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
	    		.withRegion(region)
	    		.build();
	    
	    String secret = null, decodedBinarySecret = null;
	    GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
	    		.withSecretId(secretName);
	    GetSecretValueResult getSecretValueResult = null;

	    try {
	        getSecretValueResult = client.getSecretValue(getSecretValueRequest);
	    } catch (Exception e) {
	        e.getStackTrace();
	    } finally {
	    	return asignarSecretos(getSecretValueResult, secret, decodedBinarySecret);
	    }
	}
	
	private static SecretDynamoModel asignarSecretos(GetSecretValueResult getSecretValueResult, String secret, 
			String decodedBinarySecret) {
		if (getSecretValueResult != null) {
			if (getSecretValueResult.getSecretString() != null) {
		        secret = getSecretValueResult.getSecretString();
		    }
		    else {
		        decodedBinarySecret = 
		        		new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
		    }
		}
	    
	    Gson gson = new Gson();
	    SecretDynamoModel secretDynamo;
	    if (secret != null || decodedBinarySecret != null) {
	    	secretDynamo = secret != null ? gson.fromJson(secret, SecretDynamoModel.class) : 
	    		gson.fromJson(decodedBinarySecret, SecretDynamoModel.class);
	    } else {
	    	secretDynamo = 
	    			gson.fromJson(DotEnvUtil.getInstance().get("SECRETLOCAL"), SecretDynamoModel.class);
	    }
	    return secretDynamo;
	}
}