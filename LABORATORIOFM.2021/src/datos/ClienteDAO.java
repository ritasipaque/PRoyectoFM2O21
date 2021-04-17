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

    // private static final String SQL_SELECT = "SELECT PK_id_cliente, nombre_cliente, apellido_cliente, edad, CodigoCorreo, CodigoDireccion, CodigoTelefono, DPI FROM tbl_cliente";
    //  private static final String SQL_INSERT = "INSERT INTO tbl_cliente(nombre_cliente, apellido_cliente, edad, CodigoCorreo, CodigoDireccion, CodigoTelefono, DPI ) VALUES(?, ?, ?, ?, ?, ?, ? )";
    //  private static final String SQL_UPDATE = "UPDATE tbl_cliente SET nombre_cliente=?, apellido_cliente=?, edad=?, CodigoCorreo=?, CodigoDireccion=?, CodigoTelefono=?, DPI=? WHERE PK_id_cliente = ?";
    // private static final String SQL_DELETE = "DELETE FROM tbl_cliente WHERE PK_id_cliente=?, nombre_cliente=?, apellido_cliente=?, edad=?, CodigoCorreo=?, CodigoDireccion=?, CodigoTelefono=?, DPI=?";
    //  private static final String SQL_QUERY = "SELECT PK_id_cliente, nombre_cliente, apellido_cliente, edad, CodigoCorreo, CodigoDireccion, CodigoTelefono, DPI FROM tbl_cliente WHERE nombre_cliente = ?";
    private static final String SQL_SELECT = "SELECT PK_id_cliente, nombre_cliente, apellido_cliente, edad, CodigoCorreo, CodigoDireccion, CodigoTelefono, DPI FROM tbl_cliente";
    //Insertar en la BD
    private static final String SQL_INSERT = "INSERT INTO tbl_cliente (PK_id_cliente, nombre_cliente, apellido_cliente, edad, CodigoCorreo, CodigoDireccion, CodigoTelefono, DPI ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    //Modificar la BD
    private static final String SQL_UPDATE = "UPDATE tbl_cliente SET nombre_cliente=?, apellido_cliente=?, edad=?, CodigoCorreo=?, CodigoDireccion=?, CodigoTelefono=?, DPI=?  WHERE PK_id_cliente = ?";
    //Eliminar de la BD
    private static final String SQL_DELETE = "DELETE FROM tbl_cliente WHERE PK_id_cliente=?";
    //Buscar 2 en la BD
    public static final String SQL_QUERY = "SELECT PK_id_cliente, nombre_cliente, apellido_cliente, edad, CodigoCorreo, CodigoDireccion, CodigoTelefono, DPI FROM tbl_cliente WHERE nombre_cliente = ?";
    //Buscar 2 en la BD
    public static final String SQL_QUERY2 = "SELECT PK_id_cliente FROM tbl_cliente";

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
                int PK_id_cliente = rs.getInt("PK_id_cliente");
                String nombre_cliente = rs.getString("nombre_cliente");
                String apellido_cliente = rs.getString("apellido_cliente");
                int edad = rs.getInt("edad");
                String CodigoCorreo = rs.getString("CodigoCorreo");
                String CodigoDireccion = rs.getString("CodigoDireccion");
                int CodigoTelefono = rs.getInt("CodigoTelefono");
                String DPI = rs.getString("DPI");

                cliente = new Cliente();
                cliente.setId_cliente(PK_id_cliente);
                cliente.setNombre_cliente(nombre_cliente);
                cliente.setApellido_cliente(apellido_cliente);
                cliente.setEdad(edad);
                cliente.setCodigoCorreo(CodigoCorreo);
                cliente.setCodigoDireccion(CodigoDireccion);
                cliente.setCodigoTelefono(CodigoTelefono);
                cliente.setDPI(DPI);

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
            stmt.setInt(1, cliente.getId_cliente());
            stmt.setString(2, cliente.getNombre_cliente());
            stmt.setString(3, cliente.getApellido_cliente());
            stmt.setInt(4, cliente.getEdad());
            stmt.setString(5, cliente.getCodigoCorreo());
            stmt.setString(6, cliente.getCodigoDireccion());
            stmt.setInt(7, cliente.getCodigoTelefono());
            stmt.setString(8, cliente.getDPI());

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
            stmt.setString(1, cliente.getNombre_cliente());
            stmt.setString(2, cliente.getApellido_cliente());
            stmt.setInt(3, cliente.getEdad());
            stmt.setString(4, cliente.getCodigoCorreo());
            stmt.setString(5, cliente.getCodigoDireccion());
            stmt.setInt(6, cliente.getCodigoTelefono());
            stmt.setString(7, cliente.getDPI());

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

    public Cliente query(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, cliente.getNombre_cliente());
            stmt.setString(2, cliente.getApellido_cliente());
            stmt.setInt(3, cliente.getEdad());
            stmt.setString(4, cliente.getCodigoCorreo());
            stmt.setString(5, cliente.getCodigoDireccion());
            stmt.setInt(6, cliente.getCodigoTelefono());
            stmt.setString(7, cliente.getDPI());

            rs = stmt.executeQuery();
            while (rs.next()) {

                int id_cliente = rs.getInt("PK_id_cliente");
                String nombre_cliente = rs.getString("nombre_cliente");
                String apellido_cliente = rs.getString("apellido_cliente");
                int edad = rs.getInt("edad");
                String CodigoCorreo = rs.getString("CodigoCorreo");
                String CodigoDireccion = rs.getString("CodigoDireccion");
                int CodigoTelefono = rs.getInt("CodigoTelefono");
                String DPI = rs.getString("DPI");

                cliente.setId_cliente(id_cliente);
                cliente.setNombre_cliente(nombre_cliente);
                cliente.setApellido_cliente(apellido_cliente);
                cliente.setEdad(edad);
                cliente.setCodigoCorreo(CodigoCorreo);
                cliente.setCodigoDireccion(CodigoDireccion);
                cliente.setCodigoTelefono(CodigoTelefono);
                cliente.setDPI(DPI);
            }
            //System.out.println("Registros buscado:" + persona);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return personas;  // Si se utiliza un ArrayList
        return cliente;
    }
}
