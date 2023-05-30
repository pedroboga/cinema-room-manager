import java.util.Scanner;

public class Cinema {

    private static Character[][] cinema;
    private static int ticketValue;
    private static int ticketsSold;
    private static int totalSeats;
    private static double percentageSold;
    private static int currentIncome;
    private static int totalIncome;
    private static int menuSelected;
    private static Boolean isOnMenu;
    private static Scanner scanner;
    private static int rows;
    private static int seats;

    public static void main(String[] args) {

        isOnMenu = true;
        ticketsSold = 0;
        currentIncome = 0;
        scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        createCinema(rows, seats);
        totalSeats = rows * seats;
        totalIncome = getProfit(rows, seats, 0);

        printCinema();

        while (isOnMenu) {
            showMenu();
            menuSelected = scanner.nextInt();
            switch (menuSelected) {
                case 1-> printCinema();
                case 2-> buyTicket();
                case 3-> statistics();
                case 0-> isOnMenu = false;
                default-> System.out.println("Invalid Option");
            }
        }
    }
    // }

    static void buyTicket() {
        Boolean keepBuying = true;
        while (keepBuying) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();
            if (row <= 0 || seat <= 0 || cinema.length < row || cinema[row - 1].length < seat) {
                System.out.println("Wrong input!");
            } else if (cinema[row - 1][seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased");
            } else {
                cinema[row - 1][seat - 1] = 'B';

                getProfit(rows, seats, row);
                System.out.println("Ticket price: $" + ticketValue);
                ticketsSold += 1;
                currentIncome += ticketValue;
                keepBuying = false;
            }

        }
    }

    static int getProfit(int rows, int seats, int seat) {
        int total = rows * seats;
        if (total <= 60) {
            ticketValue = 10;
            return total * ticketValue;
        } else {
            int half1 = rows / 2;
            ticketValue = seat > half1 ? 8 : 10;
            int half2 = (rows % 2 == 0) ? rows / 2 : rows / 2 + 1;
            return (half1 * seats * 10) + (half2 * seats * 8);
        }
    }

    static void statistics() {
        percentageSold = ((double) ticketsSold / totalSeats) * 100;
        System.out.println("Number of purchased tickets: " + ticketsSold);
        System.out.printf("Percentage: %.2f%%%n", percentageSold);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    static void createCinema(int rows, int seats) {
        cinema = new Character[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }
    }

    static void printCinema() {
        System.out.println("Cinema:");
        for (int i = 0; i < cinema.length; i++) {
            printRows(cinema[i], i);
        }
    }

    static void printRows(Character[] row, int rowLine) {
        String line = "";
        if (rowLine == 0) {
            line = " ";
            for (int i = 0; i <= cinema.length; i++) {
                line += " " + (i + 1);
            }
            System.out.println(line);
        }
        line = String.valueOf(rowLine + 1);
        for (int j = 0; j < row.length; j++) {
            line += (" " + row[j]);
        }

        System.out.println(line);
    }

    static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}