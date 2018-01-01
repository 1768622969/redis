package redis_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class Client {
	/*
	 * 假设用户的订单
	 */
	
	// 声明一个List集合用来存放map集合
	public static List<Map> list=new ArrayList<Map>();
	/*
	 * 模拟有多个用户 发送的订单请求 模拟2个淘宝
	 */
	static{
		// 声明map集合用来表示某个订单信息
		Map map=new HashMap();
		map.put("cid", 1);
		map.put("count", 2);
		map.put("price", 200);
		list.add(map);        //  将保存订单信息的map集合存放到List集合中
		// 声明map集合用来表示某个订单信息
		Map map1=new HashMap();
		map.put("cid", 2);
		map.put("count", 1);
		map.put("price", 10);
		list.add(map1);
	}
	
	// redis连接对象
	static Jedis jedis=new Jedis();
	final static String ORDER_KEY="orderList";
	
	// 将订单信息数据遍历保存到redis中
	public static void main(String[] args) throws IOException{
		for(int i=0;i<list.size();i++){
			final Map map=list.get(i);
			jedis.rpush(ORDER_KEY.getBytes(), ObjectUtils.objectToByte(map));
		}
		jedis.close();
	}
}
