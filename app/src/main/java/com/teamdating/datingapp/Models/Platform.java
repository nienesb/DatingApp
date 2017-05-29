package com.teamdating.datingapp.Models;

import java.util.Date;

/**
 * Created by Janine on 19-4-2017.
 */

public class Platform {
    private Integer id;
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
    private Integer googleAnalyticsId;
    private Date firstPaymentDate;
    private Date firstSignupDate;
    private Integer messagecentralId;
    private Messagecentral messagecentral;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getTrackthisWebsiteIds() {
        return trackthisWebsiteIds;
    }

    public void setTrackthisWebsiteIds(String trackthisWebsiteIds) {
        this.trackthisWebsiteIds = trackthisWebsiteIds;
    }

    public String getTrackthisCountryIds() {
        return trackthisCountryIds;
    }

    public void setTrackthisCountryIds(String trackthisCountryIds) {
        this.trackthisCountryIds = trackthisCountryIds;
    }

    public String getTrackthisPaymentTypeIds() {
        return trackthisPaymentTypeIds;
    }

    public void setTrackthisPaymentTypeIds(String trackthisPaymentTypeIds) {
        this.trackthisPaymentTypeIds = trackthisPaymentTypeIds;
    }

    public String getTrackthisDeviceIds() {
        return trackthisDeviceIds;
    }

    public void setTrackthisDeviceIds(String trackthisDeviceIds) {
        this.trackthisDeviceIds = trackthisDeviceIds;
    }

    public String getMessagecentralPlatformId() {
        return messagecentralPlatformId;
    }

    public void setMessagecentralPlatformId(String messagecentralPlatformId) {
        this.messagecentralPlatformId = messagecentralPlatformId;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public int getGoogleAnalyticsId() {
        return googleAnalyticsId;
    }

    public void setGoogleAnalyticsId(int googleAnalyticsId) {
        this.googleAnalyticsId = googleAnalyticsId;
    }

    public Date getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(Date firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Date getFirstSignupDate() {
        return firstSignupDate;
    }

    public void setFirstSignupDate(Date firstSignupDate) {
        this.firstSignupDate = firstSignupDate;
    }

    public int getMessagecentralId() {
        return messagecentralId;
    }

    public void setMessagecentralId(int messagecentralId) {
        this.messagecentralId = messagecentralId;
    }

    public Messagecentral getMessagecentral() {
        return messagecentral;
    }

    public void setMessagecentral(Messagecentral messagecentral) {
        this.messagecentral = messagecentral;
    }
}
