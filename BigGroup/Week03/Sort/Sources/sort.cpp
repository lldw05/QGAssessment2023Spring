#include"../Headers/sort.h"


/**
 *  @name        : void insertSort(int *a,int n);
 *  @description : ���������㷨
 *  @param       : ����ָ�� a, ���鳤�� n
 */
void insertSort(int* a, int n) {
	int temp = 0;
	for (int i = 1; i < n; i++){
		//���������
		temp = a[i];
		int j = i - 1;
		//Ҫôtemp>=ǰһ�� Ҫô����������ͷ��
		while (j >= 0 && temp < a[j]) {
			a[j + 1] = a[j];
			j--;
		}
		a[j + 1] = temp;
	}
}


/**
 *  @name        : void MergeArray(int *a,int begin,int mid,int end,int *temp);
 *  @description : �鲢���򣨺ϲ����飩
 *  @param       : ����ָ��a���������begin�������е�mid�������յ�end����������ָ��temp
 */
void MergeArray(int* a, int begin, int mid, int end, int* temp) {
	//��ߵ�һ��
	int l_p = begin;
	//�ұߵ�һ��
	int r_p = mid+1;
	//��ʱ�����ͷ
	int tp = begin;
	
	//�ϲ�
	while (l_p <= mid && r_p <= end) {

		if (a[l_p] < a[r_p]) {
			temp[tp++] = a[l_p++];
		}
		else {
			temp[tp++] = a[r_p++];
		}
	}

	//�ϲ�ʣ����
	while (l_p <= mid) {
		temp[tp++] = a[l_p++];
	}
	//�ϲ�ʣ����
	while (r_p <= end) {
		temp[tp++] = a[r_p++];
	}
	//������temp copy������a
	while (begin <= end) {
		a[begin] = temp[begin];
		begin++;
	}
}


/**
 *  @name        : void MergeSort(int *a,int begin,int end,int *temp);
 *  @description : �鲢���� ����+���úϲ�
 *  @param       : ����ָ��a���������begin�������յ�end����������ָ��temp
 */
void MergeSort(int* a, int begin, int end, int* temp) {
	//1��Ԫ�� �����軮�� Ҳ��������
	//���ֲ�������ķֿ��ܶ������ ֻ�ǰ�ĳ����������begin ��һ����������end
	if (begin < end) {
		
		int mid = begin + (end - begin) / 2;
		//�ݹ黮����
		MergeSort(a, begin, mid, temp);
		//�ݹ黮����
		MergeSort(a, mid + 1, end, temp);
		//�ϲ�
		MergeArray(a, begin, mid, end, temp);
	}
}

/*
arr �����ַ
n ���鳤��
*/
void MergeSortEntrance(int arr[], int n) {
	//newһ������
	int* temp = new int[n];
	if (temp) {
		MergeSort(arr, 0, n - 1, temp);
		//delete[]temp;
		temp = NULL;
	}
	else {
		cout << "new����ʧ��" << endl;
	}
}

/**
 *  @name        : void QuickSort(int *a, int begin, int end);
 *  @description : �������򣨵ݹ�棩
 *  @param       : ����ָ��a���������begin�������յ�end
 */
void QuickSort_Recursion(int* a, int begin, int end) {
	//
	if (begin >= end) {
		return;
	}
	//end -= 1; begin += 1;
	//cout << "����" << endl;
	//ѡ��׼�� ������a[0] ��Ϊ��������begin��һ����ԭa�����ͷ
	int key = a[begin];
	int i = begin;
	int j = end;
	int temp = 0;
	while (i < j) {
		//����j��������Ѱ�ұ�keyС��
		while (i<j&&key < a[j]) {
			j--;
		}
		//����i��������Ѱ�ұ�key���
		while (i < j && a[i] <= key) {
			i++;
		}
		if (i < j) {
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	//����׼�����ڽ�Ϊ�м��λ��
	a[begin] = a[i];
	a[i] = key;
	//��ʱ�Ѿ��������

	//������
	if(i-1>=0)
	QuickSort_Recursion(a, begin, i - 1);
	//QuickSort_Recursion(a, begin, i );
	if(i+1<end)
	QuickSort_Recursion(a, i+1, end);
	//QuickSort_Recursion(a, i, end);
}
/**
* �����Ż�
* ����ȡ��ѡ���� 
* Ƕ�ײ�������
*/
void fastSortPlus(int* a, int begin, int end) {
	//�ж��Ƿ�ʹ�ò��� 20
	if (begin + (end - begin) / 2 <= 20) {
		insertSort(a, end - begin + 1);
	}

	//����ȡ��ѡ���� ��׼����Ȼ�ſ�ͷ
	int key = 0;
	int mid = begin + (end - begin) / 2;
	if (a[mid] > a[end]) {
		// amid<= aend
		swap(a[mid], a[end]);
	}
	if (a[begin] > a[end]) {
		//abegin<=aend
		swap(a[begin], a[end]);
	}
	if (a[mid]>a[begin]) {
		//amid<=abegin
		swap(a[begin], a[mid]);
	}
	//��ʱ��׼����beginλ��


	int i = begin;
	int j = end;
	int temp = 0;
	while (i < j) {
		//����j��������Ѱ�ұ�key���
		while (i < j && key < a[j]) {
			j--;
		}
		//����i��������Ѱ�ұ�keyС��
		while (i < j && a[i] <= key) {
			i++;
		}
		if (i < j) {
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	//����׼�����ڽ�Ϊ�м��λ��
	a[begin] = a[i];
	a[i] = key;

	//������
	QuickSort_Recursion(a, begin, i - 1);
	QuickSort_Recursion(a, i + 1, end);
}
/**
 *  @name        : void QuickSort(int *a,int size)
 *  @description : �������򣨷ǵݹ�棩
 *  @param       : ����ָ��a�����鳤��size
 */
void QuickSort(int* a, int size);


/**
 *  @name        : void QuickSort(int *a, int begin, int end)
 *  @description : �������������ţ�
 *  @param       : ����ָ��a���������begin�������յ�end
 */
int Partition(int* a, int begin, int end);


/**
 *  @name        : void CountSort(int *a, int size , int max)
 *  @description : ��������
 *  @param       : ����ָ��a�����鳤��size���������ֵmax
 */
void CountSort(int* a, int size, int max) {

	if (size <= 1)return;
	//count���� ����Ϊmax+1
	int *count = new int[max+1]();

	//���� ����ԭʼ���� ����Ϊsize
	for (int i = 0; i < size; i++) {
		count[a[i]]++;
	}

	//�ۼ�ֵ ����count���� ����Ϊmax+1 �������xС��Ԫ�صĸ���
	for (int i = 1; i < max+1; i++) {
		count[i] += count[i - 1];
	}

	//����һ����ʱ����
	int* temp = new int[size];

	//����ԭʼ���� Ȼ���ÿ��Ԫ�ط��ڶ�Ӧλ����
	for (int i = 0; i < size; i++) {
		temp[count[a[i]] - 1] = a[i];
		count[a[i]]--;
	}

	//��temp���Ƶ�a
	for (int i = 0; i < size; i++) {
		a[i] = temp[i];
	}

	delete[]temp;
	delete[]count;
}


/**
 *  @name        : void RadixCountSort(int *a,int size)
 *  @description : ������������  ��Ϊ�޷�������ά���� ����������һ����������ԭ��
 *  @param       : ����ָ��a�����鳤��size
 */
void RadixCountSort(int* a,  int size) {

	//�����λ���ĸ���
	int d=0;
	int pmax = a[0];
	for (int i = 0; i < size; i++) {
		if (a[i] > pmax)
			pmax = a[i];
	}
	while (pmax > 0) {
		pmax /= 10;
		d++;
	}
	cout << d << endl;
	//dΪ��������λ�� ��pmax=100 ��d=3

	//��������
	int cnt[10] = { 0 };
	//��ʱ����
	int* temp = new int[size];

	//����  1->10->100->...
	int radix = 1;

	int k;
	//�����dλ�� ����d��ѡ��
	for (int i = 1; i <= d; i++) {

		//���Ͱ
		//cout << "���Ͱ" << endl;
		for (int j = 0; j < 10; j++) {
			cnt[j] = 0;
		}

		//����
		//cout << "����" << endl;
		for (int j = 0; j < size; j++) {
			//���λ ʮλ ��λ�����ϵ�����
			k = (a[j] / radix) % 10;
			cnt[k]++;
		}

		//��������ԭ��
		//cout << "��������ԭ��" << endl;
		for (int j = 1; j < 10; j++) {
			cnt[j] += cnt[j - 1];//cnt[j]Ϊÿ����������֮���ڵڼ�λ
		}

		for (int j = size - 1; j >= 0; j--) {
			//�õ���Ҫ�Ƚϵ���λ����
			k = (a[j] / radix) % 10;
			temp[cnt[k] - 1] = a[j];
			cnt[k]--;
		}

		//copy��a����
		/*cout << "copy��a����" << endl;*/
		for (int j = 0; j < size; j++) {
			a[j] = temp[j];
		}
		radix *= 10;
	}

	delete[]temp;
}


/**
 *  @name        : void ColorSort(int *a,int size)
 *  @description : ��ɫ����
 *  @param       : ����ָ��a��ֻ��0��1��2Ԫ�أ������鳤��size
 */
void ColorSort(int* a, int size) {

	int p0 = 0;
	int p2 = size-1;
	for (int i = 0; i < size; i++) {
		if (i>p2) {
			break;
		}
		if (a[i] == 0) {
			swap(a[p0++], a[i]);
		}else if (a[i] == 2) {
			swap(a[p2--], a[i--]);
			
		}
		//printff(a, size); cout << endl;
		
	}
}


/**
 *  @name        : ����
 *  @description : ��һ�������������ҵ���K��/С����
 *  @param       : ����ָ��a�����鳤��size   1Ϊ�� 0ΪС
 */
int findK(int* a, int size, bool bigOrSmall,int k) {

	//����һ��
	fastSortPlus(a, 0, size - 1);

	if (bigOrSmall) {
		//��k�� ����n-k+1С
		return a[size - k + 1 - 1];
	}
	else {
		//��kС
		return a[k - 1];
	}
	
}