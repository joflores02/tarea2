import java.util.Date;
import java.time.Instant;
import java.time.Duration;

/**
 * Clase que representa una reunión presencial.
 */
class ReunionPresencial extends Reunion {
    private String sala;

    /**
     * Constructor para crear una nueva reunión presencial.
     *
     * @param fecha          La fecha de la reunión.
     * @param horaPrevista   La hora prevista de inicio de la reunión.
     * @param duracionPrevista La duración prevista de la reunión.
     * @param Tipo           El tipo de la reunión.
     * @param sala           La sala donde se llevará a cabo la reunión.
     */
    public ReunionPresencial(Date fecha, Instant horaPrevista, Duration duracionPrevista, tipoReunion Tipo, String sala) {
        super(fecha, horaPrevista, duracionPrevista, Tipo);
        this.sala = sala;
    }

    /**
     * Inicia la reunión y registra la hora de inicio.
     */
    @Override
    public void iniciar() {
        setHoraInicio(Instant.now());
        System.out.println("Iniciando reunión en la sala: " + sala);
    }

    /**
     * Finaliza la reunión y registra la hora de finalización.
     */
    @Override
    public void finalizar() {
        if (getHoraInicio() != null) {
            setHoraFin(Instant.now());
            System.out.println("Finalizando reunión en la sala: " + sala +
                    " a las: " + getHoraFin());
        } else {
            System.out.println("La reunión no ha sido iniciada.");
        }
    }

    /**
     * Obtiene la sala donde se lleva a cabo la reunión.
     *
     * @return La sala de la reunión.
     */
    public String getSala() {
        return sala;
    }

    @Override
    public String toString() {
        return super.toString() + ", sala='" + sala + '\'' + '}';
    }
}
