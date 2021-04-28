package com.mercadolibre.challenge_mutantes.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "StatsDna")
public class DnaStats {
	
	@DynamoDBHashKey
	@DynamoDBAttribute
	private String statsId;
	
	@DynamoDBAttribute
	private Long humans;
	
	@DynamoDBAttribute
	private Long mutants;
	
	@DynamoDBAttribute
	private Double ratio;
}