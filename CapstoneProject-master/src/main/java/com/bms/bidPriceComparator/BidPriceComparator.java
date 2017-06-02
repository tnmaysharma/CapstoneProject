package com.bms.bidPriceComparator;

import java.util.Comparator;

import com.bms.domain.BidList;

public class BidPriceComparator implements Comparator<BidList> {

	@Override
	public int compare(BidList o1, BidList o2) {
		if (Integer.valueOf(o1.getBiddingPrice()) == Integer.valueOf(o2.getBiddingPrice()))
			return 0;
		else if (Integer.valueOf(o1.getBiddingPrice()) > Integer.valueOf(o2.getBiddingPrice()))
			return 1;
		else
			return -1;
	}

}
