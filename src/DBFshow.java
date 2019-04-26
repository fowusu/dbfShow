import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



	class DBFshow {
		Frame F;
		ResultSet Rs;
		Button b1;
		TextField T1, T2, T3, T4, T5;
		Label L1, L2, L3, L4, L5;

		DBFshow() {

			T1 = new TextField(10);
			T2 = new TextField(10);
			T3 = new TextField(10);
			b1 = new Button("NEXT");
			L1 = new Label("RegNo");
			L2 = new Label("NAME");
			L3 = new Label("MARKS");
			L4 = new Label("PERCENTAGE");
			L5 = new Label("RESULT");
			T4 = new TextField(10);
			T5 = new TextField(10);
			F = new Frame();
			F.setLayout(new GridLayout(6, 2));
			F.add(L1);
			F.add(T1);
			F.add(L2);
			F.add(T2);
			F.add(L3);
			F.add(T3);
			F.add(L4);
			F.add(T4);
			F.add(L5);
			F.add(T5);
			F.add(new Label());
			F.add(b1);
			F.setSize(400, 400);
			F.setVisible(true);

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/qa", "root", "");
				Statement S = con.createStatement();
				Rs = S.executeQuery("Select * From School");
				if (Rs.next()) {
					showRecord();
				}

			} catch (Exception e) {
				System.out.print(e.toString());
			}

			b1.addActionListener(

					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent X) {
							try {
							if (Rs.next()) {
								showRecord();
							}

						} catch (Exception e) {
							System.out.print(e.toString());
						}
						}
					});

		}
		
		public void showRecord() {
			
			
			int RegNo, Marks; 
			Float per;
			String name,result; 
			try {
			RegNo = Rs.getInt(1);
			Marks = Rs.getInt(3);
			name = Rs.getString(2); 
			per = (float) Marks * 150/100;
			
			if (per >= 60) {
				result = "PASS";
			}else {
				result = "FAIL";
			}
			
			T1.setText(Integer.toString(RegNo));
			T2.setText(name);
			T3.setText(Integer.toString(Marks));
			T4.setText(Float.toString(per));
			T5.setText(result);
			
			}
			catch(Exception e){
				System.out.print(e.toString());
			}
		}
		
		public static void main( String abc[]) {
			
			new DBFshow();
		}
		
	}

	

	


