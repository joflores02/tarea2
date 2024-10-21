/**
 * Clase que representa un empleado de la empresa.
 */
public class Empleado {
    private String id;
    private String apellido;
    private String nombre;
    private String correo;

    /**
     * Constructor para crear un nuevo empleado.
     *
     * @param id       El identificador del empleado.
     * @param nombre   El nombre del empleado.
     * @param apellido El apellido del empleado.
     * @param correo   El correo electrónico del empleado.
     */
    public Empleado(String id, String nombre, String apellido, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    /**
     * Obtiene el identificador del empleado.
     *
     * @return El ID del empleado.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el apellido del empleado.
     *
     * @return El apellido del empleado.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Obtiene el correo electrónico del empleado.
     *
     * @return El correo del empleado.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nombre El nuevo nombre del empleado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el apellido del empleado.
     *
     * @param apellido El nuevo apellido del empleado.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Establece el identificador del empleado.
     *
     * @param id El nuevo ID del empleado.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Establece el correo electrónico del empleado.
     *
     * @param correo El nuevo correo del empleado.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return id + " " + nombre + " " + apellido + " " + correo;
    }
}
