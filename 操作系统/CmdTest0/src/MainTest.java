import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import util.AllUser;
import util.MyDir;
import util.MyDisk;
import util.MyDiskBlock;
import util.MyFile;

public class MainTest {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("�������ļ�ϵͳ,�������˺���������״�ע��,ע��ɹ������̵�½");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("�������û����˺�");
		String username;
		while ((username = br.readLine().trim()).equals(""))
			System.out.println("�û���������������������");
		System.out.println("����������");
		String password;
		while ((password = br.readLine().trim()).equals(""))
			System.out.println("�û�����������������������");
		
		
		MyDir nowdir = new MyDir(username);
		nowdir.setPassword(password);
		System.out.println("ע��ɹ���" + username + "����ӭ�����ļ�ϵͳ���������������help");
		MyDir dirsave = null;
		MyFile filesave = null;
		while (true) {
			System.out.print(username + "/");
			Stack<String> brid = new Stack<String>();

			MyDir k = new MyDir();
			k = nowdir;
			while (nowdir.getFatherDir() != null) // ѭ�����¸�Ŀ¼���̿�(���)�ʹ�С��ֱ����Ŀ¼
			{

				brid.push(nowdir.getName() + "/");
				nowdir = nowdir.getFatherDir();
			}
			nowdir = k;
			while (!brid.empty())
				System.out.print(brid.pop());

			String s = br.readLine().trim();

			if (s.equals("ls")) // Ŀ¼��ʾ
				nowdir.ls();
			else if (s.startsWith("cd") && !s.equals("cd ..")) { // ��ת
				String real = s.substring(3, s.length());
				MyDir a = nowdir.cd(real);
				if (a != null) {
					MyDir b = nowdir;
					nowdir = a;
					nowdir.setFatherDir(b);
				} else
					System.out.println("�������Ŀ¼����������������");

			} else if (s.equals("cd ..")) { //����
				if (nowdir.cdReturn() != null)
					nowdir = nowdir.cdReturn();
				else
					System.out.println("�Ѿ��˵���Ŀ¼");

			} else if (s.startsWith("touch") && s.length() > 6) { // �����ļ�
				String real = s.substring(6, s.length());
				nowdir.addFile(new MyFile(real, 0, 0));
				nowdir.setFile(nowdir.getFile(real),7);//�������ļ�Ĭ��Ȩ��Ϊ�ɶ���д��ִ��

			}else if (s.startsWith("chmod") && s.length() > 6) { // ���ļ�����Ȩ��
				String real = s.substring(6, s.length());
				if(nowdir.is_exist(real)) {
//				--- r-- rw- rwx r-x --x
//				0	4	6	7	5	1
				System.out.println("������Ҫ���õ�Ȩ��ֵ������--4��д--2����ִ��--1�������ۼ�ֵ�������ļ�������Ȩ�ޣ�");
				int access = sc.nextInt();
				nowdir.setFile(nowdir.getFile(real), access);
				System.out.println("Ȩ�����óɹ���");
				}else
					System.out.println("���ļ������ڣ�");
			}else if (s.startsWith("rmfile") && s.length() > 7) { // ɾ���ļ�
				String real = s.substring(7, s.length());
				MyFile a = nowdir.getFile(real);
				if (a != null) {
					ArrayList<MyDiskBlock> blocklist = a.getBlocklist();
					ArrayList<Integer> thenw = new ArrayList<Integer>();
					for (MyDiskBlock one : blocklist) {
						MyDisk.getInstance().deleteUsed(one.getId());
						thenw.add(one.getId());
					}
					nowdir.deleteFile(real);

					MyDir save2 = new MyDir();
					save2 = nowdir;
					while (nowdir.getFatherDir() != null) // ѭ�����¸�Ŀ¼���̿飨ɾ�����ʹ�С��ֱ����Ŀ¼
					{

						nowdir.removeold(thenw);
						nowdir.updateSize();
						nowdir = nowdir.getFatherDir();
					}
					nowdir = save2;

					System.out.println("ɾ���ɹ�");
				} else
					System.out.println("�Բ��𣬸��ļ�������");
			} else if (s.startsWith("rnmfile") && s.length() > 8) { // �ļ�������
				String real = s.substring(8, s.length());
				MyFile a = nowdir.getFile(real);
				if (a != null) {
					System.out.println("�������µ��ļ���");
					String newname = br.readLine().trim();
					if (nowdir.canPasteFile(new MyFile(newname))) { // �ж��Ƿ����ͬ���ļ�
						if (!newname.equals("")) {
							nowdir.deleteFile(real);
							MyFile thnew = a;
							thnew.setName(newname);
							nowdir.addFile(thnew);
							System.out.println("�ļ��������ɹ�!!!");
						}
					} else
						System.out.println("�Բ����Ѿ�����ͬ���ļ�");

				} else
					System.out.println("�Բ��𣬸��ļ�������");
			} else if (s.equals("vi")) { // �ļ��༭
				System.out.println("�������ļ�����");
				String filename = br.readLine();
				MyFile a = nowdir.getFile(filename);
				if (a != null) {
					if((a.getAccess()==6)||(a.getAccess()==7)) {
					System.out.println("��༭");
					ArrayList<MyDiskBlock> list = a.getBlocklist();
					ArrayList<Integer> oldused = new ArrayList<Integer>(); // ��������ԭ���ļ��Ĵſ��

					StringBuffer all = new StringBuffer();
					for (MyDiskBlock one : list) {
						oldused.add(one.getId());
						all.append(one.getContent());
						MyDisk.getInstance().deleteUsed(one.getId());
					}

					MyDir save1 = new MyDir();
					save1 = nowdir;
					while (nowdir.getFatherDir() != null) // ѭ�����¸�Ŀ¼���̿飬ֱ����Ŀ¼
					{

						nowdir.removeold(oldused);
						nowdir.updateSize();
						nowdir = nowdir.getFatherDir();
					}
					nowdir = save1;

					System.out.println(all);
					StringBuffer realcontent = new StringBuffer(br.readLine());
					int point = 0;
					ArrayList<MyDiskBlock> newFileBlocklist = new ArrayList<MyDiskBlock>();

					ArrayList<Integer> thenw = new ArrayList<Integer>();
					int blocksize = MyDiskBlock.getSize();
					int sizecount = 0;
					if (realcontent.length() > blocksize) {
						for (int i = 0; i < realcontent.length() - blocksize; i = i + blocksize) {
							StringBuffer op = new StringBuffer(realcontent.substring(i, i + blocksize));
							MyDiskBlock newblock = new MyDiskBlock(op);
							MyDisk.getInstance().addUsed(newblock);
							thenw.add(newblock.getId());
							newFileBlocklist.add(newblock);
							point = i;
							sizecount++;
						}
						StringBuffer rest = new StringBuffer(
								realcontent.substring(point + blocksize, realcontent.length()));
						sizecount++;
						MyDiskBlock ano = new MyDiskBlock(rest);
						MyDisk.getInstance().addUsed(ano); // �޸ĺ�����ݱ��浽����
						thenw.add(ano.getId());
						newFileBlocklist.add(ano); // ���浽�ļ�
					} else {
						StringBuffer shortone = new StringBuffer(realcontent.substring(0, realcontent.length()));
						MyDiskBlock one = new MyDiskBlock(shortone);
						sizecount++;
						MyDisk.getInstance().addUsed(one);
						thenw.add(one.getId());
						newFileBlocklist.add(one);
					}
					a.setOldsize(a.getNewsize()); // ��ԭ�����ļ���С��Ϊ��һ���ļ���С

					int newsize = sizecount * blocksize;
					a.setNewsize(newsize); // �����ļ������´�С

					MyDir save2 = new MyDir();
					save2 = nowdir;
					while (nowdir.getFatherDir() != null) // ѭ�����¸�Ŀ¼���̿�(���)�ʹ�С��ֱ����Ŀ¼
					{

						nowdir.addnew(thenw);
						nowdir.updateSize();
						nowdir = nowdir.getFatherDir();
					}
					nowdir = save2;

					a.setBlocklist(newFileBlocklist);
					System.out.println("�༭�ɹ�������");
					if(MyDisk.getInstance().getRestNum()<0)
					{
						String real0 = filename;
						MyFile a0 = nowdir.getFile(real0);
							ArrayList<MyDiskBlock> blocklist = a0.getBlocklist();
							ArrayList<Integer> thenw0 = new ArrayList<Integer>();
							for (MyDiskBlock one : blocklist) {
								MyDisk.getInstance().deleteUsed(one.getId());
								thenw0.add(one.getId());
							}
							nowdir.deleteFile(real0);

							MyDir save20 = new MyDir();
							save20 = nowdir;
							while (nowdir.getFatherDir() != null) // ѭ�����¸�Ŀ¼���̿飨ɾ�����ʹ�С��ֱ����Ŀ¼
							{

								nowdir.removeold(thenw);
								nowdir.updateSize();
								nowdir = nowdir.getFatherDir();
							}
							nowdir = save20;
						System.out.println("���ڴ��̿�ʹ�õĴ��̿鲻�㣨�����̿ռ䲻�㣩�����ļ�����ʧ�ܣ�");
						String real00 = filename;
						nowdir.addFile(new MyFile(real00, 0, 0));
						nowdir.setFile(nowdir.getFile(real00),7);
					}
					}
					else
						System.out.println("Ȩ�޲��������޷�ִ�д˲�����");
				} else
					System.out.println("�Բ����ļ�����������");
					
			} else if (s.startsWith("cat") && s.length() > 4) { // ��ʾ�ļ�����
				String filename = s.substring(4, s.length());
				MyFile a = nowdir.getFile(filename);
				if (a != null) {
					if(a.getAccess()>3) {
					ArrayList<MyDiskBlock> list = a.getBlocklist();

					StringBuffer all = new StringBuffer();
					for (MyDiskBlock one : list) {
						all.append(one.getContent());
					}

					System.out.println(all);
					}else
						System.out.println("Ȩ�޲��������޷�ִ�д˲�����");
				} else
					System.out.println("�ļ�������������");

			} else if (s.startsWith("cpfile") && s.length() > 7) { // �ļ�����
				String real = s.substring(7, s.length());
				MyFile a = nowdir.getFile(real);
				if (a != null) {
					filesave = new MyFile();
					filesave = a;
					System.out.println("�����ļ������а�ɹ�");
				} else
					System.out.println("�Բ����ļ�����������");
			} else if (s.equals("ptfile")) { //�ļ�ճ��
				if (filesave != null) {
					ArrayList<MyDiskBlock> fileblocklist = filesave.getBlocklist();
					if (nowdir.canPasteFile(filesave)) { // �ж�Ŀ¼���Ƿ���ͬ���ļ�
						StringBuffer realcontent = new StringBuffer();
						for (MyDiskBlock one : fileblocklist) {
							realcontent.append(one.getContent()); // �Ƚ��ļ��е�StringBufferȫ����������
						}

						int point = 0;
						ArrayList<MyDiskBlock> newFileBlocklist = new ArrayList<MyDiskBlock>(); // �����µĴ��̿��б�����������StringBuffer
						int blocksize = MyDiskBlock.getSize();
						int sizecount = 0;
						ArrayList<Integer> thenw = new ArrayList<Integer>();
						if (realcontent.length() > blocksize) {
							for (int i = 0; i < realcontent.length() - blocksize; i = i + blocksize) {
								StringBuffer op = new StringBuffer(realcontent.substring(i, i + blocksize));
								MyDiskBlock newblock = new MyDiskBlock(op);
								MyDisk.getInstance().addUsed(newblock); 
								thenw.add(newblock.getId());
								newFileBlocklist.add(newblock);
								point = i;
								sizecount++;
							}
							StringBuffer rest = new StringBuffer(
									realcontent.substring(point + blocksize, realcontent.length()));
							sizecount++;
							MyDiskBlock ano = new MyDiskBlock(rest);
							MyDisk.getInstance().addUsed(ano); // �޸ĺ�����ݱ��浽����,һ��Ҫ�ȱ��浽����,�ڷ���ô��̺�֮��,�ٱ��浽�ļ�
							thenw.add(ano.getId());
							newFileBlocklist.add(ano); // ���浽�ļ�
						} else {
							StringBuffer shortone = new StringBuffer(realcontent.substring(0, realcontent.length()));
							MyDiskBlock one = new MyDiskBlock(shortone);
							sizecount++;
							MyDisk.getInstance().addUsed(one);
							thenw.add(one.getId());
							newFileBlocklist.add(one);
						}

						MyDir save2 = new MyDir();
						save2 = nowdir;
						while (nowdir.getFatherDir() != null) // ѭ�����¸�Ŀ¼���̿�(���)�ʹ�С��ֱ����Ŀ¼
						{

							nowdir.addnew(thenw);
							nowdir.updateSize();
							nowdir = nowdir.getFatherDir();
						}
						nowdir = save2;

						MyFile anoth = new MyFile(filesave.getName()); // �½�һ���ļ��������渴�ư������Ϣ,��Ҫֱ����filesave,֮ǰ�ڴ˴�����,debug��ʱ2Сʱ,������Ϊûnewһ���µ�MyFile
						anoth.setBlocklist(newFileBlocklist);
						anoth.setNewsize(filesave.getNewsize());
						anoth.setOldsize(filesave.getOldsize());

						nowdir.addFile(anoth);
					} else
						System.out.println("��Ŀ¼���Ѿ���ͬ���ļ�������ճ��");
				}

				else
					System.out.println("���а���û���ļ�");
			}

			else if (s.startsWith("rnmdir") && s.length() > 7) { // Ŀ¼������,1
				String real = s.substring(7, s.length());
				MyDir old = nowdir.getDir(real);
				if (old != null) {
					System.out.println("�������µ�Ŀ¼��");
					String newname = br.readLine().trim();
					if (nowdir.canPasteDir(new MyDir(newname))) { // ȷ�ϸ�Ŀ¼����ͬ��Ŀ¼
						if (newname != "") {
							nowdir.deleteDir(real);
							MyDir thnew = old;
							thnew.setName(newname);
							nowdir.addDir(thnew);
							System.out.println("Ŀ¼�������ɹ�!!!");
						} else {
							System.out.println("�ļ���δ����");
						}
					} else
						System.out.println("�Ѿ�����ͬ��Ŀ¼");

				} else
					System.out.println("�Բ��𣬲����ڸ�Ŀ¼");

			} else if (s.startsWith("rmdir") && s.length() > 6) { // ɾ��Ŀ¼
				String real = s.substring(6, s.length());
				MyDir old = nowdir.getDir(real);
				if (old != null) {
					ArrayList<Integer> all = new ArrayList<Integer>();
					all = old.getUsedblock();
					for (Integer a : all) {
						MyDisk.getInstance().deleteUsed(a);
					}

					nowdir.deleteDir(real);

					MyDir save2 = new MyDir();
					save2 = nowdir;
					while (nowdir.getFatherDir() != null) // ѭ�����¸�Ŀ¼���̿�(ɾ��)�ʹ�С��ֱ����Ŀ¼
					{

						nowdir.removeold(all);
						nowdir.updateSize();
						nowdir = nowdir.getFatherDir();
					}
					nowdir = save2;
				} else
					System.out.println("�Բ��𣬲����ڸ�Ŀ¼");
			}

			else if (s.startsWith("mkdir") && s.length() > 6) { // ����Ŀ¼
				String dirname = s.substring(6, s.length());
				MyDir dir = new MyDir(dirname, 0, 0);
				nowdir.addDir(dir);

			} else if (s.startsWith("cpdir") && s.length() > 6) { // ����Ŀ¼
				String real = s.substring(6, s.length());
				MyDir a = nowdir.getDir(real);
				if (a != null) {
					dirsave = new MyDir();
					dirsave = (MyDir) MyDir.cloneObject(a);
					System.out.println("Ŀ¼�ɹ����Ƶ����а�");
				} else
					System.out.println("�Բ��𣬲����ڸ�Ŀ¼");
			} else if (s.equals("ptdir")) { // ճ��Ŀ¼
				if (dirsave != null) {
					MyDir instance = new MyDir();
					instance = (MyDir) MyDir.cloneObject(dirsave);
					if (nowdir.canPasteDir(instance)) { // �����ͬ��Ŀ¼��������ӣ�
						instance.setFatherDir(nowdir);
						ArrayList<Integer> oldused = instance.getUsedblock();

						ArrayList<Integer> thenew = new ArrayList<Integer>();
						ArrayList<MyDiskBlock> bridge = new ArrayList<MyDiskBlock>();
						for (int i = 0; i < oldused.size(); i++) {
							MyDiskBlock a = new MyDiskBlock();
							MyDisk.getInstance().addUsed(a);
							thenew.add(a.getId());

						}
						instance.setUsedblock(thenew);
						nowdir.addDir(instance);

						MyDir save2 = new MyDir();
						save2 = nowdir;
						while (nowdir.getFatherDir() != null) // ѭ�����¸�Ŀ¼���̿�(���)�ʹ�С��ֱ����Ŀ¼
						{
							nowdir.addnew(thenew);
							nowdir.updateSize();
							nowdir = nowdir.getFatherDir();
						}
						nowdir = save2;
					} else
						System.out.println("Ŀ¼���Ѿ���ͬ��Ŀ¼���޷�ճ��");
				} else
					System.out.println("���а���û��Ŀ¼");
			} else if (s.equals("show")) { // ��ʾ����ʹ�����
				MyDisk.getInstance().show();
			} 
			else if (s.equals("format")) { // ��ʽ��
				System.out.println("����������");
				String fm = br.readLine().trim();
				if (nowdir.getPassword().equals(fm)) 
				{
					while(nowdir.cdReturn() != null)
						nowdir = nowdir.cdReturn();
						System.out.println("�Ѿ��˵���Ŀ¼");
						nowdir.format_MyDir_MyFile();
						MyDisk.getInstance().format_MyDisk();
						System.out.println("��ʽ���ɹ���");
				}
				else
					System.out.println("�������");
			}else if (s.equals("help")) { // ��ʾ����
				System.out.println("------------------------------------��½��ע��----------------------------------");
				System.out.println("login	��½");
				System.out.println("logout	ע�� ");
				System.out.println("------------------------------------��ʾ����ת����------------------------------");
				System.out.println("ls	��ʾ��ǰ�ļ�Ŀ¼�������ļ����ļ���	");
				System.out.println("cd	��ת��ָ��Ŀ¼		cd ..	��ת���ϲ�Ŀ¼        ");
				System.out.println("------------------------------------Ŀ¼����------------------------------------");
				System.out.println("mkdir		����Ŀ¼		rmdir	ɾ��Ŀ¼		rnmdir	������Ŀ¼");
				System.out.println("cpdir		����Ŀ¼		ptdir	ճ��Ŀ¼ ");
				System.out.println("------------------------------------�ļ�����------------------------------------");
				System.out.println("touch		�����ļ�		rmfile	ɾ���ļ�		rnmfile	  �������ļ�");
				System.out.println("cpfile		�����ļ�		ptfile	ճ���ļ�		cat	��ʾ�ļ�����");
				System.out.println("chmod		����Ȩ��		vi		�༭�ļ���������ȷ���ļ������ܴ򿪽��б༭��");
				System.out.println("------------------------------------���̲���------------------------------------");
				System.out.println("show	��ʾ����ʹ�����		format	��ʽ��");
				System.out.println("------------------------------------�˳�ϵͳ------------------------------------");
				System.out.println("exit");
			} else if (s.equals("exit")) {
				System.out.println("�ɹ��˳�ϵͳ");
				System.exit(0);
			} else if (s.equals("cls")) {
				for (int i = 0; i <= 30; i++) {
					System.out.println();
				}
			}
			else if (s.equals("logout")) {
				MyDir thisone = new MyDir();
				thisone = nowdir;
				while (nowdir.getFatherDir() != null) // ѭ�����¸�Ŀ¼���̿�(���)�ʹ�С��ֱ����Ŀ¼
				{

					brid.push(nowdir.getName() + "/");
					nowdir = nowdir.getFatherDir();
				}
				AllUser.getInstance().addUser(thisone);
				System.out.println("��ע��������ԣ�1������exit�뿪  2��login+�û�����½  3������add+�û���  ����û�");
				String in = br.readLine().trim();
				if (in.equals("exit"))
					System.exit(0);
				else if (in.startsWith("login") && in.length() > 6) { // ��½ԭ�е��˺�
					String anouser = in.substring(6, in.length());
					if (AllUser.getInstance().whetherExist(anouser)) {
						for(int i=0;i<3;i++) 
						{
							System.out.println("����������");
							String pw1 = br.readLine().trim();
							if (AllUser.getInstance().isOk(anouser, pw1)) 
							{
								nowdir = AllUser.getInstance().getUserDir(anouser);
								System.out.println("��½�ɹ���" + anouser + "����ӭ�����ļ�ϵͳ���������������help");
								username = anouser;
								break;
							}
							else
								System.out.println("�������");
						}
					} else
						System.out.println("�����ڸ��û�");
				} else if (in.startsWith("add") && in.length() > 4) { // �������˺Ų���¼
					String newuser = in.substring(4, in.length());
					if (!AllUser.getInstance().whetherExist(newuser)) {
						nowdir = new MyDir(newuser);
						System.out.println("����������");
						String pw2 = br.readLine().trim();
						nowdir.setPassword(pw2);
						System.out.println("ע��ɹ���" + newuser + "����ӭ�����ļ�ϵͳ���������������help");
						AllUser.getInstance().addUser(nowdir);
						username = newuser;
					} 
					else
						System.out.println("�Ѿ�����ͬ���û�");
				} 
				else 
					System.out.println("�ļ�ϵͳ�޴�ָ�����������");
			}
			else
				System.out.println("�޸�ָ�����������");
		}
	}
}