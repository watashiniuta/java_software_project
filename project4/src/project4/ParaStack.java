package project4;

public class ParaStack<T> {
	protected ObjectStack stack;
	
	public ParaStack() {
		stack = new ObjectStack();
	}
	
	public boolean isEmpty() { return stack.isEmpty(); }
	
	public int size() { return stack.size(); }
	
	public void push(T item) { stack.push(item); }
	
	public T pop() { return (T) stack.pop(); };
}
