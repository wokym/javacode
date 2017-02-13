package cache;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;


public class VehicleRedisCache {
	private static final Logger logger = Logger.getLogger(VehicleRedisCache.class);
//	private static final RedisTemplate<String, Object> redisTemplateCache=VehicleUtils.findSpringBeanByName("redisTemplateCache", RedisTemplate.class);
	
	
	private static JedisPool pool;
	private static int DBIndex=5;
	private static String host="123.57.134.69";
	private static int port = 6379;
	private static int timeout = 60 * 1000;
	private static String password="nlpturing2016";
	private static final int CACHE_TIME_OUT_SECOND=60*60;
	private static String FINAL_CACHE_KEY="c94c510a6dc14e5ab8b7a1e4a773bf74";
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(1000);
		config.setMaxIdle(20);
		config.setMaxWaitMillis(5000);
		config.setTestOnBorrow(false);
		pool = new JedisPool(config, host, port, timeout,password);// 线程数量限制，IP地址，端口，超时时间,密码
	}
	
	public static void main(String[] args) throws Exception {
		testListPush();
//		testList();
//		testMap();
//		getCache();
//		fan();
//		add();
//		 get();
		
		try (Jedis jedis =pool.getResource()){
    		jedis.select(DBIndex);
    		jedis.flushDB();
    	}
	}

	//得到相关数据库的，jedis
	public static Jedis getJedisBySelectDB(){
		
//		ValueOperations<String, Object> opsForValue = redisTemplateCache.opsForValue();
//		opsForValue.s
//		redisTemplateCache.boundListOps(key)
		Jedis jedis =pool.getResource();
		jedis.select(DBIndex);
		return jedis;
	}
	//添加List，指定时间
	public static void saveListStr(String key,int seconds,String ...strings ){
		try(Jedis jedis =getJedisBySelectDB()){
			logger.info("Redis正在存值,数据库："+DBIndex+";key="+key+";value="+Arrays.toString(strings));
			jedis.lpush(key, strings);
			if(seconds<0)
				jedis.persist(key);
			else
				jedis.expire(key, seconds);
			logger.info("Redis存值成功,数据库："+DBIndex+";key="+key+";value="+Arrays.toString(strings));
		}
	}
	
	public static void saveCacheContextInfo(String key,String ...strings ){
		if(StringUtils.isBlank(key)) return;
		try(Jedis jedis =getJedisBySelectDB()){
			key=FINAL_CACHE_KEY+key;
			logger.info("Redis正在存值,数据库："+DBIndex+";key="+key+";value="+Arrays.toString(strings));
			jedis.lpush(key, strings);
			jedis.expire(key, CACHE_TIME_OUT_SECOND);
			jedis.ltrim(key, 0, 10);
			logger.info("Redis存值成功,数据库："+DBIndex+";key="+key+";value="+Arrays.toString(strings));
		}
	}
	
	//取key的List集合，int个数
	public static List<String> getCacheList(String key,int length){
		if(length<0||StringUtils.isBlank(key)) return null;
		try(Jedis jedis =getJedisBySelectDB()){
			key=FINAL_CACHE_KEY+key;
			return jedis.lrange(key, 0, length-1);
		}
	}
	
	
	
	
	
	
    /**
     * redis操作Map
     */
    public static void testMap() {
    	try (Jedis jedis =pool.getResource()){
    		jedis.select(DBIndex);
    		//-----添加数据----------  
    		Map<String, String> map = new HashMap<String, String>();
    		map.put("name", "xinxin");
    		map.put("age", "22");
    		map.put("qq", "123456");
    		jedis.hmset("user",map);
    		//取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List  
    		//第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数  
    		List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
    		System.out.println(rsmap);  
    		
    		//删除map中的某个键值  
    		jedis.hdel("user","age");
    		System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null  
    		System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2 
    		System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true  
    		System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  
    		System.out.println(jedis.hvals("user"));//返回map对象中的所有value 
    		
    		Iterator<String> iter=jedis.hkeys("user").iterator();  
    		while (iter.hasNext()){  
    			String key = iter.next();  
    			System.out.println(key+":"+jedis.hmget("user",key));  
    		}  
    	}
    }
    
    /** 
     * jedis操作List 
     */  
    public static void testList(){  
    	try (Jedis jedis =pool.getResource()){
    		jedis.select(DBIndex);
    		//开始前，先移除所有的内容  
    		jedis.del("java framework");  
    		System.out.println(jedis.lrange("java framework",0,-1));  
    		//先向key java framework中存放三条数据  (先进后出)
    		jedis.lpush("java framework","spring");  
    		jedis.lpush("java framework","struts");  
    		jedis.lpush("java framework","hibernate");  
    		//再取出所有数据jedis.lrange是按范围取出，  
    		// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
    		System.out.println(jedis.lrange("java framework",0,-1));  
    		
    		jedis.del("java framework");
    		jedis.rpush("java framework","spring");  //先进先出
    		jedis.rpush("java framework","struts");  
    		jedis.rpush("java framework","hibernate"); 
    		System.out.println(jedis.lrange("java framework",0,-1));
    	}
    }  
    
    /** 
     * jedis操作Set 
     */  
    public static void testSet(){ 
    	try (Jedis jedis =pool.getResource()){
    		jedis.select(DBIndex);
    		//添加  
    		jedis.sadd("user","liuling");  
    		jedis.sadd("user","xinxin");  
    		jedis.sadd("user","ling");  
    		jedis.sadd("user","zhangxinxin");
    		jedis.sadd("user","who");  
    		//移除noname  
    		jedis.srem("user","who");  
    		System.out.println(jedis.smembers("user"));//获取所有加入的value  
    		System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素  
    		System.out.println(jedis.srandmember("user"));  
    		System.out.println(jedis.scard("user"));//返回集合的元素个数  
    	}
    }  
  
    public static void testListPush() throws InterruptedException { 
    	try (Jedis jedis =pool.getResource()){
    		jedis.select(DBIndex);
    		jedis.flushDB();
    		//jedis 排序  
    		//注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）  
    		jedis.del("a");//先清除数据，再加入数据进行测试  
//    		jedis.rpush("a", "1");  
    		jedis.lpush("a","6");  
    		jedis.lpush("a","3");  
    		jedis.lpush("a","9");  
    		System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]  
    		System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果  
    		System.out.println(jedis.lrange("a",0,-1));  
    		jedis.del("a");//先清除数据，再加入数据进行测试
    	}
    }  
    
    public static void getCache(){
    	try (Jedis jedis =pool.getResource()){
    		jedis.select(DBIndex);
			// 开始前，先移除所有的内容  
			jedis.del("messages");  
			jedis.rpush("messages", "Hello how are you?");  
			jedis.rpush("messages", "Fine thanks. I'm having fun with redis.");  
			jedis.rpush("messages", "I should look into this NOSQL thing ASAP");  
			
			// 再取出所有数据jedis.lrange是按范围取出，  
			// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
			List<String> values = jedis.lrange("messages", 0, -1);  
			System.out.println(values);  
    		// 清空数据  
    		System.out.println(jedis.flushDB());  
    		// 添加数据  
    		jedis.lpush("lists", "vector");  
    		jedis.lpush("lists", "ArrayList");  
    		jedis.lpush("lists", "LinkedList");  
    		// 数组长度  
    		System.out.println(jedis.llen("lists"));  
    		// 排序  
    		System.out.println(jedis.sort("lists"));  
    		// 字串  
    		System.out.println(jedis.lrange("lists", 0, 3));  
    		// 修改列表中单个值  
    		jedis.lset("lists", 0, "hello list!");  
    		// 获取列表指定下标的值  
    		System.out.println(jedis.lindex("lists", 1));  
    		// 删除列表指定下标的值  
    		System.out.println(jedis.lrem("lists", 1, "vector"));  
    		// 删除区间以外的数据  
    		System.out.println(jedis.ltrim("lists", 0, 1));  
    		// 列表出栈  
    		System.out.println(jedis.lpop("lists"));  
    		// 整个列表值  
    		System.out.println(jedis.lrange("lists", 0, -1));  
    	}
    }
    
    public static void fan(){
    	try (Jedis shardedJedis =pool.getResource()){
    		shardedJedis.select(DBIndex);
    		System.out.println("======================list=========================="); 
    		// 清空数据 
//    		System.out.println("清空库中所有数据："+shardedJedis.flushDB()); 
    		shardedJedis.flushDB();
    		System.out.println("=============增=============");
    		shardedJedis.lpush("stringlists", "vector"); 
    		shardedJedis.lpush("stringlists", "ArrayList"); 
    		shardedJedis.lpush("stringlists", "vector");
    		shardedJedis.lpush("stringlists", "vector");
    		shardedJedis.lpush("stringlists", "LinkedList");
    		shardedJedis.lpush("stringlists", "MapList");
    		shardedJedis.lpush("stringlists", "SerialList");
    		shardedJedis.lpush("stringlists", "HashList");
    		shardedJedis.lpush("numberlists", "3");
    		shardedJedis.lpush("numberlists", "1");
    		shardedJedis.lpush("numberlists", "5");
    		shardedJedis.lpush("numberlists", "2");
    		System.out.println("所有元素-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
    		System.out.println("所有元素-numberlists："+shardedJedis.lrange("numberlists", 0, -1));
    		
    		System.out.println("=============删=============");
    		// 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
    		System.out.println("成功删除指定元素个数-stringlists："+shardedJedis.lrem("stringlists", 2, "vector")); 
    		System.out.println("删除指定元素之后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
    		// 删除区间以外的数据 
    		System.out.println("删除下标0-3区间之外的元素："+shardedJedis.ltrim("stringlists", 0, 10));
    		
    		System.out.println("删除指定区间之外元素后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
    		// 列表元素出栈 
    		System.out.println("出栈元素："+shardedJedis.lpop("stringlists")); 
    		System.out.println("元素出栈后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
    		
    		System.out.println("=============改=============");
    		// 修改列表中指定下标的值 
    		shardedJedis.lset("stringlists", 0, "hello list!"); 
    		System.out.println("下标为0的值修改后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
    		System.out.println("=============查=============");
    		// 数组长度 
    		System.out.println("长度-stringlists："+shardedJedis.llen("stringlists"));
    		System.out.println("长度-numberlists："+shardedJedis.llen("numberlists"));
    		// 排序 
    		/*
    		 * list中存字符串时必须指定参数为alpha，如果不使用SortingParams，而是直接使用sort("list")，
    		 * 会出现"ERR One or more scores can't be converted into double"
    		 */
    		SortingParams sortingParameters = new SortingParams();
    		sortingParameters.alpha();
    		sortingParameters.limit(0, 3);
    		System.out.println("返回排序后的结果-stringlists："+shardedJedis.sort("stringlists",sortingParameters)); 
    		System.out.println("返回排序后的结果-numberlists："+shardedJedis.sort("numberlists"));
    		// 子串：  start为元素下标，end也为元素下标；-1代表倒数一个元素，-2代表倒数第二个元素
    		System.out.println("子串-第二个开始到结束："+shardedJedis.lrange("stringlists", 1, -1));
    		// 获取列表指定下标的值 
    		System.out.println("获取下标为2的元素："+shardedJedis.lindex("stringlists", 2)+"\n");
    	}
    }
	

	public static void testString(){
		try (Jedis jedis =pool.getResource()){
			jedis.select(DBIndex);
			jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin  
			System.out.println(jedis.get("name"));//执行结果：xinxin  
			jedis.append("name", " is my lover"); //拼接
			System.out.println(jedis.get("name")); 
			jedis.del("name");  //删除某个键
			System.out.println(jedis.get("name"));
			//设置多个键值对
			jedis.mset("name","liuling","age","23","qq","476777XXX");
			jedis.incr("age"); //进行加1操作
			System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
		}
	}
	
	public static void addItemToList(String key, byte[] value) {
		try (Jedis jedis =pool.getResource()){
				jedis.connect();
				jedis.select(DBIndex);
				jedis.lpush(key.getBytes(), value);
		 }
	}
	@SuppressWarnings("finally")
	public static List<String> getItemFromList(String key) {
			try (Jedis jedis =pool.getResource()){
				jedis.select(DBIndex);
				long len = jedis.llen(key);
				if (len == 0)
					return null;
				return jedis.lrange(key, 0, (int) len);
		 }
	}

	public static void addItem(String key, byte[] value) {
		try (Jedis jedis =pool.getResource()){
			jedis.select(DBIndex);
			jedis.set(key.getBytes(), value);
		}
	}

	public static byte[] getItem(String key) {
		try (Jedis jedis =pool.getResource()){
			jedis.select(DBIndex);
			return jedis.get(key.getBytes());
		}
	}

	public static void delItem(String key) {
		try (Jedis jedis =pool.getResource()){
			jedis.select(DBIndex);
			jedis.del(key.getBytes());
		}
	}

	public static long getIncrement(String key) {
		try (Jedis jedis =pool.getResource()){
			jedis.select(DBIndex);
			return jedis.incr(key);
		}
	}

	/**
	 * 设置map 可以存储用户信息
	 * 
	 * @param key
	 * @param map
	 */
	public static void setHashMap(String key, HashMap<String, String> map) {
		try (Jedis jedis =pool.getResource()){
			jedis.select(DBIndex);
			if (map != null && !map.isEmpty()) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					jedis.hset(key, entry.getKey(), entry.getValue());
				}
			}
		}
	}

	public static Map<String, String> getHashMap(String key) {
		
		try (Jedis jedis =pool.getResource()){
			jedis.select(DBIndex);
			return jedis.hgetAll(key);
		}
	}

	/**
	 * 添加set
	 * 
	 * @param key
	 * @param set
	 */
	public static void addSet(String key, Set<String> set) {
		try(Jedis jedis =pool.getResource()){
			jedis.select(DBIndex);
			if (set != null && !set.isEmpty()) {
				for (String value : set) {
					/*
					 * for ( Iterator<String> memberItr =
					 * jedis.smembers(str).iterator();//返回key对应set的所有元素，结果是无序的
					 * memberItr.hasNext();){ final String member =
					 * memberItr.next(); if (!jedis.sismember(str, member)){
					 * jedis.srem(str, member); } }
					 */
					jedis.sadd(key, value);
				}
			}
		}
			
	}

	public static Set<String> getSet(String key) {
		try(Jedis jedis =pool.getResource()){
			jedis.select(DBIndex);
			return jedis.smembers(key);
		}
	}
}