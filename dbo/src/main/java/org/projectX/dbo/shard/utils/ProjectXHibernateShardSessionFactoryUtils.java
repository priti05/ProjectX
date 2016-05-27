package org.projectX.dbo.shard.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.shards.ShardId;
import org.hibernate.shards.ShardedConfiguration;
import org.hibernate.shards.cfg.ConfigurationToShardConfigurationAdapter;
import org.hibernate.shards.cfg.ShardConfiguration;
import org.hibernate.shards.loadbalance.RoundRobinShardLoadBalancer;
import org.hibernate.shards.strategy.ShardStrategy;
import org.hibernate.shards.strategy.ShardStrategyFactory;
import org.hibernate.shards.strategy.ShardStrategyImpl;
import org.hibernate.shards.strategy.access.ParallelShardAccessStrategy;
import org.hibernate.shards.strategy.access.ShardAccessStrategy;
import org.hibernate.shards.strategy.resolution.AllShardsShardResolutionStrategy;
import org.hibernate.shards.strategy.resolution.ShardResolutionStrategy;
import org.hibernate.shards.strategy.selection.RoundRobinShardSelectionStrategy;
import org.hibernate.shards.strategy.selection.ShardSelectionStrategy;

@Deprecated
public enum ProjectXHibernateShardSessionFactoryUtils {
	
	INSTANCE;
	
	private final SessionFactory sessionFactory=buildHibernateSessionFactory();
	public SessionFactory getInstance(){
		return sessionFactory;
	}
	
	private final SessionFactory buildHibernateSessionFactory(){
		//Logger logger = LogManager.getLogger(ProjectXHibernateShardSessionFactoryUtils.class.getName());
		Configuration prototypeConfig = new Configuration().configure("/configuration/hibernate/xml/projectX.cfg.xml");
		//logger.info("inside buildHibernateSessionFactory");
		try{
			List<ShardConfiguration> shardConfigs = new ArrayList<ShardConfiguration>();
			shardConfigs.add(buildShardConfiguration("/configuration/hibernate/xml/projectX.cfg.xml"));
			shardConfigs.add(buildShardConfiguration("/configuration/hibernate/xml/projectX_1.cfg.xml"));
			ShardStrategyFactory shardStrategyFactory = buildShardStrategyFactory();
			ShardedConfiguration shardedConfig = new ShardedConfiguration(
		            prototypeConfig,
		            shardConfigs,
		            shardStrategyFactory);
			return shardedConfig.buildShardedSessionFactory();
		}catch(Throwable te){
			//logger.fatal("Session Factory hasn't been initialized" + te.getMessage());
			//logger.fatal("stackTrace::",te);
			//logger.fatal("end of buildHibernateSessionFactory");
			throw new ExceptionInInitializerError(te);
		}
	}
	
	
	private final ShardStrategyFactory buildShardStrategyFactory() {
		//Logger logger = LogManager.getLogger(ProjectXHibernateShardSessionFactoryUtils.class.getName());
		//logger.info("Inside buildShardStrategyFactory");
		    ShardStrategyFactory shardStrategyFactory = new ShardStrategyFactory() {
		         public ShardStrategy newShardStrategy(List<ShardId> shardIds) {
		               	RoundRobinShardLoadBalancer loadBalancer = new RoundRobinShardLoadBalancer(shardIds);
		               	ShardSelectionStrategy pss = new RoundRobinShardSelectionStrategy(loadBalancer);
		               	ShardResolutionStrategy prs = new AllShardsShardResolutionStrategy(shardIds);
		               	ShardAccessStrategy pas = new ParallelShardAccessStrategy(createThreadPool());
		               	return new ShardStrategyImpl(pss, prs, pas);
		            }
		    };
		   // logger.info("Leaving buildShardStrategyFactory");
	        return shardStrategyFactory;
	    }
	
	private final ThreadPoolExecutor createThreadPool(){
		//Logger logger = LogManager.getLogger(ProjectXHibernateShardSessionFactoryUtils.class.getName());
		//logger.info("Inside createThreadPool");
		ThreadFactory factory = new ThreadFactory() {
	        public Thread newThread(Runnable r) {
	            Thread t = Executors.defaultThreadFactory().newThread(r);
	            t.setDaemon(true);
	            return t;
	        }
	    };
	   //logger.info("Leaving createThreadPool"); 
	   return new ThreadPoolExecutor(10,50,60,TimeUnit.SECONDS,new SynchronousQueue<Runnable>(),factory);
	}
	
	private final ShardConfiguration buildShardConfiguration(String path){
		Configuration configuration = new Configuration();
		configuration.configure(path);
		return new ConfigurationToShardConfigurationAdapter(configuration);
	}
	

}
