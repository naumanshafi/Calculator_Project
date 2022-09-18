package Calculator;

public class stringProcessingClass {
    public static double calculationOfExpression(final String str) {
        return new Object() {
            int pos = -1, ch;
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            boolean inputSymbol(int singleChar) {
                while (ch == ' ') nextChar();
                if (ch == singleChar) {
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse() {
                nextChar();
                double x = ParseExpressionFunction();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }



            double ParseExpressionFunction() {
                double x = ParseTermFunction();
                for (;;) {
                    if      (inputSymbol('+')) x += ParseTermFunction();
                    else if (inputSymbol('-')) x -= ParseTermFunction();
                    else return x;
                }
            }

            double ParseTermFunction() {
                double x = parseFactorFunction();
                for (;;) {
                    if      (inputSymbol('*')) x *= parseFactorFunction();
                    else if (inputSymbol('/')) x /= parseFactorFunction();
                    else return x;
                }
            }

            double parseFactorFunction() {
                if (inputSymbol('+')) return parseFactorFunction();
                if (inputSymbol('-')) return -parseFactorFunction();
                double x=0.0;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } 
                return x;
            }
        }.parse();
    }

}
