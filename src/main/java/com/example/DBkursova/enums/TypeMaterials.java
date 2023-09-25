package com.example.DBkursova.enums;

import lombok.Getter;

@Getter
public enum TypeMaterials {
    NATURAL("Природные"), RAW("В виде сырья");
    final String value;

    TypeMaterials(String value) {
        this.value = value;
    }
}
