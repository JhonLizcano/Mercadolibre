package com.mercadolibre.challenge_mutantes.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.mercadolibre.challenge_mutantes.domain.DnaSequenceDB;
import com.mercadolibre.challenge_mutantes.domain.DnaStats;
import com.mercadolibre.challenge_mutantes.utils.Constants;

@Repository
public class DnaSequenceDBRepository {
	
	@Autowired
    private DynamoDBMapper dynamoDBMapper;
	
	/*
	 * Método que guarda una secuencia de DNA analizado siempre y cuando no exista la secuencia en la BD
	 * Además de que alimenta las estadísticas
	 * @parameter dnaSequenceDB
	 * @parameter mutant
	 * @return dnaSequenceDB
	 */
	public DnaSequenceDB save(DnaSequenceDB dnaSequenceDB, boolean mutant) {
		DnaSequenceDB dnaBuscado =  dynamoDBMapper.load(DnaSequenceDB.class, dnaSequenceDB.getSequence());
		if(dnaBuscado == null) {
			saveStats(mutant);
			dynamoDBMapper.save(dnaSequenceDB);
		}
		return dnaSequenceDB;
	}
	
	/*
	 * Método que se encarga de guardar las estadísticas o actualizarlas
	 * @parameter mutant
	 */
	public void saveStats(boolean mutant) {
		DnaStats dnaStats = DnaStats.builder().statsId(Constants.STATS).build();
		DnaStats statsBuscado = getStats(dnaStats);
		if(statsBuscado == null) {
			dnaStats.setHumans(!mutant ? Constants.ONE_LONG : Constants.ZERO_LONG);
			dnaStats.setMutants(mutant ? Constants.ONE_LONG : Constants.ZERO_LONG);
			dnaStats.setRatio(mutant ? Constants.ZERO : Constants.ZERO_POINT_ONE);
			dynamoDBMapper.save(dnaStats);
		} else {
			statsBuscado.setHumans(!mutant ? statsBuscado.getHumans() + 1 : statsBuscado.getHumans());
			statsBuscado.setMutants(mutant ? statsBuscado.getMutants() + 1 : statsBuscado.getMutants());
			statsBuscado.setRatio(statsBuscado.getHumans() > Constants.ZERO ? 
					statsBuscado.getMutants() / Double.valueOf(statsBuscado.getHumans()) : Constants.ZERO);
			dynamoDBMapper.save(statsBuscado, new DynamoDBSaveExpression()
					.withExpectedEntry(Constants.STATS_ID, new ExpectedAttributeValue(
	                        new AttributeValue().withS(statsBuscado.getStatsId()))));
		}
	}
	
	/*
	 * Método que retorna el registro de estadíticas guardado en la BD
	 * @parameter dnaStats
	 */
	public DnaStats getStats(DnaStats dnaStats) {
		return dynamoDBMapper.load(DnaStats.class, dnaStats.getStatsId());
	}
}