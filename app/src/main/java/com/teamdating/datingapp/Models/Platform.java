package com.teamdating.datingapp.Models;

import java.util.Date;

/**
 * Created by Janine on 19-4-2017.
 */

public class Platform {
    private int id;
    private Date created;
    private Date updated;
    private String name;
    private String hostname;
    private String aliasName;
    private String trackthisWebsiteIds;
    private String trackthisCountryIds;
    private String trackthisPaymentTypeIds;
    private String trackthisDeviceIds;
    private String messagecentralPlatformId;
    private String groupIds;
    private int googleAnalyticsId;
    private Date firstPaymentDate;
    private Date firstSignupDate;
    private int messagecentralId;
    private Messagecentral messagecentral;

    public String getTrackthisWebsiteIds() {
        return trackthisWebsiteIds;
    }

    public void setTrackthisWebsiteIds(String trackthisWebsiteIds) {
        this.trackthisWebsiteIds = trackthisWebsiteIds;
    }

    public String getMessagecentralPlatformId() {
        return messagecentralPlatformId;
    }

    public void setMessagecentralPlatformId(String messagecentralPlatformId) {
        this.messagecentralPlatformId = messagecentralPlatformId;
    }

    public String getTrackthisDeviceIds() {
        return trackthisDeviceIds;
    }

    public void setTrackthisDeviceIds(String trackthisDeviceIds) {
        this.trackthisDeviceIds = trackthisDeviceIds;
    }

    public String getTrackthisCountryIds() {
        return trackthisCountryIds;
    }

    public void setTrackthisCountryIds(String trackthisCountryIds) {
        this.trackthisCountryIds = trackthisCountryIds;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getTrackthisPaymentTypeIds() {
        return trackthisPaymentTypeIds;
    }

    public void setTrackthisPaymentTypeIds(String trackthisPaymentTypeIds) {
        this.trackthisPaymentTypeIds = trackthisPaymentTypeIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getMessagecentralId() {
        return messagecentralId;
    }

    public void setMessagecentralId(int messagecentralId) {
        this.messagecentralId = messagecentralId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(Date firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Messagecentral getMessagecentral() {
        return messagecentral;
    }

    public void setMessagecentral(Messagecentral messagecentral) {
        this.messagecentral = messagecentral;
    }

    public Date getFirstSignupDate() {
        return firstSignupDate;
    }

    public void setFirstSignupDate(Date firstSignupDate) {
        this.firstSignupDate = firstSignupDate;
    }

    public int getGoogleAnalyticsId() {
        return googleAnalyticsId;
    }

    public void setGoogleAnalyticsId(int googleAnalyticsId) {
        this.googleAnalyticsId = googleAnalyticsId;
    }

    @Override
    public String toString() {
        return "ClassPojo [trackthisWebsiteIds = " + trackthisWebsiteIds + ", " +
                "messagecentralPlatformId = " + messagecentralPlatformId + ", " +
                "trackthisDeviceIds = " + trackthisDeviceIds + ", " +
                "trackthisCountryIds = " + trackthisCountryIds + ", " +
                "hostname = " + hostname + ", trackthisPaymentTypeIds = " + trackthisPaymentTypeIds + ", " +
                "id = " + id + ", aliasName = " + aliasName + ", groupIds = " + groupIds + ", " +
                "created = " + created + ", updated = " + updated + ", " +
                "messagecentralId = " + messagecentralId + ", name = " + name + ", " +
                "firstPaymentDate = " + firstPaymentDate + ", messagecentral = " + messagecentral + ", " +
                "firstSignupDate = " + firstSignupDate + ", googleAnalyticsId = " + googleAnalyticsId + "]";
    }

}
