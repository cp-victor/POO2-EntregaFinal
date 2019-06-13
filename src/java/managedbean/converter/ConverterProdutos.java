package managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Produto;
import servico.ProdutoService;

/**
 *
 * @author mateu
 */
@FacesConverter("converterProduto")
public class ConverterProdutos implements Converter{
    
	private ProdutoService servico = new ProdutoService();
        
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
            Produto e = null;
            if(!value.isEmpty()) {
                    e = servico.getByName(value);
            }
            return e;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,
			Object produto) {
		if (produto == null || produto.equals("")) {
			return null;
		} else {
			return ((Produto) produto).getNome();
		}
	}
}