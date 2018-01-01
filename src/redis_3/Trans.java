package redis_3;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * ÊÂÎñ²Ù×÷
 * @author Administrator
 *
 */
public class Trans {
	static Jedis redis=new Jedis();
	public static void main(String[] args) {
		Transaction trans=redis.multi();
		trans.set("1611", "zz");
		trans.zadd("et1611", 60, "xx");
		trans.zadd("et1611", 50, "yy");
		trans.exec();
	}
}
