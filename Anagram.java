public class Anagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("silent","listen"));  // true
        System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie","Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

        System.out.println(preProcess("What? No way!!!"));

        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

        String str = "1234567";
        boolean pass = true;
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test Failed");
    }

    
    public static String preProcess(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + ('a' - 'A')); 
            }
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                result = result + c; 
            }
        }
        return result;
    }

    
    public static boolean isAnagram(String s1, String s2) {
        
        String p1 = preProcess(s1);
        String p2 = preProcess(s2);
        if (p1.length() != p2.length()) {
            return false;
        }
        for (int i = 0; i < p1.length(); i++) {
            char charToFind = p1.charAt(i);
            int indexInP2 = p2.indexOf(charToFind);
            if (indexInP2 == -1) {
                return false;
            } 
            String partBefore = p2.substring(0, indexInP2);
            String partAfter = p2.substring(indexInP2 + 1);
            p2 = partBefore + partAfter;
        }

        return p2.length() == 0;
    }

   
   	public static String randomAnagram(String str) {
		String temp = str;
		String newStr="";
		for(int i=0; i<str.length(); i++)
		{
			int randomIndex = (int)(Math.random() * temp.length());
			char c = temp.charAt(randomIndex);
			temp = temp.substring(0, randomIndex) + temp.substring(randomIndex + 1);
			newStr= newStr +c;

		}
		return newStr;
	}

  
}