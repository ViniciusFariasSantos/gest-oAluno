package com.vinicius.Aluno.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vinicius.Aluno.Model.Aluno;
import com.vinicius.Aluno.Model.StatusAluno;
import com.vinicius.Aluno.repository.ReposiAluno;







@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	private static final String CADASTRO_VIEW = "CadastroAluno";
	

	@Autowired
	public ReposiAluno repositorio;
	
	
	@RequestMapping("/cadastro")
	public ModelAndView cadastro() {
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Aluno());
		return mv;
	}

	
	
	
	
	
	

	@RequestMapping(method = RequestMethod.POST)
		public String salvar(@Validated Aluno aluno, Errors errors, RedirectAttributes attributes) {

			// colocando a mensagem Negativa pra funcionar.
			if (errors.hasErrors()) {

				return CADASTRO_VIEW;

			}
			// metodo salvar
			repositorio.save(aluno);

			// colocando a mensagem Positiva pra funcionar.
			attributes.addFlashAttribute("mensagem", "Título Salvo com sucesso!");
			return "redirect:/aluno/cadastro";
	}

	
	
	
	@ModelAttribute("todosStatusAlunos")
	public List<StatusAluno> todosStatusAlunos() {
		// Esta lista é para meu Pedente e recebido, validando eles dois
		return Arrays.asList(StatusAluno.values());

	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Aluno aluno) {
		// Estou indo para meu Model Titulo e pegando meus dados e passando para o
		// usuario
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(aluno);
		return mv;
	}

	
	
	

	@RequestMapping("/excluir/{codigo}")
	public String excluir(@PathVariable ("codigo")Aluno aluno) {
		
		
		// Estou indo para meu Model Titulo e excluido os dados
		
		
		this.repositorio.delete(aluno);
		return "redirect:/aluno";
	}
	
	
	@RequestMapping
	public ModelAndView Pesquisa(@RequestParam(defaultValue="") String nomeAluno) {
		// Aqui estou pesquisando e retornando todos os repositorio do meu banco de
		// dados.
		Iterable<Aluno> todosAlunos = repositorio. findByNomeAlunoContaining(nomeAluno);

		ModelAndView mv = new ModelAndView("PesquisaAluno");
		
		mv.addObject("aluno", todosAlunos);
		return mv;

	}

}
