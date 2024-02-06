import java.text.DecimalFormat;
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
        resultString = calculate(temp);
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
        return new String[]{number1String, operatorString, number2String, resultString};
    }
   
private String calculate(String array[]) {
    String num1 = "0";
    String num2 = "0";
    char op = ' ';
    String temp="";
    double number1 = 0;
    double number2 = 0;
    boolean breakIterator = false;
        num1 = "";  // Reset num1 to an empty string
        num2 = "";  // Reset num2 to an empty string
        number1 = 0;  // Reset number1 to 0
        number2 = 0;  // Reset number2 to 0
        //Put the original value of num1 in temp, to be displayed in the gui if number1String is the only variable used. E.G. 6² stays in temp while the result is stored in number1String and resultString
        temp = array[0];
        int index1 = 0;
        int index2 = 0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j< array[i].length();j++){          
                switch(i)
                {
                    case 0:
                    if (Character.isDigit(array[i].charAt(j))) {
                        num1 += (array[i].charAt(j));
                    }
                    else if(array[i].charAt(j)=='.'){
                        num1 += String.valueOf(array[i].charAt(j));
                        //cant direcly parse '.' to the double number1, so need to put it in String num1 first
                    }
                    else if(array[i].charAt(j)=='-'){
                        num1 += String.valueOf(array[i].charAt(j));
                    }
                    else if(array[i].charAt(j)=='√'){
                        //if String contains other special symbols besides √, the order of the symbols needs to be established first to calculate in the right order
                        if(array[i].contains("%")&& array[i].contains("²")){ //In case String contains √, % and ²
                          index1= array[i].indexOf("%");
                          index2= array[i].indexOf("²");
                          num1 =String.valueOf(array[i]).replace("√", "");
                          num1= num1.replace("%", "");
                            num1= num1.replace("²", "");
                            number1 = Double.parseDouble(num1);
                            num1=String.valueOf(number1);
                          if(index1>index2){
                           number1= square(number1);
                           number1 = percentage(number1, number2, (byte)1, containsCharacters(num1, "%"));
                            number1 = Math.sqrt(number1);
                            num1=String.valueOf(number1); //Current value needs to be assigned to num1, as after the loop the final values of num1 and num2 need to be assigned to number1 and number2 once again, as a direct assignment is not possible because of the '.', whcih can not be directly put into a double
                          }
                          else if(index1<index2){
                            number1 = percentage(number1, number2, (byte)1, containsCharacters(num1, "%"));
                            number1= square(number1);
                            number1 = Math.sqrt(number1);  
                            num1=String.valueOf(number1);
                          }                        
                          breakIterator=true;
                        }
                        else if (array[i].contains("%")){ //In case String contains √ and %
                            num1 =String.valueOf(array[i]).replace("√", "");
                            num1= num1.replace("%", "");
                            number1 = Double.parseDouble(num1);
                            number1 = percentage(number1, number2, (byte)1, containsCharacters(num1, "%"));
                            number1 = Math.sqrt(number1);      
                            num1=String.valueOf(number1);   
                            breakIterator=true;      
                        }
                        else if(array[i].contains("²")){ //In case String contains √ and ²
                            num1 =String.valueOf(array[i]).replace("√", "");
                            num1= num1.replace("²", "");
                            number1 = Double.parseDouble(num1);
                            number1 = square(number1);
                            number1 = Math.sqrt(number1);   
                            num1=String.valueOf(number1);     
                            breakIterator=true;
                        }
                        else{ //In case String only contains √
                            num1 =String.valueOf(array[i]).replace("√", "");
                            number1 = Double.parseDouble(num1);
                            number1 = Math.sqrt(number1);      
                            num1=String.valueOf(number1);  
                            breakIterator=true;    
                        }
                    }
                    else if(array[i].charAt(j)=='²'){
                       if(array[i].contains("%")){ //In case String contains ² and %
                            index1= array[i].indexOf("%");
                            index2= array[i].indexOf("²");
                            num1 =String.valueOf(array[i]).replace("²", "");
                            num1= num1.replace("%", "");
                            number1 = Double.parseDouble(num1);
                            num1=String.valueOf(number1);
                            if(index1>index2){
                                number1= square(number1);
                                number1 = percentage(number1, number2, (byte)1, containsCharacters(num1, "%"));
                                num1=String.valueOf(number1);
                            }
                            else if(index1<index2){
                                number1 = percentage(number1, number2, (byte)1, containsCharacters(num1, "%"));
                                number1= square(number1);
                                num1=String.valueOf(number1);
                            }
                        }
                        else{ //In case String only contains ²
                            num1 =String.valueOf(array[i]).replace("²", "");
                            number1 = Double.parseDouble(num1);
                            number1 = square(number1);
                            num1=String.valueOf(number1);
                        }
                        breakIterator=true;
                    }
                    else if(array[i].charAt(j)=='%'){ 
                        if(array[i].contains("²")){ //In case String contains % and ²
                            index1= array[i].indexOf("%");
                            index2= array[i].indexOf("²");
                            num1 =String.valueOf(array[i]).replace("%", "");
                            num1= num1.replace("²", "");
                            number1 = Double.parseDouble(num1);
                            num1=String.valueOf(number1);
                            if(index1>index2){
                                number1 = percentage(number1, number2, (byte)1, containsCharacters(num1, "%"));
                                number1= square(number1);
                                num1=String.valueOf(number1);
                            }
                            else if(index1<index2){
                                number1= square(number1);
                                number1 = percentage(number1, number2, (byte)1, containsCharacters(num1, "%"));
                                num1=String.valueOf(number1);
                            }
                        }
                        else{ //In case String only contains %
                            num1 =String.valueOf(array[i]).replace("%", "");
                            number1 = Double.parseDouble(num1);
                            number1 = percentage(number1, number2, (byte)1, containsCharacters(num1, "%"));
                            num1=String.valueOf(number1);
                        }
                        breakIterator=true;
                    }
                    break;
                    case 2:
                    if (Character.isDigit(array[i].charAt(j))) {
                        num2 += String.valueOf(array[i].charAt(j));
                    }
                    else if(array[i].charAt(j)=='.'){
                        num2 += String.valueOf(array[i].charAt(j));
                    }
                    else if(array[i].charAt(j)=='-'){
                        num2 += String.valueOf(array[i].charAt(j));
                    }
                    else if(array[i].charAt(j)=='√'){
                        //if String contains other special symbols besides √, the order of the symbols needs to be established first to calculate in the right order
                        if(array[i].contains("%")&& array[i].contains("²")){ //In case String contains √, % and ²   
                          index1= array[i].indexOf("%");
                          index2= array[i].indexOf("²");
                          num2 =String.valueOf(array[i]).replace("√", "");
                          num2= num2.replace("%", "");
                            num2= num2.replace("²", "");
                            number2 = Double.parseDouble(num2);
                            num2=String.valueOf(number2);
                          if(index1>index2){
                           number2= square(number2);
                           number2 = percentage(number1, number2, (byte)2, containsCharacters(num1, "%"));
                            number2 = Math.sqrt(number2);
                            num2=String.valueOf(number2);
                          }
                          else if(index1<index2){
                            number2 = percentage(number1, number2, (byte)2, containsCharacters(num1, "%"));
                            number2= square(number2);
                            number2 = Math.sqrt(number2);  
                            num2=String.valueOf(number2);
                          }     
                        }
                        else if (array[i].contains("%")){ //In case String contains √ and %
                            num2 =String.valueOf(array[i]).replace("√", "");
                            num2= num2.replace("%", "");
                            number2 = Double.parseDouble(num2);
                            number2 = percentage(number1, number2, (byte)2, containsCharacters(num1, "%"));
                            number2 = Math.sqrt(number2);
                            num2=String.valueOf(number2);   
                        }
                        else if(array[i].contains("²")){ //In case String contains √ and ²
                            num2 =String.valueOf(array[i]).replace("√", "");
                            num2= num2.replace("²", "");
                            number2= Double.parseDouble(num2);
                            number2 = square(number2);
                            number2 = Math.sqrt(number2);    
                            num2=String.valueOf(number2);    
                        }
                        else{ //In case String only contains √
                            num2 =String.valueOf(array[i]).replace("√", "");
                            number2 = Double.parseDouble(num2);
                            number2 = Math.sqrt(number2);      
                            num2=String.valueOf(number2);      
                        }
                        breakIterator=true;
                    }
                    else if(array[i].charAt(j)=='²'){
                       if(array[i].contains("%")){ //In case String contains ² and %
                            index1= array[i].indexOf("%");
                            index2= array[i].indexOf("²");
                            num2 =String.valueOf(array[i]).replace("²", "");
                            num2= num2.replace("%", "");
                            number2 = Double.parseDouble(num2);
                            num2=String.valueOf(number2);
                            if(index1>index2){
                                number2= square(number2);
                                number2 = percentage(number1, number2, (byte)2, containsCharacters(num1, "%"));
                                num2=String.valueOf(number2);
                            }
                            else if(index1<index2){
                                number2 = percentage(number1, number2, (byte)2, containsCharacters(num1, "%"));
                                number2= square(number2);
                                num2=String.valueOf(number2);
                            }
                        }
                        else{ //In case String only contains ²
                            num2 =String.valueOf(array[i]).replace("²", "");
                            number2 = Double.parseDouble(num2);
                            number2 = square(number2);
                            num2=String.valueOf(number2);
                        }
                        breakIterator=true;
                    }
                    else if(array[i].charAt(j)=='%'){ 
                        if(array[i].contains("²")){ //In case String contains % and ²
                            index1= array[i].indexOf("%");
                            index2= array[i].indexOf("²");
                            num2 =String.valueOf(array[i]).replace("%", "");
                            num2= num1.replace("²", "");
                            number2 = Double.parseDouble(num2);
                            num2=String.valueOf(number2);
                            if(index1>index2){
                                number2 = percentage(number1, number2, (byte)2, containsCharacters(num1, "%"));
                                number2= square(number2);
                                num2=String.valueOf(number2);
                            }
                            else if(index1<index2){
                                number2= square(number2);
                                number2 = percentage(number1, number2, (byte)2, containsCharacters(num1, "%"));
                                num2=String.valueOf(number2);
                            }
                        }
                        else{ //In case String only contains %
                            num2 =String.valueOf(array[i]).replace("%", "");
                            number2 = Double.parseDouble(num2);
                            number2 = percentage(number1, number2, (byte)2, containsCharacters(temp, "%"));// use temp instead as temp has the original value of num1 and num1 has already been updated at this point
                            num2=String.valueOf(number2);
                        }
                        breakIterator=true;
                    }
                    break;
                    case 1:
                    if(isNumeric(num1)) number1 = Double.parseDouble(num1);
                    op = array[1].charAt(0);
                    break;
                }
                if(breakIterator){
                    breakIterator = false;
                    break;
                }
            }
        }
    if((op!= '\u0000' && op!=' ')){
      number1 = Double.parseDouble(num1);
       number2 = Double.parseDouble(num2);
       switch (op) {
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
        resultString= formatNumbers(resultString);
    } 
    //if there is no operator, there is only 1 number and the result should be the same as the number
    else{
        resultString = String.valueOf(number1);
        resultString= formatNumbers(resultString);
        number1String = temp;
    }
    //if there are values in number1 and number2, the should be assigned to number1String and number2String, so that they canbe displayed in the gui
    if(number1 !=0 && number2 !=0){
        number1String = String.valueOf(number1);
        number2String = String.valueOf(number2);
        number1String = formatNumbers(number1String);
        number2String = formatNumbers(number2String);
    }
    operatorString= String.valueOf(op);
    num1="";
    num2="";
    temp="";
    number1=0;
    number2=0;
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
    public static double square(double number1){
        return number1 *number1;
    }
    public static double percentage(double number1, double number2, byte kindOfCalculation, boolean truth){
        double result =0;
        if(kindOfCalculation==1){
            if(number2==0)result= (number1/100);
        }
        else if(kindOfCalculation==2){
            if(truth==true){
                result= number2/100;
            }
            else if(truth==false){
                result= (number1 *number2)/100;       
            }
        }
        return result;
    }
    public static double squareRoot(double number1){
        return Math.sqrt(number1);
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
    public boolean containsCharacters(String input, String characters) {
        for (char c : characters.toCharArray()) {
            if (input.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
    private String formatNumbers(String str){
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");

        return str = decimalFormat.format(Double.parseDouble(str));
    }
}
