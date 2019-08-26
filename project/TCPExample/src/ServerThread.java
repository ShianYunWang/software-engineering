import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
	private Socket socket;
	Broadcast broadcast;
	ArrayList<ClientData> clientDatas;

	public ServerThread(Socket socket,ArrayList<ClientData> clientDatas, Broadcast broadcast) {
		this.socket = socket;
		this.broadcast = broadcast;
		this.clientDatas = clientDatas;
		System.out.println(this.broadcast);
	}
	
	public void run() {
//		broadcast.sendAllMsg("wtf");
		while(true) {
			try {
				clientDatas.add(new ClientData(socket));
				
				BufferedReader waitClientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//				BufferedReader receiveBroadcastMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String waitClientText = waitClientInput.readLine();//���T��
				System.out.println(waitClientText);
				broadcast.sendAllMsg(waitClientText);//�s��
//				String waitBroadcastMsg = receiveBroadcastMsg.readLine();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}
