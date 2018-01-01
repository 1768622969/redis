package redis_2;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class ListTest {
	public static void main(String[] args) {
		
	}
	
	// redis连接对象
	static final Jedis jedis=new Jedis();
	
	@Test
	public void testWriterList(){
//		jedis.lpush("gid", "11");
//		jedis.lpush("gid", "22");
//		jedis.rpush("gid", "38");
		jedis.lpop("gid");
		jedis.rpop("gid");
	}
	
	@Test
	public void testReaderList(){
		List list=jedis.lrange("gid", 0, 1);
		long len=jedis.llen("gid");
		System.out.println(len);
		jedis.close();
	}
}
