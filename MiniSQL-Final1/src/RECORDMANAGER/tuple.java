package RECORDMANAGER;


import java.util.Vector;
//�������������ڴ洢Table�е�һ����¼
//ʵ��ԭ����һ��String��Vector���ַ�����ʽһ��һ���ش洢ÿ�����Զ�Ӧ��ֵ��
public class tuple {
	public Vector<String> units;
	public tuple(Vector<String> units){
		this.units=units;
	}
	public tuple(){units = new Vector<String>();}
	public String getString(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<units.size();i++){
			sb.append("\t"+units.get(i));
		}
		return sb.toString();
	}
}
