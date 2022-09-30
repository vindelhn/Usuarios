package com.example.usuarios.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "debe indicar el nombre")
    private String nombre;
    @NotNull(message = "debe indicar el telefono")
    private String telefeno;
    @NotNull(message = "debe indicar el sitio de trabajo")
    private String sitio_trabajo;
    @NotNull(message = "debe indicar el pais")
    private String pais;
    @NotNull(message = "debe indicar la ciudad de recidencia")
    private String ciudad_residencia;
    @NotNull(message = "debe indicar la fecha de nacimiento")
    private LocalDate fecha_nacimiento;
    @Transient
    private Integer edad;


    public Usuario(Long id, String nombre, String telefeno, String sitio_trabajo, String pais, String ciudad_residencia, LocalDate fecha_nacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.telefeno = telefeno;
        this.sitio_trabajo = sitio_trabajo;
        this.pais = pais;
        this.ciudad_residencia = ciudad_residencia;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Usuario(String nombre, String telefeno, String sitio_trabajo, String pais, String ciudad_residencia, LocalDate fecha_nacimiento) {
        this.nombre = nombre;
        this.telefeno = telefeno;
        this.sitio_trabajo = sitio_trabajo;
        this.pais = pais;
        this.ciudad_residencia = ciudad_residencia;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Usuario() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefeno() {
        return telefeno;
    }

    public void setTelefeno(String telefeno) {
        this.telefeno = telefeno;
    }

    public String getSitio_trabajo() {
        return sitio_trabajo;
    }

    public void setSitio_trabajo(String sitio_trabajo) {
        this.sitio_trabajo = sitio_trabajo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Integer getEdad() {
        return Period.between(this.fecha_nacimiento, LocalDate.now()).getYears();
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
