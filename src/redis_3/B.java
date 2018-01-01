package redis_3;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class B {
	public static final String CHANNEL_A="channel_a";
	static final Jedis jedis=new Jedis();
	
	public static void main(String[] args) {
		/*jedis.subscribe(new JedisPubSub(){
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("接收的消息："+message);
				super.onMessage(channel, message);
			}
		}, CHANNEL_A);*/
		new seedT("b").start();
		new JeT("a").start();
	}
}
