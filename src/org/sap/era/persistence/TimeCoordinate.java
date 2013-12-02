package org.sap.era.persistence;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.sap.era.persistence.enums.EIntervalTag;
import org.sap.era.persistence.enums.EOriginTag;
import org.sap.era.persistence.enums.EPeriodTag;

@Entity
@Table(name = "T_TIME_COORDINATE")
public class TimeCoordinate implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String STANDARD_DESCRIPTION_TEMPLATE = "The %s day %s the %s";
	private static final String NON_OFFSET_DESCRIPTION_TEMPLATE = "The %s.";
	/*
	 * Fields
	 */

	@Id
	@GeneratedValue
	private long id;

	@Enumerated(EnumType.ORDINAL)
	private EPeriodTag periodTag;

	@Enumerated(EnumType.ORDINAL)
	private EIntervalTag intervalTag;

	@Enumerated(EnumType.ORDINAL)
	private EOriginTag originTag;

	@Basic
	private int OffsetDays;

	private String description;

	/*
	 * Public Methods
	 */

	public boolean tryAdjust() {
		if (this.periodTag != null && this.intervalTag != null && this.originTag != null) {
			this.intervalTag.setPeriodTag(this.periodTag);
			this.originTag.setPeriodTag(this.periodTag);
			this.originTag.setIntervalTag(this.intervalTag);
			/**
			 * Build the date offset description
			 */
			// Refactor if necessary
			final String POSITIVE_PREFIX = "After";
			final String NEGATIVE_PREFIX = "Before";
			final String DATE_OFFSET_SUFFIX = "st";

			int postiveOffsetDays = Math.abs(this.getOffsetDays());
			if (postiveOffsetDays == 0) {
				this.description = String.format(NON_OFFSET_DESCRIPTION_TEMPLATE, this.getOriginTag());
			} else {
				String postiveOffsetDaysValue = postiveOffsetDays + DATE_OFFSET_SUFFIX;
				String prefix = null;
				if (OffsetDays > 0) {
					prefix = POSITIVE_PREFIX;
				} else {
					prefix = NEGATIVE_PREFIX;
				}
				this.description = String.format(STANDARD_DESCRIPTION_TEMPLATE, postiveOffsetDaysValue, prefix, this.getOriginTag());
			}
			return true;
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
	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonIgnore
	public EPeriodTag getPeriodTag() {
		return periodTag;
	}

	public void setPeriodTag(EPeriodTag periodTag) {
		this.periodTag = periodTag;
		tryAdjust();
	}

	public EIntervalTag getIntervalTag() {
		return intervalTag;
	}

	public void setIntervalTag(EIntervalTag intervalTag) {
		this.intervalTag = intervalTag;
		tryAdjust();
	}

	public EOriginTag getOriginTag() {
		return originTag;
	}

	public void setOriginTag(EOriginTag originTag) {
		this.originTag = originTag;
	}

	public int getOffsetDays() {
		return OffsetDays;
	}

	public void setOffsetDays(int offsetDays) {
		OffsetDays = offsetDays;
	}

	public String getDescription() {
		return this.description;
	}
}