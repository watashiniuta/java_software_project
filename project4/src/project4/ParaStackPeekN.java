package project4;

public class ParaStackPeekN<T> extends ParaStack<T> {
	
	public ParaStackPeekN() {}
	
	public T peek(int n) {
		if (n > size() || n <= 0) { 
			System.out.println("peek error!!!");
			return null;
		}

        ObjectStack temp = new ObjectStack();
        T result = null;

        for (int i = 0; i < n; i++) {
            result = this.pop();
            temp.push(result);
        }

        while (!temp.isEmpty()) {
            this.push((T) temp.pop());
        }

        return result;
	}
}



