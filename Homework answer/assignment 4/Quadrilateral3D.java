import java.awt.Color;

public class Quadrilateral3D extends Shape3D {

    public Quadrilateral3D(int id, String name, String description, Color color, double height, double width, double length) {

        super(id, name, description, color, height, width, length);
    }

    @Override
    public double area() {

        return 2 * (width * height + width * length + length * height);
    }

    @Override
    public double perimeter() {

        return 2 * (width + height);
    }
}