package com.surya.intentserviceapp.dto;

import java.util.Date;

/**
 * Created by suryak on 7/10/15.
 */
public class FdnDTO {
    private long id;
    private String fdnId;
    private String icon;
    private String contentTitle;
    private String contentText;
    private int androidPriority;
    private int isDisplayed;
    private int displayCount;
    private int isConsumedByUser;
    private String userAction;
    private Date userActionTimestamp;
    private Date createdTimestamp;
    private Date modifiedTimestamp;
    private Date lastDisplayedTimestamp;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("FdnDTO [ ")
                .append("id = ").append(id).append(" , ")
                .append("fdnId = ").append(fdnId).append(" , ")
                .append("icon = ").append(icon).append(" , ")
                .append("contentTitle = ").append(contentTitle).append(" , ")
                .append("contentText = ").append(contentText).append(" , ")
                .append("androidPriority = ").append(androidPriority).append(" , ")
                .append("isDisplayed = ").append(isDisplayed).append(" , ")
                .append("displayCount = ").append(displayCount).append(" , ")
                .append("isConsumedByUser = ").append(isConsumedByUser).append(" , ")
                .append("userAction = ").append(userAction).append(" , ")
                .append("userActionTimestamp = ").append(userActionTimestamp).append(" , ")
                .append("createdTimestamp = ").append(createdTimestamp).append(" , ")
                .append("modifiedTimestamp = ").append(modifiedTimestamp).append(" , ")
                .append("lastDisplayedTimestamp = ").append(lastDisplayedTimestamp)
                .append(" ]");
        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFdnId() {
        return fdnId;
    }

    public void setFdnId(String fdnId) {
        this.fdnId = fdnId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public int getAndroidPriority() {
        return androidPriority;
    }

    public void setAndroidPriority(int androidPriority) {
        this.androidPriority = androidPriority;
    }

    public int getIsDisplayed() {
        return isDisplayed;
    }

    public void setIsDisplayed(int isDisplayed) {
        this.isDisplayed = isDisplayed;
    }

    public int getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(int displayCount) {
        this.displayCount = displayCount;
    }

    public int getIsConsumedByUser() {
        return isConsumedByUser;
    }

    public void setIsConsumedByUser(int isConsumedByUser) {
        this.isConsumedByUser = isConsumedByUser;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public Date getUserActionTimestamp() {
        return userActionTimestamp;
    }

    public long getUserActionTimeAsLong() {
        return userActionTimestamp == null ? 0 : userActionTimestamp.getTime();
    }

    public void setUserActionTimestamp(Date userActionTimestamp) {
        this.userActionTimestamp = userActionTimestamp;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public long getCreatedTimeAsLong() {
        return createdTimestamp == null ? 0 : createdTimestamp.getTime();
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Date getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public long getModifiedTimeAsLong() {
        return modifiedTimestamp == null ? 0 : modifiedTimestamp.getTime();
    }

    public void setModifiedTimestamp(Date modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    public Date getLastDisplayedTimestamp() {
        return lastDisplayedTimestamp;
    }

    public long getLastDisplayedTimeAsLong() {
        return lastDisplayedTimestamp == null ? 0 : lastDisplayedTimestamp.getTime();
    }

    public void setLastDisplayedTimestamp(Date lastDisplayedTimestamp) {
        this.lastDisplayedTimestamp = lastDisplayedTimestamp;
    }
}
