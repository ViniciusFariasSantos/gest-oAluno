package com.vinicius.Aluno.Model;

public enum StatusAluno {

	APROVADO("Aprovado"),
	REPROVADO("Reprovado");
	
	
	private String descricao;
	
	
	private StatusAluno(String descricao) {
		
		this.descricao = descricao;
		
			}

	public String getDescricao() {
		
		return descricao;
	}
	
	
}
