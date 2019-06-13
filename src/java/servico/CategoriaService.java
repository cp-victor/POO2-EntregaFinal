/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import modelo.Categoria;

/**
 *
 * @author victo
 */
public class CategoriaService extends DAO<Categoria, Long>{
    public Categoria getByName(String name)
    {
        List<Categoria> a = getAll(Categoria.class);
         for(Categoria e: a){
           if(e.getDescricao().equals(name))
               return e;
        }
        return null;
    }
    
    public boolean verificaSeExiste(String desc){
            boolean ret = false;
            List<Categoria> listaCat;
            
            listaCat = getAll(Categoria.class);
            
            for(Categoria c : listaCat)
            {
                if (c.getDescricao().toUpperCase().equals(desc.toUpperCase()))
                    ret = true;                    
            }
            
            return ret;
        }
}
