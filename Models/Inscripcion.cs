public class Inscripcion {
    public int Id { get; set; }
    public int EventoId { get; set; }
    public int ParticipanteId { get; set; }
    public DateTime FechaRegistro { get; set; }
    public string Estado { get; set; } // Activo/Cancelado
}