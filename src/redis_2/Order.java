package redis_2;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class Order {
	static Jedis jedis=new Jedis();
	final static String ORDER_KEY="orderList";
	
	public static void main(String[] args) throws Exception {
		byte[] srcByte=jedis.lpop(ORDER_KEY.getBytes());
		Map map=(Map)ObjectUtils.byteToObject(srcByte);
		System.out.println(map.get("cid"));
	}
}
