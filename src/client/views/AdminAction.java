package client.views;

import client.interfaces.*;
import client.models.User;
import client.simplifiers.RequestSimplifiers;
import client.views.components.Component;
import client.views.components.TableView;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.CommonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


import utils.ConsoleColor;
import utils.ValidEmail;

public class AdminAction {
    PrintWriter writer;
    BufferedReader reader;
    Scanner scanner = new Scanner(System.in);
    int userId;
    public AdminAction(PrintWriter writer, BufferedReader reader, int userId)
    {
        this.reader = reader;
        this.writer = writer;
        this.userId = userId;
        this.starts();
    }

    public void starts() {
        int choice = 0;
        while(choice != 55 && choice != 44) {
        Component.pageTitleView("ADMIN ACTIVITIES");
        CommonUtil.addTabs(11, true);
        System.out.println("1. Statistics");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Users");
        CommonUtil.addTabs(11, false);
        System.out.println("3. System summary");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");

            try {
                Component.chooseOptionInputView("Choose an option: ");
                choice  = scanner.nextInt();
                switch(choice) {
                    case 1:
                        this.chooseStat();
                        break;
                    case 2:
                        this.usersOperation();
                        break;
                    case 3:
                        this.overallStatics();
                        break;
                    case 44:
                        break;

                    case 55:
                        Component.closeUIView();
                        System.exit(1);
                        break;

                    default:
                        CommonUtil.addTabs(11, false);
                        CommonUtil.useColor("\u001b[1;31m");
                        System.out.println("Enter a valid choice (1,5): ");
                        CommonUtil.resetColor();
                }
            } catch (Exception var2) {
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor("\u001b[1;31m");
                System.out.println("is incorrect input");
                CommonUtil.resetColor();
            }
            if(choice == 44){
                break;
            }
        }


    }


    private void chooseStat() {
        int choice = 0;
        while(choice != 55 && choice != 44){
        Component.pageTitleView("VIEW STATISTICS OF THE APP");
        CommonUtil.addTabs(11, true);
        System.out.println("1. Message Reports");
        CommonUtil.addTabs(11, false);
        System.out.println("2. User Reports");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Group Reports");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");
                try {
                    Component.chooseOptionInputView("Choose an option: ");
                    choice  = scanner.nextInt();
                    switch (choice) {
                        case 1 -> {
                            CommonUtil.clearScreen();
                            this.choosePeriod("messaging");
                        }
                        case 2 -> {
                            System.out.flush();
                            CommonUtil.clearScreen();
                            this.choosePeriod("userReport");
                        }
                        case 3 -> {
                            System.out.flush();
                            CommonUtil.clearScreen();
                            this.choosePeriod("groupReport");
                        }
                        case 44->{
                            break;
                        }
                        case 55->{
                            Component.closeUIView();
                            System.exit(1);
                            break;
                        }
                    }
                } catch (Exception var2) {
                    CommonUtil.addTabs(11, false);
                    CommonUtil.useColor("\u001b[1;31m");
                    System.out.println("is incorrect input");
                    CommonUtil.resetColor();
                }
            if(choice == 44){
                break;
            }
            }

    }


    private void choosePeriod(String range) {
        int choice = 0;
        while(choice != 55 && choice != 44){
        Component.pageTitleView("CHOOSE " + range.toUpperCase(Locale.ROOT) + " REPORT");
        CommonUtil.addTabs(11, true);
        System.out.println("1. Daily");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Monthly");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Yearly");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");


                try {
                    Component.chooseOptionInputView("Choose an option: ");
                    choice  = scanner.nextInt();
                    List<List> reports = null;
                    switch(choice) {
                        case 1:
                            if (range.contains("messaging")) {
                               reports =  new RequestSimplifiers(writer,reader).getDiallyMessages("stats/messages/daily");
                                printStatistics(reports,"message:");
                            } else if(range.contains("userReport")) {
                               reports =  new RequestSimplifiers(writer,reader).getDiallyMessages("stats/user/daily");
                                printStatistics(reports,"user:");
                            }else if (range.contains("groupReport")){
                                reports =  new RequestSimplifiers(writer,reader).getDiallyMessages("stats/groups/daily");
                                printStatistics(reports,"group:");
                            }
                            break;
                        case 2:
                          CommonUtil.addTabs(12, true);
                          CommonUtil.useColor(ConsoleColor.HighIntensityColor.CYAN_BRIGHT);
                          System.out.println("--------------------------------");
                          CommonUtil.addTabs(12, false);
                          System.out.println("|  Monthly report Coming soon   |");
                          CommonUtil.addTabs(12, false);
                          System.out.println("---------------------------------");
                          CommonUtil.resetColor();
                            break;
                        case 3:
                            CommonUtil.addTabs(12, true);
                            CommonUtil.useColor(ConsoleColor.HighIntensityColor.CYAN_BRIGHT);
                            System.out.println("--------------------------------");
                            CommonUtil.addTabs(12, false);
                            System.out.println("|   Yearly report Coming soon   |");
                            CommonUtil.addTabs(12, false);
                            System.out.println("---------------------------------");
                            CommonUtil.resetColor();
                            break;
                        case 4:
                            this.starts();
                            break;
                        case 44:
                            break;
                        case 55:
                            Component.closeUIView();
                            System.exit(1);
                            break;
                        default:
                            CommonUtil.addTabs(11, false);
                            CommonUtil.useColor("\u001b[1;31m");
                            System.out.print("Enter a valid choice (1,5): ");
                            CommonUtil.resetColor();
                    }
                } catch (Exception var3) {
                    CommonUtil.addTabs(11, false);
                    CommonUtil.useColor("\u001b[1;31m");
                    System.out.println("is incorrect input");
                    CommonUtil.resetColor();
                }
            if(choice == 44){
                break;
            }
            }
        }

    private void usersOperation() {
        int choice = 0;
        while(choice != 55 && choice != 44){
        Component.pageTitleView("USER OPERATIONS");
        CommonUtil.addTabs(11, true);
        System.out.println("1. Invite user");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Reactivate user");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Deactivate user");
        CommonUtil.addTabs(11, false);
        System.out.println("4. View all Users");
        CommonUtil.addTabs(11, false);
        System.out.println("5. View user logs");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");


                try {
                    Component.chooseOptionInputView("Choose an option: ");
                    choice  = scanner.nextInt();
                    switch (choice) {
                        case 1 -> InviteUsers();
                        case 2 -> {
                            int id = CheckUserExists(allInactiveUsers());
                            if(id != -1){
                                De_ActivateUser(id,"users/activate");
                            }
                        }
                        case 3 -> {
                            int id = CheckUserExists(new SendMessageView(userId, writer, reader).allActiveUsers());
                            if(id != -1){
                                De_ActivateUser(id,"users/deactivate");
                            }
                        }
                        case 4 -> new UserView(userId, writer, reader).allActiveUsers();
                        case 5 -> System.out.println("choice 5");
                        case 6 -> this.starts();
                        case 44 -> {
                            break;
                        }
                        case 55 -> {
                            Component.closeUIView();
                            System.exit(1);
                        }
                        default -> {
                            CommonUtil.addTabs(11, false);
                            CommonUtil.useColor("\u001b[1;31m");
                            System.out.print("Enter a valid choice (1,5): ");
                            CommonUtil.resetColor();
                        }
                    }
                } catch (Exception var3) {
                    CommonUtil.addTabs(11, false);
                    CommonUtil.useColor("\u001b[1;31m");
                    System.out.println("is incorrect input");
                    CommonUtil.resetColor();
                }
            }
        }
    public void InviteUsers() throws IOException {
        Component.pageTitleView("SEND INVITATION TO JOIN CLASS_C CHAT");
        Scanner scanner = new Scanner(System.in);
        List<String> emails = new ArrayList<String>();
        String email = "";
        CommonUtil.addTabs(12, false);
        CommonUtil.useColor(ConsoleColor.HighIntensityColor.CYAN_BRIGHT);
        System.out.println("* Type [-1] to skip field update *");
        CommonUtil.resetColor();
        System.out.println();

        while(!email.equals("-1")){
            CommonUtil.addTabs(11, false);
            CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
            System.out.print("Email: ");
            CommonUtil.resetColor();
            email = scanner.nextLine();
            if(!email.equals("-1")){
                if(!new ValidEmail(email).checkEmail()){
                    Component.showErrorMessage("This email is not valid; it is not saved");
                }
                else{
                    emails.add(email);
                }

            }
        }
        String key = "users/invite";
        Request request  = new Request(emails,key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        CommonUtil.addTabs(11, false);
        CommonUtil.useColor(ConsoleColor.RegularColor.BLUE);
        System.out.println("Sending emails");
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            Component.alertSuccessMessage(11, "Email sent successfully");
        }
        else{
            Component.alertDangerErrorMessage(11, "Email failed to send.");
        }
    }

    /**
     * method to print out the formatted statistics
     * @param all
     * @param  trimStr {category of stastistics}
     */
    private void printStatistics(List<List> all, String trimStr){
        if(all.get(1).size() <= 0){
            CommonUtil.addTabs(12, false);
            CommonUtil.useColor(ConsoleColor.HighIntensityColor.CYAN_BRIGHT);
            System.out.println("Nothing to show");
            CommonUtil.resetColor();
            return;
        }
        TableView st = new TableView();
        int i,sum=0;

        st.setShowVerticalLines(true);
        st.setHeaders("number", "dates",trimStr.replace(":",""));
        for ( i = 0; i < all.get(0).size(); i++) {
            sum += Integer.parseInt(all.get(1).get(i).toString());
            st.addRow(Integer.toString(i+1), all.get(0).get(i).toString().replace(trimStr,""), all.get(1).get(i).toString());
        }
        st.addRow(Integer.toString(i+1),"total",String.valueOf(sum));
        st.print();
    }
    private void overallStatics() throws IOException {
        TableView ovt = new TableView();
        ovt.setShowVerticalLines(true);
        ovt.setHeaders("number","property","total");
        ovt.addRow("1","messages",String.valueOf(findSum(new RequestSimplifiers(writer,reader).getDiallyMessages("stats/messages/daily"))));
        ovt.addRow("2","groups",String.valueOf(findSum(new RequestSimplifiers(writer,reader).getDiallyMessages("stats/groups/daily"))));
        ovt.addRow("3","users",String.valueOf(findSum(new RequestSimplifiers(writer,reader).getDiallyMessages("stats/user/daily"))));
        ovt.addRow("4","system Visits",String.valueOf(findSum(new RequestSimplifiers(writer,reader).getDiallyMessages("stats/visit/daily"))));

        ovt.print();
    }
    private int findSum(List<List> list){
        if (list.size() == 0){
            return 0;
        }
        int sum = 0;
        for ( int i = 0; i < list.get(0).size(); i++) {
            sum += Integer.parseInt(list.get(1).get(i).toString());
        }
       return sum;

    }
    public int CheckUserExists(UsersList list) throws  IOException{
        if(list == null){
            CommonUtil.addTabs(11, true);
            CommonUtil.useColor(ConsoleColor.RegularColor.PURPLE);
            System.out.println("Users list is null");
            CommonUtil.resetColor();
            return -1;
        }
        int choice = 0;
        List ids = list.getIds();
        User[] users = list.getUsers();
        if(users.length == 0){
            CommonUtil.addTabs(11, true);
            CommonUtil.useColor(ConsoleColor.RegularColor.PURPLE);
            System.out.println("Users empty");
            CommonUtil.resetColor();
            return -1;
        }
        else{


        do{
            System.out.println("");
            Component.chooseOptionInputView("Type user id deactivate: ");
            choice  = Component.getChooseOptionChoice();
            if (choice == -1) break;
            if(!ids.contains(choice)){
                CommonUtil.addTabs(11, true);
                Component.alertDangerErrorMessage(11, "User not found, try another!");

            }
        }while(!ids.contains(choice));
        for (User user : users) {
            if(user.getUserID() == choice){
                return choice;
            }
        }
        }
        return -1;
    }
    public UsersList allInactiveUsers() throws IOException {
        String  key= "users/inactive";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("USERS LIST");
        List ids = new ArrayList<Integer>();
        if(response.isSuccess()){
            User[] users = new UserResponseDataDecoder().returnUsersListDecoded(response.getData());
            CommonUtil.addTabs(11, true);
            for (User user : users) {
                ids.add(user.getUserID());
                System.out.println(user.getUserID()+". "+user.getFname()+" "+user.getLname());
                CommonUtil.addTabs(11, false);
            }
            if(users.length == 0){
                return null;
            }
            return new UsersList(users,ids);
        }else {
            Component.alertDangerErrorMessage(11, "Failed to read users list, sorry for the inconvenience");
        }
        System.out.println("");
        return null;

    }
    public void De_ActivateUser(int userId,String key) throws IOException {
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            Component.alertSuccessMessage(11, "Successfully Done!");
        }
        else{
            Component.alertDangerErrorMessage(11, "Failing, try again");
        }
    }
    }
