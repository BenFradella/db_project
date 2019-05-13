import java.sql.*;

public class StudentMajor
{
	public static void main(String[] args)
	{
	    Connection connection = null;
	    try
	    {
		//step 1: connect to the database server.
		String host = "cslab-db.cs.wichita.edu";
		int port = 3306;
		String database = "dbuser3_database";
		String user = "dbuser3";
		String password = "zKVTeqAjCDCa";
		String url = String.format("jdbc:mariadb:\/\/%s:%s/%s?user=%s&password=%s", host, port, database, user, password);

		connection = DriverManager.getConnection(url);

		//step 2: build and execute teh query.
		Statement stmt = connection.createStatement();
		String qry = "select Title "
			     + " from Movies ";
		ResultSet rs = stmt.executeQuery(qry);

		//Step 3: prepare the output.
		System.out.format("%n");
		System.out.format("%-12s%n", "Title");

		while (rs.next())
		{
			String title = rs.getString("Title");
			System.out.format("%-12s%n", title);
		}
		rs.close();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(connection != null)
			   connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	}
	
}

