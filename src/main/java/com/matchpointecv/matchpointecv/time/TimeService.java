package com.matchpointecv.matchpointecv.time;

import java.util.List;

public interface TimeService {

    List<TimeDTO> getAll();

    TimeDTO save(TimeDTO timeDTO);
}
