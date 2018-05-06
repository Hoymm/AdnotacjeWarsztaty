import javax.annotation.PostConstruct;

public class X {
    public X() {
        System.out.println("KONSTRUKTOR X");
    }

    @PostConstruct
    void metoda(){
        System.out.println("Wykonujemy jakąś metodę");
    }
}
