/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightBook;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author 91984
 */
public class User {
    //Function To Book The Tickets For The Admin Which We Have Called As Case1 From Main Function
    public static void book(Admin currentFlight, int tickets,int passengerID)
    {
       String passengerDetail = "";
       //All The User Details
       passengerDetail = "Passenger ID " + passengerID + "    " + " Number of Tickets Booked " 
                            + tickets + "    " + "Total cost " + currentFlight.price * tickets;
       //To Write Passenger Details In A Text File("PassengerInfo.txt)
       try
        {
        String s=" ---PASSENGER DETAILS--- "+" User Name(ID): "+passengerID+" No Of Tickets Booked: "+tickets+" Total Cost: "+currentFlight.price*tickets;
        File newTextFile=new File("C:/Users/91984/Desktop/PassengerInfo.txt");
        FileWriter fw=new FileWriter(newTextFile);
        fw.write(s);
        fw.close();
        }
        catch(IOException iox)
        {
          iox.printStackTrace();
        }
       //Adding The User Details For The Particular Admin Object
       currentFlight.addPassengerDetails(passengerDetail,tickets,passengerID);
       currentFlight.flightSummary();
       currentFlight.printDetails();
    }
    //Function To Cancel The Tickets By A Particular Passenger Which We Have Called As Case2 From Main Function
    public static void cancel(Admin currentFlight, int passengerID)
    {
        currentFlight.cancelTicket(passengerID);
        currentFlight.flightSummary();
        currentFlight.printDetails();
    }
    //Function To Print The Admin Details Which We Have Called As Case3 From Main Function
    public static void print(Admin f)
    {
        f.printDetails();
    }
    public static void main(String[] args)
    {
        Scanner obj=new Scanner(System.in);//To Get Input From The User
        //Created 100 flights Initially Asuming Admin Database
        ArrayList<Admin> flights = new ArrayList<Admin>();
        for(int i=0;i<10;i++)//Currently 100 Flights Available
        {
            flights.add(new Admin());
        }
        //Unique Id For Every User Before Booking The Seat
        int passengerID = 1;
        // Asking The User Whether He Have To Book A Seat (Or) Cancel The Admin Ticket (Or) Print The Details(In Text Files)-->
        while(true)
        {
        System.out.println("Enter 1 (or) 2 (or) (3) As Per Your(User's) Choice");
        System.out.println("1.Book A Flight Seat\n"+"2.Cancel The Seat Booked\n"+"3.Print The Details");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice)
        {
            //For Booking The Ticket
            case 1:
            {
                System.out.println("Enter Flight Name(ID):");
                int fid = sc.nextInt();
                //Checking Whether Admin Name(ID) Is Valid Or Not--> 
                if(fid > flights.size())
                {
                    System.out.println("Fight Name(ID) Is Not Valid!!!!");
                    break;
                }
                //Search For The Required Admin For The User
                Admin currentFlight = null;
                for(Admin f : flights)
                {
                    if(f.flightID == fid)
                    {
                        currentFlight = f;//currentflight->TargetFlight
                        f.flightSummary();
                        break;
                    }
                }
                System.out.println("Enter The Number Of Tickets(User Needs For His/Her Family):");
                int t = sc.nextInt();
                //Check If Whether The Tickets Are Available
                if(t > currentFlight.tickets)
                {
                    System.out.println("Sorry,Not Enough Tickets!!!");//Tickets Not Available
                    break;
                }
                //If Tickets Available--->Calling Book Function To Book The Tickets
                book(currentFlight,t,passengerID);
                //Incrementing The Passenger ID->Since Next Booking Can Have Different Passenger ID
                passengerID = passengerID + 1;
                break;
            }
            //For Cancelling The Ticket
            case 2:
            {
                System.out.println("Enter The Flight Name(ID) And Passenger ID For Cancelling The Ticket(As User)");
                int fid = sc.nextInt();
                //Checking Whether Admin Name(ID) Is Valid Or Not--> 
                if(fid > flights.size())
                {
                    System.out.println("Fight Name(ID) Is Not Valid!!!!!!!");
                    break;
                }
                //Search For The Required Admin To Cancel For The User 
                Admin currentFlight = null;
                for(Admin f : flights)
                {
                    if(f.flightID == fid)
                    {
                        currentFlight = f;
                        break;
                    }
                }
                //Getting Passenger ID From The Passenger To Find The Booking To Be Cancelled
                int id = sc.nextInt();
                //Now Call The Function For Cancelling The Booking
                cancel(currentFlight,id);
                break;
            }
            //Printing Details Of The Admin Along With The Passenger's Details
            case 3:
            {
                //Traversing Through All The Flights
                for(Admin f : flights)
                {
                        //Checking If Admin Has Atleast 1 Passenger Details
                        if(f.passengerDetails.size() == 0)
                        {
                            System.out.println("No passenger Details for  - Flight " + f.flightID);//If No Passenger Details Is Availble For The Corresponding Admin
                        }
                        else
                        {
                         print(f);//If Passenger Details Available,Print The Corresponding Admin Details
                        }
                }
                break;
            }
            default:
            {
                break;
            }
        }
        }
    }
}
