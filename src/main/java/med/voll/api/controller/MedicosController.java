package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosListagemMedicos;
import med.voll.api.medico.DadosMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository repository;

    @GetMapping
    public Page<DadosListagemMedicos> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedicos::new);
    }

    @PostMapping
    public void register(@RequestBody @Valid DadosMedico dados) {
        repository.save(new Medico(dados));
    }

}
