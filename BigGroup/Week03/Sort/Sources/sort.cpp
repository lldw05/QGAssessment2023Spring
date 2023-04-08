#include"../Headers/sort.h"


/**
 *  @name        : void insertSort(int *a,int n);
 *  @description : 插入排序算法
 *  @param       : 数组指针 a, 数组长度 n
 */
void insertSort(int* a, int n) {
	int temp = 0;
	for (int i = 1; i < n; i++){
		//存下这个数
		temp = a[i];
		int j = i - 1;
		//要么temp>=前一个 要么遍历到数组头部
		while (j >= 0 && temp < a[j]) {
			a[j + 1] = a[j];
			j--;
		}
		a[j + 1] = temp;
	}
}


/**
 *  @name        : void MergeArray(int *a,int begin,int mid,int end,int *temp);
 *  @description : 归并排序（合并数组）
 *  @param       : 数组指针a，数组起点begin，数组中点mid，数组终点end，承载数组指针temp
 */
void MergeArray(int* a, int begin, int mid, int end, int* temp) {
	//左边第一个
	int l_p = begin;
	//右边第一个
	int r_p = mid+1;
	//临时数组的头
	int tp = begin;
	
	//合并
	while (l_p <= mid && r_p <= end) {

		if (a[l_p] < a[r_p]) {
			temp[tp++] = a[l_p++];
		}
		else {
			temp[tp++] = a[r_p++];
		}
	}

	//合并剩下左
	while (l_p <= mid) {
		temp[tp++] = a[l_p++];
	}
	//合并剩下右
	while (r_p <= end) {
		temp[tp++] = a[r_p++];
	}
	//把数组temp copy到数组a
	while (begin <= end) {
		a[begin] = temp[begin];
		begin++;
	}
}


/**
 *  @name        : void MergeSort(int *a,int begin,int end,int *temp);
 *  @description : 归并排序 划分+调用合并
 *  @param       : 数组指针a，数组起点begin，数组终点end，承载数组指针temp
 */
void MergeSort(int* a, int begin, int end, int* temp) {
	//1个元素 则无需划分 也无需排序
	//划分并不是真的分开很多个数组 只是把某个索引当做begin 另一个索引当做end
	if (begin < end) {
		
		int mid = begin + (end - begin) / 2;
		//递归划分左
		MergeSort(a, begin, mid, temp);
		//递归划分右
		MergeSort(a, mid + 1, end, temp);
		//合并
		MergeArray(a, begin, mid, end, temp);
	}
}

/*
arr 数组地址
n 数组长度
*/
void MergeSortEntrance(int arr[], int n) {
	//new一个数组
	int* temp = new int[n];
	if (temp) {
		MergeSort(arr, 0, n - 1, temp);
		//delete[]temp;
		temp = NULL;
	}
	else {
		cout << "new数组失败" << endl;
	}
}

/**
 *  @name        : void QuickSort(int *a, int begin, int end);
 *  @description : 快速排序（递归版）
 *  @param       : 数组指针a，数组起点begin，数组终点end
 */
void QuickSort_Recursion(int* a, int begin, int end) {
	//
	if (begin >= end) {
		return;
	}
	//end -= 1; begin += 1;
	//cout << "快排" << endl;
	//选基准数 不能是a[0] 因为传进来的begin不一定是原a数组的头
	int key = a[begin];
	int i = begin;
	int j = end;
	int temp = 0;
	while (i < j) {
		//先让j从右往左寻找比key小的
		while (i<j&&key < a[j]) {
			j--;
		}
		//再让i从左往右寻找比key大的
		while (i < j && a[i] <= key) {
			i++;
		}
		if (i < j) {
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	//将基准数放在较为中间的位置
	a[begin] = a[i];
	a[i] = key;
	//此时已经划分完成

	//处理左
	if(i-1>=0)
	QuickSort_Recursion(a, begin, i - 1);
	//QuickSort_Recursion(a, begin, i );
	if(i+1<end)
	QuickSort_Recursion(a, i+1, end);
	//QuickSort_Recursion(a, i, end);
}
/**
* 快排优化
* 三数取中选基数 
* 嵌套插入排序
*/
void fastSortPlus(int* a, int begin, int end) {
	//判断是否使用插排 20
	if (begin + (end - begin) / 2 <= 20) {
		insertSort(a, end - begin + 1);
	}

	//三数取中选基数 基准数仍然放开头
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
	//此时基准数在begin位置


	int i = begin;
	int j = end;
	int temp = 0;
	while (i < j) {
		//先让j从右往左寻找比key大的
		while (i < j && key < a[j]) {
			j--;
		}
		//再让i从左往右寻找比key小的
		while (i < j && a[i] <= key) {
			i++;
		}
		if (i < j) {
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	//将基准数放在较为中间的位置
	a[begin] = a[i];
	a[i] = key;

	//处理左
	QuickSort_Recursion(a, begin, i - 1);
	QuickSort_Recursion(a, i + 1, end);
}
/**
 *  @name        : void QuickSort(int *a,int size)
 *  @description : 快速排序（非递归版）
 *  @param       : 数组指针a，数组长度size
 */
void QuickSort(int* a, int size);


/**
 *  @name        : void QuickSort(int *a, int begin, int end)
 *  @description : 快速排序（枢轴存放）
 *  @param       : 数组指针a，数组起点begin，数组终点end
 */
int Partition(int* a, int begin, int end);


/**
 *  @name        : void CountSort(int *a, int size , int max)
 *  @description : 计数排序
 *  @param       : 数组指针a，数组长度size，数组最大值max
 */
void CountSort(int* a, int size, int max) {

	if (size <= 1)return;
	//count数组 长度为max+1
	int *count = new int[max+1]();

	//计数 遍历原始数组 长度为size
	for (int i = 0; i < size; i++) {
		count[a[i]]++;
	}

	//累计值 遍历count数组 长度为max+1 计算出比x小的元素的个数
	for (int i = 1; i < max+1; i++) {
		count[i] += count[i - 1];
	}

	//创建一个临时数组
	int* temp = new int[size];

	//遍历原始数组 然后把每个元素放在对应位置上
	for (int i = 0; i < size; i++) {
		temp[count[a[i]] - 1] = a[i];
		count[a[i]]--;
	}

	//将temp复制到a
	for (int i = 0; i < size; i++) {
		a[i] = temp[i];
	}

	delete[]temp;
	delete[]count;
}


/**
 *  @name        : void RadixCountSort(int *a,int size)
 *  @description : 基数计数排序  因为无法创建二维数组 所以运用了一点计数排序的原理
 *  @param       : 数组指针a，数组长度size
 */
void RadixCountSort(int* a,  int size) {

	//求最大位数的个数
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
	//d为最大的数的位数 如pmax=100 得d=3

	//计数数组
	int cnt[10] = { 0 };
	//临时数组
	int* temp = new int[size];

	//基数  1->10->100->...
	int radix = 1;

	int k;
	//最大是d位数 进行d次选择
	for (int i = 1; i <= d; i++) {

		//清空桶
		//cout << "清空桶" << endl;
		for (int j = 0; j < 10; j++) {
			cnt[j] = 0;
		}

		//计数
		//cout << "计数" << endl;
		for (int j = 0; j < size; j++) {
			//求个位 十位 百位。。上的数字
			k = (a[j] / radix) % 10;
			cnt[k]++;
		}

		//计数排序原理
		//cout << "计数排序原理" << endl;
		for (int j = 1; j < 10; j++) {
			cnt[j] += cnt[j - 1];//cnt[j]为每个数排序完之后在第几位
		}

		for (int j = size - 1; j >= 0; j--) {
			//得到需要比较的那位数字
			k = (a[j] / radix) % 10;
			temp[cnt[k] - 1] = a[j];
			cnt[k]--;
		}

		//copy到a数组
		/*cout << "copy到a数组" << endl;*/
		for (int j = 0; j < size; j++) {
			a[j] = temp[j];
		}
		radix *= 10;
	}

	delete[]temp;
}


/**
 *  @name        : void ColorSort(int *a,int size)
 *  @description : 颜色排序
 *  @param       : 数组指针a（只含0，1，2元素），数组长度size
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
 *  @name        : 自拟
 *  @description : 在一个无序序列中找到第K大/小的数
 *  @param       : 数组指针a，数组长度size   1为大 0为小
 */
int findK(int* a, int size, bool bigOrSmall,int k) {

	//排序一遍
	fastSortPlus(a, 0, size - 1);

	if (bigOrSmall) {
		//第k大 即第n-k+1小
		return a[size - k + 1 - 1];
	}
	else {
		//第k小
		return a[k - 1];
	}
	
}