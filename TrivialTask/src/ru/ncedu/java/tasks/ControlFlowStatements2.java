package ru.ncedu.java.tasks;

/**
 * ���� ������ - ��������� ������������ ����������� ����������� <code>if, case, for, while</code> � Java.<br/>
 * �������������: ����������� � �������� ������, ���������, ��������� ���������� � ������,
 *  � ����� ������� ������� ������ {@link Math}.<br/>
 * <br/>
 * �������<br/>
 * <dl>
 * <dt>�������� if</dt><br/>
 * <dd>��������� �� ��� ���� ������� � ����������� �� �������. </dd>
 * <dt>�������� case</dt>
 * <dd>������� ��������� �������� � ����������� �� �������������� ��������.</dd>
 * <dt>�������� for</dt>
 * <dd>��������� � ���������� ��������� ������. </dd>
 * <dt>�������� while</dt>
 * <dd>���������� ���������� ����������. </dd>
 * <br/>
 * ����������<br/>
 * ������ ����� ������ ��� ����� ��������� � ��������� �������������� �������� (Exceptions).<br/>
 * ��� ��� ������ ��������� ���������� ������� ����������:<br/>
 * <code>
 * public class ControlFlowStatements2Impl implements ControlFlowStatements2 {  }
 * </code>
 * <br/>
 * ����� main �� �����������, �� ��� ���� � main �� ������ ���������, ��� ���������� ���� ������, ��������: 
 * <code>
 * ControlFlowStatements2 object = new ControlFlowStatements2Impl();
 * System.out.println(object.decodeMark(4));
 * </code>
 * <br/>
 *  @author Elena Protsenko
 *  @author Alexey Evdokimov
 */
public interface ControlFlowStatements2{
	/**
	 * ��� ������� ������ x ����� �������� ��������� ������� f, ����������� �������� ������ ����:<br/>
	 * <pre>
	 *        | 2*x, ���� x<-2 ��� x>2,
	 *  f(x)= | 
	 *        | -3*x, � ��������� ������.
	 * </pre>
	 * @return �������� f.
	 */
	int getFunctionValue(int x);
	/**
	 * ���� ����� ����� mark.
	 * ������� ������-�������� ������, ��������������� ����� mark:<br/>
	 * 1 � Fail, 2 � Poor, 3 � Satisfactory, 4 � Good, 5 � Excellent.<br/> 
	 * ���� mark �� ����� � ��������� 1�5, �� ������� ������ "Error".
	 * @param mark
	 * @return ��������� ������������� mark
	 */
	String decodeMark(int mark);
	
	/**
	 * ������� ��������� ������, ���������� 5x8 ������������ ���������,
	 * � ��������� ������� �������� ��������� ��������, ��������� �� ��� ��������:
	 * array[i][j] = i � ������� 4 ����� ������ ���������� �� j.<br/>
	 * ��� ���������� � ������� � ������ ����� ������������� ������������ 
	 * ����������� ������ ������ {@link Math}.
	 * @return ������
	 */
	double[][] initArray();
	/**
	* ����� ������������ ������� ��������� ���������� �������.
	* @param array ������, ���������� ��� ������� ���� �������
	* @return ������������ ������� ������� array.
	*/
	double getMaxValue(double[][] array);
	
	/**
	 * ��������� ����� ����������, �������� � ������ ���� 10 ��.
	 * ������ ��������� ���� �� ���������� ����� ������� �� P ��������� ��
	 * ������� ����������� ���.<br/>
	 * �� ��������� P ����������, ����� ������� ���� ��������� ������ ���������� �� ��� ���
	 * �������� 200 �� (����� �����), � ��� ��������� ������ (������������ �����).
	 * @param P ������� ���������� ������� � ����
	 * @return ���������� � ������� (� ���� ���������� ������ {@link Sportsman}) ����� ����������� �������������� �������
	 */
	Sportsman calculateSportsman(float P);
	/**
	 * ��������������� ��� ��� ������ {@link ControlFlowStatements2#calculateSportsman(float)}.<br/>
	 * ����������: ����������� ��� ����� ���������, ��������� ����� ������ ������� ��� ��������,
	 * � �� ����� ��� ����� ��������� ������ �������� ������ ��� ������� ������ 
	 * (�������� ���������� � ������ � Java - ������ �� ��������, � �� �� ������).<br/>
	 * ������: ��� ����� ���� �� �������� ��� ������������ ����, ������� ��������� ������ calculateProbeg?
	 */
	public static class Sportsman{
		private int trainingDays = 0;
		private float totalDistance;
		/**
		 * ���������� ����, ������� ������������ ���������.
		 */
		public final int getTrainingDays() {
			return trainingDays;
		}
		/**
		 * ��������� ������ ���������� �� ��������� {@link #trainingDays} ����.
		 */
		public float getTotalDistance() {
			return totalDistance;
		}
		/**
		 * �������� ������ �� ������ ������ ���������� ���
		 * @param distance ������ � ���� ����
		 */
		public void addDay(float distance) {
			this.trainingDays ++;
			this.totalDistance += distance;
		}
		
		@Override
		public String toString() {
			return trainingDays+" days: "+totalDistance+" km";
		}
	}
}