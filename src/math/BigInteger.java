package math;
public class BigInteger {
	boolean negative;
	int numDigits;
	DigitNode front;
	
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}
	
	
	public static BigInteger parse(String integer) 
			throws IllegalArgumentException {
	        BigInteger val = new BigInteger();
	        int t = 0;
	        char firstChar = integer.charAt(t);
	        		if (firstChar != '-' && firstChar != '+' && !Character.isDigit(firstChar) && firstChar != ' ')
	        {
	            throw new IllegalArgumentException("Incorrect Format");
	        }
	        	
	        		
	        		String remain = integer;
	        		remain = integer.trim();
	        		
	        		if(remain.charAt(0) == '-') {
	        			val.negative = true;
	        			remain = remain.substring(1);
				}else {
	        			val.negative = false;
	        			if (remain.charAt(0) == '+') {
	        				remain = remain.substring(1).trim();
	        			}
	        		}

	        for (int i = 0; i < remain.length(); i++)
	        {
	        	
	        		if (remain.charAt(i) == '0')
	            {
	        			remain = remain.substring(i);
	                continue;
	            }
	            else 
	        		{
	            		remain = remain.substring(0);
	                break;
	                
	            }
	            
	            
	        }
	        
	        for (int i = 0; i < remain.length(); i++)
	        {
	            char ch = remain.charAt(i);
	            if (!Character.isDigit(ch))
	            {
	                throw new IllegalArgumentException("Incorrect Format");
	            }
	            
	            int element = Integer.parseInt(String.valueOf(ch));
	            val.addToFront(element);
	        }
	        return val;
	    }
	    
	    private void addToFront(int i)
	    {
	        DigitNode newNode = new DigitNode(i, front);
	        front = newNode;
	        
	    }
	    
	    
	public BigInteger add(BigInteger other) {
		DigitNode number = this.front;
		DigitNode second = other.front;
		
        DigitNode result = null;
        DigitNode prev = null;
        DigitNode temp = null;
        
        int carry = 0, sum;
        
        if (this.numDigits != other.numDigits) {
        	{
                
                while (number != null || other != null)
                {
                    int x, y;
                    if (number != null)
                    {
                        x = number.digit;
                    }
                    else
                    {
                        x = 0;
                    }
                    
                    if (second != null)
                    {
                        y = second.digit;
                    }
                    else
                    {
                        y = 0;
                    }
                    sum = carry + x + 9 - y;
                    carry = (sum > 9) ? 1 : 0;
                    sum = sum % 10;
                    temp = new DigitNode(sum, null);
                    
                    if (result == null)
                    {
                        result = temp;
                    }
                    else
                    {
                        prev.next = temp;
                    }
                    
                    prev = temp;
                    
                    if (number != null)
                    {
                        number = number.next;
                    }
                    if (second != null)
                    {
                        second = second.next;
                    }
                }
                
                temp = result;
                DigitNode dn = null;
                DigitNode interim = null;
                while (temp != null)
                {
                    sum = temp.digit + carry;
                    int newVal = sum % 10;
                    carry = sum / 10;
                    
                    interim = new DigitNode(newVal, null);
                    if (dn == null)
                    {
                        dn = interim;
                    }
                    else
                    {
                        prev.next = interim;
                    }
                    
                    prev = interim;
                    
                    temp = temp.next;
                }
                
                BigInteger val = new BigInteger();
                val.front = dn;
                return val;
            }
        }
         
        while (number != null || second != null)
        {
            int x, y;
            if (number != null)
            {
                x = number.digit;
            }
            else
            {
                x = 0;
            }
            
            if (second != null)
            {
                y = second.digit;
            }
            else
            {
                y = 0;
            }

            if(this.negative == false && other.negative == false) {
            sum = carry + x + y;
            }else if(this.negative == true && other.negative == false) {
            	sum = carry - x + y;
            }else if(this.negative == true && other.negative == true){
            	sum = (carry - x - y)*(-1) ;
            }else {
            	sum = carry + x - y;
            }
            
            
            carry = (sum > 9) ? 1 : 0;
            sum = sum % 10;
            temp = new DigitNode(sum, null);
            
            if (result == null)
            {
                result = temp;
            }
            else
            {
                prev.next = temp;
            }
            
            prev = temp;
            
            if (number != null)
            {
                number = number.next;
            }
            if (second != null)
            {
                second = second.next;
            }
        }
        
        if (carry > 0)
        {
            temp.next = new DigitNode(carry, null);
        }
        
        BigInteger val = new BigInteger();
        val.front = result;
        if (this.negative == true && other.negative == true) {
        	val.negative = true;
        }else {
        val.negative = false;
        }
        return val;
        }
	
	

	/**
	 * Returns the BigInteger obtained by multiplying the given BigInteger
	 * with this BigInteger - DOES NOT MODIFY this BigInteger
	 * 
	 * @param other BigInteger to be multiplied
	 * @return A new BigInteger which is the product of this BigInteger and other.
	 */
	public BigInteger multiply(BigInteger other) {
		DigitNode number = this.front;
		DigitNode second = other.front;
		DigitNode result = null;
        DigitNode prev = null;
        DigitNode temp = null;
        
        int product;
        
        while (number != null)
        {
            int x = number.digit;
            DigitNode tmpOther = second;
            while (tmpOther != null)
            {
                int y = tmpOther.digit;
                product = x * y;
                temp = new DigitNode(product, null);
                
                if (result == null)
                {
                    result = temp;
                }
                else
                {
                    prev.next = temp;
                }
                
                prev = temp;
                
                if (tmpOther != null)
                {
                    tmpOther = tmpOther.next;
                }
            }
            if (number != null)
            {
                number = number.next;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (result != null)
        {
            int mod = result.digit % 10;
            int carry = result.digit / 10;
            if (result.next != null)
            {
                result.next.digit = result.next.digit + carry;
            }
            sb.insert(0, mod);
            
            if (result != null)
            {
                result = result.next;
            }
        }
        
        while (sb.charAt(0) == '0' && sb.length() > 1)
        {
            sb.deleteCharAt(0);
        }
        
        String val = "";
        if (negative == false)
        {
            val = sb.toString();
        }
        else
        {
            val = "-" + sb.toString();
        }
        
        BigInteger retVal = BigInteger.parse(val);
        return retVal;
	}
   
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public void printList() {
		DigitNode currentLink = front;
		System.out.print("CURRENT LIST: ");
		while(currentLink!=null) {
			currentLink.printLink();
			currentLink = currentLink.next;
		}
	}
	public String toString() {
		if (front == null) {
			return "0";
		}
		
		String retval = front.digit + "";
		
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
				retval = curr.digit + retval;
		}
		
		if (negative) {
			retval = '-' + retval;
		}
		
		return retval;
	}
	
}
