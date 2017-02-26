package ru.ncedu.java.tasks;

import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector {

	private double[] arr = null;
	
	@Override
	public void set(double... elements) {
		// TODO Auto-generated method stub
		arr = elements;
	}

	@Override
	public double[] get() {
		// TODO Auto-generated method stub
		return arr;
	}

	@Override
	public ArrayVector clone() {
		// TODO Auto-generated method stub
		ArrayVector arrclone = new ArrayVectorImpl();
		arrclone.set(arr.clone());
		return arrclone;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		if (arr == null) {
			return 0;
		} else {
			return arr.length;
		}		
	}

	@Override
	public void set(int index, double value) {
		// TODO Auto-generated method stub
		if (index >= 0 && index < this.getSize()) {
			arr[index] = value;
		} else {		
			double[] newarr = new double[index + 1];
			
			for (int i = 0; i < this.getSize(); i++)
				newarr[i] = arr[i];
			
			for (int i = this.getSize(); i < index; i++)
				newarr[i] = 0;
			
			newarr[index] = index;
			arr = newarr;
		}
	}

	@Override
	public double get(int index) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (this.getSize() == 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
			
		return arr[index];
	}

	@Override
	public double getMax() {
		// TODO Auto-generated method stub	
		double max = arr[0];
		for (double a: arr) {
			if (a > max) {
				max = a;
			}
		}
		
		return max;
	}

	@Override
	public double getMin() {
		// TODO Auto-generated method stub
		double min = arr[0];
		for (double a: arr) {
			if (a < min) {
				min = a;
			}
		}
		
		return min;
	}

	@Override
	public void sortAscending() {
		// TODO Auto-generated method stub
		Arrays.sort(arr);
	}

	@Override
	public void mult(double factor) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getSize(); i++) {
			arr[i] *= factor;
		}
	}

	@Override
	public ArrayVector sum(ArrayVector anotherVector) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getSize() && i < anotherVector.getSize(); i++) {
			arr[i] += anotherVector.get(i);
		}
		
		return this;
	}

	@Override
	public double scalarMult(ArrayVector anotherVector) {
		// TODO Auto-generated method stub
		double scalar = 0;
		for (int i = 0; i < this.getSize() && i < anotherVector.getSize(); i++) {
			scalar += arr[i] * anotherVector.get(i);
		}
		
		return scalar;
	}

	@Override
	public double getNorm() {
		// TODO Auto-generated method stub
		return Math.sqrt(scalarMult(this));
	}
	
	public String toString() {
		String str = "";
		for (double a: arr) {
			str += a + " ";
		}
		
		return str;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayVector arr = new ArrayVectorImpl();
		arr.set(2,2,2);
		System.out.println(arr.getNorm());
	}
}
