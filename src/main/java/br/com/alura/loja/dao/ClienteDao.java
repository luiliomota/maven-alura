package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {

	EntityManager em;
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		em.persist(cliente);
	}
	
	public List<Cliente> buscarTodos (){

		String jpql = "SELECT c FROM Cliente c";
		List<Cliente> clientes = em.createQuery(jpql, Cliente.class).getResultList();
		return clientes;
	}

	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}
	public void atualizar (Cliente cliente) {
		em.merge(cliente);
	}
	public void remover(Cliente cliente) {
		cliente = em.merge(cliente);
		em.remove(cliente);
	}
}
