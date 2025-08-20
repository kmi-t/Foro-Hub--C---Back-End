package alura.challenge.ForoHub.controller;

import alura.challenge.ForoHub.dto.TopicoRequest;
import alura.challenge.ForoHub.model.Topico;
import alura.challenge.ForoHub.service.TopicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoService.listar();
    }

    @PostMapping
    public Topico crearTopico(@RequestBody TopicoRequest request) {
        return topicoService.crear(request);
    }
}
