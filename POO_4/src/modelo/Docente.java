/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;







/**
 *
 * @author pc
 */
public class Docente extends Persona {
    
    private int id;
    private String codigo;
    private String salario;
    private String fecha_ingreso;
    private String fecha_registro;
    Conexion cn; 
    
    public Docente() {}
    public Docente(int id, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento,
                   String codigo, String salario, String fecha_ingreso, String fecha_registro) {
        super(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.codigo = codigo;
        this.salario = salario;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_registro = fecha_registro;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getFecha_Ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_Ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_Registro() {
        return fecha_registro;
    }

    public void setFecha_Registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

   public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        query = "SELECT id_docente, codigo, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, salario, fecha_ingreso, fecha_registro FROM docente;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);

        
        String encabezado[] = {"ID Docente", "Codigo", "NIT", "Nombres", "Apellidos", "Direccion", "Telefono", "Fecha Nacimiento", "Salario", "Fecha Ingreso", "Fecha Registro"};
        tabla.setColumnIdentifiers(encabezado);

       
        String datos[] = new String[11];

        while (consulta.next()) {
            datos[0] = consulta.getString("id_docente");
            datos[1] = consulta.getString("codigo");
            datos[2] = consulta.getString("nit");
            datos[3] = consulta.getString("nombres");
            datos[4] = consulta.getString("apellidos");
            datos[5] = consulta.getString("direccion");
            datos[6] = consulta.getString("telefono");
            datos[7] = consulta.getString("fecha_nacimiento");
            datos[8] = consulta.getString("salario");
            datos[9] = consulta.getString("fecha_ingreso");
            datos[10] = consulta.getString("fecha_registro");
            tabla.addRow(datos);
        }
        cn.cerrar_conexion();

    } catch (Exception ex) {
        cn.cerrar_conexion();
        System.out.println("Error: " + ex.getMessage());
    }
    return tabla;
}

    
   
    
    
    
    @Override
public void agregar() {
    try {
        PreparedStatement parametro;
        String query = "INSERT INTO docente(codigo, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, salario, fecha_ingreso, fecha_registro) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        cn = new Conexion();
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        
        // Asegúrate de que todos estos métodos devuelvan valores válidos
        parametro.setString(1, this.getCodigo());
        parametro.setString(2, this.getNit());
        parametro.setString(3, this.getNombres());
        parametro.setString(4, this.getApellidos());
        parametro.setString(5, this.getDireccion());
        parametro.setString(6, this.getTelefono());
        parametro.setString(7, this.getFecha_Nacimiento());
        parametro.setString(8, this.getSalario());
        parametro.setString(9, this.getFecha_Ingreso());
        parametro.setString(10, this.getFecha_Registro());

        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null, Integer.toString(executar) + " Registro ingresado", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}

     public void actualizar (){
       try{
           PreparedStatement parametro;
           String query = "UPDATE docente SET codigo=?,nit=?,nombres=?,apellidos=?,direccion=?,telefono=?,fecha_nacimiento=?, salario=?,fecha_ingreso=?,fecha_registro=? WHERE id_docente =?;";
           
           cn = new Conexion();
           cn.abrir_conexion();
           parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
           parametro.setString(1, this.getCodigo());
           parametro.setString(2, this.getNit());
           parametro.setString(3, this.getNombres());
           parametro.setString(4, this.getApellidos());
           parametro.setString(5, this.getDireccion());
           parametro.setString(6, this.getTelefono());
           parametro.setString(7, this.getFecha_Nacimiento());
           parametro.setString(8, this.getSalario());
           parametro.setString(9, this.getFecha_Ingreso());
           parametro.setString(10, this.getFecha_Registro());
           parametro.setInt(11, this.getId());
           
           int executar = parametro.executeUpdate();
           cn.cerrar_conexion();
           JOptionPane.showMessageDialog(null,Integer.toString(executar) +"Registro ingresador","Actualizado", JOptionPane.INFORMATION_MESSAGE);
                   
       }catch (Exception ex){
           System.out.println("Error..."+ ex.getMessage());
       }
    
}
     public void elimiinar (){
       try{
           PreparedStatement parametro;
           String query = "delete from docente WHERE id_docente =?;";
           
           cn = new Conexion();
           cn.abrir_conexion();
           parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
           parametro.setInt(1, this.getId());
           
           int executar = parametro.executeUpdate();
           cn.cerrar_conexion();
           JOptionPane.showMessageDialog(null,Integer.toString(executar) +"Registro ingresador","Eliminado", JOptionPane.INFORMATION_MESSAGE);
                   
       }catch (Exception ex){
           System.out.println("Error..."+ ex.getMessage());
       }
    
}
   }
