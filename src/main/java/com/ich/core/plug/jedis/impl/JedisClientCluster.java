package com.ich.core.plug.jedis.impl;

import com.ich.core.plug.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;
/**
 *  redis客户端集群版实现类
  * @ClassName: JedisClientCluster
  * @Description: TODO
  * @author Comsys-Administrator
  * @date 2016年6月16日 上午11:53:05
  *
 */
public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisCluster jedisCluster;
	/**
	 * 
	 */
	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}
	/**
	 * 
	 */
	@Override
	public String set(int index,String key, String value) {
		jedisCluster.select(index);
		return jedisCluster.set(key, value);
	}
	/**
	 * 
	 */
	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.get(key);
	}
	/**
	 * 
	 */
	@Override
	public String get(int index,String key) {
		jedisCluster.select(index);
		// TODO Auto-generated method stub
		return jedisCluster.get(key);
	}
	/**
	 * 
	 */
	@Override
	public Long hset(int index,String key, String item, String value) {
		// TODO Auto-generated method stub
		jedisCluster.select(index);
		return jedisCluster.hset(key, item, value);
	}
	/**
	 * 
	 */
	@Override
	public Long hset(String key, String item, String value) {
		// TODO Auto-generated method stub
		return jedisCluster.hset(key, item, value);
	}
	
	/**
	 * 
	 */
	@Override
	public String hget(int index,String key, String item) {
		// TODO Auto-generated method stub
		jedisCluster.select(index);
		return jedisCluster.hget(key, item);
	}
	/**
	 * 
	 */
	@Override
	public String hget(String key, String item) {
		// TODO Auto-generated method stub
		return jedisCluster.hget(key, item);
	}
	/**
	 * 
	 */
	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.incr(key);
	}
	/**
	 * 
	 */
	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.decr(key);
	}
	/**
	 * 设置过期时间
	 */
	@Override
	public Long expire(String key, int sencond) {
		// TODO Auto-generated method stub
		return jedisCluster.expire(key, sencond);
	}
	/**
	 * 设置过期时间
	 */
	@Override
	public Long expire(int index,String key, int sencond) {
		// TODO Auto-generated method stub
		jedisCluster.select(index);
		return jedisCluster.expire(key, sencond);
	}
	/**
	 *返回过期时间
	 */
	@Override
	public Long ttl(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.ttl(key);
	}
	@Override
	public Long ttl(int index ,String key) {
		// TODO Auto-generated method stub
		jedisCluster.select(index);
		return jedisCluster.ttl(key);
	}
	@Override
	public Long hdel(String key, String item) {
		// TODO Auto-generated method stub
		return jedisCluster.hdel(key, item);
	}
	@Override
	public Long hdel(int index,String key, String item) {
		// TODO Auto-generated method stub
		jedisCluster.select(index);
		return jedisCluster.hdel(key, item);
	}
	@Override
	public Long del(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.del(key);
	}
	@Override
	public Long del(int index ,String key) {
		// TODO Auto-generated method stub
		jedisCluster.select(index);
		return jedisCluster.del(key);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void flushAll() {
		// TODO Auto-generated method stub
		jedisCluster.flushAll();
		 
	}
	@SuppressWarnings("deprecation")
	@Override
	public void flushAll(int index) {
		// TODO Auto-generated method stub
		jedisCluster.select(index);
		jedisCluster.flushAll();
		 
	}
	@Override
	public void flushDB(String key) {
		// TODO Auto-generated method stub
		 jedisCluster.flushDB();
	}
	@Override
	public void flushDB(int index,String key) {
		// TODO Auto-generated method stub
		jedisCluster.select(index);
		 jedisCluster.flushDB();
	}
}
