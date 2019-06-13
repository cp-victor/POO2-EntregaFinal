/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author mateu
 */
@Entity
@Table(name = "ItemPed")
public class ItemPedido implements Serializable {
    @TableGenerator(
        name = "incrementItem",
        allocationSize = 1,
        initialValue = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE,
                     generator = "incrementItem")
    @Id
    private long itempedido;
    
    @ManyToOne
    private Pedido ped;
    
    @OneToOne
    private Produto prod;
    
    private int quantidade;

    public long getItempedido() {
        return itempedido;
    }

    public Pedido getPed() {
        return ped;
    }

    public Produto getProd() {
        return prod;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPed(Pedido ped) {
        this.ped = ped;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
