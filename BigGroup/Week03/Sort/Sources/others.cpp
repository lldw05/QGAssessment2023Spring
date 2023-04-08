#include"../Headers/sort.h"

/*缓冲 使用完某个功能后 输入w清空窗口回到菜单... */
void buffer() {
	cout << "------------" << endl;
	cout << "输入任意继续......" << endl;
	string s; cin >> s;
	system("cls");
	menu();
}

void getData(int *a,int n,int MAX) {

	//fstream out("../data.txt");//打开文件
	//srand((unsigned)time(NULL));//随机种子 
	////int MAX = 9999;//min max的值可以自己随便设置
	//int MIN = 0;
	//for (int i = 0; i < n; i++)
	//	out << rand() %MAX+1 << endl;//产生的数据在min到max之间 
	//out.close();//关闭文件

	////读取数据文件
	//ifstream in("../", ios::in);
	//if (!in.is_open())
	//{
	//	cout << "open error!" << endl;
	//	return;
	//}
	////将数据文件数据存入数组
	//int i = 0;
	//while (!in.eof() && i < n)
	//{
	//	in >> a[i++];
	//}
	//return;

	srand((unsigned)time(NULL));//随机种子 
	for (int i = 0; i < n; i++) {
		a[i] = rand() % (MAX + 1);//0-MAX 包括0和MAX
	}
}
void printff(int* a,int size ) {
	for (int i = 0; i < size; i++) {
		cout << a[i] << " ";
		//if (i % 10 == 0) cout << endl;
	}
}

//*在菜单栏时调用的输入函数 0-9  返回char字符
char cinmenu() {
	cout << "请输入0-9的数字orz:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~请重新输入0-9的数字orz:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '9'||p=='z') {
			ok = 1;
			cout << "输入成功:" << p << endl;
			//cout << "-----------------------" << endl;
			cout << endl << endl;
			break;
		}
		cout << "输入错误喵~请重新输入0-9的数字orz:" << endl;
	}
	return p;
}


bool alreadyGetData(int* a) {
	if (!a) {
		return false;
	}
	else {
		return true;
	}
}

void xiabo() {
	//下播！
	cout << "谢谢使用喵" << endl;
	exit(0);
}

/*输入函数 只能输入1到4直接的数字 其他则会报错*/
int cinnum() {
	cout << "请输入一个1到5之间的数字:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~请重新输入1到5之间的数字:" << endl;
			continue;
		}
		p = s[0];
		if ('1' <= p && p <= '5') {
			ok = 1;
			cout << "输入成功:" << p << endl;
			break;
		}
		cout << "输入错误喵~请重新输入1到5之间的数字:" << endl;
	}
	return int(p - '0');
}
//求第k大/小时 用于输入k的值的函数
int cink(int n) {
	int k;
	string str;
	bool ok = false;
	bool jump = false;
	while (!ok)   // 判断输入的内容是否为整形
	{
		jump = false;
		cout << "请输入k的值(0<=k<=n):";
		cin >> str;
		for (int i = 0; i < str.size(); i++) {
			if (!('0' <= str[i] && str[i] <= '9')) {
				cout << "输入内容格式有误，请输入数字!"<<endl;
				jump = true;
				break;
			}
		}
		if (jump) {
			continue;
		}
		k = atoi(str.c_str());     //输入没有问题，进行字符串转换为整形
		if (!(0 <= k && k <= n)) {
			cout << "k的值有误!";
			continue;
		}
		ok = 1;
	}
	return k;

}

bool cinBigorSmall() {
	cout << "请选择第k大or第k小:1.大\t0.小" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~请重新输入0或1:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '1') {
			ok = 1;
			cout << "输入成功:" << p << endl;
			break;
		}
		cout << "输入错误喵~请重新输入0或1:" << endl;
	}
	if (p == '0') {
		return false;
	}
	else {
		return true;
	}
}