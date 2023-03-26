#include "../Headers/LinkStack.h"

LinkStack *s = new LinkStack;

int main()
{
	s->top = NULL;
	s->count = -1;
	menu();
	/*int a = 1;
	int* p = &a;
	*p = 2;
	cout << *p << endl;
	test(p);
	cout << *p;*/
}
// void test(int* p) {
//	(* p)++;
// }
void menu()
{
	cout << "------"
		 << "欢迎来到我LinkStack作业展示"
		 << "------" << endl;
	cout << "------"
		 << "您现在有一个栈顶指针s" << endl;
	cout << "------"
		 << "您可以进行以下操作" << endl;
	cout << "------"
		 << "1.初始化列栈" << endl;
	cout << "------"
		 << "2.判断栈是否为空" << endl;
	cout << "------"
		 << "3.入栈" << endl;
	cout << "------"
		 << "4.出栈" << endl;
	cout << "------"
		 << "5.得到栈顶元素" << endl;
	cout << "------"
		 << "6.检测栈长度" << endl;
	cout << "------"
		 << "7.清空栈" << endl;
	cout << "------"
		 << "8.销毁栈" << endl;
	cout << "------"
		 << "0.退出" << endl;
	showLStack(s);

	char option;
	bool ok = false;

	// 想选2到8之前必须先选1
	while (!ok)
	{
		// 输入0到8
		option = cinmenu();
		bool isInited = alreadyInited(s);
		if (option != '0' && option != '1' && !isInited)
		{
			// 选2到8 且 未进行初始化时
			/*cout << s<<endl;*/
			cout << "链栈还没有进行初始化哟~ 请您先进行初始化" << endl;
		}
		else
		{
			ok = 1;
		}
	}
	// 0或1   或已初始化选2到8
	switch (option)
	{
	case '1':
		cout << "1.初始化列栈:\t" << endl;
		InitLStackController();
		break;
	case '2':
		cout << "2.判断栈是否为空\t" << endl;
		judgeIsEmptyController();
		break;
	case '3':
		cout << "3.入栈:\t" << endl;
		pushLStackController();
		break;
	case '4':
		cout << "4.出栈:\t" << endl;
		popLStackController();
		break;
	case '5':
		cout << "5.得到栈顶元素:\t" << endl;
		getTopLStackController();
		break;
	case '6':
		cout << "6.检测栈长度:\t" << endl;
		LStackLengthController();
		break;
	case '7':
		cout << "7.清空栈:\t" << endl;
		clearLStackController();
		break;
	case '8':
		cout << "8.销毁栈:\t" << endl;
		destroyLStackController();
		break;
	case '0':
		cout << "0.退出:\t" << endl;
		xiabo();
		break;
	default:
		// 不可能到达这里
		buffer();
	}
	buffer();
}

//*在菜单栏时调用的输入函数 0-8  返回char字符
char cinmenu()
{
	cout << "请输入0-8的数字:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok)
	{
		cin >> s;
		if (s.size() > 1)
		{
			cout << "输入错误喵~请重新输入0-8的数字:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '8')
		{
			ok = 1;
			cout << "输入成功:" << p << endl;
			// cout << "-----------------------" << endl;
			cout << endl
				 << endl;
			break;
		}
		cout << "输入错误喵~请重新输入0-8的数字:" << endl;
	}
	return p;
}

/*输入函数 只能输入0到9直接的数字 其他则会报错*/
int cinnum()
{
	cout << "请输入一个0到9之间的数字:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok)
	{
		cin >> s;
		if (s.size() > 1)
		{
			cout << "输入错误喵~请重新输入0到9之间的数字:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '9')
		{
			ok = 1;
			cout << "输入成功:" << p << endl;
			break;
		}
		cout << "输入错误喵~请重新输入0到9之间的数字:" << endl;
	}
	return int(p - '0');
}

/*缓冲 使用完某个功能后 输入w清空窗口回到菜单... */
void buffer()
{
	cout << "------------" << endl;
	cout << "输入任意继续......" << endl;
	string s;
	cin >> s;
	system("cls");
	menu();
}

void InitLStackController()
{

	cout << "------------" << endl;
	cout << "开始初始化ing~" << endl;
	Status ok = initLStack(s);
	if (ok == 1)
	{
		cout << "初始化成功喵~" << endl;
	}
	else
	{
		cout << "初始化失败喵~ 可能是因为链栈已经初始化过了" << endl;
	}
}

void judgeIsEmptyController()
{

	cout << "------------" << endl;
	if (isEmptyLStack(s))
	{
		cout << "链栈为空！" << endl;
	}
	else
	{
		cout << "链栈不为空！" << endl;
	}
}

void pushLStackController()
{
	cout << "原链栈为:" << endl;
	showLStack(s);
	// 输入0到9之间的数字
	int data = cinnum();
	pushLStack(s, data);
	cout << "入栈成功:" << endl;
	showLStack(s);
}

void popLStackController()
{
	cout << "原链栈为:" << endl;
	showLStack(s);
	int data = 0;
	int *p = &data;
	Status ok = popLStack(s, p);
	if (ok)
	{
		cout << "出栈成功:" << *p << endl;
		showLStack(s);
	}
	else
	{
		cout << "出栈失败~ 链栈为空" << endl;
	}
}

void getTopLStackController()
{
	int data = 0;
	int *p = &data;
	Status ok = getTopLStack(s, p);
	if (!ok)
	{
		cout << "获取失败喵~ 可能是因为链栈为空" << endl;
	}
	else
	{
		cout << "获取成功:" << *p << endl;
	}
	p = NULL;
}

void LStackLengthController()
{
	int length = 0;
	int *p = &length;
	LStackLength(s, p);
	cout << "获取成功,链栈长度为" << *p << endl;
}

void clearLStackController()
{

	Status ok = clearLStack(s);
	if (ok)
	{
		cout << "清空成功喵~" << endl;
	}
	else
	{
		cout << "清空失败喵~ 可能是因为链栈已经是空的了" << endl;
	}
}

void destroyLStackController()
{
	destroyLStack(s);
	cout << "链栈摧毁成功" << endl;
}

void xiabo()
{
	// 下播！
	cout << "谢谢使用喵" << endl;
	exit(0);
}