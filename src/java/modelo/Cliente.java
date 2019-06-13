/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


/**
 *
 * @author 171095
 */

@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable{
    @TableGenerator(
        name = "incrementCli",
        allocationSize = 1,
        initialValue = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "incrementCli")
    private long codigo;
    
    private String nome;
    private String endereco;
    private String telefone;
    private boolean status;
    private double limite;
    
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    public Cliente() {
        //pedidos = new ArrayList<>();
    }
    
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void addPedido(Pedido pedido){
        pedidos.add(pedido);
    }
    
    public void removePedido(Pedido pedido){
        pedidos.remove(pedido);
    }
}
