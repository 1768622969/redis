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
			
			// mset 同时设置多个key value
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
			
			// mget 同时获取多个key的值 
			if(command.startsWith("mget ")){
				String[] str=command.split(" ");
				for(int i=1;i<str.length;i++){		
					jedis.mget(str[i]);
				}
				System.out.println(jedis.mget(str));
			}
			
			// ping  检查链接是否存活 
			if(command.equals("ping")){
				System.out.println("PONG");	
			//	System.out.println(jedis.ping());	
			}
			
			// echo   在命令行打印内容
			if(command.startsWith("echo ")){
				String[] str=command.split(" ");
				String key=str[1];
				System.out.println("\""+key+"\"");	
			}
			
			// quit 退出客户端
			if(command.startsWith("quit")){
				System.out.println("bye~");
				System.exit(0);
			}
			
			// select 选择数据库
			if(command.startsWith("select ")){
				String[] str=command.split(" ");
				if(Integer.parseInt(str[1])<16){
					System.out.println("OK");
				}else{
					System.out.println("数据库不存在");
				}
			}
			
			// dbsize 返回当前数据库中key的数目
			if(command.startsWith("dbsize")){
				System.out.println("<integer>"+jedis.dbSize());
			}
			
			// flushdb 删除当前数据库中的所有key
			if(command.startsWith("flushdb")){
				jedis.flushDB();
				System.out.println("OK");
			}
			
			// flushall 删除当前数据库中的所有key
			if(command.startsWith("flushall")){
				jedis.flushAll();
				System.out.println("OK");
			}
			
			// append 追加字符串
			if(command.startsWith("append ")){
				String[] str=command.split(" ");
				String key=str[1];
				String value=str[2];
				jedis.append(key, value);
				String getV=jedis.get(key);
				System.out.println("<integer>"+getV.length());
			}
			
			// incr 自增（+1）
			if(command.startsWith("incr ")){
					String[] str=command.split(" ");
					String key=str[1];
					System.out.println("\""+jedis.incr(key)+"\"");
			}
			
			// decr 自减（-1）
			if(command.startsWith("decr ")){
					String[] str=command.split(" ");
					String key=str[1];
					System.out.println("\""+jedis.decr(key)+"\"");
			}
				
			// strlen 返回key的值的长度
			if(command.startsWith("strlen ")){
				String[] str=command.split(" ");
				String key=str[1];
				System.out.println("<integer>"+jedis.strlen(key));
			}
			
			// setrange 字符串替换（包含start）
			if(command.startsWith("setrange ")){
				String[] str=command.split(" ");
				String key=str[1];
				String start=str[2];
				String value=str[3];
				jedis.setrange(key, Integer.parseInt(start), value);
				System.out.println("<integer>"+jedis.strlen(key));
			}
			
			// getrange 获取指定位置字符串
			if(command.startsWith("getrange ")){
				String[] str=command.split(" ");
				String key=str[1];
				String start=str[2];
				String end=str[3];
				System.out.println("\""+jedis.getrange(key, Integer.parseInt(start), Integer.parseInt(end))+"\"");
			}
			
			
			// hset 给key中的filed字段赋值
			if(command.startsWith("hset ")){
				String[] str=command.split(" ");
				String key=str[1];
				String field=str[2];
				String value=str[3];
				jedis.hset(key, field, value);
				System.out.println();
			}
			
			// hget 获取key中field字段的值
			if(command.startsWith("hget ")){
				String[] str=command.split(" ");
				String key=str[1];
				String field=str[2];
				System.out.println(jedis.hget(key, field));
			}
			
			// hexists 判断key中是否存在field
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
			
			// hlen 获取key中field的数量
			if(command.startsWith("hlen ")){
				String[] str=command.split(" ");
				String key=str[1];
				System.out.println("<integer>"+jedis.hlen(key));
			}
			
			// hdel 删除key中的field字段
			if(command.startsWith("hdel ")){
				String[] str=command.split(" ");
				String key=str[1];
				String field=str[2];
				jedis.hdel(key, field);
				System.out.println("<integer>"+1);
			}
			
			//lpush 头部添加值
			if(command.startsWith("lpush ")) {
				String[] c=command.split(" ");
				String key=c[1];
				String value=c[2];
				jedis.lpush(key, value);
			}
			//rpush 尾部添加值
			if(command.startsWith("rpush ")){
				String[] c=command.split(" ");
				String key=c[1];
				String value=c[2];
				jedis.rpush(key, value);
			}
			//lpop 头部弹出
			if(command.startsWith("lpop ")) {
				String[] c=command.split(" ");
				String key=c[1];
				jedis.lpop(key);
			}
			//rpop 尾部弹出
			if(command.startsWith("rpop ")) {
				String[] c=command.split(" ");
				String key=c[1];
				jedis.lpop(key);
			}
			//sadd 添加值
			if(command.startsWith("sadd ")) {
				String[] c=command.split(" ");
				String key=c[1];
				String member=c[2];
				jedis.sadd(key,member);
			}
			//smembers 遍历集合
			if(command.startsWith("smembers ")) {
				String[] c=command.split(" ");
				String key=c[1];
				System.out.println(jedis.smembers(key));
			}
			//scard 获取key的元素数量
			if(command.startsWith("scard ")) {
				String[] c=command.split(" ");
				String key=c[1];
				System.out.println(jedis.scard(key));
			}
			//srem 删除指定元素
			if(command.startsWith("srem ")) {
				String[] c=command.split(" ");
				String key=c[1];
				String member=c[2];
				jedis.srem(key, member);
			}
			//sismember 判断元素是否存在
			if(command.startsWith("sismember ")) {
				String[] c=command.split(" ");
				String key=c[1];
				String member=c[2];
				System.out.println(jedis.sismember(key,member));
			}
			//spop 随机弹出
			if(command.startsWith("spop ")) {
				String[] c=command.split(" ");
				String key=c[1];
				System.out.println(jedis.spop(key));
			}			
		}
	}
}













