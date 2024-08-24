package br.edu.ifsp.TrabPRW3;

import br.edu.ifsp.TrabPRW3.dao.AlunoDAO;
import br.edu.ifsp.TrabPRW3.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        // Feito por: Bruno Mascioli de Souza - SC3038025
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDAO alunoDAO = new AlunoDAO(em);
        new App(alunoDAO).execute();
    }
}
