package alura.challenge.ForoHub.service;

import alura.challenge.ForoHub.dto.TopicoRequest;
import alura.challenge.ForoHub.model.Topico;
import alura.challenge.ForoHub.repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    // Listar todos los tópicos
    public List<Topico> listar() {
        return topicoRepository.findAll();
    }

    // Crear un nuevo tópico
    public Topico crear(TopicoRequest request) {
        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setAutor(request.getAutor());
        topico.setCurso(request.getCurso());
        return topicoRepository.save(topico);
    }

    // Obtener un tópico por ID
    public Optional<Topico> obtenerPorId(Long id) {
        return topicoRepository.findById(id);
    }

    // Actualizar un tópico existente
    public Topico actualizar(Long id, TopicoRequest request) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.setTitulo(request.getTitulo());
            topico.setMensaje(request.getMensaje());
            topico.setAutor(request.getAutor());
            topico.setCurso(request.getCurso());
            return topicoRepository.save(topico);
        }
        return null; // O lanzar excepción según tu preferencia
    }

    // Eliminar un tópico
    public void eliminar(Long id) {
        topicoRepository.deleteById(id);
    }
}
