import java.util.TreeSet;

public class ShapeList {

    private TreeSet<Shape> setShapes = new TreeSet<>();

    public boolean add(Shape shape) throws Exception {

        if (setShapes.contains(shape))
            throw new Exception("Duplicate object");

        setShapes.add(shape);

        return true;
    }

    public TreeSet<Shape2D> get2DShapes() {

        TreeSet<Shape2D> retSet = new TreeSet<>();

        for (Shape shape : setShapes)
            if (shape instanceof Shape2D)
                retSet.add((Shape2D) shape);

        return retSet;
    }

    public TreeSet<Shape3D> get3DShapes() {

        TreeSet<Shape3D> retSet = new TreeSet<>();

        for (Shape shape : setShapes)
            if (shape instanceof Shape3D)
                retSet.add((Shape3D) shape);

        return retSet;
    }

    public void printFormatted() {

        System.out.println("+------+-------------+-------+-----------------------+-------------------+");
        System.out.println("| ID   | Name        | Color | Dimensions            | Description       |");
        System.out.println("+------+-------------+-------+-----------------------+-------------------+");

        Object[] parts = new Object[5];

        for (Shape shape : setShapes) {

            if (shape instanceof Quadrilateral) {

                Quadrilateral q = (Quadrilateral) shape;

                parts[0] = q.getId();
                parts[1] = q.getName();
                parts[2] = q.getColor();
                parts[3] = q.getDimensions();
                parts[4] = q.getDescription();
            }
            else if (shape instanceof Quadrilateral3D) {

                Quadrilateral3D q = (Quadrilateral3D) shape;

                parts[0] = q.getId();
                parts[1] = q.getName();
                parts[2] = q.getColor();
                parts[3] = q.getDimensions();
                parts[4] = q.getDescription();
            }

            System.out.printf("| %-4s | %-11s | %-5s | $%-20s | %-17s |%n", parts[0], parts[1], parts[2], parts[3], parts[4]);
            System.out.println("+------+-------------+-------+-----------------------+-------------------+");
        }
    }

    public int getSize() {

        return setShapes.size();
    }
}