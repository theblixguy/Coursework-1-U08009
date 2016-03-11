package javaapplication1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author suyashsrijan
 */
public class WeatherApplication {

    /* Some global variables that our program needs to run. This includes array
     lists to store rainfall, temperature and month values, as well as some other
     variables. 
    
     We use three array lists, one for month, one for rainfall and one
     for temperature. We use the index value of the elements in each array list
     to form a relationship between them. For example, index 0 would provide the
     rainfall and temperature value for the first month (Jan), index 1 would
     provide the rainfall and temperature value for the second month (Feb), and 
     so on. We don't have to do anything specific to achieve this relationship,
     it is already achieved due to the nature of how we add elements to the list
     */
    
    ArrayList<String> monthsList = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
    ArrayList<Double> tempList = new ArrayList<>();
    ArrayList<Double> rainfallList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public static WeatherApplication weatherApp;

    /* Main function of the program. It creates a new instance of itself and then
     continues to ask for user data and handle user menu selection */
    
    public static void main(String[] args) {
        weatherApp = new WeatherApplication();
        weatherApp.askUserForData();
        weatherApp.handleUserSelection(false);
    }

    /* Function to close the program */
    
    public void closeProgram() {
        System.exit(0);
    }

    /* Function to handle user's menu selection and execute the appropriate method. 
     Once the method associated with the option has finished executing, the menu is
     displayed again for the user to choose another option if they wish to */
    
    public void handleUserSelection(Boolean startWithNewLine) {
        int option = weatherApp.getMenuSelection(startWithNewLine);

        switch (option) {
            case 1:
                weatherApp.closeProgram();
                break;
            case 2:
                weatherApp.displayFormattedData();
                weatherApp.handleUserSelection(true);
                break;
            case 3:
                weatherApp.changeTempValue();
                weatherApp.handleUserSelection(true);
                break;
            case 4:
                weatherApp.changeRainfallValue();
                weatherApp.handleUserSelection(true);
                break;
            case 5:
                weatherApp.printTotalRainfall(weatherApp.rainfallList);
                weatherApp.handleUserSelection(true);
                break;
            case 6:
                weatherApp.printValueWithMessage("Mean temperature: ", weatherApp.calculateAverage(weatherApp.tempList));
                weatherApp.printEmptyLine();
                weatherApp.printValueWithMessage("Mean rainfall: ", weatherApp.calculateAverage(weatherApp.rainfallList));
                weatherApp.handleUserSelection(true);
                break;
            case 7:
                weatherApp.printColdestMonths();
                weatherApp.handleUserSelection(true);
                break;
            case 8:
                weatherApp.calcPrintMonthsBelowDrought(weatherApp.rainfallList);
                weatherApp.handleUserSelection(true);
                break;
            case 9:
                weatherApp.calcPrintMinMax(weatherApp.tempList, "The month with the highest temperature: ", "The month with the lowest temperature: ");
                weatherApp.handleUserSelection(true);
                break;
            case 10:
                weatherApp.calcPrintMinMax(weatherApp.rainfallList, "The wettest month is: ", "The driest month is: ");
                weatherApp.handleUserSelection(true);
                break;
            default:
                System.out.println("Invalid menu option");
                weatherApp.handleUserSelection(true);
        }
    }

    /* Function to get user's menu selection and pass it to handleUserSelection()
     method, which takes the selection and executes the corresponding function */
    
    public int getMenuSelection(Boolean startWithNewLine) {
        int selection = 0;

        if (startWithNewLine) {
            System.out.println("\nPlease type the option you want to select:\n");
        } else {
            System.out.println("Please type the option you want to select:\n");
        }

        System.out.println("1 -> Quit");
        System.out.println("2 -> Display formatted data");
        System.out.println("3 -> Change a temperature value");
        System.out.println("4 -> Change a rainfall value");
        System.out.println("5 -> Display total rainfall for current year");
        System.out.println("6 -> Display mean rainfall and mean temperature for current year");
        System.out.println("7 -> Display months when temperature was below 3 degrees");
        System.out.println("8 -> Display months when rainfall was below drought level");
        System.out.println("9 -> Display months with highest and lowest temperatures");
        System.out.println("10 -> Display wettest and driest months");

        selection = scanner.nextInt();

        return selection;

    }

    /* Function to ask the user for temperature and rainfall data for 12 months and
     store them in an ArrayList after validation */
    
    public void askUserForData() {
        for (int i = 0; i < 12; i++) {
            printValueWithMessage("Please enter temperature for ", monthsList.get(i));
            Double temp = scanner.nextDouble();

            if (temp >= -30.0 && temp <= 60.0) {
                tempList.add(temp);
            } else {
                System.out.println("Values should be between -30 and 60 (inclusive)");
            }
        }

        for (int i = 0; i < 12; i++) {
            printValueWithMessage("Please enter rainfall for ", monthsList.get(i));
            Double rainfall = scanner.nextDouble();

            if (rainfall >= 0.0 && rainfall <= 300.0) {
                rainfallList.add(rainfall);
            } else {
                System.out.println("Values should be between 0 and 300 (inclusive)");
            }
        }
    }

    /* Function to display rainfall and temperature data in a formatted way */
    
    public void displayFormattedData() {
        System.out.println(String.format("%-10s%-10s", "Month", "Temperature"));

        for (int i = 0; i < tempList.size(); i++) {
            System.out.println(String.format("%-10s%-10f", monthsList.get(i), tempList.get(i)));
        }

        System.out.println("-------------------");

        System.out.println(String.format("%-10s%-10s", "Month", "Rainfall"));

        for (int i = 0; i < tempList.size(); i++) {
            System.out.println(String.format("%-10s%-10f", monthsList.get(i), rainfallList.get(i)));
        }
    }

    /* Function to change a particular temperature value in the temperature list. 
    It uses scanner to retrieve input and validates it before modifying the temp
    value. If the user enters an incorrect month or value (or both), it throws an
    error message and asks the user for input again. */
    
    public void changeTempValue() {
        System.out.println("Please enter the month for which you want to change the value: ");
        String month = scanner.next();

        System.out.println("Please enter the new temperature value: ");
        Double tempVal = scanner.nextDouble();

        if (tempVal >= -30 && tempVal <= 60) {
            if (month.equalsIgnoreCase("January")) {
                tempList.set(0, tempVal);
            } else if (month.equalsIgnoreCase("February")) {
                tempList.set(1, tempVal);
            } else if (month.equalsIgnoreCase("March")) {
                tempList.set(2, tempVal);
            } else if (month.equalsIgnoreCase("April")) {
                tempList.set(3, tempVal);
            } else if (month.equalsIgnoreCase("May")) {
                tempList.set(4, tempVal);
            } else if (month.equalsIgnoreCase("June")) {
                tempList.set(5, tempVal);
            } else if (month.equalsIgnoreCase("July")) {
                tempList.set(6, tempVal);
            } else if (month.equalsIgnoreCase("August")) {
                tempList.set(7, tempVal);
            } else if (month.equalsIgnoreCase("September")) {
                tempList.set(8, tempVal);
            } else if (month.equalsIgnoreCase("October")) {
                tempList.set(9, tempVal);
            } else if (month.equalsIgnoreCase("November")) {
                tempList.set(10, tempVal);
            } else if (month.equalsIgnoreCase("December")) {
                tempList.set(11, tempVal);
            } else {
                System.out.println("Invalid month, please enter again\n");
                changeTempValue();
            }
        } else {
            System.out.println("Values should be between -30 and 60 (inclusive)");
            changeTempValue();
        }
    }

    /* Function to change a particular rainfall value in the rainfall list. 
    It uses scanner to retrieve input and validates it before modifying the temp
    value. If the user enters an incorrect month or value (or both), it throws an
    error message and asks the user for input again. */
    
    public void changeRainfallValue() {
        System.out.println("Please enter the month for which you want to change the value: ");
        String month = scanner.next();

        System.out.println("Please enter the new rainfall value: ");
        Double rainfallVal = scanner.nextDouble();

        if (rainfallVal >= 0 && rainfallVal <= 300) {
            if (month.equalsIgnoreCase("January")) {
               rainfallList.set(0, rainfallVal);
            } else if (month.equalsIgnoreCase("February")) {
                rainfallList.set(1, rainfallVal);
            } else if (month.equalsIgnoreCase("March")) {
                rainfallList.set(2, rainfallVal);
            } else if (month.equalsIgnoreCase("April")) {
                rainfallList.set(3, rainfallVal);
            } else if (month.equalsIgnoreCase("May")) {
                rainfallList.set(4, rainfallVal);
            } else if (month.equalsIgnoreCase("June")) {
                rainfallList.set(5, rainfallVal);
            } else if (month.equalsIgnoreCase("July")) {
                rainfallList.set(6, rainfallVal);
            } else if (month.equalsIgnoreCase("August")) {
                rainfallList.set(7, rainfallVal);
            } else if (month.equalsIgnoreCase("September")) {
                rainfallList.set(8, rainfallVal);
            } else if (month.equalsIgnoreCase("October")) {
                rainfallList.set(9, rainfallVal);
            } else if (month.equalsIgnoreCase("November")) {
                rainfallList.set(10, rainfallVal);
            } else if (month.equalsIgnoreCase("December")) {
                rainfallList.set(11, rainfallVal);
            } else {
                System.out.println("Invalid month, please enter again\n");
                changeRainfallValue();
            }
        } else {
            System.out.println("Values should be between 0 and 300 (inclusive)");
            changeRainfallValue();
        }
    }

    /* Function to print the total rainfall in a year. It loops through all the
    values in the rainfallList and adds them and then prints the value */
    
    public void printTotalRainfall(ArrayList<Double> rainfallList) {
        Double sum = 0.0;

        for (int i = 0; i < rainfallList.size(); i++) {
            sum = sum + rainfallList.get(i);
        }

        System.out.println("Total rainfall (Jan - Dec): " + sum + "mm");
    }

    /* Function to calculate the average of all values in a list. It calculates
    the sum of all the values in the list, and then divides it by the number of
    values in the list to get the mean, and returns it. It also checks if the list is
    empty or not. */
    
    public Double calculateAverage(ArrayList<Double> valuesList) {
        Double sum = 0.0;
        Double mean = 0.0;

        if (!valuesList.isEmpty()) {
            for (Double value : valuesList) {
                sum += value;
            }
        }

        mean = sum / valuesList.size();
        return mean;
    }

    /* Function to print an empty line */
    
    public void printEmptyLine() {
        System.out.println();
    }

    /* Function to print a message along with a value. It uses Java generics to
     print any value with a message so there's no need to have seperate function
     for each type of value */
    
    public <T> void printValueWithMessage(String message, T value) {
        System.out.println(message + value);
    }

    /* Function to calculate the coldest month(s) */
    
    public ArrayList<String> getColdestMonths() {
        ArrayList<String> coldestMonths = new ArrayList<>();

        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i) < 3) {
                coldestMonths.add(monthsList.get(i));
            }
        }

        return coldestMonths;
    }

    /* Function to print the coldest months, which is calculated using the
     getColdestMonths() function */
    
    public void printColdestMonths() {
        ArrayList<String> coldestMonthsList = getColdestMonths();
        System.out.println("The coldest months are: ");

        for (String month : coldestMonthsList) {
            System.out.println(month);
        }
    }

    /* Function to calcuate and print all months below the Drought level */
    
    public void calcPrintMonthsBelowDrought(ArrayList<Double> rainfallList) {
        Double droughtLevel = 0.75 * 30;
        System.out.println("The months when rainfall was below drought level are: ");

        for (int i = 0; i < rainfallList.size(); i++) {
            if (rainfallList.get(i) < droughtLevel) {
                System.out.println(monthsList.get(i));
            }
        }
    }

    /* Function to find and print the maximum and minimum value in a list using Java
    Collections along with a user specified message for the maximum value and minimum value */
    
    public void calcPrintMinMax(ArrayList<Double> valuesList, String maxString, String minString) {
        Double maxVal = Collections.max(valuesList);
        Double minVal = Collections.min(valuesList);

        System.out.println(maxString);
        System.out.println(monthsList.get(valuesList.indexOf(maxVal)));
        printEmptyLine();
        System.out.println(minString);
        System.out.println(monthsList.get(valuesList.indexOf(minVal)));

    }
}