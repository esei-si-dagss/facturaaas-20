package es.uvigo.esei.dagss.facturaaas.servicios;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class PasswordHelper {
    // JASYPT library PasswordEncryptor (MD5, salt 8 bytes, 1000 iterations)

    public static final String encodePlainPassword(String plainPassword) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        String encodedPassword = passwordEncryptor.encryptPassword(plainPassword);
        return encodedPassword;
    }

    public static boolean checkPassword(String passwordToCheck, String encodedPassword) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        return (passwordEncryptor.checkPassword(passwordToCheck, encodedPassword));
    }
}
