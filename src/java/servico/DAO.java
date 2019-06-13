/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import modelo.Cliente;

/**
 * 
 * @author mateus
 * @param <T>
 */
public class DAO <T, I extends Serializable> {
    private static EntityManagerFactory emf;
    private EntityManager em;
    
    public DAO(){
        emf = Persistence.createEntityManagerFactory("PrAula1610PU");
    }
        
    public T save(T entity){
        T saved = null;
                
        getEntityManager().getTransaction().begin();
        saved = getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();
        
        return saved;
    }
    
    private EntityManager open(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }
    
    public void remove(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(entity);
        getEntityManager().getTransaction().commit();
    }
    
    public T getById(Class<T> classe, I pk){
        try {
            return getEntityManager().find(classe, pk);
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<T> getAll(Class<T> classe){
        return getEntityManager().
                createQuery(
                        "Select o from "
                        + classe.getSimpleName() + " o")
                .getResultList();
    }
    
    public EntityManager getEntityManager(){
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }
    
    public void closeEntityManager() {
        if (em != null) {
            em.close();
        }
        em = null;
    }
    
}
