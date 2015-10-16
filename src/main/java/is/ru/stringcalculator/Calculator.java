package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		else{
			return sum(splitNumbers(text));
		}
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    	if(numbers.charAt(0) == '/' && numbers.charAt(1) == '/'){
			String newNumbers = numbers.substring(4, numbers.length());
			return newNumbers.split("(\\\n)|" + String.format("(%c)", numbers.charAt(2)));
		} 
		return numbers.split("(,)|(\\\n)");
	}
      
	private static int sum(String[] numbers){
 		int total = 0;
		String message = "Negatives not allowed: ";
        	for(String number : numbers){
			int num = toInt(number);
			if(num < 0){
				message += number + ",";
			}
			else if(num > 1000){
				continue;
			}
			else{
				total += num;
			}
		}
		if(message.length() > 23){
			throw new IllegalArgumentException(message.substring(0, message.length() -1));
		}
		return total;
    }



}
