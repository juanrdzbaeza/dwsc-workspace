package dwsc.microservices.eureka.client.data_validator.service;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

@Service
public class DataValidatorServiceImpl implements DataValidatorService {
	
	@Override
	public boolean validateData(String data) {
		boolean valid = false;
		
		int numericDataPart = Integer.parseInt(data.substring(0, 8));
		String dataLetter = data.substring(8);
		
		int remainder = numericDataPart % 23;
		String expectedLetter = getLetterFromRemainder(remainder);
		
		if(dataLetter.equals(expectedLetter)) {
			valid = true;
		}
		
		return valid;
	}
	
	private String getLetterFromRemainder(int remainder) {
		Map<Integer, String> remainderLetterDic = new TreeMap<Integer, String>();
		
		remainderLetterDic.put(0, "T");		remainderLetterDic.put(1, "R");
		remainderLetterDic.put(2, "W");		remainderLetterDic.put(3, "A");
		remainderLetterDic.put(4, "G");		remainderLetterDic.put(5, "M");
		remainderLetterDic.put(6, "Y");		remainderLetterDic.put(7, "F");
		remainderLetterDic.put(8, "P");		remainderLetterDic.put(9, "D");
		remainderLetterDic.put(10, "X");	remainderLetterDic.put(11, "B");
		remainderLetterDic.put(12, "N");	remainderLetterDic.put(13, "J");
		remainderLetterDic.put(14, "Z");	remainderLetterDic.put(15, "S");
		remainderLetterDic.put(16, "Q");	remainderLetterDic.put(17, "V");
		remainderLetterDic.put(18, "H");	remainderLetterDic.put(19, "L");
		remainderLetterDic.put(20, "C");	remainderLetterDic.put(21, "K");
		remainderLetterDic.put(22, "E");
		
		return remainderLetterDic.get(remainder);
	}
	
	
	
}
