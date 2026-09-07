/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import excepciones.CalificacionInvalidaException;
import excepciones.CodigoDuplicadoException;
import excepciones.EstudianteNoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import modelo.Calificacion;
import modelo.Estudiante;




public class GestionEstudiantes {
  private List<Estudiante> listaEstudiantes;
  public GestionEstudiantes() 
  { listaEstudiantes = new ArrayList<>(); }
  
  // Registrar un estudiante
  public void agregarEstudiante(Estudiante estudiante)
          throws CodigoDuplicadoException { 
      for (Estudiante estudianteActual : listaEstudiantes) { 
          if (estudianteActual.getCodigo() .equalsIgnoreCase(estudiante.getCodigo())) { 
              throw new CodigoDuplicadoException(
                      "El código ya existe: " + estudiante.getCodigo()
              ); 
          }
      }
      listaEstudiantes.add(estudiante); }
  
  // Mostrar todos los estudiantes
  public List<Estudiante> obtenerEstudiantes() {
      return listaEstudiantes;
  }
  // Buscar estudiante por código
  public Estudiante buscarPorCodigo(String codigo)
          throws EstudianteNoEncontradoException {
      for (Estudiante estudiante : listaEstudiantes) {
          if (estudiante.getCodigo().equalsIgnoreCase(codigo)) {
              return estudiante;
          }
      }
      throw new EstudianteNoEncontradoException(
              "No se encontró el estudiante con código: " + codigo
              );
  }
  // Agregar una calificación
  public void agregarCalificacion(String codigo, Calificacion calificacion)
          throws EstudianteNoEncontradoException,
            CalificacionInvalidaException {
      double nota = calificacion.getNota();
      if (nota < 0 || nota > 20) {
          throw new CalificacionInvalidaException(
                  "La nota debe estar entre 0 y 20."
                  );
      }
      Estudiante estudiante = buscarPorCodigo(codigo);
      estudiante.agregarCalificacion(calificacion);
      
  }
  // Obtener el promedio del estudiante
  public double obtenerPromedio(String codigo)
          throws EstudianteNoEncontradoException {
      Estudiante estudiante = buscarPorCodigo(codigo);
      return estudiante.calcularPromedio();
  }
}
