package ETSMed.API.model.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoRecord enderecoRecord){
        this.logradouro = enderecoRecord.logradouro();
        this.bairro = enderecoRecord.bairro();
        this.cep = enderecoRecord.cep();
        this.numero = enderecoRecord.numero();
        this.complemento = enderecoRecord.complemento();
        this.cidade = enderecoRecord.cidade();
        this.uf = enderecoRecord.uf();

    }

    public void atualizarInformacoes(EnderecoRecord endereco) {
        this.logradouro = endereco.logradouro() != null? endereco.logradouro() : this.logradouro;
        this.bairro = endereco.bairro() != null? endereco.bairro() : this.bairro;
        this.cep = endereco.cep() != null? endereco.cep() : this.cep;
        this.numero = endereco.numero() != null? endereco.numero() : this.numero;
        this.complemento = endereco.complemento() != null? endereco.complemento() : this.complemento;
        this.cidade = endereco.cidade() != null? endereco.cidade() : this.cidade;
        this.uf = endereco.uf() != null? endereco.uf() : this.uf;
    }
}
