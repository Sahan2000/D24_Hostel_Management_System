package lk.ijse.d24_hostel_management_system.util;

import java.util.regex.Pattern;

public class RegexPatterns {
    private static final Pattern namePattern = Pattern.compile("^[a-zA-Z '.-]{4,}$");
    private static final Pattern emailPattern = Pattern.compile("(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])(com)$");
    private static final Pattern doublePattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");
    private static final Pattern intPattern = Pattern.compile("^[1-9]\\d*$");
    private static final Pattern contactPattern = Pattern.compile("^0[0-9]{9}$");

    public static Pattern getNamePattern() {
        return namePattern;
    }

    public static Pattern getDoublePattern() {
        return doublePattern;
    }

    public static Pattern getEmailPattern() {
        return emailPattern;
    }

    public static Pattern getIntPattern() {
        return intPattern;
    }

    public static Pattern getContactPattern() {
        return contactPattern;
    }
}
