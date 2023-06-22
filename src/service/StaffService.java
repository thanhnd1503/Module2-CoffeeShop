package service;
import interfaces.Staff;
import coffeeshop.StaffFullTime;
import coffeeshop.StaffPartTime;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class StaffService {
    private static  StaffService staffService = new StaffService();
    private static Set<Staff> staffs ;
    private StaffService(){
        staffService = StaffService.getStaffService();
        staffs = new LinkedHashSet<>();
        readFile();
    }

    public static StaffService getStaffService() {
        return staffService;
    }



    public static Set<Staff> getStaff() {
        return staffs;
    }

    public void add(Staff staff){
        staffs.add(staff);

    }
    public boolean checkStaffList(){
        if(staffs.size() > 0){
            return true;
        }
        System.out.println("List empty!!!");
        return false;
    }


    public void fixStaffFullTime(String id, String name, int numberOfWorkingDays, int wallet) throws IOException {
        for (Staff staff: staffs
             ) {
            if (staff.getId().equals(id)){
               staff.setName(name);
               staff.setnumberOfWorkingDays(numberOfWorkingDays);
               staff.setWallet(wallet);
               saveToFile();
            }

        }
    }
    public void fixStaffPartTime(String id, String name, int numberOfShifts, int wallet) throws IOException {
        for (Staff staff: staffs
        ) {
            if (staff.getId().equals(id)){
                staff.setName(name);
                staff.setNumberOfShifts(numberOfShifts);
                staff.setWallet(wallet);
                saveToFile();
            }

        }
    }

    public Staff remove(String id)  {
        for (Staff staff: staffs
             ) {
            if (staff.getId().equals(id)){
                staffs.remove(staff);
                saveToFile();
                return staff;
            }
        }
        return null;
    }

    public boolean checkId(String id){
        for (Staff staff: staffs
             ) {
            if (staff.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void display(){
        System.out.println(staffs);
    }

    public void calcSalary(){
        for (Staff staff: staffs
             ) {
            if (staff.getType().equals("staffFullTime")){
                int newWalletFull = staff.getWallet() + (staff.getnumberOfWorkingDays()*staff.getSalaryFull());
                staff.setWallet(newWalletFull);
            } else if (staff.getType().equals("staffPartTime")) {
                int newWalletPart = staff.getWallet() + (staff.getNumberOfShifts()*staff.getSalaryPart());
                staff.setWallet(newWalletPart);
            }
        }
    }

    public int caclSumSalary(){
        int sum = 0;
        for (Staff staff : staffs
        ){
            sum += staff.getWallet();
        }
        return sum;
    }

    public void saveToFile() {
        String path = "coffeShop\\src\\files\\staffs.txt";
        FileWriter fw = null;
        BufferedWriter bufferedWriter = null;
        try{
             fw = new FileWriter(path);
             bufferedWriter = new BufferedWriter(fw);
            for (Staff staff: staffs
            ) {
                bufferedWriter.write(staff.toFile());
                bufferedWriter.newLine();
            }

        }catch (IOException e){
            System.out.println(e);
        }finally {
            try{
                bufferedWriter.close();
                fw.close();
            }catch (IOException e){
                System.out.println(e);
            }

        }
    }

    public static Staff handleLine(String line){
        String[] strings = line.split(",");
        if (strings[3].equals("staffFullTime")){
            return new StaffFullTime(strings[0].trim(),strings[1].trim(),Integer.parseInt(strings[2].trim()),strings[3].trim(),Integer.parseInt(strings[4].trim()));
        } else if (strings[3].equals("staffPartTime")) {
            return new StaffPartTime(strings[0].trim(),strings[1].trim(),Integer.parseInt(strings[2].trim()),strings[3].trim(),Integer.parseInt(strings[4].trim()));
        }
        return null;
    }


    public static void readFile()  {
        staffs.clear();
        FileReader fileReader = null;
        BufferedReader reader = null;
        try{
            fileReader = new FileReader("coffeShop\\src\\files\\staffs.txt");
            reader = new BufferedReader(fileReader);
            String line  ;
                    while ((line = reader.readLine()) != null){
                        Staff staff = handleLine(line);
                        staffs.add(staff);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                reader.close();
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
