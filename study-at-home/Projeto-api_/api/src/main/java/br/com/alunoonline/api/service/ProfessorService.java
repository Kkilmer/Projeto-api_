package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public void criarProfessor(Professor professor){
        professorRepository.save(professor);
    }

    public List<Professor> listarTodosProfessor() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarAProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id) {
        professorRepository.deleteById(id);
    }

    public void atualizarAlunoPorId(Long id, Professor aluno) {
        // PRIMEIRO PASSO: VER SE O ALUNO EXISTE NO BD
        Optional<Professor> alunoDoBancoDeDados = buscarAProfessorPorId(id);

        // E SE NÃO EXISTIR??
        if (alunoDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado no banco de dados");
        }

        // SE CHEGAR AQUI, SIGNIFICA QUE EXITE ALUNO! ENTÃO
        // VOU ARMEZENA-LO EM UMA VARIAVEL
        Professor professorEditado = alunoDoBancoDeDados.get();

        // COM ESSE ALUNO EDITADO DE CIMA, FAÇO
        // OS SETS NECESSÁRIOS PARA ATUALIZAR OS ATRIBUTOS DELE.
        professorEditado.setNome(aluno.getNome());
        professorEditado.setCpf(aluno.getCpf());
        professorEditado.setEmail(aluno.getEmail());

        // COM O ALUNO TOTALMENTE EDITADO ACIMA
        // EU DEVOLVO ELE EDITADO/ATUALIZADO PARA O BANCO DE DADOS
        professorRepository.save(professorEditado);

    }
}
