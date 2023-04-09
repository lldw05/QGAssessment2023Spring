#include"../inc/binary_sort_tree.h"



void xiabo() {
	//下播！
	cout << "谢谢使用喵" << endl;
	exit(0);
}

/*缓冲 使用完某个功能后 输入w清空窗口回到菜单... */
void buffer() {
	cout << "------------" << endl;
	cout << "输入任意继续......" << endl;
	string s; cin >> s;
	system("cls");
	menu();
}

//*在菜单栏时调用的输入函数 0-7  返回char字符
char cinmenu() {
	cout << "请输入0-7的数字orz:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~请重新输入0-7的数字orz:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '7' || p == 'z') {
			ok = 1;
			cout << "输入成功:" << p << endl;
			//cout << "-----------------------" << endl;
			cout << endl << endl;
			break;
		}
		cout << "输入错误喵~请重新输入0-7的数字orz:" << endl;
	}
	return p;
}


bool alreadyInit(BinarySortTreePtr tree) {
	//如果root为空
	if (tree->root==NULL) {
		return false;
	}
	//root非空
	else {
		return true;
	}
}

bool cinZeroorOne() {
	cout << "请选择遍历方式:1." << endl;
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


//插入节点时 输入数字
int cinNum() {
	int k;
	string str;
	bool ok = false;
	bool jump = false;
	while (!ok)   // 判断输入的内容是否为整形
	{
		jump = false;
		cout << "请输入一个正整数(0~2147483647):";
		cin >> str;
		for (int i = 0; i < str.size(); i++) {
			if (!('0' <= str[i] && str[i] <= '9')) {
				cout << "输入内容格式有误，请输入正整数!" << endl;
				jump = true;
				break;
			}
		}
		if (jump) {
			continue;
		}

		k = atoi(str.c_str());     //输入没有问题，进行字符串转换为整形
		if (k == INT_MAX) {
			cout << "数值过大喵~" << endl;
			continue;
		}
		ok = 1;
	}
	return k;

}