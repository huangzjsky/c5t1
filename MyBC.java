import java.util.StringTokenizer;
import java.util.Stack;
import java.util.Scanner;

public class MyBC {
    private Stack<Character> stackDC;
    private Stack<Character> stackOP;

    public MyBC() {
        stackDC = new Stack<Character>();
        stackOP = new Stack<Character>();
    }

    public void change (String expr)
    {
        char a;
        String token;
        StringTokenizer tokenizer = new StringTokenizer (expr);

        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();

            //如果是运算符，调用isOperator
            if (isOperator(token)) {
                if ( isFinal(token)) {
                    a = stackOP.pop();
                    while (a != '(')  {
                        stackDC.push(a);
                        a = stackOP.pop();
                    }
                }
                else if (stackOP.empty() || isStart(token) || operatorComp(stackOP.peek() , token.charAt(0))) stackOP.push(token.charAt(0));
                else {
                    stackDC.push(stackOP.pop());
                    stackOP.push(token.charAt(0));
                }
            }
            else{  //如果是操作数
                    stackDC.push(token.charAt(0));

            }
        }

        while (!stackOP.empty())  stackDC.push(stackOP.pop());

        System.out.println(stackDC);
    }


    private boolean isOperator (String token)
    {
        return ( token.equals("+") || token.equals("-") ||
                token.equals("*") || token.equals("/")  ||
                token.equals("(") || token.equals(")") );
    }

    private boolean isStart (String token)
    {
        return ( token.equals("(")  );
    }

    private boolean isFinal (String token)
    {
        return ( token.equals(")")  );
    }

    private boolean operatorComp (char token1 , char token2)
    {
        return ( ((token2 == '*' || token2 == '/' )
            && (token1 == '+' || token2 == '-'))
            || (token1 == '(')) ;
    }

    public static void main(String args[]) {
        MyBC BC = new MyBC();
        Scanner scan = new Scanner(System.in);
        BC.change(scan.nextLine());

    }
}
