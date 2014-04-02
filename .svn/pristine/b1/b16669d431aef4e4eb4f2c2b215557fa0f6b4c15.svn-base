package org.sap.era.persistence;

import static javax.persistence.EnumType.ORDINAL;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.sap.era.persistence.enums.EPeriodTag;

@Entity
@Table(name = "T_YEARLY_TABEL_GROUP_ASSIGNMENT")
public class PeriodicTableGroupAssignment extends TableGroupAssignment {

	private static final String DESCRIPTION_TEMPLATE = "Assignments would create %s, the task would be created at %s, start at %s, end at %s.";

	public PeriodicTableGroupAssignment() {
	}

	/*
	 * Fields
	 */
	@Enumerated(ORDINAL)
	private EPeriodTag periodTag;

	@OneToOne(cascade = { CascadeType.ALL })
	private TimeCoordinate createTimeCoordinate;
	@OneToOne(cascade = { CascadeType.ALL })
	private TimeCoordinate startTimeCoordinate;
	@OneToOne(cascade = { CascadeType.ALL })
	private TimeCoordinate endTimeCoordinate;

	private String description;

	/*
	 * Public Methods
	 */

	public boolean tryAdjust() {
		if (this.periodTag != null) {
			if (this.createTimeCoordinate != null) {
				this.createTimeCoordinate.setPeriodTag(this.periodTag);
			}
			if (this.startTimeCoordinate != null) {
				this.startTimeCoordinate.setPeriodTag(this.periodTag);
			}
			if (this.endTimeCoordinate != null) {
				this.endTimeCoordinate.setPeriodTag(this.periodTag);
			}
			boolean isAdjusted = this.createTimeCoordinate != null && this.startTimeCoordinate != null && this.endTimeCoordinate != null;
			// Refactor if necessary
			if (isAdjusted) {
				this.description = String.format(DESCRIPTION_TEMPLATE, this.getPeriodTag(), this.getCreateTimeCoordinate(),
						this.getStartTimeCoordinate(), this.getEndTimeCoordinate());
			}
			return isAdjusted;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.getDescription();
	}

	/*
	 * Getters & Setters
	 */
	public EPeriodTag getPeriodTag() {
		return this.periodTag;
	}

	public void setPeriodTag(EPeriodTag periodTag) {
		this.periodTag = periodTag;
		tryAdjust();
	}

	public TimeCoordinate getCreateTimeCoordinate() {
		return this.createTimeCoordinate;
	}

	public void setCreateTimeCoordinate(TimeCoordinate createTimeCoordinate) {
		this.createTimeCoordinate = createTimeCoordinate;
		tryAdjust();
	}

	public TimeCoordinate getStartTimeCoordinate() {
		return this.startTimeCoordinate;
	}

	public void setStartTimeCoordinate(TimeCoordinate startTimeCoordinate) {
		this.startTimeCoordinate = startTimeCoordinate;
		tryAdjust();
	}

	public TimeCoordinate getEndTimeCoordinate() {
		return this.endTimeCoordinate;
	}

	public void setEndTimeCoordinate(TimeCoordinate endTimeCoordinate) {
		this.endTimeCoordinate = endTimeCoordinate;
		tryAdjust();
	}

	public String getDescription() {
		tryAdjust();
		return this.description;
	}
}