package br.com.trabalhojavaee.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.trabalhojavaee.model.Cep;

@FacesConverter("converters.CepConverter")
public class CepConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value != null && !value.trim().isEmpty()) {
			
			String cep = value.replaceAll("\\-", "");
			
			try {
				Long.valueOf(cep);
				Cep cepRetorno = new Cep();
				cepRetorno.setRegiao(cep.substring(0, 5));
				cepRetorno.setSufixo(cep.substring(5, 8));
				return cepRetorno;
				
			} catch (NumberFormatException e) {
				FacesMessage message = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Erro de conversão",
                        "O valor informado não é um número de CEP!");
                throw new ConverterException(message);
			}
		}

		return value;
	}

	@Override
 	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		String cep = (value == null ? null : value.toString());
		
		if (cep != null && !cep.trim().isEmpty()) {
			cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);
			return cep;
		}
		
		return null;
	}

}