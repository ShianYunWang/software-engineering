package blood99;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class blood99 extends JFrame implements ActionListener{
	
	private static ArrayList cardbase = new ArrayList() ;//牌庫
	private int judge_ten_or_twenty;//判斷10或20
	private int judge_times_of_card4=1;//看迴轉用幾次
	private int judge_whether_use_card5=0;//看有沒有用指定
	private int judge_player=1;//判斷換誰出牌
	private int counts_cards=5;//發牌用ㄉ 拿完手牌的
	private int judge_pos_neg ;//判斷正負 用在+10-10
	private int total_number_int; //用在顯示器計算
	private String total_number_str="0";//顯示器
	private static String card_01;//BUTTON上的字 初始手牌
	private static String card_02;
	private static String card_03;
	private static String card_04;
	private static String card_05;
	private JLabel show_number; //顯示現在數字多少(上面的總數
	private JLabel show_function;	// 顯示用了哪些功能牌(下面的
	private JLabel show_player;	// 顯示換誰
	private JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10; 
	public static void main(String[] args) {

		///////////////////////////發牌洗牌用(以下)
		  Final pk = new Final();
		  pk.createCard();
		  for (int i = 0; i < 13; i++) {
		   for (int j = 0; j < 4; j++) {
		    pk.createdetail(i, j);
		   }
		  }
		  //pk.printdetail();
		  for (int k=0;k<52;k++) {
			  cardbase.add(pk.cardList().get(k));
		  }
		  System.out.println(pk.cardList());		  
		  System.out.println(cardbase);
		  System.out.println(cardbase.get(0));
		  card_01=(String) cardbase.get(0);
		  card_02=(String) cardbase.get(1);
		  card_03=(String) cardbase.get(2);
		  card_04=(String) cardbase.get(3);
		  card_05=(String) cardbase.get(4);

		///////////////////////////發牌洗牌用(以上)
		  
		  //建立FRAME
		  blood99 frame = new blood99();
			frame.setTitle("Player1");//改名字
			frame.setVisible(true);
			frame.setResizable(false);
	}
	
	public blood99(){
		
		
		
		//frame相關參數
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//顯示總數
		show_number = new JLabel(total_number_str);
		show_number.setSize(200, 200);
		show_number.setLocation(350, 0);
		show_number.setFont(new java.awt.Font("Dialog", 1, 100));
		//顯示功能
		show_function = new JLabel("開始!");
		show_function.setSize(200, 200);
		show_function.setLocation(200, 200);
		show_function.setFont(new java.awt.Font("Dialog", 1, 30));
		//顯示換誰
		show_player = new JLabel("換Player"+judge_player);
		show_player.setSize(200, 200);
		show_player.setLocation(500, 200);
		show_player.setFont(new java.awt.Font("Dialog", 1, 30));
		//設置按鈕
		//五張撲克牌
		bt1 = new JButton(card_01);
		bt2 = new JButton(card_02);
		bt3 = new JButton(card_03);
		bt4 = new JButton(card_04);
		bt5 = new JButton(card_05);
		bt1.setBounds(50,430,80,120);
		bt2.setBounds(150,430,80,120);
		bt3.setBounds(250,430,80,120);
		bt4.setBounds(350,430,80,120);
		bt5.setBounds(450,430,80,120);
		//判斷+10or-10
		bt6 = new JButton("+");
		bt7 = new JButton("-");
		bt6.setBounds(565,430,50,50);
		bt7.setBounds(565,500,50,50);
		bt8 = new JButton("P1");
		bt9 = new JButton("P2");
		bt10= new JButton("P3");
		bt8.setBounds(650,430,50,30);
		bt9.setBounds(650,470,50,30);
		bt10.setBounds(650,510,50,30);
		
		//計算PLAYER
		
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
		// 把東西加到frame裡面
		this.add(bt1);
		this.add(bt2);
		this.add(bt3);
		this.add(bt4);
		this.add(bt5);
		this.add(bt6);
		this.add(bt7);
		this.add(bt8);
		this.add(bt9);
		this.add(bt10);
		this.add(show_number);
		this.add(show_player);
		this.add(show_function);
		
	}
		//看換誰
		public void function_show_player() {
			function_check_card5();
			if (judge_player>=4) {
				judge_player=1;
			}
			else if (judge_player<=0) {
				judge_player=3;
			}
			show_player.setText("換Player"+judge_player);
			
		}
		//看有沒有用過5
		public void function_check_card5() {
			if(judge_whether_use_card5==0) {//沒有用指定
				function_check_card4();
			}
			else if(judge_whether_use_card5%2==1) {//有用指定
				judge_whether_use_card5--;

			}
		}
		//看有沒有用過4
		public void function_check_card4() {
				if(judge_times_of_card4%2==1) {//用了偶數次迴轉
					judge_player++;//換下一個人
				}
				else if(judge_times_of_card4%2==0) {//用了奇數次迴轉
					judge_player--;//換上一個人
				}
			
			}

		//檢查有沒有超過99
		public void function_check() {
			if (Integer.valueOf(show_number.getText())>99) {
				show_number.setText("99");
				total_number_str="99";
				total_number_int=99;
				show_function.setText("YOU LOSE");
		}
		}
		//判別出的牌是哪一張
		public void function_A() {
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int+=1;
			show_function.setText("+1");
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		public void function_2() {
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int+=2;
			show_function.setText("+2");
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		public void function_3() {
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int+=3;
			show_function.setText("+3");
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		public void function_4() {
			show_function.setText("迴轉!");//印在顯示器上
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
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int+=6;
			show_function.setText("+6");
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		public void function_7() {
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int+=7;
			show_function.setText("+7");
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		public void function_8() {
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int+=8;
			show_function.setText("+8");
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		public void function_9() {
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int+=9;
			show_function.setText("+9");
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		public void function_10() {
			judge_ten_or_twenty=10;
			show_function.setText("+-10");
		}
		public void function_J() {
			show_function.setText("PASS");
			function_check();
			function_show_player();
		}
		public void function_Q() {
			judge_ten_or_twenty=20;
			show_function.setText("+-20");
		}
		public void function_K() {
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int=99;
			show_function.setText("玖拾玖");
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		//判別用加十還是減十
		public void function_positive() {
			judge_pos_neg=1;
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int+=judge_ten_or_twenty*judge_pos_neg;
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		public void function_negetive() {
			judge_pos_neg=-1;
			total_number_int = Integer.valueOf(total_number_str); //把顯示的數字轉成整數做計算
			total_number_int+=judge_ten_or_twenty*judge_pos_neg;
			total_number_str= String.valueOf(total_number_int);//再把數字轉成字串
			show_number.setText(total_number_str);//印在顯示器上
			function_check();
			function_show_player();
		}
		
		// 判斷出的牌是什麼
		public void actionPerformed (ActionEvent e) {
				
							
				//判斷出哪一張牌
				if ((e.getActionCommand().equals("梅花1"))||(e.getActionCommand().equals("方塊1"))||(e.getActionCommand().equals("紅心1"))||(e.getActionCommand().equals("黑桃1"))){
					function_A();
				}
				else if ((e.getActionCommand().equals("梅花2"))||(e.getActionCommand().equals("方塊2"))||(e.getActionCommand().equals("紅心2"))||(e.getActionCommand().equals("黑桃2"))){
					function_2();
				}
				else if ((e.getActionCommand().equals("梅花3"))||(e.getActionCommand().equals("方塊3"))||(e.getActionCommand().equals("紅心3"))||(e.getActionCommand().equals("黑桃3"))){
					function_3();
				}
				else if ((e.getActionCommand().equals("梅花4"))||(e.getActionCommand().equals("方塊4"))||(e.getActionCommand().equals("紅心4"))||(e.getActionCommand().equals("黑桃4"))){
					function_4();
				}
				else if ((e.getActionCommand().equals("梅花5"))||(e.getActionCommand().equals("方塊5"))||(e.getActionCommand().equals("紅心5"))||(e.getActionCommand().equals("黑桃5"))){
					function_5();
				}
				else if ((e.getActionCommand().equals("梅花6"))||(e.getActionCommand().equals("方塊6"))||(e.getActionCommand().equals("紅心6"))||(e.getActionCommand().equals("黑桃6"))){
					function_6();
				}
				else if ((e.getActionCommand().equals("梅花7"))||(e.getActionCommand().equals("方塊7"))||(e.getActionCommand().equals("紅心7"))||(e.getActionCommand().equals("黑桃7"))){
					function_7();
				}
				else if ((e.getActionCommand().equals("梅花8"))||(e.getActionCommand().equals("方塊8"))||(e.getActionCommand().equals("紅心8"))||(e.getActionCommand().equals("黑桃8"))){
					function_8();
				}
				else if ((e.getActionCommand().equals("梅花9"))||(e.getActionCommand().equals("方塊9"))||(e.getActionCommand().equals("紅心9"))||(e.getActionCommand().equals("黑桃9"))){
					function_9();
				}
				else if ((e.getActionCommand().equals("梅花10"))||(e.getActionCommand().equals("方塊10"))||(e.getActionCommand().equals("紅心10"))||(e.getActionCommand().equals("黑桃10"))){
					function_10();
				}
				else if ((e.getActionCommand().equals("梅花11"))||(e.getActionCommand().equals("方塊11"))||(e.getActionCommand().equals("紅心11"))||(e.getActionCommand().equals("黑桃11"))){
					function_J();
				}
				else if ((e.getActionCommand().equals("梅花12"))||(e.getActionCommand().equals("方塊12"))||(e.getActionCommand().equals("紅心12"))||(e.getActionCommand().equals("黑桃12"))){
					function_Q();
				}
				else if ( (e.getActionCommand().equals("梅花13"))||(e.getActionCommand().equals("方塊13"))||(e.getActionCommand().equals("紅心13"))||(e.getActionCommand().equals("黑桃13"))){
					function_K();
				}
				else if ( e.getActionCommand().equals("+")){
					function_positive();
				}
				else if ( e.getActionCommand().equals("-")){
					function_negetive();
				}
				else if ( e.getActionCommand().equals("P1")){
					judge_player=1;
					function_show_player();
				}
				else if ( e.getActionCommand().equals("P2")){
					judge_player=2;
					function_show_player();
				}
				else if ( e.getActionCommand().equals("P3")){
					judge_player=3;
					function_show_player();
				}
				//出牌後要抽牌
				if ((e.getSource().equals(bt1))){
					bt1.setText(String.valueOf(cardbase.get(counts_cards)));
					counts_cards++;
					
				}
				else if ((e.getSource().equals(bt2))){
					bt2.setText(String.valueOf(cardbase.get(counts_cards)));
					counts_cards++;
					
				}
				else if ((e.getSource().equals(bt3))){
					bt3.setText(String.valueOf(cardbase.get(counts_cards)));
					counts_cards++;
					
				}
				else if ((e.getSource().equals(bt4))){
					bt4.setText(String.valueOf(cardbase.get(counts_cards)));
					counts_cards++;
					
				}
				else if ((e.getSource().equals(bt5))){
					bt5.setText(String.valueOf(cardbase.get(counts_cards)));
					counts_cards++;
					
				}
			// implement button listener
		}
	
}

