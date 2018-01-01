package Model.MODEL;

import java.math.BigDecimal;

public class TotalReportModel {

	private int time;
	private BigDecimal revenue;
	private String dayName;
	public TotalReportModel(int time, long revenue){
		this.time = time;
		this.revenue = BigDecimal.valueOf(revenue);
	}
	
	public TotalReportModel(String dayName, long revenue){
		this.setDayName(dayName);
		this.revenue = BigDecimal.valueOf(revenue);
	}
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * @return the revenue
	 */
	public BigDecimal getRevenue() {
		return revenue;
	}
	/**
	 * @param revenue the revenue to set
	 */
	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	/**
	 * @return the dayName
	 */
	public String getDayName() {
		return dayName;
	}

	/**
	 * @param dayName the dayName to set
	 */
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

}
