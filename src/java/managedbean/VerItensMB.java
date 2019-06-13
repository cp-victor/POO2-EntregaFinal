/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Pedido;
import servico.Dados;

/**
 *
 * @author victo
 */
@ManagedBean(name="verItensMB")
@SessionScoped
public class VerItensMB {
    private int cli = Dados.getCliFinal();
    private List<Pedido> listaPed = Dados.getLISTA_CLIENTES().get(cli).getPedidos();
    private Pedido auxped;
    private long auxPk;
    

    public int getCli() {
        return cli;
    }

    public Pedido getListaPed() {
        for(Pedido p: listaPed){
            if(p.getNumero() == Dados.getPedidoVer()){
                auxped = p;
            }
        }
        return listaPed.get(listaPed.indexOf(auxped));
    }

    public long getAuxPk() {
        return auxPk;
    }

    public void setAuxPk(long auxPk) {
        this.auxPk = auxPk;
    }
    
    
}
