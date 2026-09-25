package project4;

public class ObjectStack extends ObjectStackLimitedCapacity{
	
	public ObjectStack() {}
	
	public void push(Object o) {
        if (top >= elements.length - 1) {
            Object[] newArray = new Object[2 * elements.length];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
        
        elements[++top] = o;
    }
}
