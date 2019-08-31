/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationlife;

/**
 *
 * @author Kwabena
 */

import java.io.*;
import java.util.*;

public class readfile {
    
    private Scanner x;
    
    public void openFile(String nameOfFile){
        try{
            x = new Scanner(new File(nameOfFile+".txt"));
        }
        catch(Exception e){
            System.out.println("could not find the file my g");
        }
    }
    
    public String readFile(){
        String result="";
        while(x.hasNext()){
            String a = x.next();
            //String b = x.next();
            //String c = x.next();
            result += a+"\n";
            //System.out.println(a);
        }
        return result.trim();
    }
    
    public void closeFile(){
        x.close();
    }
}
