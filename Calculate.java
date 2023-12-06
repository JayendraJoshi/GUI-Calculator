public class Calculate {
    String number1String ="";
    String operatorString ="";
    String number2String="";

    public void determine(String equation){
        char currentChar;

        for (int i = 0; i < equation.length(); i++) {
            currentChar = equation.charAt(i);

            if (Character.isDigit(currentChar)) {
                if (operatorString.isEmpty()) {
                    number1String += currentChar;
                } else {
                    number2String += currentChar;
                }
            } else {
                operatorString += currentChar;
            }
        }

        System.out.println(number1String + " " + operatorString+ " " + number2String);

    }

    public void addition(double zahl1, double zahl2){

        double ergebnis = zahl1 + zahl2;

    }


}
