package ETSMed.API.controller;

import ETSMed.API.model.medico.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid MedicoRecord medicoRecord){
        repository.save(new Medico(medicoRecord));
    }
    @GetMapping
    public Page<MedicoRecordList> listarMedicos(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable){
        return repository.findAllByAtivoTrue(pageable).map(MedicoRecordList::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoRecordUpdate medicoRecordUpdate){
        var medico = repository.getReferenceById(medicoRecordUpdate.id());
        medico.atualizarInformacoes(medicoRecordUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable int id){
        var medico = repository.getReferenceById(id);
            medico.excluir();

    }

}
