package imgClass;

    import java.applet.*;  
import java.awt.*;  
import java.awt.image.*;  
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
      
    @SuppressWarnings("serial")  
    public class imgf extends Applet {  
        /** 灰度变换的算法其实很简单，只要提取每个象素点的红、绿、蓝三原色，然后根据公式：灰度值= 红色亮度值*30%+绿色亮 度值*59%+蓝色亮度值*11%，计算出一个灰度值，并将其作为 红，绿，蓝三原色的新值重新写回显存即可。 */  
        Image art, Buf;  
        int onced = 0;  
        boolean is_color = true;  
        Graphics Bufg;//使用双缓冲区技术抑制闪烁；  
        Dimension xy = null;  
      
        public void init() {  
//            art = getImage(getDocumentBase(), "C:/Users/Administrator.PC-20150420HXQF/Desktop/1.jpg");  
//            int srcH = art.getHeight(null);  
//            int srcW = art.getWidth(null); 
//            resize(srcW, srcH);//装入图片；  
        	try {
				art= ImageIO.read(new File("C:/Users/Administrator.PC-20150420HXQF/Desktop/1.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            resize(640, 480);//装入图片；  
        }  
      
        public void paint(Graphics g) {  
            g.setColor(new Color(0xffffff));  
            g.fillRect(0, 0, getWidth(), getHeight());  
            if (onced == 0)  
            //如果是第一次装入图片，则直接显示；  
            {  
                g.drawImage(art, 0, 0, this);  
            }  
            if ((onced == 1) || (onced == 2))  
            //如果正在进行灰度变换，则提示等待；  
            {  
                g.setColor(new Color(255, 200, 0));  
                g.drawString("running!", 1, 30);  
            }  
            if (onced == 3)  
            //如果灰度变换完毕，则显示结果；  
            {  
                if (is_color)  
                    g.drawImage(Buf, 0, 0, this);  
                else  
                    g.drawImage(art, 0, 0, this);  
                is_color = !is_color;//在彩色与黑白之间变化；  
            }  
        }  
      
        public boolean mouseDown(Event evt, int x, int y) {  
            if (onced == 0) {  
                onced = 1;  
            }  
            repaint();//用鼠标触发事件；  
            return true;  
        }  
      
        public boolean mouseUp(Event evt, int x, int y) {  
            if (onced == 1) {  
                onced = 2;  
                int wd = art.getWidth(this); //取得图片宽；  
                int ht = art.getHeight(this);//取得图片高；  
                GetPixels(art, 0, 0, wd, ht);//调用灰度变换方法；  
            }  
            return true;  
        }  
      
        public void GetPixels(Image img, int x, int y, int w, int h) {  
            int[] pixels = new int[w * h];  
            //定义一块内存空间；  
            PixelGrabber pg = new PixelGrabber(img, x, y, w, h, pixels, 0, w);  
            try {  
                pg.grabPixels();  
            } catch (InterruptedException e) {  
                System.err.println("interrupted waiting for pixels!");  
                return;  
            }  
            for (int i = 0; i < h; i++) {  
                for (int j = 0; j < w; j++) {  
                    // 获得像素的颜色  
                    int color = pixels[w * i + j];  
                    int alpha = (color & 0xFF000000) >> 24;  
                    int red = (int) (((color & 0x00FF0000) >> 16) * 0.3);  
                    int green = (int) (((color & 0x0000FF00) >> 8) * 0.59);  
                    int blue = (int) ((color & 0x000000FF) * 0.11);  
                    color = red + green + blue;  
                    color = (alpha << 24) | (color << 16) | (color << 8) | color;  
                    //System.out.println(Integer.toHexString(color));  
                    //由红，绿，蓝值得到灰度值；  
                    pixels[w * i + j] = color;  
                }  
            }  
            MemoryImageSource memoryImageSource = new MemoryImageSource(w, h, pixels, 0, w);
            
            Image pic = createImage(new MemoryImageSource(w, h, pixels, 0, w));  
            Bufg.drawImage(pic, 0, 0, this); //显示黑白图片；  
            onced = 3;  
            repaint();  
        }  
      
        public void update(Graphics g) {  
            if (xy == null) {  
                xy = getSize();  
                Buf = createImage(xy.width, xy.height);  
                Bufg = Buf.getGraphics();  
            }  
            paint(g);//修改update方法，避免闪烁；  
        }  
    }
