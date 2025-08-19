package alura.challenge.ForoHub.controller;

import alura.challenge.ForoHub.dto.TopicoRequest;
import alura.challenge.ForoHub.dto.TopicoResponse;
import alura.challenge.ForoHub.model.Topico;
import alura.challenge.ForoHub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public Page<TopicoResponse> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) String fechaInicio, // formato: "2025-08-01T00:00"
            @RequestParam(required = false) String fechaFin,    // formato: "2025-08-31T23:59"
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.by("fechaCreacion").ascending());
        Page<Topico> pageResult;

        if (curso != null && anio != null) {
            pageResult = topicoRepository.buscarPorCursoYAnio(curso, anio, pageRequest);
        } else if (fechaInicio != null && fechaFin != null) {
            LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
            LocalDateTime fin = LocalDateTime.parse(fechaFin);
            List<Topico> resultados = topicoRepository.findByFechaCreacionBetween(inicio, fin);
            int start = Math.min((int) pageRequest.getOffset(), resultados.size());
            int end = Math.min(start + pageRequest.getPageSize(), resultados.size());
            pageResult = new PageImpl<>(resultados.subList(start, end), pageRequest, resultados.size());
        } else {
            pageResult = topicoRepository.findAll(pageRequest);
        }

        return pageResult.map(t -> new TopicoResponse(
                t.getId(),
                t.getTitulo(),
                t.getMensaje(),
                t.getFechaCreacion(),
                t.getStatus(),
                t.getAutor(),
                t.getCurso()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> detalle(@PathVariable Long id) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Topico t = topicoOpt.get();
        TopicoResponse response = new TopicoResponse(
                t.getId(), t.getTitulo(), t.getMensaje(),
                t.getFechaCreacion(), t.getStatus(), t.getAutor(), t.getCurso()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TopicoResponse> crear(@RequestBody @Valid TopicoRequest request) {
        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setCurso(request.getCurso());
        topico.setAutor(request.getAutor());
        topico.setStatus(request.getStatus());

        Topico nuevo = topicoRepository.save(topico);

        TopicoResponse response = new TopicoResponse(
                nuevo.getId(), nuevo.getTitulo(), nuevo.getMensaje(),
                nuevo.getFechaCreacion(), nuevo.getStatus(), nuevo.getAutor(), nuevo.getCurso()
        );

        return ResponseEntity.created(URI.create("/topicos/" + nuevo.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> actualizar(@PathVariable Long id,
                                                     @RequestBody @Valid TopicoRequest request) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Topico topico = topicoOpt.get();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setCurso(request.getCurso());
        topico.setAutor(request.getAutor());
        topico.setStatus(request.getStatus());

        Topico actualizado = topicoRepository.save(topico);

        TopicoResponse response = new TopicoResponse(
                actualizado.getId(), actualizado.getTitulo(), actualizado.getMensaje(),
                actualizado.getFechaCreacion(), actualizado.getStatus(),
                actualizado.getAutor(), actualizado.getCurso()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isEmpty()) return ResponseEntity.notFound().build();

        topicoRepository.delete(topicoOpt.get());
        return ResponseEntity.noContent().build();
    }

}


