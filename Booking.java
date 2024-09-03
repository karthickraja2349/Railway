import java.util.Map;
import java.util.List;   
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Booking {
    private short berthLimit;
    private short racLimit;
    private short waitingLimit;

    private static short upperSeatNumber = 1; 
    private static short middleSeatNumber = 2;
    private static short lowerSeatNumber = 3;

    private ArrayList<Passenger> confirmedList = new ArrayList<>();
    private ArrayList<Passenger> upperList = new ArrayList<>();
    private ArrayList<Passenger> middleList = new ArrayList<>();
    private ArrayList<Passenger> lowerList = new ArrayList<>();

    private Queue<Passenger> racQueue = new LinkedList<>();
    private Queue<Passenger> waitingQueue = new LinkedList<>();
    
    public Booking(){
         this((short)(6),(short)(1),(short)(1));
    }
    
    private Booking(short berthLimit, short racLimit, short waitingLimit){
          setBerthLimit(berthLimit);
          setRacLimit(racLimit);
          setWaitingLimit(waitingLimit);
    }   

    private void setBerthLimit(short berthLimit){
        this.berthLimit = (short) (berthLimit/3);
    }
    
    public short getBerthLimit(){
        return berthLimit;
    }

    private void setRacLimit(short racLimit){
        this.racLimit = racLimit;
    }
    public short getRacLimit(){
        return racLimit;
    }
     
    private void setWaitingLimit(short waitingLimit ){
        this.waitingLimit = waitingLimit;
    }
    public short getWaitingLimit(){
        return waitingLimit;
    }
    

    public void setLimits(short berthLimit, short racLimit, short waitingLimit){
        Booking booking = new Booking(berthLimit,racLimit,waitingLimit);
    }
        
    public void ticketBooking(Passenger passenger){
        if(upperList.size()==getBerthLimit() && middleList.size()==getBerthLimit() && lowerList.size()==getBerthLimit()){
             if(updateRacQueue(passenger)){
                System.out.println("Added to Rac List,Your ticketId is:"+ passenger.getId());
             }
             else if(updateWaitingQueue(passenger)){
                System.out.println("Added to Waiting List,Your ticketId is:"+ passenger.getId());
             }
             else{
                passenger.setId((passenger.getId()-1));
                System.out.println("Ticket not Available");
             }
        }    
        else if(checkAvailability(passenger)){
             System.out.println("Ticket confirmed, your ticketId is:"+passenger.getId()+" SeatNumber is :"+ passenger.getSeatNumber());  
             passenger.setTicketType("berth");
             confirmedList.add(passenger); 
        }
        else{
            System.out.println(passenger.getPreference()+" is not available");
            passenger.setId(passenger.getId()-1);
            availableList();
        }
    } 
    
    public void availableList() {
       System.out.println("The Available seats are:");
       System.out.println("Upper Birth:"+(getBerthLimit()-upperList.size()));
       System.out.println("Middle Birth:"+(getBerthLimit()-middleList.size()));
       System.out.println("Lower Birth:"+(getBerthLimit()-lowerList.size()));
    }


    private boolean checkAvailability(Passenger passenger){
        if(passenger.getPreference()=='U'||passenger.getPreference()=='u'){
            if(checkSeatAvailability(passenger , upperList)){
                return true;
            }
        }
        else if(passenger.getPreference()=='M'||passenger.getPreference()=='m'){
            if(checkSeatAvailability(passenger,middleList)){
                return true;
            }
        }
        else{
            if(checkSeatAvailability(passenger,lowerList)){
                return true;
            }
        }
        return true;
    }

    private boolean checkSeatAvailability(Passenger passenger,List<Passenger> list){
        Map<Short,Character> map = Cancel.getSeatNumberWithBerth();
        if(list.size()<getBerthLimit()){
            if(!map.isEmpty()){
                getSeatDetails(map,passenger);
            }
            else{
                if(passenger.getPreference()=='U'|| passenger.getPreference()=='u'){
                      passenger.setSeatNumber(upperSeatNumber);
                      upperSeatNumber+=3;
                }
                else if(passenger.getPreference()=='M'|| passenger.getPreference()=='m'){
                    passenger.setSeatNumber(middleSeatNumber);
                    middleSeatNumber+=3;
                }      
                else{
                    passenger.setSeatNumber(lowerSeatNumber);
                    lowerSeatNumber+=3;
                }
            }
            list.add(passenger);
            return true;
        }
        return false;
    }

    private void getSeatDetails(Map<Short,Character> map, Passenger passenger){
        short seatNumber = checkForPreference(map,passenger);
        passenger.setSeatNumber(seatNumber);
        map.remove(seatNumber);
    }
    
    private short checkForPreference(Map<Short,Character> map, Passenger passenger){
        short seatNumber =0;
        for(Map.Entry<Short,Character> entry : map.entrySet()){
            if(passenger.getPreference()==(char)entry.getValue()){
                seatNumber = (short)entry.getKey();
                break;
            }
        }
        return seatNumber;
    }
    private boolean updateWaitingQueue(Passenger passenger) {
        if(waitingQueue.size() < waitingLimit){
            passenger.setTicketType("waiting list");
            waitingQueue.add(passenger);
            return true;
        }
        return false;
    }
    private boolean updateRacQueue(Passenger passenger) {
        if(racQueue.size()<getRacLimit()){
            passenger.setTicketType("rac");
            racQueue.add(passenger);
            return true;
        }
        return false;
    }

    public void displayConfirmedList(){
        System.out.println("-----------------------------------------");
        for(Passenger passenger : confirmedList){
            System.out.println(passenger);
        } 
        System.out.println("-----------------------------------------");
    }

    public void displayRacList(){
        System.out.println("-----------------------------------------");
        for(Passenger passenger : racQueue){
            System.out.println(passenger);
        }
        System.out.println("-----------------------------------------");
    }

    public void displayWaitingList(){
        System.out.println("-----------------------------------------");
        for(Passenger passenger : waitingQueue){
            System.out.println(passenger);
        }
        System.out.println("-----------------------------------------");
    }

    public ArrayList<Passenger> getConfirmedList(){
        return confirmedList;
    }

    public Queue<Passenger> getRacQueue(){
        return racQueue;
    }

    public Queue<Passenger> getWaitingQueue(){
        return waitingQueue;
    }

    public ArrayList<Passenger> getUpperList(){
        return upperList;
    }

    public ArrayList<Passenger> getMiddleList(){
        return middleList;
    }

    public ArrayList<Passenger> getLowerList(){
        return lowerList;
    }

}
