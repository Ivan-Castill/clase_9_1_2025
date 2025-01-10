package practica;

import java.sql.Connection;//para la coneccion
import java.sql.DriverManager;//para ka coneccion
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws SQLException {

        String url="jdbc:mysql://localhost:3307/consurso";
        String user="root";
        String password="1234";
        String query="SELECT *FROM inscripciones";
        try (Connection cone=DriverManager.getConnection(url,user,password)){
            PreparedStatement statement=cone.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            System.out.print("coneccion activa");
            while (resultSet.next()){
                int id=resultSet.getInt("COD");
                String nombre=resultSet.getNString("NOMBRE");
                String apellido=resultSet.getNString("APELLIDO");
                int edad=resultSet.getInt("EDAD");
                String correo=resultSet.getNString("CORREO");
                System.out.print("\nid: "+id+" Nombre: "+nombre+" Apellido: "+apellido+" Correo: "+correo);
            }
        }
        catch (Exception e){
            Exception e1=e;
            e1.printStackTrace();
        }
    }
}
