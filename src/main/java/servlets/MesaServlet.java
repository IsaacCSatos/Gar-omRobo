package servlets;

// https://www.javaguides.net/2019/03/jsp-servlet-jdbc-mysql-crud-example-tutorial.html

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Mesa;
import dao.MesaDao;

@WebServlet(urlPatterns = { "/mesa", "/mesa/", "/mesa/nova", "/mesa/adiciona", "/mesa/remove", "/mesa/altera",
		"/mesa/edita", "/mesa/excluir" })
public class MesaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MesaDao mesaDao;

	public void init() {
		mesaDao = new MesaDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getServletPath();

		try {
			if (acao.equals("/mesa/adiciona")) {
				adiciona(request, response);
			} else if (acao.equals("/mesa/altera")) {
				altera(request, response);
			} else if (acao.equals("/mesa/nova")) {
				nova(request, response);
			} else if (acao.equals("/mesa/edita")) {
				edita(request, response);
			} else if (acao.equals("/mesa/remove")) {
				remove(request, response);
			} else if(acao.equals("/mesa/excluir")) {
				excluir(request, response);
			}else {
				lista(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void adiciona(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Mesa mesa = new Mesa();
		mesa.setNumero(Integer.parseInt(request.getParameter("numero")));
		mesa.setDisponibilidade(Boolean.parseBoolean(request.getParameter("disponibilidade")));

		try {
			mesaDao.adiciona(mesa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/mesa");
	}

	private void altera(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Mesa mesa = new Mesa();
		mesa.setIdMesa(Integer.parseInt(request.getParameter("id")));
		mesa.setNumero(Integer.parseInt(request.getParameter("numero")));
		mesa.setDisponibilidade(Boolean.parseBoolean(request.getParameter("disponibilidade")));
		
		mesaDao.altera(mesa);
		response.sendRedirect("/mesa");
	}

	private void nova(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mesa mesa = new Mesa();
		request.setAttribute("mesa", mesa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mesa/manter.jsp");	
		dispatcher.forward(request, response);	
	}
	
	private void edita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mesa mesa = new Mesa();
		int id = Integer.parseInt(request.getParameter("id"));

		mesa = mesaDao.getById(id);
        request.setAttribute("mesa", mesa);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/mesa/manter.jsp");	
		dispatcher.forward(request, response);
	}	

	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Mesa mesa = new Mesa();
		mesa.setIdMesa(Integer.parseInt(request.getParameter("id")));

		mesaDao.remove(mesa);
		response.sendRedirect("/mesa");
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Mesa mesa = new Mesa();
		mesa.setIdMesa(Integer.parseInt(request.getParameter("id")));
		mesa.setExcluida(true);

		mesaDao.excluir(mesa);
		response.sendRedirect("/mesa");
	}

	private void lista(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List <Mesa> mesas = mesaDao.getLista();
        request.setAttribute("mesas", mesas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mesa/consulta.jsp");
        dispatcher.forward(request, response);		

	}

}
