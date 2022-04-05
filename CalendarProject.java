import java.util.Calendar;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.File;

/*
6.12.18
Calendar Project
Description: Prints out a calendar that has
   - Enter custom date
   - Get today's date
   - Enter events into calendar
   - Print a month to a file
*/

class CalendarProject{

public static final int SIZE = 20;
public static String event[][] = new String[12][40];
public static int number = 0;
public static int mArray = 0;
public static int dayValue = - 1;

   public static void main(String[] args)throws FileNotFoundException{
   
     	showArray(0, 0);
     	commands();
   }
   public static void commands()throws FileNotFoundException{ // Commands you can type in to make calendar
 	 
     	Scanner in = new Scanner(System.in);
     	String command = "";
    	 
     	int day = -1;
     	int month = -1;
     	boolean Line = true;
     	int dayValue = -1;
    	 
     	while(true){
        	System.out.println("Please type in a command.");
        	System.out.println("\t" + "e: to enter a date and display the corresponding calendar.");
        	System.out.println("\t" + "t: to get todays date and displayt today's calendar.");
        	System.out.println("\t" + "n: to display the next month.");
        	System.out.println("\t" + "p: to display the prevoius month.");
        	System.out.println("\t" + "ev: to enter in an event.");
        	System.out.println("\t" + "fp: to print the calendar to a file.");
        	System.out.println("\t" + "q: to quit the program.");
       	 
        	command = in.nextLine();
       	 
        	// "IgnoreCase" is so that the letters can be capitalized or lowercase
        	if(command.equalsIgnoreCase("e")){ // Command for typing in month and day
           	System.out.print("Enter a date (Month/Day): ");
           	String date = in.nextLine();
           	dayValue = day = dayFromDate(date);
           	month = monthFromDate(date);
           	Line = true;
           	drawCalendar(month, day);
        	}
        	else if(command.equalsIgnoreCase("t")){ // Command to get today's calendar
           	Calendar cal = Calendar.getInstance();
           	day = cal.get(Calendar.DATE);
           	month = cal.get(Calendar.MONTH) + 1;
           	System.out.println("\nDisplaying calendar for today's date "
           	+ month + "/" + day);
           	Line = true;
           	drawCalendar(month, day);
        	}
        	else if(command.equalsIgnoreCase("n")){ // Command for the next month
           	if(day != -1 && month != -1){
              	day = 1;
              	month = nextGoodMonth(month);
              	System.out.println("\nDisplaying calendar for next month ");
              	Line = false;
              	drawCalendar(month, day);
           	}
        	else{
           	System.out.println("Display a calendar first.\n");
        	}
        	}
        	else if(command.equalsIgnoreCase("p")){ // Command for the previous month
           	if(day != -1 && month != -1){
              	day = 1;
              	month = previousMonth(month);
              	System.out.println("\nDisplaying calendar for previous month ");
              	Line = false;
              	topmonth(month);
              	drawCalendar(month, day);    
           	}
        	else{
           	System.out.println("Display a calendar first.\n");
        	}
        	}
        	else if(command.equalsIgnoreCase("ev")){
           	makeArray();
           	System.out.print("Enter a date (Month/Day): ");
           	String date = in.nextLine();
           	day = dayFromDate(date);
           	month = monthFromDate(date);
           	Line = true;
           	drawCalendar(month, day);
        	}
        	else if(command.equalsIgnoreCase("fp")){ // Command for typing in month and day
           	System.out.println("Printing...");
           	System.out.print("Enter a date (Month/Day): ");
           	String date = in.nextLine();
           	dayValue = day = dayFromDate(date);
           	month = monthFromDate(date);
           	Line = true;
           	drawCalendar(month, day);
           	printCalendar(month, day);
        	}
        	else if(command.equalsIgnoreCase("q")){ // Command to quit
           	System.out.println("Goodbye.");
           	break;
        	}
        	else{
           	System.out.println("Invalid command.\n");
        	}
     	}
     	in.close();
   }
   public static void topmonth(int month){ // Places the month name on top of the Calendar
	 
     	System.out.print("  	");
    	 
     	for(int i = 0; i < (3 * SIZE) - 4; i++){
        	System.out.print(" ");
     	}
     	String monthName[] = {"January", "February",
                     	"March", "April",
                     	"May", "June",
                     	"July","August",
                     	"September", "October",
                     	"November", "December"};
     	System.out.println(monthName[month - 1]);
   }
   public static void monday(int k){ // Prints out the Mo, Tu, We, ... above the calendar
 	 
     	System.out.print("	");
    	 
     	System.out.print("Su");
        	for(int i = 0; i < SIZE - 2; i++){
           	System.out.print(" ");
        	}
     	System.out.print("Mo");
        	for(int j = 0; j < SIZE - 2; j++){
           	System.out.print(" ");
        	}
     	System.out.print("Tu");
        	for(int j = 0; j < SIZE - 2; j++){
           	System.out.print(" ");
        	}
     	System.out.print("We");
        	for(int j = 0; j < SIZE - 2; j++){
           	System.out.print(" ");
        	}
     	System.out.print("Th");
        	for(int j = 0; j < SIZE - 2; j++){
           	System.out.print(" ");
        	}
     	System.out.print("Fr");
        	for(int j = 0; j < SIZE - 2; j++){
           	System.out.print(" ");
        	}
     	System.out.print("Sa");
        	for(int j = 0; j < SIZE - 2; j++){
           	System.out.print(" ");
        	}
     	System.out.println();
  	}
   public static void drawMonth(int month, int StartOfMonth, int maxDays){
   // Draws month based on where the month starts on and the total number of days in month
 	 
     	mArray = month;
    	 
     	int center = SIZE * 3;
     	int start = 1;
     	while(start <= maxDays){
        	if(start == 1){
           	drawRow(start, maxDays, StartOfMonth);
           	start += 7 - StartOfMonth;
        	}
        	else{
           	drawRow(start, maxDays, 0);
           	start += 7;
        	}
     	}
     	int width = SIZE * 7;
     	String insert = "";
     	for(int i = 0; i <= width; i++)
        	insert += "-";
        	System.out.println(insert);
   }
   public static void drawRow(int start, int maxDaysInMonth, int skip){ // Prints out the row in the month  <-- add int dayValue to pass into drawRow for [day]
 	 
     	String insert = "";
     	int width = SIZE * 7;
    	 
     	for(int i = 0; i <= width; i++)
        	insert += "-";
        	System.out.println(insert);
     	int day = start;
     	System.out.print('|');
     	for(int i = 1; i <= 7; i++){
           	insert = "";
        	if(i > skip && day <= maxDaysInMonth){ //START PRINT NUMBERS
           	if(dayValue == day){
              	insert += "[" + day++ + "]";
           	}
              	else{
                 	insert += day++;
                 	number = day;
              	}
        	}
        	else{
        	number++;
        	}                                	 
        	while(insert.length() < SIZE - 1) //END PRINT NUMBERS
           	insert += " ";
           	insert += "|";
           	System.out.print(insert);
     	}
      space();
   }
   public static void space(){ // Prints the blank row
 	 
     	int counter = number - 7;  
     	System.out.println();
    	 
     	for(int i = 0; i < 6; i++){  // Prints number of rows
        	for(int j = 0; j < 7; j++){
           	System.out.print("|");        	 
           	if(counter > 0 && counter < 100 && i == 0 && event[mArray - 1][counter - 1] != null){ // Checks to see if there is anything int array already
              	System.out.print(event[mArray - 1][counter - 1]);
              	if(event[mArray - 1][counter - 1].length() < SIZE - 1){
                 	for(int k = 0; k < (SIZE - event[mArray - 1][counter - 1].length() - 1); k++){
                    	System.out.print(" ");
                 	}
              	}         	 
           	}
           	else{
              	for(int k = 0; k < (SIZE - 1); k++){
                 	System.out.print(" ");            	 
           	}   
        	}
        	counter++;
     	}
     	System.out.println("|");
     	}
   }
   public static void printCalendar(int month, int day)throws FileNotFoundException{ // Prints calendar to file
   
      PrintStream testFile = new PrintStream(new File("PrintedCalendar.txt"));
  	   PrintStream console = System.out; // Stores System.out
 
  	   System.setOut(testFile); // Prints to File
  	   drawCalendar(month, day);
 
  	   System.setOut(console); // Prints to console    
      
   }
   public static void showArray(int month, int day)throws FileNotFoundException{ // Inputs the array into the calendar so it always show the events for each month
 
      File f = new File("calendarEvents.txt"); // location of file
     	Scanner input = new Scanner(f);// location of file
     	process(input);
      
   }
   public static void displayDate(int month, int day){ // Says what month and day it is
 	 
  	   System.out.println("Month: "+ month);
  	   System.out.println("Day: " + day);
   
   }
   public static int monthFromDate(String date){ // Puts the month input into the month output  
 	 
  	   int firstSlash = date.indexOf("/");
  	   String monthData = date.substring(0, firstSlash);
  	   return Integer.parseInt(monthData);
      
   }
   public static int dayFromDate(String date){ // Puts day input into day output  
   
  	   int firstSlash = date.indexOf("/");
  	   String dayData = date.substring(firstSlash + 1, date.length());
  	   return Integer.parseInt(dayData);
      
   }
   public static void makeArray()throws FileNotFoundException{
  	 
  	   File f = new File("calendarEvents.txt"); // location of file
  	   Scanner input = new Scanner(f);// location of file
  	   process(input);
 	    
  	   System.out.println("Print out the event in \"MM/DD event_title\" form.");
  	   Scanner in = new Scanner(System.in);
  	   String stuff = "";
  	   stuff = in.nextLine();
 	 
  	   int month = monthFromDate(stuff);
  	   int day = dayFromDate(stuff.substring(0, stuff.indexOf(" "))); 	 
  	   event[month - 1][day - 1] = stuff.substring(stuff.indexOf(" ") + 1, stuff.length());
  	   System.out.println(event[month - 1][day - 1]);
      
   }
   public static void process(Scanner input){
 	 
     	while(input.hasNextLine()){
        	String date = input.next();
        	String title = input.nextLine();
        	int month = Integer.parseInt(date.split("/")[0]);
        	int day = Integer.parseInt(date.split("/")[1]);
        	event[month - 1][day - 1] = title;
     	}
   }
   public static void drawCalendar(int month, int day)throws FileNotFoundException{ // Makes the calendar
 	 
     	dayValue = day;
     	ascii();
     	displayDate(month, day);
     	topmonth(month);
     	monday(1);
     	int startingDay = getStartingDay(month);
     	int maxDays = getMaximumDays(month);
     	drawMonth(month, startingDay, maxDays);
      
   }
   public static int nextGoodMonth(int month){ // Checks to see what the next month is
   
     	int i = month;
     	if(month == 12)
        	return 1;
        	return i + 1;	 
         
   }
   public static int previousMonth(int month){ // Checks to see what the last month was
   
     	int i = month;
     	if(month == 1)
        	return 12;
        	return i - 1;
         
   }
   public static int getStartingDay(int month){ // Get the day that the week starts on
   
     	Calendar cal = Calendar.getInstance();
     	cal.set(Calendar.DAY_OF_MONTH, 1);
     	cal.set(Calendar.MONTH, month - 1);
     	return cal.get(Calendar.DAY_OF_WEEK) - 1;
      
   }
   public static int getMaximumDays(int month){ // Gets the total amount of days in the month
   
     	Calendar cal = Calendar.getInstance();
     	cal.set(Calendar.MONTH, month - 1);
     	return cal.getActualMaximum(Calendar.DATE);
      
   }
   public static void ascii(){ // Ascii Drawing of Cat
   
     	int bestSize = SIZE / 2 + 3;  
     	for(int i = 0; i <= bestSize; i++){
        	System.out.print("   /\\_/\\   ");
  	   }
     	System.out.println();
     	for(int i = 0; i <= bestSize; i++){
        	System.out.print("  / o o \\  ");
  	   }
     	System.out.println();
     	for(int i = 0; i <= bestSize; i++){
        	System.out.print(" <   \"   > ");
  	   }
     	System.out.println();
     	for(int i = 0; i <= bestSize; i++){
        	System.out.print("  \\~(*)~/  ");
  	   }
     	System.out.println();
     	for(int i = 0; i <= bestSize; i++){
        	System.out.print("   // \\\\   ");
  	   }
  	   System.out.println();
   }
}


