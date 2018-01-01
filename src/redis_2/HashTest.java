package redis_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * ��ʾhash����
 * hmest  ����hash���͵�����
 * hgetall
 * hlen
 * hdel
 * @author Administrator
 *
 */

public class HashTest {
	public static void main(String[] args) {
		
	}
	// redis���Ӷ���
	static final Jedis jedis=new Jedis();
	
	@Test
	public void testWriterHash(){
		Map map=new HashMap();
		map.put("userName", "����");
		map.put("age","18");
		map.put("sex", "��");
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













