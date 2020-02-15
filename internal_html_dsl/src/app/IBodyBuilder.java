package app;

public interface IBodyBuilder extends IHTMLDocumentBuilder {
    public IBodyBuilder div();
    public IBodyBuilder p();
    public IListBuilder ul();
    public IBodyBuilder clazz(String className);
    public IBodyBuilder id(String id);
    public IBodyBuilder text(String text);
    public IBodyBuilder parent();

}