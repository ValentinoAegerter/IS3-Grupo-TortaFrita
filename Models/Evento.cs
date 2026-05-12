public class Evento {
    public int Id { get; set; }
    public string Nombre { get; set; }
    public int CupoMaximo { get; set; }
    public int CupoMinimo { get; set; }
    public DateTime FechaLimiteInscripcion { get; set; }
    public DateTime FechaEvento { get; set; }
}