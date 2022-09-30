package com.example.usuarios.service;


import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioServiceTest {


    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioService usuarioServiceTest;

    @BeforeEach
    void setUp() {
        usuarioServiceTest = new UsuarioService(usuarioRepository);
    }

    @AfterEach
    void tearDown() {
        usuarioRepository.deleteAll();
    }

    @Test
    void getAllUsers() {

        //given

        usuarioRepository.deleteAll();

        Usuario usuario1 = new Usuario(1L,
                "Alex",
                "9999",
                "Mordor",
                "hn",
                "cb",
                LocalDate.of(2020, 1, 8));

        Usuario usuario2 = new Usuario(2L,
                "Alex",
                "9999",
                "Mordor",
                "hn",
                "cb",
                LocalDate.of(2020, 1, 8));

        usuarioRepository.saveAll(Arrays.asList(usuario1,usuario2));

        ResponseEntity<List<Usuario>> result = null;

        // when
        result = usuarioServiceTest.getAllUsers();

        int size = result.getBody().size();

        String body = result.getBody().toString();

        // then
        assertEquals(200, result.getStatusCodeValue());

        assertEquals( 2 ,size );

    }

    @Test
    void saveUsuario() {

        //given
        usuarioRepository.deleteAll();

        Usuario usuario1 = new Usuario(1L,
                "Alex",
                "9999",
                "Mordor",
                "hn",
                "cb",
                LocalDate.of(2020, 1, 8));

        //when
        usuarioRepository.save(usuario1);

        ResponseEntity result = null;

        result = usuarioServiceTest.getAllUsers();

        //then
        assertEquals(200, result.getStatusCodeValue());


    }

    @Test
    void deleteUsuario() {

        //given
        usuarioRepository.deleteAll();

        Usuario usuario1 = new Usuario(
                "Alex",
                "9999",
                "Mordor",
                "hn",
                "cb",
                LocalDate.of(2020, 1, 8));

        usuarioRepository.save(usuario1);

        Long id = usuario1.getId();

        ResponseEntity result = null;

        result = usuarioServiceTest.getAllUsers();

        assertEquals(200, result.getStatusCodeValue());

        //when
        result = usuarioServiceTest.deleteUsuario(id);


        //then
        assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    void updateUsuario() {


        //given
        usuarioRepository.deleteAll();

        Usuario usuario1 = new Usuario(
                "Alex",
                "9999",
                "Mordor",
                "hn",
                "cb",
                LocalDate.of(2020, 1, 8));

        usuarioRepository.save(usuario1);

        ResponseEntity result1 = null;

        result1 = usuarioServiceTest.getAllUsers();

        assertEquals(200, result1.getStatusCodeValue());

        Long id = usuario1.getId();


        Usuario usuario2 = new Usuario(
                id,
                "Alex",
                "9999",
                "Mordor",
                "hn",
                "cb",
                LocalDate.of(2020, 1, 8));

        ResponseEntity result2 = null;

        //when
        result2 = usuarioServiceTest.updateUsuario(usuario2);

        //then
        assertEquals(200, result2.getStatusCodeValue());

    }
}
