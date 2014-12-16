package br.com.trabalhojavaee.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe responsavel por realizar a contagem de acesso as paginas do sistema.
 * 
 * @author Paulo Henrique
 * @Data 15/12/2014
 */
@WebListener
public class RequestListener implements ServletRequestListener {

	private static Map<String, Integer> acessosPagina = new HashMap<String, Integer>();
	
	private final static String XHTML = "xhtml";

	/**
	 * Construtor vazio padrao.
	 */
	public RequestListener() {
		
	}
	
	/**
	 * Metodo responsavel por realizar a verificacao das paginas xhtml 
	 * e adicionalas ao request.
	 */
	public void requestInitialized(ServletRequestEvent evt) {

		HttpServletRequest request = (HttpServletRequest) evt
				.getServletRequest();
		String path = request.getServletPath();

		if (path.endsWith(XHTML)) {
			
			String string = path;
			String[] parts = string.split("/");
			
			@SuppressWarnings("unused")
			String part1 = parts[0];
			String part2 = parts[1];
			
			if (!part2.equals("javax.faces.resource")){
				acessosPagina.put(path, getContador(path, acessosPagina));
				request.setAttribute("acessosPagina", acessosPagina);
			}else{
				
				System.out.println("Sair!");
				@SuppressWarnings("unused")
				System exit;
			}
		}
	}
	
	public void requestDestroyed(ServletRequestEvent arg0) {
	}
	
	/**
	 * Retorna um map para acessos.
	 * @return acessosPagina.
	 */
	public static Map<String, Integer> getAcessosPagina() {
		return acessosPagina;
	}
	
	/**
	 * Metodo responsavel por realizar o caontagem de acessos as paginas.
	 * 
	 * @param path
	 * @param acessosPagina2
	 * @return integer contabilizador das paginas.
	 */
	private Integer getContador(String path, Map<String, Integer> acessosPagina2) {
		Integer integer = acessosPagina2.get(path);

		return integer == null ? 1 : ++integer;
	}

	/**
	 * @WebListener
	public class RequestListener implements ServletRequestListener {

	//Contador global de requisicoes.
	private static long reqCount;

	public void requestInitialized(ServletRequestEvent sre){

		//Contexto sado para log.
		ServletContext context = sre.getServletContext( );

		//Usado para obter informacao de um novo request. 
		ServletRequest request = sre.getServletRequest( );

		//Objeto que ira receber informacoes das paginas das requisicoes.
		Pagina pagina = new Pagina();

		//Lista de paginas a ser carregada nas requisicoes.
		List<Pagina> paginas;

		String uri;

		String nome;

		
		 // O contador global de requisicoes é incrementedo neste bloco.
		 
		synchronized (context){

			context.log("Request for "+	
			 (request instanceof HttpServletRequest ? 
				((HttpServletRequest) request).getRequestURI( ) : "Unknown")+ "; Count="+ ++reqCount);
		}// fim synchronized
	
		uri = ((HttpServletRequest) request).getRequestURI( );

		Pattern p = Pattern.compile("[a-zA-Zà-úÀ-Ú]+");
		Matcher m = p.matcher(uri);
		int i = 1;
		while(m.find()) {
			System.out.println("Palavra " + i + ": " + m.group());
			i++;
		
		System.out.println("Frase completa: " + uri);
	
		uri.lastIndexOf(uri.length());
		
		//pagina.setNome(nome);

		//paginas.add(pagina);

		} //fim while find.
	}

	public void requestDestroyed(ServletRequestEvent sre){

		//Called when the servlet request is going oout of scope.

	}//requestDestroyed

}
	 */
	
	

}
