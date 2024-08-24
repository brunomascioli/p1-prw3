package br.edu.ifsp.TrabPRW3.services;

import br.edu.ifsp.TrabPRW3.dao.AlunoDAO;
import br.edu.ifsp.TrabPRW3.models.Aluno;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AlunoService {

    private final AlunoDAO dao;
    private final Scanner scanner;

    public AlunoService(AlunoDAO dao, Scanner scanner){
        this.dao = dao;
        this.scanner = scanner;
    }
    public void cadastrar(){
        System.out.println("\nCADASTRO DE ALUNO\n");
        Aluno novoAluno = createAluno();
        try {
            if(dao.save(novoAluno)) {
                System.out.println("\nAluno cadastrado com sucesso!\n");
            }
        } catch (Exception e) {
            System.out.println("\nEmail, nome e RA devem ser unicos\n");
        }
    }

    public void excluir() {
        System.out.println("\nEXCLUIR ALUNO\n");
        System.out.print("Digite o nome do aluno que deseja excluir: ");

        String nome = scanner.next();
        if (dao.delete(nome)){
            System.out.println("\nAluno removido com sucesso!\n");
        } else {
            System.out.println("\nNome não encontrado!\n");
        }
    }

    public void alterar(){
        System.out.println("\nALTERAR ALUNO\n");
        System.out.print("Digite o nome do aluno que deseja alterar: ");
        String nome = scanner.next();

        Optional<Aluno> aluno = dao.findByName(nome);
        if (aluno.isEmpty()) {
            System.out.println("\nAluno não encontrado!\n");
            return;
        }

        System.out.println("Dados do aluno:");
        System.out.println(aluno.get());
        System.out.println("\nNOVOS DADOS:");

        Aluno alunoAtualizado = createAluno();
        alunoAtualizado.setId(aluno.get().getId());

        if(dao.update(alunoAtualizado)){
            System.out.println("\nAluno atualizado com sucesso!\n");
        } else {
            System.out.println("\nNome inserido já está cadastrado!\n");
        }

    }

    public void buscarPeloNome() {
        System.out.println("\nBUSCAR ALUNO\n");
        System.out.print("Digite o nome do aluno que deseja buscar: ");
        String nome = scanner.next();
        Optional<Aluno> aluno = dao.findByName(nome);
        if (aluno.isPresent()) {
            System.out.println(aluno.get());
        } else {
            System.out.println("\nAluno não encontrado!\n");
        }
    }

    public void listarAlunos() {
        System.out.println("\nLISTA DE ALUNOS\n");
        List<Aluno> alunos = dao.findAll();
        alunos.forEach(a -> {
            System.out.print(a);
            System.out.print(STR."\nMédia: \{a.getMedia()}");
            System.out.println(STR."\nStatus: \{a.getStatus()}\n");
        });
    }

    private Aluno createAluno() {
        System.out.print("Digite o nome: ");
        String nome = scanner.next();

        System.out.print("Digite o RA: ");
        String ra = scanner.next();

        System.out.print("Digite o email: ");
        String email = scanner.next();

        System.out.print("Digite a nota 1: ");
        Double nota1 = scanner.nextDouble();

        System.out.print("Digite a nota 2: ");
        Double nota2 = scanner.nextDouble();

        System.out.print("Digite a nota 3: ");
        Double nota3 = scanner.nextDouble();

        return new Aluno(email, ra, nome, nota1, nota2, nota3);
    }

}