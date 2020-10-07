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
    public PlayfairCipher(String input, String keyfob){
        message = input.toUpperCase();
        if (message.length()/2!=0)
            message=message.concat("X");
        key = new String[5][5];
        keyMaker(keyfob);
    }

    private void keyMaker(){
        int x=(int) (Math.random()*5);
        int y=(int) (Math.random()*5);
        int in = 0;
        while(in < 25){
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
    private void keyMaker(String keyfob){
        int x=(int) (Math.random()*5);
        int y=(int) (Math.random()*5);
        int in = 0;
        while(in < 25){
            if (key[x][y] == null) {
                key[x][y] = Character.toString(keyfob.charAt(in));
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
        TwoStackQueue<String> encrypted_pairs = encrypter(letter_pairs);
        message = stringifier(encrypted_pairs);
    }
    private TwoStackQueue<String> divider(String copy){
        TwoStackQueue<String> pairs = new TwoStackQueue<>();
        String pair;
        for(int i = 2; i<=copy.length(); i+=2)
        {
            pair = copy.substring(i-2,i);
            if (pair.charAt(0) == 'J') {
                pair = "I" + Character.toString(pair.charAt(1));
            }
            if (pair.charAt(1) == 'J') {
                pair = Character.toString(pair.charAt(0)) + "I";
            }
            if (pair.charAt(0) == pair.charAt(1)){
                i--;
                pair = pair.substring(0,1);
                pair = pair.concat("X");
            }
            pairs.enqueue(pair);
        }
        return pairs;
    }
    private TwoStackQueue<String> encrypter(TwoStackQueue<String> pairs) {
        /*
        rules:
                1. If the letters are on the same row, use the letters below them to replace them.
                2. If the letters are on the same column, use the letters to their right to replace them.
                3. If the letters are different, replace them with the letters on the same row, but in the column of the other letter
                4. If the letters are the same, insert an X between them.
         */
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        String pair;
        String encrypted;
        TwoStackQueue<String> encrypted_queue = new TwoStackQueue<>();
        while (!pairs.isEmpty()) {
            pair = pairs.dequeue();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (key[i][j].equals(Character.toString(pair.charAt(0)))) {
                        x1 = i;
                        y1 = j;
                    } else if (key[i][j].equals(Character.toString(pair.charAt(1)))) {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
        //rule 1
        if (x1 == x2) {
            if (((x1 + 1) == 5) & ((x2 + 1) == 5))
                encrypted = key[0][y1] + key[0][y2];
            else if ((x1 + 1) == 5)
                encrypted = key[0][y1] + key[x2 + 1][y2];
            else if ((x2 + 1) == 5)
                encrypted = key[x1 + 1][y1] + key[0][y2];
            else
                encrypted = key[x1 + 1][y1] + key[x2 + 1][y2];
            encrypted_queue.enqueue(encrypted);
        }
        //rule 2
        else if (y1 == y2) {
            if (((y1 + 1) == 5) & ((y2 + 1) == 5))
                encrypted = key[x1][0] + key[x2][0];
            else if ((y1 + 1) == 5)
                encrypted = key[x1][0] + key[x2][y2 + 1];
            else if ((y2 + 1) == 5)
                encrypted = key[x1][y1 + 1] + key[x2][0];
            else
                encrypted = key[x1][y1 + 1] + key[x2][y2 + 1];
            encrypted_queue.enqueue(encrypted);
        }
        //rule 3
        else {
            encrypted = key[x1][y2] + key[x2][y1];
            encrypted_queue.enqueue(encrypted);
        }
    }
        return encrypted_queue;
    }
    private String stringifier(TwoStackQueue<String> encrypted_pairs){
        String put_it_all_together="";
        while (!encrypted_pairs.isEmpty()){
            put_it_all_together = put_it_all_together.concat(encrypted_pairs.dequeue());
        }
        System.out.println("message is: "+ message);
        System.out.println("encrypted version is: "+put_it_all_together);
        return put_it_all_together;
    }

    public void decrypt(){
        decryption_helper(message);
    }
    private void decryption_helper(String input){
        String copy = input;
        TwoStackQueue<String> letter_pairs = divider_dec(copy);
        TwoStackQueue<String> decrypted_pairs = decrypter(letter_pairs);
        message = stringifier_dec(decrypted_pairs);
    }
    private  TwoStackQueue<String> divider_dec(String copy){
        TwoStackQueue<String> pairs = new TwoStackQueue<>();
        String pair;
        for(int i = 2; i<=copy.length(); i+=2)
        {
            pair = copy.substring(i-2,i);
            pairs.enqueue(pair);
        }
        return pairs;
    }
    private TwoStackQueue<String> decrypter(TwoStackQueue<String> pairs){
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        String pair;
        String decrypted;
        TwoStackQueue<String> decrypted_queue = new TwoStackQueue<>();
        while (!pairs.isEmpty()) {
            pair = pairs.dequeue();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (key[i][j].equals(Character.toString(pair.charAt(0)))) {
                        x1 = i;
                        y1 = j;
                    } else if (key[i][j].equals(Character.toString(pair.charAt(1)))) {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
            //rule 1
            if (x1 == x2) {
                if (((x1 - 1) == -1) & ((x2 - 1) == -1))
                    decrypted = key[4][y1] + key[4][y2];
                else if ((x1 - 1) == -1)
                    decrypted = key[4][y1] + key[x2 - 1][y2];
                else if ((x2 - 1) == -1)
                    decrypted = key[x1 - 1][y1] + key[4][y2];
                else
                    decrypted = key[x1 - 1][y1] + key[x2 - 1][y2];
                decrypted_queue.enqueue(decrypted);
            }
            //rule 2
            else if (y1 == y2) {
                if (((y1 - 1) == -1) & ((y2 - 1) == -1))
                    decrypted = key[x1][4] + key[x2][4];
                else if ((y1 - 1) == -1)
                    decrypted = key[x1][4] + key[x2][y2 - 1];
                else if ((y2 - 1) == -1)
                    decrypted = key[x1][y1 - 1] + key[x2][4];
                else
                    decrypted = key[x1][y1 - 1] + key[x2][y2 - 1];
                decrypted_queue.enqueue(decrypted);
            }
            //rule 3
            else {
                decrypted = key[x1][y2] + key[x2][y1];
                decrypted_queue.enqueue(decrypted);
            }
        }
        return decrypted_queue;
    }
    private String stringifier_dec(TwoStackQueue<String> encrypted_pairs){
        String put_it_all_together="";
        String pair;
        while (!encrypted_pairs.isEmpty()){
            pair= encrypted_pairs.dequeue();
            if (pair.charAt(0)=='X')
                pair = pair.substring(1,1);
            else if (pair.charAt(1)=='X')
                pair = pair.substring(0,0);
            put_it_all_together = put_it_all_together.concat(pair);
        }

        System.out.println("message is: "+ message);
        System.out.println("decrypted version is: "+put_it_all_together);
        return put_it_all_together;
    }
    public static void main(String[] args) {
        if (args[0].compareTo("encode") == 0) {
            PlayfairCipher in = new PlayfairCipher(args[1], args[2]);
            in.encrypt();
        } else if (args[0].compareTo("decode") == 0) {
            PlayfairCipher in = new PlayfairCipher(args[1], args[2]);
            in.decrypt();
        } else {
            PlayfairCipher t1 = new PlayfairCipher("ABCDEFGHIJ");
            System.out.println(t1.message);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print("[" + t1.key[i][j] + "]");
                }
                System.out.println();
            }
            t1.encrypt();
            t1.decrypt();

            PlayfairCipher test1 = new PlayfairCipher("WHITEHAT", "PLAYFIREXMBCDGHKNOQSTUVWZ");
            test1.encrypt();
            test1.decrypt();
        }
    }
}
