package demo.service;

import demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by yazhouye on 6/10/17.
 */
public interface RunningInfoService {
    void saveRunningInfo(List<RunningInfo> runningInfoList);

    Page<RunningInfo> getRunningInfoList(Pageable pageable);

    void deleteByRunningId(String runningId);

    Page<RunningInfo> findByHealthWarningLevel(String healthWarningLevel, Pageable pageable);
}
