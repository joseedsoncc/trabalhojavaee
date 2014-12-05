package br.com.trabalhojavaee.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Viabilizacao de Messagens no Faces.
 * @author Paulo
 */
public final class MessageHelper {

    private MessageHelper() {
    }
 
    private static ResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, "msg");
    }

    public static String getMessage(String messageKey, Object... parametros) {
        return MessageFormat.format(getBundle().getString(messageKey), parametros);
    }

    public static FacesMessage createMessage(Severity severity,
        String summaryKey, Object... parametros) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(severity);
        if (summaryKey != null && !summaryKey.equals("")) {
            message.setSummary(getMessage(summaryKey, parametros));
        }

        return message;
    }

    public static void addMessage(Severity severity, String componetId,
        String summaryKey, Object... parametros) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = createMessage(severity, summaryKey, parametros);
        context.addMessage(componetId, message);
    }

    public static void addErrorMessage(String componetId, String summaryKey,
        Object... parametros) {
        addMessage(FacesMessage.SEVERITY_ERROR, componetId, summaryKey, "",
            parametros);
    }

    public static void addInfoMessage(String componetId, String summaryKey) {
        addMessage(FacesMessage.SEVERITY_INFO, componetId, summaryKey, "");
    }

    public static void addWarnMessage(String componetId, String summaryKey) {
        addMessage(FacesMessage.SEVERITY_WARN, componetId, summaryKey, "");
    }

    public static void addFatalMessage(String componetId, String summaryKey) {
        addMessage(FacesMessage.SEVERITY_FATAL, componetId, summaryKey, "");
    }
}
