package dbAcces;

import AppLayer.Employee;
import AppLayer.Game;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Filip on 25-04-2016.
 */
public class dataBase {

    ArrayList<Employee> empArray;
    static final String JDBC_DRIVER  = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/examTest";
    static Connection con;

    public ArrayList<Employee> getEmployees() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);

            con = DriverManager.getConnection(DATABASE_URL, "root", "password");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employee");
            empArray = new ArrayList<>();
            while(rs.next()){
                String od = rs.getString("Employee_id");
                int id = Integer.parseInt(od);
                String fname = rs.getString("First_name");
                String lname = rs.getString("Last_name");
                String mail = rs.getString("Mail");
                String city = rs.getString("City");
                String add = rs.getString("Address");
                String zip = rs.getString("Zip");
                String num = rs.getString("PhoneNum");
                String bank = rs.getString("Bank_acc");
                String cpr = rs.getString("CPR");

                Employee employee = new Employee(id,fname,lname,mail,city,add,zip,num,bank,cpr);
                empArray.add(employee);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return empArray;
    }

    ArrayList<Game> gameArray;
    public ArrayList<Game> getGames() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "root", "password");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM games");
            gameArray = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("Name");
                String genre = rs.getString("Genre");
                String pegi = rs.getString("PEGI");
                String platform = rs.getString("Platform");
                String price = rs.getString("Price");
                String quantity = rs.getString("Quantity");
                int q = Integer.parseInt(quantity);

                Game game = new Game(name, genre, pegi, platform, price,q);
                gameArray.add(game);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameArray;
    }
}
