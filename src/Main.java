import java.text.NumberFormat;

public class Main {
    public static final byte MONTH_IN_YEAR=12;
    public  static final byte  PERCENT=100;

    public static void main(String[] args)
    {
        int principal=(int) Console.readNumber("Enter the principal: ",1000,1_000_000);
        float annualInternet=(float) Console.readNumber("Enter the Annual Internet Rate: ",1,30);
        byte years=(byte) Console.readNumber("Period(Years): ",1,30);
        printMortgage(principal, annualInternet, years);
        printPaymentSchedule(principal, annualInternet, years);

    }

    public static void printMortgage(int principal, float annualInternet, byte years) {
        String mortgageFormatted= (NumberFormat.getCurrencyInstance().
                format(calculateMortgage(principal, annualInternet, years)));
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("——————————————————");
        System.out.println("Monthly Payments "+mortgageFormatted );
    }

    public static void printPaymentSchedule(int principal, float annualInternet, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("——————————————————");
        for (int month = 0; month<= years *MONTH_IN_YEAR; month++) {
            double balance = calculateBalance(principal, annualInternet, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double calculateMortgage(int principal,float annualInterest,byte years){

        double monthInterestRate=annualInterest/MONTH_IN_YEAR/PERCENT;
        double numberOfPayments=years*MONTH_IN_YEAR;
        double mortgage=principal*monthInterestRate*Math.pow(1+monthInterestRate,numberOfPayments)
                /(Math.pow(1+monthInterestRate,numberOfPayments)-1);
        return mortgage;
    }

    public static double calculateBalance(int principal,float annualInterest,byte years,int numberOfPaymentsMade){

        double monthInterestRate=annualInterest/MONTH_IN_YEAR/PERCENT;
        double numberOfPayments=years*MONTH_IN_YEAR;
        double balance=principal*(Math.pow(1+monthInterestRate,numberOfPayments)-Math.pow(1+monthInterestRate,numberOfPaymentsMade))
                /(Math.pow(1+monthInterestRate,numberOfPayments)-1);
        return balance;
    }
}