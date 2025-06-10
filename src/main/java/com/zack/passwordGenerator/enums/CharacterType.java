package com.zack.passwordGenerator.enums;

import lombok.Getter;

@Getter
public enum CharacterType {
    UPPER("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    LOWER("abcdefghijklmnopqrstuvwxyz"),
    DIGITS("0123456789"),
    SYMBOLS("!@#$%^&*()-_+=<>?");

    private final String characters;

    CharacterType(String characters) {
        this.characters = characters;
    }
}
