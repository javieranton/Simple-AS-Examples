package Classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


/**
 * Created by Javier on 31/01/2016.
 * This class I've copied from http://codereview.stackexchange.com/questions/83219/order-of-operations-algorithm-for-calculator
 * but is changed and optimized by me seing some things like division by zero.
 */
public class Operation {
    ArrayList<String> contents;
    String item;
    Operation check;

    /*public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an operation: ");
        String a = input.nextLine();
        Operation go = new Operation();
        a = go.solution(a);
        System.out.println("Result: "+a);
    } */
    public String solution(String s){             //method which deal with solution separately
        check = new Operation();
        while(s.contains(Character.toString('('))||s.contains(Character.toString(')'))){
            for(int i=0; i<s.length();i++){
                try{                                                        //i there is not sign
                    if((s.charAt(i)==')' || Character.isDigit(s.charAt(i))) //between separate solution
                            && s.charAt(i+1)=='('){                         //or number and bracket,
                        s=s.substring(0,i+1)+"x"+(s.substring(i+1));        //it treat it as
                    }                                                       //a multiplication
                }catch (Exception ignored){}                                //ignore out of range ex
                if(s.charAt(i)==')'){                                  //search for a closing bracket
                    for(int k=i; k>=0;k--){
                        if(s.charAt(k)=='('){                          //search for a opening bracket
                            String in = s.substring(k+1,i);
                            in = check.recognize(in);
                            s=s.substring(0,k)+in+s.substring(i+1);
                            k=i=0;
                        }
                    }
                }
            }
            if( s.contains(Character.toString('(')) || s.contains(Character.toString(')')) ){
                System.out.println("Brackets error");
                return "Brackets error";
            }
        }
        s=check.recognize(s);
        return s;
    }

    public String recognize(String s){              //method divide String on numbers and operators
        contents = new ArrayList<String>();         //holds numbers and operators
        item = "";
        for(int i=s.length()-1;i>=0;i--){           //is scan String from right to left,
            if(Character.isDigit(s.charAt(i))){     //Strings are added to list, if scan finds
                item=s.charAt(i)+item;              //a operator, or beginning of String
                if(i==0){
                    put();
                }
            }else{
                if(s.charAt(i)=='.'){
                    item=s.charAt(i)+item;
                }else if(s.charAt(i)=='-' && (i==0 || (!Character.isDigit(s.charAt(i-1))))){
                    item=s.charAt(i)+item;          //this part should recognize
                    put();                    //negative numbers
                }else{
                    put();                //it add already formed number and
                    item+=s.charAt(i);          //operators to list
                    put();                //as separate Strings
                    if(s.charAt(i)=='|'){       //add empty String to list, before "|" sign,
                        item+=" ";          //to avoid removing of any meaningful String
                        put();        //in last part of result method
                    }
                }
            }
        }
        contents = result(contents, "^", "|");    //check Strings UNUSED
        contents = result(contents, "x", "/");    //for chosen
        contents = result(contents, "+", "-");    //operators
        return contents.get(0);
    }

    public void put(){
        if(!item.equals("")){
            contents.add(0,item);
            item="";
        }
    }
    public ArrayList<String> result(ArrayList<String> arrayList, String op1, String op2){
        int scale = 10;                              //controls BigDecimal decimal point accuracy
        BigDecimal result = new BigDecimal(0);
        String error = "";
        for(int k = 0; k<arrayList.size();k++){
            String simbol = arrayList.get(k);
            if(simbol.equals(op1) || simbol.equals(op2)){
                switch (simbol){
                    case "^":
                        result = new BigDecimal(arrayList.get(k-1)).pow(Integer.parseInt(arrayList.get(k+1)));
                        break;
                    case "|":
                        result = new BigDecimal(Math.sqrt(Double.parseDouble(arrayList.get(k+1))));
                        break;
                    case "x":
                        result = new BigDecimal(arrayList.get(k-1)).multiply
                            (new BigDecimal(arrayList.get(k+1)));
                        break;
                    case "/":
                        try{
                            result = new BigDecimal(arrayList.get(k-1)).divide
                                    (new BigDecimal(arrayList.get(k+1)),scale,BigDecimal.ROUND_DOWN);
                        }catch(ArithmeticException e){
                             error = "Zero Error!";
                        }
                        break;
                    case "+":
                        result = new BigDecimal(arrayList.get(k-1)).add(new BigDecimal(arrayList.get(k+1)));
                        break;
                    case "-":
                        result = new BigDecimal(arrayList.get(k-1)).subtract(new BigDecimal(arrayList.get(k+1)));
                        break;
                }
                try{       //in a case of to "out of range" ex
                    if(!error.equals("")){
                        arrayList.set(k, error);
                    }else {
                        arrayList.set(k, (result.setScale(scale, RoundingMode.HALF_DOWN).
                                stripTrailingZeros().toPlainString()));
                    }
                    arrayList.remove(k + 1);            //it replace the operator with result
                    arrayList.remove(k - 1);              //and remove used numbers from list
                }catch (Exception ignored){}
            }else{
                continue;
            }
            k=0;                     //loop reset, as arrayList changed size
        }
        return arrayList;
    }
}

