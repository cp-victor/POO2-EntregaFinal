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
@Table(name = "PEX")
@DiscriminatorColumn(name = "Expo")
public class ProdutoExportacao extends Produto implements Serializable{

    protected String destino;

    public ProdutoExportacao(){   
    }
    
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
