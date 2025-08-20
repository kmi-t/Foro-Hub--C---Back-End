package alura.challenge.ForoHub.model;

import jakarta.persistence.*;

@Entity
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private String autor;
    private String curso;
    private String status;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }

    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getAutor() { return autor; }

    public void setAutor(String autor) { this.autor = autor; }

    public String getCurso() { return curso; }

    public void setCurso(String curso) { this.curso = curso; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
