package com.mercadolibre.challenge_mutantes.domain;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import com.mercadolibre.challenge_mutantes.model.DnaSequence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "ProccesedDna")
public class DnaSequenceDB {
	
	@DynamoDBHashKey
	@DynamoDBTypeConvertedJson
	private List<DnaSequence> sequence;
	
	@DynamoDBAttribute
	private boolean mutant;
}