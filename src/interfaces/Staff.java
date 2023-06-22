package interfaces;

public interface Staff {
        String getType();
        String getId();
        String getName();
        void setName(String name);
        int getnumberOfWorkingDays();
        void setnumberOfWorkingDays(int numberOfWorkingDays);
        int getNumberOfShifts();
        void setNumberOfShifts(int numberOfShifts);
        void setType(String type);
        int getWallet();
        void setWallet(int wallet);
        int getSalaryFull();
        int getSalaryPart();

        String toFile();
}
