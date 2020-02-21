# HTML Internal DSL
An internal DSL for creating HTML documents


This project is created for the MDSD Course at University of Southern Denmark

### Create a new project
A new project can be generated by creating an instance of HTMLDocument and calling beginProject. From there on the chained methods should be self explanatory. Only body elements (div, p, li, ul) can contain classes, id and text. 

### Example: 
```
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

        document2.generateProject("My second Project");
            
```
### This code will generate this project:<br>
![Generated files](https://imgur.com/ryvjWm7.png)
