package io.renatofreire.dto;

public record User(
        String name,
        String email,
        Long phoneNumber
) {
}
