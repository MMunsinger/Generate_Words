import java.util.HashMap;
import java.util.Map;


/*
 * This class maps characters a-z to the corresponding
 * numbers found on a phones keypad
 */
public class keypadMapper {
    Map<Character, Integer> charToNumMap = new HashMap<>()
{
    {
        put('a', 2);
        put('b', 2);
        put('c', 2);
        put('d', 3);
        put('e', 3);
        put('f', 3);
        put('g', 4);
        put('h', 4);
        put('i', 4);
        put('j', 5);
        put('k', 5);
        put('l', 5);
        put('m', 6);
        put('n', 6);
        put('o', 6);
        put('p', 7);
        put('q', 7);
        put('r', 7);
        put('s', 7);
        put('t', 8);
        put('u', 8);
        put('v', 8);
        put('w', 9);
        put('x', 9);
        put('y', 9);
        put('z', 9);
    }

};


/*
 * This method reads in a char and returns the value of that char
 * Ex. 'c' returns 2. 
 */
public int getValue(char key)
{
    int result = charToNumMap.get(key);
    return result;
}

}