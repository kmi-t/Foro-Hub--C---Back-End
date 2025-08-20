package alura.challenge.ForoHub.controller;

import alura.challenge.ForoHub.dto.TopicoRequest;
import alura.challenge.ForoHub.model.Topico;
import alura.challenge.ForoHub.service.TopicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicoService topicoService;

    public TopicosController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos() {
        List<Topico> topicos = topicoService.listar();
        return ResponseEntity.ok(topicos);
    }

    @PostMapping
    public ResponseEntity<Topico> crearTopico(@RequestBody TopicoRequest request) {
        try {
            Topico nuevoTopico = topicoService.crear(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTopico);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopico(@PathVariable Long id) {
        return topicoService.obtenerPorId(id)
                .map(topico -> ResponseEntity.ok(topico))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody TopicoRequest request) {
        try {
            Topico actualizado = topicoService.actualizar(id, request);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        try {
            topicoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

