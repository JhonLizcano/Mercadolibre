package com.mercadolibre.challenge_mutantes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.mercadolibre.challenge_mutantes.model.SecretDynamoModel;
import com.mercadolibre.challenge_mutantes.utils.SecretDynamoDB;

@Configuration
public class DynamoDBConfig {
	
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(buildAmazonDynamoDB());
	}
	
	private AmazonDynamoDB buildAmazonDynamoDB() {
		SecretDynamoModel secretDynamo = SecretDynamoDB.getSecret();
		
		return AmazonDynamoDBClientBuilder
				.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
						"dynamodb.us-east-2.amazonaws.com", "us-east-2"))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
						secretDynamo.getAccessId(), secretDynamo.getSecretId())))
				.build();
	}
}