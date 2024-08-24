package br.edu.ifsp.TrabPRW3;

import br.edu.ifsp.TrabPRW3.services.AlunoService;
import br.edu.ifsp.TrabPRW3.dao.AlunoDAO;

import java.util.InputMismatchException;
import java.util.Scanner;


public class App {
    private static String MENU =
            """
            --------- CADASTRO DE ALUNOS ------------

            1 - Cadastrar aluno
            2 - Excluir aluno
            3 - Alterar aluno
            4 - Buscar aluno pelo nome
            5 - Listar alunos (com status aprovação)
            6 - Fim
                
            -----------------------------------------
            """;

    private final AlunoDAO dao;

    private Scanner scanner;
    public App(AlunoDAO dao) {
        this.dao = dao;
    }

    public void execute() {
        this.scanner = new Scanner(System.in);

        int userOption;
        boolean running = true;

        AlunoService alunoService = new AlunoService(dao, scanner);

        while (running) {

            System.out.println(MENU);

            userOption = getUserOption();

            switch (userOption) {
                case 1 -> alunoService.cadastrar();
                case 2 -> alunoService.excluir();
                case 3 -> alunoService.alterar();
                case 4 -> alunoService.buscarPeloNome();
                case 5 -> alunoService.listarAlunos();
                case 6 -> {
                    System.out.println("Encerrando o programa...");
                    running = false;
                }
                default -> System.out.println("\nOpção Inválida!\n");
            }
        }
        scanner.close();
    }

    private int getUserOption(){
        while (true) {
            System.out.print("Sua opção: ");
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida!\n");
                scanner.next();
            }
        }
    }

}
