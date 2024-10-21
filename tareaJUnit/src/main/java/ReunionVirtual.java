import java.time.Duration;
import java.time.Instant;
import java.util.Date;

 /**
 * Clase que representa una reunión virtual.
 */
class ReunionVirtual extends Reunion {
    private String enlace;

    /**
     * Constructor para crear una nueva reunión virtual.
     *
     * @param fecha           La fecha de la reunión.
     * @param horaPrevista    La hora prevista de inicio de la reunión.
     * @param duracionPrevista La duración prevista de la reunión.
     * @param tipo            El tipo de la reunión.
     * @param enlace          El enlace para unirse a la reunión virtual.
     */
    public ReunionVirtual(Date fecha, Instant horaPrevista, Duration duracionPrevista, tipoReunion tipo, String enlace) {
        super(fecha, horaPrevista, duracionPrevista, tipo);
        this.enlace = enlace;
    }

    /**
     * Inicia la reunión y registra la hora de inicio.
     */
    @Override
    public void iniciar() {
        setHoraInicio(Instant.now());
        System.out.println("Iniciando reunión en enlace: " + enlace);
    }

    /**
     * Finaliza la reunión y registra la hora de finalización.
     */
    @Override
    public void finalizar() {
        if (getHoraInicio() != null) {
            setHoraFin(Instant.now());
            System.out.println("Finalizando reunión en la sala: " + enlace +
                    " a las: " + getHoraFin());
        } else {
            System.out.println("La reunión no ha sido iniciada.");
        }
    }

    /**
     * Obtiene el enlace de la reunión virtual.
     *
     * @return El enlace de la reunión.
     */
    public String getEnlace() {
        return enlace;
    }

    @Override
    public String toString() {
        return super.toString() + ", enlace= '" + enlace + '\'' + '}';
    }
}
