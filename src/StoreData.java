import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

public class StoreData {
    public StoreData() {
    }

    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/amazon";
            String user = "tester";
            String pass = "password";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected");
            return conn;
        } catch (Exception var5) {
            System.out.println(var5);
            return null;
        }
    }

    public static void createtable(Connection conn) throws Exception {
        try {
            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS products(name varchar(255),price varchar(10),PRIMARY KEY (name));");
            create.executeUpdate();
            System.out.println("Table Created");
        } catch (Exception var2) {
            System.out.println(var2);
        }

    }

    public static void insert(ArrayList<Item> products, Connection conn) throws Exception {
        try {
            Iterator var3 = products.iterator();

            while(var3.hasNext()) {
                Item runner = (Item)var3.next();
                PreparedStatement inserting = conn.prepareStatement("REPLACE INTO products(name, price) VALUES ('" + runner.getname() + "' , '" + runner.getprice() + "');");
                inserting.executeUpdate();
            }
        } catch (Exception var5) {
            System.out.println(var5);
        }

    }
}
