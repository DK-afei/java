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
		System.out.println("这里是文件系统,请输入账号密码完成首次注册,注册成功将立刻登陆");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入用户名账号");
		String username;
		while ((username = br.readLine().trim()).equals(""))
			System.out.println("用户名输入有误，请重新输入");
		System.out.println("请输入密码");
		String password;
		while ((password = br.readLine().trim()).equals(""))
			System.out.println("用户密码输入有误，请重新输入");
		
		
		MyDir nowdir = new MyDir(username);
		nowdir.setPassword(password);
		System.out.println("注册成功，" + username + "，欢迎来到文件系统，如需帮助，输入help");
		MyDir dirsave = null;
		MyFile filesave = null;
		while (true) {
			System.out.print(username + "/");
			Stack<String> brid = new Stack<String>();

			MyDir k = new MyDir();
			k = nowdir;
			while (nowdir.getFatherDir() != null) // 循环更新父目录磁盘块(添加)和大小，直至根目录
			{

				brid.push(nowdir.getName() + "/");
				nowdir = nowdir.getFatherDir();
			}
			nowdir = k;
			while (!brid.empty())
				System.out.print(brid.pop());

			String s = br.readLine().trim();

			if (s.equals("ls")) // 目录显示
				nowdir.ls();
			else if (s.startsWith("cd") && !s.equals("cd ..")) { // 跳转
				String real = s.substring(3, s.length());
				MyDir a = nowdir.cd(real);
				if (a != null) {
					MyDir b = nowdir;
					nowdir = a;
					nowdir.setFatherDir(b);
				} else
					System.out.println("您输入的目录名有误，请重新输入");

			} else if (s.equals("cd ..")) { //返回
				if (nowdir.cdReturn() != null)
					nowdir = nowdir.cdReturn();
				else
					System.out.println("已经退到根目录");

			} else if (s.startsWith("touch") && s.length() > 6) { // 创建文件
				String real = s.substring(6, s.length());
				nowdir.addFile(new MyFile(real, 0, 0));
				nowdir.setFile(nowdir.getFile(real),7);//创建的文件默认权限为可读可写可执行

			}else if (s.startsWith("chmod") && s.length() > 6) { // 给文件设置权限
				String real = s.substring(6, s.length());
				if(nowdir.is_exist(real)) {
//				--- r-- rw- rwx r-x --x
//				0	4	6	7	5	1
				System.out.println("请输入要设置的权限值：（读--4，写--2，可执行--1，以上累计值决定该文件的所有权限）");
				int access = sc.nextInt();
				nowdir.setFile(nowdir.getFile(real), access);
				System.out.println("权限设置成功！");
				}else
					System.out.println("该文件不存在！");
			}else if (s.startsWith("rmfile") && s.length() > 7) { // 删除文件
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
					while (nowdir.getFatherDir() != null) // 循环更新父目录磁盘块（删除）和大小，直至根目录
					{

						nowdir.removeold(thenw);
						nowdir.updateSize();
						nowdir = nowdir.getFatherDir();
					}
					nowdir = save2;

					System.out.println("删除成功");
				} else
					System.out.println("对不起，该文件不存在");
			} else if (s.startsWith("rnmfile") && s.length() > 8) { // 文件重命名
				String real = s.substring(8, s.length());
				MyFile a = nowdir.getFile(real);
				if (a != null) {
					System.out.println("请输入新的文件名");
					String newname = br.readLine().trim();
					if (nowdir.canPasteFile(new MyFile(newname))) { // 判断是否存在同名文件
						if (!newname.equals("")) {
							nowdir.deleteFile(real);
							MyFile thnew = a;
							thnew.setName(newname);
							nowdir.addFile(thnew);
							System.out.println("文件重命名成功!!!");
						}
					} else
						System.out.println("对不起，已经存在同名文件");

				} else
					System.out.println("对不起，该文件不存在");
			} else if (s.equals("vi")) { // 文件编辑
				System.out.println("请输入文件名称");
				String filename = br.readLine();
				MyFile a = nowdir.getFile(filename);
				if (a != null) {
					if((a.getAccess()==6)||(a.getAccess()==7)) {
					System.out.println("请编辑");
					ArrayList<MyDiskBlock> list = a.getBlocklist();
					ArrayList<Integer> oldused = new ArrayList<Integer>(); // 用来保存原本文件的磁块号

					StringBuffer all = new StringBuffer();
					for (MyDiskBlock one : list) {
						oldused.add(one.getId());
						all.append(one.getContent());
						MyDisk.getInstance().deleteUsed(one.getId());
					}

					MyDir save1 = new MyDir();
					save1 = nowdir;
					while (nowdir.getFatherDir() != null) // 循环更新父目录磁盘块，直至根目录
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
						MyDisk.getInstance().addUsed(ano); // 修改后的内容保存到磁盘
						thenw.add(ano.getId());
						newFileBlocklist.add(ano); // 保存到文件
					} else {
						StringBuffer shortone = new StringBuffer(realcontent.substring(0, realcontent.length()));
						MyDiskBlock one = new MyDiskBlock(shortone);
						sizecount++;
						MyDisk.getInstance().addUsed(one);
						thenw.add(one.getId());
						newFileBlocklist.add(one);
					}
					a.setOldsize(a.getNewsize()); // 将原本的文件大小置为上一次文件大小

					int newsize = sizecount * blocksize;
					a.setNewsize(newsize); // 设置文件的最新大小

					MyDir save2 = new MyDir();
					save2 = nowdir;
					while (nowdir.getFatherDir() != null) // 循环更新父目录磁盘块(添加)和大小，直至根目录
					{

						nowdir.addnew(thenw);
						nowdir.updateSize();
						nowdir = nowdir.getFatherDir();
					}
					nowdir = save2;

					a.setBlocklist(newFileBlocklist);
					System.out.println("编辑成功！！！");
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
							while (nowdir.getFatherDir() != null) // 循环更新父目录磁盘块（删除）和大小，直至根目录
							{

								nowdir.removeold(thenw);
								nowdir.updateSize();
								nowdir = nowdir.getFatherDir();
							}
							nowdir = save20;
						System.out.println("由于磁盘可使用的磁盘块不足（即磁盘空间不足），此文件保存失败！");
						String real00 = filename;
						nowdir.addFile(new MyFile(real00, 0, 0));
						nowdir.setFile(nowdir.getFile(real00),7);
					}
					}
					else
						System.out.println("权限不够，你无法执行此操作！");
				} else
					System.out.println("对不起，文件名输入有误");
					
			} else if (s.startsWith("cat") && s.length() > 4) { // 显示文件内容
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
						System.out.println("权限不够，你无法执行此操作！");
				} else
					System.out.println("文件名称输入有误");

			} else if (s.startsWith("cpfile") && s.length() > 7) { // 文件复制
				String real = s.substring(7, s.length());
				MyFile a = nowdir.getFile(real);
				if (a != null) {
					filesave = new MyFile();
					filesave = a;
					System.out.println("复制文件到剪切板成功");
				} else
					System.out.println("对不起，文件名输入有误");
			} else if (s.equals("ptfile")) { //文件粘贴
				if (filesave != null) {
					ArrayList<MyDiskBlock> fileblocklist = filesave.getBlocklist();
					if (nowdir.canPasteFile(filesave)) { // 判断目录下是否有同名文件
						StringBuffer realcontent = new StringBuffer();
						for (MyDiskBlock one : fileblocklist) {
							realcontent.append(one.getContent()); // 先将文件中的StringBuffer全部拷贝出来
						}

						int point = 0;
						ArrayList<MyDiskBlock> newFileBlocklist = new ArrayList<MyDiskBlock>(); // 创建新的磁盘块列表用来存放这个StringBuffer
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
							MyDisk.getInstance().addUsed(ano); // 修改后的内容保存到磁盘,一定要先保存到磁盘,在分配好磁盘号之后,再保存到文件
							thenw.add(ano.getId());
							newFileBlocklist.add(ano); // 保存到文件
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
						while (nowdir.getFatherDir() != null) // 循环更新父目录磁盘块(添加)和大小，直至根目录
						{

							nowdir.addnew(thenw);
							nowdir.updateSize();
							nowdir = nowdir.getFatherDir();
						}
						nowdir = save2;

						MyFile anoth = new MyFile(filesave.getName()); // 新建一个文件用来保存复制板里的信息,不要直接用filesave,之前在此处出错,debug用时2小时,就是因为没new一个新的MyFile
						anoth.setBlocklist(newFileBlocklist);
						anoth.setNewsize(filesave.getNewsize());
						anoth.setOldsize(filesave.getOldsize());

						nowdir.addFile(anoth);
					} else
						System.out.println("该目录下已经有同名文件，不能粘贴");
				}

				else
					System.out.println("剪切板中没有文件");
			}

			else if (s.startsWith("rnmdir") && s.length() > 7) { // 目录重命名,1
				String real = s.substring(7, s.length());
				MyDir old = nowdir.getDir(real);
				if (old != null) {
					System.out.println("请输入新的目录名");
					String newname = br.readLine().trim();
					if (nowdir.canPasteDir(new MyDir(newname))) { // 确认该目录下无同名目录
						if (newname != "") {
							nowdir.deleteDir(real);
							MyDir thnew = old;
							thnew.setName(newname);
							nowdir.addDir(thnew);
							System.out.println("目录重命名成功!!!");
						} else {
							System.out.println("文件名未输入");
						}
					} else
						System.out.println("已经存在同名目录");

				} else
					System.out.println("对不起，不存在该目录");

			} else if (s.startsWith("rmdir") && s.length() > 6) { // 删除目录
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
					while (nowdir.getFatherDir() != null) // 循环更新父目录磁盘块(删除)和大小，直至根目录
					{

						nowdir.removeold(all);
						nowdir.updateSize();
						nowdir = nowdir.getFatherDir();
					}
					nowdir = save2;
				} else
					System.out.println("对不起，不存在该目录");
			}

			else if (s.startsWith("mkdir") && s.length() > 6) { // 创建目录
				String dirname = s.substring(6, s.length());
				MyDir dir = new MyDir(dirname, 0, 0);
				nowdir.addDir(dir);

			} else if (s.startsWith("cpdir") && s.length() > 6) { // 复制目录
				String real = s.substring(6, s.length());
				MyDir a = nowdir.getDir(real);
				if (a != null) {
					dirsave = new MyDir();
					dirsave = (MyDir) MyDir.cloneObject(a);
					System.out.println("目录成功复制到剪切板");
				} else
					System.out.println("对不起，不存在该目录");
			} else if (s.equals("ptdir")) { // 粘贴目录
				if (dirsave != null) {
					MyDir instance = new MyDir();
					instance = (MyDir) MyDir.cloneObject(dirsave);
					if (nowdir.canPasteDir(instance)) { // 检查无同名目录，才能添加；
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
						while (nowdir.getFatherDir() != null) // 循环更新父目录磁盘块(添加)和大小，直至根目录
						{
							nowdir.addnew(thenew);
							nowdir.updateSize();
							nowdir = nowdir.getFatherDir();
						}
						nowdir = save2;
					} else
						System.out.println("目录下已经有同名目录，无法粘贴");
				} else
					System.out.println("剪切板中没有目录");
			} else if (s.equals("show")) { // 显示磁盘使用情况
				MyDisk.getInstance().show();
			} 
			else if (s.equals("format")) { // 格式化
				System.out.println("请输入密码");
				String fm = br.readLine().trim();
				if (nowdir.getPassword().equals(fm)) 
				{
					while(nowdir.cdReturn() != null)
						nowdir = nowdir.cdReturn();
						System.out.println("已经退到根目录");
						nowdir.format_MyDir_MyFile();
						MyDisk.getInstance().format_MyDisk();
						System.out.println("格式化成功！");
				}
				else
					System.out.println("密码错误！");
			}else if (s.equals("help")) { // 显示帮助
				System.out.println("------------------------------------登陆和注销----------------------------------");
				System.out.println("login	登陆");
				System.out.println("logout	注销 ");
				System.out.println("------------------------------------显示和跳转操作------------------------------");
				System.out.println("ls	显示当前文件目录下所有文件或文件夹	");
				System.out.println("cd	跳转到指定目录		cd ..	跳转到上层目录        ");
				System.out.println("------------------------------------目录操作------------------------------------");
				System.out.println("mkdir		创建目录		rmdir	删除目录		rnmdir	重命名目录");
				System.out.println("cpdir		拷贝目录		ptdir	粘贴目录 ");
				System.out.println("------------------------------------文件操作------------------------------------");
				System.out.println("touch		创建文件		rmfile	删除文件		rnmfile	  重命名文件");
				System.out.println("cpfile		拷贝文件		ptfile	粘贴文件		cat	显示文件内容");
				System.out.println("chmod		设置权限		vi		编辑文件，输入正确的文件名就能打开进行编辑了");
				System.out.println("------------------------------------磁盘操作------------------------------------");
				System.out.println("show	显示磁盘使用情况		format	格式化");
				System.out.println("------------------------------------退出系统------------------------------------");
				System.out.println("exit");
			} else if (s.equals("exit")) {
				System.out.println("成功退出系统");
				System.exit(0);
			} else if (s.equals("cls")) {
				for (int i = 0; i <= 30; i++) {
					System.out.println();
				}
			}
			else if (s.equals("logout")) {
				MyDir thisone = new MyDir();
				thisone = nowdir;
				while (nowdir.getFatherDir() != null) // 循环更新父目录磁盘块(添加)和大小，直至根目录
				{

					brid.push(nowdir.getName() + "/");
					nowdir = nowdir.getFatherDir();
				}
				AllUser.getInstance().addUser(thisone);
				System.out.println("已注销，你可以：1、输入exit离开  2、login+用户名登陆  3、输入add+用户名  添加用户");
				String in = br.readLine().trim();
				if (in.equals("exit"))
					System.exit(0);
				else if (in.startsWith("login") && in.length() > 6) { // 登陆原有的账号
					String anouser = in.substring(6, in.length());
					if (AllUser.getInstance().whetherExist(anouser)) {
						for(int i=0;i<3;i++) 
						{
							System.out.println("请输入密码");
							String pw1 = br.readLine().trim();
							if (AllUser.getInstance().isOk(anouser, pw1)) 
							{
								nowdir = AllUser.getInstance().getUserDir(anouser);
								System.out.println("登陆成功，" + anouser + "，欢迎来到文件系统，如需帮助，输入help");
								username = anouser;
								break;
							}
							else
								System.out.println("密码错误！");
						}
					} else
						System.out.println("不存在该用户");
				} else if (in.startsWith("add") && in.length() > 4) { // 创建新账号并登录
					String newuser = in.substring(4, in.length());
					if (!AllUser.getInstance().whetherExist(newuser)) {
						nowdir = new MyDir(newuser);
						System.out.println("请输入密码");
						String pw2 = br.readLine().trim();
						nowdir.setPassword(pw2);
						System.out.println("注册成功，" + newuser + "，欢迎来到文件系统，如需帮助，输入help");
						AllUser.getInstance().addUser(nowdir);
						username = newuser;
					} 
					else
						System.out.println("已经存在同名用户");
				} 
				else 
					System.out.println("文件系统无此指令，请重新输入");
			}
			else
				System.out.println("无该指令，请重新输入");
		}
	}
}