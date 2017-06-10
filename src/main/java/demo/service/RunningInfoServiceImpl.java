package demo.service;

import demo.domain.RunningInfo;
import demo.domain.RunningInfoRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yazhouye on 6/10/17.
 */
@Setter
@Service
public class RunningInfoServiceImpl implements RunningInfoService{
    @Autowired
    private RunningInfoRepository repository;

    @Override
    public void saveRunningInfo(List<RunningInfo> runningInfo) {
        repository.save(runningInfo);
    }

    @Override
    public Page<RunningInfo> getRunningInfoList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteByRunningId(String runningId) {
        repository.deleteByRunningId(runningId);
    }

    @Override
    public Page<RunningInfo> findByHealthWarningLevel(String healthWarningLevel, Pageable pageable) {
        return repository.findByHealthWarningLevel(RunningInfo.HealthWarningLevel.valueOf(healthWarningLevel), pageable);
    }
}
