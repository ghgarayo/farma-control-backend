package br.com.ghx.farmacontrol.infra.exception;

public class BusinessException  extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}