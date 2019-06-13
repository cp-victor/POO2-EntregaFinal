/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "Produto")
@DiscriminatorColumn(
        name="coluna",
        discriminatorType=DiscriminatorType.STRING)
public class Produto implements Serializable{
    @TableGenerator(
        name = "incrementProd",
        allocationSize = 1,
        initialValue = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "incrementProd")
    protected long codigo;
    protected String nome;
    
    @ManyToOne
    protected Categoria categoria;
    
    protected double preco;
    protected int moeda;
    protected double imposto;
    
    public Produto(){
        
    }
    
    public boolean getIncentivo(){
        return false;
    }
    
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getMoeda() {
        return moeda;
    }

    public void setMoeda(int moeda) {
        this.moeda = moeda;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        final Produto other = (Produto) obj;
        if(this.codigo != other.codigo){
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)){
            return false;
        }
        return true;
    }
}
