package com.example.usuarios.controller;



import com.example.usuarios.model.Usuario;

import com.example.usuarios.service.UsuarioService;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController

@RequestMapping({"api/v1/users"})
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "simple test")
    @GetMapping("/test")
    public String test(){
        return "hello";
    }


    @Operation(summary = "retorna todos los usuarios")
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAllUsers() {
       return usuarioService.getAllUsers();
    }

    @Operation(summary = "agrega un usuario a la base de datos")
    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@Valid @RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @Operation(summary = "actualiza un usuario si existe en la base de datos")
    @PutMapping("/upd")
    public ResponseEntity<Object> updUser(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario);
    }

    @Operation(summary = "elimina un usuario basado en el id")
    @DeleteMapping("/del/{userId}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long userId) {
        return usuarioService.deleteUsuario(userId);
    }







}
