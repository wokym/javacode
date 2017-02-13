package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesClose {
	public static void main(String[] args) {
		String property = getProperties().getProperty("datasource.switch");
		System.out.println(property);
	}
	public static Properties getProperties() {
		InputStream in = PropertiesClose.class.getResourceAsStream("dbconfig.properties");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			Properties p = new Properties();
			p.load(reader);
			return p;
		} catch (IOException e) {
			return null;
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
