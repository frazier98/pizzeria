/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author Frazier-98
 */
class reportes {
    String nombre,Descripcion,tipo,precio,total ;

    public reportes (String nombre, String Descripcion, String tipo, String precio, String total) {
        this.nombre = nombre;
        this.Descripcion = Descripcion;
        this.tipo = tipo;
        this.precio = precio;
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPrecio() {
        return precio;
    }

    public String getTotal() {
        return total;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
    
}
