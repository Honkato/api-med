package ETSMed.API.model.service;

import ETSMed.API.model.consulta.Consulta;
import ETSMed.API.model.consulta.ConsultaRecordAgendamento;
import ETSMed.API.model.consulta.ConsultaRecordDetalhamento;
import ETSMed.API.model.consulta.ConsultaRepository;
import ETSMed.API.model.medico.Medico;
import ETSMed.API.model.medico.MedicoRepository;
import ETSMed.API.model.paciente.Paciente;
import ETSMed.API.model.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private List<ValidarAgendamentoDeConsultas> validadores;

    public AgendaDeConsultas() {
    }

    public ConsultaRecordDetalhamento agendar(ConsultaRecordAgendamento consultaRecordAgendamento){
        if(!pacienteRepository.existsById(consultaRecordAgendamento.idPaciente())){
            throw new RuntimeException("id do paciente não existe");
        }
        if(consultaRecordAgendamento.idMedico()!=0 && !medicoRepository.existsById(consultaRecordAgendamento.idMedico())) {
            throw new RuntimeException("id do medico não existe");
        }

        validadores.forEach(v -> v.validar(consultaRecordAgendamento));

        var paciente = pacienteRepository.findById(consultaRecordAgendamento.idPaciente()).get();
        var medico = escolherMedico(consultaRecordAgendamento);
        var consulta = new Consulta(0, medico, paciente, consultaRecordAgendamento.data());

        consultaRepository.save(consulta);

        return new ConsultaRecordDetalhamento(consulta);
    }

    private Medico escolherMedico(ConsultaRecordAgendamento consultaRecordAgendamento) {
        if (consultaRecordAgendamento.idMedico()!= 0){
            return medicoRepository.getReferenceById(consultaRecordAgendamento.idMedico());
        }
        if (consultaRecordAgendamento.especialidade()==null){
            throw new RuntimeException("Especialidade é obrigatoria quando não seleciona médico");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(consultaRecordAgendamento.especialidade(), consultaRecordAgendamento.data());
    }
}
