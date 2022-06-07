package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

//		cadastrarProduto();
//		removerProduto();
//		consultarProduto();
		consultarCategoria();
//		removerCategoria();
	}

	private static void consultarCategoria() {

		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		List<Categoria> categorias = categoriaDao.buscarTodas();
		categorias.forEach(c -> System.out.println("Nome Categoria: "+c.getNome()));
	}

	private static void cadastrarProduto() {
		EntityManager em = JPAUtil.getEntityManager();	
		
		String nomeCategoria = "VEICULOS";
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		Categoria categoria = new Categoria(nomeCategoria);

		Boolean jaExisteAcategoria = false;
		List<Categoria> categorias = categoriaDao.buscarCategoriaPorNome(nomeCategoria);
		for (Categoria categoria2 : categorias) {
			if((categoria2).getNome().equals(nomeCategoria)) {
				jaExisteAcategoria = true;
				categoria = categoria2;
				break;
			}
		}
		if(jaExisteAcategoria == false) {
			categoriaDao.cadastrar(categoria);
		}
		
		Produto produto =  new Produto("Camioneta","Mit",new BigDecimal(14500),categoria);
		
		em.getTransaction().begin();
		produtoDao.cadastrar(produto);
//		celular.setNome("APPLE");
		em.getTransaction().commit();
		em.close();
	}


	private static void consultarProduto() {
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
//
//		Produto p = produtoDao.buscarPorId(7L);
//		System.out.println(p.getPreco());
//
		List<Produto> lista = produtoDao.buscarTodos();
		lista.forEach(p2 -> System.out.println(p2.getNome()));
//		
//		List<Produto> listaPorNome = produtoDao.buscarPorNome("apple");
//		listaPorNome.forEach(pn -> System.out.println(pn.getNome()+": R$"+pn.getPreco()));
//		
//		List<Produto> listaPorNomeCategoria = produtoDao.buscarPorNomeCategoria("celulares");
//		listaPorNomeCategoria.forEach(pn -> System.out.println(pn.getNome()+": R$"+pn.getPreco()));
//		
//		BigDecimal precoProduto = produtoDao.buscarPrecoPorNome("apple");
//		System.out.println(precoProduto);
		
//		List<Produto> produtos = produtoDao.buscarPorParametros(null,new BigDecimal(2500),LocalDate.of(2022, 05, 24));
//		produtos.forEach(p -> System.out.println(p.getNome()+" / "+p.getDescricao()+" / "+p.getPreco()));

//		List<Produto> produtos = produtoDao.buscarPorParametrosComCriteria(null, null, LocalDate.of(2022,05,24));
//			produtos.forEach(p -> System.out.println(p.getNome()+" - "+p.getDescricao()+" - "+p.getPreco()+" - "+p.getDataCadastro()));
	}

	private static void removerProduto() {
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorId(7L);
		
		em.getTransaction().begin();
		produtoDao.remover(produto);
		em.getTransaction().commit();
		em.close();
	}

	private static void removerCategoria() {
		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		List<Categoria> categorias = categoriaDao.buscarCategoriaPorNome("");
		Categoria categoria = categoriaDao.buscarPorId(28L);
		em.getTransaction().begin();
		categoriaDao.remover(categoria);
		categorias.forEach(c -> categoriaDao.remover(c));
		em.getTransaction().commit();
		em.close();
	}

}

