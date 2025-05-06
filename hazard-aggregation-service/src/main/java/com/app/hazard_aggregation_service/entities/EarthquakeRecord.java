package com.app.hazard_aggregation_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.locationtech.jts.geom.Point;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "earthquakes")
public class EarthquakeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "event_id", unique = true, nullable = false)
    private String eventId; // Unique event ID from the earthquake data source

    @Column(name = "magnitude", nullable = false)
    private Double magnitude; // Magnitude of the earthquake

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    @Column(name = "depth")
    private Double depth; // Depth of the earthquake in km

    @Column(name = "timestamp")
    private Long timestamp; // Timestamp when the earthquake occurred


    @Column(name = "region")
    private String region; // Region of the earthquake (e.g., "Pacific Ring of Fire")

    @Column(name = "url")
    private String url; // URL for detailed information on the earthquake

    @Column(name = "severity_level")
    private String severityLevel; // Severity level based on magnitude and other factors

    @Column(name = "alerted")
    private Boolean alerted = false; // Whether the earthquake has been alerted

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // Timestamp when the earthquake record was created

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt; // Timestamp when the earthquake record was last updated

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }



    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }



    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }



    public Boolean getAlerted() {
        return alerted;
    }

    public void setAlerted(Boolean alerted) {
        this.alerted = alerted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
