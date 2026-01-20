import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("From:");
        String from = sc.next().toUpperCase();

        System.out.println("To:");
        String to = sc.next().toUpperCase();

        System.out.println("Amount:");
        double amount = sc.nextDouble();

        Currency currency = new Currency(from, to, amount);

        CurrencyService service = new CurrencyService();
        CurrencyConverter converter = new CurrencyConverter();

        double rate = service.getRate(from, to);
        double result = converter.convert(amount, rate);

        System.out.println(result);
    }
}
