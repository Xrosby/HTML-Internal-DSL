package app;

import java.util.ArrayList;

public class Element {
    private ElementType type;
    private String clazz;
    private String id;
    private String text;
    private ArrayList<Element> children;

    public Element(ElementType type) {
        this.type = type;
    }

    public ElementType getType() {
        return this.type;
    }

    public String getClazz() {
        return this.clazz;
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<Element> getChildren() {
        return this.children;
    }

    public void addChild(Element child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        this.children.add(child);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClazz(String className) {
        this.clazz = className;
    }

    public String traverseTree(Element fromNode) {
        String htmlString = visitNodeAndAppend(fromNode, fromNode.getElementString());
        return htmlString;
    }

    private String visitNodeAndAppend(Element node, String htmlString) {

        for (Element n : node.getChildren()) {
                htmlString += n.getElementString();
                if(n.getChildren() != null) {
                    visitNodeAndAppend(n, htmlString);
                }
        
        }
        System.out.println("END OF VISITNODE" + htmlString);
        return htmlString;
    }

    public String idToAttributeString() {
        return (this.id != null) ? " id=" + "\"" + this.id + "\"" : "";
    }

    public String classToAttributeString() {
        return (this.clazz != null) ? " class=" + "\"" + this.clazz + "\"" : "";
    }

    public String textToString() {
        return (this.text != null) ? this.text : "";
    }

    public String getElementString() {
        String tagName = type.name().toLowerCase();
        String className = classToAttributeString();
        String idName = idToAttributeString();
        String text = textToString();
        String elementString = String.format("<%1$s%2$s%3$s>%4$s</%1$s>", tagName, className, idName, text);
        return elementString;
    }

    @Override
    public String toString() {
        return getElementString();
    }

}