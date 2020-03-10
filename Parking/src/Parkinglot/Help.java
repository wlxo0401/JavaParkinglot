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
		btnExit.setBorderPainted(false); //JButton�� Border(�ܰ���)�� �����ش�.    
		btnExit.setFocusPainted(false); //JButton�� ���뿵�� ä��� ����
		btnExit.setContentAreaFilled(false); //JButton�� ����(focus)�Ǿ����� ����� �׵θ� ������
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
		btnPrice.setBorderPainted(false); //JButton�� Border(�ܰ���)�� �����ش�.    
		btnPrice.setFocusPainted(false); //JButton�� ���뿵�� ä��� ����
		btnPrice.setContentAreaFilled(false); //JButton�� ����(focus)�Ǿ����� ����� �׵θ� ������
		btnPrice.setOpaque(false);
		btnPrice.addActionListener(this);

		
		btnHow = new JButton(img2);
		btnHow.setRolloverIcon(img2rol);
		btnHow.setPressedIcon(img2pre);
		btnHow.setBorderPainted(false); //JButton�� Border(�ܰ���)�� �����ش�.    
		btnHow.setFocusPainted(false); //JButton�� ���뿵�� ä��� ����
		btnHow.setContentAreaFilled(false); //JButton�� ����(focus)�Ǿ����� ����� �׵θ� ������
		btnHow.setOpaque(false);
		btnHow.addActionListener(this);
		
		UpPan.add(btnPrice);
		UpPan.add(btnHow);
		
	
		
	}


	private void Down() {
		
		Card = new CardLayout();//ī�巹�̾ƿ��̿��ؼ� ��� ������ȯ
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
		CardPane.add(CenterPanel1,"price");//�� �гκ��� �̸�����
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
