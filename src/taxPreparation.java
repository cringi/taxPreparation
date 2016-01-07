import java.util.Scanner;
public class taxPreparation {
    private static Scanner scanner = new Scanner(System.in);
    private static final int SINGLE_TAX = 20, MARRIED_TAX = 25, COHABITING_UNDER_20_TAX = 10, COHABITING_20_50_TAX = 15,
    COHABITING_OVER_50_TAX = 30, EXEMPTION_REDUCTION=1000;

    private static boolean IS_SINGLE, IS_MARRIED, IS_COHABITING;
    private static int user_income, user_exemptions;

    public static void main(String[] args) {
        System.out.println("tax preparations");
        System.out.println("another really cool program by brandon berney");
        start();
    }

    private static void start() {
        reset();
        survey();
        System.out.println(calculations());
        restart_query();
    }

    private static void reset() {
        // Resets variables.
        IS_SINGLE = false;
        IS_MARRIED = false;
        IS_COHABITING = false;
    }

    private static void survey() {
        // Starts the survey.
        survey_relationship();
        survey_money();
        survey_exemptions();
    }

    private static void survey_relationship() {
        String relationship_status;
        System.out.print("\nAre you [s]ingle, [m]arried, or [c]ohabiting? ");
        relationship_status = scanner.next();
        relationship_status = relationship_status.toLowerCase();

        switch(relationship_status) {
            case "s":
                IS_SINGLE = true;
                break;
            case "m":
                IS_MARRIED = true;
                break;
            case "c":
                IS_COHABITING = true;
                break;
            default:
                System.out.println("Invalid selection.");
                survey_relationship();
                break;
        }
    }

    private static void survey_money() {
        System.out.print("Gross income: $");
        user_income = scanner.nextInt();
    }

    private static void survey_exemptions() {
        System.out.print("Exemptions: ");
        user_exemptions = scanner.nextInt();
    }

    private static String calculations() {
        String calculation;
        int tax_rate=0;
        double taxable_income, taxes_owed;
        if (IS_SINGLE) {
            tax_rate = SINGLE_TAX;
        }
        else if (IS_MARRIED) {
            tax_rate = MARRIED_TAX;
        }
        else if (IS_COHABITING){
            if (user_income < 20000) {
                tax_rate = COHABITING_UNDER_20_TAX;
            }
            else if (user_income < 50000) {
                tax_rate = COHABITING_20_50_TAX;
            }
            else {
                tax_rate = COHABITING_OVER_50_TAX;
            }
        }

        taxable_income = tax_income_calculator(user_income, user_exemptions);
        taxes_owed = tax_calculator(taxable_income, tax_rate);

        calculation = String.format("%nINCOME TAX SUMMARY%n%ntax rate: %d%%%ntaxable income: $%.2f%ntaxes owed: $%.2f%n",
                tax_rate, taxable_income, taxes_owed);
        return calculation;
    }

    private static double tax_income_calculator(double income, int exemptions) {
        return (income - (EXEMPTION_REDUCTION * exemptions));
    }

    private static double tax_calculator(double income, int tax) {
        return (income * (tax * .01));
    }

    private static void restart_query() {
        String restart;
        System.out.println("Process another customer? (y/n) ");
        restart = scanner.next();

        if (restart.equalsIgnoreCase("y")) {
            start();
        }
        else {
            System.exit(0);
        }
    }
}
