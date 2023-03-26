#include"../head/LQueue.h">

char type;
LQueue* Q = NULL;
int main(){
	
	getType(type);
	while (1) {
		menu();
		//TraverseLQueue(Q,LPrint); ʲô��������?
		showQueue(Q);
		char option;
		bool ok = false;

		//��ѡ2��8֮ǰ������ѡ1
		while (!ok) {
			//����0��8
			option = cinmenu();
			bool isInited = alreadyInited(Q);
			if (option != '0' && option != '1' && !isInited) {
				//ѡ2��8 �� δ���г�ʼ��ʱ
				/*cout << s<<endl;*/
				cout << "���л�û�н��г�ʼ��Ӵ~ �����Ƚ��г�ʼ��" << endl;
			}
			else {
				ok = 1;
			}
		}
		//0��1   ���ѳ�ʼ��ѡ2��8
		void* data = NULL;
		Status okk = FALSE;
		int leng = 0;
		switch (option) {

		case '1':
			if (alreadyInited(Q)) {
				cout << "�����Ѿ���ʼ������~" << endl;
				break;
			}
			cout << "1.��ʼ������:\t" << endl;
			Q = new LQueue;
			InitLQueue(Q);
			cout << "��ʼ����ɣ�" << endl;
			break;

		case '2':
			cout << "2.�ж϶����Ƿ�Ϊ��\t" << endl;
			okk = IsEmptyLQueue(Q);
			if (okk) {
				cout << "����Ϊ�գ�" << endl;
			}
			else {
				cout << "���в�Ϊ�գ�" << endl;
			}
			break;

		case '3':
			cout << "3.����:\t" << endl;
			//���鲻�� ����д��
			cin >> data;
			EnLQueue(Q,data);
			break;


		case '4':
			cout << "4.����:\t" << endl;
			okk = DeLQueue(Q,data);
			if (okk) {
				//cout << "���гɹ�:"<<data << endl;
			}
			else {
				cout << "����ʧ�ܣ���������Ϊ����Ϊ��" << endl;
			}
			break;


		case '5':
			cout << "5.�鿴��ͷԪ��:\t" << endl; 
			okk = GetHeadLQueue(Q,data);
			if (okk) {
				//cout << "�鿴�ɹ�:" << data << endl;
			}
			else {
				cout << "�鿴ʧ�ܣ���������Ϊ����Ϊ��" << endl;
			}
			break;


		case '6':
			cout << "6.�����г���:\t" << endl; 
			leng = LengthLQueue(Q);
			cout << "��ȡ�ɹ������г���Ϊ��" << leng << endl;
			break;


		case '7':
			cout << "7.��ն���:\t" << endl; 
			ClearLQueue(Q);
			break;


		case '8':
			cout << "8.���ٶ���:\t" << endl;
			DestoryLQueue(Q);
			Q = NULL;
			ok = 0;
			break;


		case '0':
			cout << "0.�˳�:\t" << endl;
			xiabo(); 
			break;

		}

		buffer();
	}
}


void menu() {
	cout << "------" << "��ӭ������LQueue��ҵչʾ" << "------" << endl;
	cout << "------" << "��������һ��LQueueָ��q" << endl;
	cout << "------" << "�����Խ������²���" << endl;
	cout << "------" << "1.��ʼ������" << endl;
	cout << "------" << "2.�ж϶����Ƿ�Ϊ��" << endl;
	cout << "------" << "3.����" << endl;
	cout << "------" << "4.����" << endl;
	cout << "------" << "5.�鿴��ͷԪ��" << endl;
	cout << "------" << "6.�����г���" << endl;
	cout << "------" << "7.��ն���" << endl;
	cout << "------" << "8.���ٶ���" << endl;
	cout << "------" << "0.�˳�" << endl;
}

//*�ڲ˵���ʱ���õ����뺯�� 0-8  ����char�ַ�
char cinmenu() {
	cout << "������0-8������:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~����������0-8������:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '8') {
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			//cout << "-----------------------" << endl;
			cout << endl << endl;
			break;
		}
		//cout << "���������~����������0-8������:" << endl;
	}
	return p;
}

void getType(char &type) {
	char p;
	string s = "";
	cout << "��ѡ���������ͣ�1.int\t2.char" << endl;
	while (1) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~" << endl;
			cout << "��ѡ���������ͣ�1.int\t2.char" << endl;
			continue;
		}
		p = s[0];
		if ('1' <= p && p <= '2') {
			cout << "����ɹ�:" << p << endl;
			//cout << "-----------------------" << endl;
			cout << endl << endl;
			break;
		}

	}
	if (p == '1') {
		type = 'i';
	}
	if (p == '2') {
		type = 'c';
	}

}


/*���� ʹ����ĳ�����ܺ� ����w��մ��ڻص��˵�... */
void buffer() {
	cout << "------------" << endl;
	cout << "�����������......" << endl;
	string s; cin >> s;
	system("cls");
}

void xiabo() {
	//�²���
	cout << "ллʹ����" << endl;
	exit(0);
}