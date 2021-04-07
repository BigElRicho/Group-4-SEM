public interface TechnicianInterface{

    public int technicianLevel = 0;

    public String escalateIssue();

    public String deescalateIssue();

    public String openIssue();

    public String closeIssueWithoutResolution();

    public String closeAndResolveIssue();
    
    
}