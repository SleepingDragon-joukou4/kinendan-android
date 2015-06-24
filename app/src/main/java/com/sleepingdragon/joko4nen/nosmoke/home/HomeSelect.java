package com.sleepingdragon.joko4nen.nosmoke.home;

import com.google.gson.annotations.Expose;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;


public class HomeSelect {

    @Expose
    private String UserId;
    @Expose
    private String Name;
    @Expose
    private String TeamName;
    @Expose
    private String TeamStartDate;
    @Expose
    private String TeamFinalDate;
    @Expose
    private String Deadline;
    @Expose
    private String SmokinghistoryPerformanceNumberTeamSum;
    @Expose
    private String CigaretteNumber;
    @Expose
    private String TeamCigaretteNumber;

    /**
     *
     * @return
     * The UserId
     */
    public String getUserId() {
        return UserId;
    }

    /**
     *
     * @param UserId
     * The UserId
     */
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    /**
     *
     * @return
     * The Name
     */
    public String getName() {
        return Name;
    }

    /**
     *
     * @param Name
     * The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     *
     * @return
     * The TeamName
     */
    public String getTeamName() {
        return TeamName;
    }

    /**
     *
     * @param TeamName
     * The TeamName
     */
    public void setTeamName(String TeamName) {
        this.TeamName = TeamName;
    }

    /**
     *
     * @return
     * The TeamStartDate
     */
    public String getTeamStartDate() {
        return TeamStartDate;
    }

    /**
     *
     * @param TeamStartDate
     * The TeamStartDate
     */
    public void setTeamStartDate(String TeamStartDate) {
        this.TeamStartDate = TeamStartDate;
    }

    /**
     *
     * @return
     * The TeamFinalDate
     */
    public String getTeamFinalDate() {
        return TeamFinalDate;
    }

    /**
     *
     * @param TeamFinalDate
     * The TeamFinalDate
     */
    public void setTeamFinalDate(String TeamFinalDate) {
        this.TeamFinalDate = TeamFinalDate;
    }

    /**
     *
     * @return
     * The Deadline
     */
    public String getDeadline() {
        return Deadline;
    }

    /**
     *
     * @param Deadline
     * The Deadline
     */
    public void setDeadline(String Deadline) {
        this.Deadline = Deadline;
    }

    /**
     *
     * @return
     * The SmokinghistoryPerformanceNumberTeamSum
     */
    public String getSmokinghistoryPerformanceNumberTeamSum() {
        return SmokinghistoryPerformanceNumberTeamSum;
    }

    /**
     *
     * @param SmokinghistoryPerformanceNumberTeamSum
     * The SmokinghistoryPerformanceNumberTeamSum
     */
    public void setSmokinghistoryPerformanceNumberTeamSum(String SmokinghistoryPerformanceNumberTeamSum) {
        this.SmokinghistoryPerformanceNumberTeamSum = SmokinghistoryPerformanceNumberTeamSum;
    }

    /**
     *
     * @return
     * The CigaretteNumber
     */
    public String getCigaretteNumber() {
        return CigaretteNumber;
    }

    /**
     *
     * @param CigaretteNumber
     * The CigaretteNumber
     */
    public void setCigaretteNumber(String CigaretteNumber) {
        this.CigaretteNumber = CigaretteNumber;
    }

    /**
     *
     * @return
     * The TeamCigaretteNumber
     */
    public String getTeamCigaretteNumber() {
        return TeamCigaretteNumber;
    }

    /**
     *
     * @param TeamCigaretteNumber
     * The TeamCigaretteNumber
     */
    public void setTeamCigaretteNumber(String TeamCigaretteNumber) {
        this.TeamCigaretteNumber = TeamCigaretteNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}