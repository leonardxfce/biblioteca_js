package modelo;

import java.sql.Date;
import java.util.ArrayList;
import util.ManejadorDeArchivos;

/**
 * Esta clase permite crear un nuevo préstamo, modificar préstamo ya cargado
 * (solo se puede modificar su estado), o leer todos los préstamos existentes en
 * la base de datos.
 *
 * @author IES 9-024 LAVALLE.
 * @version 2.018.
 */
public class Prestamo implements IModelo {

    private int Socio;
    private int Libro;
    private int numPrestamo;
    private Date Fecha;
    private int Plazo;
    private int Estado;

    // Constructor:
    /**
     * Constructor de la clase Prestamo (no se inicializan atributos).
     */
    public Prestamo() {
    }

    /**
     * Este método (getEstado) devuelve el id del estado del préstamo
     * actualmente almacenado en memoria.
     *
     * @return Estado (de tipo int).
     */
    public int getEstado() {
        return Estado;
    }

    /**
     * Este método (setEstado) asigna el id del estado del préstamo
     * almacenándolo en memoria.
     *
     * @param Estado (de tipo int).
     */
    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    /**
     * Este método (getSocio) devuelve el id del socio del préstamo actualmente
     * almacenado en memoria.
     *
     * @return Socio (de tipo int).
     */
    public int getSocio() {
        return Socio;
    }

    /**
     * Este método (setSocio) asigna el id del socio que realizó el pedido de
     * préstamo almacenándolo en memoria.
     *
     * @param Socio (de tipo int).
     */
    public void setSocio(int Socio) {
        this.Socio = Socio;
    }

    /**
     * Este método (getLibro) devuelve el id del libro del préstamo actualmente
     * almacenado en memoria.
     *
     * @return Libro (de tipo int).
     */
    public int getLibro() {
        return Libro;
    }

    /**
     * Este método (setLibro) asigna el id del libro prestado almacenándolo en
     * memoria.
     *
     * @param Libro (de tipo int).
     */
    public void setLibro(int Libro) {
        this.Libro = Libro;
    }

    /**
     * Este método (getNumPrestamo) devuelve el número del préstamo actualmente
     * almacenado en memoria.
     *
     * @return numPrestamo (de tipo int).
     */
    public int getNumPrestamo() {
        return numPrestamo;
    }

    /**
     * Este método (setNumPrestamo) asigna el número del préstamo almacenándolo
     * en memoria.
     *
     * @param numPrestamo (de tipo int).
     */
    public void setNumPrestamo(int numPrestamo) {
        this.numPrestamo = numPrestamo;
    }

    /**
     * Este método (getFecha) devuelve la fecha del día en que se realizó el
     * préstamo actualmente almacenado en memoria.
     *
     * @return Fecha (de tipo Date).
     */
    public Date getFecha() {
        return Fecha;
    }

    /**
     * Este método (setFecha) asigna la fecha en que se realizó el préstamo
     * almacenándolo en memoria.
     *
     * @param Fecha (de tipo Date).
     */
    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    /**
     * Este método (getPlazo) devuelve la fecha del día en que se realizó el
     * préstamo actualmente almacenado en memoria.
     *
     * @return Fecha (de tipo Date).
     */
    public int getPlazo() {
        return Plazo;
    }

    /**
     * Este método (setPlazo) asigna la fecha de entrega del préstamo
     * almacenándolo en memoria.
     *
     * @param Plazo (de tipo int).
     */
    public void setPlazo(int Plazo) {
        this.Plazo = Plazo;
    }

    /**
     * Este método (insert) envía la petición para insertar un nuevo préstamo en
     * la base de datos.
     */
    public String prepareInsert() {
        ManejadorDeArchivos ma = new ManejadorDeArchivos();
        String sql = ma.abrirArchivo("plantillas/prestamos/insert_Prestamo.sql");
        sql = sql.replace("{Socio_idSocio}", Integer.toString(this.Socio));
        sql = sql.replace("{Libro_idLibro}", Integer.toString(this.Libro));
        sql = sql.replace("{numPrestamo}", Integer.toString(this.numPrestamo));
        sql = sql.replace("{Fecha}", this.Fecha.toString());
        sql = sql.replace("{Plazo}", Integer.toString(this.Plazo));
        sql = sql.replace("{Estado_idEstado}", Integer.toString(this.Estado));
        return sql;
    }

    @Override
    public void insert() {
        String s1 = this.prepareInsert();

        CONECTOR.ejecutarSentencia(s1);
    }

    /**
     * Este método (update) envía la petición para modificar un préstamo ya
     * existente en la base de datos.
     *
     * @return
     */
    public String prepareUpdate(String identificador) {
        ManejadorDeArchivos ma = new ManejadorDeArchivos();
        String sql = ma.abrirArchivo("plantillas/prestamos/update_Prestamo.sql");
        sql = sql.replace("{Estado_idEstado}", Integer.toString(this.Estado));
        sql = sql.replace("{identificador}", identificador);
        return sql;
    }

    @Override
    public int update(String identificador) {
        String up = this.prepareUpdate(identificador);
        return CONECTOR.ejecutarSentencia(up);
    }

    /**
     * Este método (selectTodos) envía un String que contiene la consulta que
     * permite obtener todos los préstamos existentes en la base de datos.
     *
     * @return (se retornan todos los préstamos obtenidos desde la base de
     * datos).
     */
    public String prepareSelectTodos() {
        ManejadorDeArchivos ma = new ManejadorDeArchivos();
        String sql = ma.abrirArchivo("plantillas/prestamos/selectTodos_prestamo.sql");
        return sql;

    }

    @Override
    public ArrayList selectTodos() {
		String select = this.prepareSelectTodos();
        return CONECTOR.ejecutarConsulta(select);
    }

    /**
     * Este método (contarPrestamos) envía un String que contiene la consulta
     * que permite obtener la cantidad de préstamos existentes en la base de
     * datos a partir del id de un libro.
     *
     * @param idlibro
     * @return (se retorna la cantidad de los préstamos existentes en la base de
     * datos a partir del id ingresado).
     */
    public String prepareContarPrestamos(String idLibro) {
        ManejadorDeArchivos ma = new ManejadorDeArchivos();
        String sql = ma.abrirArchivo("plantillas/prestamos/contar_prestamos.sql");
        sql = sql.replace("{ID_LIBRO}", idLibro);
        return sql;
    }

    public int contarPrestamos(String idlibro) {
        String select = this.prepareContarPrestamos(idlibro);
        ArrayList cantidadLibros = CONECTOR.ejecutarConsulta(select);
        return Integer.parseInt(((ArrayList) cantidadLibros.get(0)).get(0).toString());
    }

    /**
     * Este método obtiene el numero del ultimo prestamo y lo devuelve como un
     * entero.
     *
     * @return (int) Numero del ultimo prestamo regitrado;
     */
    public String prepareUltimoPrestamo() {
        ManejadorDeArchivos ma = new ManejadorDeArchivos();
        String sql = ma.abrirArchivo("plantillas/prestamos/select_ultimoPrestamo.sql");
        return sql;
    }

    public int ultimoNumPrestamo() {
        String select = this.prepareUltimoPrestamo();
        if (((ArrayList) CONECTOR.ejecutarConsulta(select).get(0)).get(0) == null) {
            return 0;
        } else {
            return Integer.parseInt(((ArrayList) CONECTOR.ejecutarConsulta(select).get(0)).get(0).toString());
        }
    }
   
  

    public boolean comprobarDeuda(String id) {
        boolean existe = false;
        String select = "SELECT socio_idSocio FROM usuario where idUsuario = " + id;
        ArrayList resp = CONECTOR.ejecutarConsulta(select);
        System.out.println(resp.get(0));
        
        if (((ArrayList) resp.get(0)).get(0) == null) {
            existe = false;
        } else {
            select = "SELECT * FROM `prestamo` WHERE Socio_idSocio = " + ((ArrayList) resp.get(0)).get(0).toString() + " AND Estado_idEstado != 4";
            ArrayList respuesta = CONECTOR.ejecutarConsulta(select);
            if (respuesta.isEmpty() != true) {
                existe = true;
            }
        }
        return existe;
    }

    public String prepareComprobarDeudaConInfo(String id) {
        ManejadorDeArchivos ma = new ManejadorDeArchivos();
        String sql = ma.abrirArchivo("plantillas/prestamos/comprobarDeudaConInfo_prestamos.sql");
        sql = sql.replace("{Socio_idSocio}", id);
        return sql;
    }

    public ArrayList comprobarDeudaConInfo(String id) {
		String select ="SELECT prestamo.Socio_idSocio, libro.Titulo, prestamo.Fecha FROM biblioteca.prestamo INNER JOIN libro ON prestamo.Libro_idlibro = libro.idlibro WHERE Socio_idSocio = " +id+ " AND Estado_idEstado != 4;";
		ArrayList lista = CONECTOR.ejecutarConsulta(select);
        return lista;
    }

    @Override
    public int comprobarExistenciaDeRegistro(String[] data) {
        throw new UnsupportedOperationException("No soportado");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
