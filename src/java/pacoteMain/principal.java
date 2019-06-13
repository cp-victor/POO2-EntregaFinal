/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteMain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Pedido;
import modelo.ProdutoExportacao;
import modelo.ProdutoMercadoInterno;

/**
 *
 * @author mateu
 */

public class principal {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrAula1610PU");
        EntityManager em = emf.createEntityManager();
        
        for(int i=0; i<3; i++)
        {
            Categoria c1 = new Categoria();
            Cliente cl1 = new Cliente();
            Pedido p = new Pedido();
            ProdutoExportacao pe = new ProdutoExportacao();
            ProdutoMercadoInterno pmi = new ProdutoMercadoInterno();
            
            c1.setDescricao("Descricao" + i + "");
            
            cl1.setNome("Nome" + i + "");
            
            pe.setNome("Nome" + i + "");
            pe.setDestino("Destino" + i + "");
           // pe.setPedidos(p);
            
            pmi.setNome("Nome" + i + "");
            pmi.setIncentivo(true);
            
            
            em.getTransaction().begin();
                em.persist(pe);
                em.persist(pmi);
                em.persist(c1);
                em.persist(cl1);
                em.persist(p);
            em.getTransaction().commit();
        }
            em.close();
    }
}
