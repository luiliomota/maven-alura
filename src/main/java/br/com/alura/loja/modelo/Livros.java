package br.com.alura.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Livros extends Produto{

	private String autor;
	private Integer numeroDePaginas;
	
	public Livros() {
	}

	public Livros(String autor, Integer numeroDePaginas) {
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}
	
}
