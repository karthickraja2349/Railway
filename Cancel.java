import java.util.Map;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;
public class Cancel extends Booking{
    private static char preferenceTracer = '\0';
    private static short seatNumber = 0;

    private static Map<Short,Character> seatNumberWithBerth = new HashMap<>();

    public static Map<Short,Character> getSeatNumberWithBerth(){
        return seatNumberWithBerth;
    }

    public String ticketCancelling(int id,String ticketType){
        if(ticketType=="berth"){
            List<Passenger> list = getConfirmedList();
            if(check(id,list)){
                return "Cancellation successfull";
            }
        }
        else if(ticketType == "rac"){
            Queue<Passenger> queue = getRacQueue();
            if(check(id,queue)){
                return "Cancellation successfull";
            }
        }
        else {
            Queue<Passenger> queue = getWaitingQueue();
            if(check(id,queue)){
                return "Cancellation successfull";
            }
        }
        return "invalid id / berth type";
    }    
    private boolean check(int id,List<Passenger>list){
        for(Passenger passenger : list){
            if(passenger.getId()==id){
                cancel(passenger);
                return true;
            }
        }
        return false;
    }
    
    private boolean check(int id, Queue<Passenger> queue){     
        for(Passenger passenger : queue){
            if(passenger.getId()==id){
                cancel(passenger);
                return true;
            }
        }
        return false;
    }

    private void cancel(Passenger passenger){
        if(passenger.getTicketType()=="berth"){
            preferenceTracer = passenger.getPreference();
            seatNumber = passenger.getSeatNumber();

            seatNumberWithBerth.put(seatNumber,preferenceTracer);
            deleteFromAllLists(passenger);
            addRactoBerth(getRacQueue().poll());
            addWaitingtoRac(getWaitingQueue().poll());
        }
        else if(passenger.getTicketType()=="rac"){
            getRacQueue().remove(passenger);
            addWaitingtoRac(getWaitingQueue().poll());
        }
        else{
            getWaitingQueue().remove(passenger);
        }
    }

    private void deleteFromAllLists(Passenger passenger){
        getConfirmedList().remove(passenger);
        getUpperList().remove(passenger);
        getMiddleList().remove(passenger);
        getLowerList().remove(passenger);
    }

    private void addWaitingtoRac(Passenger passenger){
        if(passenger!=null){
            passenger.setTicketType("rac");
            getRacQueue().add(passenger);
        }
    }

    private void addRactoBerth(Passenger passenger){
        if(passenger!=null){
            passenger.setPreference(preferenceTracer);
            passenger.setSeatNumber(seatNumber);
            passenger.setTicketType("berth");

            if(preferenceTracer=='u'||preferenceTracer=='U'){
                getUpperList().add(passenger);
            }
            else if(preferenceTracer=='l' || preferenceTracer =='L'){
                getLowerList().add(passenger);
            }
            else{
                getMiddleList().add(passenger);
            }
            getConfirmedList().add(passenger);
            seatNumberWithBerth.remove(seatNumber);
            preferenceTracer='\0';
            seatNumber=0;
        }
     }
  }
