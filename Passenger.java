public class Passenger{
      private static int idProvider =0;
      private String name;
      private short age;
      private char preference;
      private short seatNumber;
      private int id;
      private String ticketType;
      
      public Passenger(String name, short age, char preference){
            this.id = ++idProvider;
            this.name = name;
            this.age = age;
            this.preference = preference;
       }
       
       public void setId(int id){
            Passenger.idProvider = id;
       }
       
       public int getId(){
             return id;
       }
       
       public String getName(){
             return name;
       }
       
       public short getAge(){
             return age;
       }
       
       public void setPreference(char preference){
            this.preference = preference;
       }
       public char getPreference(){
             return preference;
       }
       
       public short getSeatNumber(){
             return seatNumber;
       }
       
       public void setSeatNumber(short seatNumber){
             this.seatNumber = seatNumber;
        }
        
        public String getTicketType(){
              return   ticketType;
        }
        
        public void setTicketType(String ticketType){
              this.ticketType = ticketType;
        }
        
        public String toString(){
             StringBuilder sb = new StringBuilder();
             sb.append("-------------------------------------------------------\n");
             sb.append(String.format("|%-20s : %-16s|\n", "Passenger name",  getName()));
             sb.append(String.format("|%-20s : %-16s|\n", "Passenger Age",  getAge()));     
             sb.append(String.format("|%-20s : %-16s|\n", "Passenger preference",  getPreference())); 
             sb.append(String.format("|%-20s : %-16s|\n", "Passenger seatNumber",  getSeatNumber())); 
             sb.append(String.format("|%-20s : %-16s|\n", "Passenger id",  getId())); 
             sb.append(String.format("|%-20s : %-16s|\n", "Passenger ticketType",  getTicketType()));
             sb.append("-------------------------------------------------------\n");
             return sb.toString();
       }
 }             
                                                        
           
