package br.edu.ifgoias.sistemaacademico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifgoias.sistemaacademico.entities.Aluno;
import br.edu.ifgoias.sistemaacademico.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	public List<Aluno> findAll() {
		return repository.findAll();
	}

	public Aluno findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public Aluno insert(Aluno a) {
		return repository.save(a);
	}

	public Aluno update(Integer id, Aluno obj) {
		return repository.findById(id).map(aluno -> {
			aluno.setNome(obj.getNome());
			return repository.save(aluno);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public void delete(Integer id) {
		repository.findById(id).map(aluno -> {
			repository.delete(aluno);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}