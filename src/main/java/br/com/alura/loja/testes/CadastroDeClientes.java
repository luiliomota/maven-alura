package br.com.alura.loja.testes;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeClientes {

	public static void main(String[] args) {

//		cadastroCliente();
		consultaCliente();
//		removerCliente();
	}

	private static void removerCliente() {

		EntityManager em = JPAUtil.getEntityManager();
		
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = clienteDao.buscarPorId(2L);
		
		em.getTransaction().begin();		
		clienteDao.remover(cliente);
		em.getTransaction().commit();
		em.close();
	}

	private static void consultaCliente() {

		EntityManager em = JPAUtil.getEntityManager();
		
		ClienteDao clienteDao = new ClienteDao(em);
		List<Cliente> clientes = clienteDao.buscarTodos();
		clientes.forEach(c -> System.out.println("Nome cliente: "+c.getNome()));
	}

	private static void cadastroCliente() {
		EntityManager em = JPAUtil.getEntityManager();
		
		Cliente cliente = new Cliente("Fernanda", "65432");
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
