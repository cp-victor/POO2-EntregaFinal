/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "PMI")
@DiscriminatorColumn(name = "Inte")
public class ProdutoMercadoInterno extends Produto implements Serializable{

    private boolean incentivo;

    public ProdutoMercadoInterno(){
        
    }
    
    public boolean isIncentivo() {
        return incentivo;
    }

    public void setIncentivo(boolean incentivo) {
        this.incentivo = incentivo;
    }
}
