package ETSMed.API.controller;

import ETSMed.API.model.medico.*;
import ETSMed.API.model.paciente.PacienteRecord;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid MedicoRecordCadastrar medicoRecordCadastrar, UriComponentsBuilder uriComponentsBuilder){
        var medico = new Medico(medicoRecordCadastrar);
        repository.save(medico);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoRecord(medico));
    }
    @GetMapping
    public ResponseEntity<Page<MedicoRecordList>> listarMedicos(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable){
        var page =  repository.findAllByAtivoTrue(pageable).map(MedicoRecordList::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable int id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoRecord(medico));
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid MedicoRecordUpdate medicoRecordUpdate){
        var medico = repository.getReferenceById(medicoRecordUpdate.id());
        medico.atualizarInformacoes(medicoRecordUpdate);
        return ResponseEntity.ok(new MedicoRecord(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable int id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

}
