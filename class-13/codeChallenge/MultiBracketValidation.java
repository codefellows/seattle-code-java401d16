import java.util.*;

class MultiBracketValidation {
    public static void main(String[] args){
        System.out.println(validate("{{{}}}[](){[]}"));
        System.out.println(validate("{{{}}}[](){["));
        System.out.println(validate("{{{}}}[]()}}"));
    }

    static public boolean validate(String brackets){
        Stack<Character> bracketStack = new Stack<>();
        
        HashSet<Character> left = new HashSet<>();
        left.add('(');
        left.add('[');
        left.add('{');
        
        HashMap<Character, Character> right = new HashMap<>();
        right.put( '}', '{');
        right.put(']', '[');
        right.put(')', '(');

        for(int i = 0; i < brackets.length(); i++){
            char character = brackets.charAt(i);
            if(left.contains(character)){
                bracketStack.add(character);
            } else if(right.containsKey(character)){
                if(bracketStack.isEmpty()) return false;
                char leftChar = bracketStack.pop();
                if(right.get(character) != leftChar) return false;
            }
        }
        
        if(bracketStack.isEmpty()) return true;
        return false;
    }
}
