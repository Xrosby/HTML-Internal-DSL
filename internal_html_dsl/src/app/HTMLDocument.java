package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HTMLDocument {

    private Element root;
    private Element currentElement;
    private Element previousElement;
    private String htmlString;
    private ElementType[] blockedFromAttributes = new ElementType[] { ElementType.HEAD, ElementType.BODY,
            ElementType.TITLE, };

    public HTMLDocument() {
        root = new Element(ElementType.HTML);
        this.currentElement = root;
    }

    // __________ HEADER ELEMENTS __________//

    public HTMLDocument head() {
        Element head = new Element(ElementType.HEAD);
        this.root.addChild(head);
        this.currentElement = head;
        return this;
    }

    public HTMLDocument title(String title) {
        Element titleElement = new Element(ElementType.TITLE);
        titleElement.setText(title);
        if (this.currentElement.getType() == ElementType.HEAD) {
            this.currentElement.addChild(titleElement);
        } else {
            // TODO: Make it add title to head section dynamically
            System.out.println("Title can only be appended to head");
        }
        return this;
    }

    // ___________ BODY ELEMENTS _____________//

    public HTMLDocument body() {
        Element body = new Element(ElementType.BODY);
        appendChild(body);
        return this;
    }

    public HTMLDocument div() {
        Element div = new Element(ElementType.DIV);
        appendChild(div);
        return this;
    }

    public HTMLDocument p() {
        Element p = new Element(ElementType.P);
        appendChild(p);
        return this;

    }

    // ________ ELEMENT ATTRIBUTES __________//
    public HTMLDocument id(String id) {
        this.currentElement.setId(id);
        return this;
    }

    public HTMLDocument text(String text) {
        //TODO: Check allowed attributes
        boolean allowed = true;
        if (allowed) {
            this.currentElement.setText(text);
            return this;
        }else {
            return this;
        }

    }

    public HTMLDocument clazz(String className) {
        this.currentElement.setClazz(className);
        return this;
    }

    public HTMLDocument parent() {
        this.currentElement = this.previousElement;
        return this;
    }

    public HTMLDocument build() {
        String finalString = root.traverseTree(root);
        this.htmlString = finalString;
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

    public boolean writeHTMLDocument(String filename) {
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

    public void printElements() {
        System.out.println("THIS IS THE FINAL STRING");
        System.out.println(this.htmlString);
    }
}
