package ETSMed.API.controller;

import ETSMed.API.model.paciente.*;
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
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody PacienteRecordCadastrar pacienteRecordCadastrar, UriComponentsBuilder uriComponentsBuilder){
        var paciente = new Paciente(pacienteRecordCadastrar);
        repository.save(paciente);
        var uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteRecord(paciente));
    }
    @GetMapping
    public ResponseEntity<Page<PacienteRecordList>> listarPacientes(@PageableDefault(size = 5, sort = {"nome"})Pageable pageable){
        var page = repository.findAllByAtivoTrue(pageable).map(PacienteRecordList::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity detalharPaciente(@PathVariable int id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteRecord(paciente));
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PacienteRecordUpdate pacienteRecordUpdate){
        var paciente = repository.getReferenceById(pacienteRecordUpdate.id());
        paciente.atualizarInformacoes(pacienteRecordUpdate);
        return ResponseEntity.ok(new PacienteRecord(paciente));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable int id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }


}
