package br.com.trabalhojavaee.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="ExceptionFilter", urlPatterns = {"/paginas/usuario/*" , "/*"})
public class ExceptionFilter implements Filter {
	public void init(FilterConfig config) throws ServletException {
	}
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			
			chain.doFilter(request, response);
			
		} catch (Exception e) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			System.out.println("Ocorreu um erro durante o processamento da requisicao.");
			httpResponse.sendRedirect(httpRequest.getContextPath()
					+ "/error.xhtml");
		}
	}
}
