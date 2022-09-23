package FILEMANAGER;

import java.io.File;
import GUI.*;

public class FileManager {
	
//	���������������ļ������ж��ļ��Ƿ��Ѵ���
//	ʵ��ԭ������File.exist����
	public static boolean findFile(String filename) {
		File file = new File(filename);
		if (!file.exists())
			return false;
		return true;
	}

//	���������������ļ����������ļ�
//	ʵ��ԭ������File.createNewFile()
	public static void creatFile(String filename) {
		try {
			File myFile = new File(filename);
			// �ж��ļ��Ƿ���ڣ���������������createNewFile()����������Ŀ¼�����������쳣�������
			if (!myFile.exists())
				myFile.createNewFile();
			else
				// ������������ӳ��쳣
				throw new Exception("The new file already exists!");
		} catch (Exception ex) {
			GUI.StringPrintText("�޷��������ļ���");
			ex.printStackTrace();
		}
	}
	
//	���������������ļ�����ɾ���ļ�
//	ʵ��ԭ������File.delete()
	public static void dropFile(String filename) {
		File f=new File(filename);
		if(f.exists())f.delete();		
	}
}
