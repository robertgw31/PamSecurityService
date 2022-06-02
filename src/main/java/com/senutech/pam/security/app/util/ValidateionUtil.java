package com.senutech.pam.security.app.util;

import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ValidateionUtil {


    private static Set<String> ISO_LANGUAGES = Stream.of(Locale.getISOLanguages()).collect(Collectors.toSet());
    private static Set<String> ISO_COUNTRIES = Stream.of(Locale.getISOCountries()).collect(Collectors.toSet());
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    private ValidateionUtil() {}

    public static boolean isValidISOLanguage(String s) {
        return ISO_LANGUAGES.contains(s.toLowerCase());
    }

    public static boolean isValidISOCountry(String s) {
        return ISO_COUNTRIES.contains(s.toUpperCase());
    }

    public static boolean validateEmailAddress(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}