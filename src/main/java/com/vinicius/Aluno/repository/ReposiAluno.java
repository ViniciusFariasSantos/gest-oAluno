package com.vinicius.Aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.Aluno.Model.Aluno;


public interface ReposiAluno extends JpaRepository<Aluno, Long > {

	Iterable<Aluno> findByNomeAlunoContaining(String nomeAluno);

}
