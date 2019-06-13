package managedbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Categoria;
import org.primefaces.event.RowEditEvent;
import servico.CategoriaService;

/**
 *
 * @author victo
 */
@ManagedBean(name="CategoriaMB")
@SessionScoped
public class CategoriaMB { 
    private CategoriaService servico = new CategoriaService();
    private List<Categoria> categorias;
    private Categoria cat = new Categoria();
    private EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("PrAula1610PU");
    boolean vazia = false, existe = false;

    public boolean isVazia() {
        return vazia;
    }

    public void setVazia(boolean vazia) {
        this.vazia = vazia;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    public void salvar(){
        vazia = false;
        existe = false;
        if (!servico.verificaSeExiste(cat.getDescricao())){
            if (cat.getDescricao() != ""){
                servico.save(cat);
                this.cat = new Categoria();
            }
            else
                vazia = true;              
        }                    
        else
            existe = true;
    }
    
    public List<Categoria> getCategorias(){
        this.categorias = servico.getAll(Categoria.class);
        return categorias;
    }

    public Categoria getCat() {
        return cat;
    }

    public void remove(Categoria c){
        servico.remove(c);
    }
    
    public void setCat(Categoria cat) {
        this.cat = cat;
    }
    
    public void onRowEdit(RowEditEvent event) {
        Categoria cate = ((Categoria) event.getObject());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            em.merge(cate);
        em.getTransaction().commit();
        em.close();
        
        FacesMessage msg = new FacesMessage("Categoria editada", ((Categoria) event.getObject()).getDescricao());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel (RowEditEvent event){
        FacesMessage msg = new FacesMessage("Edição Cancelada",((Categoria) event.getObject()).getDescricao());
        FacesContext.getCurrentInstance().addMessage(null,msg);
    }
    
}
