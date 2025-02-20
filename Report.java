package bankingApp_v3.classes;

public class Report {
	
    private int reportID;
    private String reportType;

    public Report(int reportID, String reportType) {
        this.reportID = reportID;
        this.reportType = reportType;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}