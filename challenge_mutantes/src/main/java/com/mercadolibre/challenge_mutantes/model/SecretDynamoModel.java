package com.mercadolibre.challenge_mutantes.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SecretDynamoModel {
	
	@SerializedName("access_id")
	private String accessId;
	
	@SerializedName("secret_id")
	private String secretId;
}