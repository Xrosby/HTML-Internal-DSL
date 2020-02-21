package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HTMLDocument {

    private String htmlString = "";
    private ArrayList<String> clazzes;
    private ArrayList<String> ids;

    public HTMLDocument(String hString, ArrayList<String> ids, ArrayList<String> clazzes) {
        this.clazzes = clazzes;
        this.ids = ids;
        this.htmlString = hString;
    }

    public HTMLDocument() {

    }

    public IHTMLDocumentBuilder beginProject() {
        return new HTMLDocumentBuilder();
    }

    public boolean generateProject(String filename) {
        this.generateProjectDir(filename);
        this.writeCSSDocument(filename);
        this.writeIHTMLDocumentBuilder(filename);
        return false;
    }

    public void testJSOUP() {
        String html = "<head></head>";
    }

    private boolean generateProjectDir(String filename) {
        return new File("/" + filename).mkdirs();
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
        for (String clazz : this.clazzes) {
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".css"));
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

    private static class HTMLDocumentBuilder
            implements IHTMLDocumentBuilder, IHeadElementBuilder, IBodyBuilder, IListBuilder {

        private ArrayList<String> ids;
        private ArrayList<String> classes;
        private Element root;
        private Element currentElement;
        private Element previousElement;
        private String htmlString = "";

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
            return (IHeadElementBuilder) this;
        }

        public IHTMLDocumentBuilder title(String title) {
            Element titleElement = new Element(ElementType.TITLE, currentElement);
            titleElement.setText(title);
            this.currentElement.addChild(titleElement);
            return this;
        }

        // ___________ BODY ELEMENTS _____________//

        public IListBuilder ul() {
            Element ul = new Element(ElementType.UL, currentElement);
            appendChild(ul);
            return (IListBuilder) this;
        }

        public IListBuilder ol() {
            Element ol = new Element(ElementType.OL, currentElement);
            appendChild(ol);
            return (IListBuilder) this;
        }

        public IListBuilder li(String listText) {
            Element li = new Element(ElementType.LI, currentElement);
            appendChild(li);
            return (IListBuilder) this;
        }

        public IBodyBuilder body() {
            Element body = new Element(ElementType.BODY, currentElement);
            appendChild(body);
            return this;
        }

        public IBodyBuilder div() {
            Element div = new Element(ElementType.DIV, currentElement);
            appendChild(div);
            return this;
        }

        public IBodyBuilder p() {
            Element p = new Element(ElementType.P, currentElement);
            appendChild(p);
            return this;

        }

        // ________ ELEMENT ATTRIBUTES __________//
        public IBodyBuilder id(String id) {
            this.currentElement.setId(id);
            this.ids.add(id);
            return this;
        }

        public IBodyBuilder text(String text) {
            this.currentElement.setText(text);

            return this;
        }

        public IBodyBuilder clazz(String className) {
            this.currentElement.setClazz(className);
            this.classes.add(className);
            return this;
        }

        public IBodyBuilder parent() {
            if(this.currentElement.getType() == ElementType.LI){
                this.currentElement = this.previousElement.getParent();
            } else {
            this.currentElement = this.previousElement;
            }
            return this;
        }

        public HTMLDocument build() {
            this.htmlString = root.traverseTree(root);
            return new HTMLDocument(this.htmlString, this.ids, this.classes);
        }

        private void appendChild(Element newChild) {
            this.currentElement.addChild(newChild);
            setState(newChild);

        }

        private void setState(Element newChild) {
            this.previousElement = currentElement;
            this.currentElement = newChild;
        }

        

    }
}