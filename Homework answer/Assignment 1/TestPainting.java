import java.util.Scanner;

public class TestPainting {

    public static void main(String[] args) {

        Painting p1 = new Painting();

        p1.setArtistName("Mark Rothko");
        p1.setName("No. 6 (Violet, Green and Red)");
        p1.setPrice(186000000);
        p1.setYear(1951);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Artist Name: ");
        String artistName = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        Painting p2 = new Painting(artistName, name, price, year);

        System.out.printf("%24s%s%n", "Artist Name: ", p2.getArtistName());
        System.out.printf("%24s%s%n", "Name: ", p2.getName());
        System.out.printf("%24s%,.2f%n", "Price: ", p2.getPrice());
        System.out.printf("%24s%d%n", "Year: ", p2.getYear());
        System.out.printf("%24s%,d%n", "Age: ", p2.getAge());

        System.out.printf("%s", "Discounted Price Range: ");
        System.out.printf("%,.2f%s%,.2f%n", p2.getMinimumDiscountPrice(), " - ", p2.getMaximumDiscountPrice());

        scanner.close();
    }
}
