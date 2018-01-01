package redis_3;

import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class seedT extends Thread {
	public String channel;
	Jedis jedis=new Jedis();
	public seedT(String channel){
		this.channel=channel;
	}
	public void run(){
		Scanner sc=new Scanner(System.in);
		while(true){
			System.out.println("请输入发送的消息：");
			String text=sc.nextLine();
			jedis.publish(channel, text);
		}
	}
}
