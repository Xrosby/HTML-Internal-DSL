package app;

import java.util.ArrayList;
import java.util.Arrays;

public class Element {
    private ElementType type;
    private Element parent;
    private String clazz;
    private String id;
    private String text;
    private ArrayList<Element> children;

    public Element(ElementType type, Element parent) {
        this.type = type;
        this.parent = parent;
    }

    public Element getParent() {
        return this.parent;
    }

    public ElementType getType() {
        return this.type;
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
        String htmlString = fromNode.getElementString();
        htmlString += visitNodeAndAppend("", fromNode);
        return htmlString;
    }

    private String visitNodeAndAppend(String prefix, Element node) {
        StringBuilder localStringBuilder = new StringBuilder().append(node);
        if (node.getChildren() != null) {
            for (Element child : node.getChildren()) {
                String opening = prefix + child.getOpeningTag();
                System.out.println(opening);
                localStringBuilder.append(opening);
                visitNodeAndAppend("\t" + prefix, child);
                String closing = prefix + child.getClosingTag();
                System.out.println(closing);
                localStringBuilder.append(closing);
            }
        }
        return localStringBuilder.toString();
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

    public String getOpeningTag() {
        String tagName = type.name().toLowerCase();
        String className = classToAttributeString();
        String idName = idToAttributeString();
        String text = textToString();
        return "<" + tagName + className + idName + ">" + text;
    }

    public String getClosingTag() {
        String tagName = type.name().toLowerCase();
        return "</" + tagName + ">";
    }

    @Override
    public String toString() {
        return getElementString();
    }

}