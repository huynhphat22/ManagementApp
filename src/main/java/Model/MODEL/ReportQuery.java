package Model.MODEL;

import java.sql.Date;

public class ReportQuery {

	private Date date;
	private int week;
	private int month;
	private int quarter;
	private int year;
	private int periodType;
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the week
	 */
	public int getWeek() {
		return week;
	}
	/**
	 * @param week the week to set
	 */
	public void setWeek(int week) {
		this.week = week;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the quarter
	 */
	public int getQuarter() {
		return quarter;
	}
	/**
	 * @param quarter the quarter to set
	 */
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the periodType
	 */
	public int getPeriodType() {
		return periodType;
	}
	/**
	 * @param periodType the periodType to set
	 */
	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}

}
