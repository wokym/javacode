package videoUdp;

public class MainClass {
	public static void main(String[] args) {
//		test.mp4
		//传输文件
//		ClientUdpFile cud=new ClientUdpFile(8811, 8812, "D:\\downt\\video.mp4", "localhost");
		VideoClientUdpFile  cud=new VideoClientUdpFile(8811, 8812, "D:\\downt\\video.mp4", "localhost");
		//接收文件
//		ServerUdpFile sud=new ServerUdpFile("localhost", 8811, 8812, "D:\\downt\\udp.mp4");
		
//		ServerUdpFile sud=new ServerUdpFile("localhost", 8811, 8812, "D:\\downt\\udp.mp4");
		
//		VideoUdpServer vud=new VideoUdpServer(8811,"D:\\downt\\vdp.mp4");
		cud.start();
//		vud.start();
	}
}
