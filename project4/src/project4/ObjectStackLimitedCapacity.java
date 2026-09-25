package project4;

public class ObjectStackLimitedCapacity {
	protected Object[] elements;
	protected int top;
	protected static final int capacity = 10;
	
	public ObjectStackLimitedCapacity() {
		this.top = -1;
		this.elements = new Object[capacity];
	}
	
	public boolean isEmpty() { return top == -1; }
	
	public int size() {
		return top + 1;
	}
	
	public Object pop() {
		if (isEmpty()) {
			return null;
		}
		
		Object o = elements[top];
		elements[top--] = null;
		return o;
	}
	
	public void push(Object o) {
		if (top == (capacity - 1)) { System.out.println("Stack is Full!!!"); }
		elements[++top] = o;
	}
}
