package com.ojas;

import java.util.List;

public class TablePrinter {
    public static void print(String[] headers, List<String[]> rows) {
        int[] widths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
        }
        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                widths[i] = Math.max(widths[i], row[i].length());
            }
        }

        String border = "+";
        for (int w : widths) border += "-".repeat(w + 2) + "+";
        System.out.println(border);

        System.out.print("|");
        for (int i = 0; i < headers.length; i++)
            System.out.printf(" %-"+widths[i]+"s |", headers[i]);
        System.out.println();
        System.out.println(border);

        for (String[] row : rows) {
            System.out.print("|");
            for (int i = 0; i < row.length; i++)
                System.out.printf(" %-"+widths[i]+"s |", row[i]);
            System.out.println();
        }
        System.out.println(border);
    }
}

