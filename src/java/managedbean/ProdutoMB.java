package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Categoria;
import modelo.Produto;
import modelo.ProdutoExportacao;
import modelo.ProdutoMercadoInterno;
import org.primefaces.event.RowEditEvent;
import servico.CategoriaService;
import servico.ProdutoService;

/**
 *
 * @author victo
 */
@ManagedBean(name="ProdutoMB")
@SessionScoped
public class ProdutoMB {
    private ProdutoService produtoService = new ProdutoService();
    private CategoriaService servicocat = new CategoriaService();
    private Produto prod = new Produto();
    private List<Produto> prods = new ArrayList<>();
    private Categoria categoriaEscolhida;
    private Produto selectedProd;
    private ProdutoExportacao prode = new ProdutoExportacao();
    private ProdutoMercadoInterno prodi = new ProdutoMercadoInterno();
    private int radio;
    public boolean camposValidos = true;
    public String errorMsg;
    
    public void salvar(){   
        
        errorMsg = "";
        camposValidos = true;
        
        if(this.radio == 2){
            camposValidos = produtoService.verificaCampos(prode);
            if (camposValidos){
                if (!produtoService.prodExiste(prode)){
                    if (prode.getDestino().isEmpty()){
                        errorMsg = "Destino deve ser preenchido!";
                        camposValidos = false;
                    }
                    else{
                        if(categoriaEscolhida != null){ 
                            prode.setCategoria(categoriaEscolhida);
                            categoriaEscolhida.addProduto(prode);
                        }
                        produtoService.save(prode);
                        prode = new ProdutoExportacao();
                        categoriaEscolhida=null;
                    }
                }
                else{
                    errorMsg = "Produto ja cadastrado!";
                    camposValidos = false;
                }         
            }
            else{
                errorMsg = "Preencha todos os campos!";
                camposValidos = false;
            }                
        }
        else if (this.radio == 1){
            camposValidos = produtoService.verificaCampos(prodi);
            if (camposValidos){
                if (!produtoService.prodExiste(prodi)){
                    if(categoriaEscolhida != null){
                        prodi.setCategoria(categoriaEscolhida);
                        categoriaEscolhida.addProduto(prodi);
                    }        
                    produtoService.save(prodi);
                    prodi = new ProdutoMercadoInterno();
                    categoriaEscolhida=null;
                }
                else{
                    errorMsg = "Produto ja cadastrado!";
                    camposValidos = false;
                }                
            }
            else{
                errorMsg = "Preencha todos os campos!";
                camposValidos = false;
            }            
        }
        else{
            errorMsg = "Selecione um Tipo!";
            camposValidos = false;
        }
        
    }

    public boolean isCamposValidos() {
        return camposValidos;
    }

    public void setCamposValidos(boolean camposValidos) {
        this.camposValidos = camposValidos;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    public boolean compara(String x){
        return !x.equals("");
    }
    
    public boolean comparain(boolean x){
        return x;
    }
    
    public boolean rendered(){
        if(this.radio == 1)
            return true;
        else if(this.radio == 2)
            return false;
        return false;
    }

    public Categoria getCategoriaEscolhida() {
        return categoriaEscolhida;
    }
    
    public List<Categoria> getCategorias(){
      return servicocat.getAll(Categoria.class);
    }

    public void setCategoriaEscolhida(Categoria categoriaEscolhida) {
        this.categoriaEscolhida = categoriaEscolhida;
    }
    
    public Produto getSelectedProd() {
        return selectedProd;
    }

    public void setSelectedProd(ProdutoExportacao selectedProd) {
        this.selectedProd = selectedProd;
    }
    
    public void remove(Produto prod){
        Produto np = new Produto();
        np = produtoService.getByName(prod.getNome());
        produtoService.remove(np);
    }
    
    public void removeSelected(){
        selectedProd.getCategoria().removeProduto(selectedProd);
        produtoService.remove(selectedProd);
        selectedProd = null;
    }
    
    public List<Produto> getProdutos(){
        this.prods = produtoService.getAll(Produto.class);
        return prods;
    }
    
    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }
    
    public Produto getProde() {
        return prode;
    }
    
    public ProdutoMercadoInterno getProdi() {
        return prodi;
    }

    public void setProde(ProdutoExportacao prode) {
        this.prode = prode;
    }
    
    public void setProdi(ProdutoMercadoInterno prodi) {
        this.prodi = prodi;
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Produto Editado", ((Produto) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel (RowEditEvent event){
        FacesMessage msg = new FacesMessage("Edição Cancelada",((Produto) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null,msg);
    } 
    
    public String buscaIncentivo(ProdutoMercadoInterno p){
        String ret;
        
        if (p.isIncentivo())
            ret = "Sim";
        else
            ret = "Não";
        
        return ret;
    }
    
    public String buscaIncentivo(ProdutoExportacao p){
        String ret;
        
        ret = "Produto Exportacao";
        
        return ret;
    }
    
    public String buscaDestino(ProdutoMercadoInterno p){
        return "Produto interno";
    }
    public String buscaDestino(ProdutoExportacao p){
        return p.getDestino();
    }
    
}