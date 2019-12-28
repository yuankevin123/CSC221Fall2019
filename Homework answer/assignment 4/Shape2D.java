import java.awt.Color;

public abstract class Shape2D extends Shape {

    public final double height;
    public final double width;

    public Shape2D(int id, String name, String description, Color color, double height, double width) {

        super(id, name, description, color);
        this.height = height;
        this.width = width;
    }

    public String getDimensions() {

        return String.format("%.2f:%.2f", height, width);
    }

    @Override
    public String toString() {

        return String.format("%s %d %d%n", super.toString(), height, width);
    }

    @Override
    public int compareTo(Shape shape) {

        int result = -1;

        if (shape instanceof Shape2D) {

            Shape2D shape2d = (Shape2D) shape;

            if (this.getName().compareToIgnoreCase(shape2d.getName()) == 0 && this.height == shape2d.height && this.width == shape2d.width)
                result = 0;
        }

        return result;
    }

    public double getHeight() {

        return height;
    }

    public double getWidth() {

        return width;
    }
}