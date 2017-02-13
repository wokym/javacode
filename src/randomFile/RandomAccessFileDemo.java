/*
 * �����ܣ���ʾ��RandomAccessFile��Ĳ�����ͬʱʵ����һ���ļ����Ʋ�����
 */
package randomFile;

import java.io.*;

public class RandomAccessFileDemo {
 public static void main(String[] args) throws Exception {
  RandomAccessFile file = new RandomAccessFile("file", "rw");
  // ������file�ļ���д����
  file.writeInt(20);// ռ4���ֽ�
  file.writeDouble(8.236598);// ռ8���ֽ�
  file.writeUTF("����һ��UTF�ַ���");// �������д�ڵ�ǰ�ļ�ָ���ǰ�����ֽڴ�������readShort()��ȡ
  file.writeBoolean(true);// ռ1���ֽ�
  file.writeShort(395);// ռ2���ֽ�
  file.writeLong(2325451l);// ռ8���ֽ�
  file.writeUTF("����һ��UTF�ַ���");
  file.writeFloat(35.5f);// ռ4���ֽ�
  file.writeChar('a');// ռ2���ֽ�

  file.seek(0);// ���ļ�ָ��λ�����õ��ļ���ʼ��

  // ���´�file�ļ��ж����ݣ�Ҫע���ļ�ָ���λ��
  System.out.println("��������������file�ļ�ָ��λ�ö����ݡ�����������");
  System.out.println(file.readInt());
  System.out.println(file.readDouble());
  System.out.println(file.readUTF());

  file.skipBytes(3);// ���ļ�ָ������3���ֽڣ������м�������һ��booleanֵ��shortֵ��
  System.out.println(file.readLong());
//  file.readShort();
  file.skipBytes(file.readShort()); // �����ļ��С�����һ��UTF�ַ�������ռ�ֽڣ�ע��readShort()�������ƶ��ļ�ָ�룬���Բ��ü�2��
  System.out.println(file.readFloat());
  
  //������ʾ�ļ����Ʋ���
  System.out.println("�������������ļ����ƣ���file��fileCopy��������������");
  file.seek(0);
  RandomAccessFile fileCopy=new RandomAccessFile("fileCopy","rw");
  int len=(int)file.length();//ȡ���ļ����ȣ��ֽ�����
  byte[] b=new byte[len];
  file.readFully(b);
  fileCopy.write(b);
  System.out.println("������ɣ�");
 }
}
