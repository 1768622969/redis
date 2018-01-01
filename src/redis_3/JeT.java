package redis_3;

import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class JeT extends Thread {
	public String channel;
	Jedis jedis=new Jedis();
	public JeT(String channel){
		this.channel=channel;
	}
	public void run(){
		jedis.subscribe(new JedisPubSub(){
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("接收的消息："+message);
				super.onMessage(channel, message);
			}
		}, channel);
		}
	}

