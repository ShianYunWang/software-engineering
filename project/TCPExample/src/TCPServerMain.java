import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.PrintStream;

public class TCPServerMain{
	
	public static void main(String[] args) {
		ServerClass server = new ServerClass();
		server.start();
	}
}
class ServerClass implements Broadcast {
	ArrayList<ClientData> clientDatas = new ArrayList<>();
	public ServerClass() {
		
	}
	public void start () {
		try {
			ServerSocket serverSock = new ServerSocket(8080);
			System.out.print("Server started...");
			while (true) {
				Socket cSock = serverSock.accept();
				new Thread(new ServerThread(cSock,clientDatas, this)).start();
				System.out.println("connecting");
			}
		} catch (IOException e) {
			System.out.println("disconnected...");
		}
	}
	
	@Override//收到廣播指令
	public void sendAllMsg(String msg) {
		//跑所有IP
		for(int i =0;i < clientDatas.size();i++) {
			try {
				PrintStream writer = new PrintStream(clientDatas.get(i).getSocket().getOutputStream(), true);
				writer.println(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
//		System.out.println(msg);
		
	}
	private PrintStream PrintStream(OutputStream outputStream, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
	private Object PrintStream(Socket socket) {
		// TODO Auto-generated method stub
		return null;
	}
}