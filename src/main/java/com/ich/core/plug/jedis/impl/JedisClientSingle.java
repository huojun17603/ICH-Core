package com.ich.core.plug.jedis.impl;


import com.ich.core.plug.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * redis客户端单机版实现类
 * <p>Title: JedisClientSingle</p>
 * <p>Description: </p>
 * @version 1.0
 */
public class JedisClientSingle implements JedisClient {
	
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}
	@Override
	public String set(int index,String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}
	@Override
	public String get(int index,String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(int index,String key, String item, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		Long result = jedis.hset(key, item, value);
		jedis.close();
		return result;
	}
	@Override
	public Long hset(String key, String item, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, item, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(int index,String key, String item) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		String result = jedis.hget(key, item);
		jedis.close();
		return result;
	}
	@Override
	public String hget(String key, String item) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(key, item);
		jedis.close();
		return result;
	}
	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.decr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}
	@Override
	public Long expire(int index ,String key, int second) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}
	@Override
	public Long ttl(int index,String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(int index,String key, String item) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		Long result = jedis.hdel(key, item);
		jedis.close();
		return result;
	}
	@Override
	public Long hdel(String key, String item) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(key, item);
		jedis.close();
		return result;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}
	@Override
	public Long del(int index,String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public void flushAll() {
		Jedis jedis = jedisPool.getResource();
		jedis.flushAll();
		jedis.close();
	}
	@Override
	public void flushAll(int index) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		jedis.flushAll();
		jedis.close();
	}
	@Override
	public void flushDB(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.flushDB();
		jedis.close();
	}
	@Override
	public void flushDB(int index,String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(index);
		jedis.flushDB();
		jedis.close();
	}
}
