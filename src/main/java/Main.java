import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        wybierzIWyświetlMetodyPodanychAktorów(new Klaska(), "Kamil", "Przemek", "Jarek");
    }

    private static void wybierzIWyświetlMetodyPodanychAktorów(Object obj, String... autorzy) {
        Method [] wszystkieMetody = obj.getClass().getMethods();
        List <Method> metodyWybranychAktorów = wybierzMetodyPodanychAktorów(wszystkieMetody, autorzy);
        wywołajMetody(obj, metodyWybranychAktorów);
    }

    private static boolean metodaJestWłasnościąKtóregośZAutorów(Method metoda, String[] autorzy) {
        if (metoda.isAnnotationPresent(Autor.class)) {
            Autor autor = metoda.getAnnotation(Autor.class);
            for (String imieAutora : autorzy)
                if (imieAutora.equals(autor.value()))
                    return true;
        }
        return false;
    }

    private static List wybierzMetodyPodanychAktorów(Method[] wszystkieMetody, String[] autorzy) {
        List<Method> metodyAutorów = new ArrayList<>();
        for (Method metoda : wszystkieMetody)
            if (metodaJestWłasnościąKtóregośZAutorów(metoda, autorzy))
                metodyAutorów.add(metoda);
        return metodyAutorów;
    }

    private static void wywołajMetody(Object obj, List<Method> metodyWybranychAktorów) {
        for (Method metoda : metodyWybranychAktorów){
            if (metoda.getModifiers() == Modifier.PUBLIC){
                try {
                    metoda.invoke(obj);
                } catch (IllegalAccessException e) {
                    // TODO obsłużyć, ew zalogować
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO obsłużyć, ew zalogować
                    e.printStackTrace();
                }
            }
        }
    }
}
