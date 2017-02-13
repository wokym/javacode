package randomFile;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
/**
 *  �ڴ�ӳ���ļ������㴴�����޸���Щ��Ϊ̫����޷������ڴ���ļ��������ڴ�ӳ���ļ�����Ϳ�����Ϊ�ļ��Ѿ�ȫ���������ڴ棬Ȼ���������һ���ǳ�������������ʡ�
 *  ���ֽ���취�ܴ����޸��ļ��Ĵ��롣fileChannel.map(FileChannel.MapMode mode, long position, long size)����ͨ�����ļ�����ֱ��ӳ�䵽�ڴ��С�
 *  ע�⣬�����ָ�������Ǵ��ļ����ĸ�λ�ÿ�ʼӳ��ģ�ӳ��ķ�Χ���ж��Ҳ����˵����������ӳ��һ�����ļ���ĳ��СƬ�ϡ�MappedByteBuffer��ByteBuffer�����࣬
 *  ������߱���ByteBuffer�����з�������������force()��������������ǿ��ˢ�µ��洢�豸��ȥ��load()���洢�豸�е����ݼ��ص��ڴ��С�
 *  isLoaded()λ���ڴ��е������Ƿ���洢������ͬ��������ֻ�򵥵���ʾ��һ��put()��get()����������֮�⣬
 *  �㻹����ʹ��asCharBuffer( )֮��ķ����õ���Ӧ�����������ݵĻ�����ͼ�󣬿��Է���Ķ�д�����������ݡ�
 * */
public class LargeMappedFiles {
	static int length = 0x8000000; // 128 Mb

	public static void main(String[] args) throws Exception {
		// Ϊ���Կɶ���д�ķ�ʽ���ļ�������ʹ��RandomAccessFile�������ļ���
		FileChannel fc = new RandomAccessFile("test.dat", "rw").getChannel();
		//ע�⣬�ļ�ͨ���Ŀɶ���дҪ�������ļ�������ɶ�д�Ļ���֮��
		MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, length);
		//д128M������
		for (int i = 0; i < length; i++) {
			out.put((byte) 'x');
		}
		System.out.println("Finished writing");
		//��ȡ�ļ��м�6���ֽ�����
		for (int i = length / 2; i < length / 2 + 6; i++) {
			System.out.print((char) out.get(i));
		}
		fc.close();
	}
}