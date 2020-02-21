package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HTMLProjectGenerator {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public HTMLProjectGenerator() {

    }

    public boolean generateProject(String filename, ArrayList<String> clazzes, ArrayList<String> ids,
            String htmlString) {


        filename = filename.replace(" ", "-");
        if (this.generateProjectDir(filename)) {
            System.out.println("Generated directory " + filename);
        } else {
            System.out.println(ANSI_RED + "Failed to generate directory " + ANSI_RESET + filename);
            System.out.println(ANSI_RED + "Directory already exists" + ANSI_RESET);
        }
        if (this.writeCSSDocument(filename, clazzes, ids)) {
            System.out.println("Generated CSS document " + filename + ".css");
        } else {
            System.out.println(ANSI_RED + "Failed to generate CSS document " + ANSI_RESET + filename);
        }
        if (this.writeIHTMLDocumentBuilder(filename, htmlString)) {
            System.out.println("Generated HTML document " + filename + ".html");
        } else {
            System.out.println(ANSI_RED + "Failed to generate HTML document " + ANSI_RESET + filename);
        }
        return false;
    }

    private boolean generateProjectDir(String dirName) {
        File file = new File(dirName);
        if (!file.exists()) {
            return file.mkdir();
        } else {
            return false;
        }
    }

    private boolean writeIHTMLDocumentBuilder(String dirName, String htmlString) {
        String filename = "index.html";
        try {
            String saveLocation = String.format("%s/%s", dirName, filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveLocation));
            writer.write(htmlString);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return false;
        }
        return true;
    }

    private void writeClasses(BufferedWriter writer, ArrayList<String> clazzes) throws IOException {
        for (String clazz : clazzes) {
            writer.write("\n\n." + clazz + "{\n\n}");
        }
    }

    private void writeIds(BufferedWriter writer, ArrayList<String> ids) throws IOException {
        for (String id : ids) {
            writer.write("\n\n#" + id + "{\n\n}");
        }
    }

    private boolean writeCSSDocument(String filename, ArrayList<String> clazzes, ArrayList<String> ids) {

        try {
            String saveLocation = String.format("%s/%s", filename, "index.css");
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveLocation));
            writer.write("/*This document contains all generated classes and ids*/");
            writeClasses(writer, clazzes);
            writeIds(writer, ids);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return false;
        }
        return true;
    }

}