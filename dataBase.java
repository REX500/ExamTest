package dbAcces;

import AppLayer.Costumer;
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
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/examTest?useSSl=true";
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

    ArrayList<Costumer> costumerArray;
    public ArrayList<Costumer> getCostumers() throws SQLException{
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "root", "password");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM costumer");
            costumerArray = new ArrayList<>();
            while(rs.next()){
                String fname = rs.getString("First_name");
                String lname = rs.getString("Last_name");
                String mail = rs.getString("Mail");
                String city = rs.getString("City");
                String address = rs.getString("Address");
                String zip = rs.getString("Zip");
                String phone = rs.getString("PhoneNum");
                String bank = rs.getString("Bank_acc");
                String cpr = rs.getString("CPR");

                Costumer c = new Costumer(fname, lname, mail, city, address, zip, phone, bank, cpr);
                costumerArray.add(c);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return costumerArray;
    }

    public void sellGame(String name) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "root", "password");
            Statement s = con.createStatement();
            //String update = "UPDATE games SET Quantity= Quantity -1  WHERE Name "+name;
            String update1 = String.format("UPDATE games SET Quantity= Quantity -1  WHERE Name = '%s'", name);
            s.executeUpdate(update1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sellFillTable(String name, String genre, String date, String platform, String customerID)throws SQLException{

        // this method will fill up the rented game table with the required data
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "root", "password");
            Statement s = con.createStatement();
            //String command = (name+","+genre+","+date+","+platform+","+customerID+";");
            //String insert = "INSERT INTO rented_game (Name, Genre, Date, Platform, Costumer_id) VALUES "+command;

            String insert1= String.format("INSERT INTO rented_game (Name, Genre, Date, Platform, Costumer_id) VALUES ('%s','%s','%s','%s','%s')",name, genre, date, platform,customerID);
            s.executeUpdate(insert1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteEmployee(String firstName)throws SQLException{
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "root", "password");
            Statement s = con.createStatement();

            String insert1= String.format("DELETE FROM employee WHERE First_name = '%s' ", firstName);
            s.executeUpdate(insert1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteCustomer(String cpr)throws  SQLException{
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "root", "password");
            Statement s = con.createStatement();

            String insert1= String.format("DELETE FROM costumer WHERE CPR = '%s' ", cpr);
            s.executeUpdate(insert1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
