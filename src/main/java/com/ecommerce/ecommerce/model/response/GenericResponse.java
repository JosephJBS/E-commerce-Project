package com.ecommerce.ecommerce.model.response;

import com.ecommerce.ecommerce.model.constans.RespuestasEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class GenericResponse {

    private int codigo;

    private String mensaje;

    private Object data;

    private Object errores;

    public <T> T obtenerData(Class<T> clazz) {
        return new ObjectMapper().convertValue(data, clazz);
    }

    public HttpStatus status() {
        return HttpStatus.valueOf(codigo);
    }

    public static GenericResponse ok(Object object) {
        return buildResponse(RespuestasEnum.OK, RespuestasEnum.OK.mensaje(), object);
    }

    public static GenericResponse ok(Object object, String mensaje) {
        return buildResponse(RespuestasEnum.OK, mensaje, object);
    }

    public static GenericResponse errorDatosInvalidos(Object errors) {
        return GenericResponse.buildResponseError(RespuestasEnum.DATOS_INVALIDOS, errors);
    }

    //USUARIO
    public static GenericResponse usuarioNoEncontrado() {
        return buildResponse(RespuestasEnum.USUARIO_NO_ENCONTRADO);
    }

    //PRODUCTO
    public static GenericResponse productoNoEncontrado() {
        return buildResponse(RespuestasEnum.PRODUCTO_NO_ENCONTRADO);
    }

    public static GenericResponse productoActivo(Object object) {
        return buildResponse(
                RespuestasEnum.PRODUCTO_YA_ACTIVO,
                RespuestasEnum.PRODUCTO_YA_ACTIVO.mensaje(),
                object);
    }

    public static GenericResponse productoInactivo(Object object) {
        return buildResponse(
                RespuestasEnum.PRODUCTO_YA_INACTIVO,
                RespuestasEnum.PRODUCTO_YA_INACTIVO.mensaje(),
                object);
    }

    //BUILD
    private static GenericResponse buildResponse(RespuestasEnum respuestaEnum) {
        return buildResponse(respuestaEnum, respuestaEnum.mensaje());
    }

    private static GenericResponse buildResponse(RespuestasEnum respuestaEnum, String message) {
        return GenericResponse
                .builder()
                .codigo(respuestaEnum.codigo()).mensaje(message)
                .build();
    }

    private static GenericResponse buildResponse(RespuestasEnum respuestaEnum, String message, Object object) {
        return GenericResponse
                .builder()
                .data(object).codigo(respuestaEnum.codigo()).mensaje(message)
                .build();
    }

    private static GenericResponse buildResponseError(RespuestasEnum respuestaEnum, Object errores) {
        return GenericResponse
                .builder()
                .data(null).codigo(respuestaEnum.codigo()).mensaje(respuestaEnum.mensaje()).errores(errores)
                .build();
    }


}
