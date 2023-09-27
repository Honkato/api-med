package ETSMed.API.model.medico;

import ETSMed.API.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicos")
@Entity(name = "Medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private boolean ativo;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico(MedicoRecord medicoRecord) {
        this.nome = medicoRecord.nome();
        this.email = medicoRecord.email();
        this.telefone = medicoRecord.telefone();
        this.crm = medicoRecord.crm();
        this.especialidade = medicoRecord.especialidade();
        this.endereco = new Endereco(medicoRecord.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(MedicoRecordUpdate medicoRecordUpdate) {
        if (medicoRecordUpdate.nome() != null) {
        this.nome = medicoRecordUpdate.nome();
        }
        if (medicoRecordUpdate.telefone() != null) {
            this.telefone = medicoRecordUpdate.telefone();

        }
        if (medicoRecordUpdate.endereco() != null) {
            this.endereco.atualizarInformacoes(medicoRecordUpdate.endereco());
        }
    }
    public void excluir(){
        this.ativo = false;
    }
}
