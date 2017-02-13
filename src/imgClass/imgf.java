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
        /** �Ҷȱ任���㷨��ʵ�ܼ򵥣�ֻҪ��ȡÿ�����ص�ĺ졢�̡�����ԭɫ��Ȼ����ݹ�ʽ���Ҷ�ֵ= ��ɫ����ֵ*30%+��ɫ�� ��ֵ*59%+��ɫ����ֵ*11%�������һ���Ҷ�ֵ����������Ϊ �죬�̣�����ԭɫ����ֵ����д���Դ漴�ɡ� */  
        Image art, Buf;  
        int onced = 0;  
        boolean is_color = true;  
        Graphics Bufg;//ʹ��˫����������������˸��  
        Dimension xy = null;  
      
        public void init() {  
//            art = getImage(getDocumentBase(), "C:/Users/Administrator.PC-20150420HXQF/Desktop/1.jpg");  
//            int srcH = art.getHeight(null);  
//            int srcW = art.getWidth(null); 
//            resize(srcW, srcH);//װ��ͼƬ��  
        	try {
				art= ImageIO.read(new File("C:/Users/Administrator.PC-20150420HXQF/Desktop/1.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            resize(640, 480);//װ��ͼƬ��  
        }  
      
        public void paint(Graphics g) {  
            g.setColor(new Color(0xffffff));  
            g.fillRect(0, 0, getWidth(), getHeight());  
            if (onced == 0)  
            //����ǵ�һ��װ��ͼƬ����ֱ����ʾ��  
            {  
                g.drawImage(art, 0, 0, this);  
            }  
            if ((onced == 1) || (onced == 2))  
            //������ڽ��лҶȱ任������ʾ�ȴ���  
            {  
                g.setColor(new Color(255, 200, 0));  
                g.drawString("running!", 1, 30);  
            }  
            if (onced == 3)  
            //����Ҷȱ任��ϣ�����ʾ�����  
            {  
                if (is_color)  
                    g.drawImage(Buf, 0, 0, this);  
                else  
                    g.drawImage(art, 0, 0, this);  
                is_color = !is_color;//�ڲ�ɫ��ڰ�֮��仯��  
            }  
        }  
      
        public boolean mouseDown(Event evt, int x, int y) {  
            if (onced == 0) {  
                onced = 1;  
            }  
            repaint();//����괥���¼���  
            return true;  
        }  
      
        public boolean mouseUp(Event evt, int x, int y) {  
            if (onced == 1) {  
                onced = 2;  
                int wd = art.getWidth(this); //ȡ��ͼƬ��  
                int ht = art.getHeight(this);//ȡ��ͼƬ�ߣ�  
                GetPixels(art, 0, 0, wd, ht);//���ûҶȱ任������  
            }  
            return true;  
        }  
      
        public void GetPixels(Image img, int x, int y, int w, int h) {  
            int[] pixels = new int[w * h];  
            //����һ���ڴ�ռ䣻  
            PixelGrabber pg = new PixelGrabber(img, x, y, w, h, pixels, 0, w);  
            try {  
                pg.grabPixels();  
            } catch (InterruptedException e) {  
                System.err.println("interrupted waiting for pixels!");  
                return;  
            }  
            for (int i = 0; i < h; i++) {  
                for (int j = 0; j < w; j++) {  
                    // ������ص���ɫ  
                    int color = pixels[w * i + j];  
                    int alpha = (color & 0xFF000000) >> 24;  
                    int red = (int) (((color & 0x00FF0000) >> 16) * 0.3);  
                    int green = (int) (((color & 0x0000FF00) >> 8) * 0.59);  
                    int blue = (int) ((color & 0x000000FF) * 0.11);  
                    color = red + green + blue;  
                    color = (alpha << 24) | (color << 16) | (color << 8) | color;  
                    //System.out.println(Integer.toHexString(color));  
                    //�ɺ죬�̣���ֵ�õ��Ҷ�ֵ��  
                    pixels[w * i + j] = color;  
                }  
            }  
            MemoryImageSource memoryImageSource = new MemoryImageSource(w, h, pixels, 0, w);
            
            Image pic = createImage(new MemoryImageSource(w, h, pixels, 0, w));  
            Bufg.drawImage(pic, 0, 0, this); //��ʾ�ڰ�ͼƬ��  
            onced = 3;  
            repaint();  
        }  
      
        public void update(Graphics g) {  
            if (xy == null) {  
                xy = getSize();  
                Buf = createImage(xy.width, xy.height);  
                Bufg = Buf.getGraphics();  
            }  
            paint(g);//�޸�update������������˸��  
        }  
    }
