import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class KlasaTestująca {
    void wykonajTestyDlaObiektu(Object obj) {
        Method[] wszystkieMetody = obj.getClass().getDeclaredMethods();
        List<Method> metodyPrzedTestem = wybierzMetodyZAdnotacją(wszystkieMetody, PrzedMetodą.class);
        List<Method> metodyTesty = wybierzMetodyZAdnotacją(wszystkieMetody, MójTest.class);

        for (Method testDoWykonania : metodyTesty){
            wywołajMetody(obj, metodyPrzedTestem.toArray(new Method[]{}));
            wywołajMetody(obj, testDoWykonania);
        }
    }

    private void wywołajMetody(Object obj, Method... metodyDoWykonania) {
        for (Method metoda : metodyDoWykonania){
            if (metoda.getModifiers() == Modifier.PUBLIC){
                try {
                    metoda.invoke(obj);
                } catch (IllegalAccessException e) {
                    // TODO obsłużyć/zalogować
                    System.err.println(e.getMessage() + "IllegalAccessException...  :P");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO obsłużyć/zalogować
                    System.err.println(e.getMessage() + "IllegalAccessException...  :P");
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Method> wybierzMetodyZAdnotacją(Method[] wszystkieMetody, Class adnotacja) {
        List <Method> methodsToReturn = new ArrayList<>();
        for (Method metoda : wszystkieMetody)
            if (metoda.getModifiers() == Modifier.PUBLIC && metoda.isAnnotationPresent(adnotacja))
                methodsToReturn.add(metoda);
        return methodsToReturn;
    }
}
