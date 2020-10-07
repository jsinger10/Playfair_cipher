public class MyStack<T> implements MyStackInterface<T>{
        
           
    private T[] myItems;
    private int size = 0;
    
    public MyStack(){
        myItems = (T[]) new Object[10];
        //System.out.println(myItems.length);
        size = 0;
        //System.out.println(size);
    }
    
    public void ensureCapacity( int newCapacity )
        {
        if( newCapacity < size )
            return;

        T[] old = (T[]) myItems;
        myItems = (T[]) new Object[ newCapacity ];
        for( int i = 0; i < size( ); i++ )
            myItems[ i ] = old[ i ];
    }
    
    public void push(T x){
        //System.out.println("push has been called. The size is: " + size + " and the length of the stack is " + myItems.length);
        if (size == myItems.length){
                        //System.out.println("The array has reached its maximum length. ensureCapacity has been called to double the size of the array");
            ensureCapacity(size * 2);
        }
        myItems[size] = x;
        size++;
        //System.out.println("the method push has been called. Size has been incremented from " + (size-1) + " to " + size);
        //System.out.println("The item " + x + " has been added at index " + (size-1));

    }
    
    
	public T pop(){
        //System.out.println("the method pop has been called.");
        if (size == 0) {
            //System.out.println("the stack is empty, null object returned");
                        return null;
            }
        T temp = (T) myItems[size-1];
        size = size-1;
                //System.out.println("The item " + temp + "has been removed from the stack.");
                            //System.out.println("the size has been changed from " + (size + 1) +" to " + size);
        return temp;
    }
    
    
	public T peek(){
                //System.out.println("the following is the top item of the stack: ");
        return myItems[size - 1];}
    
	public boolean isEmpty(){
                //System.out.println("the method isEmpty has been called. The following is true if the stack is empty:");
        boolean res = false;
        if (size == 0) res = true;
        return res;
    }
	public int size(){
                //System.out.print("The following is the size of the array: ");
        //System.out.println(size);
        return size;
    }
    
    public static void main(String[] args){
//         MyStack<Integer> stack = new MyStack<>();
//         System.out.println("Should be 0: " + stack.size());
//         System.out.println("Should be true: " + stack.isEmpty());
//                 System.out.println("");
//         System.out.println("Should be null: " + stack.pop());

//         stack.push(1);
//                 System.out.println("");
//         System.out.println("Should be 1: " + stack.size());

//         stack.push(2);
//                 System.out.println("");

//         stack.push(3);
//                 System.out.println("");

//         System.out.println("Should be false: " + stack.isEmpty());
//                 System.out.println("");

//         System.out.print("Should return 3: ");
//         System.out.println(stack.peek());
//                 System.out.println("");

//         System.out.println("Should be 3: " + stack.pop());
//                 System.out.println("");

//         System.out.println("Should be 2: " + stack.pop());
//         MyStack<Integer> lst = new MyStack<>( );
//         System.out.println(lst.size());
//         for( int i = 0; i < 10; i++ )
//                 lst.push( i );
//         for( int i = 20; i < 30; i++ )
//                 lst.push( i );

    }
}