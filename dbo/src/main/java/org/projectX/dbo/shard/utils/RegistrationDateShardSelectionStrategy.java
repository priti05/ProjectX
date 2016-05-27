package org.projectX.dbo.shard.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.shards.ShardId;
import org.hibernate.shards.strategy.selection.ShardSelectionStrategy;

@Deprecated
public class RegistrationDateShardSelectionStrategy implements ShardSelectionStrategy {

	public ShardId selectShardIdForNewObject(Object obj) {
		// TODO Auto-generated method stub
		Integer databaseNumber = null;
		try{
			InputStream input = new FileInputStream("project-shard.properties");
			Properties prop = new Properties();
			prop.load(input);
			databaseNumber = Integer.valueOf(prop.getProperty("shard.database.number"));
		}catch(Exception e){
			throw new IllegalArgumentException("Something wrong");
		}
		return new ShardId(databaseNumber);
	}

	
	
}
