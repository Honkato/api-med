package ETSMed.API.controller;

import ETSMed.API.model.usuario.*;
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
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody UsuarioRecordCadastrar usuarioRecordCadastrar, UriComponentsBuilder uriComponentsBuilder){
        var usuario = new Usuario(usuarioRecordCadastrar);
        repository.save(usuario);
        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioRecord(usuario));
    }
//    @GetMapping
//    public ResponseEntity<Page<UsuarioRecordList>> listarUsuarios(@PageableDefault(size = 5, sort = {"nome"})Pageable pageable){
//        var page = repository.findAllByAtivoTrue(pageable).map(UsuarioRecordList::new);
//        return ResponseEntity.ok(page);
//    }
    @GetMapping("/{id}")
    public ResponseEntity detalharUsuario(@PathVariable int id){
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new UsuarioRecord(usuario));
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UsuarioRecordUpdate usuarioRecordUpdate){
        var usuario = repository.getReferenceById(usuarioRecordUpdate.id());
        usuario.atualizarInformacoes(usuarioRecordUpdate);
        return ResponseEntity.ok(new UsuarioRecord(usuario));
    }
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity deletar(@PathVariable int id){
//        var usuario = repository.getReferenceById(id);
//        usuario.excluir();
//        return ResponseEntity.noContent().build();
//    }


}
