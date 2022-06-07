package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {

	EntityManager em;
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		em.persist(pedido);
	}

	public Pedido buscarPorId (Long id) {
		return em.find(Pedido.class, id);
	}
	public Pedido buscarPorIdComCliente (Long id) {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = ?1";
		return em.createQuery(jpql,Pedido.class).setParameter(1, id).getSingleResult();
	}
	public List<ItemPedido> buscarItemPorIdDoPedido (Long id) {
		String jpql = "SELECT p FROM ItemPedido p WHERE p.pedido.id = ?1";
		return em.createQuery(jpql,ItemPedido.class).setParameter(1, id).getResultList();
	}
	public void atualizar (Pedido pedido) {
		em.merge(pedido);
	}
	public void remover(Pedido pedido, List<ItemPedido> itensPedido) {
		pedido = em.merge(pedido);
		for (ItemPedido itemPedido : itensPedido) {
			itemPedido = em.merge(itemPedido);
			pedido.removerItem(itemPedido);
			em.remove(itemPedido);
		}
		em.remove(pedido);
	}
	public List<Pedido> buscarTodosPedidos() {
		String jpql = "SELECT p FROM Pedido p";
		return em.createQuery(jpql,Pedido.class).getResultList();
	}
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}
	public List<RelatorioDeVendasVo> relatorio(){
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo(produto.nome, "
					+ "SUM(item.quantidade), "
					+ "MAX(pedido.data)) "
					+ "FROM Pedido pedido "
					+ "JOIN pedido.itens item "
					+ "JOIN item.produto produto "
					+ "GROUP BY produto.nome "
					+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql,RelatorioDeVendasVo.class).getResultList();
	}

}
