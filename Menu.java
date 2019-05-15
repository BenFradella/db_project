import java.sql.*;
import java.util.Scanner;

public class Menu
{
    public static void main(String[] args)
    {
        int choice;
        Connection conn = null;
        try
        {
            // Step 1: connect to the database server using a connection string.
            String host = "cslab-db.cs.wichita.edu";
            int port = 3306;
            String database = "dbuser3_database";
            String user = "dbuser3";
            String password = "zKVTeqAjCDCa";
            String url =
            String.format("jdbc:mariadb://%s:%s/%s?user=%s&password=%s",
                                        host, port, database, user, password);
            conn = DriverManager.getConnection(url);

            // Step 2: Display the menu and get the user response.
            choice = PrintMenuAndGetResponse( );

            // Step 3: Respond to the menu choice.
            while (choice != 7)
            {
                switch (choice)
                {
                    case 1:
                        MovieTitleQuery(conn);
                        break;
                    case 2:
                        //implement option 2
                        break;
                    case 3:
                        //implement option 3
                        break;
                    case 4:
                        //implement option 4
                        break;
                    case 5:
                        //implement option 5
                        MovieCountQuery(conn);
                        break;
                    case 6:
                        //implement option 6
                        break;
                    case 7: // To quit the program.
                        System.out.println("Exiting Program");
                        break;
                    default: // Illegal choice for integers other than 1, 2 and 3.
                        System.out.println("Illegal choice");
                        break;
                }
            choice = PrintMenuAndGetResponse( );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // Step 4: Disconnect from the database server.
            try
            {
                if (conn != null)
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    // This function controls the user interaction with the menu.
    public static int PrintMenuAndGetResponse( )
    {
        Scanner keyboard = new Scanner(System.in);
        int response;
        System.out.println("Choose from one of the following options:");
        System.out.println(" 1. List all movies.");
        System.out.println(" 2. Insert a movie");
        System.out.println(" 3. Delete a movie");
        System.out.println(" 4. Update a studio name");
        System.out.println(" 5. Count the movies produced by each studio");
        System.out.println(" 6. Count the number of movies a person is in");
        System.out.println(" 7. Quit");
        System.out.print("Your choice ==> ");
        response = keyboard.nextInt();
        // Leave a blank line before printing the output response.
        System.out.println( );
        return response;
    }

    // This function counts all movies and groups by studio
    public static void MovieCountQuery(Connection conn) throws SQLException
    {
      Statement stmt = conn.createStatement();
      String qry = "select count(StudioName), StudioName "
      +
      "from Movies "
      +
      "group by StudioName";


      ResultSet rs = stmt.executeQuery(qry);
      // Loop through the result set and print the output.
      // First -- print the output column headings.
      System.out.format("%n");
      System.out.format("%-12s%n", "StudioName");
      // Then -- print the body of the output table.
      while (rs.next())
      {
        String countstudioname = rs.getString("count(StudioName)");
        System.out.format("%s ", countstudioname);
        String studioname = rs.getString("StudioName");
        System.out.format("%s%n", studioname);
      }
      System.out.println();
      rs.close();
    }

    // This function is for the query of finding names, IDs and majors
    // of those students graduating in a particular year.
    public static void MovieTitleQuery(Connection conn)
    throws SQLException
    {
        Statement stmt = conn.createStatement();
        Scanner keyboard = new Scanner(System.in);
        String qry = "select Title "
        +
        "from Movies ";
        ResultSet rs = stmt.executeQuery(qry);
        // Loop through the result set and print the output.
        // First -- print the output column headings.
        System.out.format("%n");
        System.out.format("%-12s%n", "Title");
        // Then -- print the body of the output table.
        while (rs.next())
        {
            String title = rs.getString("Title");
            System.out.format("%-12s%n", title);
        }
        System.out.println( );
        rs.close();
    }
}
