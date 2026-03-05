package es.fplumara.dam1.alumnos.model;

public class Curso {

    private Integer id;
    private Boolean estado  ;
    private String nombre;

    public int getId() {
        return id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public Curso( Integer id , Boolean estado, String nombre) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
    }
}
