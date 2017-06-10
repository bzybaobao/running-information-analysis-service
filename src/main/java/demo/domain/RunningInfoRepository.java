package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yazhouye on 6/10/17.
 */
@Transactional (readOnly = false)
@RepositoryRestResource(path = "RunningInfoList", collectionResourceRel = "RunningInfoList")
public interface RunningInfoRepository extends JpaRepository<RunningInfo, Long> {
    @RestResource(path = "runningId", rel = "by-runningId")
    void deleteByRunningId(@Param("runningId") String runningId);

    @RestResource(path = "healthWarningLevel", rel = "by-healthWarningLevel")
    Page<RunningInfo> findByHealthWarningLevel(@Param("healthWarningLevel") RunningInfo.HealthWarningLevel healthWarningLevel, Pageable pageable);
}
