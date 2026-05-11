package med.voll.api.domain.medico;

public record DadosMedicoListar(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public DadosMedicoListar(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
