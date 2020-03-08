/*
 * Method which checks if there is any fault transaction is TransactionCheck
 * 
 * I have used a single input for this scenario which can be modified if there are multiple inputs using List or arrays
 * 
 * 
 */

package com.CreditTransaction;

import java.time.LocalDateTime;

public class FraudDetection {

	public static void main(String args[]) {

		//Used single input scenario 
		String currentTransaction= "10d7ce2f43e35fa57d1bbf8b1e2,2014-04-29T13:15:54,10.00";
		double priceThreshold = 10.00;

		//Method arguments would be the credit card number input and threshold amount
		String faultCard = TransactionCheck(currentTransaction, priceThreshold);

		//Display if there is any fault transaction or if it is successful
		if(faultCard != null) {
			System.out.println(" Fault Transaction Card Number is "+ faultCard);
		}
		else 
			System.out.println("Transaction Successful");
	}



	/* Method to check if there is any fault transaction */
	public static String TransactionCheck(String currentTransaction, double priceThreshold) {

		String cardNumber = currentTransaction.split(",")[0];
		String date =  currentTransaction.split(",")[1];
		double currentAmount = Double.parseDouble(currentTransaction.split(",")[2]);

		int amountExceedFlag =0;
		double totalAmount = 0;
		String transactionHistory [ ] = {"10d7ce2f43e35fa57d1bbf8b1e2","2014-04-29T13:15:54","20.00","10dce2f43e35fa57d1bbf8b172","2014-04-29T13:15:54","15.35"};

		LocalDateTime currentDT = LocalDateTime.parse(date);
		int currentDate =currentDT.getDayOfMonth();

		//Checking the transaction details within the transaction history
		for( int i = 0; i <transactionHistory.length; i++) {

			if(transactionHistory[i].equals(cardNumber))
			{

				LocalDateTime prevDT = LocalDateTime.parse(transactionHistory[i+1]);
				int prevDate = prevDT.getDayOfMonth();

				//Check if current and previous transactions are on the same date if so add the amounts and check if it exceeds threshold
				if( currentDate == prevDate) {	

					totalAmount  = Double.parseDouble(transactionHistory[i+2]) +  currentAmount;

					if(totalAmount >= priceThreshold)
						
						amountExceedFlag++;
					else
						amountExceedFlag = 0;
				}
			}
		}

		if(amountExceedFlag > 0){
			return cardNumber;
		}
		else return null;


	}
}
