
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
public class player4 extends JFrame implements ActionListener {
	// ��澈
	private static ArrayList cardbase = new ArrayList();
	private int counts_cards = 5;// 5 撘菜���
	// bottom 銝��5 撘菜���
	private static String card_01;
	private static String card_02;
	private static String card_03;
	private static String card_04;
	private static String card_05;
	private JButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11;
	JLabel show_number;
	private int judge_player = 1;// ����狐����
//	private int judge_ten_or_twenty;// ��10���20
	Socket connectionSock;// ��蝺�
	DataOutputStream serverOutput;//頛詨
	private JLabel show_function; // 憿舐內�鈭鈭����(銝���
	private JLabel show_player; // 憿舐內��狐
	private ImageIcon give_card;
	private ImageIcon give_card2;
	private ImageIcon give_card3;
	private ImageIcon give_card4;
	private ImageIcon give_card5;
	private JLabel show_card;// ���嚙踝嚙踝蕭��蕭嚙�
	private int judge_times_of_card4 = 1;// ��4
	private static String url;// 頧雯�������
	private String total_number_str = "00";// ���嚙踐��
	public static void main(String[] args) throws UnknownHostException, IOException {
		/////////////////////////// �����(隞乩��)
		Final pk = new Final();
		pk.createCard();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				pk.createdetail(i, j);
			}
		}
		// pk.printdetail();
		for (int k = 0; k < 52; k++) {
			cardbase.add(pk.cardList().get(k));
		}

		System.out.println(pk.cardList());
		System.out.println(cardbase);
		System.out.println(cardbase.get(0));
		card_01 = (String) cardbase.get(0);
		card_02 = (String) cardbase.get(1);
		card_03 = (String) cardbase.get(2);
		card_04 = (String) cardbase.get(3);
		card_05 = (String) cardbase.get(4);
		
		// 撱箇�RAME
		player4 frame = new player4();
		frame.setTitle("Player3");// �����
		frame.setVisible(true);
		frame.setResizable(false);
		JLabel bgLabel = null;
		ImageIcon background = null;

		background = new ImageIcon("D:\\垃圾\\NCKU\\活動\\xvillage\\JAVA\\project\\scare.jpg"); // ������

		// Image bg = background.getImage();
		// Image newimage = bg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		bgLabel = new JLabel(background); // �����＊蝷箏Label銝�
	
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight()); // ��������abel雿蔭閮剔蔭�����末憛怠���
		// 2���捆閬��JPanel嚗���雿輻setOpaque()靘蝙閬������


		frame.getLayeredPane().add(bgLabel, Integer.MIN_VALUE);
	
		frame.test();
		
	}

	public player4() throws MalformedURLException {
//��蝺�
		try {
			connectionSock = new Socket("127.0.0.1", 8000);
			serverOutput = new DataOutputStream(connectionSock.getOutputStream());
//			serverOutput.writeBytes("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 頛貉��
//		win_or_lose.add(null);
//		win_or_lose.add(null);
//		win_or_lose.add(null);

		// frame����
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		// 憿舐內蝮賣
		show_number = new JLabel(total_number_str);
		Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 35);
		show_number.setVerticalTextPosition(SwingConstants.CENTER);
		show_number.setHorizontalTextPosition(SwingConstants.CENTER);
		show_number.setFont(font);
		show_number.setForeground(Color.PINK);
		show_number.setSize(200, 200);
		show_number.setLocation(360, 30);
		show_number.setFont(new java.awt.Font("Dialog", 1, 100));
		// 憿舐內��
		show_function = new JLabel("���     憪�  ");
		Font font1 = new Font(Font.DIALOG_INPUT, Font.ITALIC, 35);
		show_function.setFont(font1);
		show_function.setForeground(Color.WHITE);
		show_function.setSize(200, 200);
		show_function.setLocation(175, 210);
		show_function.setFont(new java.awt.Font("Dialog", 1, 30));
		// 憿舐內��狐
		show_player = new JLabel("��layer" + judge_player);
		Font font2 = new Font(Font.DIALOG_INPUT, Font.ITALIC, 35);
		show_player.setFont(font2);
		show_player.setForeground(Color.WHITE);
		show_player.setSize(200, 200);
		show_player.setLocation(500, 210);
		show_player.setFont(new java.awt.Font("Dialog", 1, 30));
		// 憿舐內�����
		show_card = new JLabel("�摰嗅���");
		Font font3 = new Font(Font.DIALOG_INPUT, Font.ITALIC, 35);
		show_card.setFont(font3);
		show_card.setForeground(Color.WHITE);
		show_card.setSize(200, 200);
		show_card.setLocation(150, 90);
		show_card.setFont(new java.awt.Font("Dialog", 1, 20));

		bt11 = new JButton();
		bt11.setBounds(150, 55, 80, 120);
		bt11.setIcon(give_card);
		bt11.setVerticalTextPosition(SwingConstants.BOTTOM);
		bt11.setHorizontalTextPosition(SwingConstants.CENTER);
//		System.out.println("wtfwtf2");
		// 閮剔蔭����
		// 鈭撐�����
		// 鈭撐�����
		bt1 = new JButton(card_01);
		bt1.setVerticalTextPosition(SwingConstants.CENTER);
		bt1.setHorizontalTextPosition(SwingConstants.CENTER);
		addicon();
		bt1.setForeground(Color.WHITE);
		bt2 = new JButton(card_02);
		bt2.setVerticalTextPosition(SwingConstants.CENTER);
		bt2.setHorizontalTextPosition(SwingConstants.CENTER);
		addicon2();
		bt2.setForeground(Color.WHITE);

		bt3 = new JButton(card_03);
		bt3.setVerticalTextPosition(SwingConstants.CENTER);
		bt3.setHorizontalTextPosition(SwingConstants.CENTER);
		addicon3();
		bt3.setForeground(Color.WHITE);

		bt4 = new JButton(card_04);
		bt4.setVerticalTextPosition(SwingConstants.CENTER);
		bt4.setHorizontalTextPosition(SwingConstants.CENTER);
		addicon4();
		bt4.setForeground(Color.WHITE);

		bt5 = new JButton(card_05);
		bt5.setVerticalTextPosition(SwingConstants.CENTER);
		bt5.setHorizontalTextPosition(SwingConstants.CENTER);
		addicon5();
		bt5.setForeground(Color.WHITE);

		bt1.setBounds(50, 430, 80, 120);
		bt2.setBounds(150, 430, 80, 120);
		bt3.setBounds(250, 430, 80, 120);
		bt4.setBounds(350, 430, 80, 120);
		bt5.setBounds(450, 430, 80, 120);
		// 嚙踝�蕭謘�+10or-10
		bt6 = new JButton("+");
		bt7 = new JButton("-");
		bt6.setBounds(565, 430, 50, 50);
		bt7.setBounds(565, 500, 50, 50);

		bt8 = new JButton("P1");
		bt9 = new JButton("P2");
		bt10 = new JButton("P3");

		bt8.setBounds(650, 430, 50, 30);
		bt9.setBounds(650, 470, 50, 30);
		bt10.setBounds(650, 510, 50, 30);

		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		bt6.addActionListener(this);
		bt7.addActionListener(this);
		bt8.addActionListener(this);
		bt9.addActionListener(this);
		bt10.addActionListener(this);
		// ��镼踹�frame鋆⊿
		// ��镼踹�frame鋆⊿

		JPanel panel = new JPanel();
		panel.setSize(800, 600);
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.add(bt1);
		panel.add(bt2);
		panel.add(bt3);
		panel.add(bt4);
		panel.add(bt5);
		panel.add(bt6);
		panel.add(bt7);
		panel.add(bt8);
		panel.add(bt9);
		panel.add(bt10);
		panel.add(bt11);
		panel.add(show_number);
		panel.add(show_function);
		panel.add(show_player);
		panel.add(show_card);
		this.getLayeredPane().add(panel, 1);

	}

//	public void function_check_winner() {
//		if (judge_times_of_card4 % 2 == 1) {
//			int i = 0;
//			if ((win_or_lose.get(i) == "LOSE" & win_or_lose.get(i + 1) == "LOSE")
//					|| (win_or_lose.get(i + 1) == "LOSE" & win_or_lose.get(i + 2) == "LOSE")
//					|| (win_or_lose.get(i) == "LOSE" & win_or_lose.get(i + 2) == "LOSE")) {
//				show_function.setText("WINNER P" + judge_player);
//			}
//		} else if (judge_times_of_card4 % 2 == 0) {
//			int i = 2;
//			if ((win_or_lose.get(i) == "LOSE" & win_or_lose.get(i - 1) == "LOSE")
//					|| (win_or_lose.get(i - 1) == "LOSE" & win_or_lose.get(i - 2) == "LOSE")
//					|| (win_or_lose.get(i) == "LOSE" & win_or_lose.get(i - 2) == "LOSE")) {
//				show_function.setText("WINNER P" + judge_player);
//			}
//		}
//
//	}

	// 嚙踝蕭����防嚙踝�蕭�甇莎蕭嚙踝蕭
	public void function_player_lose_and_skip() {

		if (judge_times_of_card4 % 2 == 1) {
			int a = judge_player - 1;
			if (a + 1 == 3) {
				a = -1;
			}
//			if (win_or_lose.get(a + 1) == "LOSE") {
//				System.out.println(win_or_lose);
//				System.out.println(judge_player);
//				judge_player++;
//
//			}
		} else if (judge_times_of_card4 % 2 == 0) {
			int b = judge_player - 1;
			if (b - 1 == -1) {
				b = 3;
			}
//			if (win_or_lose.get(b - 1) == "LOSE") {
//				System.out.println(win_or_lose);
//				System.out.println(judge_player);
//				judge_player--;
//
//			}
		}

	}

	// 嚙踝蕭��蕭謜��
//	public void function_show_player() {
//
//		function_player_lose_and_skip();
//
//		function_check_card5();
//		if (judge_player == 4) {
//			judge_player = 1;
//		} else if (judge_player == 5) {
//			judge_player = 2;
//		} else if (judge_player == 0) {
//			judge_player = 3;
//		} else if (judge_player == -1) {
//			judge_player = 2;
//		}
//		System.out.println(judge_player);
//		System.out.println(win_or_lose);
//		//function_check_winner();
//		show_player.setText("��layer" + judge_player);
//
//	}

	// �嚗賃�蕭嚙踝嚙踐�蕭�嚙踝嚙踝蕭99
//	public void function_check() {
//		if (Integer.valueOf(show_number.getText()) > 99) {
//			show_number.setText("99");
//			total_number_str = "99";
//			total_number_int = 99;
//			show_function.setText("YOU LOSE");
//			win_or_lose.set(judge_player - 1, "LOSE");
//		}
//	}

	// 嚙踝蕭��蕭�嚙踐�蕭���蕭嚙踝蕭5
//	public void function_check_card5() {
//		if (judge_whether_use_card5 == 0) {// 瘝�����
//			function_check_card4();
//		} else if (judge_whether_use_card5 % 2 == 1) {// ������
//			judge_whether_use_card5--;
//			judge_player = judge_player_when_use5;
//
//		}
//	}

	// 嚙踝蕭��蕭�嚙踐�蕭���蕭嚙踝蕭4
//	public void function_check_card4() {
//		if (judge_times_of_card4 % 2 == 1) {// 嚙踐����蕭�����嚙�
//			judge_player++;// 嚙踝蕭謜蕭��蕭嚙踝���
//		} else if (judge_times_of_card4 % 2 == 0) {// 嚙踐���蕭������嚙�
//			judge_player--;// 嚙踝蕭謜蕭�嚙踝蕭����
//		}
//
//	}

	public void test() {
		new Thread(new ReceiveMsg(show_number, show_player, show_function, connectionSock)).run();
	}

	public void function_A() {
		try {
			serverOutput.writeBytes("1\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_2() {
		try {
			serverOutput.writeBytes("2\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_3() {
		try {
			serverOutput.writeBytes("3\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_4() {
		try {
			serverOutput.writeBytes("4\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_5() {
		try {
			serverOutput.writeBytes("5\n");
			System.out.println("wtf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_6() {
		try {
			serverOutput.writeBytes("6\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_7() {
		try {
			serverOutput.writeBytes("7\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_8() {
		try {
			serverOutput.writeBytes("8\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_9() {
		try {
			serverOutput.writeBytes("9\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_10() {
		try {
			serverOutput.writeBytes("10\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_J() {
		try {
			serverOutput.writeBytes("11\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_Q() {
		try {
			serverOutput.writeBytes("12\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_K() {
		try {
			serverOutput.writeBytes("13\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_positive() {
		try {
			serverOutput.writeBytes("+\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_negetive() {
		try {
			serverOutput.writeBytes("-\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_p1() {
		try {
			serverOutput.writeBytes("P1\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_p2() {
		try {
			serverOutput.writeBytes("P2\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void function_p3() {
		try {
			serverOutput.writeBytes("P3\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//�撌虫�����
	public void addicon() throws MalformedURLException {
		if (card_01.equals("暺�")) {
			url = "http://upload2.inven.co.kr/upload/2018/11/01/bbs/i15564270140.png";
		} else if (card_01.equals("暺��2")) {
			url = "https://o.quizlet.com/q6tSCqA.BiCeIodlD5Qv8A.png";
		} else if (card_01.equals("暺��3")) {
			url = "https://cdn.pixabay.com/photo/2015/08/11/11/56/spades-884155_960_720.png";
		} else if (card_01.equals("暺��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Playing_card_spade_4.svg/614px-Playing_card_spade_4.svg.png";
		} else if (card_01.equals("暺��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_spade_5.svg/819px-Playing_card_spade_5.svg.png";
		} else if (card_01.equals("暺��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Playing_card_spade_6.svg/2000px-Playing_card_spade_6.svg.png";
		} else if (card_01.equals("暺��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Playing_card_spade_7.svg/819px-Playing_card_spade_7.svg.png";
		} else if (card_01.equals("暺��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Playing_card_spade_8.svg/819px-Playing_card_spade_8.svg.png";
		} else if (card_01.equals("暺��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Playing_card_spade_9.svg/819px-Playing_card_spade_9.svg.png";
		} else if (card_01.equals("暺��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Playing_card_spade_10.svg/819px-Playing_card_spade_10.svg.png";
		} else if (card_01.equals("暺��11")) {
			url = "https://www.colourbox.com/preview/10382897-jack-of-spades.jpg";
		} else if (card_01.equals("暺��12")) {
			url = "https://i.pinimg.com/originals/64/fe/3a/64fe3aaedd0cd7e7a89f15127bbcc3f4.png";
		} else if (card_01.equals("暺��13")) {
			url = "https://www.colourbox.com/preview/10382907-king-of-spades.jpg";
		}

		else if (card_01.equals("蝝�")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Playing_card_heart_A.svg/1200px-Playing_card_heart_A.svg.png";
		} else if (card_01.equals("蝝��2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Playing_card_heart_2.svg/819px-Playing_card_heart_2.svg.png";
		} else if (card_01.equals("蝝��3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Playing_card_heart_3.svg/819px-Playing_card_heart_3.svg.png";
		} else if (card_01.equals("蝝��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Playing_card_heart_4.svg/819px-Playing_card_heart_4.svg.png";
		} else if (card_01.equals("蝝��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Playing_card_heart_5.svg/819px-Playing_card_heart_5.svg.png";
		} else if (card_01.equals("蝝��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Playing_card_heart_6.svg/819px-Playing_card_heart_6.svg.png";
		} else if (card_01.equals("蝝��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_heart_7.svg/819px-Playing_card_heart_7.svg.png";
		} else if (card_01.equals("蝝��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_8.svg/819px-Playing_card_heart_8.svg.png";
		} else if (card_01.equals("蝝��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_9.svg/819px-Playing_card_heart_9.svg.png";
		} else if (card_01.equals("蝝��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Playing_card_heart_10.svg/819px-Playing_card_heart_10.svg.png";
		} else if (card_01.equals("蝝��11")) {
			url = "https://as1.ftcdn.net/jpg/00/70/12/64/500_F_70126409_gjWU98odB9g2rVgRRl5nMjA7Qyu2EOdl.jpg";
		} else if (card_01.equals("蝝��12")) {
			url = "https://st2.depositphotos.com/2810953/8260/v/950/depositphotos_82602070-stock-illustration-poker-playing-card-queen-heart.jpg";
		} else if (card_01.equals("蝝��13")) {
			url = "https://pic.pimg.tw/wuchicake/1320816325-8500604.jpg";
		}

		else if (card_01.equals("璇A")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Playing_card_club_A.svg/614px-Playing_card_club_A.svg.png";
		} else if (card_01.equals("璇2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/Playing_card_club_2.svg/819px-Playing_card_club_2.svg.png";
		} else if (card_01.equals("璇3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Playing_card_club_3.svg/819px-Playing_card_club_3.svg.png";
		} else if (card_01.equals("璇4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Playing_card_club_4.svg/819px-Playing_card_club_4.svg.png";
		} else if (card_01.equals("璇5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_club_5.svg/819px-Playing_card_club_5.svg.png";
		} else if (card_01.equals("璇6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Playing_card_club_6.svg/819px-Playing_card_club_6.svg.png";
		} else if (card_01.equals("璇7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Playing_card_club_7.svg/819px-Playing_card_club_7.svg.png";
		} else if (card_01.equals("璇8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Playing_card_club_8.svg/819px-Playing_card_club_8.svg.png";
		} else if (card_01.equals("璇9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Playing_card_club_9.svg/819px-Playing_card_club_9.svg.png";
		} else if (card_01.equals("璇10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Playing_card_club_10.svg/819px-Playing_card_club_10.svg.png";
		} else if (card_01.equals("璇11")) {
			url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7R6EehRNz6VHS0nhXo3OgN965rRoesKeqVvUQpuJH-tLFzJZY-w";
		} else if (card_01.equals("璇12")) {
			url = "https://cdn5.vectorstock.com/i/1000x1000/68/74/poker-playing-card-queen-club-vector-8696874.jpg";
		} else if (card_01.equals("璇13")) {
			url = "https://images.freeimages.com/images/premium/previews/7046/7046174-king-of-clubs-two-playing-card.jpg";
		}

		else if (card_01.equals("�憛")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Playing_card_diamond_A.svg/819px-Playing_card_diamond_A.svg.png";
		} else if (card_01.equals("�憛�2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Playing_card_diamond_2.svg/819px-Playing_card_diamond_2.svg.png";
		} else if (card_01.equals("�憛�3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Playing_card_diamond_3.svg/819px-Playing_card_diamond_3.svg.png";
		} else if (card_01.equals("�憛�4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Playing_card_diamond_4.svg/819px-Playing_card_diamond_4.svg.png";
		} else if (card_01.equals("�憛�5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Playing_card_diamond_5.svg/819px-Playing_card_diamond_5.svg.png";
		} else if (card_01.equals("�憛�6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Playing_card_diamond_6.svg/819px-Playing_card_diamond_6.svg.png";
		} else if (card_01.equals("�憛�7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Playing_card_diamond_7.svg/819px-Playing_card_diamond_7.svg.png";
		} else if (card_01.equals("�憛�8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Playing_card_diamond_8.svg/819px-Playing_card_diamond_8.svg.png";
		} else if (card_01.equals("�憛�9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Playing_card_diamond_9.svg/614px-Playing_card_diamond_9.svg.png";
		} else if (card_01.equals("�憛�10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Playing_card_diamond_10.svg/819px-Playing_card_diamond_10.svg.png";
		} else if (card_01.equals("�憛�11")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/a/aa/Poker-sm-234-Jd.png";
		} else if (card_01.equals("�憛�12")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/7/70/Poker-sm-233-Qd.png";
		} else if (card_01.equals("�憛�13")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/d/d0/Poker-sm-232-Kd.png";
		}

		java.net.URL where = new URL(url);
		ImageIcon image = new ImageIcon(where);
		Image imgp = image.getImage();
		Image newimage = imgp.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);
		ImageIcon card1 = new ImageIcon(newimage);
		give_card = card1;
		bt1.setIcon(card1);

	}

	public void addicon2() throws MalformedURLException {
		if (card_02.equals("暺��1")) {
			url = "http://upload2.inven.co.kr/upload/2018/11/01/bbs/i15564270140.png";
		} else if (card_02.equals("暺��2")) {
			url = "https://o.quizlet.com/q6tSCqA.BiCeIodlD5Qv8A.png";
		} else if (card_02.equals("暺��3")) {
			url = "https://cdn.pixabay.com/photo/2015/08/11/11/56/spades-884155_960_720.png";
		} else if (card_02.equals("暺��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Playing_card_spade_4.svg/614px-Playing_card_spade_4.svg.png";
		} else if (card_02.equals("暺��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_spade_5.svg/819px-Playing_card_spade_5.svg.png";
		} else if (card_02.equals("暺��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Playing_card_spade_6.svg/2000px-Playing_card_spade_6.svg.png";
		} else if (card_02.equals("暺��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Playing_card_spade_7.svg/819px-Playing_card_spade_7.svg.png";
		} else if (card_02.equals("暺��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Playing_card_spade_8.svg/819px-Playing_card_spade_8.svg.png";
		} else if (card_02.equals("暺��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Playing_card_spade_9.svg/819px-Playing_card_spade_9.svg.png";
		} else if (card_02.equals("暺��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Playing_card_spade_10.svg/819px-Playing_card_spade_10.svg.png";
		} else if (card_02.equals("暺��11")) {
			url = "https://www.colourbox.com/preview/10382897-jack-of-spades.jpg";
		} else if (card_02.equals("暺��12")) {
			url = "https://i.pinimg.com/originals/64/fe/3a/64fe3aaedd0cd7e7a89f15127bbcc3f4.png";
		} else if (card_02.equals("暺��13")) {
			url = "https://www.colourbox.com/preview/10382907-king-of-spades.jpg";
		}

		else if (card_02.equals("蝝��1")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Playing_card_heart_A.svg/1200px-Playing_card_heart_A.svg.png";
		} else if (card_02.equals("蝝��2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Playing_card_heart_2.svg/819px-Playing_card_heart_2.svg.png";
		} else if (card_02.equals("蝝��3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Playing_card_heart_3.svg/819px-Playing_card_heart_3.svg.png";
		} else if (card_02.equals("蝝��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Playing_card_heart_4.svg/819px-Playing_card_heart_4.svg.png";
		} else if (card_02.equals("蝝��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Playing_card_heart_5.svg/819px-Playing_card_heart_5.svg.png";
		} else if (card_02.equals("蝝��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Playing_card_heart_6.svg/819px-Playing_card_heart_6.svg.png";
		} else if (card_02.equals("蝝��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_heart_7.svg/819px-Playing_card_heart_7.svg.png";
		} else if (card_02.equals("蝝��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_8.svg/819px-Playing_card_heart_8.svg.png";
		} else if (card_02.equals("蝝��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_9.svg/819px-Playing_card_heart_9.svg.png";
		} else if (card_02.equals("蝝��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Playing_card_heart_10.svg/819px-Playing_card_heart_10.svg.png";
		} else if (card_02.equals("蝝��11")) {
			url = "https://as1.ftcdn.net/jpg/00/70/12/64/500_F_70126409_gjWU98odB9g2rVgRRl5nMjA7Qyu2EOdl.jpg";
		} else if (card_02.equals("蝝��12")) {
			url = "https://st2.depositphotos.com/2810953/8260/v/950/depositphotos_82602070-stock-illustration-poker-playing-card-queen-heart.jpg";
		} else if (card_02.equals("蝝��13")) {
			url = "https://pic.pimg.tw/wuchicake/1320816325-8500604.jpg";
		}

		else if (card_02.equals("璇1")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Playing_card_club_A.svg/614px-Playing_card_club_A.svg.png";
		} else if (card_02.equals("璇2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/Playing_card_club_2.svg/819px-Playing_card_club_2.svg.png";
		} else if (card_02.equals("璇3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Playing_card_club_3.svg/819px-Playing_card_club_3.svg.png";
		} else if (card_02.equals("璇4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Playing_card_club_4.svg/819px-Playing_card_club_4.svg.png";
		} else if (card_02.equals("璇5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_club_5.svg/819px-Playing_card_club_5.svg.png";
		} else if (card_02.equals("璇6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Playing_card_club_6.svg/819px-Playing_card_club_6.svg.png";
		} else if (card_02.equals("璇7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Playing_card_club_7.svg/819px-Playing_card_club_7.svg.png";
		} else if (card_02.equals("璇8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Playing_card_club_8.svg/819px-Playing_card_club_8.svg.png";
		} else if (card_02.equals("璇9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Playing_card_club_9.svg/819px-Playing_card_club_9.svg.png";
		} else if (card_02.equals("璇10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Playing_card_club_10.svg/819px-Playing_card_club_10.svg.png";
		} else if (card_02.equals("璇11")) {
			url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7R6EehRNz6VHS0nhXo3OgN965rRoesKeqVvUQpuJH-tLFzJZY-w";
		} else if (card_02.equals("璇12")) {
			url = "https://cdn5.vectorstock.com/i/1000x1000/68/74/poker-playing-card-queen-club-vector-8696874.jpg";
		} else if (card_02.equals("璇13")) {
			url = "https://images.freeimages.com/images/premium/previews/7046/7046174-king-of-clubs-two-playing-card.jpg";
		}

		else if (card_02.equals("�憛�1")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Playing_card_diamond_A.svg/819px-Playing_card_diamond_A.svg.png";
		} else if (card_02.equals("�憛�2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Playing_card_diamond_2.svg/819px-Playing_card_diamond_2.svg.png";
		} else if (card_02.equals("�憛�3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Playing_card_diamond_3.svg/819px-Playing_card_diamond_3.svg.png";
		} else if (card_02.equals("�憛�4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Playing_card_diamond_4.svg/819px-Playing_card_diamond_4.svg.png";
		} else if (card_02.equals("�憛�5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Playing_card_diamond_5.svg/819px-Playing_card_diamond_5.svg.png";
		} else if (card_02.equals("�憛�6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Playing_card_diamond_6.svg/819px-Playing_card_diamond_6.svg.png";
		} else if (card_02.equals("�憛�7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Playing_card_diamond_7.svg/819px-Playing_card_diamond_7.svg.png";
		} else if (card_02.equals("�憛�8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Playing_card_diamond_8.svg/819px-Playing_card_diamond_8.svg.png";
		} else if (card_02.equals("�憛�9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Playing_card_diamond_9.svg/614px-Playing_card_diamond_9.svg.png";
		} else if (card_02.equals("�憛�10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Playing_card_diamond_10.svg/819px-Playing_card_diamond_10.svg.png";
		} else if (card_02.equals("�憛�11")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/a/aa/Poker-sm-234-Jd.png";
		} else if (card_02.equals("�憛�12")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/7/70/Poker-sm-233-Qd.png";
		} else if (card_02.equals("�憛�13")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/d/d0/Poker-sm-232-Kd.png";
		}

		java.net.URL where = new URL(url);
		ImageIcon image = new ImageIcon(where);
		Image imgp = image.getImage();
		Image newimage = imgp.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);
		ImageIcon card1 = new ImageIcon(newimage);
		give_card2 = card1;
		bt2.setIcon(card1);

	}

	public void addicon3() throws MalformedURLException {
		if (card_03.equals("暺�")) {
			url = "http://upload2.inven.co.kr/upload/2018/11/01/bbs/i15564270140.png";
		} else if (card_03.equals("暺��2")) {
			url = "https://o.quizlet.com/q6tSCqA.BiCeIodlD5Qv8A.png";
		} else if (card_03.equals("暺��3")) {
			url = "https://cdn.pixabay.com/photo/2015/08/11/11/56/spades-884155_960_720.png";
		} else if (card_03.equals("暺��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Playing_card_spade_4.svg/614px-Playing_card_spade_4.svg.png";
		} else if (card_03.equals("暺��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_spade_5.svg/819px-Playing_card_spade_5.svg.png";
		} else if (card_03.equals("暺��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Playing_card_spade_6.svg/2000px-Playing_card_spade_6.svg.png";
		} else if (card_03.equals("暺��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Playing_card_spade_7.svg/819px-Playing_card_spade_7.svg.png";
		} else if (card_03.equals("暺��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Playing_card_spade_8.svg/819px-Playing_card_spade_8.svg.png";
		} else if (card_03.equals("暺��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Playing_card_spade_9.svg/819px-Playing_card_spade_9.svg.png";
		} else if (card_03.equals("暺��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Playing_card_spade_10.svg/819px-Playing_card_spade_10.svg.png";
		} else if (card_03.equals("暺��11")) {
			url = "https://www.colourbox.com/preview/10382897-jack-of-spades.jpg";
		} else if (card_03.equals("暺��12")) {
			url = "https://i.pinimg.com/originals/64/fe/3a/64fe3aaedd0cd7e7a89f15127bbcc3f4.png";
		} else if (card_03.equals("暺��13")) {
			url = "https://www.colourbox.com/preview/10382907-king-of-spades.jpg";
		}

		else if (card_03.equals("蝝�")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Playing_card_heart_A.svg/1200px-Playing_card_heart_A.svg.png";
		} else if (card_03.equals("蝝��2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Playing_card_heart_2.svg/819px-Playing_card_heart_2.svg.png";
		} else if (card_03.equals("蝝��3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Playing_card_heart_3.svg/819px-Playing_card_heart_3.svg.png";
		} else if (card_03.equals("蝝��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Playing_card_heart_4.svg/819px-Playing_card_heart_4.svg.png";
		} else if (card_03.equals("蝝��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Playing_card_heart_5.svg/819px-Playing_card_heart_5.svg.png";
		} else if (card_03.equals("蝝��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Playing_card_heart_6.svg/819px-Playing_card_heart_6.svg.png";
		} else if (card_03.equals("蝝��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_heart_7.svg/819px-Playing_card_heart_7.svg.png";
		} else if (card_03.equals("蝝��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_8.svg/819px-Playing_card_heart_8.svg.png";
		} else if (card_03.equals("蝝��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_9.svg/819px-Playing_card_heart_9.svg.png";
		} else if (card_03.equals("蝝��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Playing_card_heart_10.svg/819px-Playing_card_heart_10.svg.png";
		} else if (card_03.equals("蝝��11")) {
			url = "https://as1.ftcdn.net/jpg/00/70/12/64/500_F_70126409_gjWU98odB9g2rVgRRl5nMjA7Qyu2EOdl.jpg";
		} else if (card_03.equals("蝝��12")) {
			url = "https://st2.depositphotos.com/2810953/8260/v/950/depositphotos_82602070-stock-illustration-poker-playing-card-queen-heart.jpg";
		} else if (card_03.equals("蝝��13")) {
			url = "https://pic.pimg.tw/wuchicake/1320816325-8500604.jpg";
		}

		else if (card_03.equals("璇A")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Playing_card_club_A.svg/614px-Playing_card_club_A.svg.png";
		} else if (card_03.equals("璇2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/Playing_card_club_2.svg/819px-Playing_card_club_2.svg.png";
		} else if (card_03.equals("璇3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Playing_card_club_3.svg/819px-Playing_card_club_3.svg.png";
		} else if (card_03.equals("璇4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Playing_card_club_4.svg/819px-Playing_card_club_4.svg.png";
		} else if (card_03.equals("璇5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_club_5.svg/819px-Playing_card_club_5.svg.png";
		} else if (card_03.equals("璇6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Playing_card_club_6.svg/819px-Playing_card_club_6.svg.png";
		} else if (card_03.equals("璇7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Playing_card_club_7.svg/819px-Playing_card_club_7.svg.png";
		} else if (card_03.equals("璇8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Playing_card_club_8.svg/819px-Playing_card_club_8.svg.png";
		} else if (card_03.equals("璇9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Playing_card_club_9.svg/819px-Playing_card_club_9.svg.png";
		} else if (card_03.equals("璇10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Playing_card_club_10.svg/819px-Playing_card_club_10.svg.png";
		} else if (card_03.equals("璇11")) {
			url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7R6EehRNz6VHS0nhXo3OgN965rRoesKeqVvUQpuJH-tLFzJZY-w";
		} else if (card_03.equals("璇12")) {
			url = "https://cdn5.vectorstock.com/i/1000x1000/68/74/poker-playing-card-queen-club-vector-8696874.jpg";
		} else if (card_03.equals("璇13")) {
			url = "https://images.freeimages.com/images/premium/previews/7046/7046174-king-of-clubs-two-playing-card.jpg";
		}

		else if (card_03.equals("�憛")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Playing_card_diamond_A.svg/819px-Playing_card_diamond_A.svg.png";
		} else if (card_03.equals("�憛�2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Playing_card_diamond_2.svg/819px-Playing_card_diamond_2.svg.png";
		} else if (card_03.equals("�憛�3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Playing_card_diamond_3.svg/819px-Playing_card_diamond_3.svg.png";
		} else if (card_03.equals("�憛�4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Playing_card_diamond_4.svg/819px-Playing_card_diamond_4.svg.png";
		} else if (card_03.equals("�憛�5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Playing_card_diamond_5.svg/819px-Playing_card_diamond_5.svg.png";
		} else if (card_03.equals("�憛�6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Playing_card_diamond_6.svg/819px-Playing_card_diamond_6.svg.png";
		} else if (card_03.equals("�憛�7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Playing_card_diamond_7.svg/819px-Playing_card_diamond_7.svg.png";
		} else if (card_03.equals("�憛�8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Playing_card_diamond_8.svg/819px-Playing_card_diamond_8.svg.png";
		} else if (card_03.equals("�憛�9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Playing_card_diamond_9.svg/614px-Playing_card_diamond_9.svg.png";
		} else if (card_03.equals("�憛�10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Playing_card_diamond_10.svg/819px-Playing_card_diamond_10.svg.png";
		} else if (card_03.equals("�憛�11")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/a/aa/Poker-sm-234-Jd.png";
		} else if (card_03.equals("�憛�12")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/7/70/Poker-sm-233-Qd.png";
		} else if (card_03.equals("�憛�13")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/d/d0/Poker-sm-232-Kd.png";
		}

		java.net.URL where = new URL(url);
		ImageIcon image = new ImageIcon(where);
		Image imgp = image.getImage();
		Image newimage = imgp.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);
		ImageIcon card1 = new ImageIcon(newimage);
		give_card3 = card1;
		bt3.setIcon(card1);

	}

	public void addicon4() throws MalformedURLException {
		if (card_04.equals("暺�")) {
			url = "http://upload2.inven.co.kr/upload/2018/11/01/bbs/i15564270140.png";
		} else if (card_04.equals("暺��2")) {
			url = "https://o.quizlet.com/q6tSCqA.BiCeIodlD5Qv8A.png";
		} else if (card_04.equals("暺��3")) {
			url = "https://cdn.pixabay.com/photo/2015/08/11/11/56/spades-884155_960_720.png";
		} else if (card_04.equals("暺��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Playing_card_spade_4.svg/614px-Playing_card_spade_4.svg.png";
		} else if (card_04.equals("暺��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_spade_5.svg/819px-Playing_card_spade_5.svg.png";
		} else if (card_04.equals("暺��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Playing_card_spade_6.svg/2000px-Playing_card_spade_6.svg.png";
		} else if (card_04.equals("暺��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Playing_card_spade_7.svg/819px-Playing_card_spade_7.svg.png";
		} else if (card_04.equals("暺��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Playing_card_spade_8.svg/819px-Playing_card_spade_8.svg.png";
		} else if (card_04.equals("暺��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Playing_card_spade_9.svg/819px-Playing_card_spade_9.svg.png";
		} else if (card_04.equals("暺��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Playing_card_spade_10.svg/819px-Playing_card_spade_10.svg.png";
		} else if (card_04.equals("暺��11")) {
			url = "https://www.colourbox.com/preview/10382897-jack-of-spades.jpg";
		} else if (card_04.equals("暺��12")) {
			url = "https://i.pinimg.com/originals/64/fe/3a/64fe3aaedd0cd7e7a89f15127bbcc3f4.png";
		} else if (card_04.equals("暺��13")) {
			url = "https://www.colourbox.com/preview/10382907-king-of-spades.jpg";
		}

		else if (card_04.equals("蝝�")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Playing_card_heart_A.svg/1200px-Playing_card_heart_A.svg.png";
		} else if (card_04.equals("蝝��2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Playing_card_heart_2.svg/819px-Playing_card_heart_2.svg.png";
		} else if (card_04.equals("蝝��3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Playing_card_heart_3.svg/819px-Playing_card_heart_3.svg.png";
		} else if (card_04.equals("蝝��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Playing_card_heart_4.svg/819px-Playing_card_heart_4.svg.png";
		} else if (card_04.equals("蝝��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Playing_card_heart_5.svg/819px-Playing_card_heart_5.svg.png";
		} else if (card_04.equals("蝝��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Playing_card_heart_6.svg/819px-Playing_card_heart_6.svg.png";
		} else if (card_04.equals("蝝��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_heart_7.svg/819px-Playing_card_heart_7.svg.png";
		} else if (card_04.equals("蝝��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_8.svg/819px-Playing_card_heart_8.svg.png";
		} else if (card_04.equals("蝝��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_9.svg/819px-Playing_card_heart_9.svg.png";
		} else if (card_04.equals("蝝��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Playing_card_heart_10.svg/819px-Playing_card_heart_10.svg.png";
		} else if (card_04.equals("蝝��11")) {
			url = "https://as1.ftcdn.net/jpg/00/70/12/64/500_F_70126409_gjWU98odB9g2rVgRRl5nMjA7Qyu2EOdl.jpg";
		} else if (card_04.equals("蝝��12")) {
			url = "https://st2.depositphotos.com/2810953/8260/v/950/depositphotos_82602070-stock-illustration-poker-playing-card-queen-heart.jpg";
		} else if (card_04.equals("蝝��13")) {
			url = "https://pic.pimg.tw/wuchicake/1320816325-8500604.jpg";
		}

		else if (card_04.equals("璇A")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Playing_card_club_A.svg/614px-Playing_card_club_A.svg.png";
		} else if (card_04.equals("璇2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/Playing_card_club_2.svg/819px-Playing_card_club_2.svg.png";
		} else if (card_04.equals("璇3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Playing_card_club_3.svg/819px-Playing_card_club_3.svg.png";
		} else if (card_04.equals("璇4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Playing_card_club_4.svg/819px-Playing_card_club_4.svg.png";
		} else if (card_04.equals("璇5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_club_5.svg/819px-Playing_card_club_5.svg.png";
		} else if (card_04.equals("璇6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Playing_card_club_6.svg/819px-Playing_card_club_6.svg.png";
		} else if (card_04.equals("璇7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Playing_card_club_7.svg/819px-Playing_card_club_7.svg.png";
		} else if (card_04.equals("璇8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Playing_card_club_8.svg/819px-Playing_card_club_8.svg.png";
		} else if (card_04.equals("璇9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Playing_card_club_9.svg/819px-Playing_card_club_9.svg.png";
		} else if (card_04.equals("璇10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Playing_card_club_10.svg/819px-Playing_card_club_10.svg.png";
		} else if (card_04.equals("璇11")) {
			url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7R6EehRNz6VHS0nhXo3OgN965rRoesKeqVvUQpuJH-tLFzJZY-w";
		} else if (card_04.equals("璇12")) {
			url = "https://cdn5.vectorstock.com/i/1000x1000/68/74/poker-playing-card-queen-club-vector-8696874.jpg";
		} else if (card_04.equals("璇13")) {
			url = "https://images.freeimages.com/images/premium/previews/7046/7046174-king-of-clubs-two-playing-card.jpg";
		}

		else if (card_04.equals("�憛")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Playing_card_diamond_A.svg/819px-Playing_card_diamond_A.svg.png";
		} else if (card_04.equals("�憛�2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Playing_card_diamond_2.svg/819px-Playing_card_diamond_2.svg.png";
		} else if (card_04.equals("�憛�3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Playing_card_diamond_3.svg/819px-Playing_card_diamond_3.svg.png";
		} else if (card_04.equals("�憛�4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Playing_card_diamond_4.svg/819px-Playing_card_diamond_4.svg.png";
		} else if (card_04.equals("�憛�5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Playing_card_diamond_5.svg/819px-Playing_card_diamond_5.svg.png";
		} else if (card_04.equals("�憛�6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Playing_card_diamond_6.svg/819px-Playing_card_diamond_6.svg.png";
		} else if (card_04.equals("�憛�7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Playing_card_diamond_7.svg/819px-Playing_card_diamond_7.svg.png";
		} else if (card_04.equals("�憛�8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Playing_card_diamond_8.svg/819px-Playing_card_diamond_8.svg.png";
		} else if (card_04.equals("�憛�9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Playing_card_diamond_9.svg/614px-Playing_card_diamond_9.svg.png";
		} else if (card_04.equals("�憛�10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Playing_card_diamond_10.svg/819px-Playing_card_diamond_10.svg.png";
		} else if (card_04.equals("�憛�11")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/a/aa/Poker-sm-234-Jd.png";
		} else if (card_04.equals("�憛�12")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/7/70/Poker-sm-233-Qd.png";
		} else if (card_04.equals("�憛�13")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/d/d0/Poker-sm-232-Kd.png";
		}

		java.net.URL where = new URL(url);
		ImageIcon image = new ImageIcon(where);
		Image imgp = image.getImage();
		Image newimage = imgp.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);
		ImageIcon card1 = new ImageIcon(newimage);
		give_card4 = card1;
		bt4.setIcon(card1);

	}

	public void addicon5() throws MalformedURLException {
		if (card_05.equals("暺�")) {
			url = "http://upload2.inven.co.kr/upload/2018/11/01/bbs/i15564270140.png";
		} else if (card_05.equals("暺��2")) {
			url = "https://o.quizlet.com/q6tSCqA.BiCeIodlD5Qv8A.png";
		} else if (card_05.equals("暺��3")) {
			url = "https://cdn.pixabay.com/photo/2015/08/11/11/56/spades-884155_960_720.png";
		} else if (card_05.equals("暺��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Playing_card_spade_4.svg/614px-Playing_card_spade_4.svg.png";
		} else if (card_05.equals("暺��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_spade_5.svg/819px-Playing_card_spade_5.svg.png";
		} else if (card_05.equals("暺��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Playing_card_spade_6.svg/2000px-Playing_card_spade_6.svg.png";
		} else if (card_05.equals("暺��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Playing_card_spade_7.svg/819px-Playing_card_spade_7.svg.png";
		} else if (card_05.equals("暺��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Playing_card_spade_8.svg/819px-Playing_card_spade_8.svg.png";
		} else if (card_05.equals("暺��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Playing_card_spade_9.svg/819px-Playing_card_spade_9.svg.png";
		} else if (card_05.equals("暺��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Playing_card_spade_10.svg/819px-Playing_card_spade_10.svg.png";
		} else if (card_05.equals("暺��11")) {
			url = "https://www.colourbox.com/preview/10382897-jack-of-spades.jpg";
		} else if (card_05.equals("暺��12")) {
			url = "https://i.pinimg.com/originals/64/fe/3a/64fe3aaedd0cd7e7a89f15127bbcc3f4.png";
		} else if (card_05.equals("暺��13")) {
			url = "https://www.colourbox.com/preview/10382907-king-of-spades.jpg";
		}

		else if (card_05.equals("蝝�")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Playing_card_heart_A.svg/1200px-Playing_card_heart_A.svg.png";
		} else if (card_05.equals("蝝��2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Playing_card_heart_2.svg/819px-Playing_card_heart_2.svg.png";
		} else if (card_05.equals("蝝��3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Playing_card_heart_3.svg/819px-Playing_card_heart_3.svg.png";
		} else if (card_05.equals("蝝��4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Playing_card_heart_4.svg/819px-Playing_card_heart_4.svg.png";
		} else if (card_05.equals("蝝��5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Playing_card_heart_5.svg/819px-Playing_card_heart_5.svg.png";
		} else if (card_05.equals("蝝��6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Playing_card_heart_6.svg/819px-Playing_card_heart_6.svg.png";
		} else if (card_05.equals("蝝��7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Playing_card_heart_7.svg/819px-Playing_card_heart_7.svg.png";
		} else if (card_05.equals("蝝��8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_8.svg/819px-Playing_card_heart_8.svg.png";
		} else if (card_05.equals("蝝��9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_heart_9.svg/819px-Playing_card_heart_9.svg.png";
		} else if (card_05.equals("蝝��10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Playing_card_heart_10.svg/819px-Playing_card_heart_10.svg.png";
		} else if (card_05.equals("蝝��11")) {
			url = "https://as1.ftcdn.net/jpg/00/70/12/64/500_F_70126409_gjWU98odB9g2rVgRRl5nMjA7Qyu2EOdl.jpg";
		} else if (card_05.equals("蝝��12")) {
			url = "https://st2.depositphotos.com/2810953/8260/v/950/depositphotos_82602070-stock-illustration-poker-playing-card-queen-heart.jpg";
		} else if (card_05.equals("蝝��13")) {
			url = "https://pic.pimg.tw/wuchicake/1320816325-8500604.jpg";
		}

		else if (card_05.equals("璇A")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Playing_card_club_A.svg/614px-Playing_card_club_A.svg.png";
		} else if (card_05.equals("璇2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/Playing_card_club_2.svg/819px-Playing_card_club_2.svg.png";
		} else if (card_05.equals("璇3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Playing_card_club_3.svg/819px-Playing_card_club_3.svg.png";
		} else if (card_05.equals("璇4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Playing_card_club_4.svg/819px-Playing_card_club_4.svg.png";
		} else if (card_05.equals("璇5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Playing_card_club_5.svg/819px-Playing_card_club_5.svg.png";
		} else if (card_05.equals("璇6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Playing_card_club_6.svg/819px-Playing_card_club_6.svg.png";
		} else if (card_05.equals("璇7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Playing_card_club_7.svg/819px-Playing_card_club_7.svg.png";
		} else if (card_05.equals("璇8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Playing_card_club_8.svg/819px-Playing_card_club_8.svg.png";
		} else if (card_05.equals("璇9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Playing_card_club_9.svg/819px-Playing_card_club_9.svg.png";
		} else if (card_05.equals("璇10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Playing_card_club_10.svg/819px-Playing_card_club_10.svg.png";
		} else if (card_05.equals("璇11")) {
			url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7R6EehRNz6VHS0nhXo3OgN965rRoesKeqVvUQpuJH-tLFzJZY-w";
		} else if (card_05.equals("璇12")) {
			url = "https://cdn5.vectorstock.com/i/1000x1000/68/74/poker-playing-card-queen-club-vector-8696874.jpg";
		} else if (card_05.equals("璇13")) {
			url = "https://images.freeimages.com/images/premium/previews/7046/7046174-king-of-clubs-two-playing-card.jpg";
		}

		else if (card_05.equals("�憛")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Playing_card_diamond_A.svg/819px-Playing_card_diamond_A.svg.png";
		} else if (card_05.equals("�憛�2")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Playing_card_diamond_2.svg/819px-Playing_card_diamond_2.svg.png";
		} else if (card_05.equals("�憛�3")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Playing_card_diamond_3.svg/819px-Playing_card_diamond_3.svg.png";
		} else if (card_05.equals("�憛�4")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Playing_card_diamond_4.svg/819px-Playing_card_diamond_4.svg.png";
		} else if (card_05.equals("�憛�5")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Playing_card_diamond_5.svg/819px-Playing_card_diamond_5.svg.png";
		} else if (card_05.equals("�憛�6")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Playing_card_diamond_6.svg/819px-Playing_card_diamond_6.svg.png";
		} else if (card_05.equals("�憛�7")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Playing_card_diamond_7.svg/819px-Playing_card_diamond_7.svg.png";
		} else if (card_05.equals("�憛�8")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Playing_card_diamond_8.svg/819px-Playing_card_diamond_8.svg.png";
		} else if (card_05.equals("�憛�9")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Playing_card_diamond_9.svg/614px-Playing_card_diamond_9.svg.png";
		} else if (card_05.equals("�憛�10")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Playing_card_diamond_10.svg/819px-Playing_card_diamond_10.svg.png";
		} else if (card_05.equals("�憛�11")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/a/aa/Poker-sm-234-Jd.png";
		} else if (card_05.equals("�憛�12")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/7/70/Poker-sm-233-Qd.png";
		} else if (card_05.equals("�憛�13")) {
			url = "https://upload.wikimedia.org/wikipedia/commons/d/d0/Poker-sm-232-Kd.png";
		}

		java.net.URL where = new URL(url);
		ImageIcon image = new ImageIcon(where);
		Image imgp = image.getImage();
		Image newimage = imgp.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);
		ImageIcon card1 = new ImageIcon(newimage);
		give_card5 = card1;
		bt5.setIcon(card1);

	}

	// ������隞�暻�
	public void actionPerformed(ActionEvent e) {
		if ((e.getActionCommand().equals("璇1")) || (e.getActionCommand().equals("�憛�1"))
				|| (e.getActionCommand().equals("蝝��1")) || (e.getActionCommand().equals("暺��1"))) {
			function_A();

		} else if ((e.getActionCommand().equals("璇2")) || (e.getActionCommand().equals("�憛�2"))
				|| (e.getActionCommand().equals("蝝��2")) || (e.getActionCommand().equals("暺��2"))) {
			function_2();
		} else if ((e.getActionCommand().equals("璇3")) || (e.getActionCommand().equals("�憛�3"))
				|| (e.getActionCommand().equals("蝝��3")) || (e.getActionCommand().equals("暺��3"))) {
			function_3();
		} else if ((e.getActionCommand().equals("璇4")) || (e.getActionCommand().equals("�憛�4"))
				|| (e.getActionCommand().equals("蝝��4")) || (e.getActionCommand().equals("暺��4"))) {
			function_4();
		} else if ((e.getActionCommand().equals("璇5")) || (e.getActionCommand().equals("�憛�5"))
				|| (e.getActionCommand().equals("蝝��5")) || (e.getActionCommand().equals("暺��5"))) {
			function_5();
		} else if ((e.getActionCommand().equals("璇6")) || (e.getActionCommand().equals("�憛�6"))
				|| (e.getActionCommand().equals("蝝��6")) || (e.getActionCommand().equals("暺��6"))) {
			function_6();
		} else if ((e.getActionCommand().equals("璇7")) || (e.getActionCommand().equals("�憛�7"))
				|| (e.getActionCommand().equals("蝝��7")) || (e.getActionCommand().equals("暺��7"))) {
			function_7();
		} else if ((e.getActionCommand().equals("璇8")) || (e.getActionCommand().equals("�憛�8"))
				|| (e.getActionCommand().equals("蝝��8")) || (e.getActionCommand().equals("暺��8"))) {
			function_8();
		} else if ((e.getActionCommand().equals("璇9")) || (e.getActionCommand().equals("�憛�9"))
				|| (e.getActionCommand().equals("蝝��9")) || (e.getActionCommand().equals("暺��9"))) {
			function_9();
		} else if ((e.getActionCommand().equals("璇10")) || (e.getActionCommand().equals("�憛�10"))
				|| (e.getActionCommand().equals("蝝��10")) || (e.getActionCommand().equals("暺��10"))) {
			function_10();
		} else if ((e.getActionCommand().equals("璇11")) || (e.getActionCommand().equals("�憛�11"))
				|| (e.getActionCommand().equals("蝝��11")) || (e.getActionCommand().equals("暺��11"))) {
			function_J();
		} else if ((e.getActionCommand().equals("璇12")) || (e.getActionCommand().equals("�憛�12"))
				|| (e.getActionCommand().equals("蝝��12")) || (e.getActionCommand().equals("暺��12"))) {
			function_Q();
		} else if ((e.getActionCommand().equals("璇13")) || (e.getActionCommand().equals("�憛�13"))
				|| (e.getActionCommand().equals("蝝��13")) || (e.getActionCommand().equals("暺��13"))) {
			function_K();
		} else if (e.getActionCommand().equals("+")) {
			function_positive();
		} else if (e.getActionCommand().equals("-")) {
			function_negetive();
		} else if (e.getActionCommand().equals("P1")) {
			function_p1();
		//	judge_player_when_use5 = 1;
	//		function_show_player();
		} else if (e.getActionCommand().equals("P2")) {
			function_p2();
		//	judge_player_when_use5 = 2;
	//		function_show_player();
		} else if (e.getActionCommand().equals("P3")) {
			function_p3();
		//	judge_player_when_use5 = 3;
		//	function_show_player();
		}

		if ((e.getSource().equals(bt1))) {
			card_01 = String.valueOf(cardbase.get(counts_cards));
			bt1.setText(String.valueOf(cardbase.get(counts_cards)));
			counts_cards++;
			bt11.setIcon(give_card);
			try {
				addicon();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if ((e.getSource().equals(bt2))) {
			card_02 = String.valueOf(cardbase.get(counts_cards));
			bt2.setText(String.valueOf(cardbase.get(counts_cards)));
			counts_cards++;
			bt11.setIcon(give_card2);
			try {
				addicon2();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if ((e.getSource().equals(bt3))) {
			card_03 = String.valueOf(cardbase.get(counts_cards));
			bt3.setText(String.valueOf(cardbase.get(counts_cards)));
			counts_cards++;
			bt11.setIcon(give_card3);
			try {
				addicon3();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if ((e.getSource().equals(bt4))) {
			card_04 = String.valueOf(cardbase.get(counts_cards));
			bt4.setText(String.valueOf(cardbase.get(counts_cards)));
			counts_cards++;
			bt11.setIcon(give_card4);
			try {
				addicon4();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if ((e.getSource().equals(bt5))) {
			card_05 = String.valueOf(cardbase.get(counts_cards));
			bt5.setText(String.valueOf(cardbase.get(counts_cards)));
			counts_cards++;
			bt11.setIcon(give_card5);
			try {
				addicon5();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// implement button listener
	}

	class ReceiveMsg extends Thread {
		JLabel jlabel, jlabel2, jlabel3;
		BufferedReader receiveBroadcastMsg;

		public ReceiveMsg(JLabel jlabel, JLabel jlabel2, JLabel jlabel3, Socket connectionSock) {
			this.jlabel = jlabel;
			this.jlabel2 = jlabel2;
			this.jlabel3 = jlabel3;
			try {
				this.receiveBroadcastMsg = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void run() {
			String waitBroadcastMsg;
			String waitBroadcastMsg2;
			String waitBroadcastMsg3;
			while (true) {
				try {

					waitBroadcastMsg = receiveBroadcastMsg.readLine();
					waitBroadcastMsg2 = receiveBroadcastMsg.readLine();
					waitBroadcastMsg3 = receiveBroadcastMsg.readLine();
					// System.out.println("wtfwtf");
					jlabel.setText(waitBroadcastMsg);
					 System.out.println(waitBroadcastMsg+":11");
					jlabel2.setText("��layer" + waitBroadcastMsg2);
					 System.out.println(waitBroadcastMsg2+":22");
					jlabel3.setText(waitBroadcastMsg3);
					 System.out.println(waitBroadcastMsg3+"33");
	// 憿舐內������jlabel
					// jlabel.setText(waitBroadcastMsg);
					// System.out.println(waitBroadcastMsg);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}