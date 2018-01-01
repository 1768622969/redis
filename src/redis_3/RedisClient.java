package redis_3;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisClient {
	public static Jedis jedis;
	
	public static void main(String[] args) {
		String host="localhost";
		String port="6379";
		for(int i=0;i<args.length;i++){
			if(args[i].equals("-h")){
				host=args[i+1];
			}
			if(args[i].equals("-p")){
				port=args[i+1];
			}
		}
		jedis=new Jedis(host,Integer.parseInt(port));
		Scanner sc=new Scanner(System.in);
		while(true){
			System.out.print(host+":"+port+">");
			String command=sc.nextLine();
			// set key value
			command=command.replace(" +", " ").trim();
		
			if(command.startsWith("set ")){
				String[] str=command.split(" ");
				String key=str[1];
				String value=str[2];
				jedis.set(key, value);
				System.out.println("OK");
			}
			
			// mset ͬʱ���ö��key value
			if(command.startsWith("mset ")){
				String[] str=command.split(" ");
				for(int i=1;i<str.length;i+=2){
					jedis.set(str[i], str[i+1]);
				}
				System.out.println("OK");
			}
				
			// get key
			if(command.startsWith("get ")){
				String[] str=command.split(" ");
				String key=str[1];
				System.out.println("\""+jedis.get(key)+"\"");
			}
			
			// mget ͬʱ��ȡ���key��ֵ 
			if(command.startsWith("mget ")){
				String[] str=command.split(" ");
				for(int i=1;i<str.length;i++){		
					jedis.mget(str[i]);
				}
				System.out.println(jedis.mget(str));
			}
			
			// ping  ��������Ƿ��� 
			if(command.equals("ping")){
				System.out.println("PONG");	
			//	System.out.println(jedis.ping());	
			}
			
			// echo   �������д�ӡ����
			if(command.startsWith("echo ")){
				String[] str=command.split(" ");
				String key=str[1];
				System.out.println("\""+key+"\"");	
			}
			
			// quit �˳��ͻ���
			if(command.startsWith("quit")){
				System.out.println("bye~");
				System.exit(0);
			}
			
			// select ѡ�����ݿ�
			if(command.startsWith("select ")){
				String[] str=command.split(" ");
				if(Integer.parseInt(str[1])<16){
					System.out.println("OK");
				}else{
					System.out.println("���ݿⲻ����");
				}
			}
			
			// dbsize ���ص�ǰ���ݿ���key����Ŀ
			if(command.startsWith("dbsize")){
				System.out.println("<integer>"+jedis.dbSize());
			}
			
			// flushdb ɾ����ǰ���ݿ��е�����key
			if(command.startsWith("flushdb")){
				jedis.flushDB();
				System.out.println("OK");
			}
			
			// flushall ɾ����ǰ���ݿ��е�����key
			if(command.startsWith("flushall")){
				jedis.flushAll();
				System.out.println("OK");
			}
			
			// append ׷���ַ���
			if(command.startsWith("append ")){
				String[] str=command.split(" ");
				String key=str[1];
				String value=str[2];
				jedis.append(key, value);
				String getV=jedis.get(key);
				System.out.println("<integer>"+getV.length());
			}
			
			// incr ������+1��
			if(command.startsWith("incr ")){
					String[] str=command.split(" ");
					String key=str[1];
					System.out.println("\""+jedis.incr(key)+"\"");
			}
			
			// decr �Լ���-1��
			if(command.startsWith("decr ")){
					String[] str=command.split(" ");
					String key=str[1];
					System.out.println("\""+jedis.decr(key)+"\"");
			}
				
			// strlen ����key��ֵ�ĳ���
			if(command.startsWith("strlen ")){
				String[] str=command.split(" ");
				String key=str[1];
				System.out.println("<integer>"+jedis.strlen(key));
			}
			
			// setrange �ַ����滻������start��
			if(command.startsWith("setrange ")){
				String[] str=command.split(" ");
				String key=str[1];
				String start=str[2];
				String value=str[3];
				jedis.setrange(key, Integer.parseInt(start), value);
				System.out.println("<integer>"+jedis.strlen(key));
			}
			
			// getrange ��ȡָ��λ���ַ���
			if(command.startsWith("getrange ")){
				String[] str=command.split(" ");
				String key=str[1];
				String start=str[2];
				String end=str[3];
				System.out.println("\""+jedis.getrange(key, Integer.parseInt(start), Integer.parseInt(end))+"\"");
			}
			
			
			// hset ��key�е�filed�ֶθ�ֵ
			if(command.startsWith("hset ")){
				String[] str=command.split(" ");
				String key=str[1];
				String field=str[2];
				String value=str[3];
				jedis.hset(key, field, value);
				System.out.println();
			}
			
			// hget ��ȡkey��field�ֶε�ֵ
			if(command.startsWith("hget ")){
				String[] str=command.split(" ");
				String key=str[1];
				String field=str[2];
				System.out.println(jedis.hget(key, field));
			}
			
			// hexists �ж�key���Ƿ����field
			if(command.startsWith("hexists ")){
				String[] str=command.split(" ");
				String key=str[1];
				String field=str[2];
				jedis.hexists(key, field);
				Set<String> strKey=jedis.hkeys(key);
			//	System.out.println(strKey);
				String result="0";
				for(String s:strKey){	
					if(s.equals(field)){
						result="1";
					}
				}
				System.out.println("<integer>"+result);
			}
			
			// hlen ��ȡkey��field������
			if(command.startsWith("hlen ")){
				String[] str=command.split(" ");
				String key=str[1];
				System.out.println("<integer>"+jedis.hlen(key));
			}
			
			// hdel ɾ��key�е�field�ֶ�
			if(command.startsWith("hdel ")){
				String[] str=command.split(" ");
				String key=str[1];
				String field=str[2];
				jedis.hdel(key, field);
				System.out.println("<integer>"+1);
			}
			
			//lpush ͷ�����ֵ
			if(command.startsWith("lpush ")) {
				String[] c=command.split(" ");
				String key=c[1];
				String value=c[2];
				jedis.lpush(key, value);
			}
			//rpush β�����ֵ
			if(command.startsWith("rpush ")){
				String[] c=command.split(" ");
				String key=c[1];
				String value=c[2];
				jedis.rpush(key, value);
			}
			//lpop ͷ������
			if(command.startsWith("lpop ")) {
				String[] c=command.split(" ");
				String key=c[1];
				jedis.lpop(key);
			}
			//rpop β������
			if(command.startsWith("rpop ")) {
				String[] c=command.split(" ");
				String key=c[1];
				jedis.lpop(key);
			}
			//sadd ���ֵ
			if(command.startsWith("sadd ")) {
				String[] c=command.split(" ");
				String key=c[1];
				String member=c[2];
				jedis.sadd(key,member);
			}
			//smembers ��������
			if(command.startsWith("smembers ")) {
				String[] c=command.split(" ");
				String key=c[1];
				System.out.println(jedis.smembers(key));
			}
			//scard ��ȡkey��Ԫ������
			if(command.startsWith("scard ")) {
				String[] c=command.split(" ");
				String key=c[1];
				System.out.println(jedis.scard(key));
			}
			//srem ɾ��ָ��Ԫ��
			if(command.startsWith("srem ")) {
				String[] c=command.split(" ");
				String key=c[1];
				String member=c[2];
				jedis.srem(key, member);
			}
			//sismember �ж�Ԫ���Ƿ����
			if(command.startsWith("sismember ")) {
				String[] c=command.split(" ");
				String key=c[1];
				String member=c[2];
				System.out.println(jedis.sismember(key,member));
			}
			//spop �������
			if(command.startsWith("spop ")) {
				String[] c=command.split(" ");
				String key=c[1];
				System.out.println(jedis.spop(key));
			}			
		}
	}
}













