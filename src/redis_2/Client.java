package redis_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class Client {
	/*
	 * �����û��Ķ���
	 */
	
	// ����һ��List�����������map����
	public static List<Map> list=new ArrayList<Map>();
	/*
	 * ģ���ж���û� ���͵Ķ������� ģ��2���Ա�
	 */
	static{
		// ����map����������ʾĳ��������Ϣ
		Map map=new HashMap();
		map.put("cid", 1);
		map.put("count", 2);
		map.put("price", 200);
		list.add(map);        //  �����涩����Ϣ��map���ϴ�ŵ�List������
		// ����map����������ʾĳ��������Ϣ
		Map map1=new HashMap();
		map.put("cid", 2);
		map.put("count", 1);
		map.put("price", 10);
		list.add(map1);
	}
	
	// redis���Ӷ���
	static Jedis jedis=new Jedis();
	final static String ORDER_KEY="orderList";
	
	// ��������Ϣ���ݱ������浽redis��
	public static void main(String[] args) throws IOException{
		for(int i=0;i<list.size();i++){
			final Map map=list.get(i);
			jedis.rpush(ORDER_KEY.getBytes(), ObjectUtils.objectToByte(map));
		}
		jedis.close();
	}
}
