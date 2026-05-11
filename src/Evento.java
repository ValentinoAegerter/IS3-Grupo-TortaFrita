import java.time.LocalDate;

public class Evento {
    private String titulo;
    private String tipo;
    private LocalDate fechaRealizacion;
    private Integer cupoMinimo;
    private Integer cupoMaximo;
    private String estadoPublicacion;

    public Evento(String titulo, String tipo, LocalDate fechaRealizacion) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.fechaRealizacion = fechaRealizacion;
        this.estadoPublicacion = "BORRADOR";
    }

    // Getters y setters omitidos por brevedad
}