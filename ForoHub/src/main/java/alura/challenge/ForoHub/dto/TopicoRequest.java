package alura.challenge.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicoRequest {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    @NotBlank(message = "El estado es obligatorio")
    private String status;

    @NotBlank(message = "El autor es obligatorio")
    private String autor;

    @NotBlank(message = "El curso es obligatorio")
    private String curso;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
}

