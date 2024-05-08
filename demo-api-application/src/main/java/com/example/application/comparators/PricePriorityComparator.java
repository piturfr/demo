/**
 * 
 */
package com.example.application.comparators;

import java.util.Comparator;

import com.example.domain.model.Price;

/**
 * 
 */
public class PricePriorityComparator implements Comparator<Price> {

	@Override
	public int compare(Price price1, Price price2) {
		Integer priority1 = price1.getPriority();
		Integer priority2 = price2.getPriority();

		if (priority1 == null && priority2 == null) {
			return 0;
		} else if (priority1 == null) {
			return 1;
		} else if (priority2 == null) {
			return -1;
		} else {
			return Integer.compare(priority1, priority2);
		}
	}

}
