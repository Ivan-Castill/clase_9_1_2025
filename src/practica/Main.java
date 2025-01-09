package practica;

import java.sql.Connection;//para la coneccion
import java.sql.DriverManager;//para ka coneccion
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws SQLException {

        String url="jdbc:mysql://localhost:3306/operadores";
        String user="root";
        String password="1234";
        String query="SELECT *FROM cliente";
        try (Connection cone=DriverManager.getConnection(url,user,password)){
            PreparedStatement statement=cone.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            System.out.print("coneccion activa");
            while (resultSet.next()){
                int id=resultSet.getInt("ID");
                String nombre=resultSet.getNString("NOMBRE");
                String apellido=resultSet.getNString("APELLIDO");
                String correo=resultSet.getNString("CORREO");
                System.out.print("\n\nid: "+id+"\n Nombre: "+nombre+"\n Apellido: "+apellido+"\n Correo: "+correo);
            }
        }
        catch (Exception e){
            Exception e1=e;
            e1.printStackTrace();
        }
    }
}
