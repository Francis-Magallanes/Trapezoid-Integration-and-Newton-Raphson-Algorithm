import java.util.ArrayList;

public class NewtonRaphson {

    private static class ColumnData{
        private double x;
        private double y;
        private double yprime;
        private double relativeError;
    }

    private static double Truncation (double number) {
        return Math.floor(number * 100000) / 100000;
    }

    private static double Function (double x){
        return  (4 * Math.pow(x,12)) - (16 * Math.pow(x,11) ) + (3 * Math.pow(x,3)) + ( 64 * x ) + 76;
        //return  Math.pow(x,5) - (16 * Math.pow(x,4) ) + (3 * Math.pow(x,3)) + ( 64 * x ) + 18;
    }

    private static double FunctionPrime (double x){
        return  (48 * Math.pow(x,11)) - (176 * Math.pow(x,10) ) + (9 * Math.pow(x,2)) + 64;
     //   return (5 * Math.pow(x,4)) - (64 * Math.pow(x,3) ) + (9 * Math.pow(x,2)) + 64 ;
    }

    private static  ArrayList<ColumnData> ExecuteNewtonRaphson(double initialValueX, double fxStop , double relativeError){
        ArrayList<ColumnData> table = new ArrayList<>();

        double computeRelativeError = 10;
        double xvalue = Truncation(initialValueX);
        double yvalue =  Truncation(Function(xvalue));
        double yprime = Truncation(FunctionPrime(xvalue));

        while(yvalue > fxStop && computeRelativeError > relativeError){

            ColumnData columnData = new ColumnData();
            columnData.x = xvalue;
            columnData.y = yvalue;
            columnData.yprime = yprime;

            if(computeRelativeError == 10) columnData.relativeError = 0;
            else columnData.relativeError = computeRelativeError;
            table.add(columnData);

            xvalue = Truncation(xvalue - (yvalue / yprime));
            yvalue = Truncation( Function(xvalue) );
            yprime = Truncation( FunctionPrime(xvalue) );
            computeRelativeError = Math.abs(Truncation((xvalue - columnData.x) / xvalue));

        }

        ColumnData columnData = new ColumnData();
        columnData.x = xvalue;
        columnData.y = yvalue;
        columnData.yprime = yprime;

        if(computeRelativeError == 10) columnData.relativeError = 0;
        else columnData.relativeError = computeRelativeError;
        table.add(columnData);

        return table;
    }

    private static void DisplayTable(ArrayList<ColumnData> table){

       System.out.println("Results of Newton-Raphson Algorithm: ");
       for (int i = 0 ; i < table.size() ; i++){
           System.out.printf("Iteration = %4d     x = %10.5f       f(x) = %16.5f        f'(x) = %16.5f       Relative Error = %10.5f\n",
                   i , table.get(i).x , table.get(i).y , table.get(i).yprime , table.get(i).relativeError);
       }

    }

    public static void main(String[] args) {
        DisplayTable( ExecuteNewtonRaphson(7 , 0.001 , 0.0001));
    }

}
