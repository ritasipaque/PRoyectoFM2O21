/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diana
 */
public class ClienteDAO {

    private static final String SQL_SELECT = "SELECT PK_id_cliente, nombre_cliente, apellido_cliente, edad, CodigoCorreo, CodigoDireccion, CodigoTelefono, DPI FROM tbl_cliente";
    private static final String SQL_INSERT = "INSERT INTO tbl_cliente(nombre_cliente, apellido_cliente, edad, CodigoCorreo, CodigoDireccion, CodigoTelefono, DPI ) VALUES(?, ?,?, ?,,?, ?,?, ? )";
    private static final String SQL_UPDATE = "UPDATE tbl_cliente SET nombre_cliente=?, apellido_cliente=?, edad=?, CodigoCorreo=?, CodigoDireccion=?, CodigoTelefono=?, DPI=? WHERE PK_id_cliente = ?";
    private static final String SQL_DELETE = "DELETE FROM tbl_cliente WHERE PK_id_cliente=?, nombre_cliente=?, apellido_cliente=?, edad=?, CodigoCorreo=?, CodigoDireccion=?, CodigoTelefono=?, DPI=?";
    private static final String SQL_QUERY = "SELECT PK_id_cliente, nombre_cliente, apellido_cliente, edad, CodigoCorreo, CodigoDireccion, CodigoTelefono, DPI FROM tbl_cliente WHERE nombre_cliente = ?";

    public List<Cliente> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_cliente = rs.getInt("PK_id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                String correo = rs.getString("correo");
                String direccion = rs.getString("direccion");
                int telefono = rs.getInt("telefono");
                String dpi = rs.getString("dpi");

                cliente = new Cliente();
                cliente.setId_cliente(id_cliente);
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setEdad(edad);
                cliente.setCorreo(correo);
                cliente.setDireccion(direccion);
                cliente.setTelefono(telefono);
                cliente.setDpi(dpi);

                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return clientes;
    }

    public int insert(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setInt(3, cliente.getEdad());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getDireccion());
            stmt.setInt(6, cliente.getTelefono());
            stmt.setString(7, cliente.getDpi());

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

    public int update(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setInt(3, cliente.getEdad());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getDireccion());
            stmt.setInt(6, cliente.getTelefono());
            stmt.setString(7, cliente.getDpi());

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

    public int delete(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
           // stmt.setInt(1, cliente.getId_usuario());
            
            
            
            
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

//    public Usuario query(Usuario usuario) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            conn = Conexion.getConnection();
//            System.out.println("Ejecutando query:" + SQL_QUERY);
//            stmt = conn.prepareStatement(SQL_QUERY);
//            stmt.setString(1, usuario.getUsername());
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                int id_usuario = rs.getInt("id_usuario");
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//
//                usuario = new Usuario();
//                usuario.setId_usuario(id_usuario);
//                usuario.setUsername(username);
//                usuario.setPassword(password);
//            }
//            //System.out.println("Registros buscado:" + persona);
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            Conexion.close(rs);
//            Conexion.close(stmt);
//            Conexion.close(conn);
//        }
//
//        //return personas;  // Si se utiliza un ArrayList
//        return usuario;
//    }
}
