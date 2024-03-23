package com.ecommerce.ecommerce.model.constans;

public enum RespuestasEnum  {

    OK(200, "Proceso exitoso."),
    USUARIO_NO_ENCONTRADO(404, "No se encontró el usuario solicitado."),
    DATOS_INVALIDOS(401, "Los datos proporcionados son inválidos.");


    private int codigo;

    private String mensaje;

    public int codigo() {
        return codigo;
    }

    public String mensaje() {
        return mensaje;
    }

    private RespuestasEnum (int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

}
