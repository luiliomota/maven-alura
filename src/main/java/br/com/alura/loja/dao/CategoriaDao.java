package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	public Categoria buscarPorId(long id) {
		return em.find(Categoria.class, id);
	}

	public List<Categoria> buscarTodas(){
		String jpql ="SELECT c FROM Categoria c";
		return em.createQuery(jpql, Categoria.class).getResultList();
	}
	
	public List<Categoria> buscarCategoriaPorNome(String nome){
		String jpql = "SELECT c FROM Categoria c WHERE c.nome = ?1";
		return em.createQuery(jpql,Categoria.class).setParameter(1, nome).getResultList(); 
	}
	public void atualizar (Categoria categoria) {
		em.merge(categoria);
	}
	
	public void remover (Categoria categoria) {
		categoria = em.merge(categoria);
		em.remove(categoria);
	}

	
}
