package jstp;

import java.lang.reflect.Field;
import java.util.HashMap;

public class JSTPObject {
    private HashMap<String, Field> fields;

    public JSTPObject() {
        this.fields = new HashMap<>();
    }

    public void addField (String name, Class c) {
        Field f;
        //f.set();
    }
}
