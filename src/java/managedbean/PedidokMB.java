/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import servico.ClienteService;
import servico.Dados;
import servico.ItemPedidoService;
import servico.PedidoService;

/**
 *
 * @author mateu
 */
@ManagedBean(name="PedidokMB")
@SessionScoped
public class PedidokMB {
    private ClienteService clienteService = new ClienteService();
    private PedidoService pedidoService = new PedidoService();
    private ItemPedidoService itemService = new ItemPedidoService();
    private Cliente cli;
    private long pkCli;
    public boolean b = false;
    public String errorMsg;
    private long pkItem;
    private VerItensMB vmb = new VerItensMB();
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String verificaCli(){
        if (clienteService.clienteCadastrado(pkCli)){
                if (clienteService.clienteApto(pkCli)){
                    cli = clienteService.getById(Cliente.class, pkCli);
                    b = true;
                    return "MostrarPed.xhtml";
                }
                else{
                    errorMsg = "Cliente bloqueado!";
                    b = false;
                    return "MostrarPedLogin.xhtml";
                }
                    
        }
        else{
            errorMsg = "Cliente nao cadastrado!";
            b = false;
            return "logincli.xhtml";
        }
    }

    public long getPkItem() {
        return pkItem;
    }

    public void setPkItem(long pkItem) {
        this.pkItem = pkItem;
    }    
    
    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
    
    public String getClienteNome(){
        return pedidoService.getById(Pedido.class, pkCli).getCliente().getNome();
    }

    public List<Pedido> getClientePedidos(){
        return pedidoService.getAll(Pedido.class);
    }
    /*
    public List<ItemPedido> getItens(){
        return itemService.getById(ItemPedido.class, pkItem);
    }*/
    
    public String ver(long x){
        //this.pkPed = x;
        return "verItens.xhtml";
    }

    public long getPkCli() {
        return pkCli;
    }

    public void setPkCli(long pkCli) {
        this.pkCli = pkCli;
    }
}
