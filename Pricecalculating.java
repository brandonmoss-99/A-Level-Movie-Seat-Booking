package computingproject;

public class Pricecalculating {

    static double StandardCost; //holds the standard cost of the booking
    static double DiscountedCost; //holds the cost of the booking with discounts applied

    public static void addnormal(double NSeatPrice) { //adds price of normal seats to total cost
        StandardCost = StandardCost + Prices.NSeatPrice;
    }

    public static void addpremium(double PSeatPrice) { //adds price of premium seats to total cost
        StandardCost = StandardCost + Prices.PSeatPrice;
    }

    public static double discountcalc() { //calculates the discountedcost
        if (Seatbooking.SeatsBookedCount > Prices.MaxSavingSeats) { //checks if the amount of seats booked is greater than the maximum amount of seats for the discount
            DiscountedCost = StandardCost - ((Prices.SavingsPerSeat * Prices.MaxSavingSeats) - Prices.SavingsPerSeat) * StandardCost; //calculates cost with discount applied
            return DiscountedCost; //gives the value of the discountedcost to whatever has called this method
        } else {
            DiscountedCost = StandardCost - ((Prices.SavingsPerSeat * Seatbooking.SeatsBookedCount) - Prices.SavingsPerSeat) * StandardCost; //calculates cost with discount applied
            return DiscountedCost; //gives the value of the discountedcost to whatever has called this method
        }
    }

}
