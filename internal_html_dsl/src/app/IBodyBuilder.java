package app;

public interface IBodyBuilder {
    public IBodyBuilder div();
    public IBodyBuilder p();
    public IListBuilder ul();
    public IListBuilder ol();
    public IBodyBuilder clazz(String className);
    public IBodyBuilder id(String id);
    public IBodyBuilder text(String text);
    public IBodyBuilder parent();
    public HTMLDocument build();

}