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

import java.util.Queue;

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
        message = input.toUpperCase();
        if (message.length()/2!=0)
            message=message.concat("X");
        key = new String[5][5];
        keyMaker();
    }

    private void keyMaker(){
        int x=(int) (Math.random()*5);
        int y=(int) (Math.random()*5);
        int in = 0;
        while(in < 25){
            //System.out.println("(" + x + ", " + y + ")");
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

    public void encrypt(){
        encryption_helper(message);
    }
    private void encryption_helper(String input){
        String copy = input;
        TwoStackQueue<String> letter_pairs = divider(copy);
        /*
        while (!letter_pairs.isEmpty()){
            System.out.println(letter_pairs.dequeue());
        }
        */

    }
    private TwoStackQueue<String> divider(String copy){
        TwoStackQueue<String> pairs = new TwoStackQueue<>();
        String pair;
        for(int i = 2; i<=copy.length(); i+=2)
        {
            pair = copy.substring(i-2,i);
            if (pair.charAt(0) == pair.charAt(1)){
                i--;
                pair = pair.substring(0,1);
                pair = pair.concat("X");
            }
            System.out.println("pair is " + pair);
            pairs.enqueue(pair);
        }
        return pairs;
    }
    private TwoStackQueue<String> encrypter(TwoStackQueue<String> pairs){
        /*
        rules:
                1. If the letters are on the same row, use the letters below them to replace them.
                2. If the letters are on the same column, use the letters to their right to replace them.
                3. If the letters are different, replace them with the letters on the same row, but in the column of the other letter
                4. If the letters are the same, insert an X between them.
         */
        return null;
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
        t1.encrypt();
    }
}
