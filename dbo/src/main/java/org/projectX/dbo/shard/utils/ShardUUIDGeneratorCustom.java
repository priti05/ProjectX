package org.projectX.dbo.shard.utils;

import java.io.Serializable;
import java.math.BigInteger;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDHexGenerator;
import org.hibernate.shards.ShardId;
import org.hibernate.shards.session.ShardedSessionImpl;
import org.hibernate.shards.id.ShardEncodingIdentifierGenerator;
import org.hibernate.shards.util.Preconditions;

@Deprecated
public class ShardUUIDGeneratorCustom  extends UUIDHexGenerator implements ShardEncodingIdentifierGenerator{

	private static String ZERO_STRING = "00000000000000000000000000000000";
	
	private int getShardId() {
	    ShardId shardId = ShardedSessionImpl.getCurrentSubgraphShardId();
	    Preconditions.checkState(shardId != null);
	    return shardId.getId();
	  }
	
	
	public ShardId extractShardId(Serializable identifier) {
		 Preconditions.checkNotNull(identifier);   
		 String strippedHexId = ((BigInteger)identifier).toString(16);
		 String hexId = ZERO_STRING.substring(0, 32 - strippedHexId.length()) + strippedHexId;
		 return new ShardId(Integer.decode("0x" + hexId.substring(0, hexId.length()-28)));
	}
	
	 @Override
	  public Serializable generate(SessionImplementor session, Object object) {
	    String id =  new StringBuilder(32).append(format((short)getShardId()))
	                                      .append(format(getIP()))
	                                      .append(format((short)(getJVM()>>>16)))
	                                      .append(format(getHiTime()))
	                                      .append(format(getLoTime()))
	                                      .append(format(getCount()))
	                                      .toString();
	    return new BigInteger(id, 16);
	  }

}
