package randomFile;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * RandomAccessFile是用来访问那些保存数据记录的文件的，你就可以用seek( )方法来访问记录，并进行读写了。这些记录的大小不必相同；但是其大小和位置必须是可知的。
 * 但是该类仅限于操作文件。RandomAccessFile不属于InputStream和OutputStream类系的。实际上，除了实现DataInput和DataOutput接口之外
 * (DataInputStream和DataOutputStream也实现了这两个接口)，它和这两个类系毫不相干，甚至不使用InputStream和OutputStream类中已经存在的任何功能；
 * 它是一个完全独立的类，所有方法(绝大多数都只属于它自己)都是从零开始写的。这可能是因为RandomAccessFile能在文件里面前后移动，所以它的行为与其它的I/O类有些根本性的不同。
 * 总而言之，它是一个直接继承Object的，独立的类。基本上，RandomAccessFile的工作方式是，把DataInputStream和DataOutputStream结合起来，
 * 再加上它自己的一些方法，
 * 比如定位用的getFilePointer( )，在文件里移动用的seek( )，以及判断文件大小的length( )、skipBytes()跳过多少字节数。
 * 此外，它的构造函数还要一个表示以只读方式("r")，还是以读写方式("rw")打开文件的参数 (和C的fopen( )一模一样)。它不支持只写文件。
 * 只有RandomAccessFile才有seek搜寻方法，而这个方法也只适用于文件。BufferedInputStream有一个mark( )方法，你可以用它来设定标记(把结果保存在一个内部变量里)，
 * 然后再调用reset( )返回这个位置，但是它的功能太弱了，而且也不怎么实用。RandomAccessFile的绝大多数功能，但不是全部，
 * 已经被JDK 1.4的nio的"内存映射文件(memory-mapped files)"给取代了，你该考虑一下是不是用"内存映射文件"来代替RandomAccessFile了。
 * */
public class TestRandomAccessFile {
	public static void main(String[] args) throws IOException {
		RandomAccessFile rf = new RandomAccessFile("rtestqq.dat", "rw");
		for (int i = 0; i < 10; i++) {
			//写入基本类型double数据
			rf.writeDouble(i * 1.414);
		}
		rf.close();
		rf = new RandomAccessFile("rtest.dat", "rw");
		//直接将文件指针移到第5个double数据后面
		rf.seek(5 * 8);
		//覆盖第6个double数据
		rf.writeDouble(47.0001);
		rf.close();
		rf = new RandomAccessFile("rtest.dat", "r");
		for (int i = 0; i < 10; i++) {
			System.out.println("Value " + i + ": " + rf.readDouble());
		}
		rf.close();
	}
	
	/**
	 * 
	 * @param skip 跳过多少过字节进行插入数据
	 * @param str 要插入的字符串
	 * @param fileName 文件路径
	 */
	public static void beiju(long skip, String str, String fileName){
		try {
			RandomAccessFile raf = new RandomAccessFile(fileName,"rw");
			if(skip <  0 || skip > raf.length()){
				System.out.println("跳过字节数无效");
				return;
			}
			byte[] b = str.getBytes();
			raf.setLength(raf.length() + b.length);
			for(long i = raf.length() - 1; i > b.length + skip - 1; i--){
				raf.seek(i - b.length);
				byte temp = raf.readByte();
				raf.seek(i);
				raf.writeByte(temp);
			}
			raf.seek(skip);
			raf.write(b);
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} 