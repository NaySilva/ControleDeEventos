package br.edu.ifpi.eventos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	  public boolean preHandle(HttpServletRequest request, 
	      HttpServletResponse response,
	      Object controller) throws Exception {

	      String uri = request.getRequestURI();
	      if(uri.endsWith("loginForm") ||
	          uri.endsWith("efetuaLogin") || 
	                   uri.contains("resources") ||
	      				uri.endsWith("novoUsuario")){
	    	  System.out.println("1");
	        return true;
	      }

	      if(request.getSession()
	          .getAttribute("usuarioLogado") != null) {
	    	  System.out.println(2);
	        return true;
	      }

	      response.sendRedirect("loginForm");
	      System.out.println(3);
	      return false;
	  }

}
