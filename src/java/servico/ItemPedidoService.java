/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.ItemPedido;
import modelo.Pedido;

/**
 *
 * @author mateu
 */
public class ItemPedidoService extends DAO<ItemPedido, Long>{
    public void produtoNoCarrinho(ItemPedido ip, List<ItemPedido> lista, int qtdItem){
        boolean FoundRep = false;
        
        for (ItemPedido ipd : lista){
            if (ipd.getProd().getNome().equals(ip.getProd().getNome())){
                ipd.setQuantidade(ipd.getQuantidade() + qtdItem);
                FoundRep = true;
            }
        }
        if(FoundRep == false){
            ip.setQuantidade(qtdItem);
            lista.add(ip);
        }
    }
}