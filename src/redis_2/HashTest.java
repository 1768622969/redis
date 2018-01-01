package redis_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 演示hash类型
 * hmest  设置hash类型的属性
 * hgetall
 * hlen
 * hdel
 * @author Administrator
 *
 */

public class HashTest {
	public static void main(String[] args) {
		
	}
	// redis连接对象
	static final Jedis jedis=new Jedis();
	
	@Test
	public void testWriterHash(){
		Map map=new HashMap();
		map.put("userName", "后羿");
		map.put("age","18");
		map.put("sex", "男");
		jedis.hmset("user:1",map);
		jedis.close();
	}
	
	@Test
	public void testReaderTest(){
		Map map=jedis.hgetAll("user:1");
		long len= jedis.hlen("user:1");
		Set<String> keyAll=jedis.hkeys("user:1");
		System.out.println(keyAll);
		jedis.close();
	}
}













