package com.TCC.banco_de_ideias_back.controller;


import com.TCC.banco_de_ideias_back.dto.UsuarioCadastroDTO;
import com.TCC.banco_de_ideias_back.model.Usuario;
import com.TCC.banco_de_ideias_back.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }
    @PostMapping
    public Usuario cadastrar(@RequestBody UsuarioCadastroDTO dto) {
        return usuarioService.cadastrar(dto);
    }
}
