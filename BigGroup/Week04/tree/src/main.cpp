#include"../inc/binary_sort_tree.h"
BinarySortTreePtr tree = new BinarySortTree;
clock_t start, endtime;



int main() {
    printf("Hello\n");
	tree->root = NULL;
    /*NodePtr p = new Node;
    p->left = NULL;
    p->right = NULL;
    p->value = NULL;*/
	menu();
    return 0;
}

void menu() {
	cout << "------" << "BinarySortTreePtr Show" << "------" << endl;
	cout << "------" << "您现在有一个指向BinarySortTree的指针tree" << endl;
	cout << "------" << "您可以进行以下操作" << endl;
	cout << "------" << "0.初始化根节点(默认值为100)" << endl;
	cout << "------" << "1.查找节点" << endl;
	cout << "------" << "2.插入节点" << endl;
	cout << "------" << "3.删除节点" << endl;
	cout << "------" << "4.先序遍历" << endl;
	cout << "------" << "5.中序遍历" << endl;
	cout << "------" << "6.后序遍历" << endl;
	cout << "------" << "7.层序遍历" << endl;/*
	cout << "------" << "7.先序遍历(递归)" << endl;
	cout << "------" << "8.中序遍历(递归)" << endl;
	cout << "------" << "9.后序遍历(递归)" << endl;*/
	cout << "------" << "z.退出" << endl;
	cout << "------" << "注:orz不是orz而是\"or z\" ~" << endl;/*
	cout << "------" << "注:选择生成500个数据即可查看具体数据~" << endl;
	cout << "------" << "注:每次排序完都会帮您重新生成新的数据喵~" << endl;*/
	//cout << "------" << "0." << endl;


	char option;
	bool ok = false;

	//想选1到7之前必须先选1
	while (!ok) {
		//输入1到7
		option = cinmenu();
		bool alreadyok = alreadyInit(tree);
		if (option != '0' && option != 'z' && !alreadyok) {
			//选1到7 且 未进行初始化
			/*cout << s<<endl;*/
			cout << "您还没有进行初始化哟~ 请您先进行初始化" << endl;
		}
		else {
			ok = 1;
		}
	}
	int num = 0;
	Status ojbk = false;
	//0或z   或已alreadyInit选1到7
	switch (option) {

	case '0':
		cout << "0.初始化根节点(默认值为100):\t" << endl;
		
		ojbk = BST_init(tree);
		if (ojbk) {
			cout << "初始化成功喵！" << endl;
		}
		else {
			cout << "初始化失败~可能是已经初始化过啦" << endl;
		}
		break;

	case '1':
		cout << "1.查找节点:\t" << endl;
		num = cinNum();
		ojbk = BST_search(tree, num);
		if (ojbk) {
			cout << "查找成功:" << num << endl;
		}
		else {
			cout << "查找失败~树里没有这个值哦" << endl;
		}
		break;

	case '2':
		cout << "2.插入节点:\t" << endl;
		num = cinNum();
		ojbk = BST_insert(tree, num);
		if (ojbk) {
			cout << "插入成功:" << num << endl;
		}
		else {
			cout << "插入失败~树里已经存在这个值了喵~" << endl;
		}
		break;

	case '3':
		cout << "3.删除节点:\t" << endl;
		num = cinNum();
		ojbk = BST_delete(tree, num);
		if (ojbk) {
			cout << "删除成功:" << num << endl;
		}
		else {
			cout << "删除失败~可能是树里不存在这个值喵~" << endl;
		}
		break;

	case '4':
		cout << "4.先序遍历:\t" << endl;
		if (tree->root == NULL) {
			cout << "无法遍历，原因是 目前树是空的哦~" << endl;
			break;
		}
		//非递归
		//进行计时和遍历
		start = clock();
		BST_preorderI(tree, visit);
		endtime = clock();
		cout << endl << "非递归Total time:" << endtime - start << "ms" << endl;


		//递归
		//进行计时和遍历
		start = clock();
		BST_preorderR(tree->root, visit);
		endtime = clock();
		cout << endl << "递归Total time:" << endtime - start << "ms" << endl;

		break;

	case '5':
		cout << "5.中序遍历:\t" << endl;
		if (tree->root == NULL) {
			cout << "无法遍历，原因是 目前树是空的哦~" << endl;
			break;
		}
		
		//非递归
		//进行计时和遍历
		start = clock();
		BST_inorderI(tree, visit);
		endtime = clock();
		cout << endl << "非递归Total time:" << endtime - start << "ms" << endl;


		//递归
		//进行计时和遍历
		start = clock();
		BST_inorderR(tree->root, visit);
		endtime = clock();
		cout << endl << "递归Total time:" << endtime - start << "ms" << endl;

		break;

	case '6':
		cout << "6.后序遍历:\t" << endl;
		if (tree->root == NULL) {
			cout << "无法遍历，原因是 目前树是空的哦~" << endl;
			break;
		}

		//非递归
		//进行计时和遍历
		start = clock();
		BST_postorderI(tree, visit);
		endtime = clock();
		cout << endl << "非递归Total time:" << endtime - start << "ms" << endl;


		//递归
		//进行计时和遍历
		start = clock();
		BST_postorderR(tree->root, visit);
		endtime = clock();
		cout << endl << "递归Total time:" << endtime - start << "ms" << endl;

		break;

	case '7':
		cout << "7.层序遍历:\t" << endl;
		if (tree->root == NULL) {
			cout << "无法遍历，原因是 目前树是空的哦~" << endl;
			break;
		}

		//进行计时和遍历
		start = clock();
		BST_levelOrder(tree, visit);
		endtime = clock();
		cout << endl << "Total time:" << endtime - start << "ms" << endl;

		break;

	case 'z':
		cout << "z.退出:\t" << endl;
		xiabo(); break;


	default:
		//不可能到达这里
		buffer();
	}
	
	buffer();

}