
package model;

import java.sql.Date;
import java.sql.Time;

public class Schedule {
    private int scheduleId;
    private String doctorId;
    private String doctorName;
    private Date channelingDate;
    private Time channelingTime;
    private int patientLimit;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getChannelingDate() {
        return channelingDate;
    }

    public void setChannelingDate(Date channelingDate) {
        this.channelingDate = channelingDate;
    }

    public Time getChannelingTime() {
        return channelingTime;
    }

    public void setChannelingTime(Time channelingTime) {
        this.channelingTime = channelingTime;
    }

    public int getPatientLimit() {
        return patientLimit;
    }

    public void setPatientLimit(int patientLimit) {
        this.patientLimit = patientLimit;
    }   
}
