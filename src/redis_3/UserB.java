package redis_3;

import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class UserB {
	// 订阅A的频道
	public final static String CHANNEL_A="channel_a";
	static final Jedis jedis=new Jedis();
	
	public static void main(String[] args) {
		new sendUserB().start();
		jedis.subscribe(new JedisPubSub(){
			public void onMessage(String channel,String message){
				System.out.println("获取的消息："+message);
				super.onMessage(channel, message);
			}
		}, CHANNEL_A);
	}
}

class sendUserB extends Thread{
	public static final String CHANNEL_B="channel_b";
	static final Jedis jedis=new Jedis();
	Scanner sc=new Scanner(System.in);
	public void run(){	
		while(true){
			System.out.println("请输入发送的消息：");
			String text=sc.nextLine();
			jedis.publish(CHANNEL_B, text);
		}
	}
}
