public class TwoStackQueue<T> implements TwoStackQueueInterface<T>{
    
    MyStack<T> s1 = new MyStack<>();
    MyStack<T> s2 = new MyStack<>();
    public void enqueue(T x){
        s1.push(x);
        //System.out.println("enqueue was called. " + x + " was enqueued.");
    }
	public T dequeue(){
        if (s2.size()==0 && s1.size()!=0){
            //System.out.println("s2 has a size of " + s2.size() + " and so all of s1 will be copied over in reverse order");
            int targetSize = s1.size();
            for (int i = 0; i < targetSize; i++){
                T temp = s1.pop();
                //System.out.println("the item being transfered at cycle " + i + " is " + temp);
                s2.push(temp);
            }
        }
        else if (s1.size() == 0 && s2.size() == 0){
            //System.out.println("both stacks are empty. Null is being returned");
            return null;
        }
        T temp = s2.pop();
        //System.out.println("item " + temp + " was dequeued");
        return temp;
    }
	public int size(){
        //System.out.println(s1.size() + "+" + s2.size());
        return s1.size() + s2.size();
    }
	public boolean isEmpty(){
        boolean res = false;
        //System.out.println("Checking if s1 and s2 are empty. If yes, return true. Else return false");
        if (s2.isEmpty()){
            if (s1.isEmpty()){
                res = true;
            }
        }
        return res;
    }

    
    public static void main(String[] args)
    {
//         TwoStackQueue test = new TwoStackQueue();
//         System.out.println("Should be true: " + test.isEmpty());
//         test.enqueue(1);
//         test.enqueue(2);
//         System.out.println("Should be 2: " + test.size());
//         System.out.println("Should be false: " + test.isEmpty());
//         System.out.println("Should be 1: " + test.dequeue());
//         System.out.println("Should be false: " + test.isEmpty());
//         System.out.println("Should be 1: " + test.size());
//         System.out.println("Should be false: " + test.isEmpty());
    }
}