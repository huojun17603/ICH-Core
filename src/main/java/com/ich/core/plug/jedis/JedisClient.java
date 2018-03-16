package com.ich.core.plug.jedis;

public interface JedisClient {
	public String set(String key, String value);
	public String get(String key);
	
	public String set(int index, String key, String value);
	public String get(int index, String key);
	
	public Long hset(int index, String key, String item, String value);
	public Long hset(String key, String item, String value);
	
	public String hget(int index, String key, String item);
	public String hget(String key, String item);
	
	public Long incr(String key);
	public Long decr(String key);
	
	public Long expire(String key, int sencond);
	public Long expire(int index, String key, int sencond);
	
	public Long ttl(String key);
	public Long ttl(int index, String key);
	
	public Long hdel(String key, String item);
	public Long hdel(int index, String key, String item);
	
	public Long del(String key);
	public Long del(int index, String key);
	
	public void flushAll();
	public void flushAll(int index);
	
	public void flushDB(String db);
	public void flushDB(int index, String db);
}
