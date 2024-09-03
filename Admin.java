
public class Admin{
    private int id;
    private String name;
    private String userName;
    private String password;
    
    private final String main_Admin_name = "root";
    private final String main_Admin_User_Name = "MainAdmin";
    private final String main_Admin_Password = "Main@123";
    
    public Admin(){

    }
    
    public Admin(int id , String name , String userName , String password){
         this.id = id ;
         this.name = name ;
         this.userName = userName;
         this.password = password;
    }
    
    public Admin(String name , String userName , String password){
       this.name = name;
       this.userName = userName;
       this.password  = password;
    }
    
      public String getRootName () {
	   return main_Admin_name;
      }

     public String getRootUserName (){
	  return main_Admin_User_Name;
     }

     public String getRootPassword (){
        return main_Admin_Password;
    }
   
    public int getId (){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public String getPassword(){
        return password;
    }

    public void addLimits(short berthLimit, short racLimit, short waitingLimit){
       Booking booking = new Booking();
        booking.setLimits(berthLimit,racLimit,waitingLimit);
    }
    
    public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("----------------------------------------------------\n");
       sb.append(String.format("| %-20s : %-25s|\n", "Admin Id is ",getId()));
       sb.append(String.format("| %-20s : %-25s|\n", "Admin name is ", getName()));
       sb.append(String.format("| %-20s : %-25s|\n", "Admin userName is", getUserName()));
       sb.append(String.format("| %-20s : %-25s|\n", "Admin password is","********"));
       sb.append("-----------------------------------------------------\n");
       return sb.toString();
   }
   
}       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
