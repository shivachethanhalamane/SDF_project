package arbitraryarithmetic;

public class AInteger{
    public String value;
    public AInteger(){
        this.value="0";
    }
    public AInteger(String value){
        this.value=value;
    }

    public AInteger(AInteger number){
        this.value=number.value;
    }
    public  static AInteger parse(AInteger s){
        return new AInteger(s);
    }

    public  AInteger erase_leading_zeroes(AInteger AInteger1){
        AInteger A1=new AInteger("");
        int first_non_zero_digit_not_detected=1;
    for(int i=0;i<AInteger1.value.length();i++){
        if(AInteger1.value.charAt(i)=='0'&& first_non_zero_digit_not_detected==1&&i!=AInteger1.value.length()-1){
            continue;
        }
        else{
            first_non_zero_digit_not_detected=0;
            A1.value=A1.value+AInteger1.value.charAt(i);
        }
    
    
    }        
    return A1;                 
    }
    public  int compare(AInteger A, AInteger B) {
        A = erase_leading_zeroes(A);
        B = erase_leading_zeroes(B);
        
        if (A.value.length() > B.value.length()) return 1;
        if (A.value.length() < B.value.length()) return -1;
        
        for (int i = 0; i < A.value.length(); i++) {
            if (A.value.charAt(i) > B.value.charAt(i)) return 1;
            if (A.value.charAt(i) < B.value.charAt(i)) return -1;
        }
        return 0;
    }
    



    public  AInteger add(AInteger AInteger1,AInteger AInteger2){
        if(AInteger1.value.charAt(0)!='-'&&AInteger2.value.charAt(0)!='-'){
            while (AInteger1.value.length() < AInteger2.value.length()) AInteger1.value = "0" + AInteger1.value;    
            while (AInteger2.value.length() < AInteger1.value.length()) AInteger2.value = "0" + AInteger2.value;     //equalizing lengths
    
            AInteger answer = new AInteger("");//empty
            int carry = 0;
    
            // Adding digit by digit from right to left, taking care of carrying.
            for (int i = AInteger1.value.length() - 1; i >= 0; i--) {
                int first_digit =  AInteger1.value.charAt(i) - '0';
                int second_digit = AInteger2.value.charAt(i) - '0';
                int sum = first_digit + second_digit + carry;
    
                carry = sum / 10;
                answer.value=Integer.toString(sum % 10)+answer.value;
            }
    
            //carry for last step.
            if(carry > 0) {
                answer.value=Integer.toString(carry)+answer.value;
            }   

            AInteger removed_leading_zero = new AInteger("");
            int first_non_zero_digit_not_detected=1;

            for(int i=0;i<answer.value.length();i++){
                if(answer.value.charAt(i)=='0'&&first_non_zero_digit_not_detected==1&&i!=answer.value.length()-1){
                    continue;
                }
                else{
                    first_non_zero_digit_not_detected=0;
                    removed_leading_zero.value=removed_leading_zero.value+answer.value.charAt(i);
                }
            }
            
            
            return removed_leading_zero;
        }
        else if(AInteger1.value.charAt(0)=='-'&&AInteger2.value.charAt(0)=='-'){
            AInteger first=new AInteger("");
            AInteger second=new AInteger("");
            for(int i=1;i<AInteger1.value.length();i++){
                first.value=first.value+AInteger1.value.charAt(i);
            }
            for(int i=1;i<AInteger2.value.length();i++){
                second.value=second.value+AInteger2.value.charAt(i);
            }
            AInteger solution=new AInteger("");
            solution=add(first, second);
            solution.value="-"+solution.value;
            return solution;
        }
        else if(AInteger1.value.charAt(0)!='-'&&AInteger2.value.charAt(0)=='-'){
            AInteger second=new AInteger("");
            for(int i=1;i<AInteger2.value.length();i++){
                second.value=second.value+AInteger2.value.charAt(i);
            }
            AInteger solution=new AInteger("");
            solution=sub(AInteger1, second);
            return solution;
        }
        else{
            AInteger first=new AInteger("");
            for(int i=1;i<AInteger1.value.length();i++){
                first.value=first.value+AInteger1.value.charAt(i);
            }
            AInteger solution=new AInteger("");
            solution=sub(AInteger2,first);
            return solution;
        }
    }
public  AInteger sub(AInteger AInteger1,AInteger AInteger2){
    if(AInteger1.value.charAt(0)!='-'&&AInteger2.value.charAt(0)!='-'){
    while (AInteger1.value.length() < AInteger2.value.length()) AInteger1.value = "0" + AInteger1.value;    
    while (AInteger2.value.length() < AInteger1.value.length()) AInteger2.value = "0" + AInteger2.value; 
    int greater=1; //which is bigger 1 or 2
    for(int i=0;i<AInteger1.value.length();i++){
        if((AInteger1.value.charAt(i)-'0')>(AInteger2.value.charAt(i)-'0')){
            greater=1;
            break;
        }
        else if((AInteger1.value.charAt(i)-'0')<(AInteger2.value.charAt(i)-'0')){
            greater=2;
            break;
        }//if both are equal it is fine if I take first as greater
    }
    if(greater==2){
        AInteger temp=AInteger2;
        AInteger2=AInteger1;
        AInteger1=temp;
    }
    AInteger answer = new AInteger("");
    int borrow=0;
    for(int i=0;i<AInteger1.value.length();i++){
        int first_digit =  AInteger1.value.charAt(AInteger1.value.length()-1-i) - '0';
        int second_digit = AInteger2.value.charAt(AInteger1.value.length()-1-i) - '0';
        int difference=first_digit-second_digit-borrow;
        if(difference<0){
            borrow=1;
            difference=difference+10;
        }
        else{
            borrow=0;
        }
        answer.value=Integer.toString(difference)+answer.value;
    }
    AInteger removed_leading_zero = new AInteger("");
            int first_non_zero_digit_not_detected=1;

            for(int i=0;i<answer.value.length();i++){
                if(answer.value.charAt(i)=='0'&&first_non_zero_digit_not_detected==1&&i!=answer.value.length()-1){
                    continue;
                }
                else{
                    first_non_zero_digit_not_detected=0;
                    removed_leading_zero.value=removed_leading_zero.value+answer.value.charAt(i);
                }
            }
            if(greater==2){
                removed_leading_zero.value="-"+removed_leading_zero.value;
            }
        return removed_leading_zero;    
    }
    else if(AInteger2.value.charAt(0)=='-'){
        AInteger second=new AInteger("");
            for(int i=1;i<AInteger2.value.length();i++){
                second.value=second.value+AInteger2.value.charAt(i);
            }
            AInteger solution=new AInteger("");   
            solution=add(AInteger1, second);
            return solution;
    }
    else{
        AInteger second=new AInteger("");
        for(int i=0;i<AInteger2.value.length();i++){
            second.value=second.value+AInteger2.value.charAt(i);
        }
        AInteger solution=new AInteger("");  
        second.value="-"+second.value; 
        solution=add(AInteger1, second); 
        return solution;
    }
}
public  AInteger mul(AInteger AInteger1,AInteger AInteger2){
    if(AInteger1.value.charAt(0)!='-'&&AInteger2.value.charAt(0)!='-'){
    AInteger summation=new AInteger("0");
    AInteger A1=new AInteger("");
    AInteger A2=new AInteger("");
    int first_non_zero_digit_not_detected=1;
    for(int i=0;i<AInteger1.value.length();i++){
        if(AInteger1.value.charAt(i)=='0'&&first_non_zero_digit_not_detected==1 &&i!=AInteger1.value.length()-1){
            continue;
        }
        else{
            first_non_zero_digit_not_detected=0;
            A1.value=A1.value+AInteger1.value.charAt(i);
        }
    }
    int second_non_zero_digit_not_detected=1;
    for(int i=0;i<AInteger2.value.length();i++){
        if(AInteger2.value.charAt(i)=='0'&& second_non_zero_digit_not_detected==1&&i!=AInteger2.value.length()-1){
            continue;
        }
        else{
            second_non_zero_digit_not_detected=0;
            A2.value=A2.value+AInteger2.value.charAt(i);
        }
    }
    for (int i = 0; i < A2.value.length(); i++) {
        AInteger temp_sum = new AInteger("");
        int second_digit = A2.value.charAt(A2.value.length() - 1 - i) - '0';
        int carry = 0;

        // mul each digit of A1 by current digit of A2
        for (int j = 0; j < A1.value.length(); j++) {
            int first_digit = A1.value.charAt(A1.value.length() - 1 - j) - '0';
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
    return summation;
}
else if(AInteger1.value.charAt(0)=='-'&&AInteger2.value.charAt(0)=='-'){
    AInteger first=new AInteger("");
    AInteger second=new AInteger("");
        for(int i=1;i<AInteger1.value.length();i++){
            first.value=first.value+AInteger1.value.charAt(i);
        }
        for(int i=1;i<AInteger2.value.length();i++){
            second.value=second.value+AInteger2.value.charAt(i);
        }
        AInteger solution=new AInteger("");
        solution=mul(first, second);
        return solution;
}

else if(AInteger1.value.charAt(0)!='-'&&AInteger2.value.charAt(0)=='-'){
    AInteger second=new AInteger("");
    for(int i=1;i<AInteger2.value.length();i++){
        second.value=second.value+AInteger2.value.charAt(i);
    }
    AInteger solution=new AInteger("");
    solution=mul(AInteger1, second);
    if(!solution.value.equals("0")){
        solution.value="-"+solution.value;}
    return solution;
}
else{
    AInteger first=new AInteger("");
    for(int i=1;i<AInteger1.value.length();i++){
        first.value=first.value+AInteger1.value.charAt(i);
    }
    AInteger solution=new AInteger("");
    solution=mul(first,AInteger2);
    if(!solution.value.equals("0")){
        solution.value="-"+solution.value;}
    return solution;
}

}


    public  AInteger div(AInteger dividend, AInteger divisor) {
        if((dividend.value.charAt(0)!='-'&&divisor.value.charAt(0)!='-')){
        dividend = erase_leading_zeroes(dividend);
        divisor = erase_leading_zeroes(divisor);
    
        if (divisor.value.equals("0")) {
            throw new ArithmeticException("div by zero is not allowed.");
        }
    
    
        AInteger result = new AInteger("");
        AInteger current_dividend = new AInteger("");
    
        for (int i = 0; i < dividend.value.length(); i++) {
            current_dividend.value += dividend.value.charAt(i);
            current_dividend = erase_leading_zeroes(current_dividend);
    
            int count = 0;
            while (compare(current_dividend, divisor) >= 0) {
                current_dividend = sub(current_dividend, divisor);
                count++;
            }
            result.value += Integer.toString(count);
        }
    
        return erase_leading_zeroes(result);
    }
    else if((dividend.value.charAt(0)=='-'&&divisor.value.charAt(0)=='-')){
        AInteger first=new AInteger("");
        AInteger second=new AInteger("");
            for(int i=1;i<dividend.value.length();i++){
                first.value=first.value+dividend.value.charAt(i);
            }
            for(int i=1;i<divisor.value.length();i++){
                second.value=second.value+divisor.value.charAt(i);
            }
            AInteger solution=new AInteger("");
            solution=div(first, second);
            return solution;
    }
    else if((dividend.value.charAt(0)=='-'&&divisor.value.charAt(0)!='-')){
        AInteger first=new AInteger("");
        for(int i=1;i<dividend.value.length();i++){
            first.value=first.value+dividend.value.charAt(i);
        }
        AInteger solution=new AInteger("");
        solution=div(first, divisor);
        if(!solution.value.equals("0")){
            solution.value="-"+solution.value;}
        return solution;
    }
    else{
        AInteger second=new AInteger("");
        for(int i=1;i<divisor.value.length();i++){
            second.value=second.value+divisor.value.charAt(i);
        }
        AInteger solution=new AInteger("");
        solution=div(dividend, second);
        if(!solution.value.equals("0")){
            solution.value="-"+solution.value;}
        return solution;
    }
    
}
    

    // public  void main(String[] args) {
    //     System.out.println(AInteger.add(new AInteger("123"), new AInteger("456")).value); // 579
    //     System.out.println(AInteger.sub(new AInteger("123"), new AInteger("-124")).value); // -333
    // }
}