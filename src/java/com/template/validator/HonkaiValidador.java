package com.template.validator;

public class HonkaiValidador {

    public static boolean isValidoParaSalvar(String nome, String efeito) {
        return nome != null && !nome.trim().isEmpty() &&
                efeito != null && !efeito.trim().isEmpty();
    }
    public static boolean isIdValido(String idText) {
        if (idText == null || idText.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(idText);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}