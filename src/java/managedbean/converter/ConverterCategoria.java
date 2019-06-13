package managedbean.converter;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Categoria;
import servico.CategoriaService;

/**
 *
 * @author mateu
 */
@FacesConverter("converterCategoria")
public class ConverterCategoria implements Converter{
    
	private CategoriaService servico = new CategoriaService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		Categoria e=null;
		if(value != null && !value.equals("")) {
			e = this.getByDescricao(value);	
		}
		
		return e;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,
			Object categoria) {
		if (categoria == null || categoria.equals("")) {
			return null;
		} else {
			return ((Categoria) categoria).getDescricao();

		}
	}
        
        private Categoria getByDescricao(String value){
            List<Categoria> lista = servico.getAll(Categoria.class);
            for(Categoria e: lista)
            {
               if(e.getDescricao().equals(value))
                   return e;
            }        
            return null;
            
        }
}