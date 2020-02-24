# HTML Internal DSL
An internal DSL for creating HTML documents


This project is created for the MDSD Course at University of Southern Denmark

## Create a new project
A new project can be generated by creating an instance of HTMLDocument and calling beginProject. From there on the chained methods should be self explanatory. Only body elements (div, p, li, ul) can contain classes, id and text. When you are done writing your document, call <i>.build()</i> and then <i>.generateProject(\<\<projectName\>\>)</i> from your <b>HTMLDocument</b> object.

### Example: 
```java
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


#### index.html
```html
<html>

<head>
    <title>My internal dsl </title>
</head>

<body>
    <div class="some-container">
        <p>some text in the container </p>
        <ul>
            <li>Første element </li>
            <li>Næste element </li>
            <li>Sidste element </li>
        </ul>
        <div class="some-other-container" id="some-id"> </div>
    </div>
</body>

</html>

```

#### index.css
```css
/*This document contains all generated classes and ids*/

.some-container{

}

.some-other-container{

}

#some-id{

}
```

## Rules of the fluent interface
Any new project starts with <i>beginProject()</i>. From this method you can choose to add the <b>head</b> or the <b>body</b> section. Any body elements can chain a class, id or text which will be added to the attributes or innerHTML (text). <b>li</b> elements can only follow <b>ul</b> and <b>ol</b>  elements. To get back to the parent class to embed tags correctly, you can use the <i>.parent()</i> function.

## Test
To test out the DSL, you can run the App.java, and it should generate a new project for you. You can also begin your own HTMLDocument and test it here.