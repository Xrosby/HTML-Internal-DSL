package app;



public class App {

 
    public static void main(String[] args) throws Exception {
       HTMLDocument document = 
                new HTMLDocument()
                    .head().clazz("Some class")
                        .title("My internal DSL")
                    .body()
                        .div().clazz("container").id("my-specific-container")
                        .p().clazz("my-content").text("Some internal dsl content")
                    .build();

        document.printElements();
        document.writeHTMLDocument("my_test_file");
    }



   
    }
