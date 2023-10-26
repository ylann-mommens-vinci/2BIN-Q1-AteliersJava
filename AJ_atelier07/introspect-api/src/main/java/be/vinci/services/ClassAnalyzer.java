package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Logger;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods",getMethods());
        return objectBuilder.build();
    }

    /**
     * Get a field, and create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        //TODO done add missing info
        objectBuilder.add("name",f.getName());
        objectBuilder.add("type",f.getType().getSimpleName());
        objectBuilder.add("visibility", getFieldVisibility(f));
        objectBuilder.add("isStatic", isFieldStatic(f));

        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     * This method rely on the getField() method to handle each field one by one.
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all fields descriptions to array (use the getField() method above)
        for (Field f: aClass.getDeclaredFields()) {
            arrayBuilder.add(getField(f));
        }
        return arrayBuilder.build();
    }

    public JsonObject getMethod(Method m){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("Name",m.getName());
        //objectBuilder.add("Type",);
        //objectBuilder.add("visibility",);
        //objectBuilder.add("parameters",);
        objectBuilder.add("return",m.getReturnType().getSimpleName());
        return objectBuilder.build();
    }

    public JsonArray getMethods(){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Method m: aClass.getDeclaredMethods()) {
            arrayBuilder.add(getMethod(m));
        }
        return null;
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {
        // TODO done
        return Modifier.isStatic(f.getModifiers());
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) {
        //TODO done
        if (Modifier.isPrivate(f.getModifiers()))
            return "private";
        if (Modifier.isPublic(f.getModifiers()))
            return "public";
        if (Modifier.isProtected(f.getModifiers()))
            return "protected";
        return "package";
    }
}
