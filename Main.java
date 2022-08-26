package practice.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("welcome");
		String url = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10515030";
		String username = "sql10515030";
		String password = "xnSWGuzHf3";
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println(con);
			System.out.println("Connection Established successfully");
			System.out.println("please enter a number 1 for get the data " + "2 for insert the data "
					+ "3 for update the data " + "4 for delete the data");
			int opertion = sc.nextInt();
			switch (opertion) {
			case 1:
				Statement st = con.createStatement();
				String query = "Select * from UserLogin";
				ResultSet rs = st.executeQuery(query); // Execute query
				while (rs.next()) {
					String name = rs.getString("Username");
					String Email = rs.getString("Email");
					String Password = rs.getString("Password");
					System.out.println("Username " + name + " password " + password + " Email " + Email);
				}
				st.close();
				con.close();
				break;
			case 2:
				System.out.println("enter new Username");
				String name = sc.next();
				System.out.println("please enter Email id");
				String email = sc.next();
				System.out.println("enter your new Passowrd");
				String pass = sc.next();
				String sql = "insert into UserLogin values('" + name + "','" + email + "','" + pass + "')";
				Statement st1 = con.createStatement();
				int m = st1.executeUpdate(sql);
				if (m == 1) {
					System.out.println("inserted successfully : " + sql);
				} else {
					System.out.println("inserted failed");
				}
				st1.close();
				con.close();
				break;
			case 3:
				System.out.println("enter a name where you want to chnage ");
				String namess = sc.next();
				String sqls = "SELECT * FROM `UserLogin` WHERE `Username` LIKE '" + namess + "'";
				Statement st2 = con.createStatement();
				ResultSet m2 = st2.executeQuery(sqls);
				while (m2.next()) {
					String name2 = m2.getString("Username");
//					String Email2 = m2.getString("Email");
					String Password2 = m2.getString("Password");
//					System.out.println("Username " + name2 + " password " + Password2 + " Email " + Email2);
					if (name2 != null) {
						System.out.println("please enter old password");
						String oldpass = sc.next();
						System.out.println(Password2);
						System.out.println(Password2.getClass().getSimpleName());
						if (Password2.equalsIgnoreCase(oldpass)) {
							System.out.println("please enter new password");
							String newpass = sc.next();
							String newsql = "UPDATE UserLogin SET Password = '" + newpass + "' WHERE Username = '"
									+ name2 + "';";
							Statement st3 = con.createStatement();
							int m3 = st3.executeUpdate(newsql);
							System.out.println("set a new password successfully" + m3);
							st3.close();
						}

					}
					st2.close();
				}
				break;
			case 4:
				System.out.println("you want to permanently delete the data");
				System.out.println("please enter your user name");
				String dname = sc.next();
				System.out.println("please enter password");
				String oldpass = sc.next();
				String sqlsd = "SELECT * FROM `UserLogin` WHERE `Username` LIKE '" + dname + "'";
				Statement std = con.createStatement();
				ResultSet md = std.executeQuery(sqlsd);
				while (md.next()) {
					String oldname = md.getString("Username");
					String oldpassowrd = md.getString("Password");
					if (dname.equalsIgnoreCase(oldname) && oldpass.equalsIgnoreCase(oldpassowrd)) {
						String dquery = "DELETE FROM UserLogin WHERE Username='" + dname + "'";
						int m3 = std.executeUpdate(dquery);
						System.out.println("delete user successfully...");
					}
				}
				md.close();
				std.close();
				break;
			default:
				System.out.println("Please choose new Operation this is not working");
				break;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
