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

import classes.Pedido;
import classes.Produto;
import classes.ProdutosPedido;
import dao.PedidoDao;
import dao.ProdutoDao;
import dao.ProdutosPedidoDao;

@WebServlet(urlPatterns = { "/produtosPedido", "/produtosPedido/", "/produtosPedido/nova", "/produtosPedido/adiciona",
		"/produtosPedido/remove", "/produtosPedido/altera", "/produtosPedido/edita", "/produtosPedido/excluir" })
public class ProdutosPedidoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProdutosPedidoDao produtosPedidoDao;

	public void init() {
		produtosPedidoDao = new ProdutosPedidoDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getServletPath();

		try {
			if (acao.equals("/produtosPedido/adiciona")) {
				adiciona(request, response);
			} else if (acao.equals("/produtosPedido/altera")) {
				altera(request, response);
			} else if (acao.equals("/produtosPedido/nova")) {
				nova(request, response);
			} else if (acao.equals("/produtosPedido/edita")) {
				edita(request, response);
			} else if (acao.equals("/produtosPedido/remove")) {
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
		Produto produto = new Produto();
		produto.setIdProduto(Integer.parseInt(request.getParameter("idProduto")));

		Pedido pedido = new Pedido();
		pedido.setIdPedido(Integer.parseInt(request.getParameter("idPedido")));

		ProdutosPedido produtosPedido = new ProdutosPedido();

		produtosPedido.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		produtosPedido.setValorUni(Double.parseDouble(request.getParameter("valorUnidade")));
		produtosPedido.setObeservacao(request.getParameter("obeservacao"));
		produtosPedido.setPedido(pedido);
		produtosPedido.setProduto(produto);

		try {
			produtosPedidoDao.adiciona(produtosPedido);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/produtosPedido");
	}

	private void altera(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Produto produto = new Produto();
		produto.setIdProduto(Integer.parseInt(request.getParameter("idProduto")));

		Pedido pedido = new Pedido();
		pedido.setIdPedido(Integer.parseInt(request.getParameter("idPedido")));

		ProdutosPedido produtosPedido = new ProdutosPedido();

		produtosPedido.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		produtosPedido.setValorUni(Double.parseDouble(request.getParameter("valorUnidade")));
		produtosPedido.setObeservacao(request.getParameter("obeservacao"));
		produtosPedido.setPedido(pedido);
		produtosPedido.setProduto(produto);

		produtosPedidoDao.altera(produtosPedido);
		response.sendRedirect("/produtosPedido");
	}

	private void nova(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdutosPedido produtosPedido = new ProdutosPedido();
		request.setAttribute("produtosPedido", produtosPedido);
		
		PedidoDao pedidoDao = new PedidoDao();
		List<Pedido> pedidos = pedidoDao.getLista();
		request.setAttribute("pedidos", pedidos);

		ProdutoDao produtoDao = new ProdutoDao();
		List<Produto> produtos = produtoDao.getLista();
		request.setAttribute("produtos", produtos);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/produtosPedido/manter.jsp");
		dispatcher.forward(request, response);
	}

	private void edita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdutosPedido produtosPedido = new ProdutosPedido();
		int id = Integer.parseInt(request.getParameter("id"));

		produtosPedido = produtosPedidoDao.getById(id);
		request.setAttribute("produtosPedido", produtosPedido);
		
		PedidoDao pedidoDao = new PedidoDao();
		List<Pedido> pedidos = pedidoDao.getLista();
		request.setAttribute("pedidos", pedidos);

		ProdutoDao produtoDao = new ProdutoDao();
		List<Produto> produtos = produtoDao.getLista();
		request.setAttribute("produtos", produtos);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/produtosPedido/manter.jsp");
		dispatcher.forward(request, response);
	}

	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		ProdutosPedido produtosPedido = new ProdutosPedido();
		produtosPedido.setIdProdutosPedido(Integer.parseInt(request.getParameter("id")));

		produtosPedidoDao.remove(produtosPedido);
		response.sendRedirect("/produtosPedido");
	}

	private void lista(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ProdutosPedido> produtosPedidos = produtosPedidoDao.getLista();
		request.setAttribute("produtosPedidos", produtosPedidos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/produtosPedido/consulta.jsp");
		dispatcher.forward(request, response);

	}

}
