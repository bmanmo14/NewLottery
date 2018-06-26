import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

class LotterySet {
	public int one;
	public int two;
	public int three;
	public int four;
	public int five;
	
	public LotterySet() {
		one = 0;
		two = 0;
		three = 0;
		four = 0;
		five = 0;
	}
}

public class NewLottery {

	public static void main(String[] args) {
		String csvFile = "/Users/brandonmouser/Downloads/Lottery_Powerball_Winning_Numbers__Beginning_2010.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
		HashMap<Integer, Integer> numberOccurs = new HashMap<Integer, Integer>();
	    int highest = 0;
        
		for(int i = 1; i <= 69; i++) {
			numberOccurs.put(i, 0);
		}
		
        try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
        	line = br.readLine();
			while ((line = br.readLine()) != null) {
			    // use comma as separator
			    String[] allNumbers = line.split(cvsSplitBy);
			    String[] numbers = allNumbers[1].split(" ");
			    int one = Integer.parseInt(numbers[0]);
			    int two = Integer.parseInt(numbers[1]);
			    int three = Integer.parseInt(numbers[2]);
			    int four = Integer.parseInt(numbers[3]);
			    int five = Integer.parseInt(numbers[4]);
			    //String[] powerball = allNumbers[2].split(",");
			    int six = Integer.parseInt(allNumbers[2]);

			    int oneCount = numberOccurs.get(one) + 1;
			    int twoCount = numberOccurs.get(two) + 1;
			    int threeCount = numberOccurs.get(three) + 1;
			    int fourCount = numberOccurs.get(four) + 1;
			    int fiveCount = numberOccurs.get(five) + 1;
			    //int sixCount = numberOccurs.get(six) + 1;
			    
			    highest = Integer.max(oneCount, 
			    		Integer.max(twoCount, 
			    		Integer.max(threeCount, 
			    		Integer.max(fourCount, 
			    		Integer.max(fiveCount, highest)))));
			    
			    numberOccurs.remove(one);
			    numberOccurs.remove(two);
			    numberOccurs.remove(three);
			    numberOccurs.remove(four);
			    numberOccurs.remove(five);
			    //numberOccurs.remove(six);
			    
			    numberOccurs.put(one, oneCount);
			    numberOccurs.put(two, twoCount);
			    numberOccurs.put(three, threeCount);
			    numberOccurs.put(four, fourCount);
			    numberOccurs.put(five, fiveCount);
			    //numberOccurs.put(six, sixCount);
			    
			    
			    
			    
			}
        }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ArrayList<Integer> allNumbers = new ArrayList<Integer>();

        for(int i = 1; i <= 69; i++) {
        		int occurs = numberOccurs.get(i);
        		for(int j = occurs; j <= highest+1; j++) {
        			allNumbers.add(i);
        		}
        }
        ArrayList<LotterySet> lotterySet = new ArrayList<LotterySet>();

        Random rand = new Random();
        // Gets me a number between 0 and 70, inclusive.
		int randomNum = rand.nextInt(71);
        for(int j = 0; j < 1; j++) {
        		LotterySet set = new LotterySet();
        		ArrayList<Integer> numbers = new ArrayList<Integer>();
        		while(numbers.size() != 5) {
        			int number = allNumbers.get(rand.nextInt(allNumbers.size()));
        			if(!numbers.contains(number)) {
        				numbers.add(number);
        			}
        		}
        		Collections.sort(numbers);
        		set.one = numbers.get(0);
        		set.two = numbers.get(1);
        		set.three = numbers.get(2);
        		set.four = numbers.get(3);
        		set.five = numbers.get(4);
        		lotterySet.add(set);
        }
        
        int size = lotterySet.size();
        int oneTotal = 0;
        int twoTotal = 0;
        int threeTotal = 0;
        int fourTotal = 0;
        int fiveTotal = 0;
        for(int i = 0; i < size; i++) {
        		LotterySet set = lotterySet.get(i);
        		oneTotal += set.one;
        		twoTotal += set.two;
        		threeTotal += set.three;
        		fourTotal += set.four;
        		fiveTotal += set.five;
        }
        
        System.out.print(oneTotal/size + " ");
        System.out.print(twoTotal/size + " ");
        System.out.print(threeTotal/size + " ");
        System.out.print(fourTotal/size + " ");
        System.out.print(fiveTotal/size + " ");
        System.out.println();
	}

}
