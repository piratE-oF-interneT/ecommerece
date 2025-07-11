package com.app.community_report_service.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class ReportRequest {


        private String title;

        private String description;

        private String category;

        private LocalDateTime reportedAt;

        private String location;

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }


        public LocalDateTime getReportedAt() {
                return reportedAt;
        }

        public void setReportedAt(LocalDateTime reportedAt) {
                this.reportedAt = reportedAt;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }
}
