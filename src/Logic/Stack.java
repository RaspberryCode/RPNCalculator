package Logic;


import java.util.Objects;

public class Stack {


    private String[] stack;
    private int stackPosition;
    private int size;

    public Stack(Stack copy) {
        this.stack = copy.stack.clone();
        this.stackPosition = copy.stackPosition;
        this.size = copy.size;
    }

    //TODO dodać test: size mniejsze, większe od 10
    public Stack(int s) {
        this.size = s;
        if (size < 10)
            size = 10;
        this.stack = new String[size];
        this.stackPosition = -1;
    }

    //TODO dodać test do stosu pustego i wypełnionego
    public boolean isEmpty() {
        return stackPosition < 0;
    }

    public int getSize() {
        return size;
    }

    //TODO Dodać wyjątek, dodać testy: jakie wartości zwraca z wykorzystaniem push
    public String pop() {
        String top = new String("Stack is empty");
        if (!isEmpty()) {
            top = top();
            stack[stackPosition] = "0";
            stackPosition--;
        }
        return top;
    }

    //TODO dodać wyjątek, dodać testy
    public boolean topEquals(String str) {
        if (isEmpty())
            return false;

        return Objects.equals(str, top());
    }

    //TODO test jw.
    public String top() {
        if (isEmpty())
            return "Stack is empty";
        else return stack[stackPosition];
    }

    //TODO Dodać wyjątek przy przepełnionym stosie, test
    public void push(String str) {
        if (stackPosition < size)
            stack[++stackPosition] = str;
    }

    public void push(String[] strings) {
        for (String element : strings) {
            if (stackPosition < size)
                stack[++stackPosition] = element;
        }
    }

    //TODO dodać wyjątek, dodać testy
    public boolean topEquals(String[] strings) {
        for (String element : strings)
            if (element.equals(this.top()))
                return true;
        return false;
    }

    @Override
    public String toString() {
        Stack tmpStack = new Stack(this);
        String toString = new String();

        while (!tmpStack.isEmpty())
            toString += tmpStack.pop() + " ";

        return toString;
    }
}
