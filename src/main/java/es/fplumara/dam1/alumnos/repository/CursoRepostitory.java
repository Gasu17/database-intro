package es.fplumara.dam1.alumnos.repository;

import es.fplumara.dam1.alumnos.model.Curso;

import java.util.List;

public interface CursoRepostitory {

  public void insertCurso (Curso curso ) ;

  public void eliminarNombre (String texto);

  public List<Curso> listarPorEstado (boolean estado);

  public List<Curso> listarOrdenadoPor(String campo , String orden);

}
