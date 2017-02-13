package cache;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemCachedTest {
	static MemCachedClient client=new MemCachedClient("cctlesson_cache");
	static{
		SockIOPool sockIOPool = SockIOPool.getInstance("cctlesson_cache");
		sockIOPool.setWeights(new Integer[]{1});
		sockIOPool.setServers(new String[]{ "localhost:11211"});
		sockIOPool.setInitConn(10);
		sockIOPool.setMinConn(10);
		sockIOPool.setMaxConn(20);
		sockIOPool.setMaintSleep(3000);
		sockIOPool.setNagle(false);
		sockIOPool.setSocketTO(3000);
		sockIOPool.setSocketConnectTO(0);
		sockIOPool.setAliveCheck(false);
		
		sockIOPool.setHashingAlg(1);//一致性算法alg=3 使用MD5 hash算法
		
		sockIOPool.initialize();
	}
	
	
	public static void main(String[] args) {
//		boolean set = client.add("user", "hahah");
//		client.delete("user");
//		boolean set = client.add("user",new A(),new Date(1*60*1000));
//		System.out.println(set);
//		MemcachedItem gets = client.gets("user");
//		long casUnique = gets.getCasUnique();
//		System.out.println(casUnique+"=FFF="+client.get("user"));
//		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//		System.out.println(sf.format(new Date(3*60*1000)));
	
	}
}
