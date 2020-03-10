package Parkinglot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class DayPayTable extends JFrame implements ActionListener {
	private DayPayTable dpt;
	private JRadioButton btnTable;
	private JRadioButton btnChart;
	private JComboBox<String> FirstYear,FirstMonth,FirstDay;
	private JComboBox<String> LastYear,LastMonth,LastDay;
	private ArrayList<String> yeararray; // �⵵
	private ArrayList<String> montharray; // ��
	private ArrayList<String> dayarray; // ��
	private JButton Submit;
	private JLabel SumPrice;
	private JPanel TablePan;
	private JPanel CenterPan;
	private Vector column,data,row;
	private JScrollPane jsp;
	private JTable table;
	private String sum;
	private DefaultTableModel model;
	private String FirstSelect,LastSelect;

	public DayPayTable(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(500, 300);
		setSize(550,450);


		setLayout(new BorderLayout());
		JPanel TopPan = new JPanel(new GridLayout(2,1));
		JPanel BtnPan = new JPanel();
		BtnPan.setBackground(Color.white);
		btnTable = new JRadioButton("ǥ");
		btnTable.setBackground(Color.white);
		btnChart = new JRadioButton("��Ʈ");
		btnChart.setBackground(Color.white);
		ButtonGroup btngroup = new ButtonGroup();
		btngroup.add(btnTable);
		btngroup.add(btnChart);
		BtnPan.add(btnTable);
		BtnPan.add(btnChart);
		JPanel DayPan = new JPanel();
		DayPan.setBackground(Color.white);
		Submit = new JButton("�Է�");
		Submit.addActionListener(this);
	
		CenterPan = new JPanel();
	
		TablePan = new JPanel();
		jsp = new JScrollPane(table);
	

		model = new DefaultTableModel(data,column);
		table = new JTable(model);

		table = new JTable(data, column);

		//JLabel
		JPanel SumPricePan = new JPanel();
		SumPricePan.setBackground(Color.white);
		JLabel A = new JLabel("�Է��Ͻ� ��¥�� �� ���� �����  : ");
		SumPrice = new JLabel("0");
		JLabel B = new JLabel(" �� �Դϴ�.");

		SumPricePan.add(A);
		SumPricePan.add(SumPrice);
		SumPricePan.add(B);

		// ��¥�迭 ����
		ArrayList<String> yeararray; // �⵵
		ArrayList<String> montharray; // ��
		ArrayList<String> dayarray; // ��

		Calendar oCalendar = Calendar.getInstance( );  // ���� ��¥/�ð� ���� ���� ���� ���

		// ���� ��¥
		int toyear = oCalendar.get(Calendar.YEAR);
		int tomonth = oCalendar.get(Calendar.MONTH) + 1;
		int today = oCalendar.get(Calendar.DAY_OF_MONTH);

		// �⵵

		yeararray = new ArrayList<String>();


		for(int i = toyear + 10; i>= toyear -10; i--){
			yeararray.add(String.valueOf(i));
			//System.out.println(i);
		}  
		FirstYear= new JComboBox<String>(yeararray.toArray(new String[yeararray.size()]));
		LastYear = new JComboBox<String>(yeararray.toArray(new String[yeararray.size()]));

		FirstYear.setSelectedItem(String.valueOf(toyear));
		LastYear.setSelectedItem(String.valueOf(toyear));


		// ��

		montharray = new ArrayList<String>();

		for(int i = 1; i <= 12; i++){
			montharray.add(addZeroString(i));

		}  
		FirstMonth  = new JComboBox<String>(montharray.toArray(new String[montharray.size()]));
		LastMonth = new JComboBox<String>(montharray.toArray(new String[montharray.size()]));


		// ��

		dayarray = new ArrayList<String>();

		for(int i = 1; i <= 31; i++){
			dayarray.add(addZeroString(i));
		}  
		FirstDay = new JComboBox<String>(dayarray.toArray(new String[dayarray.size()]));
		LastDay = new JComboBox<String>(dayarray.toArray(new String[dayarray.size()]));


		/////////////DayPan �����ºκ�
		DayPan.add(FirstYear);
		DayPan.add(new JLabel("��"));
		DayPan.add(FirstMonth);
		DayPan.add(new JLabel("��"));
		DayPan.add(FirstDay);
		DayPan.add(new JLabel("��  ~"));
		DayPan.add(LastYear);
		DayPan.add(new JLabel("��"));
		DayPan.add(LastMonth);
		DayPan.add(new JLabel("��"));
		DayPan.add(LastDay);
		DayPan.add(new JLabel("��  "));
		DayPan.add(Submit);
		/////////////
		TopPan.add(BtnPan);
		TopPan.add(DayPan);

		add(TopPan,BorderLayout.NORTH);
		add(CenterPan);
		add(SumPricePan,BorderLayout.SOUTH);
		CenterPan.setVisible(true);

		setVisible(true);
	}
	private String addZeroString(int k){
		String value=Integer.toString(k);
		if(value.length()==1) {
			value="0"+value;
		}
		return value;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String s,s1;

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "parking", "tiger");
			st = con.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		FirstSelect =FirstYear.getSelectedItem()+"-"+FirstMonth.getSelectedItem()+"-"+FirstDay.getSelectedItem();
		LastSelect =LastYear.getSelectedItem()+"-"+LastMonth.getSelectedItem()+"-"+LastDay.getSelectedItem();





		if(obj == Submit) {
			System.out.println("111");
			if(btnTable.isSelected()) {
				//if cardlayout�� ���̺� �̸�? �Ʒ� try�� �����ϰ�
				CenterPan.removeAll();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.fireTableDataChanged();
				model.setNumRows(0);


				try {

					//				con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "parking", "tiger");
					//				st = con.createStatement();
					s ="select day,price from log WHERE day >= TO_DATE('"+ FirstSelect
							+ "','YYYY-MM-DD') AND day < TO_DATE('"
							+LastSelect +"','YYYY-MM-DD')+1 ORDER BY day ASC";

					System.out.println(s);

					rs = st.executeQuery(s);
					ResultSetMetaData rsmt = rs.getMetaData();



					int c = rsmt.getColumnCount();
					column = new Vector(c);

					for (int i = 1; i <= c; i++) {
						column.add(rsmt.getColumnName(i));
					}

					data = new Vector();
					row = new Vector();

					while (rs.next()) {
						row = new Vector(c);

						for (int i = 1; i <= c; i++) {
							row.add(rs.getString(i));
						}

						data.add(row);
					}
					model = new DefaultTableModel(data,column);
					table = new JTable(model);
					//table.setPreferredSize(new Dimension(530,290));
					jsp= new JScrollPane(table);
					jsp.setPreferredSize(new Dimension(530,400));
					jsp.setVisible(true);

					CenterPan.add(jsp);

					s1="select sum(price) from log WHERE day >= TO_DATE('"+ FirstSelect
							+ "','YYYY-MM-DD') AND day < TO_DATE('"
							+LastSelect +"','YYYY-MM-DD')+1 ORDER BY day ASC";
					System.out.println(s1);
					rs = st.executeQuery(s1);

					if(rs.next()) { //�̰� �޴°Թ���

						sum = rs.getString("sum(price)");
					}

					SumPrice.setText(sum);
					TablePan.setVisible(true);
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ERROR");
				} finally {
					try {
						st.close();
						rs.close();
						con.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "ERROR CLOSE");
					}

				}
			}	
			if(btnChart.isSelected()) {
				System.out.println("2222");
				CenterPan.removeAll();
				String X[] = new String[100] ;
				String Y[] = new String[100] ;
				try {


					s ="select day,sum(price) from log WHERE day >= TO_DATE('"+ FirstSelect
							+ "','YYYY-MM-DD') AND day < TO_DATE('"
							+LastSelect +"','YYYY-MM-DD')+1 group by day ORDER BY day ASC";


					System.out.println(s);
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					rs = st.executeQuery(s);
					rs.last();
					int c =rs.getRow();
					rs.beforeFirst();
					System.out.println(c);
					//����������
					DefaultCategoryDataset data = new DefaultCategoryDataset();
					while(rs.next()) {
						int i=1;
						i++;
						X[i]=rs.getString("day");
						Y[i]=rs.getString("sum(price)");


						data.addValue(Integer.parseInt(Y[i]), "pay", X[i]);
						System.out.println(X[i]);
					}

					//					DefaultCategoryDataset data = new DefaultCategoryDataset();
					//					
					//						data.addValue(Integer.parseInt(Y[i]), "pay", X[i]);
					//				
					BarRenderer render = new BarRenderer();
					CategoryAxis xAxis = new CategoryAxis("Day");
					ValueAxis yAxis = new NumberAxis("sum price");

					CategoryPlot plot = new CategoryPlot(data,xAxis,yAxis,render);

					JFreeChart chart = new JFreeChart(plot);

					ChartPanel chartpane = new ChartPanel(chart);
					chartpane.setMaximumDrawWidth(4000);
					chartpane.setMaximumDrawHeight(4000);
					chartpane.setPreferredSize(new Dimension(500,300));
					CenterPan.add(chartpane);

					CenterPan.revalidate();
					CenterPan.repaint();
					System.out.println("33333");

				}



				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ERROR");
				} finally {
					try {
						System.out.println("344444");
						st.close();
						rs.close();
						con.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "ERROR CLOSE");
					}

				}
			}
		}
	}
}
