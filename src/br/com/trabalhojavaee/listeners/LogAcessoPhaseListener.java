package br.com.trabalhojavaee.listeners;

import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.trabalhojavaee.managedbean.LoginBean;
/**
 * 
 * @author Paulo Henrique
 * @Data 03-01-2014
 */
public class LogAcessoPhaseListener implements PhaseListener{

	private static final long serialVersionUID = -6679617179413348825L;

	private static final Logger logger = Logger.getLogger(LogAcessoPhaseListener.class.getName());

	private String remoteAddress;

	/**
	 * Metodo responsavel por interceptar os eventos e validar o acesso as paginas do sistema.
	 */
	public void afterPhase(PhaseEvent event) {

		FacesContext context = event.getFacesContext();

		String viewId = context.getViewRoot().getViewId();

		LoginBean loginBean = context.getApplication().evaluateExpressionGet(
				context, "#{loginBean}", LoginBean.class);

		if (!viewId.equals("/logout.xhtml") && !viewId.equals("/login.xhtml")) {

			loginBean.autenticar();

			if (loginBean.getUsuarioAutenticado() != null){
				ExternalContext contextCurrent = FacesContext.getCurrentInstance().getExternalContext();  
				HttpServletRequest request = (HttpServletRequest)contextCurrent.getRequest();  
				this.remoteAddress =  request.getRemoteAddr(); 
				logger.warning("Acesso permitido em " + viewId + " por "+ this.remoteAddress);

			}else {
				ExternalContext externalContext = context.getExternalContext();
				HttpSession httpSession = (HttpSession)
						externalContext.getSession(false);
				httpSession.invalidate();

				ExternalContext contextCurrent = FacesContext.getCurrentInstance().getExternalContext();  
				HttpServletRequest request = (HttpServletRequest)contextCurrent.getRequest();  

				this.remoteAddress =  request.getRemoteAddr(); 

				if (loginBean.getUsuarioAutenticado() == null) {
					logger.warning("Acesso indevido em " + viewId + " por "+ this.remoteAddress +".");
				}
			}
		}
	}

	public void beforePhase(PhaseEvent event) { 

		/*
		 * Sem implementacao
		 *  
		 */ 

	}

	public PhaseId getPhaseId() { 

		return PhaseId.RESTORE_VIEW; 

	}
}