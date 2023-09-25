package com.matchpointecv.matchpointecv.time;

import java.util.List;

public interface TimeService {

    List<TimeDTO> getAll();

    List<TimeDTO> getAllByIds(List<Long> ids);

    TimeDTO save(TimeDTO timeDTO);
}
