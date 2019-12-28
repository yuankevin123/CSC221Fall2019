import java.awt.Color;

public abstract class Shape implements Comparable<Shape> {

    protected final int id;
    protected final String name;
    protected final String description;
    protected Color color;

    public Shape(int id, String name, String description, Color color) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public abstract double area();

    public abstract double perimeter();

    @Override
    public String toString() {

        return String.format("%d %s %s %s%n", id, description, name, color.toString());
    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    public String getColor() {

        String strColor = "";

        if (color == Color.BLACK)
            strColor = "Black";
        else if (color == Color.BLUE)
            strColor = "Blue";
        else if (color == Color.GREEN)
            strColor = "Green";
        else if (color == Color.RED)
            strColor = "Red";
        else if (color == Color.WHITE)
            strColor = "White";

        return strColor;
    }
}