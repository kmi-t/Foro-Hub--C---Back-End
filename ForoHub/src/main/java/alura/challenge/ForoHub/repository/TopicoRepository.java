package alura.challenge.ForoHub.repository;

import alura.challenge.ForoHub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND FUNCTION('YEAR', t.fechaCreacion) = :anio")
    Page<Topico> buscarPorCursoYAnio(@Param("curso") String curso, @Param("anio") int anio, Pageable pageable);
    List<Topico> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);

}



