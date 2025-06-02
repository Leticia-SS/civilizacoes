package org.infnet.dto;

public class ErrorResponse {
    private String message;
    private String erro;

    public ErrorResponse(String message, String erro) {
        this.message = message;
        this.erro = erro;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
