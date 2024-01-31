public class ServerAddress {
    public String ipAddress;
    public int portNo;

    public void getAddress(String s){

        //creates char buffer which stores ip address character by character
        char[] ch = new char[32];
        int i = 0;
        while(s.charAt(i)!=' '){
            ch[i]=s.charAt(i++);
        }

        //Creates a string with ip address from ch[] buffer
        this.ipAddress = new String(ch,0,i);

        i++;

        //Creates char buffer which stores port no.
        char[] chp = new char[8];
        int j = 0;
        while(i<s.length() && s.charAt(i)!=' '){
            chp[j++] = s.charAt(i++);
        }

        this.portNo = Integer.parseInt(new String(chp,0,j));
    }
    
}
