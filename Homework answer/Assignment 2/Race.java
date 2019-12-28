import java.util.Random;

public class Race {

    private static final int MAX_ITERATIONS = 100;
    private static final Random random = new Random();
    private static int runner1Positin = 0;
    private static int runner2Positin = 0;

    @Override
    public String toString() {

        return "Race simulation class";
    }

    public static void main(String[] args) {

        System.out.printf("%s%n", "On Your Mark, Get Set, Go");

        int counter = 0;

        printPositions(counter);

        while (runner1Positin != MAX_ITERATIONS - 1 && runner2Positin != MAX_ITERATIONS - 1) {

            ++counter;

            moveRunner1();
            moveRunner2();

            printPositions(counter);
        }

        if (runner1Positin == runner2Positin)
            System.out.printf("%s%n", "It's a tie");
        else if (runner1Positin > runner2Positin)
            System.out.printf("%s%n", "Runner#1 wins.");
        else
            System.out.printf("%s%n", "Runner2 wins.");

        System.out.printf("Time Elapsed = %d seconds\n", counter);
    }

    private static void printPositions(int counter) {

        System.out.printf("Time: %s%n", counter);

        for (int i = 0; i < MAX_ITERATIONS; ++i) {
            if (i == runner1Positin && runner2Positin == runner1Positin)
                System.out.print('B');
            else if (i == runner1Positin)
                System.out.printf("%s", "R1");
            else if (i == runner2Positin)
                System.out.printf("%s", "R2");
            else
                System.out.print(' ');
        }

        System.out.println();
        for (int i = 0; i < MAX_ITERATIONS; i++)
            System.out.printf("%s", "-");
        System.out.println();
    }

    private static void moveRunner1() {

        int num = random.nextInt(10);

        if (num >= 0 && num <= 4) // 50% Jump
            runner1Positin += 3;
        else if (num <= 7) // 30% Slip
            runner1Positin -= 6;
        else  // 20% Walk
            ++runner1Positin;

        // left boundary check
        if (runner1Positin < 0)
            runner1Positin = 0;
        else if (runner1Positin >= MAX_ITERATIONS)
            runner1Positin = MAX_ITERATIONS - 1;
    }

    private static void moveRunner2() {

        int num = random.nextInt(10);

        switch (num) {
            case 0: // 10% no moves
                break;
            case 1:
            case 2: // 10% Jump
                runner2Positin += 5;
                break;
            case 3:
            case 4: // 30% Small Slip
                runner2Positin -= 2;
                break;
            case 5: // 10% Slip
                runner2Positin -= 10;
                break;
            default: // 40% Walk
                ++runner2Positin;
                break;
        }

        // left boundary check
        if (runner2Positin < 0)
            runner2Positin = 0;
        else if (runner2Positin >= MAX_ITERATIONS)
            runner2Positin = MAX_ITERATIONS - 1;
    }
}
