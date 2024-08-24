package br.edu.ifsp.TrabPRW3.dao;

import br.edu.ifsp.TrabPRW3.models.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class AlunoDAO {
    private final EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public boolean save(Aluno aluno) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(aluno);
        transaction.commit();
        return true;
    }

    public Optional<Aluno> findByName(String nome) {
        try {
            String jpqlQuery = "SELECT a FROM Aluno a WHERE a.nome = :nome";
            return Optional.ofNullable(em.createQuery(jpqlQuery, Aluno.class)
                    .setParameter("nome", nome)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Aluno> findAll(){
        String jpqlQuery = "SELECT a FROM Aluno a";
        return em.createQuery(jpqlQuery, Aluno.class).getResultList();
    }

    public boolean delete(String nome) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Optional<Aluno> aluno = findByName(nome);
            if (aluno.isPresent()){
                em.remove(aluno.get());
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public boolean update(Aluno aluno) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.merge(aluno);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @FunctionalInterface
    private interface EntityManagerConsumer {
        void accept(EntityManager em);
    }
}
