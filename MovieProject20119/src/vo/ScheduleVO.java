package vo;

import java.sql.Timestamp;

public class ScheduleVO {
	private int schNo;
	private String movieName;
	private Timestamp runDay;
	private int roomNo;
	private int seatCnt;
	
	public ScheduleVO() {}
	
	public ScheduleVO(int schNo, String movieName, Timestamp runDay, int roomNo, int seatCnt) {
		this.schNo = schNo;
		this.movieName = movieName;
		this.runDay = runDay;
		this.roomNo = roomNo;
		this.seatCnt = seatCnt;
	}

	public int getSchNo() {
		return schNo;
	}

	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Timestamp getRunDay() {
		return runDay;
	}

	public void setRunDay(Timestamp runDay) {
		this.runDay = runDay;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getSeatCnt() {
		return seatCnt;
	}

	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}

	@Override
	public String toString() {
		return "[movieName : " + movieName + "] [runDay : " + runDay + "] [roomNo : " + roomNo + "] [seatCnt : " + seatCnt + "]";
	}
}
