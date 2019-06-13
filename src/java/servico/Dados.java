/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Produto;

/**
 *
 * @author victo
 */
public class Dados {
    private static List<Cliente> LISTA_CLIENTES;
    private static List<Produto> LISTA_PRODUTOS;
    private static List<Categoria> LISTA_CATEGORIAS;
    private static List<Pedido> LISTA_ITEMPEDIDOS;
    private static int auxC;
    private static int contPed=0;
    private static int contCli=0;
    private static int cliFinal;
    private static int pedidoVer=0;
    
    private static EntityManagerFactory emf = 
                    Persistence.createEntityManagerFactory("PrAula1610PU");
    
    
    private static void init(){
        LISTA_CLIENTES = new ArrayList();
    }
    
    private static void init2(){
        LISTA_PRODUTOS = new ArrayList() ;
    }
    
    private static void init3(){
        LISTA_CATEGORIAS = new ArrayList();
    }
    
    private static void init4(){
        LISTA_ITEMPEDIDOS = new ArrayList();
    }
    
    public static List<Cliente> getLISTA_CLIENTES() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query;
        query = em.createQuery("select cl from Cliente cl");
        LISTA_CLIENTES = query.getResultList();
        
        return LISTA_CLIENTES;
    }
        
    public static List<Produto> getLISTA_PRODUTOS() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("select p from Produto p");
        LISTA_PRODUTOS = query.getResultList();
        return LISTA_PRODUTOS;
    }
    
    public static List<Categoria> getLISTA_CATEGORIAS() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("select c from Categoria c");
        //query.setMaxResults(5); //pagination
        LISTA_CATEGORIAS = query.getResultList();
        return LISTA_CATEGORIAS;
    }
    
    public static List<Pedido> getLISTA_ITEMPEDIDOS() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("select pe from Pedido pe");
        LISTA_ITEMPEDIDOS = query.getResultList();
        return LISTA_ITEMPEDIDOS;
    }
    
    public static int getAuxC(){
        return auxC;
    }
    
    public static void setAuxC(int aux){
        auxC = aux;
    }

    public static int getContPed(int x){
        if(x == 1)
            setContPed();
        return contPed;
    }

    public static void setContPed(){
        Dados.contPed++;
    }

    public static int getContPed() {
        return contPed;
    }

    public static void setContPed(int contPed) {
        Dados.contPed = contPed;
    }

    
//    public static int getContCli() {
//        return contCli;
//    }
//
//    public static void setContCli(int contCli) {
//        Dados.contCli = contCli;
//    }
//    

    public static int getCliFinal() {
        return cliFinal;
    }

    public static void setCliFinal(int cliFinal) {
        Dados.cliFinal = cliFinal;
    }
    
    public static void limpaLista(){
        LISTA_ITEMPEDIDOS = new ArrayList<>();
    }

    public static int getPedidoVer() {
        return pedidoVer;
    }

    public static void setPedidoVer(int pedidoVer) {
        Dados.pedidoVer = pedidoVer;
    }
}
