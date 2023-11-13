package databasemysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DataBase {
	Connection con;
	Scanner sc=new Scanner(System.in);
	String uname;
	String pass;
	boolean isAdmin;
	
	void createConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/users";

		String uname;
		System.out.println("Enter DataBase username");
		uname=sc.next();
		
		String pass;
		System.out.println("Enter DataBase password");
		pass=sc.next();
		
		con=DriverManager.getConnection(url,uname,pass);
	}
	
	
	void verifyUser()throws SQLException{
		System.out.println("Enter username");
		uname=sc.next();
		System.out.println("Enter Password");
		pass=sc.next();
		boolean isAdmin=uname.equals("admin") && pass.equals("admin");
		if(isAdmin){
			System.out.println("Welcome Admin");
		}
		else {
			System.out.println("Welcome "+uname);
		}
	}
	
	void insertData()throws SQLException {
		Statement st=con.createStatement();
		System.out.println("Enter employee ID");
		int id=sc.nextInt();
		System.out.println("Enter employee name");
		String name=sc.next();
		System.out.println("Enter employee Age");
		int age=sc.nextInt();
		System.out.println("Enter Address");
		String address=sc.next();
		System.out.println("Enter Salary");
		double salary=sc.nextDouble();
		System.out.println("Enter mail id");
		String mail=sc.next();
		System.out.println("Enter mobile number");
		int mobno=sc.nextInt();
		st.execute("insert into users.employeedetails values("+id+name+age+address+salary+mail+mobno+")");
		System.out.println("Data Inserted");
	}
	
		void retrieveData()throws SQLException {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from users.employeedetails");
			while(rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getInt(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getDouble(5));
				System.out.println(rs.getString(6));
				System.out.println(rs.getInt(7));
			}
		}

	void deleteData()throws SQLException{
		Statement st=con.createStatement();
		System.out.println("Enter emp id to delete");
		int id=sc.nextInt();
		st.execute("Delete from users.employeedetails where empId="+id);
		System.out.println("record deleted");
	}
	
	void updateData()throws SQLException{
		Statement st=con.createStatement();
		System.out.println("Enter age");
		int age=sc.nextInt();
		System.out.println("Enter new Address");
		String address=sc.next();
		System.out.println("Enter new mobile number");
		double mobno=sc.nextDouble();
		System.out.println("Enter new mail id");
		String mail=sc.next();
		
		st.execute("update users.employeedetails set age="+age+"address"+address+"mobile no"+mobno+"mail"+mail);
		System.out.println("Record updated");
	}
		void showOptions()throws SQLException{
			if(isAdmin) {
				System.out.println("Select options");
				System.out.println("1.Update");
				System.out.println("2.Delete");
				System.out.println("3.Retrieve");
				System.out.println("4.Insert");
				int option=sc.nextInt();
				switch(option) {
				case 1:
					updateData();
					break;
				case 2:
					deleteData();
					break;
				case 3:
					retrieveData();
					break;
				case 4:
					insertData();
					break;
					default:
						System.out.println("Invalid option");
						break;
				}
			}
			else {
				System.out.println("Select option");
				System.out.println("1.UPDATE");
				System.out.println("2.Retrieve");
				int option=sc.nextInt();
				switch(option) {
				case 1:
				updateData();
				break;
				case 2:
				retrieveData();
				break;
				default:
					System.out.println("Invalid option");
					break;
				}
			}
		}
			void closeConnection()throws SQLException{
				con.close();
			}
			
	
	public static void main(String args[])throws SQLException {
		DataBase db=new DataBase();
		db.createConnection();
		db.verifyUser();
		db.showOptions();
	}
	}


