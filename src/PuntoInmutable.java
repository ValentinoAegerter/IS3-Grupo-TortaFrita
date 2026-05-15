public final class PuntoInmutable {
    private final double x;
    private final double y;

    public PuntoInmutable(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        // Use Double.toString to keep a reasonable representation
        return String.format("Punto(x=%s, y=%s)", Double.toString(x), Double.toString(y));
    }

    // Retorna un nuevo punto desplazado
    public PuntoInmutable trasladar(double dx, double dy) {
        return new PuntoInmutable(this.x + dx, this.y + dy);
    }

    // Retorna un nuevo punto escalado (multiplica ambas coordenadas)
    public PuntoInmutable escalar(double factor) {
        return new PuntoInmutable(this.x * factor, this.y * factor);
    }

    // Refleja respecto al eje X (invierte la coordenada y)
    public PuntoInmutable reflejarEjeX() {
        return new PuntoInmutable(this.x, -this.y);
    }

    // Refleja respecto al eje Y (invierte la coordenada x)
    public PuntoInmutable reflejarEjeY() {
        return new PuntoInmutable(-this.x, this.y);
    }

    // Distancia al origen
    public double distanciaAlOrigen() {
        return Math.sqrt(x * x + y * y);
    }

    // Distancia euclídea a otro punto
    public double distanciaA(PuntoInmutable otro) {
        double dx = this.x - otro.x;
        double dy = this.y - otro.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Método main de demostración
    public static void main(String[] args) {
        PuntoInmutable original = new PuntoInmutable(3.0, 4.0);
        System.out.println("Punto original: " + original);

        // Transformaciones encadenadas
        PuntoInmutable transformado = original.trasladar(1.0, 1.0).escalar(2.0);
        System.out.println("Después de trasladar(1,1) y escalar(2): " + transformado);

        // Verificar que el original no cambió
        System.out.println("Verificar original inmutable: " + original);

        // Otras transformaciones
        PuntoInmutable reflejadoX = original.reflejarEjeX();
        PuntoInmutable reflejadoY = original.reflejarEjeY();
        System.out.println("Reflejado en eje X: " + reflejadoX);
        System.out.println("Reflejado en eje Y: " + reflejadoY);

        // Distancias
        System.out.println("Distancia del original al origen: " + original.distanciaAlOrigen());
        System.out.println("Distancia entre original y transformado: " + original.distanciaA(transformado));
    }
}
