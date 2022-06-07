package br.com.alura.loja.testes;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {

//		cadastrarPedido();
		removerPedido();
//		relatorioPedido();
		
	}

	private static void relatorioPedido() {

		EntityManager em = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(em);

		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorio();
		relatorio.forEach(r -> System.out.println(r));
		System.out.println(pedidoDao.valorTotalVendido());
	}

	private static void cadastrarPedido() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		Produto produto = produtoDao.buscarPorId(26L);
		Produto produto2 = produtoDao.buscarPorId(27L);
		Cliente cliente = clienteDao.buscarPorId(1L);

		em.getTransaction().begin();
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido, produto));
		pedido.adicionarItem(new ItemPedido(1, pedido, produto2));
		pedidoDao.cadastrar(pedido);
		em.getTransaction().commit();
		System.out.println(pedidoDao.valorTotalVendido());
		em.close();
	}
	
	private static void removerPedido() {
		
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(em);
		Long id = 13L;
		Pedido pedido = pedidoDao.buscarPorIdComCliente(id);
//		Pedido pedido = pedidoDao.buscarPorId(id);
		List<ItemPedido> itensPedido = pedidoDao.buscarItemPorIdDoPedido(id);
		
		System.out.println("Pedido: "+pedido.getId());
		itensPedido.forEach(p -> System.out.println("Id do item do pedido: " + p.getId()));
		System.out.println(pedidoDao.valorTotalVendido());
		
		em.getTransaction().begin();
		pedidoDao.remover(pedido,itensPedido);
		em.getTransaction().commit();
		System.out.println(pedidoDao.valorTotalVendido());
		em.close();
	}

}
