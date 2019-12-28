import java.awt.Color;

public abstract class Shape3D extends Shape2D {

    public final double length;

    public Shape3D(int id, String name, String description, Color color, double height, double width, double length) {

        super(id, name, description, color, height, width);
        this.length = length;
    }

    @Override
    public String toString() {

        return String.format("%s %d %d %d%n", super.toString(), height, width, length);
    }

    @Override
    public int compareTo(Shape shape) {

        int result = -1;

        if (shape instanceof Shape3D) {

            Shape3D shape3d = (Shape3D) shape;

            if (super.compareTo(shape) == 0 && this.length == shape3d.length)
                result = 0;
        }

        return result;
    }

    public double getLength() {

        return length;
    }

    public String getDimensions() {

        return String.format("%s:%.2f", super.getDimensions(), length);
    }
}
