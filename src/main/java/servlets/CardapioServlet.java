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
import classes.Produto;
import classes.ProdutosPedido;
import dao.CategoriaDao;
import dao.ProdutoDao;
import dao.ProdutosPedidoDao;

@WebServlet(urlPatterns = { "/cardapio", "/cardapio/", "/cardapio/nova", "/cardapio/adiciona", "/cardapio/remove",
		"/cardapio/altera", "/cardapio/edita", "/cardapio/excluir", "/cardapio/add" })
public class CardapioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CategoriaDao categoriaDao;

	public void init() {
		categoriaDao = new CategoriaDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getServletPath();

		try {
			if (acao.equals("/cardapio/adiciona")) {
				adiciona(request, response);
			} else if (acao.equals("/cardapio/altera")) {
				altera(request, response);
			} else if (acao.equals("/cardapio/add")) {
				add(request, response);
			} else if (acao.equals("/cardapio/nova")) {
				nova(request, response);
			} else if (acao.equals("/cardapio/edita")) {
				edita(request, response);
			} else if (acao.equals("/cardapio/remove")) {
				remove(request, response);
			} else if (acao.equals("/cardapio/excluir")) {
				excluir(request, response);
			} else {
				lista(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void adiciona(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		

		response.sendRedirect("/categoria");
	}

	private void altera(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(Integer.parseInt(request.getParameter("id")));
		categoria.setNome(request.getParameter("nome"));

		categoriaDao.altera(categoria);
		response.sendRedirect("/cardapio");
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

		ProdutoDao produtoDao = new ProdutoDao();
		List<Produto> produtos = produtoDao.getLista();
		request.setAttribute("produtos", produtos);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/cardapio/manter.jsp");
		dispatcher.forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto produto = new Produto();
		ProdutoDao produtoDao = new ProdutoDao();
		
		int id = Integer.parseInt(request.getParameter("id"));

		produto = produtoDao.getById(id);
		request.setAttribute("produto", produto);

		List<Produto> produtos = produtoDao.getLista();
		request.setAttribute("produtos", produtos);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/cardapio/add.jsp");
		dispatcher.forward(request, response);
	}

	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(Integer.parseInt(request.getParameter("id")));

		categoriaDao.remove(categoria);
		response.sendRedirect("/cardapio");
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(Integer.parseInt(request.getParameter("id")));
		categoria.setExcluida(true);
		categoriaDao.excluir(categoria);
		response.sendRedirect("/cardapio");
	}

	private void lista(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Categoria> categorias = categoriaDao.getLista();
		request.setAttribute("categorias", categorias);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cardapio/consulta.jsp");
		dispatcher.forward(request, response);

	}

}
