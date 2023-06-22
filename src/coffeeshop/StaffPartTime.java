package coffeeshop;

import interfaces.Staff;

public class StaffPartTime implements Staff {
    private String id;
    private String name;
    private int numberOfShifts;
    private final int salaryPart = 120000;
    private String type;

    private int wallet;

    public StaffPartTime(String id, String name, int numberOfShifts, String type, int wallet) {
        this.id = id;
        this.name = name;
        this.numberOfShifts= numberOfShifts;
        this.type = type;
        this.wallet = wallet;
    }

    @Override
    public int getWallet() {
        return wallet;
    }

    @Override
    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    @Override
    public int getSalaryFull() {
        return 0;
    }

    public StaffPartTime() {
    }

    public StaffPartTime(StaffPartTimeBuilder staffBuild) {
        this.id = staffBuild.id;
        this.name = staffBuild.name;
        this.numberOfShifts = staffBuild.numberOfShifts;
        this.type = staffBuild.type;
    }

    @Override
    public String getId() {
        return id;
    }


    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getnumberOfWorkingDays() {
        return this.numberOfShifts*2;
    }

    @Override
    public void setnumberOfWorkingDays(int numberOfWorkingDays) {
        throw new IllegalArgumentException("This method does not apply to PartTime");
    }

    @Override
    public int getNumberOfShifts() {
        return numberOfShifts;
    }
    @Override
    public void setNumberOfShifts(int numberOfShifts) {
        this.numberOfShifts = numberOfShifts;
    }
    @Override
    public int getSalaryPart() {
        return salaryPart;
    }

    @Override
    public String toFile() {
        return id + "," + name + "," + numberOfShifts + "," + type + "," + wallet;
    }

    @Override
    public String getType() {
        return type;
    }
    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StaffPartTime{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberOfShifts=" + numberOfShifts +
                ", salary=" + salaryPart +
                ", wallet=" + wallet+
                ", type='" + type + '\'' +
                '}' + "\n";
    }

    public static class StaffPartTimeBuilder{
        private String id;
        private String name;
        private int numberOfShifts;
        private String type;
        private int wallet;

        public StaffPartTimeBuilder() {
        }

        public StaffPartTimeBuilder id(String id){
            this.id = id;
            return this;
        }
        public StaffPartTimeBuilder name(String name){
            this.name = name;
            return this;
        }
        public StaffPartTimeBuilder numberOfShift(int numberOfShifts){
            this.numberOfShifts = numberOfShifts;
            return this;
        }
        public StaffPartTimeBuilder type(String type){
            this.type = type;
            return this;
        }
        public StaffPartTimeBuilder wallet(int wallet){
            this.wallet = wallet;
            return this;
        }

        public StaffPartTime build(){
            return new StaffPartTime(this);
        }
    }
}
