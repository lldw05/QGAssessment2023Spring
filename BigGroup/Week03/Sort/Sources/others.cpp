#include"../Headers/sort.h"

/*���� ʹ����ĳ�����ܺ� ����w��մ��ڻص��˵�... */
void buffer() {
	cout << "------------" << endl;
	cout << "�����������......" << endl;
	string s; cin >> s;
	system("cls");
	menu();
}

void getData(int *a,int n,int MAX) {

	//fstream out("../data.txt");//���ļ�
	//srand((unsigned)time(NULL));//������� 
	////int MAX = 9999;//min max��ֵ�����Լ��������
	//int MIN = 0;
	//for (int i = 0; i < n; i++)
	//	out << rand() %MAX+1 << endl;//������������min��max֮�� 
	//out.close();//�ر��ļ�

	////��ȡ�����ļ�
	//ifstream in("../", ios::in);
	//if (!in.is_open())
	//{
	//	cout << "open error!" << endl;
	//	return;
	//}
	////�������ļ����ݴ�������
	//int i = 0;
	//while (!in.eof() && i < n)
	//{
	//	in >> a[i++];
	//}
	//return;

	srand((unsigned)time(NULL));//������� 
	for (int i = 0; i < n; i++) {
		a[i] = rand() % (MAX + 1);//0-MAX ����0��MAX
	}
}
void printff(int* a,int size ) {
	for (int i = 0; i < size; i++) {
		cout << a[i] << " ";
		//if (i % 10 == 0) cout << endl;
	}
}

//*�ڲ˵���ʱ���õ����뺯�� 0-9  ����char�ַ�
char cinmenu() {
	cout << "������0-9������orz:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~����������0-9������orz:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '9'||p=='z') {
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			//cout << "-----------------------" << endl;
			cout << endl << endl;
			break;
		}
		cout << "���������~����������0-9������orz:" << endl;
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
	//�²���
	cout << "ллʹ����" << endl;
	exit(0);
}

/*���뺯�� ֻ������1��4ֱ�ӵ����� ������ᱨ��*/
int cinnum() {
	cout << "������һ��1��5֮�������:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~����������1��5֮�������:" << endl;
			continue;
		}
		p = s[0];
		if ('1' <= p && p <= '5') {
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			break;
		}
		cout << "���������~����������1��5֮�������:" << endl;
	}
	return int(p - '0');
}
//���k��/Сʱ ��������k��ֵ�ĺ���
int cink(int n) {
	int k;
	string str;
	bool ok = false;
	bool jump = false;
	while (!ok)   // �ж�����������Ƿ�Ϊ����
	{
		jump = false;
		cout << "������k��ֵ(0<=k<=n):";
		cin >> str;
		for (int i = 0; i < str.size(); i++) {
			if (!('0' <= str[i] && str[i] <= '9')) {
				cout << "�������ݸ�ʽ��������������!"<<endl;
				jump = true;
				break;
			}
		}
		if (jump) {
			continue;
		}
		k = atoi(str.c_str());     //����û�����⣬�����ַ���ת��Ϊ����
		if (!(0 <= k && k <= n)) {
			cout << "k��ֵ����!";
			continue;
		}
		ok = 1;
	}
	return k;

}

bool cinBigorSmall() {
	cout << "��ѡ���k��or��kС:1.��\t0.С" << endl;
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