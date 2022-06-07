package br.com.alura.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	public Categoria() {
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
/*	Mapeamento usando chave composta
	
	@EmbeddedId
	private CategoriaId id;

	public Categoria() {
	}
	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "Tipo Teste");
	}

	public String getNome() {
		return id.getNome();
	}

	@Override
	public String toString() {
		return this.id.getNome();
	}
*/
}
