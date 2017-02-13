package nioconcurrent;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
//ByteBuffer
//CharBuffer
//DoubleBuffer
//FloatBuffer
//IntBuffer
//LongBuffer
//ShortBuffer
//这些Buffer覆盖了你能通过IO发送的基本数据类型：byte, short, int, long, float, double 和 char。 
//Java NIO 还有个 Mappedyteuffer
public class FileBuffer {
	 private static final int start = 0; 
	    private static final int size = 1024; 

	    public static void main(String args[]) throws Exception { 
	        RandomAccessFile raf = new RandomAccessFile("C:\\kym\\testFile\\file.txt", "rw"); 
	        FileChannel fc = raf.getChannel(); 
	        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, size); 
	        mbb.put(0, (byte)97); 
	        mbb.put(1023, (byte)122); 
	        fc.close();
	        raf.close(); 
	    } 
}
