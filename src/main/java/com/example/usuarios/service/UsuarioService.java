package com.example.usuarios.service;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service

public class UsuarioService {


    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<List<Usuario>> getAllUsers(){
        try {
            List<Usuario> result = null;
            result =  usuarioRepository.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> saveUsuario(Usuario usuario)
    {
        try {
            usuarioRepository.save(usuario);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteUsuario(Long userId){
        try {

            usuarioRepository.deleteById(userId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Object> updateUsuario(Usuario usuario) {

        try {
            Long userId = usuario.getId();
            Usuario usuarioFind = usuarioRepository.findById( userId ).orElseThrow(() -> new IllegalStateException(
                            "record with id "+userId+" does not exist"
                    )
            );
            usuarioFind.setNombre(usuario.getNombre());
            usuarioFind.setTelefeno(usuario.getTelefeno());
            usuarioFind.setSitio_trabajo(usuario.getSitio_trabajo());
            usuarioFind.setPais(usuario.getPais());
            usuarioFind.setCiudad_residencia(usuario.getCiudad_residencia());
            usuarioFind.setFecha_nacimiento(usuario.getFecha_nacimiento());

            usuarioRepository.save(usuarioFind);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
