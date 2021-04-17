/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.logging.Logger;

/**
 *
 * @author Diana
 */
public class Cliente {
    
    private int id_cliente;
    private String nombre_cliente;
    private String apellido_cliente;
    private int edad;
    private String CodigoCorreo;
    private String CodigoDireccion;
    private int CodigoTelefono;
    private String DPI;

    public int getId_cliente() {
        return id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public int getEdad() {
        return edad;
    }

    public String getCodigoCorreo() {
        return CodigoCorreo;
    }

    public String getCodigoDireccion() {
        return CodigoDireccion;
    }

    public int getCodigoTelefono() {
        return CodigoTelefono;
    }

    public String getDPI() {
        return DPI;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCodigoCorreo(String CodigoCorreo) {
        this.CodigoCorreo = CodigoCorreo;
    }

    public void setCodigoDireccion(String CodigoDireccion) {
        this.CodigoDireccion = CodigoDireccion;
    }

    public void setCodigoTelefono(int CodigoTelefono) {
        this.CodigoTelefono = CodigoTelefono;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

   @Override
    public String toString() {
        return "Cliente{" + "PK_id_cliente=" + id_cliente + ", nombre_cliente=" + nombre_cliente + ", apellido_cliente=" + apellido_cliente + ", edad=" + edad + ", CodigoCorreo=" + CodigoCorreo + ", CodigoDireccion=" + CodigoDireccion + ", CodigoTelefono=" + CodigoTelefono + ", DPI=" + DPI +  '}';
    }

    
  
    
    
    
    
}
