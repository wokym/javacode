package randomFile;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * RandomAccessFile������������Щ�������ݼ�¼���ļ��ģ���Ϳ�����seek( )���������ʼ�¼�������ж�д�ˡ���Щ��¼�Ĵ�С������ͬ���������С��λ�ñ����ǿ�֪�ġ�
 * ���Ǹ�������ڲ����ļ���RandomAccessFile������InputStream��OutputStream��ϵ�ġ�ʵ���ϣ�����ʵ��DataInput��DataOutput�ӿ�֮��
 * (DataInputStream��DataOutputStreamҲʵ�����������ӿ�)��������������ϵ������ɣ�������ʹ��InputStream��OutputStream�����Ѿ����ڵ��κι��ܣ�
 * ����һ����ȫ�������࣬���з���(���������ֻ�������Լ�)���Ǵ��㿪ʼд�ġ����������ΪRandomAccessFile�����ļ�����ǰ���ƶ�������������Ϊ��������I/O����Щ�����ԵĲ�ͬ��
 * �ܶ���֮������һ��ֱ�Ӽ̳�Object�ģ��������ࡣ�����ϣ�RandomAccessFile�Ĺ�����ʽ�ǣ���DataInputStream��DataOutputStream���������
 * �ټ������Լ���һЩ������
 * ���綨λ�õ�getFilePointer( )�����ļ����ƶ��õ�seek( )���Լ��ж��ļ���С��length( )��skipBytes()���������ֽ�����
 * ���⣬���Ĺ��캯����Ҫһ����ʾ��ֻ����ʽ("r")�������Զ�д��ʽ("rw")���ļ��Ĳ��� (��C��fopen( )һģһ��)������֧��ֻд�ļ���
 * ֻ��RandomAccessFile����seek��Ѱ���������������Ҳֻ�������ļ���BufferedInputStream��һ��mark( )������������������趨���(�ѽ��������һ���ڲ�������)��
 * Ȼ���ٵ���reset( )�������λ�ã��������Ĺ���̫���ˣ�����Ҳ����ôʵ�á�RandomAccessFile�ľ���������ܣ�������ȫ����
 * �Ѿ���JDK 1.4��nio��"�ڴ�ӳ���ļ�(memory-mapped files)"��ȡ���ˣ���ÿ���һ���ǲ�����"�ڴ�ӳ���ļ�"������RandomAccessFile�ˡ�
 * */
public class TestRandomAccessFile {
	public static void main(String[] args) throws IOException {
		RandomAccessFile rf = new RandomAccessFile("rtestqq.dat", "rw");
		for (int i = 0; i < 10; i++) {
			//д���������double����
			rf.writeDouble(i * 1.414);
		}
		rf.close();
		rf = new RandomAccessFile("rtest.dat", "rw");
		//ֱ�ӽ��ļ�ָ���Ƶ���5��double���ݺ���
		rf.seek(5 * 8);
		//���ǵ�6��double����
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
	 * @param skip �������ٹ��ֽڽ��в�������
	 * @param str Ҫ������ַ���
	 * @param fileName �ļ�·��
	 */
	public static void beiju(long skip, String str, String fileName){
		try {
			RandomAccessFile raf = new RandomAccessFile(fileName,"rw");
			if(skip <  0 || skip > raf.length()){
				System.out.println("�����ֽ�����Ч");
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