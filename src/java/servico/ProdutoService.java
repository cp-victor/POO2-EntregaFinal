package servico;

import java.util.List;
import modelo.Categoria;
import modelo.Produto;
/**
 *
 * @author victo
 */
public class ProdutoService extends DAO<Produto, Long>{
  
    public Produto getByName(String name)
    {
        
        List<Produto> a = getAll(Produto.class);
        for(Produto p: a){
           if(p.getNome().equals(name))
               return p;
        }
        return null;
    }
    
    public boolean verificaCampos(Produto p){
        boolean ret = true;        
        
        if (p.getNome().isEmpty() || p.getPreco() == 0 || p.getMoeda() == 0)
            ret = false;
        
        return ret;
    }
    
    public boolean prodExiste(Produto p){
        boolean ret = false;
        
        List<Produto> produtos = getAll(Produto.class);
        
        for (Produto pr : produtos)
        {
            if (pr.getNome().toUpperCase().equals(p.getNome().toUpperCase()))
                ret = true;
        }
        
        return ret;
    }
     
}
