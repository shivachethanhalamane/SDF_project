package myinfarith;

import arbitraryarithmetic.*;

public class MyInfArith {
    public static void main( String[] args){
        if(args.length != 4){
            System.out.println("Usage: java MyInfArith <int/float> <add/sub/mul/div> <number1> <number2>");
            return;
        }
        String type = args[0];
        String operation = args[1];
        String num1 = args[2];
        String num2 = args[3];
        if(type.equals("int")){
            AInteger a1 = new AInteger(num1);
            AInteger a2 = new AInteger(num2);
            switch(operation){
                case "add":
                    System.out.println(a1.add(a1, a2).value);
                    break;
                case "sub":         
                    System.out.println(a1.sub(a1, a2).value);
                    break;
                case "mul":             
                    System.out.println(a1.mul(a1, a2).value);
                    break;
                case "div":
                    System.out.println(a1.div(a1, a2).value);
                    break;
                default:
                    System.out.println("Invalid operation. Use add, sub, mul, or div.");
                    break;
                }
        }
        else if(type.equals("float")){
            AFloat a1 = new AFloat(num1);
            AFloat a2 = new AFloat(num2);
            switch(operation){
                case "add":
                    System.out.println(a1.add(a1, a2).value);
                    break;
                case "sub":         
                    System.out.println(a1.sub(a1, a2).value);
                    break;
                case "mul":             
                    System.out.println(a1.mul(a1, a2).value);
                    break;
                case "div":
                    System.out.println(a1.div(a1, a2).value);
                    break;
                default:
                    System.out.println("Invalid operation. Use add, sub, mul, or div.");
                    break;
                }
        }
        else{
            System.out.println("Invalid type. Use AInteger or AFloat.");
        }

    }
    //end of project
}
