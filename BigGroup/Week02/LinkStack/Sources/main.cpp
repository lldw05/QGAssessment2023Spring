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
		 << "��ӭ������LinkStack��ҵչʾ"
		 << "------" << endl;
	cout << "------"
		 << "��������һ��ջ��ָ��s" << endl;
	cout << "------"
		 << "�����Խ������²���" << endl;
	cout << "------"
		 << "1.��ʼ����ջ" << endl;
	cout << "------"
		 << "2.�ж�ջ�Ƿ�Ϊ��" << endl;
	cout << "------"
		 << "3.��ջ" << endl;
	cout << "------"
		 << "4.��ջ" << endl;
	cout << "------"
		 << "5.�õ�ջ��Ԫ��" << endl;
	cout << "------"
		 << "6.���ջ����" << endl;
	cout << "------"
		 << "7.���ջ" << endl;
	cout << "------"
		 << "8.����ջ" << endl;
	cout << "------"
		 << "0.�˳�" << endl;
	showLStack(s);

	char option;
	bool ok = false;

	// ��ѡ2��8֮ǰ������ѡ1
	while (!ok)
	{
		// ����0��8
		option = cinmenu();
		bool isInited = alreadyInited(s);
		if (option != '0' && option != '1' && !isInited)
		{
			// ѡ2��8 �� δ���г�ʼ��ʱ
			/*cout << s<<endl;*/
			cout << "��ջ��û�н��г�ʼ��Ӵ~ �����Ƚ��г�ʼ��" << endl;
		}
		else
		{
			ok = 1;
		}
	}
	// 0��1   ���ѳ�ʼ��ѡ2��8
	switch (option)
	{
	case '1':
		cout << "1.��ʼ����ջ:\t" << endl;
		InitLStackController();
		break;
	case '2':
		cout << "2.�ж�ջ�Ƿ�Ϊ��\t" << endl;
		judgeIsEmptyController();
		break;
	case '3':
		cout << "3.��ջ:\t" << endl;
		pushLStackController();
		break;
	case '4':
		cout << "4.��ջ:\t" << endl;
		popLStackController();
		break;
	case '5':
		cout << "5.�õ�ջ��Ԫ��:\t" << endl;
		getTopLStackController();
		break;
	case '6':
		cout << "6.���ջ����:\t" << endl;
		LStackLengthController();
		break;
	case '7':
		cout << "7.���ջ:\t" << endl;
		clearLStackController();
		break;
	case '8':
		cout << "8.����ջ:\t" << endl;
		destroyLStackController();
		break;
	case '0':
		cout << "0.�˳�:\t" << endl;
		xiabo();
		break;
	default:
		// �����ܵ�������
		buffer();
	}
	buffer();
}

//*�ڲ˵���ʱ���õ����뺯�� 0-8  ����char�ַ�
char cinmenu()
{
	cout << "������0-8������:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok)
	{
		cin >> s;
		if (s.size() > 1)
		{
			cout << "���������~����������0-8������:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '8')
		{
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			// cout << "-----------------------" << endl;
			cout << endl
				 << endl;
			break;
		}
		cout << "���������~����������0-8������:" << endl;
	}
	return p;
}

/*���뺯�� ֻ������0��9ֱ�ӵ����� ������ᱨ��*/
int cinnum()
{
	cout << "������һ��0��9֮�������:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok)
	{
		cin >> s;
		if (s.size() > 1)
		{
			cout << "���������~����������0��9֮�������:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '9')
		{
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			break;
		}
		cout << "���������~����������0��9֮�������:" << endl;
	}
	return int(p - '0');
}

/*���� ʹ����ĳ�����ܺ� ����w��մ��ڻص��˵�... */
void buffer()
{
	cout << "------------" << endl;
	cout << "�����������......" << endl;
	string s;
	cin >> s;
	system("cls");
	menu();
}

void InitLStackController()
{

	cout << "------------" << endl;
	cout << "��ʼ��ʼ��ing~" << endl;
	Status ok = initLStack(s);
	if (ok == 1)
	{
		cout << "��ʼ���ɹ���~" << endl;
	}
	else
	{
		cout << "��ʼ��ʧ����~ ��������Ϊ��ջ�Ѿ���ʼ������" << endl;
	}
}

void judgeIsEmptyController()
{

	cout << "------------" << endl;
	if (isEmptyLStack(s))
	{
		cout << "��ջΪ�գ�" << endl;
	}
	else
	{
		cout << "��ջ��Ϊ�գ�" << endl;
	}
}

void pushLStackController()
{
	cout << "ԭ��ջΪ:" << endl;
	showLStack(s);
	// ����0��9֮�������
	int data = cinnum();
	pushLStack(s, data);
	cout << "��ջ�ɹ�:" << endl;
	showLStack(s);
}

void popLStackController()
{
	cout << "ԭ��ջΪ:" << endl;
	showLStack(s);
	int data = 0;
	int *p = &data;
	Status ok = popLStack(s, p);
	if (ok)
	{
		cout << "��ջ�ɹ�:" << *p << endl;
		showLStack(s);
	}
	else
	{
		cout << "��ջʧ��~ ��ջΪ��" << endl;
	}
}

void getTopLStackController()
{
	int data = 0;
	int *p = &data;
	Status ok = getTopLStack(s, p);
	if (!ok)
	{
		cout << "��ȡʧ����~ ��������Ϊ��ջΪ��" << endl;
	}
	else
	{
		cout << "��ȡ�ɹ�:" << *p << endl;
	}
	p = NULL;
}

void LStackLengthController()
{
	int length = 0;
	int *p = &length;
	LStackLength(s, p);
	cout << "��ȡ�ɹ�,��ջ����Ϊ" << *p << endl;
}

void clearLStackController()
{

	Status ok = clearLStack(s);
	if (ok)
	{
		cout << "��ճɹ���~" << endl;
	}
	else
	{
		cout << "���ʧ����~ ��������Ϊ��ջ�Ѿ��ǿյ���" << endl;
	}
}

void destroyLStackController()
{
	destroyLStack(s);
	cout << "��ջ�ݻٳɹ�" << endl;
}

void xiabo()
{
	// �²���
	cout << "ллʹ����" << endl;
	exit(0);
}