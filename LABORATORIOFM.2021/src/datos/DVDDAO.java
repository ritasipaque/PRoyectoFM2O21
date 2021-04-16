package datos;


import dominio.DVD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PERSONAL
 */
public class DVDDAO {
    
       private static final String SQL_INSERT = "INSERT INTO LaboratioFM2021A(ID,Nombre,Clasificacion,valor) VALUES(?, ?,?, ?)";
    private static final String SQL_SELECT = "SELECT ID,Nombre,Clasificacion,valor FROM LaboratioFM2021A";
    private static final String SQL_QUERY = "SELECT ID,Nombre,Clasificacion,valor FROM LaboratioFM2021A WHERE Id_cliente = ?";
  private static final String SQL_UPDATE = "UPDATE LaboratioFM2021ASET  ID=?,Nombre=?,Clasificacion=?,valor=? WHERE Id_cliente";
    private static final String SQL_DELETE = "DELETE FROM LaboratioFM2021A  WHERE Id_cliente = ? ";
  
     public List<DVD> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DVD venta = null;
        List<  DVD> ventas = new ArrayList<  DVD>();
        try {
            /**
             *
             * conecion con sql de selecccion
             */
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                /**
                 *
                 * busqueda de datos de la bitacocora en la de usuarios
                 */
                String id = rs.getString("ID");
                String nombre  = rs.getString("Nombre");
                String clasificacion =      rs.getString("Clasificacion");
                String valor = rs.getString("Valor");
                  

                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
              

                  venta = new DVD();
                  venta.setID(id);
                    venta.setNombre(nombre);
                 venta.setClasificacion(clasificacion );
                 venta.setValor(valor);
               
                    ventas.add(venta);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
          
    Conexion.close(stmt);
            Conexion.close(conn);
        }
  return ventas;

       
    }
 public   DVD query(DVD venta){
        /**
         *
         * conexion de base de datos
         */
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<DVD> ventas = new ArrayList<DVD>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
             stmt.setString(1, venta.getID());
          
            rs = stmt.executeQuery();
            while (rs.next()) {
              
               String id = rs.getString("ID");
                String nombre  = rs.getString("Nombre");
                String clasificacion =      rs.getString("Clasificacion");
                String valor = rs.getString("Valor");
                  

                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
              

                  venta = new DVD();
                  venta.setID(id);
                    venta.setNombre(nombre);
                 venta.setClasificacion(clasificacion );
                 venta.setValor(valor);
               
                    ventas.add(venta);
                /**
                 *
                 * busqueda de datos de la bitacocora en la de usuarios
                 */
                
               
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
           
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return venta;


    
     }
     public int insert(DVD insertar)  {
         ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stmt = null;
      
        int rows = 0;
        try {
             
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,  insertar.getID());
            stmt.setString(2,  insertar.getNombre());       
            stmt.setString(3,   insertar.getClasificacion());
             stmt.setString(4,  insertar.getValor());
     

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
          
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
       public int update(DVD mod) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
           stmt.setString(1,  mod.getID());
            stmt.setString(2,  mod.getNombre());       
            stmt.setString(3,   mod.getClasificacion());
             stmt.setString(4,  mod.getValor());
   rows = stmt.executeUpdate();
         
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

       public int delete(DVD eliminar) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
             
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
           
           System.out.println("Ejecutando query:" + SQL_DELETE);
               stmt.setString(1,  eliminar.getID());
         
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
    
}
