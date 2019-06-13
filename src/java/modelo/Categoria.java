package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable{
    @TableGenerator(
        name = "incrementCat",
        allocationSize = 1,
        initialValue = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "incrementCat")
    private long codigo;
    private String descricao;
    
    public Categoria(){
    }
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Categoria(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public void addProduto(Produto produto){
        produtos.add(produto);
    }
   
    public void removeProduto(Produto produto){
        produtos.remove(produto);
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
        final Categoria other = (Categoria) obj;
        if(this.codigo != other.codigo){
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)){
            return false;
        }
        return true;
    }
}
