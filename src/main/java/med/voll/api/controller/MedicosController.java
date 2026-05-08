package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosListagemMedicos;
import med.voll.api.medico.DadosMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository repository;

    @GetMapping
    public List<DadosListagemMedicos> list() {
        return repository.findAll().stream().map(DadosListagemMedicos::new).toList();
    }

    @PostMapping
    public void register(@RequestBody @Valid DadosMedico dados) {
        repository.save(new Medico(dados));
    }

}
