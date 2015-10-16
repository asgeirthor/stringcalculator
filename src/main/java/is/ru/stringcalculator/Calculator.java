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
			String delimiter = findDelimiter(numbers);
			StringBuilder delim = new StringBuilder("");
			CharSequence escapeChars = "\\";
			for(int i = 0; i < delimiter.length(); i++){
				delim.append(escapeChars);
				delim.append(delimiter.charAt(i));
			}
			if(delimiter.length() > 1){
				String newNumbers = numbers.substring(numbers.indexOf("]") +2, numbers.length());
				return newNumbers.split("(\\\n)|" + String.format("(%s)", delim));
			}
			else{
				String newNumbers = numbers.substring(4, numbers.length());
	                        return newNumbers.split("(\\\n)|" + String.format("(%s)", delimiter));
			}
		} 
		return numbers.split("(,)|(\\\n)");
	}

	private static String findDelimiter(String numbers){
		String delimiter = "";
		if(numbers.indexOf('[') == -1){
			char delim = numbers.charAt(2);
			delimiter = "" + delim;
		}
		else{
                        int indexOfClosingBracket;
                        indexOfClosingBracket = numbers.indexOf("]");
                        delimiter = numbers.substring(3, indexOfClosingBracket);
		}
		return delimiter;
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
