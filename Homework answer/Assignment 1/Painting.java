import java.util.GregorianCalendar;

public class Painting {

    private String artistName;
    private String name;
    private double price;
    private int year;

    public Painting() {

        artistName = "-";
        name = "-";
        price = 0;
        year = 0;
    }

    public Painting(String artistName, String name, double price, int year) {

        this.artistName = artistName;
        this.name = name;
        this.price = price;
        this.year = year;
    }

    public int getAge() {

        return new GregorianCalendar().get(GregorianCalendar.YEAR) - year;
    }

    public double getMinimumDiscountPrice() {

        return price * 0.85;
    }

    public double getMaximumDiscountPrice() {

        return price * 0.90;
    }

    public String getArtistName() {

        return artistName;
    }

    public void setArtistName(String artistName) {

        this.artistName = artistName;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public int getYear() {

        return year;
    }

    public void setYear(int year) {

        this.year = year;
    }
}