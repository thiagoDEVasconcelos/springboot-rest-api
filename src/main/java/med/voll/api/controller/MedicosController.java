package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository repository;

    @GetMapping
    public Page<DadosMedicoListar> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosMedicoListar::new);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosMedicoCadastrar dados) {
        repository.save(new Medico(dados));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosMedicoAtualizar dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarDados(dados);
    }

    @DeleteMapping
    @Transactional
    public void remover(@RequestBody @Valid DadosMedicoRemover dados) {
        repository.deleteById(dados.id());
    }

}
