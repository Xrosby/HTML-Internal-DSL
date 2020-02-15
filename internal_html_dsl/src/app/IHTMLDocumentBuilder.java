package app;

public interface IHTMLDocumentBuilder{


    public IBodyBuilder body();
    public IHeadElementBuilder head();
    public IHTMLDocumentBuilder build();
    public boolean generateProject(String filename);

    
    
}