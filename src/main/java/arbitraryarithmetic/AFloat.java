package arbitraryarithmetic;

public class AFloat{
    public String value;
    public AFloat(){
        this.value="0.0";			//initializing
    }
    public AFloat(String value){
        this.value=value;
    }

    public AFloat(AFloat number){
        this.value=number.value;
    }
    public static AFloat parse(AFloat s){
        return new AFloat(s);
    }
    public  AFloat eraseLeadingZeroes(AFloat input) {
        String original = input.value;
        String cleaned = "";
    
        int dotPosition = -1;
    
        // Find position of decimal point
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == '.') {
                dotPosition = i;
                break;
            }
        }
    
        // If no decimal point whole string is integer
        if (dotPosition == -1) {
            dotPosition = original.length();
        }
    
        // Remove leading zeroes from integer part
        int firstNonZeroFound = 0;
        for (int i = 0; i < dotPosition; i++) {
            char ch = original.charAt(i);
            if (ch != '0' || (i == dotPosition - 1)) {
                firstNonZeroFound = 1;
            }
            if (firstNonZeroFound == 1) {
                cleaned += ch;
            }
        }
    
        // Add the decimal part back
        for (int i = dotPosition; i < original.length(); i++) {
            cleaned += original.charAt(i);
        }
    
        return new AFloat(cleaned);
    }
    public  AFloat eraseTrailingZeroes(AFloat input) {
        int point = -1;

        // Find the position of the decimal point using a for loop
        for (int i = 0; i < input.value.length(); i++) {
            if (input.value.charAt(i) == '.') {
                point = i;
                break; // Exit the loop once the decimal is found
            }
        }

        // If there's no decimal point, return the value as it is
        if (point == -1) {
            return input;
        }
        int allzero=0;
        // Find the last non-zero digit after the decimal point
        int lastNonZeroIndex = point;
        for (int i = input.value.length() - 1; i > point; i--) {
            if (input.value.charAt(i) != '0') {
                lastNonZeroIndex = i;
                break; // Stop when the last non-zero digit is found
            }
            if(i==point+1){
                allzero=1;
            }
        }

        // Get the cleaned value up to the last non-zero index
        String cleaned = input.value.substring(0, lastNonZeroIndex + 1);
        if (allzero==1){
            cleaned=cleaned+ "0";
        }

        return new AFloat(cleaned); // Return a new AFloat object with the cleaned value
    }
    public  int compare(AFloat A, AFloat B) {
        if(A.value.charAt(0)=='-') return -1;
        A = eraseLeadingZeroes(A);
        B = eraseLeadingZeroes(B);
        A= eraseTrailingZeroes(A);
        B= eraseTrailingZeroes(B);
        if (A.value.length() > B.value.length()) return 1;
        if (A.value.length() < B.value.length()) return -1;
        
        for (int i = 0; i < A.value.length(); i++) {
            if (A.value.charAt(i) > B.value.charAt(i)) return 1;
            if (A.value.charAt(i) < B.value.charAt(i)) return -1;
        }
        return 0;
    }
    public  int number_of_leading_zeroes(AFloat number){
        int count=0;
        for(int i=0;i<number.value.length();i++){
            if(number.value.charAt(i)=='0'){
                count++;
            } 
            else{
                break;
            }
        }
        return count;
    }




    public  AFloat add(AFloat AFloat1, AFloat AFloat2) {
        // Separate the integer and decimal parts
        if(AFloat1.value.charAt(0)!='-'&& AFloat2.value.charAt(0)!='-'){

        
        String intPart1 = "";
        String decPart1 = "";
        String intPart2 = "";
        String decPart2 = "";
    
        // Separate AFloat1
        int dotIndex1 = -1;
        for (int i = 0; i < AFloat1.value.length(); i++) {
            if (AFloat1.value.charAt(i) == '.') {
                dotIndex1 = i;
                break;
            }
        }
        if (dotIndex1 == -1) {
            intPart1 = AFloat1.value;
            decPart1 = "0";
        } else {
            for (int i = 0; i < dotIndex1; i++) {
                intPart1 += AFloat1.value.charAt(i);
            }
            for (int i = dotIndex1 + 1; i < AFloat1.value.length(); i++) {
                decPart1 += AFloat1.value.charAt(i);
            }
        }
    
        // Separate AFloat2
        int dotIndex2 = -1;
        for (int i = 0; i < AFloat2.value.length(); i++) {
            if (AFloat2.value.charAt(i) == '.') {
                dotIndex2 = i;
                break;
            }
        }
        if (dotIndex2 == -1) {
            intPart2 = AFloat2.value;
            decPart2 = "0";
        } else {
            for (int i = 0; i < dotIndex2; i++) {
                intPart2 += AFloat2.value.charAt(i);
            }
            for (int i = dotIndex2 + 1; i < AFloat2.value.length(); i++) {
                decPart2 += AFloat2.value.charAt(i);
            }
        }
    
        // Equalize decimal part lengths
        while (decPart1.length() < decPart2.length()) decPart1 += "0";
        while (decPart2.length() < decPart1.length()) decPart2 += "0";
    
        // Add decimal part
        String decimalSum = "";
        int carry = 0;
        for (int i = decPart1.length() - 1; i >= 0; i--) {
            int d1 = decPart1.charAt(i) - '0';
            int d2 = decPart2.charAt(i) - '0';
            int sum = d1 + d2 + carry;
            carry = sum / 10;
            decimalSum = (sum % 10) + decimalSum;
        }
    
        // Equalize integer part lengths
        while (intPart1.length() < intPart2.length()) intPart1 = "0" + intPart1;
        while (intPart2.length() < intPart1.length()) intPart2 = "0" + intPart2;
        
        //if decimal had a carry in the last step it remains and gets carried to integer in the first step
        // Add integer part with carry from decimal
        String integerSum = "";
        for (int i = intPart1.length() - 1; i >= 0; i--) {
            int d1 = intPart1.charAt(i) - '0';
            int d2 = intPart2.charAt(i) - '0';
            int sum = d1 + d2 + carry;
            carry = sum / 10;
            integerSum = (sum % 10) + integerSum;
        }
        if (carry > 0) integerSum = carry + integerSum;
    
        // Combine
        String result = integerSum + "." + decimalSum;
    
        AFloat resultFloat = new AFloat(result);
        resultFloat = eraseLeadingZeroes(resultFloat);
        resultFloat = eraseTrailingZeroes(resultFloat);
        return resultFloat;
    }
    else if(AFloat1.value.charAt(0)=='-'&& AFloat2.value.charAt(0)=='-'){
        AFloat first = new AFloat("");
        AFloat second = new AFloat("");
    for (int i = 1; i < AFloat1.value.length(); i++) {
        first.value = first.value + AFloat1.value.charAt(i);
    }
    for (int i = 1; i < AFloat2.value.length(); i++) {
        second.value = second.value + AFloat2.value.charAt(i);
    }
    AFloat solution = new AFloat("");
    solution = add(first, second);
    if(!solution.value.equals("0.0")){
    solution.value = "-" + solution.value;}
    return solution;
        }
    else if(AFloat1.value.charAt(0)!='-'&& AFloat2.value.charAt(0)=='-'){
        AFloat second = new AFloat("");
        for (int i = 1; i < AFloat2.value.length(); i++) {
            second.value = second.value + AFloat2.value.charAt(i);
        }
        AFloat solution = new AFloat("");
        solution = sub(AFloat1, second);
        return solution;
    }
    else{
        AFloat first = new AFloat("");
        for (int i = 1; i < AFloat1.value.length(); i++) {
            first.value = first.value + AFloat1.value.charAt(i);
        }
        AFloat solution = new AFloat("");
        solution = sub(AFloat2,first);
        
        return solution;
    }
}
    public  AFloat sub(AFloat AFloat1, AFloat AFloat2) {
        if(AFloat1.value.charAt(0)!='-'&& AFloat2.value.charAt(0)!='-'){
        String intPart1 = "", decPart1 = "";
        String intPart2 = "", decPart2 = "";
    
        // Extract parts from AFloat1
        int dotIndex1 = -1;
        for (int i = 0; i < AFloat1.value.length(); i++) {
            if (AFloat1.value.charAt(i) == '.') {
                dotIndex1 = i;
                break;
            }
        }
        if (dotIndex1 == -1) {
            intPart1 = AFloat1.value;
            decPart1 = "0";
        } else {
            for (int i = 0; i < dotIndex1; i++) intPart1 += AFloat1.value.charAt(i);
            for (int i = dotIndex1 + 1; i < AFloat1.value.length(); i++) decPart1 += AFloat1.value.charAt(i);
        }
    
        // Extract parts from AFloat2
        int dotIndex2 = -1;
        for (int i = 0; i < AFloat2.value.length(); i++) {
            if (AFloat2.value.charAt(i) == '.') {
                dotIndex2 = i;
                break;
            }
        }
        if (dotIndex2 == -1) {
            intPart2 = AFloat2.value;
            decPart2 = "0";
        } else {
            for (int i = 0; i < dotIndex2; i++) intPart2 += AFloat2.value.charAt(i);
            for (int i = dotIndex2 + 1; i < AFloat2.value.length(); i++) decPart2 += AFloat2.value.charAt(i);
        }
    
        // Equalize decimal lengths
        while (decPart1.length() < decPart2.length()) decPart1 += "0";
        while (decPart2.length() < decPart1.length()) decPart2 += "0";
    
        // Equalize integer lengths
        while (intPart1.length() < intPart2.length()) intPart1 = "0" + intPart1;
        while (intPart2.length() < intPart1.length()) intPart2 = "0" + intPart2;
    
        // Combine both into single strings for comparison
        String full1 = intPart1 + decPart1;
        String full2 = intPart2 + decPart2;
        int minus=0;
        // Swap if AFloat2 > AFloat1
        if (full2.compareTo(full1) > 0) {
            String tempInt = intPart1; intPart1 = intPart2; intPart2 = tempInt;
            String tempDec = decPart1; decPart1 = decPart2; decPart2 = tempDec;
            minus=1;
        }
    
        // Subtract decimal part
        String decimalDiff = "";
        int borrow = 0;
        for (int i = decPart1.length() - 1; i >= 0; i--) {
            int d1 = decPart1.charAt(i) - '0' - borrow;
            int d2 = decPart2.charAt(i) - '0';
            if (d1 < d2) {
                d1 += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            decimalDiff = Integer.toString(d1 - d2) + decimalDiff;
        }
        // here last borrow if it is there it is subtacted from int part
        // Subtract integer part
        String integerDiff = "";
        for (int i = intPart1.length() - 1; i >= 0; i--) {
            int d1 = intPart1.charAt(i) - '0' - borrow;
            int d2 = intPart2.charAt(i) - '0';
            if (d1 < d2) {
                d1 += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            integerDiff = Integer.toString(d1 - d2) + integerDiff;
        }
    
        // Combine result
        String result = integerDiff + "." + decimalDiff;
    
        AFloat resultFloat = new AFloat(result);
        resultFloat = eraseLeadingZeroes(resultFloat);
        resultFloat = eraseTrailingZeroes(resultFloat);
        
        if(minus==1){
            resultFloat.value="-"+resultFloat.value;
        }
        if (resultFloat.value.equals("-0.0")) {
            resultFloat.value = "0.0";
        }
        return resultFloat;
    }
    else if(AFloat1.value.charAt(0)=='-'&& AFloat2.value.charAt(0)=='-'){
        AFloat first = new AFloat("");
        AFloat second = new AFloat("");
    for (int i = 1; i < AFloat1.value.length(); i++) {
        first.value = first.value + AFloat1.value.charAt(i);
    }
    for (int i = 1; i < AFloat2.value.length(); i++) {
        second.value = second.value + AFloat2.value.charAt(i);
    }
    AFloat solution = new AFloat("");
    solution = sub(second, first);
    if (solution.value.equals("-0.0")) {
        solution.value = "0.0";
    }
    return solution;
    }
    else if(AFloat1.value.charAt(0)!='-'&& AFloat2.value.charAt(0)=='-'){
        AFloat second = new AFloat("");
        for (int i = 1; i < AFloat2.value.length(); i++) {
            second.value = second.value + AFloat2.value.charAt(i);
        }
        AFloat solution = new AFloat("");
        solution = add(second, AFloat1);
        if (solution.value.equals("-0.0")) {
            solution.value = "0.0";
        }
        return solution;
    }
    else{
        AFloat first = new AFloat("");
        for (int i = 1; i < AFloat1.value.length(); i++) {
            first.value = first.value + AFloat1.value.charAt(i);
        }
        AFloat solution = new AFloat("");
            solution=add(first, AFloat2);
            solution.value="-"+solution.value;

        if (solution.value.equals("-0.0")) {
            solution.value = "0.0";
        }
        return solution;
    }
}   
    
    public  int countDecimalDigits(AFloat number) {
        int decimal=0;
        int found_decimal=0;
        for(int i=0;i<number.value.length();i++){
            if(number.value.charAt(i)=='.'){
                found_decimal=1;
            }
            else if(found_decimal==1){
                decimal=decimal+1;
            }
        }
        return decimal;
    }

    public  AFloat removeDecimal(AFloat number) {
        AFloat result = new AFloat("");
    
        for (int i = 0; i < number.value.length(); i++) {
            char c = number.value.charAt(i);
            if (c != '.') {
                result.value=result.value+c;
            }
        }
    
        return result;
}
    public  AFloat mul(AFloat AFloat1,AFloat AFloat2){
        if(AFloat1.value.charAt(0)!='-'&& AFloat2.value.charAt(0)!='-'){
        AFloat num1 = eraseLeadingZeroes(AFloat1);
        AFloat num2 = eraseLeadingZeroes(AFloat2);

        num1 = eraseTrailingZeroes(num1);
        num2 = eraseTrailingZeroes(num2);

        // Get the count of digits after the decimal point for both numbers
        int decimalCount1 = countDecimalDigits(num1);
        int decimalCount2 = countDecimalDigits(num2);
        int totalDecimal=decimalCount1+decimalCount2;
        num1=removeDecimal(num1);
        num2=removeDecimal(num2);
        AFloat summation=new AFloat("0");
    for (int i = 0; i < num2.value.length(); i++) {
        AFloat temp_sum = new AFloat("");
        int second_digit = num2.value.charAt(num2.value.length() - 1 - i) - '0';
        int carry = 0;

        // mul each digit of num1 by current digit of num2
        for (int j = 0; j < num1.value.length(); j++) {
            int first_digit = num1.value.charAt(num1.value.length() - 1 - j) - '0';
            int product = first_digit * second_digit + carry;
            carry = product / 10;
            temp_sum.value = Integer.toString(product % 10) + temp_sum.value;
        }

        if (carry > 0) {
            temp_sum.value = Integer.toString(carry) + temp_sum.value;
        }

        // Add zeros for the current row
        for (int z = 0; z < i; z++) {
            temp_sum.value += "0";
        }

        summation = add(summation, temp_sum); // Reuse your add method
    }
    AFloat summation_without_decimal=new AFloat("");
    for(int i=0;i<summation.value.length()-2;i++){
        summation_without_decimal.value=summation_without_decimal.value+summation.value.charAt(i);
    }
    AFloat decimal_put=new AFloat("");
    if(summation_without_decimal.value.length()>totalDecimal){
        for(int i=0;i<summation_without_decimal.value.length();i++){
            decimal_put.value=decimal_put.value+summation_without_decimal.value.charAt(i);
            if(i==summation_without_decimal.value.length()-1-totalDecimal&&totalDecimal!=0){
                decimal_put.value=decimal_put.value+".";
            }
            else if(i==summation_without_decimal.value.length()-1-totalDecimal&&totalDecimal==0){
                decimal_put.value=decimal_put.value+".0";
            }
        }
    }
    else{
        decimal_put.value="0.";
        for(int i=0;i<totalDecimal-summation_without_decimal.value.length();i++){
            decimal_put.value+="0";
        }
        for(int i=0;i<summation_without_decimal.value.length();i++){
            decimal_put.value+=summation_without_decimal.value.charAt(i);
        }
    }
    decimal_put=eraseLeadingZeroes(decimal_put);
    decimal_put=eraseTrailingZeroes(decimal_put);
    return decimal_put;
        }
        else if(AFloat1.value.charAt(0)=='-'&& AFloat2.value.charAt(0)=='-'){
            AFloat first = new AFloat("");
            AFloat second = new AFloat("");
        for (int i = 1; i < AFloat1.value.length(); i++) {
            first.value = first.value + AFloat1.value.charAt(i);
        }
        for (int i = 1; i < AFloat2.value.length(); i++) {
            second.value = second.value + AFloat2.value.charAt(i);
        }
        AFloat solution = new AFloat("");
        solution = mul(first, second);
        return solution;
        }
        else if(AFloat1.value.charAt(0)!='-'&& AFloat2.value.charAt(0)=='-'){
            AFloat second = new AFloat("");
            for (int i = 1; i < AFloat2.value.length(); i++) {
                second.value = second.value + AFloat2.value.charAt(i);
            }
            AFloat solution = new AFloat("");
            solution = mul(second, AFloat1);
            if(solution.value.equals("0.0")){
                    solution.value="0.0";
                }
            else{
                solution.value="-"+solution.value;
            }
            return solution;
        }
        else{
            AFloat first = new AFloat("");
            for (int i = 1; i < AFloat1.value.length(); i++) {
                first.value = first.value + AFloat1.value.charAt(i);
            }
            AFloat solution = new AFloat("");
                solution=mul(first, AFloat2);
                if (!solution.value.equals("0.0")){
                    solution.value="-"+solution.value;
                }

            return solution;
        }
        
    }

    public  AFloat div(AFloat AFloat1, AFloat AFloat2) {
        if(AFloat1.value.charAt(0)!='-'&& AFloat2.value.charAt(0)!='-'){
        AFloat dividend = eraseLeadingZeroes((AFloat1));
        AFloat divisor = eraseLeadingZeroes(AFloat2);

        dividend = eraseTrailingZeroes(dividend);
        divisor = eraseTrailingZeroes(divisor);
        // Check for div by zero
        
        if (divisor.value.equals("0.0")|| divisor.value.equals("0")) {
            throw new ArithmeticException("div by zero is not allowed.");
        }
        if (dividend.value.equals("0.0")|| divisor.value.equals("0")) {
            return new AFloat("0.0");
        }



        // Get the count of digits after the decimal point for both numbers
        int decimalCount1 = countDecimalDigits(dividend);
        int decimalCount2 = countDecimalDigits(divisor);
        dividend=removeDecimal(dividend);
        divisor=removeDecimal(divisor);
        //decimalCount1+=number_of_leading_zeroes(dividend)-1;
        //decimalCount2+=number_of_leading_zeroes(divisor)-1;
        int totalDecimal=decimalCount1-decimalCount2;
        dividend=eraseLeadingZeroes(dividend);
        divisor=eraseLeadingZeroes(divisor);

        AFloat result = new AFloat("");
        AFloat current_dividend = new AFloat("");
        AFloat current_dividend_without_decimal=new AFloat("");
        for (int i = 0; i < dividend.value.length(); i++) {
            current_dividend.value += dividend.value.charAt(i);
            current_dividend = eraseLeadingZeroes(current_dividend);
    
            int count = 0;
            while (compare(current_dividend, divisor) >= 0) {
                current_dividend =sub(current_dividend, divisor);
                current_dividend_without_decimal.value="";
                current_dividend.value=current_dividend.value.substring(0,current_dividend.value.length()-2);
                count++;  
            }
            //System.out.println("current_dividend: \n" + current_dividend.value+"\ncount:"+count);   

            result.value += Integer.toString(count); 
            //System.out.println("\nwhile looprunning for "+ (i+1)+"th digit");
        }
        //printd dividend
        int remaining_decimal_places=0;
        if(totalDecimal<=0){
            remaining_decimal_places=30-totalDecimal;
        }
        else{
            if(result.value.length()<=totalDecimal){
                remaining_decimal_places=30-totalDecimal;
        }
            else{
                remaining_decimal_places=30-(result.value.length()-totalDecimal);
            }
        
        }
        int terminate=0;
        AFloat decimal=new AFloat("");
        decimal.value=current_dividend.value;
        AFloat decimal_part=new AFloat("");
        for(int i=0;i<remaining_decimal_places;i++){
            if(decimal.value.equals("0")){
                terminate=1;
            }
            decimal.value+="0";
            int count=0;
            while (compare(decimal, divisor) >= 0){
                decimal=sub(decimal, divisor);
                decimal.value=decimal.value.substring(0,decimal.value.length()-2);
                decimal=eraseLeadingZeroes(decimal);
                count++;
                if(decimal.value.equals("0")){
                    terminate=1;
                }
            }
            decimal_part.value+=Integer.toString(count);
            if(terminate==1){
                break;
            }
        }
        result=eraseLeadingZeroes(result);
        
        AFloat finalresult=new AFloat("");
        if(totalDecimal<=0){
            for(int i=0;i<result.value.length();i++){
                finalresult.value+=result.value.charAt(i);
            }
            if((-1)*totalDecimal<=decimal_part.value.length()){
                
            for(int i=0;i<(-1*totalDecimal);i++){
                finalresult.value+=decimal_part.value.charAt(i);
            }
            finalresult.value+=".";
            for(int i=(-1*totalDecimal);i<decimal_part.value.length();i++){
                finalresult.value+=decimal_part.value.charAt(i);
            }
        }
        else{
            int a=decimal_part.value.length();
            for(int i=0;i<(1-totalDecimal)-a;i++){
                decimal_part.value+="0";
            }

            for(int i=0;i<(-1*totalDecimal);i++){
                finalresult.value+=decimal_part.value.charAt(i);
            }
            finalresult.value+=".";
            for(int i=(-1*totalDecimal);i<decimal_part.value.length();i++){
                finalresult.value+=decimal_part.value.charAt(i);
            }

        }
        }
        else{
            if(result.value.length()<=totalDecimal){
                finalresult.value="0.";
                for(int i=0;i<totalDecimal-result.value.length();i++){
                    finalresult.value+="0";
                }
                for(int i=0;i<result.value.length();i++){
                finalresult.value+=result.value.charAt(i);
                }
                for(int i=0;i<decimal_part.value.length();i++){
                finalresult.value+=decimal_part.value.charAt(i);
                }
            }
            else{
                for(int i=0;i<result.value.length();i++){
                   finalresult.value+=result.value.charAt(i);
                    if(i==result.value.length()-(totalDecimal+1)){
                    finalresult.value+=".";
                    }
                }
                for(int i=0;i<decimal_part.value.length();i++){
                    finalresult.value+=decimal_part.value.charAt(i);
                }
            }  
    }
    finalresult=eraseLeadingZeroes(finalresult);
    //if last value is dot put 0 at end
    if(finalresult.value.charAt(finalresult.value.length()-1)=='.'){
        finalresult.value+="0";
    }
    if(finalresult.value.charAt(0)=='.'){
        finalresult.value="0"+finalresult.value;
    }
    if(decimal_part.value.equals("0")){
        finalresult=finalresult.eraseTrailingZeroes(finalresult);
    }
        return  finalresult;//new AFloat(Integer.toString(totalDecimal));
    }
    else if(AFloat1.value.charAt(0)=='-'&& AFloat2.value.charAt(0)=='-'){
        AFloat first = new AFloat("");
        AFloat second = new AFloat("");
        for (int i = 1; i < AFloat1.value.length(); i++) {
            first.value = first.value + AFloat1.value.charAt(i);
        }
        for (int i = 1; i < AFloat2.value.length(); i++) {
            second.value = second.value + AFloat2.value.charAt(i);
        }
        AFloat solution = new AFloat("");
        solution = div(first, second);
        return solution;
    }
    //one plus other minus
    else if(AFloat1.value.charAt(0)!='-'&& AFloat2.value.charAt(0)=='-'){
        AFloat second = new AFloat("");
        for (int i = 1; i < AFloat2.value.length(); i++) {
            second.value = second.value + AFloat2.value.charAt(i);
        }
        AFloat solution = new AFloat("");
        solution = div(AFloat1, second);
        if(solution.value.equals("0.0")){
            solution.value="0.0";
        }
        else{
            solution.value="-"+solution.value;
        }
        return solution;
    }
    else{
        AFloat first = new AFloat("");
        for (int i = 1; i < AFloat1.value.length(); i++) {
            first.value = first.value + AFloat1.value.charAt(i);
        }
        AFloat solution = new AFloat("");
            solution=div(first, AFloat2);
            if (!solution.value.equals("0.0")){
                solution.value="-"+solution.value;
            }

        return solution;
        }
    }     


    // public  void main(String[] args) {
    //     System.out.println(AFloat.div(new AFloat("0.69"), new AFloat("0.69")).value);     

    // }
}
