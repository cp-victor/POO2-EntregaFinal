/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Cliente;
import org.primefaces.event.RowEditEvent;
import servico.ClienteService;
import servico.ItemPedidoService;

/**
 *
 * @author 171095
 */
@ManagedBean(name="ClienteMB", eager = true)
@SessionScoped
public class ClienteMB {
    private ClienteService servico = new ClienteService();
    private ItemPedidoService servicoItem = new ItemPedidoService();
    private Cliente cli = new Cliente();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrAula1610PU");
    private long aux;
    boolean b = true;
    private int radioStat;
    boolean camposValidos = true;
    public String errorMsg;
    
    @ManagedProperty(value="#{ItemPedidoMB}")
    private ItemPedidoMB itemPedidoMB;

    public boolean isCamposValidos() {
        return camposValidos;
    }

    public void setCamposValidos(boolean camposValidos) {
        this.camposValidos = camposValidos;
    }

    public int getRadioStat() {
        return radioStat;
    }

    public void setRadioStat(int radioStat) {
        this.radioStat = radioStat;
    }   
    
    public void salvar(){
        camposValidos = servico.validaCampos(cli, radioStat);
        if (camposValidos){
            if (radioStat == 1)
                cli.setStatus(true);
            else
                cli.setStatus(false);             
        
            servico.save(cli);
            cli = new Cliente();
        }        
    }

    

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }   
    
    public long getAux() {
        return aux;
    }
    
    public void setAux(long aux){
        this.aux = aux;
    }
    
    public List<Cliente> getClientes(){
        return servico.getAll(Cliente.class);
    }

    public Cliente getCli() {
        return cli;
    }

    public void setItemPedidoMB(ItemPedidoMB itemPedidoMB) {
        this.itemPedidoMB = itemPedidoMB;
    }
    
    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public boolean isB() {
        return b;
    }
    
    public void remove(Cliente c){
        servico.remove(c);
    }
    
    public void onRowEdit(RowEditEvent event) {
        Cliente clie = ((Cliente) event.getObject());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            em.merge(clie);
        em.getTransaction().commit();
        em.close();
        
        FacesMessage msg = new FacesMessage("Cliente Editado", ((Cliente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel (RowEditEvent event){
        FacesMessage msg = new FacesMessage("Edição Cancelada",((Cliente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null,msg);
    }
    
    public String statusCliente(Cliente c)
    {
        String ret;
        
        if (c.getStatus())
            ret = "Liberado";
        else
            ret = "Bloqueado";
                    
        return ret;
    }
    
    
    
}
