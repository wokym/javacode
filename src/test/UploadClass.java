package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;


public class UploadClass {
	public static void main(String[] args) {
		String str="\"aaa\"\"aa\"";
		str = str.replaceAll("^\"|\"$","");
		System.out.println(str);
		String a=null;
		Map<String,Object> map=new HashMap<>();
		map.put(a, "aa");
		Object object = map.get(null);
		System.out.println(object);
		String pat="^[0-9]+$";
		System.out.println("22:22".matches(pat));
	}
	//�ļ��ϴ�
//	public void uploadFile(HttpServletRequest request,HttpServletResponse response){
//		//�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
////		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
//		String format = simp.format(new Date());
//		Object school_id = request.getSession().getAttribute("school_id");//ѧУid
//		//�ļ����͵�
//        String savePath = request.getSession().getServletContext().getRealPath("/upload/");
//        String path="teacherclass/"+school_id+"/"+format+"/";
//        //�ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼
//        String tempPath =  request.getSession().getServletContext().getRealPath("/upload/temp/"+path);
//        File tmpFile = new File(tempPath);
//        if (!tmpFile.exists()) {
//            //������ʱĿ¼
//            tmpFile.mkdirs();
//        }
//        
//        //��Ϣ��ʾ
//        String message = "";
//        try{
//            //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
//            //1������һ��DiskFileItemFactory����
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            //���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼���С�
//            factory.setSizeThreshold(1024*100);//���û������Ĵ�СΪ100KB�������ָ������ô�������Ĵ�СĬ����10KB
//            //�����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
//            factory.setRepository(tmpFile);
//            //2������һ���ļ��ϴ�������
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            //�����ļ��ϴ�����
//            upload.setProgressListener(new ProgressListener(){
//                public void update(long pBytesRead, long pContentLength, int arg2) {
//                    System.out.println("�ļ���СΪ��" + pContentLength + ",��ǰ�Ѵ���" + pBytesRead);
//                    /**
//                     * �ļ���СΪ��14608,��ǰ�Ѵ���4096
//				                        �ļ���СΪ��14608,��ǰ�Ѵ���7367
//				                        �ļ���СΪ��14608,��ǰ�Ѵ���11419
//				                        �ļ���СΪ��14608,��ǰ�Ѵ���14608
//                     */
//                }
//            });
//             //����ϴ��ļ�������������
//            upload.setHeaderEncoding("UTF-8"); 
//            //3���ж��ύ�����������Ƿ����ϴ���������
//            if(!ServletFileUpload.isMultipartContent(request)){
//                //���մ�ͳ��ʽ��ȡ����
//                return;
//            }
//            
//            //�����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB
//            upload.setFileSizeMax(1024*1024);
//            //�����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB
//            upload.setSizeMax(1024*1024*10);
//            //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
//            List<FileItem> list = upload.parseRequest(request);
//            for(FileItem item : list){
//                //���fileitem�з�װ������ͨ�����������
//                if(item.isFormField()){
//                    String name = item.getFieldName();
//                    //�����ͨ����������ݵ�������������
//                    String value = item.getString("UTF-8");
//                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
//                    System.out.println(name + "=" + value);
//                }else{//���fileitem�з�װ�����ϴ��ļ�
//                    //�õ��ϴ����ļ����ƣ�
//                    String filename = item.getName();
//                    System.out.println(filename);
//                    if(filename==null || filename.trim().equals("")){
//                        continue;
//                    }
//                    //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
//                    //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
//                    filename = filename.substring(filename.lastIndexOf("\\")+1);
//                    //�õ��ϴ��ļ�����չ��
//                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
//                    //�����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
//                    System.out.println("�ϴ����ļ�����չ���ǣ�"+fileExtName);
//                    //��ȡitem�е��ϴ��ļ���������
//                     InputStream in = item.getInputStream();
//                    //�õ��ļ����������
//                    String saveFilename = CompanyUtils.getUUID()+"_"+filename;
//                    //�õ��ļ��ı���Ŀ¼
//                    String realSavePath = savePath+"/"+path;
//                    File relpat=new File(realSavePath);
//                    if (!relpat.exists()) {
//                        //����Ŀ¼
//                    	relpat.mkdirs();
//                    }
//                    //����һ���ļ������
//                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
//                    //����һ��������
//                    byte buffer[] = new byte[1024];
//                    //�ж��������е������Ƿ��Ѿ�����ı�ʶ
//                    int len = 0;
//                    //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
//                    while((len=in.read(buffer))>0){
//                        //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
//                        out.write(buffer, 0, len);
//                    }
//                    //�ر�������
//                    in.close();
//                    //�ر������
//                    out.close();
//                    //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
//                    item.delete();
//                    message = "�ļ��ϴ��ɹ���";
//                }
//            }
//        }catch (FileUploadBase.FileSizeLimitExceededException e) {
//            e.printStackTrace();
//            request.setAttribute("message", "�����ļ��������ֵ������");
//            return;
//        }catch (FileUploadBase.SizeLimitExceededException e) {
//            e.printStackTrace();
//            request.setAttribute("message", "�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������");
//            return;
//        }catch (Exception e) {
//            message= "�ļ��ϴ�ʧ�ܣ�";
//            e.printStackTrace();
//        }
//        request.setAttribute("message",message);
//	}
}
