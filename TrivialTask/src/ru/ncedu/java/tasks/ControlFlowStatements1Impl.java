package ru.ncedu.java.tasks;

public class ControlFlowStatements1Impl implements ControlFlowStatements1 {

	@Override
	public float getFunctionValue(float x) {
		// TODO Auto-generated method stub
		if (x > 0) {
			return (float) (2 * Math.sin(x));
		} else {
			return 6 - x;
		}
	}

	@Override
	public String decodeWeekday(int weekday) {
		// TODO Auto-generated method stub
		switch (weekday) {
		case (1):
			return "Monday";		
		case (2):
			return "Tuesday";		
		case (3):
			return "Wednesday";		
		case (4):
			return "Thursday";		
		case (5):
			return "Friday";		
		case (6):
			return "Saturday";	
		case (7):
			return "Sunday";		
		default:
			return "error";
		}
	}

	@Override
	public int[][] initArray() {
		// TODO Auto-generated method stub
		int n = 8;
		int m = 5;
		
		int[][] arr = new int[n][m];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				arr[i][j] = i * j;
		
		return arr;
	}

	@Override
	public int getMinValue(int[][] array) {
		// TODO Auto-generated method stub
		int min = array[0][0];  
		
		for (int i = 0; i < array.length; i++)
			for (int j = 0; j < array[i].length; j++)
				if (array[i][j] < min) {
					min = array[i][j];
				}
					
		return min;
	}

	@Override
	public BankDeposit calculateBankDeposit(double P) {
		// TODO Auto-generated method stub
		double max  = 5000;
		double pc = P / 100;
		
		BankDeposit bd = new BankDeposit();
		bd.amount = 1000;		
		
		while (bd.amount <= max) {
			bd.amount += bd.amount * pc;
			bd.years++;
		}
		
		return bd;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
