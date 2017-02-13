package hdfs.fileSystemHadoop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShowFileStatusTest {
	private FileSystem fs;
	@Before
	public void iniFile() throws IOException, URISyntaxException{
		// 1����һ����������һ��Ĭ�ϵ��ļ�ϵͳ����conf/core-site.xml��ͨ��fs.default.name��ָ���ģ������conf/core-site.xml��û�������򷵻ر����ļ�ϵͳ����
		// 2���ڶ�������ͨ��uri��ָ��Ҫ���ص��ļ�ϵͳ�����磬���uri���ϸ����������е�hdfs://172.20.59.227:8888/user/myuser/output10������hdfs��ʶ��ͷ��
		// ��ô�ͷ���һ��hdfs�ļ�ϵͳ�����uri��û����Ӧ�ı�ʶ�򷵻ر����ļ�ϵͳ����
		// 3����������������ļ�ϵͳ�Ļ���ͬ��2������ͬ�ģ�����ͬʱ���޶��˸��ļ�ϵͳ���û������ڰ�ȫ�����Ǻ���Ҫ�ġ�
		// ��ʱ���������Ҫʹ��һ�������ļ�ϵͳ�������ʹ����һ���ܷ���ķ�����
		fs=FileSystem.get(new URI("hdfs://master:8020"),new Configuration());
		
	}
	@After
	public void close() throws IOException{
		if(fs!=null) fs.close();
	}
	@Test
	public void getFile(){
		InputStream in = null;
		try {
//			in=fs.open(new Path("D:\\downt\\hadoopfile\\input.txt"));
			in=fs.open(new Path("hdfs://master:8020/user/root/in/test1.txt"));
//			in = new URL("hdfs://master:8020/user/root/in/test1.txt").openStream();
			System.out.println("************");
			IOUtils.copyBytes(in, System.out, 4096, false);
			System.out.println("************");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(in);
		}
	}
	
	@Test
	public void createFile() throws Exception {
		FSDataOutputStream create=null;
		InputStream in=null;
		try {
			create = fs.create(new Path("hdfs://master:8020/user/root/in/testWriterSql.txt"),
					new Progressable(){
				//Ĭ��ÿ�δ���65024byte
				@Override
				public void progress() {
					 System.out.println("*-");
				}
			});
			byte[] bytes = "hello world 2016".getBytes();
//			create.write(bytes);
			in=new FileInputStream("D:\\downt\\hadoopfile\\mydata.sql");
//			IOUtils.copyBytes(in,create, 4096, false);
			byte [] byts=new byte[1024];
			int i=-1;
			while((i=in.read(byts))!=-1){
				create.write(byts,0,i);
			}
			create.flush();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			in.close();
			create.close();
		}
//		
	}
	@Test
	public void fileStatusForFile() throws IOException {
		String uri = "hdfs://master:8020/user/root/in/";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		Path[] paths = new Path[1];
		paths[0] = new Path(uri);
		FileStatus[] status = fs.listStatus(paths);
		Path[] listedPaths = FileUtil.stat2Paths(status);
		System.out.println("********");
		for (Path p : listedPaths) {
		    System.out.println(p);
		}
		System.out.println("********");
	}
	
	
	@Test
	public void createFileDir() throws IOException {
		String uri = "/user/root/in/";
//		Configuration conf = new Configuration();
//		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FileStatus fileStatus = fs.getFileStatus(new Path("/user/root/in"));
//		fs.mkdirs(new Path("/haha/����"));
//		fs.delete(new Path("/haha/"), true);
		System.out.println("addr="+fileStatus.getPath()+";size=");
//		fs.rename(new Path("/user/root/ins"),new Path("/user/root/in"));������
		FileStatus[] status = fs.listStatus(new Path(uri));
//		Path[] listedPaths = FileUtil.stat2Paths(status);
		System.out.println("********");
		for (FileStatus p : status) {
		    System.out.println("addr="+p.getPath()+";size="+p.getLen());
		}
		System.out.println("********");
	}
	
	 
}
