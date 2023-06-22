package factory;

import interfaces.Staff;
import coffeeshop.StaffFullTime;
import coffeeshop.StaffPartTime;

public class StaffFactory {
    private StaffFactory(){}
    public static final Staff getStaff(String id, String name, int numberOfShifts, String type, int numberOfWorkingDays, int wallet){
        Staff staff = null;
        switch (type){
            case "staffFullTime" ->
                    staff = new StaffFullTime.StaffFullTimeBuilder()
                        .id(id)
                        .name(name)
                        .type(type)
                        .numberOfWorkingDays(numberOfWorkingDays)
                        .wallet(wallet)
                        .build();
            case "staffPartTime" ->
                    staff = new StaffPartTime.StaffPartTimeBuilder()
                        .id(id)
                        .name(name)
                        .type(type)
                        .numberOfShift(numberOfShifts)
                        .wallet(wallet)
                        .build();
            default -> throw new IllegalArgumentException("This staff type is un exits");
        }
        return staff;
    }
}
