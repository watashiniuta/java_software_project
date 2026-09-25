package project3;

public class Schedule {
	String title;
	String start;
	String end;
	String place;
	String memo;

	public Schedule(String title, String start, String end, String place, String memo) {
		this.title = title;
		this.start = start;
		this.end = end;
		this.place = place;
		this.memo = memo;
	}
	
	public void print() {
		if(this.title != null) {
			System.out.println("TITLE = " + this.title);
		}
		if(this.start != null) {
			System.out.println("START = " + this.start);
		}
		if(this.end != null) {
			System.out.println("END = " + this.end);
		}
		if(this.place != null) {
			System.out.println("PLACE = " + this.place);
		}
		if(this.memo != null) {
			System.out.println("MEMO = " + this.memo);
		}
	}
}
