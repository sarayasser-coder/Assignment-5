package eg.edu.alexu.csd.datastructure.stack.cs;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ExpressionEvaluator implements IExpressionEvaluator {
    /**
     *Evaluate the priority of operator
     * @param op
     * operator that pushed to stack ,the peak operator.
     * @return priority
     */
    public int priority (Object op){
        char p = (char) op;
        switch (p){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Convert infix notation to be suitable for dealing with negative numbers using dummy zero.
     * @param infix
     * infix from user
     * @return suitable infix notation
     */
    public String negative(String infix){
        String suitInfix="";
        int k=0;
        for (int i=0 ;i<infix.length();i++){
            if (((infix.charAt(i)=='('||infix.charAt(i)=='+'||infix.charAt(i)=='-'||infix.charAt(i)=='*'||infix.charAt(i)=='/')&&(infix.charAt(i+1)=='+'||infix.charAt(i+1)=='-'||infix.charAt(i+1)=='/'||infix.charAt(i+1)=='*'))||(infix.charAt(i)=='-'&&i==0 )){
                if (infix.charAt(i + 1) == '-') {
                    suitInfix += infix.charAt(i) + "(0";
                    k = 1;
                }
                else if (infix.charAt(i+1)=='+'||infix.charAt(i+1)=='*'||infix.charAt(i+1)=='/'){
                    throw new RuntimeException("Invalid Operator!!");
                }
                else {
                    suitInfix += "(0" + infix.charAt(i);
                    k=1;
                }
            }
            else if(k==1){
                if (i<infix.length()-1){
                if(Character.isDigit(infix.charAt(i+1))||Character.isAlphabetic(infix.charAt(i+1))) {
                    suitInfix += infix.charAt(i);
                }
                else {
                    suitInfix += infix.charAt(i) + ")";
                    k=0;
                }
                }
                else {
                    suitInfix += infix.charAt(i) + ")";
                }
            }
            else{
                suitInfix += infix.charAt(i);
            }
        }
        return suitInfix;
    }
    @Override
    public String infixToPostfix(String expression) {
        String infix = expression.replaceAll("\\s+", "");
        String suitInfix = negative(infix);
        Stack S = new Stack();
        String postfix = "";
        for (int i=0;i<suitInfix.length();i++){
            if(Character.isDigit(suitInfix.charAt(i)) || Character.isAlphabetic(suitInfix.charAt(i))){
                postfix += suitInfix.charAt(i);
            }
            else if (suitInfix.charAt(i) == '('){
                S.push('(');
            }
            else if (suitInfix.charAt(i) == ')'){
                while ((char)S.peek() != '('){
                    postfix += S.peek();
                    S.pop();
                }
                S.pop();
            }
            else if (suitInfix.charAt(i) == '+'||suitInfix.charAt(i) == '-'||suitInfix.charAt(i) == '*'||suitInfix.charAt(i) == '/'){
                while (!S.isEmpty()&&priority(suitInfix.charAt(i)) <= priority(S.peek())){
                    postfix += S.peek();
                    S.pop();
                }
                S.push(suitInfix.charAt(i));
                postfix += " ";
            }
            else {
                throw new RuntimeException("Invalid Operator!!");
            }
        }
        while (!S.isEmpty()){
            postfix += S.peek();
            S.pop();
        }
        for (int i=0;i<postfix.length();i++) {
            if (i<postfix.length()-1) {
                if (Character.isAlphabetic(postfix.charAt(i))) {
                    if (Character.isAlphabetic(postfix.charAt(i+1))) {
                        throw new RuntimeException("Invalid Variable");
                    }
                }
            }
        }
        return postfix;
    }

    /**
     *Evaluate math operations.
     * @param op1
     * first operand, the second in the stack.
     * @param op2
     * second operand ,the peak of the stack.
     * @param op
     * operator wanted to be executed
     * @return the result of operation
     */
    public float mathOperation(float op1,float op2,char op){
        switch (op){
            case'+':
                return op1 + op2;
            case '-':
                return op1-op2;
            case '*':
                return op1*op2;
            case '/':
                if (op2 == 0){
                    throw new RuntimeException("Invalid Division!!");
                }
                return op1-op2;
            default:
                throw new RuntimeException("Invalid Operator!!");
        }
    }
    @Override
    public int evaluate(String expression) {
        if (Character.isAlphabetic(expression.charAt(0))){
            throw new RuntimeException("Invalid Evaluation!!");
        }
    int k=0;
    for (int i =0;i<expression.length();i++){
        if(!Character.isDigit(expression.charAt(i))){
            break;
        }
        else {
            k++;
        }

    }
    if(k==expression.length()){
        return Integer.parseInt(expression);
    }
    Stack S = new Stack();
    String temp = "";
    char op;
    float op1;
    float op2;
    float value;
    float result;
        for (int i=0;i<expression.length();i++){
            if (Character.isDigit(expression.charAt(i))) {
                if (Character.isDigit(expression.charAt(i + 1))) {
                    temp += expression.charAt(i);
                }
                else {
                    temp += expression.charAt(i);
                    value = Float.parseFloat(temp.trim());
                    S.push(value);
                    temp = "";
                    while (i<expression.length()-1) {
                        if (expression.charAt(i + 1) == ' ') {
                            i++;
                        }
                        else {
                            break;
                        }
                    }
                }
            }
            else {
                op = expression.charAt(i);
                op2 = (Float) S.peek();
                S.pop();
                op1 = (Float) S.peek();
                S.pop();
                result = mathOperation(op1, op2, op);
                S.push(result);
                while (i<expression.length()-1) {
                    if (expression.charAt(i + 1) == ' ') {
                        i++;
                    }
                    else{
                        break;
                    }
                }
            }
        }
        return (int) Math.round((Float)S.peek()) ;
    }
}
