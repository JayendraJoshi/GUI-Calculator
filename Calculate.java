import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {
    String number1String = "";
    String operatorString = "";
    String number2String = "";
    String resultString = "";
    boolean isResult=false;
    DecimalFormat dfNumbers = new DecimalFormat("#.####");
    DecimalFormat dfResult = new DecimalFormat("#.######");
    public String returnResultEquation(String equation) {

        String[] temp = extractNumbersAndOperatorToArray(equation);
        System.out.println(temp[1] + "operator");
        System.out.println(operatorString);
        resultString = chooseCalculation(temp);
        System.out.println(resultString + " resultstring");
        System.out.println(number1String +" "+ operatorString +" "+ number2String + " = "+ resultString);
        return number1String + " " + operatorString + " " + number2String + " = " + resultString;

    }
    public String[] extractNumbersAndOperatorToArray(String equation) {
        char currentChar;

        for (int i = 0; i < equation.length(); i++) {
            currentChar = equation.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.' || currentChar == '%' || currentChar == '√' || currentChar == '²') {
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
        System.out.println(number1String + " " + operatorString + " " + number2String +"insideExtract");
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
    String num1 = "0";
    String num2 = "0";
    char op = ' ';

    String temp="";
    double number1 = 0;
    double number2 = 0;

    for (int i = 0; i < 3; i++) {

        switch (i) {

            case 0:
                num1 = (array[i]);
                break;
            case 1:
                try {
                    op = array[i].charAt(0);
                }catch(Exception e){

                }
                break;
            case 2:
                num2 = (array[i]);
                break;
        }
    }

    if((num1.contains("√") && num1.contains("²"))|| (num2.contains("√") && num2.contains("²"))){
        System.out.println("1");
        if (num1.contains("√") && num1.contains("²")){
            num1 = (num1.replace("√", "").replace("²", ""));
            number1 = Double.parseDouble(num1);
            number1 = Math.sqrt(number1 *number1);
        }
        if(num2.contains("√") && num2.contains("²")){
            num2 = (num2.replace("√", "").replace("²", ""));
            number2 = Double.parseDouble(num2);
            number2 = Math.sqrt(number2 *number2);
        }
    }
    if((num1.contains("²") && num1.contains("%"))||(num2.contains("²")) && num2.contains("%")){
        System.out.println("2");
        if (num1.contains("²") && num1.contains("%")){
            num1 = (num1.replace("²", "").replace("%", ""));
            number1 = Double.parseDouble(num1);
            number1 = number1*(number1/100);
            number1 = Math.sqrt(number1);
        }
        if(num2.contains("²") && num2.contains("%")){
            num2 = (num2.replace("²", "").replace("%", ""));
            number2 = Double.parseDouble(num2);
            number2 = number2*(number2/100);
            number2 = Math.sqrt(number2);
        }
    }
    if (num1.contains("√")||num2.contains("√")){
        System.out.println("3");
        if (num1.contains("√")){
            num1 = (num1.replace("√", ""));
            number1 = Double.parseDouble(num1);
            number1 = Math.sqrt(number1);
        }
        if(num2.contains("√")){
            num2 = (num2.replace("√", ""));
            number2 = Double.parseDouble(num2);
            number2 = Math.sqrt(number2);
        }
    }
    if (num1.contains("%")||num2.contains("%")){
        System.out.println("4");
        if (num1.contains("%")){
            num1 = (num1.replace("%", ""));
            number1 = Double.parseDouble(num1);
            number1 = number1/100;
        }
        if(num2.contains("%")){
            num2 = (num2.replace("%", ""));
            number2 = Double.parseDouble(num2);
            number2 = number2=(number1/100)*number2;
        }
    }
    if(num1.contains("²")||num2.contains("²")){
        if (num1.contains("²")){
            num1 = (num1.replace("²", ""));
            number1 = (Double.parseDouble(num1));
            number1 = number1*number1;
        }
        if(num2.contains("²")){
            num2 = (num2.replace("²", ""));
            number2 = Double.parseDouble(num2);
            number2 = number2*number2;
        }
    }
//if there is an operator,there are 2 numbers and a calculation should be done
    if((op!= '\u0000' && op!=' ')){
            System.out.println("op is not empty");
            if(number1==0)number1 = Double.parseDouble(num1);
            if (number2==0)number2 = Double.parseDouble(num2);

        switch (op) {
            case '+':
                resultString = String.valueOf(addition(number1, number2));
                break;
            case '-':
            System.out.println("Min");
                resultString = String.valueOf(subtraction(number1, number2));
                System.out.println(resultString);
                break;
            case '*':
            System.out.println("Mal");
                System.out.println(number1+" "+number2);
                resultString = String.valueOf(multiplication(number1, number2));
                System.out.println(resultString);
                break;
            case '/':
                resultString = String.valueOf(division(number1, number2));
                break;
        }
    } 
    //if there is no operator, there is only 1 number and the result should be the same as the number
    else{
        System.out.println("op is empty");
        resultString = String.valueOf(number1);
    }
    //if there are values in number1 and number2, the should be assigned to number1String and number2String, so that they canbe displayed in the gui
    if(number1 !=0)number1String = String.valueOf(number1);
    if(number2 !=0)number2String = String.valueOf(number2);

    operatorString= String.valueOf(op);

    System.out.println(resultString + " resultstring in chooseCalculation");
    return resultString;
}

    private static double addition(double number1, double number2){

        double result= number1+ number2;
        return result;
    }
    private static double subtraction(double number1, double number2){
        double result= (number1) - (number2);
        System.out.println(result + " result in subtraction");
        return result;
    }
    private static double multiplication(double number1, double number2){
        System.out.println("in multiplication");
        System.out.println(number1+" "+number2);
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

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

}
