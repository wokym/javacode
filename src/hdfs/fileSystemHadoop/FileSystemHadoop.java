package hdfs.fileSystemHadoop;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class FileSystemHadoop {
	 static {
	 URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	 }
	public static void main(String[] args) {
		InputStream in = null;
		try {
			in = new URL("hdfs://master:8020/user/root/in/test1.txt").openStream();
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

}
