package computingproject;

public class Prices {

    static double NSeatPrice = 6.50; //the price for a normal seat
    static double PSeatPrice = 8; //the price for a premium seat
    static double SavingsPerSeat = 0.05; //how much each additional seat reduces the total cost by (0.01 = 1% per seat, 0.05 = 5% per seat)
    static int MaxSavingSeats = 5; //how many seats can be added before the discount isn't applied anymore (if SavingsPerSeat = 0.05, then 5 seats means a max of 20% off (4 additional seats))

}
