package project5;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
	private String title;
	private LocalDateTime start;
	private LocalDateTime end;
	private String place;
	private String memo;

	public Schedule(String title, LocalDateTime start, LocalDateTime end, String place, String memo) {
		this.title = title;
		this.start = start;
		this.end = end;
		this.place = place;
		this.memo = memo;
	}
	
	public void print() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		if(this.title != null) {
			System.out.println("TITLE = " + this.title);
		}
		if(this.start != null) {
			System.out.println("START = " + this.start.format(formatter));
		}
		if(this.end != null) {
			System.out.println("END = " + this.end.format(formatter));
		}
		if(this.place != null) {
			System.out.println("PLACE = " + this.place);
		}
		if(this.memo != null) {
			System.out.println("MEMO = " + this.memo);
		}
		System.out.println(";");
	}
	
	public String getTitle() { return this.title; }
	public LocalDateTime getStart() { return this.start; }
	public LocalDateTime getEnd() { return this.end; }
	public String getPlace() { return this.place; }
	public String getMemo() { return this.memo; }
	
	public void setTitle(String title) { this.title = title; }
	public void setStart(LocalDateTime start) { this.start = start; }
	public void setEnd(LocalDateTime end) {this.end = end; }
	public void setPlace(String place) { this.place = place; }
	public void setMemo(String memo) { this.memo = memo; }
}
