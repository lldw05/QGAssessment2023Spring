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
	cout << "------" << "您现在有一个数组指针a" << endl;
	cout << "------" << "您可以进行以下操作" << endl;
	cout << "------" << "0.生成随机数据" << endl;
	cout << "------" << "1.计数排序" << endl;
	cout << "------" << "2.插入排序" << endl;
	cout << "------" << "3.归并排序" << endl;
	cout << "------" << "4.快排" << endl;
	cout << "------" << "5.超级快排(优化版pluspromax++)" << endl;
	cout << "------" << "6.系统快排" << endl;
	cout << "------" << "7.基数排序" << endl;
	cout << "------" << "8.颜色排序" << endl;
	cout << "------" << "9.得到第K大/小的数" << endl;
	cout << "------" << "z.退出" << endl;
	cout << "------" << "注:orz不是orz而是\"or z\" ~" << endl;
	cout << "------" << "注:选择生成500个数据即可查看具体数据~" << endl;
	cout << "------" << "注:每次排序完都会帮您重新生成新的数据喵~" << endl;
	//cout << "------" << "0." << endl;


	char option;
	bool ok = false;

	//想选1到9之前必须先选1
	while (!ok) {
		//输入1到9
		option = cinmenu();
		bool alreadyGet = alreadyGetData(a);
		if (option != '0' && option != 'z' && !alreadyGet) {
			//选1到9 且 未进行生成数据
			/*cout << s<<endl;*/
			cout << "您还没有生成随机数据哟~ 请您先进行生成数据" << endl;
		}
		else {
			ok = 1;
		}
	}
	//int op=0;
	int ass = 0;
	bool bigorsmall = 0;
	int k = 0;
	//0或z   或已getData选1到9
	switch (option) {

	case '0':
		cout << "0.生成随机数据:\t" << endl;
		cout << "请您选择想要生成的数据量：1.5e4\t2.1e5\t3.2e5\t4.500\t5.只有0,1,2的数据*500个" << endl;
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
		cout << "1.计数排序:\t" << endl;
		if(n==500) {
			//打印一遍
			cout << "排序前:" << endl;
			printff(a, n);
		}

		//进行计时和排序
		start = clock();
		CountSort(a, n, 1000);
		endtime = clock();
		
		if (n == 500) {
			//打印一遍
			cout << "排序后:" << endl;
			printff(a, n);
		}
		cout << endl << "Total time:" << endtime - start << "ms" << endl;

		break;

	case '2':
		cout << "2.插入排序:\t" << endl;

		if (n == 500) {
			//打印一遍
			cout << "排序前:" << endl;
			printff(a, n);
		}

		start = clock();
		insertSort(a, n);
		endtime = clock();

		if (n == 500) {
			//打印一遍
			cout << "排序后:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '3':
		cout << "3.归并排序:\t" << endl;

		if (n == 500) {
			//打印一遍
			cout << "排序前:" << endl;
			printff(a, n);
		}

		start = clock();
		MergeSortEntrance(a, n);
		endtime = clock();

		if (n == 500) {
			//打印一遍
			cout << "排序后:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;

		break;

	case '4':
		cout << "4.快排:\t" << endl;

		if (n == 500) {
			//打印一遍
			cout << "排序前:" << endl;
			printff(a, n);
		}

		start = clock();
		QuickSort_Recursion(a, 0, n - 1);
		endtime = clock();

		if (n == 500) {
			//打印一遍
			cout << "排序后:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '5':
		cout << "5.超级快排:\t" << endl;

		if (n == 500) {
			//打印一遍
			cout << "排序前:" << endl;
			printff(a, n);
		}

		start = clock();
		fastSortPlus(a, 0, n - 1);
		endtime = clock();

		if (n == 500) {
			//打印一遍
			cout << "排序后:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '6':
		cout << "6.系统快排:\t" << endl;

		if (n == 500) {
			//打印一遍
			cout << "排序前:" << endl;
			printff(a, n);
		}

		start = clock();
		sort(a, a + n);
		endtime = clock();

		if (n == 500) {
			//打印一遍
			cout << "排序后:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '7':
		cout << "7.基数排序:\t" << endl;

		if (n == 500) {
			//打印一遍
			cout << "排序前:" << endl;
			printff(a, n);
		}

		start = clock();
		RadixCountSort(a, n);
		endtime = clock();

		if (n == 500) {
			//打印一遍
			cout << "排序后:" << endl;
			printff(a, n);
		}

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '8':
		if (op!=5) {
			cout << "现有的数据不适合使用颜色排序噢~";
			break;
		}
		cout << "8.颜色排序:\t" << endl;

		
			//打印一遍
		cout << "排序前:" << endl;
		printff(a, n); cout << endl;
		

		start = clock();
		ColorSort(a, n);
		endtime = clock();


		
		//打印一遍
		cout << "排序后:" << endl;
		printff(a, n);
		

		cout << endl << "Total time:" << endtime - start << "ms" << endl;
		break;

	case '9':
		cout << "9.得到第K大/小的数:\t" << endl;

		k = cink(n);
		bigorsmall = cinBigorSmall();
		ass  = findK(a, n, bigorsmall, k);
		cout << "寻找成功:" << ass << endl;
		break;

	case 'z':
		cout << "9.退出:\t" << endl;
		xiabo(); break;


	default:
		//不可能到达这里
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