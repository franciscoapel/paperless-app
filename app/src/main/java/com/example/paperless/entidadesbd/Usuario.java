package com.example.paperless.entidadesbd;

public class Usuario {
    private String nombres;
    private String apellidos;
    private String rut;
    private String email;
    private String contrasena;

    public Usuario(String nombres, String apellidos, String rut, String email, String contrasena) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rut = rut;
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getRut() {return rut;}
}
