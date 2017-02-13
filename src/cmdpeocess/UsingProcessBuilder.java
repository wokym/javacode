package cmdpeocess;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
/**
 * ��J2SE5.0֮ǰʹ��Runtime��exec����ִ�б�������.
 * ��J2Se5.0֮��,����ʹ��ProcessBuilderִ�б�������
 * ���ṩ�Ĺ��ܸ��ӷḻ,�ܹ��������ù���Ŀ¼������������
 * ����PorcessBuilderִ��Windows����ϵͳ��"ipconfig/all"����,��ȡ����������MAC��ַ
*/
/**�ؼ���������
 * �ñ�������������Ĳ���ѡ���ProcessBuilder����,����start����ִ������,����һ������,����һ��Process����
 * ProcessBuilder��environment����������н��̵Ļ�������,�õ�һ��Map,�����޸Ļ�������
 * ProcessBuilder��directory�����л�����Ŀ¼
 * Process��getInputStream������ý��̵ı�׼�����,getErrorStream������ý��̵Ĵ��������
*/
public class UsingProcessBuilder {
    /**ִ���Զ����һ������,���������C:/temp��,������Ҫ��������������֧��*/
    public static boolean executeMyCommand1(){
           //����ϵͳ���̴�����
           ProcessBuilder pb = new ProcessBuilder("myCommand","myArg1","myArg2");
           Map<String, String> env = pb.environment(); //��ý��̵Ļ���
           //���ú�ȥ����������
           env.put("VAR1", "myValue");
           env.remove("VAR0");
           env.put("VAR2", env.get("VAR1") + ";");
           //������������,��ȡ������������ֵ
           Iterator it=env.keySet().iterator();
           String sysatt = null;
           while(it.hasNext())
           {
                  sysatt = (String)it.next();
                  System.out.println("System Attribute:"+sysatt+"="+env.get(sysatt));
           }
           pb.directory(new File("C:/temp"));
           try{
                  Process p = pb.start(); //�õ�����ʵ��
                  //�ȴ�����ִ�����
                  if(p.waitFor() != 0){
                         //����������н����Ϊ0,��ʾ�����Ǵ����˳���
                         //��ý���ʵ���Ĵ������
                         InputStream error = p.getErrorStream();
                         //do something
                  }
                  InputStream sdin = p.getInputStream(); //��ý���ʵ���ı�׼���
                  //do something
           }catch(IOException e){
           }catch(InterruptedException e){
           }
           return true;
    }
    /**��ȡWindowsϵͳ�µ�������MAC��ַ*/
    public static List<String>  getPhysicalAddress(){
           Process p = null;
           List<String> address = new ArrayList<String>(); //���������б�
           try{
                  p = new ProcessBuilder("ipconfig","/all").start(); //ִ��ipconfig/all����
           }catch(IOException e){
                  return address;
           }
           byte[] b = new byte[1024];
           int readbytes = -1;
           StringBuffer sb = new StringBuffer();
           //��ȡ�������ֵ
           //��JAVA IO��,������������JVM����,��д������ⲿ����Դ����
           InputStream in = p.getInputStream();
           try{
                  while((readbytes = in.read(b)) != -1){
                         sb.append(new String(b,0,readbytes));
                  }
           }catch(IOException e1){
           }finally {
                  try{
                         in.close();
                  }catch (IOException e2){
                  }
           }
           //�����Ƿ������ֵ,�õ���������
           String rtValue = sb.toString();
           System.out.println(rtValue);
           int i = rtValue.indexOf("Physical Address. . . . . . . . . :");
           while (i > 0){
                  rtValue = rtValue.substring(i + "Physical Address. . . . . . . . . :".length());
                  address.add(rtValue.substring(1,18));
                  i = rtValue.indexOf("Physical Address. . . . . . . . . :");
           }
           return address;
    }
    public static void main(String[] args){
//           executeMyCommand1();
    	getPhysicalAddress();
    }
}
