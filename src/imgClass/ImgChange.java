package imgClass;

    import java.awt.Component;
import java.awt.Image;  
import java.awt.color.ColorSpace;  
import java.awt.image.BufferedImage;  
import java.awt.image.ColorConvertOp;  
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
      


    import javax.imageio.ImageIO;  
      


    import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
      
    public class ImgChange {  
          
        public static void main(String[] args) {  
//            	File file=new File("C:/Users/Administrator.PC-20150420HXQF/Desktop/11110.jpg");  
//            		changeImge(file);
        	File file=new File("C:/Users/Administrator.PC-20150420HXQF/Desktop/1.jpg"); 
        	changeImg(file,"C:/Users/Administrator.PC-20150420HXQF/Desktop/11110.jpg");
        }  
          
        /** 
         * * 转换图片 * * 
         */  
        public static void changeImge(File img) {  
            try {  
                Image image = ImageIO.read(img);  
                int srcH = image.getHeight(null);  
                int srcW = image.getWidth(null);  
                BufferedImage bufferedImage = new BufferedImage(srcW, srcH,BufferedImage.TYPE_3BYTE_BGR);  
                bufferedImage.getGraphics().drawImage(image, 0,0, srcW, srcH, null); 
                bufferedImage=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null).filter (bufferedImage,null);   
                FileOutputStream fos = new FileOutputStream(img);  
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);  
                encoder.encode(bufferedImage);  
                fos.close();  
                System.out.println("转换成功...");  
            } catch (Exception e) {  
                e.printStackTrace();  
                throw new IllegalStateException("图片转换出错！", e);  
            }  
        } 
        
        public static void  changeImg(File img,String file) {
        	 
            BufferedImage bi = null;  
            try {  
                bi = ImageIO.read(img);  
	            int width = bi.getWidth();  
	            int height = bi.getHeight();  
	            int minx = bi.getMinX();  
	            int miny = bi.getMinY();  
	            for (int i = miny; i < height; i++) {  
	                for (int j = minx; j < width; j++) {  
	                    // 获得像素的颜色  
	                    int color = bi.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字  
	                    int alpha = (color & 0xFF000000) >> 24;  
	                    int red = (int) (((color & 0x00FF0000) >> 16) * 0.3);  
	                    int green = (int) (((color & 0x0000FF00) >> 8) * 0.59);  
	                    int blue = (int) ((color & 0x000000FF) * 0.11);  
	                    color = red + green + blue;  
	                    color = (alpha << 24) | (color << 16) | (color << 8) | color;  
	                    //System.out.println(Integer.toHexString(color));  
	                    //由红，绿，蓝值得到灰度值；  
	                    bi.setRGB(j, i, color);
	                }  
	            } 
	            bi.flush();
	            FileOutputStream fos = new FileOutputStream(file);  
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);  
	            encoder.encode(bi);  
	            fos.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            } 
            System.out.println("ok");
        }
    }  