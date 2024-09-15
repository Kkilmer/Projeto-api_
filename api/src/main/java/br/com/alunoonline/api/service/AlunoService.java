package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id) {
        alunoRepository.deleteById(id);
    }

    // Novo método para atualizar aluno ->
//    public Aluno atualizarAluno(Long id, Aluno alunoAtualizado) {
//        Optional<Aluno> alunoExistente = alunoRepository.findById(id);
//
//        if (alunoExistente.isPresent()) {
//            Aluno aluno = alunoExistente.get();
//            // Atualizando os dados do aluno existente com os dados recebidos
//            aluno.setNome(alunoAtualizado.getNome());
//            aluno.setEmail(alunoAtualizado.getEmail());
//            aluno.setCpf(alunoAtualizado.getCpf());
//            // Outras propriedades do aluno que você deseja atualizar
//
//            // Salvando o aluno atualizado
//            return alunoRepository.save(aluno);
//        } else {
//            // Aqui você pode lançar uma exceção ou retornar nulo
//            throw new RuntimeException("Aluno com ID " + id + " não encontrado.");
//        }
//    }
}
