/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightBook;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 91984
 */
public class Admin
{
    //Flight Name(ID) Which Is Alloted Everytime A New Admin Object Is Created By Admin
    static int id = 0;
    int flightID;
    //Number Of Seats(Tickets) In The Admin By Admin
    int tickets;
    //Original Price For The Ticket By Admin
    int price;
    String flightSource="";
    String flightDest="";
    int DepartTime;
    //ArrayList Of String Datatype Which Consists Of Passenger Details For Printing
    ArrayList<String> passengerDetails;
    //ArrayList Of String Datatype Which Consists Of Passenger IDs
    ArrayList<Integer> passengerIDs;
    //ArrayList Of Integer Datatype Which Consists Of The Number Of Tickets Booked By Each Passenger(ID)
    ArrayList<Integer> bookedTicketsPerPassenger;
    //list of cost paid by every passenger ID
    ArrayList<Integer> passengerCost;
    //Setting Values As A Admin
    public Admin()//ASSUMING MYSELF AS A ADMIN
    {
        tickets = 100;
        price = 5000;
        id = id + 1;
        flightID = id;
        flightSource="AAA";
        flightDest="ZZZ";
        DepartTime=10;
        passengerDetails = new ArrayList<String>();
        passengerIDs = new ArrayList<Integer>();
        bookedTicketsPerPassenger = new ArrayList<Integer>();
        passengerCost = new ArrayList<Integer>();
    }
    //Passenger Details To Admin
    public void addPassengerDetails(String passengerDetail,int numberOfTickets,int passengerID)
    {
       //Updating Price Using Dynamic Pricing
       if(numberOfTickets>=numberOfTickets/2)//For First 50% Of Seats
       {
           price=price*numberOfTickets;//Normal Price
           System.out.println("Booked Successfully! With Price->"+price);
       }
       else if(numberOfTickets<=((numberOfTickets-numberOfTickets/2)*(40/100)))//Next 40% Of Seats
       {
           price=price+(120*numberOfTickets);//Incrementing The Base Price By 20%
           System.out.println("Booked Successfully! With Price->"+price);
       }
       else if(numberOfTickets<=((numberOfTickets-numberOfTickets/2)-(numberOfTickets-numberOfTickets/2))*10/100)//Next 10% Of Seats 
       {
           price=price+(150*numberOfTickets);//Incrementing The Base Price By 50%
           System.out.println("Booked Successfully! With Price->"+price);
       }
        //Updating Available No Of Tickets
        tickets-= numberOfTickets;
        bookedTicketsPerPassenger.add(numberOfTickets);
        passengerDetails.add(passengerDetail);
        passengerIDs.add(passengerID);
        passengerCost.add(price);
    }
    //Cancelling Tickets By A Particular Passenger
    public void cancelTicket(int passengerID)
    {
        //Finding The index To Remove From All List
       int indexToRemove = passengerIDs.indexOf(passengerID);
       if(indexToRemove < 0)
       {
           System.out.println("UserID not found!");
           return;
       }
       int ticketsToCancel = bookedTicketsPerPassenger.get(indexToRemove);
       //As We Have Cancelled We Increment The No Of Tickets
       tickets+=ticketsToCancel;
       //Removing Details Of The User From All Lists
       bookedTicketsPerPassenger.remove(indexToRemove);
       passengerIDs.remove(Integer.valueOf(passengerID));
       passengerDetails.remove(indexToRemove);
       passengerCost.remove(indexToRemove);
       System.out.println("Cancelled Booked Tickets Successfully!!!");
    }
    //Functions To Print Details About Flights And Passengers To Improve Readability
    public void flightSummary()
    {
        System.out.println("Flight ID: " + flightID  + " Remaining Tickets: " + tickets +" Current Flight Ticket Price: " + price+" Flight Source: "+flightSource+" Flight Destination: "+flightDest+" Flight Departure Time: "+DepartTime);
        //To Write Flight Details In A Text File("FlightInfo.txt")
        try
        {
        String s="---FLIGHT DETAILS----"+"Flight Name(ID): "+flightID+"   "+" Remaining Tickets: "+tickets+"   "+" Current Flight Ticket Price: "+price+"   "+" Flight Source: "+flightSource+"   "+" Flight Destination: "+flightDest+"   "+" Flight Departure Time: "+DepartTime+"   ";
        File newTextFile=new File("C:/Users/91984/Desktop/FlightInfo.txt");
        FileWriter fw=new FileWriter(newTextFile);
        fw.write(s);
        fw.close();
        }
        catch(IOException iox)
        {
          iox.printStackTrace();;
        }
    }
    public void printDetails()
    {
       System.out.println("Flight ID " + flightID + "->");
        for(String detail : passengerDetails)
            System.out.println(detail);
    }
}

























//If Need To Refund
       //price-=ticketsToCancel;
       //System.out.println("Refunded Amount After Cancellation:"+passengerCost.get(indexToRemove));
