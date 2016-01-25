/**
 * Created by simonhamermesh on 1/20/16.
 */
enum Color {RED, BLUE, YELLOW, GREEN, ORANGE}

public class ColorfulThing {

    Color enumColor;
    //ENUM version of constructor
    ColorfulThing(Color input) { enumColor = input;}
    public Color getEnumColor() {return enumColor;}

    String strColor;
    // String version of constructor
    ColorfulThing(String input) {strColor = input;}
    public String getStrColor() {return strColor;}


    public static Color getRandomColor() {
        return Color.values()[(int) (Math.random() * Color.values().length)];
    }
}