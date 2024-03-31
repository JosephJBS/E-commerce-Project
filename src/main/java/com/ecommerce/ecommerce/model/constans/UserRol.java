package com.ecommerce.ecommerce.model.constans;

public enum UserRol {

    ADM("Administrador", "ADM"),
    TRB("Trabajador", "TRB"),
    CLNT("Cliente", "CLNT");

    private String descripcion;
    private String abreviatura;

    UserRol(String descripcion, String abreviatura) {
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

}
