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
	cout << "------" << "��������һ��ָ��BinarySortTree��ָ��tree" << endl;
	cout << "------" << "�����Խ������²���" << endl;
	cout << "------" << "0.��ʼ�����ڵ�(Ĭ��ֵΪ100)" << endl;
	cout << "------" << "1.���ҽڵ�" << endl;
	cout << "------" << "2.����ڵ�" << endl;
	cout << "------" << "3.ɾ���ڵ�" << endl;
	cout << "------" << "4.�������" << endl;
	cout << "------" << "5.�������" << endl;
	cout << "------" << "6.�������" << endl;
	cout << "------" << "7.�������" << endl;/*
	cout << "------" << "7.�������(�ݹ�)" << endl;
	cout << "------" << "8.�������(�ݹ�)" << endl;
	cout << "------" << "9.�������(�ݹ�)" << endl;*/
	cout << "------" << "z.�˳�" << endl;
	cout << "------" << "ע:orz����orz����\"or z\" ~" << endl;/*
	cout << "------" << "ע:ѡ������500�����ݼ��ɲ鿴��������~" << endl;
	cout << "------" << "ע:ÿ�������궼��������������µ�������~" << endl;*/
	//cout << "------" << "0." << endl;


	char option;
	bool ok = false;

	//��ѡ1��7֮ǰ������ѡ1
	while (!ok) {
		//����1��7
		option = cinmenu();
		bool alreadyok = alreadyInit(tree);
		if (option != '0' && option != 'z' && !alreadyok) {
			//ѡ1��7 �� δ���г�ʼ��
			/*cout << s<<endl;*/
			cout << "����û�н��г�ʼ��Ӵ~ �����Ƚ��г�ʼ��" << endl;
		}
		else {
			ok = 1;
		}
	}
	int num = 0;
	Status ojbk = false;
	//0��z   ����alreadyInitѡ1��7
	switch (option) {

	case '0':
		cout << "0.��ʼ�����ڵ�(Ĭ��ֵΪ100):\t" << endl;
		
		ojbk = BST_init(tree);
		if (ojbk) {
			cout << "��ʼ���ɹ�����" << endl;
		}
		else {
			cout << "��ʼ��ʧ��~�������Ѿ���ʼ������" << endl;
		}
		break;

	case '1':
		cout << "1.���ҽڵ�:\t" << endl;
		num = cinNum();
		ojbk = BST_search(tree, num);
		if (ojbk) {
			cout << "���ҳɹ�:" << num << endl;
		}
		else {
			cout << "����ʧ��~����û�����ֵŶ" << endl;
		}
		break;

	case '2':
		cout << "2.����ڵ�:\t" << endl;
		num = cinNum();
		ojbk = BST_insert(tree, num);
		if (ojbk) {
			cout << "����ɹ�:" << num << endl;
		}
		else {
			cout << "����ʧ��~�����Ѿ��������ֵ����~" << endl;
		}
		break;

	case '3':
		cout << "3.ɾ���ڵ�:\t" << endl;
		num = cinNum();
		ojbk = BST_delete(tree, num);
		if (ojbk) {
			cout << "ɾ���ɹ�:" << num << endl;
		}
		else {
			cout << "ɾ��ʧ��~���������ﲻ�������ֵ��~" << endl;
		}
		break;

	case '4':
		cout << "4.�������:\t" << endl;
		if (tree->root == NULL) {
			cout << "�޷�������ԭ���� Ŀǰ���ǿյ�Ŷ~" << endl;
			break;
		}
		//�ǵݹ�
		//���м�ʱ�ͱ���
		start = clock();
		BST_preorderI(tree, visit);
		endtime = clock();
		cout << endl << "�ǵݹ�Total time:" << endtime - start << "ms" << endl;


		//�ݹ�
		//���м�ʱ�ͱ���
		start = clock();
		BST_preorderR(tree->root, visit);
		endtime = clock();
		cout << endl << "�ݹ�Total time:" << endtime - start << "ms" << endl;

		break;

	case '5':
		cout << "5.�������:\t" << endl;
		if (tree->root == NULL) {
			cout << "�޷�������ԭ���� Ŀǰ���ǿյ�Ŷ~" << endl;
			break;
		}
		
		//�ǵݹ�
		//���м�ʱ�ͱ���
		start = clock();
		BST_inorderI(tree, visit);
		endtime = clock();
		cout << endl << "�ǵݹ�Total time:" << endtime - start << "ms" << endl;


		//�ݹ�
		//���м�ʱ�ͱ���
		start = clock();
		BST_inorderR(tree->root, visit);
		endtime = clock();
		cout << endl << "�ݹ�Total time:" << endtime - start << "ms" << endl;

		break;

	case '6':
		cout << "6.�������:\t" << endl;
		if (tree->root == NULL) {
			cout << "�޷�������ԭ���� Ŀǰ���ǿյ�Ŷ~" << endl;
			break;
		}

		//�ǵݹ�
		//���м�ʱ�ͱ���
		start = clock();
		BST_postorderI(tree, visit);
		endtime = clock();
		cout << endl << "�ǵݹ�Total time:" << endtime - start << "ms" << endl;


		//�ݹ�
		//���м�ʱ�ͱ���
		start = clock();
		BST_postorderR(tree->root, visit);
		endtime = clock();
		cout << endl << "�ݹ�Total time:" << endtime - start << "ms" << endl;

		break;

	case '7':
		cout << "7.�������:\t" << endl;
		if (tree->root == NULL) {
			cout << "�޷�������ԭ���� Ŀǰ���ǿյ�Ŷ~" << endl;
			break;
		}

		//���м�ʱ�ͱ���
		start = clock();
		BST_levelOrder(tree, visit);
		endtime = clock();
		cout << endl << "Total time:" << endtime - start << "ms" << endl;

		break;

	case 'z':
		cout << "z.�˳�:\t" << endl;
		xiabo(); break;


	default:
		//�����ܵ�������
		buffer();
	}
	
	buffer();

}