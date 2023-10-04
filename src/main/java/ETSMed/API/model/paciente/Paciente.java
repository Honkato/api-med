package ETSMed.API.model.paciente;

import ETSMed.API.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pacientes")
@Entity(name = "Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nome;
    String telefone;
    String email;
    String cpf;
    @Embedded
    Endereco endereco;
    boolean ativo;

    public Paciente(PacienteRecordCadastrar pacienteRecordCadastrar) {
        this.nome = pacienteRecordCadastrar.nome();
        this.telefone = pacienteRecordCadastrar.telefone();
        this.email = pacienteRecordCadastrar.email();
        this.cpf = pacienteRecordCadastrar.cpf();
        this.endereco = new Endereco(pacienteRecordCadastrar.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(PacienteRecordUpdate pacienteRecordUpdate) {
        if (pacienteRecordUpdate.nome() != null){
            this.nome = pacienteRecordUpdate.nome();
        }
        if (pacienteRecordUpdate.telefone() != null){
            this.telefone = pacienteRecordUpdate.telefone();

        }        if (pacienteRecordUpdate.endereco() != null){
            this.endereco.atualizarInformacoes(pacienteRecordUpdate.endereco());
        }
    }
    public void excluir(){this.ativo = false;}
}
