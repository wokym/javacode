package videoUdp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class VideoClientUdpFile extends Thread {
	private DatagramSocket send;
	private DatagramPacket pkg;
	private DatagramPacket messagepkg;
	private int serverPort;
	private int clientPort;
	private String path;
	private String ip;

	public VideoClientUdpFile(int serverPort, int clientPort, String path, String ip) {
		super();
		this.serverPort = serverPort;
		this.clientPort = clientPort;
		this.path = path;
		this.ip = ip;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void send() {
		try {
			// 文件发送者设置监听端口
			send = new DatagramSocket(clientPort);
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(new File(path)));
			// 确认信息包
			byte[] messagebuf = new byte[1024];
			messagepkg = new DatagramPacket(messagebuf, messagebuf.length);
			// 文件包
			byte[] buf = new byte[1024 * 63];
			int len;
			int i=0;
			while ((len = bis.read(buf)) != -1) {
				i++;
				pkg = new DatagramPacket(buf, len, new InetSocketAddress(ip,
						serverPort));
				send.send(pkg);
			}
			// 文件传完后，发送一个结束包
			System.out.println("client...........ok="+i);
			bis.close();
			send.close();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		send();
	}
}