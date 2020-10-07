//File Name: PlayfairCipher.java
//Name: Julian Singer
//OSIS: 235628682

//Description:
/*
 */
//Known bugs:
/*
 */
//Learned:
/*
 */

public class PlayfairCipher {

    /*
    what I need to do
    start with a program that can divide pairs of letters into twos,
        if a double, push second back onto the pile and add an x in its place
        use stacks and queues to do this
    NEXT
        create a program that can create a key to encrypt with
        object will have 2 fields:
            String message
            String[][] key encrypted with
    create basic logical framework for encryption
        locate first letter in 2d array, save coords
        locate second letter in 2d array, save coords
        check rules for same column, row
        execute encryption of that pair of letters
        push encrypted pair onto queue
        once fully encrypted, make a string of each of the pairs of encrypted
   same thing but reverse for the decryption
        to test, use the known encryption key
        later perfect by having it test decryptions
     */
    String message;
    String[][] key;
    String alphabet= "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    public PlayfairCipher(String input){
        message = input;
        key = new String[5][5];
        keyMaker();
    }

    private void keyMaker(){
        int x=(int) (Math.random()*5);
        int y=(int) (Math.random()*5);
        int in = 0;
        while(in < 25){
            System.out.println("(" + x + ", " + y + ")");
            if (key[x][y] == null) {
                key[x][y] = Character.toString(alphabet.charAt(in));
                in++;
            }
            else
            {
                x=(int) (Math.random()*5);
                y=(int) (Math.random()*5);
            }
        }
    }
    public static void main(String[] args) {
        PlayfairCipher t1 = new PlayfairCipher("ABCDEFG");
        System.out.println(t1.message);
        for (int i =0; i<5; i++){
            for (int j=0; j<5; j++){
                System.out.print("[" + t1.key[i][j] + "]");
            }
            System.out.println();
        }
    }
}
