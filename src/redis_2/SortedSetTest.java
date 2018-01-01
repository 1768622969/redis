package redis_2;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class SortedSetTest {
	public static void main(String[] args) {
		
	}
	
	//redis连接对象
	static final Jedis jedis=new Jedis();
	
	@Test
	public void testWriterSortedSet(){
		jedis.zadd("1611", 10, "zs");
	}
	
	@Test
	public void testReaderSortedSet(){
		Long i=jedis.zcard("1611");
		System.out.println(i);
	}
}
