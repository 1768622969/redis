package redis_3;

import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class UserB {
	// ����A��Ƶ��
	public final static String CHANNEL_A="channel_a";
	static final Jedis jedis=new Jedis();
	
	public static void main(String[] args) {
		new sendUserB().start();
		jedis.subscribe(new JedisPubSub(){
			public void onMessage(String channel,String message){
				System.out.println("��ȡ����Ϣ��"+message);
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
			System.out.println("�����뷢�͵���Ϣ��");
			String text=sc.nextLine();
			jedis.publish(CHANNEL_B, text);
		}
	}
}
