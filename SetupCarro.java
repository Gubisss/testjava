package br.com.fiap.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.modelo.Carro;
import br.com.fiap.modelo.Skill;
import br.com.fiap.negocio.BusinessObject;

/**
 * Servlet implementation class SetupCarro
 */
@WebServlet("/setupCarro")
public class SetupCarro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupCarro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aux = request.getParameter("id");
		long id = Long.parseLong(aux);
		
		BusinessObject negocio = new BusinessObject();
		try {
			Carro pes = negocio.recuperaCarro(id);
			String[] ti = {"html", "python", "java", "oracle", "estatistica", "pilha"};
			String[] idiomas = {"portugues", "ingles", "espanhol", "alemao"};
			
			List<Skill> skTi = new ArrayList<>();
			List<Skill> skIdi = new ArrayList<>();
			
			for(String conhecimento : ti) {
				Skill s = new Skill();
				if (pes.getSkills().contains(conhecimento))
					s.setSelecionado(true);
				s.setNome(conhecimento);
				skTi.add(s);
			}			
			
			for(String i : idiomas) {
				Skill s = new Skill();
				s.setNome(i);
				if (pes.getIdiomas().contains(i)) 
					s.setSelecionado(true);
				skIdi.add(s);
			}
			request.setAttribute("ti", skTi);
			request.setAttribute("idiomas", skIdi);
			
			request.setAttribute("Carro", pes);
			request.getRequestDispatcher("alteraCarro.jsp").forward(request, response);
						
		} 
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
