package med.voll.api.controller;

import med.voll.api.medico.DadosMedicoRegistrado;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class MedicosController {

    @PostMapping
    public void register(@RequestBody DadosMedicoRegistrado data) {
        System.out.println(data);
    }

}
