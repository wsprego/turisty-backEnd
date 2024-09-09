package br.ifba.turisty.domain.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserUpdateDataDTO {
    @NotNull(message = "O campo 'nome' não pode ser nulo!")
    @NotBlank(message = "O campo 'nome' não pode estar vazio!")
    private String name;

    @Email(message = "Email inválido!")
    @NotNull(message = "O campo 'email' não pode ser nulo!")
    @NotBlank(message = "O campo 'email' não pode estar vazio!")
    private String email;

    @NotNull(message = "O campo 'senha' não pode ser nulo!")
    @NotBlank(message = "O campo 'nome' não pode estar vazio!")
    private String password;
}
