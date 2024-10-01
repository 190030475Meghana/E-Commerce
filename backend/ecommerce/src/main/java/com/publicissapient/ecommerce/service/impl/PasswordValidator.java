package com.publicissapient.ecommerce.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
        private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$";

        public static boolean isValidPassword(String password) {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
}
