package cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class SerializableTest {
	public static void main(String[] args) {
		try {
			ByteArrayOutputStream bas=new ByteArrayOutputStream();
			ObjectOutput ob=new ObjectOutputStream(bas);
			ob.writeObject(new A());
			byte[] byteArray = bas.toByteArray();
			String string = new String(byteArray,"iso-8859-1");
			System.out.println(string);
			ByteArrayInputStream bi=new ByteArrayInputStream(string.getBytes("iso-8859-1"));
			ObjectInput oi=new ObjectInputStream(bi);
			A readObject = (A)oi.readObject();
			System.out.println(readObject.name);
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class A implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name ="haha";
}
