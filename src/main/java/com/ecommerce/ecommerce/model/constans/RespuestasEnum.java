package com.ecommerce.ecommerce.model.constans;

public enum RespuestasEnum  {

    OK(200, "Proceso exitoso."),
    USUARIO_NO_ENCONTRADO(404, "No se encontró el usuario solicitado."),
    PRODUCTO_NO_ENCONTRADO(405, "No se encontró el producto solicitado."),
    DATOS_INVALIDOS(400, "Los datos proporcionados son inválidos."),
    PRODUCTO_YA_ACTIVO(409, "El producto ya está activo."),
    PRODUCTO_YA_INACTIVO(409, "El producto ya está inactivo.");


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
