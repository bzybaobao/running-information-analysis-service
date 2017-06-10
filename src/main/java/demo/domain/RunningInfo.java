package demo.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.Random;

/**
 * Created by yazhouye on 6/10/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "private")
public class RunningInfo {
    public enum HealthWarningLevel {
        LOW, NORMAL, HIGH;
    }

    @Id
    @GeneratedValue
    private Long Id;

    private String runningId;
    private double latitude;
    private double longitude;
    private double runningDistance;
    private double totalRunningTime;

    private int heartRate;
    private HealthWarningLevel healthWarningLevel;

    private Date timestamp;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "user_name")),
            @AttributeOverride(name = "address", column = @Column(name = "user_address"))
    })
    private final UserInfo userInfo;

    public RunningInfo() {
        this.userInfo = null;
    }

    public RunningInfo(final String name, final String address) {
        this.userInfo = new UserInfo(name, address);
    }


    @JsonCreator
    public RunningInfo(
            @JsonProperty("runningId") String runningId,
            @JsonProperty("totalRunningTime") String totalRunningTime,
            @JsonProperty("heartRate") String heartRate,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("latitude") String latitude,
            @JsonProperty("longitude") String longitude,
            @JsonProperty("runningDistance") String runningDistance,
            @JsonProperty("userInfo") UserInfo userInfo) {

        this.runningId = runningId;
        this.totalRunningTime = Double.parseDouble(totalRunningTime);
        this.heartRate = getRandomHeartRate(60, 200);

        if(this.heartRate > 120) {
            this.healthWarningLevel = HealthWarningLevel.HIGH;
        } else if(this.heartRate > 75) {
            this.healthWarningLevel = HealthWarningLevel.NORMAL;
        } else if(this.heartRate >= 60) {
            this.healthWarningLevel = HealthWarningLevel.LOW;
        } else {

        }

        this.timestamp = new Date();
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDistance);

        this.userInfo = userInfo;
    }

    public RunningInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserName() {
        return this.userInfo == null ? null : userInfo.getUsername();
    }

    public String getUserAddress() {
        return this.userInfo == null ? null : userInfo.getAddress();
    }

    private int getRandomHeartRate(int min, int max) {
        Random r = new Random();
        return min + r.nextInt(max - min + 1);
    }
}
