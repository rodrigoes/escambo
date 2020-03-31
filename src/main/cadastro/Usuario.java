package main.cadastro;

import java.util.List;

public class Usuario {
	private String name;
	private List<Item> itens;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Usuario(List<Item> itens) {
		this.itens = itens;
	}
}
