// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
        System.out.println();
        System.out.println();
		// Computes the periodical payment using brute force search
		System.out.print("Periodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
        System.out.println();
        System.out.println();
		// Computes the periodical payment using bisection search
		System.out.print("Periodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}


	public static double endBalance(double loan, double rate, int n, double payment)
	{	
	   double balance = loan;
       for (int i = 0; i < n; i++) 
	   {
        balance = (balance * (1 + rate / 100)) - payment;
       }
        return balance;
	}
	
	
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon)
	{
		double g = loan / n; 
        iterationCounter = 0;
         while (endBalance(loan, rate, n, g) > 0)
		{
        g += epsilon;
        iterationCounter++;
        }
       return g;
    }
    
 
    public static double bisectionSolver(double loan, double rate, int n, double epsilon)
    {
    iterationCounter = 0;
    return bisect(loan / n, loan, loan, rate, n, epsilon);
    }

private static double bisect(double low, double high, double loan, double rate, int n, double epsilon) 
{
    double mid = (low + high) / 2.0;

    if ((high - low) <= epsilon) {
        return mid;
    }

    iterationCounter++;

    if (endBalance(loan, rate, n, mid) > 0) {
        return bisect(mid, high, loan, rate, n, epsilon);
    } else {
        return bisect(low, mid, loan, rate, n, epsilon);
    }
}   


}
   
