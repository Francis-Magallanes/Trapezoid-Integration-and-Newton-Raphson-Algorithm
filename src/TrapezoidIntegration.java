
public class TrapezoidIntegration {

    private static double Truncation (double number) {

        //this function will truncate the inputted value
        return Math.floor(number*100000)/ 100000;
    }

    private static double Function(double x){
        //This function is for evaulauting the f(x)
        //this is where the adjustments are made for different f(x) of the integral

         return   (3 * Math.pow(x,3)) + ( 64 * x ) + 76;
        //return (5 * x) - (2 * (Math.pow(x,2))) + (4 * (Math.pow(x,3))) ;
        //return  Math.pow(x,5) - (16 * Math.pow(x,4) ) + (3 * Math.pow(x,3)) + ( 64 * x ) + 18;

    }

    private static double TrapezoidRule(double lowerLimit, double upperLimit, int numSegments){

        double sum = 0.0; // initialization of the sum

        double h = Truncation((upperLimit - lowerLimit) / numSegments); // this is for the computation and truncation of h

        double yUpperLimit = Truncation( Function(upperLimit) ); //evaluating and truncating the f(b)

        double yLowerLimit = Truncation( Function(lowerLimit) ); // evaluating and truncating the f(a)

        // this is the part where the sum of f(a + ih) is computed
        //note that the value of f(a+ih) is truncated first before adding to the sum
        for(int i = 1 ; i <= numSegments - 1 ; i++ ) sum += Truncation( Function( lowerLimit + (i * h) ) );

        sum = 2 * sum ;

        double multiplier = Truncation((upperLimit - lowerLimit) / (2 * numSegments) ) ; //computating the truncated value of (b-a)/2n

        // applying the formula the based from the computed values
        //the result of this computation will be truncated too.
        return Truncation( multiplier * (yUpperLimit + sum + yLowerLimit)) ;

    }

    public static void main(String[] args) {
        //"TrapezoidRule(10, 20,64)" will execute the trapezoid rule algorithm based on the hard-coded f(x)
        // 10 is the lower limit of integration parameter while 20 is the upper limit of integration parameter
        // 64 is the number of segments parameter
        // these parameters need to be adjusted manually depending on the definite integral to be computed
        System.out.println("The answer is " + TrapezoidRule(10, 20,64));
    }

}
