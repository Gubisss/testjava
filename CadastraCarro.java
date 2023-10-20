package br.com.fiap.controle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.modelo.Carro;
import br.com.fiap.negocio.BusinessObject;

/**
 * Servlet implementation class CadastraCarro
 */
@WebServlet("/CadastraCarro")
public class CadastraCarro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastraCarro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cor = request.getParameter("cor");
		String data = request.getParameter("ano");
		String[] conhecimentos = request.getParameterValues("skills");
		String[] idiomas = request.getParameterValues("idiomas");

		Carro pes = new Carro();
		pes.setNome(nome);
		pes.setcor(cor);
		
		List<String> linguas = new ArrayList<>();
		for(String s : idiomas) { 
			linguas.add(s);
		}
		
		pes.setIdiomas(linguas);
		
		List<String> skills = new ArrayList<>();
		for(int i = 0; i < conhecimentos.length; i++) {
			skills.add(conhecimentos[i]);
		}
		
		pes.setSkills(skills);
		
		LocalDate ld = LocalDate.parse(data);
		pes.setano(ld);
		
		try {
			
			BusinessObject negocio = new BusinessObject();
			negocio.cadastraCarro(pes);
			//colocando a Carro cadastrada para a página JSP
			request.setAttribute("info", pes);
			request.getRequestDispatcher("exibeCarro.jsp").forward(request, response);			
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);			
		}	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
