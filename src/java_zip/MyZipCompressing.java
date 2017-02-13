package java_zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/** 
 * ����ʵ����ZIPѹ��������Ϊ2���� �� ѹ����compression�����ѹ��decompression�� 
 * <p> 
 * ���¹��ܰ������˶�̬���ݹ��JAVA���ļ��������ԶԵ����ļ������⼶���ļ��н���ѹ���ͽ�ѹ�� ���ڴ������Զ���Դ����·����Ŀ�����·���� 
 * <p> 
 * �ڱ��δ����У�ʵ�ֵ���ѹ�����֣���ѹ���ּ�������Decompression���֡� 
 *  
 * @author HAN 
 *  
 */  

public class MyZipCompressing {
	private int k = 1; // ����ݹ��������  
	  
    public MyZipCompressing() {  
        // TODO Auto-generated constructor stub  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        MyZipCompressing book = new MyZipCompressing();  
        try {  
            book.zip("C:\\Users\\Administrator.PC-20150420HXQF\\Desktop\\ZipTestCompressing.zip",  
                    new File("D:\\downt"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
  
    private void zip(String zipFileName, File inputFile) throws Exception {  
        System.out.println("ѹ����...");  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));  
        BufferedOutputStream bo = new BufferedOutputStream(out);  
        zip(out, inputFile, inputFile.getName(), bo);  
        bo.close();  
        out.close(); // ������ر�  
        System.out.println("ѹ�����");  
    }  
  
    private void zip(ZipOutputStream out, File f, String base,  
            BufferedOutputStream bo) throws Exception { // ��������  
        if (f.isDirectory()) {  
            File[] fl = f.listFiles();  
            if (fl.length == 0) {  
                out.putNextEntry(new ZipEntry(base + "/")); // ����zipѹ�������base  
                System.out.println(base + "/");  
            }  
            for (int i = 0; i < fl.length; i++) {  
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // �ݹ�������ļ���  
            }  
            System.out.println("��" + k + "�εݹ�");  
            k++;  
        } else {  
            out.putNextEntry(new ZipEntry(base)); // ����zipѹ�������base  
            System.out.println(base);  
            FileInputStream in = new FileInputStream(f);  
            BufferedInputStream bi = new BufferedInputStream(in);  
            int b;  
            while ((b = bi.read()) != -1) {  
                bo.write(b); // ���ֽ���д�뵱ǰzipĿ¼  
            }  
            bo.flush();
            bi.close();  
            in.close(); // �������ر�  
        }  
    }  
}
