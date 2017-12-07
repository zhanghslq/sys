package com.yb.service;

import java.util.Date;

public interface HoseService {
	Double queryHoseByPumpAndDate(Date start,Date end,String pump,Integer hose,String station);
}
