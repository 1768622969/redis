package redis_3;

import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class A {
	public static final String CHANNEL_A="channel_a";
	static final Jedis jedis=new Jedis();
	
	public static void main(String[] args) {
		/*Scanner sc=new Scanner(System.in);
		while(true){
			System.out.println("请输入发送的消息：");
			String text=sc.nextLine();
			jedis.publish(CHANNEL_A, text);
		}*/
		new seedT("a").start();
		new JeT("b").start();
	}
}
