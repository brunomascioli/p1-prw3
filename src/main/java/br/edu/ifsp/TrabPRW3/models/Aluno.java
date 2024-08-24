package br.edu.ifsp.TrabPRW3.models;

import jakarta.persistence.*;

import java.util.regex.Pattern;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String ra;
    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double nota1;

    @Column(nullable = false)
    private Double nota2;

    @Column(nullable = false)
    private Double nota3;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");


    public Aluno(String email, String ra, String nome, Double nota1, Double nota2, Double nota3) {
        this.email = email;
        this.ra = ra;
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public Aluno() {}

    @PrePersist
    @PreUpdate
    private void validate() {
        if (email != null && !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido");
        }

    }

    public double getMedia() {
        double media = (nota1 + nota2 + nota3) / 3;
        return Math.round(media * 100) / 100.0;
    }

    public String getStatus() {
        double media = getMedia();
        if(media >= 6) {
            return "Aprovado";
        } else if (media >= 4) {
            return "Recuperação";
        } else {
            return "Reprovado";
        }
    }

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email;}

    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getNota1() { return nota1; }
    public void setNota1(Double nota1) { this.nota1 = nota1; }

    public Double getNota2() { return nota2; }
    public void setNota2(Double nota2) { this.nota2 = nota2; }

    public Double getNota3() { return nota3; }
    public void setNota3(Double nota3)  { this.nota3 = nota3; }

    @Override
    public String toString() {
        return String.format(
                "Nome: %s\n" +
                "Email: %s\n" +
                "RA: %s\n" +
                "Notas: %.2f - %.2f - %.2f",
                nome, email, ra, nota1, nota2, nota3
        );
    }

}
