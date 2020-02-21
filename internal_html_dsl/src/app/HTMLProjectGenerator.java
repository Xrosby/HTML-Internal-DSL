package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HTMLProjectGenerator {

    public HTMLProjectGenerator() {

    }

    public boolean generateProject(String filename, ArrayList<String> clazzes, ArrayList<String> ids,
            String htmlString) {
        if (this.generateProjectDir(filename)) {
            System.out.println("Generated directory " + filename);
        }
        if (this.writeCSSDocument(filename, clazzes, ids)) {
            System.out.println("Generated CSS document " + filename + ".css");
        }
        if (this.writeIHTMLDocumentBuilder(filename, htmlString)) {
            System.out.println("Generated HTML document " + filename + ".html");
        }
        return false;
    }

    private boolean generateProjectDir(String filename) {
        File file = new File("/" + filename + "/");
        if (!file.exists()) {
            return file.mkdir();
        } else {
            return false;
        }
    }

    private boolean writeIHTMLDocumentBuilder(String filename, String htmlString) {
        try {
            if (!filename.contains(".html")) {
                filename += ".html";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".css"));
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