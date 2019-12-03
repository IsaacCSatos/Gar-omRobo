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
import dao.CategoriaDao;
import dao.ProdutoDao;

@WebServlet(urlPatterns = { "/produto", "/produto/", "/produto/nova", "/produto/adiciona", "/produto/remove", "/produto/altera",
		"/produto/edita" })
public class ProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProdutoDao produtoDao;

	public void init() {
		produtoDao = new ProdutoDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getServletPath();

		try {
			if (acao.equals("/produto/adiciona")) {
				adiciona(request, response);
			} else if (acao.equals("/produto/altera")) {
				altera(request, response);
			} else if (acao.equals("/produto/nova")) {
				nova(request, response);
			} else if (acao.equals("/produto/edita")) {
				edita(request, response);
			} else if (acao.equals("/produto/remove")) {
				remove(request, response);
			} else {
				lista(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void adiciona(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Categoria c = new Categoria();
		c.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
		
		Produto produto = new Produto();
		produto.setNome(request.getParameter("nome"));
		produto.setValor(Double.parseDouble(request.getParameter("valor")));
		produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdestoque")));
		produto.setCategoria(c);
        
		try {
			produtoDao.adiciona(produto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/produto");
	}

	private void altera(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Produto produto = new Produto();
		Categoria c = new Categoria();
		c.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
		
		produto.setIdProduto(Integer.parseInt(request.getParameter("id")));
		produto.setNome(request.getParameter("nome"));
		produto.setValor(Double.parseDouble(request.getParameter("valor")));
		produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdestoque")));
		produto.setCategoria(c);
		
		produtoDao.altera(produto);
		response.sendRedirect("/produto");
	}

	private void nova(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto produto = new Produto();
		request.setAttribute("produto", produto);
		CategoriaDao categoriaDao = new CategoriaDao();
		List <Categoria> categorias = categoriaDao.getLista();
        request.setAttribute("categorias", categorias);
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/produto/manter.jsp");	
		dispatcher.forward(request, response);	
	}
	
	private void edita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto produto = new Produto();
		int id = Integer.parseInt(request.getParameter("id"));

		produto = produtoDao.getById(id);
        request.setAttribute("produto", produto);
        
        CategoriaDao categoriaDao = new CategoriaDao();
		List <Categoria> categorias = categoriaDao.getLista();
        request.setAttribute("categorias", categorias);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/produto/manter.jsp");	
		dispatcher.forward(request, response);
	}	

	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Produto produto = new Produto();
		produto.setIdProduto(Integer.parseInt(request.getParameter("id")));

		produtoDao.remove(produto);
		response.sendRedirect("/produto");
	}

	private void lista(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List <Produto> produtos = produtoDao.getLista();
        request.setAttribute("produtos", produtos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/produto/consulta.jsp");
        dispatcher.forward(request, response);		

	}

}
