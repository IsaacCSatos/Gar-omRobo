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

import classes.Categoria;
import dao.CategoriaDao;

@WebServlet(urlPatterns = { "/categoria", "/categoria/", "/categoria/nova", "/categoria/adiciona", "/categoria/remove", "/categoria/altera",
		"/categoria/edita","/categoria/excluir" })
public class CategoriaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CategoriaDao categoriaDao;

	public void init() {
		categoriaDao = new CategoriaDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getServletPath();

		try {
			if (acao.equals("/categoria/adiciona")) {
				adiciona(request, response);
			} else if (acao.equals("/categoria/altera")) {
				altera(request, response);
			} else if (acao.equals("/categoria/nova")) {
				nova(request, response);
			} else if (acao.equals("/categoria/edita")) {
				edita(request, response);
			} else if (acao.equals("/categoria/remove")) {
				remove(request, response);
			}else if(acao.equals("/categoria/excluir")) {
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
		Categoria categoria = new Categoria();
		categoria.setNome(request.getParameter("nome"));

		try {
			categoriaDao.adiciona(categoria);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/categoria");
	}

	private void altera(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(Integer.parseInt(request.getParameter("id")));
		categoria.setNome(request.getParameter("nome"));
		
		categoriaDao.altera(categoria);
		response.sendRedirect("/categoria");
	}

	private void nova(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Categoria categoria = new Categoria();
		request.setAttribute("categoria", categoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/categoria/manter.jsp");	
		dispatcher.forward(request, response);	
	}
	
	private void edita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Categoria categoria = new Categoria();
		int id = Integer.parseInt(request.getParameter("id"));

		categoria = categoriaDao.getById(id);
        request.setAttribute("categoria", categoria);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/categoria/manter.jsp");	
		dispatcher.forward(request, response);
	}	

	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(Integer.parseInt(request.getParameter("id")));

		categoriaDao.remove(categoria);
		response.sendRedirect("/categoria");
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(Integer.parseInt(request.getParameter("id")));
		categoria.setExcluida(true);
		categoriaDao.excluir(categoria);
		response.sendRedirect("/categoria");
	}

	private void lista(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List <Categoria> categorias = categoriaDao.getLista();
        request.setAttribute("categorias", categorias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/categoria/consulta.jsp");
        dispatcher.forward(request, response);		

	}

}
