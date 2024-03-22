package com.ecommerce.ecommerce.model;

public enum UserRol {

    ADMIN ("Administrador","ADM"),
    TRABAJADOR("Trabajador","TRB");

    private String descripcion;
    private String abreviatura;

    UserRol(String descripcion, String abreviatura){
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

}
