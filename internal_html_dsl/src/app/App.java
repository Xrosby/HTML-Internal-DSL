package app;



public class App {

 
    public static void main(String[] args) throws Exception {
       IHTMLDocumentBuilder document = 
                new HTMLDocumentBuilder()
                    .head().title("My internal dsl")
                    .body()
                        .div().clazz("container").id("my-specific-container")
                        .p().clazz("my-content").text("Some internal dsl content")
                    .parent()
                    .div().clazz("some-other-container").text("Some random div text")
                    .build();


        document.generateProject("my_test_file");
    }


   
    }
