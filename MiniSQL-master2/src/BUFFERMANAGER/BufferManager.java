package BUFFERMANAGER;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BufferManager {
	static final int NUMOFBLOCKS = 20;
	static final int NOTEXIST = -1;

	private static Block[] blocks = new Block[NUMOFBLOCKS];// ��һ��block���������buffer,�ܹ�ռ��80k�Ŀռ�
	private static int pointer = 0;//����ʵ��ʱ���㷨���ÿ���buffer�е��±����ָ��
	
//	������������ʼ������ʹ��Buffer Managerǰ����һ��
//	ʵ��ԭ��Ϊbuffer�����ڴ�ռ�
	public static void initialize() {
		for (int i = 0; i < NUMOFBLOCKS; i++)
			blocks[i] = new Block();
	}

//	�����������ر�Buffer Manager,���˳�����֮ǰ����
//	ʵ��ԭ����Buffer�е�������д���ļ�
	public static void close() {
		for (int i = 0; i < NUMOFBLOCKS; i++) {
			if (blocks[i].valid==true)
				writeToDisk(i);
		}
	}
	
	
	public static void dropblocks(String filename){
		for (int i = 0; i < NUMOFBLOCKS; i++)
			if (blocks[i].filename.equals(filename))
				blocks[i].valid=false;												
	}

//	���������������ļ����Ϳ��ţ�����һ��block
//	ʵ��ԭ������findBlock����������Ƿ���buffer�У����򷵻����block�����򣬵���getFreeBlockNum�õ�buffer�п��õ�һ�����±꣬���ļ��е����ݶ�������飬����������顣
	public static Block getBlock(String filename, int blockoffset) {
		// ָ���ļ����͵ڼ���block������һ��BLOCK
		int num = findBlock(filename, blockoffset);
		if (num != NOTEXIST)
			return blocks[num];
		else {
			num = getFreeBlockNum();
			File file = new File(filename);
			if (!file.exists()) {
				blocks[num].blockoffset = blockoffset;
				blocks[num].filename = filename;
				for (int i = 0; i < Block.BLOCKSIZE; i++)
					blocks[num].data[i] = 0;
				return blocks[num];
			}
			readFromDisk(filename, blockoffset, num);
			return blocks[num];
		}
	}

//	���������������ļ����Ϳ��ţ�����һ��block��buffer�е��±꣬�������buffer���򷵻�-1
//	ʵ��ԭ����buffer�е����п���б���������
	private static int findBlock(String filename, int blockoffset) {
		for (int i = 0; i < NUMOFBLOCKS; i++)
			if (blocks[i].valid)
				if(blocks[i].filename.equals(filename))
					if(blocks[i].blockoffset == blockoffset) {
				return i;
			}
		return NOTEXIST;
	}

//	��������������һ���ɱ��滻��ȥ��block���±ꡣ������ʹ��ʱ���㷨����ѡ�񣬲���������������buffer�еĿ顣
//	ʵ��ԭ����pointerָ��buffer����һ���顣�����fixedΪ1����pointerָ����һ���鲢������һ��ѭ�����������ָ��Ŀ�reference_bitΪ1�����reference_bitλ��1�����ָ��Ŀ�reference_bitΪ0����������д���ļ���������pointer��ֵ��	
	private static int getFreeBlockNum() {
		/* ����Clock�㷨��һ������LRU���㷨�������ų�����������block�������滻 */
		/* ������е�block����������buffer�У��˴���������ѭ�� */
		do {
			pointer = (pointer + 1) % NUMOFBLOCKS;
			if (blocks[pointer].reference_bit == true
					&& blocks[pointer].fixed == false)
				blocks[pointer].reference_bit = false;
			else if (blocks[pointer].reference_bit == false) {
				writeToDisk(pointer);
				return pointer;
			}
		} while (true);
	}

//	���������������ļ�������ƫ�ƣ������ݴ��ļ���ȡ��buffer���±�Ϊnum�Ŀ��У����Ա��λ���г�ʼ������Чλ��reference_bit��1��dirty��fixedλ��0��
//	ʵ��ԭ��ͨ��RandomAccessFileִ���ļ���ȡ
	private static boolean readFromDisk(String filename, int blockoffset,
			int num) {
		File file = null;
		RandomAccessFile raf = null;
		blocks[num].filename = filename;
		blocks[num].blockoffset = blockoffset;
		blocks[num].valid = true;
		blocks[num].reference_bit = true;
		blocks[num].dirty = false;
		blocks[num].fixed = false;
		for (int i = 0; i < Block.BLOCKSIZE; i++)
			blocks[num].data[i] = 0;
		try {
			file = new File(filename);
			raf = new RandomAccessFile(file, "rw");

			if (raf.length() >= blocks[num].blockoffset * Block.BLOCKSIZE
					+ Block.BLOCKSIZE) {
				raf.seek(blockoffset * Block.BLOCKSIZE);
				raf.read(blocks[num].data, 0, Block.BLOCKSIZE);
			} else
				for (int j = 0; j < Block.BLOCKSIZE; j++)
					blocks[num].data[j] = 0;
			raf.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (raf != null) {
					raf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

//	������������buffer���±�Ϊnum�Ŀ�д�ص��ļ��У����ѿ����Чλ��0
//	ʵ��ԭ�����dirtyλΪ0����ִ��д����������ִ��д������
	private static void writeToDisk(int num) {
		if (blocks[num].dirty == false) {
			blocks[num].valid = false;
			return;
		} else {
			File file = null;
			RandomAccessFile raf = null;
			try {
				file = new File(blocks[num].filename);
				raf = new RandomAccessFile(file, "rw");
				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				raf.seek(blocks[num].blockoffset * Block.BLOCKSIZE);
				raf.write(blocks[num].data);
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (raf != null) {
						raf.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			blocks[num].valid = false;
		}
	}
}
