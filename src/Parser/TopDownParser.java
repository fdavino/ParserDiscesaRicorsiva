package Parser;

import Lexer.Lexer;
import Utils.Token;

import java.util.ArrayList;

public class TopDownParser {

    private ArrayList<Token> status;
    private Lexer lex;
    private int globalPointer;


    public TopDownParser(String ss){
        status = new ArrayList<>();
        lex = new Lexer(ss);
        globalPointer = 0;
    }

    public void parse(){
        status.add(lex.getTokens());
        if(program())
            System.out.println("accettata");
        else
            System.out.println("non accettata");
    }

    private boolean program(){
        status.add(lex.getTokens());
        if(stmt()) {
            return B();
        }
        return false;
    }

    private boolean B(){
        status.add(lex.getTokens());
        if(status.get(globalPointer).getOp().equalsIgnoreCase(";")){
            globalPointer++;
            if(stmt()) {
                return B();
            }
            else
                return false;
        }
        return true;
    }

    private boolean stmt(){
        status.add(lex.getTokens());
        if(status.get(globalPointer).getOp().equalsIgnoreCase("if")){
            globalPointer++;
            if(expr()){
                if(status.get(globalPointer).getOp().equalsIgnoreCase("then")){
                    globalPointer++;
                    return stmt();
                }
            }
        }
        else if(status.get(globalPointer).getClasse().equalsIgnoreCase("id")){
            globalPointer++;
            if(status.get(globalPointer).getOp().equalsIgnoreCase("ass")) {
                globalPointer++;
                return (expr());
            }
        }

        return false;
    }

    private boolean expr(){
        status.add(lex.getTokens());
        if(term()){
            return A();
        }
        return false;
    }
    private boolean A(){
        status.add(lex.getTokens());
        if(status.get(globalPointer).getClasse().equalsIgnoreCase("relop")) {
            globalPointer++;
            return term();
        }
        return true;
    }
    private boolean term(){
        status.add(lex.getTokens());
        if(status.get(globalPointer).getClasse().equalsIgnoreCase("id")) {
            globalPointer++;
            return true;
        }
        else if (status.get(globalPointer).getClasse().equalsIgnoreCase("const")) {
            globalPointer++;
            return true;
        }
        else
            return false;
    }
}
