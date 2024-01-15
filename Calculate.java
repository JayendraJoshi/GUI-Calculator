import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {
    String number1String = "";
    String operatorString = "";
    String number2String = "";
    String resultString = "";
    boolean isResult=false;


    public String returnResultEquation(String equation) {

        String[] temp = extractNumbersAndOperatorToArray(equation);
        resultString = chooseCalculation(temp);
        return number1String + " " + operatorString + " " + number2String + " = " + resultString;

    }

    public String[] extractNumbersAndOperatorToArray(String equation) {
        char currentChar;
        String[] variableArray = new String[0];
        /*

        if (hasResult(equation)) {
            variableArray = new String[4];
            for (int i = 0; i < equation.length(); i++) {
                currentChar = equation.charAt(i);

                if (Character.isDigit(currentChar)) {
                    if (operatorString.isEmpty()) {
                        number1String += currentChar;
                    } else if (!operatorString.contains("=")) {
                        number2String += currentChar;
                    } else {
                        resultString += currentChar;
                    }
                }
                else {
                    operatorString += currentChar;
                }


            }
            variableArray[0] = number1String;
            variableArray[1] = operatorString;
            variableArray[2] = number2String;
            variableArray[3]=resultString;


        }
        else if(hasNumbers(equation)) {
            variableArray = new String[3];
            for (int i = 0; i < equation.length(); i++) {
                currentChar = equation.charAt(i);

                if (Character.isDigit(currentChar)) {
                    if (operatorString.isEmpty()) {
                        number1String += currentChar;
                    } else if (!operatorString.contains("=")) {
                        number2String += currentChar;
                    }
                } else {
                    operatorString += currentChar;
                }



            }
            variableArray[0] = number1String;
            variableArray[1] = operatorString;
            variableArray[2] = number2String;
        }

        else if(hasNumber1(equation)){
            variableArray = new String[2];
            
            for (int i = 0; i < equation.length(); i++) {
                currentChar = equation.charAt(i);

                if (Character.isDigit(currentChar)) {
                    if (operatorString.isEmpty()) {
                        number1String += currentChar;
                    }
                }
                else{
                    operatorString += currentChar;
                }
            }
             
            variableArray[0] = number1String;
            variableArray[1] = operatorString;
             
        }
        operatorString =operatorString.replace("=","");
        operatorString = operatorString.replace(" ","");
        operatorString = operatorString.replace(".","");
        System.out.println(number1String);
        System.out.println(number2String);
        System.out.println(operatorString);
        System.out.println(resultString);

    return variableArray;

         */

        for (char c : equation.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                if (operatorString.isEmpty()) {
                    number1String += c;
                } else if (!isResult) {
                    number2String += c;
                } else {
                    resultString += c;
                }
            } else if (c != ' ') {
                if (c == '=') {
                    isResult = true;
                } else {
                    operatorString += c;
                }
            }
        }

        return new String[]{number1String, operatorString, number2String, resultString};

    }


    public String extractValuesFromArray(String[] containsNumbersAndOperator){

        double num1;
        double num2;
        char op;
        double result;
        //if(hasResult())

        for (int i=0;i<4;i++){
            try {

                switch (i) {

                    case 0:
                        number1String = (containsNumbersAndOperator[i]);
                        break;
                    case 1:
                        op = containsNumbersAndOperator[i].charAt(0);
                        operatorString= String.valueOf(op);
                        break;
                    case 2:
                        number2String = containsNumbersAndOperator[i];
                        break;
                    case 3:
                        resultString = (containsNumbersAndOperator[i]);
                }
            }
            catch (Exception e){
                System.out.println("Error");
            }

        }
        return number1String + " " + operatorString + " " + number2String + " = " + resultString;
    }
private String chooseCalculation(String array[]){
    double num1 =0;
    double num2 =0;
    char op = '0';

        for (int i=0;i<3;i++){

            switch (i){

                case 0:
                    num1 = Double.parseDouble(array[i]);
                    break;
                case 1:
                    op = array[i].charAt(0);
                    break;
                case 2:
                    num2 = Double.parseDouble(array[i]);
                    break;
            }
    }

        //Should use variable op instead, due to errors I have resorted to using the global variable operatorString
        switch(operatorString.charAt(0)){

            case'+':
                resultString=String.valueOf(addition(num1,num2));
                    break;
            case'-':
                resultString=String.valueOf(subtraction(num1,num2));
                break;
            case'*':
                resultString=String.valueOf(multiplication(num1,num2));
                break;
            case '/':
                resultString=String.valueOf(division(num1,num2));
                break;

        }

        return resultString;
}


    private static double addition(double number1, double number2){

        double result= number1+ number2;
        return result;
    }
    private static double subtraction(double number1, double number2){

        double result= number1- number2;
        return result;
    }
    private static double multiplication(double number1, double number2){

        double result= number1* number2;
        return result;
    }
    private static double division(double number1, double number2){

        double result= number1/ number2;
        return result;
    }

    public void reset(){
        number1String="";
        number2String="";
        operatorString="";
        resultString="";
        isResult=false;
    }
    public boolean hasResult(String equation){
        Pattern pattern = Pattern.compile("=\\s*([0-9]+(\\.[0-9]+)?)");

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(equation);

        // Check if the pattern is found in the input
        return matcher.find();
    }
    public boolean hasNumber1(String equation){
// Create a pattern that matches a number
        Pattern pattern = Pattern.compile("\\b\\d+\\b");

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(equation);

        // Check if the pattern is found in the input
        boolean firstMatch = matcher.find();

        // If a first match is found, check if there is a second match
        if (firstMatch) {
            return !matcher.find();
        }

        // If no first match is found, return false
        return false;
    }
    public boolean hasNumbers(String equation){
// Create a pattern that matches a number
        Pattern pattern = Pattern.compile("\\b\\d+\\b");

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(equation);

        // Check if the pattern is found in the input
        boolean firstMatch = matcher.find();

        // If a first match is found, check if there is a second match
        if (firstMatch) {
            boolean secondMatch = matcher.find();

            // If a second match is found, check if there is a third match
            if (secondMatch) {
                return !matcher.find();
            }
        }

        // If no first match is found, or if no second match is found, return false
        return false;
    }

}
