package math;
public class DigitNode {
	int digit;
	DigitNode next;
	DigitNode(int digit, DigitNode next) {
		this.digit = digit;
		this.next = next;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return digit + "";
	}
	public void printLink() {
		System.out.print("("+digit+")-> ");
	}
}
