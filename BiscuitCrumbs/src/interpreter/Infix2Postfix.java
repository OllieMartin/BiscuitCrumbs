package interpreter;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author ACHCHUTHAN
 */
@SuppressWarnings("serial")
public class Infix2Postfix extends Stack<AST> {

    public Infix2Postfix() {
    }

    /**
     *@return postfixString value
     */
   
    public ArrayList<AST> InToPost(ArrayList<AST> inFix) {
        ArrayList<AST> postFix = new ArrayList<AST>();

        for (int index = 0; index < inFix.size(); ++index) {
            String chValue = inFix.get(index).getType();
            if (chValue.equals("LPAREN")) {
                push(inFix.get(index));
            } else if (chValue.equals("RPAREN")) {
                AST oper = peek();
                while (!(oper.getType().equals("LPAREN")) && !(isEmpty())) {
                    postFix.add(oper);
                    pop();
                    oper = peek();
                }
                pop();
            } else if (chValue.equals("PLUS") || chValue.equals("MINUS")) {
                //Stack is empty
                if (isEmpty()) {
                    push(inFix.get(index));
                    //current Stack is not empty
                } else {
                    AST oper = peek();
                    while (!(isEmpty() || oper.getType().equals("LPAREN") || oper.getType().equals("RPAREN"))) {
                        pop();
                        postFix.add(oper);
                    }
                    push(inFix.get(index));
                }
            } else if (chValue.equals("MULTIPLY") || chValue.equals("DIVIDE")) {
                if (isEmpty()) {
                    push(inFix.get(index));
                } else {
                    AST oper = peek();
                    while (!oper.getType().equals("PLUS") && !oper.getType().equals("MINUS") && !isEmpty()) {
                        pop();
                       postFix.add( oper);
                    }
                    push(inFix.get(index));
                }
            } else {
                postFix.add(inFix.get(index));
            }
        }
        while (!isEmpty()) {
            AST oper = peek();
            if (!oper.getType().equals("LPAREN")) {
                pop();
               postFix.add( oper);
            }
        }
        return postFix;
    }
}