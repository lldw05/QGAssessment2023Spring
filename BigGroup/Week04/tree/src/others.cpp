#include"../inc/binary_sort_tree.h"



void xiabo() {
	//�²���
	cout << "ллʹ����" << endl;
	exit(0);
}

/*���� ʹ����ĳ�����ܺ� ����w��մ��ڻص��˵�... */
void buffer() {
	cout << "------------" << endl;
	cout << "�����������......" << endl;
	string s; cin >> s;
	system("cls");
	menu();
}

//*�ڲ˵���ʱ���õ����뺯�� 0-7  ����char�ַ�
char cinmenu() {
	cout << "������0-7������orz:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~����������0-7������orz:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '7' || p == 'z') {
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			//cout << "-----------------------" << endl;
			cout << endl << endl;
			break;
		}
		cout << "���������~����������0-7������orz:" << endl;
	}
	return p;
}


bool alreadyInit(BinarySortTreePtr tree) {
	//���rootΪ��
	if (tree->root==NULL) {
		return false;
	}
	//root�ǿ�
	else {
		return true;
	}
}

bool cinZeroorOne() {
	cout << "��ѡ�������ʽ:1." << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~����������0��1:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '1') {
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			break;
		}
		cout << "���������~����������0��1:" << endl;
	}
	if (p == '0') {
		return false;
	}
	else {
		return true;
	}
}


//����ڵ�ʱ ��������
int cinNum() {
	int k;
	string str;
	bool ok = false;
	bool jump = false;
	while (!ok)   // �ж�����������Ƿ�Ϊ����
	{
		jump = false;
		cout << "������һ��������(0~2147483647):";
		cin >> str;
		for (int i = 0; i < str.size(); i++) {
			if (!('0' <= str[i] && str[i] <= '9')) {
				cout << "�������ݸ�ʽ����������������!" << endl;
				jump = true;
				break;
			}
		}
		if (jump) {
			continue;
		}

		k = atoi(str.c_str());     //����û�����⣬�����ַ���ת��Ϊ����
		if (k == INT_MAX) {
			cout << "��ֵ������~" << endl;
			continue;
		}
		ok = 1;
	}
	return k;

}