package redis_2;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class SetTest {
	public static void main(String[] args) {
		
	}
	
	// redis连接对象
	static final Jedis jedis=new Jedis();
	
	@Test
	public void testWriterSet(){
		jedis.sadd("1611","zs");
		jedis.sadd("1611","ls");
		jedis.sadd("1611","zz");
		jedis.srem("1611","zs");
	}
	
	@Test
	public void testRearderSet(){
		Boolean b=jedis.sismember("1611","ss");
		Set<String> s=jedis.smembers("1611");
		String str=jedis.srandmember("1611");
		System.out.println(s);
	}
}
