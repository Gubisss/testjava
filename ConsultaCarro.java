package br.com.fiap.controle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.modelo.Carro;
import br.com.fiap.negocio.BusinessObject;

@WebServlet("/consultaCarro")
public class ConsultaCarro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaCarro() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dtIni = request.getParameter("ini");
		String dtFim = request.getParameter("fim");
		
		LocalDate ini = LocalDate.parse(dtIni);
		LocalDate fim = LocalDate.parse(dtFim);
		
		try {
			BusinessObject neg = new BusinessObject();
			List<Carro> res = neg.recuperaPorData(ini, fim);
			request.setAttribute("lista", res);
			request.getRequestDispatcher("consulta.jsp").forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
		
		
	}

}
