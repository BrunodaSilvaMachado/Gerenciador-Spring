package br.com.adaca.util;

public class ErrorDetails {

    private Long timestamp;
    private Long status;
    private String title;
    private String message;

    public ErrorDetails(Long timestamp, Long status, String title, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.title = title;
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}