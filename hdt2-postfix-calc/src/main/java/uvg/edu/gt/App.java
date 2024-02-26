package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App 
{
public static int evaluatePostfix(String expression) {
    VectorStack<Integer> stack = new VectorStack<>();

    String[] tokens = expression.split("\\s+");

    for (String token : tokens) {
        if (token.matches("\\d+")) {
            stack.push(Integer.parseInt(token));
        } else {
            if (stack.size() < 2) {
                throw new IllegalArgumentException("Expresión Invalida");
            }
            int operand2 = stack.pop();
            int operand1 = stack.pop();
            switch (token) {
                case "+":
                    stack.push(operand1 + operand2);
                    break;
                case "-":
                    stack.push(operand1 - operand2);
                    break;
                case "*":
                    stack.push(operand1 * operand2);
                    break;
                case "/":
                    stack.push(operand1 / operand2);
                    break;
                default:
                    throw new IllegalArgumentException("Operador invalido: " + token);
            }
        }
    }

    if (stack.size() != 1) {
        throw new IllegalArgumentException("Expresión Invalida");
    }

    return stack.pop();
}

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\main\\java\\uvg\\edu\\gt\\datos.txt"));
            String expression = reader.readLine();
            reader.close();
            int result = evaluatePostfix(expression);
            System.out.println("Resultado: " + result);
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
    }
}