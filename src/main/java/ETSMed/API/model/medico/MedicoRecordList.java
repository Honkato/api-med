package ETSMed.API.model.medico;

public record MedicoRecordList(
        int id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        boolean ativo
) {
    public MedicoRecordList(Medico medico){
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.isAtivo()
        );
    }
}
