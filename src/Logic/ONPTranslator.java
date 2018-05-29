package Logic;

public class ONPTranslator {

    private Stack prefix;

    public String calculate(String input) {
        prefix = toPrefix(input);
        return calculatePrefix(prefix);
    }

    //TODO test, TODO exception
    public Stack toPrefix(String infix) {
        Stack tmpStack = new Stack(infix.length());
        Stack tmpPrefix = new Stack(infix.length());
        if (!infix.endsWith("="))
            infix += "=";
        for (int i = 0; i < infix.length(); i++)
            //rozpoznawanie liczby zmiennoprzecinkowej
            if (infix.charAt(i) >= '0' && infix.charAt(i) <= '9' || infix.charAt(i) == '.') {
                StringBuilder number = new StringBuilder(12);
                number.append(infix.charAt(i));

                while (infix.charAt(i + 1) >= '0' && infix.charAt(i + 1) <= '9' || infix.charAt(i + 1) == '.')
                    number.append(infix.charAt(++i));

                tmpPrefix.push(number.toString());
                //rozpoznawanie znaku
            } else {
                switch (infix.charAt(i)) {
                    case '-':
                    case '+': {
                        while (!tmpStack.isEmpty() && !tmpStack.topEquals("("))
                            tmpPrefix.push(tmpStack.pop());
                        tmpStack.push(infix.substring(i, i + 1));
                        break;
                    }
                    case '*':
                    case '/': {
                        while (!tmpStack.isEmpty() && !tmpStack.topEquals(new String[]{"(", "-", "+"}))
                            tmpPrefix.push(tmpStack.pop());
                        tmpStack.push(infix.substring(i, i + 1));
                        break;
                    }
                    case '^':
                        while (!tmpStack.isEmpty() && !tmpStack.topEquals(new String[]{"(", "-", "+", "*", "/"}))
                            tmpPrefix.push(tmpStack.pop());
                        tmpStack.push(infix.substring(i, i + 1));
                        break;
                    case '(':
                        tmpStack.push(infix.substring(i, i + 1));
                        break;

                    case ')':
                        while (!tmpStack.isEmpty() && !tmpStack.topEquals("("))
                            tmpPrefix.push(tmpStack.pop());
                        if (tmpStack.topEquals("("))
                            tmpStack.pop();
                        break;
                    case '=':

                        while (!tmpStack.isEmpty() && !tmpStack.topEquals("("))
                            tmpPrefix.push(tmpStack.pop());
                        tmpPrefix.push(infix.substring(i, i + 1));
                        break;
                    default:
                        break;
                }
            }

        Stack tmp = new Stack(100);
        while (!tmpPrefix.isEmpty())
            tmp.push(tmpPrefix.pop());

        return tmp;
    }

    public String calculatePrefix(Stack prefixStack) {
        Stack tmpStack = new Stack(50); //stos przetrzymujący liczby
        String tmp;
        double firstDouble, secondDouble;

        while (!prefixStack.isEmpty()) {
            tmp = prefixStack.pop();
            //jeśli jest liczbą wrzuć na stos pomocniczy
            if (tmp.charAt(0) >= '0' && tmp.charAt(0) <= '9') {
                tmpStack.push(tmp);

            } else if (tmp.charAt(0) == '=')
                return tmpStack.pop();

            else {//jeśli nie, wykonaj działanie na dwóch liczbach ze stosu
                firstDouble = Double.parseDouble(tmpStack.pop());
                secondDouble = Double.parseDouble(tmpStack.pop());
                switch (tmp.charAt(0)) {
                    case '-': {
                        tmpStack.push("" + (firstDouble - secondDouble));
                        break;
                    }
                    case '+': {
                        tmpStack.push("" + (firstDouble + secondDouble));
                        break;
                    }
                    case '*': {
                        tmpStack.push("" + (firstDouble * secondDouble));
                        break;
                    }
                    case '/': {
                        tmpStack.push("" + (firstDouble / secondDouble));
                        break;
                    }
                    case '^': {
                        tmpStack.push("" + Math.pow(firstDouble, secondDouble));
                        break;
                    }
                    default:
                        break;
                }
            }

        }
        return "0";
    }
}