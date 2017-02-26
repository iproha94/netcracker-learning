package ru.ncedu.java.tasks;

public class ControlFlowStatements2Impl implements ControlFlowStatements2 {

	@Override
	public int getFunctionValue(int x) {
		// TODO Auto-generated method stub
		
		if (Math.abs(x) > 2) {
			return 2 * x;
		} else {
			return -3 * x;
		}
	}

	@Override
	public String decodeMark(int mark) {
		switch (mark) {
		case (1):
			return "Fail";		
		case (2):
			return "Poor";		
		case (3):
			return "Satisfactory";		
		case (4):
			return "Good";		
		case (5):
			return "Excellent";				
		default:
			return "Error";
		}
	}

	@Override
	public double[][] initArray() {
		// TODO Auto-generated method stub
		int n = 5;
		int m = 8;
		
		double[][] arr = new double[n][m];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				arr[i][j] = Math.pow(i, 4) - Math.sqrt(j);
		
		return arr;
	}

	@Override
	public double getMaxValue(double[][] array) {
		// TODO Auto-generated method stub
		double max = array[0][0];  
		
		for (int i = 0; i < array.length; i++)
			for (int j = 0; j < array[i].length; j++)
				if (array[i][j] > max) {
					max = array[i][j];
				}
					
		return max;
	}

	@Override
	public Sportsman calculateSportsman(float P) {
		// TODO Auto-generated method stub
		Sportsman sm = new Sportsman();
		
		sm.addDay(10);
		int max = 200;
		
		float predday = 10;
		
		while (sm.getTotalDistance() <= max) {
			predday += predday * (P / 100);
			sm.addDay(predday);
		}
		
		return sm;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControlFlowStatements2 object = new ControlFlowStatements2Impl();
		System.out.println(object.calculateSportsman(200).toString());

	}

}
