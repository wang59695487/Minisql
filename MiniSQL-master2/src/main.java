import API.*;
import GUI.*;

public class main {
    public static void main(String []args){
        //GUI.StringPrintText("Welcome to MiniSql.Please enter the command");

        try{
            API.Initialize();
            GUI.Initialize();
        }
        catch(Exception e){
            GUI.StringPrintText("Interpreter error:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
