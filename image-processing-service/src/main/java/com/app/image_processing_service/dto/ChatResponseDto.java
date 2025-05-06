package com.app.image_processing_service.dto;

import java.util.List;

public class ChatResponseDto {

    private String botReply;
    private String severityLevel;
    private List<String> immediateActions;
    private String helpCenterContact;

    public ChatResponseDto(String botReply, String severityLevel, List<String> immediateActions, String helpCenterContact) {
        this.botReply = botReply;
        this.severityLevel = severityLevel;
        this.immediateActions = immediateActions;
        this.helpCenterContact = helpCenterContact;
    }

    public String getBotReply() {
        return botReply;
    }

    public void setBotReply(String botReply) {
        this.botReply = botReply;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public List<String> getImmediateActions() {
        return immediateActions;
    }

    public void setImmediateActions(List<String> immediateActions) {
        this.immediateActions = immediateActions;
    }

    public String getHelpCenterContact() {
        return helpCenterContact;
    }

    public void setHelpCenterContact(String helpCenterContact) {
        this.helpCenterContact = helpCenterContact;
    }
}
