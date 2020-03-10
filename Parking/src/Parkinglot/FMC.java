package Parkinglot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FMC extends JPanel implements ActionListener{
	
	private JLabel lbl;
	private JTextField tf;
	private JButton btn;
	private JPanel Main;
	public FMC(String title) {

		setLayout(new FlowLayout());
		
		ImageIcon back = new ImageIcon("img/FindMyCar/back.png");
		
		ImageIcon img = new ImageIcon("img/FindMyCar/find.png");

		lbl = new JLabel("차량번호를 입력해주세요");
	
		lbl.setForeground(Color.white);

		
		tf = new JTextField();

		tf.setForeground(Color.white);

		
	
		btn = new JButton("Find");

		add(lbl);
		add(btn);
	
		add(tf);

		setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		DB_Conn dc = new DB_Conn();
		String sql = "select * from car where carnumber = '" + tf.getText() +"'";
		System.out.println(sql);
		ResultSet rs = dc.excuteQuery(sql);
		
		try {
            if(rs.next()) {


                if(tf.getText().equals(rs.getString("carnumber"))) {
                	JOptionPane.showMessageDialog(null,"현재 차량 위치는 : "+ rs.getString("space")+ " 입니다.");
                }


            }else {
                JOptionPane.showMessageDialog(null, "없는 차량입니다.","알림", JOptionPane.INFORMATION_MESSAGE);
            }


		} catch (HeadlessException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
