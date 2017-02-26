package com.netcracker.edu.java.tasks;

import java.util.Arrays;

import static java.lang.Math.*;

public class ComplexNumberImpl implements ComplexNumber {
	private double re;
	private double im;
	
	public ComplexNumberImpl() {
		re = 0;
		im = 0;
	}
	
	public ComplexNumberImpl(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	@Override
	public double getRe() {
		// TODO Auto-generated method stub
		return re;
	}

	@Override
	public double getIm() {
		// TODO Auto-generated method stub
		return im;
	}

	@Override
	public boolean isReal() {
		// TODO Auto-generated method stub
		return im == 0;
	}

	@Override
	public void set(double re, double im) {
		// TODO Auto-generated method stub
		this.re = re;
		this.im = im;
	}
	
	private int indexOfSign(String str) {
		return str.indexOf('+') != -1 ? str.indexOf('+') : str.indexOf('-');
	}

	private int lastIndexOfSign(String str) {
		return str.lastIndexOf('+') != -1 ? str.lastIndexOf('+') : str.lastIndexOf('-');
	}
	
	@Override
	public void set(String value) throws NumberFormatException {
		// TODO Auto-generated method stub
		int posi = value.indexOf('i');
		if (posi == -1) {
			re = new Double(value);
			im = 0;
		} else  if (posi == 0) {
			re = 0;
			im = 1;
		} else if ((indexOfSign(value) == lastIndexOfSign(value)) && (indexOfSign(value) == -1)) {
			re = 0;
			im = new Double(value.substring(0, posi));
		} else if ((indexOfSign(value) == lastIndexOfSign(value))) {
			if (posi == indexOfSign(value) + 1) {
				im = value.lastIndexOf('+') != -1 ? 1 : -1;
			} else {
				im = new Double(value.substring(lastIndexOfSign(value), posi));
			}
			
			value = value.substring(0, lastIndexOfSign(value));
			re = "".equals(value) ? 0 : new Double(value);
		} else {
			String restr = value.substring(0, lastIndexOfSign(value));
			re = "".equals(restr) ? 0 : new Double(restr);
			
			
			if (posi == lastIndexOfSign(value) + 1) {
				im = value.lastIndexOf('+') != -1 ? 1 : -1;
			} else {
				im = new Double(value.substring(lastIndexOfSign(value), posi));
			}		
		}	
	}

	@Override
	public ComplexNumber copy() {
		// TODO Auto-generated method stub
		return new ComplexNumberImpl(re, im);
	}

	@Override
	public ComplexNumber clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new ComplexNumberImpl(re, im);
	}

	@Override
	public int compareTo(ComplexNumber other) {
		// TODO Auto-generated method stub
		Double len1 = sqrt(pow(this.getRe(), 2) + pow(this.getIm(), 2)); 
		Double len2 = sqrt(pow(other.getRe(), 2) + pow(other.getIm(), 2)); 
		return len1.compareTo(len2);
	}

	@Override
	public void sort(ComplexNumber[] array) {
		// TODO Auto-generated method stub
		Arrays.sort(array);
	}
	
	@Override
	public ComplexNumber negate() {
		// TODO Auto-generated method stub
		re *= -1;
		im *= -1;
		
		return this;
	}

	@Override
	public ComplexNumber add(ComplexNumber arg2) {
		// TODO Auto-generated method stub
		re += arg2.getRe();
		im += arg2.getIm();
		return this;
	}

	@Override
	public ComplexNumber multiply(ComplexNumber arg2) {
		// TODO Auto-generated method stub
		double a = re;
		double c = arg2.getRe();
		double b = im;
		double d = arg2.getIm();
		re = a * c - b * d;
		im = b * c + a * d;
		return this;
	}
	
	public boolean equals(Object other) {
		
		if (other instanceof ComplexNumber) {
			return (this.getIm() == ((ComplexNumber)other).getIm() && this.getRe() == ((ComplexNumber)other).getRe());
		} else {
			return false;
		}
	}
	
	public String toString() {
		if (re == 0 && im == 0)
			return "0";
		
		String str = "";
		
		if (re != 0) {
			str += re;
			
			if (im > 0) {
				str += "+";
			}
		}
		
		if (im != 0) {
			str += im + "i";
		}
		
		return str;
	}

	public static void main(String[] args) {
		ComplexNumber a = new ComplexNumberImpl();
		a.set("i");
		System.out.println(a);
	}
}
