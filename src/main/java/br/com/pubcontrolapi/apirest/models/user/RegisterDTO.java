package br.com.pubcontrolapi.apirest.models.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
