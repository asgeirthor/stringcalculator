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
		if(number.equals("")){
			return 0;
		}
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
		if(numbers.startsWith("//")){
                        String delimiter = "";

                        if(!numbers.contains("[")){
                                delimiter = findDelimiter(numbers);
				String numberText = numbers.substring(findNumbers(numbers), numbers.length());
                                return numberText.split(String.format("%s", delimiter));
                        }
                        else{
                                String numberText = numbers.substring(findNumbers(numbers), numbers.length());
                                delimiter = findDelimiters(numbers);
                                return numberText.split(String.format("%s", delimiter));
                        }
                }
                return numbers.split("(,)|(\\\n)");
	}

        private static int findNumbers(String numbers) {
               for (int i = 0; i < numbers.length(); i++) {
                        if (numbers.charAt(i) == '\n') {
                                return i + 1;
                        }
                }
                return 0;
        }

        private static String findDelimiter(String numbers){
                Character delim = numbers.charAt(2);
                return delim.toString();
        }

        private static String findDelimiters(String numbers){
                StringBuilder delim = new StringBuilder();
                while(numbers.indexOf('[') != -1){
                        int openingBracket = numbers.indexOf("[");
                        int closingBracket = numbers.indexOf("]");
                        String potentialDelim = numbers.substring(openingBracket +1, closingBracket);

                        for(int i = 0; i < potentialDelim.length(); i++){
                                if(isLegal(potentialDelim.charAt(i))){
                                        delim.append(potentialDelim.charAt(i));
                                }
                                else{
                                        delim.append("\\" + potentialDelim.charAt(i));
                                }
                        }
                        if(numbers.indexOf('[') != -1){
                                delim.append("|");
                        }
                        numbers = numbers.substring(closingBracket +1, numbers.length());
                }
                return delim.toString();
        }

        public static boolean isLegal(char c){
                if  (c == '\\' || c == '.' || c == '[' || c == ']' ||
                        c == '{' || c == '}' || c == '(' || c == ')' ||
                        c == '*' || c == '+' || c == '-' || c == '?' ||
                        c == '^' || c == '$' || c == '|'){
                        return false;
                }
                return true;
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
