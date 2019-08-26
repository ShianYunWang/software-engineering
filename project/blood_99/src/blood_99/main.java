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
	private int total_number_int; // �Φb��ܾ��p��
	private String total_number_str = "0";// ��ܾ���l��
	ServerSocket serverSock;// �s�u
	private int judge_pos_neg;// �P�_���t �Φb+10-10
	private int judge_ten_or_twenty;// �P�_10��20
	private static ArrayList cardbase = new ArrayList();// �P�w
	private int judge_times_of_card4 = 1;// �ݰj��δX��
	private int judge_whether_use_card5 = 0;// �ݦ��S���Ϋ��w
	private int judge_player = 1;// �P�_���֥X�P
	private int counts_cards = 5;// �o�P�Σx ������P��
	private static String card_01;// BUTTON�W���r ��l��P
	private static String card_02;
	private static String card_03;
	private static String card_04;
	private static String card_05;
	private JLabel show_number; // ��ܲ{�b�Ʀr�h��(�W�����`��
	private JLabel show_function; // ��ܥΤF���ǥ\��P(�U����
	private JLabel show_player; // ��ܴ���
	private JButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10;

	public static void main(String[] args) {
		main frame = new main();
	}

	public main() {

		// frame�����Ѽ�
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		// ����`��
		show_number = new JLabel(total_number_str);
		show_number.setSize(200, 200);
		show_number.setLocation(350, 0);
		show_number.setFont(new java.awt.Font("Dialog", 1, 100));
		// ��ܥ\��
		show_function = new JLabel("�}�l!");
		show_function.setSize(200, 200);
		show_function.setLocation(200, 200);
		show_function.setFont(new java.awt.Font("Dialog", 1, 30));
		// ��ܴ���
		show_player = new JLabel("��Player" + judge_player);
		show_player.setSize(200, 200);
		show_player.setLocation(500, 200);
		show_player.setFont(new java.awt.Font("Dialog", 1, 30));

		this.add(show_number);
		this.add(show_player);
		this.add(show_function);

		setTitle("Player1");// ��W�r
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

	// �ݴ���
	public void function_show_player() {
		function_check_card5();
		if (judge_player >= 4) {
			judge_player = 1;
		} else if (judge_player <= 0) {
			judge_player = 3;
		}
		show_player.setText("��Player" + judge_player);

	}

	// �ݦ��S���ιL5
	public void function_check_card5() {
		if (judge_whether_use_card5 == 0) {// �S���Ϋ��w
			function_check_card4();
		} else if (judge_whether_use_card5 % 2 == 1) {// ���Ϋ��w
			judge_whether_use_card5--;

		}
	}

	// �ݦ��S���ιL4
	public void function_check_card4() {
		if (judge_times_of_card4 % 2 == 1) {// �ΤF���Ʀ��j��
			judge_player++;// ���U�@�ӤH
		} else if (judge_times_of_card4 % 2 == 0) {// �ΤF�_�Ʀ��j��
			judge_player--;// ���W�@�ӤH
		}

	}

	// �ˬd���S���W�L99
	public void function_check() {
		if (Integer.valueOf(show_number.getText()) > 99) {
			show_number.setText("99");
			show_function.setText("YOU LOSE");
		}
	}

	// �P�O�X���P�O���@�i
	public void function_A() {
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int += 1;
		show_function.setText("+1");
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
		function_check();
		function_show_player();
	}

	public void function_2() {
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int += 2;
		show_function.setText("+2");
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
		function_check();
		function_show_player();
	}

	public void function_3() {
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int += 3;
		show_function.setText("+3");
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
		function_check();
		function_show_player();
	}

	public void function_4() {
		show_function.setText("�j��!");// �L�b��ܾ��W
		judge_times_of_card4++;
		function_check();
		function_show_player();
	}

	public void function_5() {
		show_function.setText("���w");
		judge_whether_use_card5++;
		function_check();
		}

	public void function_6() {
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int += 6;
		show_function.setText("+6");
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
		function_check();
		function_show_player();
	}

	public void function_7() {
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int += 7;
		show_function.setText("+7");
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
		function_check();
		function_show_player();
	}

	public void function_8() {
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int += 8;
		show_function.setText("+8");
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
		function_check();
		function_show_player();
	}

	public void function_9() {
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int += 9;
		show_function.setText("+9");
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
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
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int = 99;
		show_function.setText("�h�B�h");
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
		function_check();
		function_show_player();
	}

	// �P�O�Υ[�Q�٬O��Q
	public void function_positive() {
		judge_pos_neg = 1;
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int += judge_ten_or_twenty * judge_pos_neg;
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
		function_check();
		function_show_player();
	}

	public void function_negetive() {
		judge_pos_neg = -1;
		total_number_int = Integer.valueOf(total_number_str); // ����ܪ��Ʀr�ন��ư��p��
		total_number_int += judge_ten_or_twenty * judge_pos_neg;
		total_number_str = String.valueOf(total_number_int);// �A��Ʀr�ন�r��
		show_number.setText(total_number_str);// �L�b��ܾ��W
		function_check();
		function_show_player();
	}

	// �P�_�X���P�O����
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
		}// ip��}

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
					// �X�P��n��P
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