/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import servico.ClienteService;
import servico.Dados;
import servico.ItemPedidoService;
import servico.PedidoService;
import servico.ProdutoService;

/**
 *
 * @author mateu
 */
@ManagedBean(name="ItemPedidoMB")
@SessionScoped
public class ItemPedidoMB {
    private ItemPedidoService itemService = new ItemPedidoService();
    private ProdutoService produtoService = new ProdutoService();
    private ClienteService clienteService = new ClienteService();
    private PedidoService pedidoService = new PedidoService();
    private List<ItemPedido> itens = new ArrayList();
    private int qtdItem;
    private Cliente cli;
    private Pedido ped;
    private Produto produtoEscolhido;
    private Pedido pedidoAtual;
    private ItemPedido itemP = new ItemPedido();
    private double total;
    public boolean b = false;
    public String errorMsg;
    long cliCod;
    
    public void salvar(){
        if(ped == null){
            ped = new Pedido();
        }
        
        ped.setCliente(cli);
        
        itemP.setProd(produtoEscolhido);
        itemP.setPed(ped);
        itemService.produtoNoCarrinho(itemP, itens, qtdItem);
        total += qtdItem * itemP.getProd().getPreco();
        
        itemP = new ItemPedido();
    }
    
    public String finalizar(){
        Calendar calendar = new GregorianCalendar();
        Date date = new Date();
        calendar.setTime(date);
        
        List pedidos = pedidoService.getAll(Pedido.class);
        long pkPedido = pedidos.size() + 1;
        
        ped.setData(calendar.getTime());
        for(ItemPedido item: itens){
            item.getPed().setNumero(pkPedido);
            itemService.save(item);
        }
        
        ped = new Pedido();
        
        return "pedidok.xhtml";
    }
    
    public String verificaCli(){
        if (clienteService.clienteCadastrado(cliCod)){
                if (clienteService.clienteApto(cliCod)){
                    setTotal();
                    cli = clienteService.getById(Cliente.class, cliCod);
                    b = true;
                    return "itemped.xhtml";
                }
                else{
                    errorMsg = "Cliente bloqueado!";
                    b = false;
                    return "logincli.xhtml";
                }
                    
        }
        else{
            errorMsg = "Cliente nao cadastrado!";
            b = false;
            return "logincli.xhtml";
        }
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public long getCliCod() {
        return cliCod;
    }

    public void setCliCod(long cliCod) {
        this.cliCod = cliCod;
    }

    public int getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(int qtdItem) {
        this.qtdItem = qtdItem;
    }
    
    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }   
    
    public double getTotal() {
        return total;
    }
    
    public ItemPedidoService getItemService() {
        return itemService;
    }

    public void setItemService(ItemPedidoService itemService) {
        this.itemService = itemService;
    }

    public List<Produto> getProdutos(){
      return produtoService.getAll(Produto.class);
    }
    
    public ProdutoService getProdutoService() {
        return produtoService;
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public Produto getProdutoEscolhido() {
        return produtoEscolhido;
    }

    public void setProdutoEscolhido(Produto produtoEscolhido) {
        this.produtoEscolhido = produtoEscolhido;
    }

//    public ItemPedido getSelectedItem() {
//        return selectedItem;
//    }
//
//    public void setSelectedItem(ItemPedido selectedItem) {
//        this.selectedItem = selectedItem;
//    }

    public ItemPedido getItemP() {
        return itemP;
    }

    public void setItemP(ItemPedido itemP) {
        this.itemP = itemP;
    }

    public void setTotal() {
        this.total = 0;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(ItemPedido item) {
        this.itens.add(item);
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }
    
    public void remove(ItemPedido prod){
        itemService.remove(prod);
    }
    
}
