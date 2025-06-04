package br.edu.ifce.meuprimeirospringboot.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String cpf) {
        super("Usuário com CPF " + cpf + " não encontrado.");
    }
}
