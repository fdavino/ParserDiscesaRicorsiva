package Main;

import Parser.TopDownParser;

public class main {

    public static void main(String[] argv){
        TopDownParser tdp = new TopDownParser("input.txt");
        tdp.parse();
    }
}
