package util;

public class Validador {
    public static boolean validarCpf(String cpf) {
        if (cpf == null) {
            return false;
        }
        cpf = cpf.replaceAll("\\D", ""); // remove inutilidades
        if (cpf.length() != 11) {
            return false;
        }
        // checa se os digitos são todos iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        try {
            // calcula primeiro digito verificador
            int sum1 = 0;
            for (int i = 0; i < 9; i++) {
                sum1 += Integer.parseInt(cpf.substring(i, i + 1)) * (10 - i);
            }
            int dv1 = 11 - (sum1 % 11);
            if (dv1 > 9) {
                dv1 = 0;
            }
            if (dv1 != Integer.parseInt(cpf.substring(9, 10))) {
                return false;
            }
            // calcula o segundo digito verificador
            int sum2 = 0;
            for (int i = 0; i < 10; i++) {
                sum2 += Integer.parseInt(cpf.substring(i, i + 1)) * (11 - i);
            }
            int dv2 = 11 - (sum2 % 11);
            if (dv2 > 9) {
                dv2 = 0;
            }
            if (dv2 != Integer.parseInt(cpf.substring(10, 11))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validarPlaca(String placa) {
        if (placa == null) {
            return false;
        }
        placa = placa.toUpperCase().replace("-", ""); // normaliza pra caps lock e remove o hifen
        // formato antigo: AAA1234 
        // Mercosul: AAA1A23
        return placa.matches("[A-Z]{3}\\d{4}") || placa.matches("[A-Z]{3}\\d[A-Z]\\d{2}");
    }
}