package app;

public interface IHTMLDocumentBuilder{


    public IBodyBuilder body();
    public IHeadElementBuilder head();
    public HTMLDocument build();

    
    
}