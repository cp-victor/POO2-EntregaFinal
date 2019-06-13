/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import modelo.Cliente;

/**
 *
 * @author 171095
 */
public class ClienteService extends DAO<Cliente, Long>{
    public boolean validaCampos(Cliente c, int stat){
        boolean ret = true;
        
        if (c.getEndereco().isEmpty() ||
                c.getLimite() == 0    ||
                c.getNome().isEmpty() ||
                c.getTelefone().isEmpty())
            ret = false;
        if (stat != 1 && stat != 2)
            ret = false;
        
        return ret;
    }
    
    public boolean clienteCadastrado(long codigo){
        boolean ret = false;
        Cliente aux;
        
        aux = getById(Cliente.class, codigo);
        
        if (aux != null)
            ret = true;
        
        return ret;
    }
    
    public boolean clienteApto(long codigo){
        boolean ret = false;
        Cliente aux = new Cliente();
        
        aux = getById(Cliente.class, codigo);
        
        if (aux.getStatus())
            ret = true;
        
        return ret;
    }
}
