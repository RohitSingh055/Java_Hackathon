package application;
import java.sql.*;

public class database {

	public static Connection connectDb() 
    {
        try{
        	
        Connection connect = null;
//        String driver = "oracle.jdbc.driver.OracleDriver";;
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String pwd = "2003";
//		Class.forName(driver);
		connect = DriverManager.getConnection(jdbc_url,user,pwd);
            
        if(connect!=null)
        {
        	return connect;
        }
            else
           System.out.println("Connection Failed");
        
       }
    catch(Exception e)
        {e.printStackTrace();}
	return null;
    
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
