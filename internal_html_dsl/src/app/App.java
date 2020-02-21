package app;


public class App {

 
    public static void main(String[] args) throws Exception {

   
        HTMLDocument document2 = new HTMLDocument()
            .beginProject()
                .head()
                    .title("My internal dsl")
                .body()
                .div().clazz("some-container")
                    .p().text("some text in the container")
                .parent()
                    .ul().li("Første element").li("Næste element").li("Sidste element")
                .parent().div().clazz("some-other-container").id("some-id")
                .build();

        document2.generateProject("new-test-file");
            
            
                

    }
   
    }
