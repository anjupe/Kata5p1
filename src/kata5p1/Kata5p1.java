package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5p1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Entrar\\Downloads\\SQLiteDatabaseBrowserPortable\\Data\\Kata5.db");
        
        Statement statement = connection.createStatement();
        
        ResultSet rs = statement.executeQuery("Select * FROM PEOPLE;");
        
        while (rs.next()){
            System.out.println("ID = " + rs.getInt("Id") + " Name = "+ rs.getString("Nombre"));
        }
        
        statement.execute("CREATE TABLE IF NOT EXISTS mail ( 'Id' INTEGER PRIMARY KEY AUTOINCREMENT"
                           +" , 'Mail' TEXT NOT NULL);");
        String nameFile="C:\\Users\\Entrar\\Documents\\NetBeansProjects\\Kata5p1\\emails.txt";
        BufferedReader reader = new BufferedReader(new FileReader (new File(nameFile)));
        String mail;
        String query;
        while ((mail = reader.readLine())!=null){
            if (!mail.contains("@")) continue;
            query= "INSERT INTO mail (Mail) VALUES ('" +mail+ "');";
            statement.executeUpdate(query);
        } 
        query= "SELECT count(*)FROM mail";
        rs = statement.executeQuery(query);
        System.out.println(rs.getInt(1));
        
    }   
}
