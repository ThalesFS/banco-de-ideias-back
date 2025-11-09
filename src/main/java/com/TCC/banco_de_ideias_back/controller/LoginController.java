package com.TCC.banco_de_ideias_back.controller;

import com.TCC.banco_de_ideias_back.dto.LoginResponseDTO;
import com.TCC.banco_de_ideias_back.dto.LoginDTO;
import com.TCC.banco_de_ideias_back.model.Usuario;
import com.TCC.banco_de_ideias_back.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

// aparentemente https nao aparece pro CORS
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final UsuarioRepository usuarioRepository;
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO){

        Usuario usuario = usuarioRepository.findByEmailAndSenha(loginDTO.email, loginDTO.senha);

        if(usuario == null){
            throw  new RuntimeException("Credenciais invalidas ou usuario inexistente");
        }

        return new LoginResponseDTO(usuario.getId(), usuario.getNome(), usuario.getTipo().name());
    }
}
