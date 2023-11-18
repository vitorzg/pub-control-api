package br.com.pubcontrol.apirest.models.user;

public record RegisterDTO(String login, String password, String name, UserRole role) {
}