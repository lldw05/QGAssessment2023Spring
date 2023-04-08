#include"../Headers/sort.h"
int* a = NULL;
int n = 0;
clock_t start, endtime; 
int op = 0;
int main() {
	menu();

	
	
}

void menu() {
	cout << "------" << "Sort Show" << "------" << endl;
	cout << "------" << "��������һ������ָ��a" << endl;
	cout << "------" << "�����Խ������²���" << endl;
	cout << "------" << "0.�����������" << endl;
	cout << "------" << "1.��������" << endl;
	cout << "------" << "2.��������" << endl;
	cout << "------" << "3.�鲢����" << endl;
	cout << "------" << "4.����" << endl;
	cout << "------" << "5.��������(�Ż���pluspromax++)" << endl;
	cout << "------" << "6.ϵͳ����" << endl;
	cout << "------" << "7.��������" << endl;
	cout << "------" << "8.��ɫ����" << endl;
	cout << "------" << "9.�õ���K��/С����" << endl;
	cout << "------" << "z.�˳�" << endl;
	cout << "------" << "ע:orz����orz����\"or z\" ~" << endl;
	cout << "------" << "ע:ѡ������500�����ݼ��ɲ鿴��������~" << endl;
	cout << "------" << "ע:ÿ�������궼��������������µ�������~" << endl;
	//cout << "------" << "0." << endl;


	char option;
	bool ok = false;

	//��ѡ1��9֮ǰ������ѡ1
	while (!ok) {
		//����1��9
		option = cinmenu();
		bool alreadyGet = alreadyGetData(a);
		if (option != '0' && option != 'z' && !alreadyGet) {
			//ѡ1��9 �� δ������������
			/*cout << s<<endl;*/
			cout << "����û�������������Ӵ~ �����Ƚ�����������" << endl;
		}
		else {
			ok = 1;
		}
	}
	//int op=0;
	int ass = 0;
	bool bigorsmall = 0;
	int k = 0;
	//0��z   ����getDataѡ1��9
	switch (option) {

	case '0':
		cout << "0.�����������:\t" << endl;
		cout << "����ѡ����Ҫ���ɵ���������1.5e4\t2.1e5\t3.2e5\t4.500\t5.ֻ��0,1,2������*500��" << endl;
		op = cinnum();
		if (op == 1) {
			n = 50000;
		}
		else if (op == 2) {
			n = 100000;
		}
		else if (op == 3) {
			n = 200000;
		}else if (op == 4) {
			n = 500;
		}
		else if (op == 5) {
			cout << "op=5" << endl;
			n = 500;
			a = new int[n]();
			getData(a, n, 2);
			break;
		}
		a = new int[n]();
		getData(a, n, 1000);
		break;

	case '1':
		cout << "1.��������:\t" << endl;
		if(n==500) {
			//��ӡһ��
			cout << "����ǰ:" << endl;
			printff(a, n);
		}

		//���м�ʱ������
		start = clock();
		CountSort(a, n, 1000);
		endtime = clock();
		
		if (n == 500) {
			//��ӡһ��
			cout << "�����:" << endl;
			printff(a, n);
		}
		cout << endl << "Total time:" << endtime - start << "ms" << endl;

		break;

	case '2':
		cout << "2.��������:\t" << endl;

		if (n == 500) {
			//��ӡһ��
			cout << "����ǰ:" << endl;
			printff(a, n);
		}

		start = clock();
		insertSort(a, n);
		endtime = clock();

		if (n == 500) {
			//��ӡһ��
			cout << "�����:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '3':
		cout << "3.�鲢����:\t" << endl;

		if (n == 500) {
			//��ӡһ��
			cout << "����ǰ:" << endl;
			printff(a, n);
		}

		start = clock();
		MergeSortEntrance(a, n);
		endtime = clock();

		if (n == 500) {
			//��ӡһ��
			cout << "�����:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;

		break;

	case '4':
		cout << "4.����:\t" << endl;

		if (n == 500) {
			//��ӡһ��
			cout << "����ǰ:" << endl;
			printff(a, n);
		}

		start = clock();
		QuickSort_Recursion(a, 0, n - 1);
		endtime = clock();

		if (n == 500) {
			//��ӡһ��
			cout << "�����:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '5':
		cout << "5.��������:\t" << endl;

		if (n == 500) {
			//��ӡһ��
			cout << "����ǰ:" << endl;
			printff(a, n);
		}

		start = clock();
		fastSortPlus(a, 0, n - 1);
		endtime = clock();

		if (n == 500) {
			//��ӡһ��
			cout << "�����:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '6':
		cout << "6.ϵͳ����:\t" << endl;

		if (n == 500) {
			//��ӡһ��
			cout << "����ǰ:" << endl;
			printff(a, n);
		}

		start = clock();
		sort(a, a + n);
		endtime = clock();

		if (n == 500) {
			//��ӡһ��
			cout << "�����:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '7':
		cout << "7.��������:\t" << endl;

		if (n == 500) {
			//��ӡһ��
			cout << "����ǰ:" << endl;
			printff(a, n);
		}

		start = clock();
		RadixCountSort(a, n);
		endtime = clock();

		if (n == 500) {
			//��ӡһ��
			cout << "�����:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '8':
		if (op!=5) {
			cout << "���е����ݲ��ʺ�ʹ����ɫ������~";
			break;
		}
		cout << "8.��ɫ����:\t" << endl;

		
			//��ӡһ��
		cout << "����ǰ:" << endl;
		printff(a, n); cout << endl;
		

		start = clock();
		ColorSort(a, n);
		endtime = clock();


		
		//��ӡһ��
		cout << "�����:" << endl;
		printff(a, n);
		

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '9':
		cout << "9.�õ���K��/С����:\t" << endl;

		k = cink(n);
		bigorsmall = cinBigorSmall();
		ass  = findK(a, n, bigorsmall, k);
		cout << "Ѱ�ҳɹ�:" << ass << endl;
		break;

	case 'z':
		cout << "9.�˳�:\t" << endl;
		xiabo(); break;


	default:
		//�����ܵ�������
		buffer();
	}
	if (op == 5) {
		getData(a, n, 2);
	}
	else{
		getData(a, n, 1000);
	}
	buffer();
	
}