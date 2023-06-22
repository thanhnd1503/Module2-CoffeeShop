package coffeeshop;

import interfaces.Staff;

public class StaffFullTime implements Staff {
    private String id;
    private String name;
    private int numberOfWorkingDays;
    private final int salaryFull = 300000;
    private String type;

    private int wallet;


    public StaffFullTime(String id, String name, int numberOfWorkingDays, String type, int wallet) {
        this.id = id;
        this.name = name;
        this.numberOfWorkingDays = numberOfWorkingDays;
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

    public StaffFullTime() {
    }

    public StaffFullTime(StaffFullTimeBuilder staffBuild) {
        this.id = staffBuild.id;
        this.name = staffBuild.name;
        this.numberOfWorkingDays = staffBuild.numberOfWorkingDays;
        this.type = staffBuild.type;
        this.wallet = staffBuild.numberOfWorkingDays*salaryFull;
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
        return numberOfWorkingDays;
    }
    @Override
    public void setnumberOfWorkingDays(int numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    @Override
    public int getNumberOfShifts() {
        return this.numberOfWorkingDays/2;
    }

    @Override
    public void setNumberOfShifts(int numberOfShifts) {
        throw new IllegalArgumentException("This method does not apply to FullTime");
    }


    @Override
    public int getSalaryFull() {
        return salaryFull;
    }

    @Override
    public int getSalaryPart() {
        return 0;
    }

    @Override
    public String toFile() {
        return id + "," + name + "," + numberOfWorkingDays + ","  + type + "," + wallet;
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
        return "StaffFullTime{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberOfWorkingDays=" + numberOfWorkingDays +
                ", salary=" + salaryFull +
                ", wallet=" + wallet +
                ", type='" + type + '\'' +
                '}' + "\n";
    }

    public static class StaffFullTimeBuilder{
        private String id;
        private String name;
        private int numberOfWorkingDays;
        private String type;
        private int wallet;

        public StaffFullTimeBuilder() {
        }

        public StaffFullTimeBuilder id(String id){
            this.id = id;
            return this;
        }
        public StaffFullTimeBuilder name(String name){
            this.name = name;
            return this;
        }
        public StaffFullTimeBuilder numberOfWorkingDays(int numberOfWorkingDays){
            this.numberOfWorkingDays = numberOfWorkingDays;
            return this;
        }
        public StaffFullTimeBuilder type(String type){
            this.type = type;
            return this;
        }
        public StaffFullTimeBuilder wallet(int wallet){
            this.wallet = wallet;
            return this;
        }

        public StaffFullTime build(){
           return new StaffFullTime(this);
        }
    }
}
