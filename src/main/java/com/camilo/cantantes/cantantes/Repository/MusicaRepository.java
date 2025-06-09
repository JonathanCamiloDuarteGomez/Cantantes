package com.camilo.cantantes.cantantes.Repository;

import com.camilo.cantantes.cantantes.model.Cancion;
import com.camilo.cantantes.cantantes.model.Cantante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository  extends JpaRepository<Cantante,Long> {
    //Derived Queries
    Optional<Cantante> findByNombre(String nombre);

    Optional<Cantante> findByIdCantante(Integer id);
    //SQL puro
    @Query(value = "SELECT nombre FROM cantantes;", nativeQuery = true)
    List<String> obtenerNombresDeCantantes();
    //JPQL
    //: significa el dato que estoy recibiendo.
    @Query("SELECT s.nombre, c.titulo FROM Cantante s RIGHT JOIN Cancion c ON c.cantante.idCantante = s.idCantante WHERE s.nombre = :nombre")
    List<Object[]> cancionesDelAutor( String nombre);


}
