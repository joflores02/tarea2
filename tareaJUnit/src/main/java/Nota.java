/**
 * Clase que representa una nota asociada a una reunión.
 */
public class Nota {
    private String contenido;

    /**
     * Constructor para crear una nueva nota.
     *
     * @param contenido El contenido de la nota.
     */
    public Nota(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene el contenido de la nota.
     *
     * @return El contenido de la nota.
     */
    public String getContenido() {
        return contenido;
    }

    @Override
    public String toString() {
        return "Nota: " + contenido;
    }
}
