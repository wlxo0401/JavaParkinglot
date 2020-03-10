package Parkinglot;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class Help extends JFrame implements ActionListener{
	

	private JPanel UpPan;
	private JButton btnPrice;
	private JButton btnHow;
	private JButton btnExit;
	private ImageIcon img01;
	private JLabel imglbl01;
	private JPanel CardPane,CenterPanel1, CenterPanel2;
	private CardLayout Card;
	private ImageIcon img02;
	private JLabel imglbl02;
	
	
	public Help(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
		setBackground(new Color(89,89,89));
		Up();
		Down();
	
		ImageIcon img = new ImageIcon("img/help/helpexitbtnrol.png");
		ImageIcon imgrol = new ImageIcon("img/help/helpexitbtn.png");
		ImageIcon imgpre = new ImageIcon("img/help/helpexitbtnpre.png");
		
		btnExit = new JButton(img);
		btnExit.setRolloverIcon(imgrol);
		btnExit.setPressedIcon(imgpre);
		btnExit.setBorderPainted(false); //JButton의 Border(외곽선)을 없애준다.    
		btnExit.setFocusPainted(false); //JButton의 내용영역 채우기 안함
		btnExit.setContentAreaFilled(false); //JButton이 선택(focus)되었을때 생기는 테두리 사용안함
		btnExit.setOpaque(false);
		btnExit.addActionListener(this);
		setUndecorated(true);
		
		add(UpPan, BorderLayout.NORTH);
		add(CardPane,BorderLayout.CENTER);
		add(btnExit, BorderLayout.SOUTH);
		setVisible(true);
		
	}


	private void Up() {
		ImageIcon img = new ImageIcon("img/help/helelrol.png");
		ImageIcon imgrol = new ImageIcon("img/help/helel.png");
		ImageIcon imgpre = new ImageIcon("img/help/helelpre.png");
		
		ImageIcon img2 = new ImageIcon("img/help/helphelprol.png");
		ImageIcon img2rol = new ImageIcon("img/help/helphelp.png");
		ImageIcon img2pre = new ImageIcon("img/help/helphelppre.png");
		
		UpPan = new JPanel();
		UpPan.setBackground(Color.white);
		UpPan.setLayout(new GridLayout(1, 2));
		
		btnPrice = new JButton(img);
		btnPrice.setRolloverIcon(imgrol);
		btnPrice.setPressedIcon(imgpre);
		btnPrice.setBorderPainted(false); //JButton의 Border(외곽선)을 없애준다.    
		btnPrice.setFocusPainted(false); //JButton의 내용영역 채우기 안함
		btnPrice.setContentAreaFilled(false); //JButton이 선택(focus)되었을때 생기는 테두리 사용안함
		btnPrice.setOpaque(false);
		btnPrice.addActionListener(this);

		
		btnHow = new JButton(img2);
		btnHow.setRolloverIcon(img2rol);
		btnHow.setPressedIcon(img2pre);
		btnHow.setBorderPainted(false); //JButton의 Border(외곽선)을 없애준다.    
		btnHow.setFocusPainted(false); //JButton의 내용영역 채우기 안함
		btnHow.setContentAreaFilled(false); //JButton이 선택(focus)되었을때 생기는 테두리 사용안함
		btnHow.setOpaque(false);
		btnHow.addActionListener(this);
		
		UpPan.add(btnPrice);
		UpPan.add(btnHow);
		
	
		
	}


	private void Down() {
		
		Card = new CardLayout();//카드레이아웃이용해서 가운데 내용전환
		CardPane = new JPanel();
		CardPane.setLayout(Card);
		CardPane.setBackground(Color.white);
		
		CenterPanel1 =new JPanel();
		CenterPanel1.setBackground(Color.WHITE);
		CenterPanel2 =new JPanel();
		CenterPanel2.setBackground(Color.white);
		
		img01 = new ImageIcon("img/PriceList.PNG");
		img02 = new ImageIcon("img/help.png");
		imglbl01 = new JLabel(img01);
		imglbl02 = new JLabel(img02);
		CenterPanel1.add(imglbl01);
		CenterPanel2.add(imglbl02);
		CardPane.add(CenterPanel1,"price");//각 패널별로 이름설정
		CardPane.add(CenterPanel2,"HowToUse");
		
	
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnExit)
		{
			setVisible(false);
		}
		else if(obj == btnPrice)
		{
			Card.show( CardPane, "price");
			
		}
		else if(obj == btnHow) {
			Card.show( CardPane, "HowToUse");
			
		}
				
		
	}


}
