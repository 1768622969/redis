package cn.et;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;

public class Query {
	public static void main(String[] args) throws IOException {
		String name="zs";
		String str=query(name);
		System.out.println(str);
	}
	// redis连接对象
	static final Jedis jedis=new Jedis();

	private static String query(String name) throws IOException {
		// 判断redis是否存在该用户信息  存在直接返回
		if(jedis.keys(name).size()>0){
			return jedis.get(name);
		}
		
		FileInputStream fis=new FileInputStream("C:\\ProgramData\\MySQL\\MySQL Server 5.5\\data\\test\\userinfo.CSV");
		BufferedReader bf=new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		String line=null;
		String result=null;
		while((line=bf.readLine())!=null){
			String[] str=line.split(",");
			String value=str[0].replace("\"", "")+"-"+
			                    str[1].replace("\"", "")+
			                    str[2].replace("\"", "")+
			                    str[3].replace("\"", "")+
			                    str[4].replace("\"", "");
			String key=str[1].replace("\"","");
			if(name.equals(key)){
				jedis.set(key, value);
				result=value;
				break;
			}
		}
		bf.close();
		fis.close();
		jedis.close();
		return result;
	}
}
