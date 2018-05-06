import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) {
        AdnotacjaDziedziczona adnotacjaDziedziczona = B.class.getAnnotation(AdnotacjaDziedziczona.class);
        System.out.println(adnotacjaDziedziczona.value());
    }
}

@AdnotacjaDziedziczona("JAKAŚ WARTOŚĆ")
class A{}

class B extends A{}