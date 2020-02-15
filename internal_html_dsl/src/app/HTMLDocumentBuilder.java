package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HTMLDocumentBuilder implements IHTMLDocumentBuilder {

    private ArrayList<String> ids;
    private ArrayList<String> classes;
    private Element root;
    private Element currentElement;
    private Element previousElement;
    private String htmlString = "";
    private List<ElementType> blockedFromAttributes = Arrays.asList(ElementType.HEAD, ElementType.BODY,
            ElementType.TITLE);

    public HTMLDocumentBuilder() {
        root = new Element(ElementType.HTML, null);
        this.currentElement = root;
        this.ids = new ArrayList<>();
        this.classes = new ArrayList<>();
    }

    // __________ HEADER ELEMENTS __________//

    public IHeadElementBuilder head() {
        Element head = new Element(ElementType.HEAD, currentElement);
        this.root.addChild(head);
        this.currentElement = head;
        return (IHeadElementBuilder)this;
    }

    public IHeadElementBuilder title(String title) {
        Element titleElement = new Element(ElementType.TITLE, currentElement);
        titleElement.setText(title);
        this.currentElement.addChild(titleElement);
        return (IHeadElementBuilder)this;
    }

    // ___________ BODY ELEMENTS _____________//

    public IListBuilder ul() {
        return (IListBuilder)this;
    }

    public IListBuilder ol() {
        return (IListBuilder)this;
    }

    public IListBuilder li() {
        return (IListBuilder)this;
    }

    public IBodyBuilder body() {
        Element body = new Element(ElementType.BODY, currentElement);
        appendChild(body);
        return (IBodyBuilder)this;
    }

    public IBodyBuilder div() {
        Element div = new Element(ElementType.DIV, currentElement);
        appendChild(div);
        return (IBodyBuilder)this;
    }

    public IBodyBuilder p() {
        Element p = new Element(ElementType.P, currentElement);
        appendChild(p);
        return (IBodyBuilder)this;

    }

    // ________ ELEMENT ATTRIBUTES __________//
    public IBodyBuilder id(String id) {
        if (attributesAllowed()) {
            this.currentElement.setId(id);
        }
        this.ids.add(id);
        return (IBodyBuilder)this;
    }

    public IBodyBuilder text(String text) {
        if (attributesAllowed()) {
            this.currentElement.setText(text);
        }
        return (IBodyBuilder)this;
    }

    public IBodyBuilder clazz(String className) {
        if (attributesAllowed()) {
            this.currentElement.setClazz(className);
        }
        this.classes.add(className);
        return (IBodyBuilder)this;
    }

    private boolean attributesAllowed() {
        return !blockedFromAttributes.contains(currentElement.getType());
    }

    public IBodyBuilder parent() {
        this.currentElement = this.previousElement;
        return (IBodyBuilder)this;
    }

    public IHTMLDocumentBuilder build() {
        this.htmlString = root.traverseTree(root);
        return this;
    }

    private void appendChild(Element newChild) {
        this.currentElement.addChild(newChild);
        setState(newChild);

    }

    private void setState(Element newChild) {
        this.previousElement = currentElement;
        this.currentElement = newChild;
    }

    public String getHTMLString() {
        return this.getHTMLString();
    }

    public boolean generateProject(String filename) {
        this.writeCSSDocument(filename);
        this.writeIHTMLDocumentBuilder(filename);
        return false;
    }

    private boolean writeIHTMLDocumentBuilder(String filename) {
        try {
            if (!filename.contains(".html")) {
                filename += ".html";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(this.htmlString);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return false;
        }
        return true;
    }

    private void writeClasses(BufferedWriter writer) throws IOException {
        for (String clazz : this.classes) {
            writer.write("\n\n." + clazz + "{\n\n}");
        }
    }

    private void writeIds(BufferedWriter writer) throws IOException {
        for (String id : this.ids) {
            writer.write("\n\n#" + id + "{\n\n}");
        }
    }

    private boolean writeCSSDocument(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename+ ".css"));
            writer.write("/*This document contains all generated classes and ids*/");
            writeClasses(writer);
            writeIds(writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return false;
        }
        return true;
    }

    public void printElements() {
        System.out.println(this.htmlString);
    }

}
