
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


/*
 * This Program take a string of any characters including integer, symbols, string as input.
 * It finds any repeated, unique substring from the input and print out the substrings to the console.
 * 
 * @author  Tzu-Jung(Dara)Chi
 * @Since   29-11-2019
 * @version 1.1
 */
public class java_DaraChi{

	public static void main(String[] args) {
		String N = "123123";//testing numeric input; 
		NABTest(N);	
		String N2 = "abcabc";//testing string input
		NABTest(N2);
		String N3 = "AbcABC";//testing case sensitive input
		NABTest(N3);	
		String N4 = "1%a2%3";//testing mixed input
		NABTest(N4);	
		String N5 = "@#$$#@";//testing symbol input
		NABTest(N5);	
		String N6 = "";//testing empty string input
		NABTest(N6);		
	}

	/*
	 * take input N. and output repeated, unique substring.
	 * @param N: original string input
	 */
	public static void NABTest(String N) {
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		System.out.println("Input: "+ N);	
		//find out maximum length for possible repeated substrings which is half of the input length.
		for(int size = 1; size <= checkMaximunHalfLength(N);size++) { //set the size of desired substring.
			//loop through each char in the string for all substrings according to the size set.
			for(int startPoint = 0; startPoint <= N.length()-size; startPoint++) {
				String substring = findASubstring(N,startPoint, startPoint+size);
				updateSubstringHashMap(substring,hMap);//store all substring in hashMap and and update count accordingly.
			}
		}	
		findRepeatedSubstring(hMap);
	}

	/*
	 * take string input and return a length that is half of the length of the original string.
	 * @param input: original string input.
	 */
	public static int checkMaximunHalfLength(String input) {
		
		return input.length()/2;
	}
	
	/*
	 *take the length of desired substring and start point to find the substring.
	 *@param input: original string input.  
	 *@param startPoint: starting index position inclusive.
	 *@param endPoint: ending index position exclusive.
	 */
	public static String findASubstring(String input,int startPoint,int endPoint){
		
		return input.substring(startPoint, endPoint);	
	}
	
	/*
	 * take a substring as key and put it in the hashmap and update value accordingly.
	 * @param substring: potential repeated substring.
	 * @param hMap: hashmap to store all the substrings.
	 */
	public static void updateSubstringHashMap(String substring, HashMap<String, Integer> hMap){
	
		if(hMap.containsKey(substring)) {
			hMap.put(substring, (int)hMap.get(substring)+1);
		} else 
			hMap.putIfAbsent(substring, 1);
	}
	
	/*
	 * filter passed hashmap with value > 1 and print out a processed, key-sorted LinkedHashMap.
	 * reference:https://www.mkyong.com/java8/java-8-filter-a-map-examples/
	 * reference:https://www.java67.com/2017/07/how-to-sort-map-by-values-in-java-8.html
	 * @param hMap: hashMap which kept all substrings.
	 */
	public static void findRepeatedSubstring(HashMap<String, Integer> hMap) {
		Map<String, Integer> processedMap = new HashMap<String, Integer>();
		processedMap = hMap.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByKey())
				.filter(map->map.getValue()>1)
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue(), (p1,p2)->p2,LinkedHashMap::new));
		
		System.out.println(processedMap.size()+" Substrings: "+processedMap.keySet()+"\n");
	}

}
	


