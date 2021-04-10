public class TechnicianLevelOne implements TechnicianInterface{

    //Attributes
    int numberOfTicketsCurrentlyAssigned = 0;
    int technicianLevel = 1;
    String userName = "";
    String password = "";

    //Constructors
    public TechnicianLevelOne(int startingTicktes, int level, String userName, String password){
        this.numberOfTicketsCurrentlyAssigned = startingTicktes;
        this.technicianLevel = level;
        this.userName = userName;
        this.password = password;
    }

    //Methods
    @Override
    public String escalateIssue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deescalateIssue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String openIssue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String closeIssueWithoutResolution() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String closeAndResolveIssue() {
        // TODO Auto-generated method stub
        return null;
    }

    public String changeUserName(){
        // TODO Auto-generated method stub
        return null;
    }

    public String changePassword(String newPassword){
        
        return null;
    }

    @Override
    public String changeUsername() {
        // TODO Auto-generated method stub
        return null;
    }

}