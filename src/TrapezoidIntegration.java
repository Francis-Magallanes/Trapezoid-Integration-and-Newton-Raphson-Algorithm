
public class TrapezoidIntegration {

    private static double Truncation (double number) {
        return Math.floor(number*100000)/ 100000;
    }

    private static double Function(double x){
        return   (3 * Math.pow(x,3)) + ( 64 * x ) + 76;
        //return (5 * x) - (2 * (Math.pow(x,2))) + (4 * (Math.pow(x,3))) ;
        //return  Math.pow(x,5) - (16 * Math.pow(x,4) ) + (3 * Math.pow(x,3)) + ( 64 * x ) + 18;

    }

    private static double TrapezoidRule(double lowerLimit, double upperLimit, int numSegments){
        double sum = 0.0;

        double h = Truncation((upperLimit - lowerLimit) / numSegments);

        double yUpperLimit = Truncation( Function(upperLimit) );

        double yLowerLimit = Truncation( Function(lowerLimit) );

        for(int i = 1 ; i <= numSegments - 1 ; i++ ) sum += Truncation( Function( lowerLimit + (i * h) ) );

        sum = 2 * sum ;

        double multiplier = Truncation((upperLimit - lowerLimit) / (2 * numSegments) ) ;

        return Truncation( multiplier * (yUpperLimit + sum + yLowerLimit)) ;
    }

    public static void main(String[] args) {
        System.out.println("The answer is " + TrapezoidRule(10, 20,32));
    }

}
