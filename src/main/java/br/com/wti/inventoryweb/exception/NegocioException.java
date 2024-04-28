package br.com.wti.inventoryweb.exception;

/**
 * @author Washington Antunes for wTI on 27/04/2024
 */
public class NegocioException extends RuntimeException{

    public NegocioException(String mensagem) {
        super(mensagem);
    }
}
