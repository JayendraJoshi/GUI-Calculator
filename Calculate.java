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
        System.out.println(resultString + " resultstring");
        System.out.println(number1String +" "+ operatorString +" "+ number2String + " = "+ resultString);
        return number1String + " " + operatorString + " " + number2String + " = " + resultString;

    }

    public String[] extractNumbersAndOperatorToArray(String equation) {
        char currentChar;

        for (int i = 0; i < equation.length(); i++) {
            currentChar = equation.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.' || currentChar == '%') {
                if (operatorString.isEmpty()) {
                    number1String += currentChar;
                } else if (!operatorString.contains("=")) {
                    number2String += currentChar;
                } else {
                    resultString += currentChar;
                }
            } else if (currentChar == '-' && i < equation.length() - 1 && Character.isDigit(equation.charAt(i + 1))) {
                if (number1String.isEmpty()) {
                    number1String += currentChar;
                } else if (!operatorString.isEmpty() && number2String.isEmpty()) {
                    number2String += currentChar;
                }
            } else {
                operatorString += currentChar;
            }

            operatorString = operatorString.replace("=", "");
            operatorString = operatorString.replace(" ", "");
            operatorString = operatorString.replace(".", "");
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

            }

        }
        return number1String + " " + operatorString + " " + number2String + " = " + resultString;
    }
private String chooseCalculation(String array[]) {
    String num1 = "";
    String num2 = "";
    char op = '0';

    double number1 = 0;
    double number2 = 0;

    for (int i = 0; i < 3; i++) {

        switch (i) {

            case 0:
                num1 = (array[i]);
                break;
            case 1:
                op = array[i].charAt(0);
                break;
            case 2:
                num2 = (array[i]);
                break;
        }
    }
    //Should use variable op instead, due to errors I have resorted to using the global variable operatorString
    if (!num1.contains("%") && !num2.contains("%")) {
        number1 = Double.parseDouble(num1);
        number2 = Double.parseDouble(num2);

        switch (operatorString.charAt(0)) {

            case '+':
                resultString = String.valueOf(addition(number1, number2));
                break;
            case '-':
                resultString = String.valueOf(subtraction(number1, number2));
                break;
            case '*':
                resultString = String.valueOf(multiplication(number1, number2));
                break;
            case '/':
                resultString = String.valueOf(division(number1, number2));
                break;

        }
    } else {
        if (num1.contains("%") && !num2.contains("%")) {
            number1 = Double.parseDouble(num1.replace("%", ""));
            number2 = Double.parseDouble(num2);
            resultString = String.valueOf(percentage(number1, number2, (byte) 1, op));
        } else if (!num1.contains("%") && num2.contains("%")) {
            number1 = Double.parseDouble(num1);
            number2 = Double.parseDouble(num2.replace("%", ""));
            System.out.println("Starting percentage method");
            resultString = String.valueOf(percentage(number1, number2, (byte) 2, op));
        } else if (num1.contains("%") && num2.contains("%")) {
            number1 = Double.parseDouble(num1.replace("%", ""));
            number2 = Double.parseDouble(num2.replace("%", ""));
            resultString = String.valueOf(percentage(number1, number2, (byte) 3, op));
        }
        number1String = String.valueOf(number1 / 100);
    }
    return resultString;
}
    private static double addition(double number1, double number2){

        double result= number1+ number2;
        return result;
    }
    private static double subtraction(double number1, double number2){
        double result= (number1) - (number2);
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
    private static double percentage(double number1, double number2, byte calculationProcedure, char op){
        double result = 0;
        if(calculationProcedure==1){
            System.out.println("Inside calculationprocedure 1");
            number1 = number1/100;
        }
        else if(calculationProcedure==2){
            System.out.println("Inside calculationprocedure 2");
            number2=(number1/100)*number2;
            System.out.println(number2 + " number2");
        }
        else if(calculationProcedure==3){
            System.out.println("Inside calculationprocedure 3");
            number1 = number1/100;
            number2=(number1/100)*number2;
        }

        switch (op) {
            case '+':
                result = (addition(number1, number2));
                break;
            case '-':
                result = (subtraction(number1, number2));
                break;
            case '*':
                result = (multiplication(number1, number2));
                break;
            case '/':
                result = (division(number1, number2));
                break;

        }
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
        Pattern pattern = Pattern.compile("=\\s*(-?[0-9]+(\\.[0-9]+)?)");

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
