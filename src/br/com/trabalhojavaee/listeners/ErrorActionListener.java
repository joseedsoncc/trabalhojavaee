package br.com.trabalhojavaee.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.annotation.WebListener;

public class ErrorActionListener implements ActionListener {
    private static final Logger logger = Logger.getAnonymousLogger();
    private ActionListener actionListener;
    public ErrorActionListener(ActionListener actionListener) {
       this.actionListener = actionListener;
    }

 
	private void navigateToView(String view) {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = context.getApplication()
				.getNavigationHandler();
		navigationHandler.handleNavigation(context, null, view);
	}
	
	   @Override
	    public void processAction(ActionEvent event)
	            throws AbortProcessingException {
		        try {
	            actionListener.processAction(event);
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, e.getMessage());
				navigateToView("error");
			}
		}

}