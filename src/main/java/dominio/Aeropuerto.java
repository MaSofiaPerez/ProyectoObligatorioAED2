
package dominio;

public class Aeropuerto implements Comparable<Aeropuerto> {
   private String codigo;
    private String nombre;

    public Aeropuerto(String codigo, String nombre) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   @Override
    public int compareTo(Aeropuerto otra) {
        return this.codigo.compareTo(otra.getCodigo());
        }
}
