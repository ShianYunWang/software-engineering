package blood_99;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class main extends JFrame implements ActionListener {
	private int total_number_int; // 用在顯示器計算
	private String total_number_str = "0";// 顯示器初始化
	ServerSocket serverSock;// 連線
	private int judge_pos_neg;// 判斷正負 用在+10-10
	private int judge_ten_or_twenty;// 判斷10或20
	private static ArrayList cardbase = new ArrayList();// 牌庫
	private int judge_times_of_card4 = 1;// 看迴轉用幾次
	private int judge_whether_use_card5 = 0;// 看有沒有用指定
	private int judge_player = 1;// 判斷換誰出牌
	private int counts_cards = 5;// 發牌用ㄉ 拿完手牌的
	private static String card_01;// BUTTON上的字 初始手牌
	private static String card_02;
	private static String card_03;
	private static String card_04;
	private static String card_05;
	private JLabel show_number; // 顯示現在數字多少(上面的總數
	private JLabel show_function; // 顯示用了哪些功能牌(下面的
	private JLabel show_player; // 顯示換誰
	private JButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10;

	public static void main(String[] args) {
		main frame = new main();
	}

	public main() {

		// frame相關參數
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		// 顯示總數
		show_number = new JLabel(total_number_str);
		show_number.setSize(200, 200);
		show_number.setLocation(350, 0);
		show_number.setFont(new java.awt.Font("Dialog", 1, 100));
		// 顯示功能
		show_function = new JLabel("開始!");
		show_function.setSize(200, 200);
		show_function.setLocation(200, 200);
		show_function.setFont(new java.awt.Font("Dialog", 1, 30));
		// 顯示換誰
		show_player = new JLabel("換Player" + judge_player);
		show_player.setSize(200, 200);
		show_player.setLocation(500, 200);
		show_player.setFont(new java.awt.Font("Dialog", 1, 30));

		this.add(show_number);
		this.add(show_player);
		this.add(show_function);

		setTitle("Player1");// 改名字
		setVisible(true);
		setResizable(false);
		try {
			ServerSocket serverSock = new ServerSocket(8000);
			System.out.print("Server started...");
			while (true) {
				Socket cSock = serverSock.accept();
				Chat chat = new Chat(cSock);
				Thread chatthread = new Thread(chat);
//				System.out.println("connect");
				chatthread.start();
				System.out.println("connecting");
			}
		} catch (IOException e) {
			System.out.println("disconnected...");
		}

	}

	// 看換誰
	public void function_show_player() {
		function_check_card5();
		if (judge_player >= 4) {
			judge_player = 1;
		} else if (judge_player <= 0) {
			judge_player = 3;
		}
		show_player.setText("換Player" + judge_player);

	}

	// 看有沒有用過5
	public void function_check_card5() {
		if (judge_whether_use_card5 == 0) {// 沒有用指定
			function_check_card4();
		} else if (judge_whether_use_card5 % 2 == 1) {// 有用指定
			judge_whether_use_card5--;

		}
	}

	// 看有沒有用過4
	public void function_check_card4() {
		if (judge_times_of_card4 % 2 == 1) {// 用了偶數次迴轉
			judge_player++;// 換下一個人
		} else if (judge_times_of_card4 % 2 == 0) {// 用了奇數次迴轉
			judge_player--;// 換上一個人
		}

	}

	// 檢查有沒有超過99
	public void function_check() {
		if (Integer.valueOf(show_number.getText()) > 99) {
			show_number.setText("99");
			show_function.setText("YOU LOSE");
		}
	}

	// 判別出的牌是哪一張
	public void function_A() {
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int += 1;
		show_function.setText("+1");
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	public void function_2() {
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int += 2;
		show_function.setText("+2");
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	public void function_3() {
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int += 3;
		show_function.setText("+3");
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	public void function_4() {
		show_function.setText("迴轉!");// 印在顯示器上
		judge_times_of_card4++;
		function_check();
		function_show_player();
	}

	public void function_5() {
		show_function.setText("指定");
		judge_whether_use_card5++;
		function_check();
		}

	public void function_6() {
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int += 6;
		show_function.setText("+6");
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	public void function_7() {
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int += 7;
		show_function.setText("+7");
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	public void function_8() {
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int += 8;
		show_function.setText("+8");
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	public void function_9() {
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int += 9;
		show_function.setText("+9");
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	public void function_10() {
		judge_ten_or_twenty = 10;
		show_function.setText("+-10");

	}

	public void function_J() {
		show_function.setText("PASS");
		function_check();
		function_show_player();
	}

	public void function_Q() {
		judge_ten_or_twenty = 20;
		show_function.setText("+-20");

	}

	public void function_K() {
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int = 99;
		show_function.setText("玖拾玖");
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	// 判別用加十還是減十
	public void function_positive() {
		judge_pos_neg = 1;
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int += judge_ten_or_twenty * judge_pos_neg;
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	public void function_negetive() {
		judge_pos_neg = -1;
		total_number_int = Integer.valueOf(total_number_str); // 把顯示的數字轉成整數做計算
		total_number_int += judge_ten_or_twenty * judge_pos_neg;
		total_number_str = String.valueOf(total_number_int);// 再把數字轉成字串
		show_number.setText(total_number_str);// 印在顯示器上
		function_check();
		function_show_player();
	}

	// 判斷出的牌是什麼
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("A")) {
			function_A();
		} else if (e.getActionCommand().equals("2")) {
			function_2();
		} else if (e.getActionCommand().equals("3")) {
			function_3();
		} else if (e.getActionCommand().equals("4")) {
			function_4();
		} else if (e.getActionCommand().equals("5")) {
			function_5();
		} else if (e.getActionCommand().equals("6")) {
			function_6();
		} else if (e.getActionCommand().equals("7")) {
			function_7();
		} else if (e.getActionCommand().equals("8")) {
			function_8();
		} else if (e.getActionCommand().equals("9")) {
			function_9();
		} else if (e.getActionCommand().equals("10")) {
			function_10();
		} else if (e.getActionCommand().equals("J")) {
			function_J();
		} else if (e.getActionCommand().equals("Q")) {
			function_Q();
		} else if (e.getActionCommand().equals("K")) {
			function_K();
		} else if (e.getActionCommand().equals("+")) {
			function_positive();
		} else if (e.getActionCommand().equals("-")) {
			function_negetive();
		}
		

		// implement button listener
	}

	class Chat implements Runnable {
		private Socket socket;

		public Chat(Socket socket) {
			this.socket = socket;
		}// ip位址

		public void run() {
			try {

				BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					System.out.println("wtf");
				while (true) {
					String clientText = clientInput.readLine();
					System.out.println("From Client: " + clientText);
					System.out.print(clientText);
					if (clientText.equals("1")) {
						function_A();
					} else if (clientText.equals("2")) {
						function_2();
					} else if (clientText.equals("3")) {

						function_3();
					} else if (clientText.equals("4")) {

						function_4();
					} else if (clientText.equals("5")) {

						function_5();
					} else if (clientText.equals("6")) {
						function_6();
					} else if (clientText.equals("7")) {
						function_7();
					} else if (clientText.equals("8")) {
						function_8();
					} else if (clientText.equals("9")) {
						function_9();
					} else if (clientText.equals("10")) {
						function_10();
					} else if (clientText.equals("11")) {
						function_J();
					} else if (clientText.equals("12")) {
						function_Q();
					} else if (clientText.equals("13")) {
						function_K();
					} else if (clientText.equals("+")) {
						function_positive();
					} else if (clientText.equals("-")) {
						function_negetive();
					} else if (clientText.equals("P1")) {
						judge_player = 1;
						function_show_player();
					} else if (clientText.equals("P2")) {
						judge_player = 2;
						function_show_player();
					} else if (clientText.equals("P3")) {
						judge_player = 3;
						function_show_player();
					}
					// 出牌後要抽牌
					if ((clientText.equals(bt1))) {
						bt1.setText(String.valueOf(cardbase.get(counts_cards)));
						counts_cards++;

					} else if ((clientText.equals(bt2))) {
						bt2.setText(String.valueOf(cardbase.get(counts_cards)));
						counts_cards++;

					} else if ((clientText.equals(bt3))) {
						bt3.setText(String.valueOf(cardbase.get(counts_cards)));
						counts_cards++;

					} else if ((clientText.equals(bt4))) {
						bt4.setText(String.valueOf(cardbase.get(counts_cards)));
						counts_cards++;

					} else if ((clientText.equals(bt5))) {
						bt5.setText(String.valueOf(cardbase.get(counts_cards)));
						counts_cards++;
					}

					// implement button listener
				}
				//
//					if (clientText.equals("bye"))
//						break;
//				}

//					System.out.println("wtf2");
//				clientInput.close();
//					socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
