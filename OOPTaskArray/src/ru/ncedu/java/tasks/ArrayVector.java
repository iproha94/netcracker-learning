package ru.ncedu.java.tasks;

/**
 * ���� ������ - ����������� � ��������� � ������� � Java, ���������� ��������� ������,
 *  ������� ����������� ������� (����������� �������).
 * 
 * �������
 * ����������� ����� ��� ������ � �������� (����� ������������ �����, ���������) 
 *  � ������� �������� ��������� ����������.
 * 
 * ����������
 * ��������� ������, ������������ ������ ���������, ������ ��������������� ������ �������
 *  � ������� �������� ������� � ���� ���� "������".
 * ������ ������������ �������� ���������� Java, ����� ������� Math � Arrays.
 * ����� ������������� ������ ���� ����������� ��� ���������� (default constructor).
 * ����� ������ �������� ��������� ����� ������ ������ ������������ � ��� ������ set-������.
 * 
 * ����������
 * ������ ����� ������ ��� ����� ��������� � ��������� �������������� �������� (Exceptions).
 * 
 * @author Andrey Gavrilov
 * @author Alexander Kharichkin
 * @author Alexey Evdokimov
 */
public interface ArrayVector {
    /**
     * ������ ��� �������� ������� (���������� ����� �������).
     * ������������ ������ �� �����������.
     * @param elements �� ����� null
     */
    void set(double... elements);
    /**
     * ���������� ��� �������� �������. ������ �� �����������.
     */
    double[] get();
    /**
     * ���������� ����� ������� (�����, ��������� ��������� 
     *  � ������� �� �������� � ��������� ��������� ������� �������).
     */
    ArrayVector clone();
    /**
     * ���������� ����� ��������� �������.
     */
    int getSize();

    /**
     * �������� ������� �� �������. 
     * @param index � ������ ������ ������� �� ������� �������:
     *  �) ���� index<0, ������ �� ����������;
     *  �) ���� index>=0, ������ ������� ������������� ���, ����� index ���� ���������.
     */
    void set(int index, double value);
    /**
     * ���������� ������� �� �������.
     * @param index � ������ ������ ������� �� ������� ������� 
     *   ������ �������������� ArrayIndexOutOfBoundsException
     */
    double get(int index) throws ArrayIndexOutOfBoundsException;

    /**
     * ���������� ������������ ������� �������.
     */
    double getMax();
    /**
     * ���������� ����������� ������� �������.
     */
    double getMin();
    /**
     * ��������� �������� ������� � ������� �����������.
     */
    void sortAscending();

    /**
     * �������� ������ �� �����
     * @param factor
     */
    void mult(double factor);
    /**
     * ���������� ������ � ������ ��������, ��������� ���������� � ��������� ������� �������.
     * ���� ������� ����� ������ ������, ��������� �������� �������� ������� �� �����������.
     * @param anotherVector �� ����� null
     * @return ������ �� ���� (��������� ��������)
     */
    ArrayVector sum(ArrayVector anotherVector);
    /**
     * ���������� ��������� ������������ ���� ��������.
     * ���� ������� ����� ������ ������, ��������� �������� �������� ������� �� �����������.
     * @param anotherVector �� ����� null
     */
    double scalarMult(ArrayVector anotherVector);  
    /**
     * ���������� ��������� ����� ������� (����� ������� 
     *  � n-������ ���������� ������������, n={@link #getSize()}).
     * ������� ����� ������� ����� ���������� ������������ ������� �� ����.
     */
    double getNorm();
}