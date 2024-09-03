import java.util.Scanner;

public class RailwayReservation extends Booking {
      
      private Scanner input = new Scanner(System.in); 
     
      public static void main(String[]args){
              RailwayReservation railwayReservation = new RailwayReservation();
              railwayReservation.start(); 
      }

      public void start(){
          while(true){
                displayMenu();
                short choice = input.nextShort();
                switch(choice){
                        case 1:
                            adminMenu();
                            break;
                        case 2:
                            userMenu();
                            break;
                        case 3:
                            exitMenu();
                        default:
                             System.out.println("Please Enter a valid choice, you Entered:"+choice);             
                 }
           }
        }

        private void admin(){
               short choice = input.nextShort();
               switch(choice){
                   case 1:
                     addLimit();
                     break;
                   case 2:
                    displayConfirmedList();  
                     break;
                   case 3: 
                     displayRacList();
                     break;  
                   case 4:
                    displayWaitingList();
                     break;
                   case 5:
                     return;
                   default:
                     System.out.println("Please Enter a valid choice, you Entered:"+choice);     

               }
        }
      
      private void user(){
                    short choice = input.nextShort();
                    switch(choice){
                        case 1:
                           bookTicket();
                           break;
                        case 2:
                           cancelTicket();
                           break; 
                        case 3:
                           availableList();
                           break;
                        case 4:
                          return;
                        default:
                           System.out.println("Please Enter a valid choice, you Entered:"+choice);               
                    
               }
       }

       private void displayMenu(){
            System.out.println("-----------------------------------------");
            System.out.println("Welcome to indian Railways");
            System.out.println("It was a demo application");
            System.out.println("------------------------------------------");
            System.out.println("|  Option     |        MainMenu          |");
            System.out.println("|-------------+------------------------- |"); 
            System.out.println("|    1        |     Admin                |");
            System.out.println("|    2        |     user                 |");
            System.out.println("|    3        |     Exit                 |");
            System.out.println("------------------------------------------");
            System.out.println("Enter your choice:");

       }

       private void adminMenu(){
             System.out.println("------------------------------------------");
             System.out.println("|  Option     |        AdminMenu          |");  
             System.out.println("|-------------+-------------------------- |");
             System.out.println("|    1        |     Add limit             |");
             System.out.println("|    2        |  Display confirmList      |");
             System.out.println("|    3        |  Display Rac details      |");
             System.out.println("|    4        |  Display WaitingList      |");
             System.out.println("|    5        |     Back                  |");
             System.out.println("-------------------------------------------");
             System.out.println("Enter your choice:");
             admin();

       }
       
       private void userMenu(){
             System.out.println("------------------------------------------");
             System.out.println("|  Option     |        UserMenu          |");     
             System.out.println("|-------------+------------------------- |");    
             System.out.println("|    1        |     Booking              |");
             System.out.println("|    2        |     Cancelling           |");
             System.out.println("|    3        |     DisplayDeatils       |");
             System.out.println("|    4        |     Back                 |");
             System.out.println("------------------------------------------");
             System.out.println("Enter your choice:");
             user();
             
        }   

        private void addLimit(){
            Admin admin = new Admin();    
            input.nextLine();
            System.out.println("Enter berth limit:");
            short berthLimit = input.nextShort();
            System.out.println("Enter rac limit:");
            short racLimit = input.nextShort();
            System.out.println("Enter waiting list limit:");
            short waitingLimit = input.nextShort();
            admin.addLimits(berthLimit,racLimit,waitingLimit);   

        }
        
        private void bookTicket(){
             input.nextLine();   
             System.out.println("Enter your Name:");
             String name = input.nextLine();
             System.out.println("Enter your Age:");
             short age = input.nextShort();
             System.out.println("Enter your SeatPreference:");
             char preference = input.next().charAt(0);
             if(preference=='U'||preference=='u'||preference=='M'||preference=='m'||preference=='L'||preference=='l'){
                Passenger passenger = new Passenger(name,age,preference);
                ticketBooking(passenger);
                return;
             }    
             else{
                System.out.println("Preference is wrong!");
             }
        }

        private void cancelTicket(){
             Cancel cancel = new Cancel();   
             System.out.println("Enter your ticketType");
             String ticketType = input.nextLine().toLowerCase();
             System.out.println("Ente your TicketId:");
             int ticketId = input.nextInt();
             if(ticketType=="rac" || ticketType=="berth" || ticketType == "waiting list"){
               cancel.ticketCancelling(ticketId,ticketType);
             }  
             else{
                System.out.println("Invalid ticketType");
             }
        }

        private void exitMenu(){
             System.out.println("------------------------------------------");   
             System.out.println("|  Option     |        ExitMenu          |");  
             System.out.println("|-------------+--------------------------|");
             System.out.println("|    1        |   Are u sure to Exit     |");
             System.out.println("|    2        |       Stay here          |");
             System.out.println("------------------------------------------");
             System.out.println("Enter your choice:");
             short choice = input.nextShort();
             switch(choice){
                case 1:
                   System.out.println("Thank you for visiting us!");
                   System.exit(0);
                case 2:
                   return;   
             }
        }
             

}
