import java.util.HashMap;

public class InfoMapping {
    public HashMap<String,String> getMap(String message){

        //Creates hashmap to store information passed to its
        HashMap<String, String> map = new HashMap<String, String>();

        // stores temporary key and value messages
        String key = null, value = null;

        int i = 0, j = 0;

        //stores each key value pair in char buffer
        char[] charBuff = new char[256];

        //checks for the length of message passed
        while(i<message.length()){

            //adds the character at index i to the character buffer, if they are not equal to ':', '"', and space
            if((message.charAt(i)!=':') && (message.charAt(i)!='\"') && (message.charAt(i)!=' ')){
                charBuff[j++] = message.charAt(i++);
            }
            else if(message.charAt(i) == ':'){ 
                // if the character encountered is ':' it constructs string key with the value in char buffer and resets the char buffer
                key = new String(charBuff,0,j); 
                i++;
                j=0;
            }
            else if(message.charAt(i)== ' '){
                // if the character encountered is space it constructs string value with the value in char buffer and resets the char buffer
                value = new String(charBuff,0,j);

                // checks if the value constructed has key associated with it or else map value of the string as key in the map to null
                if(key==null){
                    map.put(value,null);
                }else{
                    map.put(key,value);
                }

                i++;
                j=0;
            }
            // as messages are contained within double quotes and can contain any of the character, the double quotes will be handled seperately 
            else{
                // reads every character until the character at i equals '"';
                charBuff[j++] = message.charAt(i++);
                while(message.charAt(i)!='\"'){
                    charBuff[j++] = message.charAt(i++);
                }
                charBuff[j++] = message.charAt(i);
                value = new String(charBuff,0,j);

                // checks if the value constructed has key associated with it or else map value of the string as key in the map to null
                if(key==null){
                    map.put(value,null);
                }else{
                    map.put(key,value);
                }
                i=i+2;
                j=0;
            }
        }

        // returns the reference to resultant map
        return map;

    }
}
