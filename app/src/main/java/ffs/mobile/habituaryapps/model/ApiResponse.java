package ffs.mobile.habituaryapps.model;


import java.util.List;

public class ApiResponse {
    private String status;
    private List<YourGoals> goals;

    public String getStatus() { return status; }
    public List<YourGoals> getGoals() { return goals; }
}
