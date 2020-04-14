import java.util.ArrayList;

public class NewtonRaphson {

    //this part of the code will create a data structure where it will
    //store the different computed row values such as the x value,
    // y value, y' value and relative error
    private static class RowData {

        private double x;
        private double y;
        private double yprime;
        private double relativeError;
    }

    private static double Truncation (double number) {

        //this function will truncate the inputted value
        return Math.floor(number * 100000) / 100000;
    }

    private static double Function (double x){
        //This function is for evaulauting the f(x)
        //this is where the adjustments are made for different f(x) of the function

        return  (4 * Math.pow(x,12)) - (16 * Math.pow(x,11) ) + (3 * Math.pow(x,3)) + ( 64 * x ) + 76;
        //return  Math.pow(x,5) - (16 * Math.pow(x,4) ) + (3 * Math.pow(x,3)) + ( 64 * x ) + 18;
    }

    private static double FunctionPrime (double x){
        //This function is for evaulauting the f'(x)
        //this is where the adjustments are made for different f'(x) of the function

        return  (48 * Math.pow(x,11)) - (176 * Math.pow(x,10) ) + (9 * Math.pow(x,2)) + 64;
     //   return (5 * Math.pow(x,4)) - (64 * Math.pow(x,3) ) + (9 * Math.pow(x,2)) + 64 ;
    }

    private static  ArrayList<RowData> ExecuteNewtonRaphson(double initialValueX, double fxStop , double relativeError){

        //this will initialize the dynamic array variable where the row data will be stored and be displayed
        ArrayList<RowData> table = new ArrayList<>();

        //this will initialize the variables to used for the computation in the loop
        double computeRelativeError = 10; //this will ensure that the boolean expression in the loop will be true so that the loop will work
        double xvalue = Truncation(initialValueX);
        double yvalue =  Truncation(Function(xvalue));
        double yprime = Truncation(FunctionPrime(xvalue));

        //this loop is for the iterative process of the newton-raphson algorithm
        //the loop will break if the computed y is below or equal to the y stopping criteria or
        // if the computed relative error is below or equal to the relative error stopping criteria
        while(yvalue > fxStop && computeRelativeError > relativeError){

            //this will initialize the variable where the row data will be stored.
            RowData rowData = new RowData();

            //this will store the value of x,y and yprime to the variable
            rowData.x = xvalue;
            rowData.y = yvalue;
            rowData.yprime = yprime;

            if(computeRelativeError == 10) rowData.relativeError = 0; //this condition is for storing 0 in the relative error instead of 10 at the start of the iteration
            else rowData.relativeError = computeRelativeError;

            table.add(rowData);//this will add the row data to dynamic array variable and the data will be stored

            //this part of the code is for the computation of new x value(based from the newton-raphson algorithm), f(x), f'(x), and the relative error
            //note that the computed values will be truncated for each of the iteration
            xvalue = Truncation(xvalue - (yvalue / yprime));
            yvalue = Truncation( Function(xvalue) );
            yprime = Truncation( FunctionPrime(xvalue) );
            computeRelativeError = Math.abs(Truncation((xvalue - rowData.x) / xvalue));//note that the rowData.x is the value of the x at the previous iteration

        }

        //this part of the code will store the row data at which the loop exited
        //without this, the computed row values which the loop exited will not be displayed
        RowData rowData = new RowData();
        rowData.x = xvalue;
        rowData.y = yvalue;
        rowData.yprime = yprime;

        if(computeRelativeError == 10) rowData.relativeError = 0;
        else rowData.relativeError = computeRelativeError;
        table.add(rowData);

        return table; //returning the computed values
    }

    private static void DisplayTable(ArrayList<RowData> table){

        //this function will display the stored value of the dynamic array through iteration

       System.out.println("Results of Newton-Raphson Algorithm: ");
       for (int i = 0 ; i < table.size() ; i++){
           System.out.printf("Iteration = %4d     x = %10.5f       f(x) = %16.5f        f'(x) = %16.5f       Relative Error = %10.5f\n",
                   i , table.get(i).x , table.get(i).y , table.get(i).yprime , table.get(i).relativeError);
       }

    }

    public static void main(String[] args) {
        DisplayTable( ExecuteNewtonRaphson(7 , 0.001 , 0.0001)); //this will execute the newton-raphson algorithm and it will display the results
    }

}
