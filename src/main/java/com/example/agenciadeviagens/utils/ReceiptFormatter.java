package com.example.agenciadeviagens.utils;

public class ReceiptFormatter {

    private static final int RECEIPT_WIDTH = 60;
    private static final String BORDER_CHAR = "=";
    private static final String LINE_CHAR = "-";
    private static final String YELLOW = "\033[33m";
    private static final String RESET = "\u001B[0m";
    private static final String ITALIC = "\033[3m";

    public static String createHeader(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(BORDER_CHAR.repeat(RECEIPT_WIDTH)).append("\n");
        sb.append(centerText(YELLOW + title + RESET)).append("\n");
        sb.append(BORDER_CHAR.repeat(RECEIPT_WIDTH)).append("\n");
        return sb.toString();
    }

    public static String createSection(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(LINE_CHAR.repeat(RECEIPT_WIDTH)).append("\n");
        sb.append(centerText(ITALIC + title + RESET)).append("\n");
        sb.append(LINE_CHAR.repeat(RECEIPT_WIDTH)).append("\n");
        return sb.toString();
    }

    public static String createLine(String label, String value) {
        int dotsLength = RECEIPT_WIDTH - label.length() - value.length();
        if (dotsLength < 2) dotsLength = 2;
        return String.format("%s %s %s\n", label, ".".repeat(dotsLength), value);
    }

    public static String createFooter() {
        return BORDER_CHAR.repeat(RECEIPT_WIDTH) + "\n";
    }

    public static String centerText(String text) {
        int padding = (RECEIPT_WIDTH - text.length()) / 2;
        if (padding < 0) padding = 0;
        return " ".repeat(padding) + text;
    }

    public static String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
}
