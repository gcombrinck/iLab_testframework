package utils;

public class GenerateRandom{
    public String cellNumber;

    public String generateCellNumber() {
        long randomNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        System.out.println(randomNumber);
        //Add space after every 3rd number
        cellNumber = String.valueOf(randomNumber).replaceAll(".{3}", "$0 ");
        //Replace first number with a 0
        cellNumber = cellNumber.replace(cellNumber.charAt(0), '0');
        cellNumber = cellNumber.substring(0, cellNumber.lastIndexOf(" ")) + "" + cellNumber.substring(cellNumber.lastIndexOf(" ") + 1);
        return cellNumber;
    }
};
