package demo.rest;

import demo.service.RunningInfoService;
import demo.domain.RunningInfo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yazhouye on 6/10/17.
 */
@RestController
public class RunningInfoBulkUploadController {
    private final String kDefaultPage = "0";
    private final String kDefaultItemPerPage1 = "2";
    private final String kDefaultItemPerPage2 = "7";

    @Autowired
    private RunningInfoService runningInfoService;

    @RequestMapping(value = " /upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInfo> runningInfo) {
        this.runningInfoService.saveRunningInfo(runningInfo);
    }

    @RequestMapping(value = " /delete/{runningId}", method = RequestMethod.DELETE)
    public ResponseEntity<List<JSONObject>> deleteByRunningId(
            @PathVariable String runningId,
            @RequestParam (name = "page", required = false, defaultValue = kDefaultPage) Integer page,
            @RequestParam (name = "size", required = false, defaultValue = kDefaultItemPerPage2) Integer size) {
        this.runningInfoService.deleteByRunningId(runningId);
        PageRequest request = new PageRequest(page, size, Sort.Direction.DESC, "heartRate");
        Page<RunningInfo> rawResult = this.runningInfoService.getRunningInfoList(request);
        return dataProcessing(rawResult);
    }

    @RequestMapping(value = "/fetchAll/info", method = RequestMethod.GET)
    public ResponseEntity<List<JSONObject>> getAllRunningInfo(
            @RequestParam (name = "page", required = false, defaultValue = kDefaultPage) Integer page,
            @RequestParam (name = "size", required = false, defaultValue = kDefaultItemPerPage1) Integer size) {
        PageRequest request = new PageRequest(page, size, Sort.Direction.DESC, "heartRate");
        Page<RunningInfo> rawResult = this.runningInfoService.getRunningInfoList(request);
        return dataProcessing(rawResult);
    }

    @RequestMapping(value = " /check/level/{healthWarningLevel}", method = RequestMethod.GET)
    public ResponseEntity<List<JSONObject>> findByHealthWarningLevel(
            @PathVariable String healthWarningLevel,
            @RequestParam (name = "page", required = false, defaultValue = kDefaultPage) Integer page,
            @RequestParam (name = "size", required = false, defaultValue = kDefaultItemPerPage2) Integer size) {
        Page<RunningInfo> rawResult = this.runningInfoService.findByHealthWarningLevel(healthWarningLevel, new PageRequest(page, size));
        return dataProcessing(rawResult);
    }

    private ResponseEntity<List<JSONObject>> dataProcessing(Page<RunningInfo> rawResult) {
        List<RunningInfo> content = rawResult.getContent();

        List<JSONObject> results = new ArrayList<JSONObject>();
        for (RunningInfo item : content) {
            JSONObject info = new JSONObject();
            info.put("runningId", item.getRunningId());
            info.put("totalRunningTime", item.getTotalRunningTime());
            info.put("heartRate",item.getHeartRate());
            info.put("userId", item.getId());
            info.put("userName", item.getUserInfo().getUsername());
            info.put("userAddress", item.getUserInfo().getAddress());
            info.put("healthWarningLevel", item.getHealthWarningLevel());
            results.add(info);
        }
        return new ResponseEntity<List<JSONObject>>(results, HttpStatus.OK);
    }
}
