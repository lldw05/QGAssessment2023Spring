#include"duLinkedList.h"
#include<iostream>
using namespace std;

DuLinkedList head = NULL;

int main() {

	menu();
}
/**************************************************************
*	Prototype Declare Section
**************************************************************/

/**
 *  @name        : Status InitList_DuL(DuLinkedList *L)
 *	@description : initialize an empty linked list with only the head node
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status InitList_DuL(DuLinkedList* L) {
	DuLNode* newhead = new DuLNode;
	newhead->data = NULL;
	newhead->next = NULL;
	newhead->prior = NULL;
	*L = newhead;
	return SUCCESS;
}

/**
 *  @name        : void DestroyList_DuL(DuLinkedList *L)
 *	@description : destroy a linked list
 *	@param		 : L(the head node)
 *	@return		 : status
 *  @notice      : None
 */
void DestroyList_DuL(DuLinkedList* L) {
	
	//�ж�L�Ƿ�Ϊ�ջ�Lָ���
	if (L == NULL || *L == NULL) {
		return;
	}

	//
	DuLNode* temp = *L;
	DuLNode* p = *L;
	p = p->next;
	while (p != NULL) {
		free(temp);
		temp = p;
		p = p->next;
	}
	temp = NULL;
	p = NULL;
	//һ��ʼд���� L=NULL headָ���˲����ڵ�λ�� �����Ұָ��
	*L = NULL;
	//��� Lָ���
}

/**
 *  @name        : Status InsertBeforeList_DuL(DuLNode *p, LNode *q)
 *	@description : insert node q before node p
 *	@param		 : p, q
 *	@return		 : status
 *  @notice      : None
 */
Status InsertBeforeList_DuL(DuLNode* p, DuLNode* q) {
	//�ж�p q �Ƿ�Ϊ��
	if (p == NULL || q == NULL) {
		return ERROR;
	}

	//���ȼ�¼��p��ǰһ���ڵ�
	DuLNode* temp = p->prior;

	//��q�ŵ�p��ǰ��
	p->prior = q;
	q->next = p;
	q->prior = temp;
	return SUCCESS;
}

/**
 *  @name        : Status InsertAfterList_DuL(DuLNode *p, DuLNode *q)
 *	@description : insert node q after node p
 *	@param		 : p, q
 *	@return		 : status
 *  @notice      : None
 */
Status InsertAfterList_DuL(DuLNode* p, DuLNode* q) {
	//�ж�p q �Ƿ�Ϊ��
	if (p == NULL || q == NULL) {
		return ERROR;
	}

	//���ȼ�¼��p�ĺ�һ���ڵ�
	DuLNode* temp = p->next;

	//��q�ŵ�p��ǰ��
	p->next = q;
	q->prior = p;
	q->next = temp;
	return SUCCESS;
}

/**
 *  @name        : Status DeleteList_DuL(DuLNode *p, ElemType *e)
 *	@description : delete the first node after the node p and assign its value to e
 *	@param		 : p, e
 *	@return		 : status
 *  @notice      : None
 */
Status DeleteList_DuL(DuLNode* p, ElemType* e) {

	//����p�Ƿ�Ϊβ����p�Ƿ�ΪNULL ���򷵻�error
	if (p->next == NULL || p == NULL) {
		return ERROR;
	}

	//pnext Ϊp����һ���ڵ�
	DuLNode* pnext = p->next;
	//eָ��һ��������   ��pnext���� �浽eָ���������
	*e = pnext->data;

	//��p��ָ����ָ��pnext����һ���ڵ㣨ΪNULLҲ���£���ΪNULLҲ���£�
	p->next = pnext->next;

	//ɾ��pnext
	free(pnext); pnext = NULL;


	if (p->next != NULL)
	{//��p����һ����Ϊ��ʱ ����һ���Ľڵ��priorָ��p
		p->next->prior = p;
	}
	else {//p����һ��Ϊ�� 

	}
	return SUCCESS;
}

/**
 *  @name        : void TraverseList_DuL(DuLinkedList L, void (*visit)(ElemType e))
 *	@description : traverse the linked list and call the funtion visit
 *	@param		 : L(the head node), visit
 *	@return		 : Status
 *  @notice      : None
 */
void TraverseList_DuL(DuLinkedList L, void (*visit)(ElemType e)) {
	//�ж�L�Ƿ�ΪNULL�������������
	if (L == NULL) {
		return;
	}

	DuLinkedList temp = L;

	while (temp != NULL) {

		//����visit����
		visit(temp->data);

		//������һ���ڵ�
		temp = temp->next;
	}
}

/*
* visit����
*/
void visit(ElemType e) {
	cout << e << " ";
}
/*�˵�*/
void menu() {
	cout << "------��ӭ�����ҵ�˫��������ҵչʾ------" << endl;
	cout << "-------------------------------" << endl;
	cout << "----��������һ��DuLNode���͵Ŀ�ָ��head" << endl;
	cout << "----�����Խ������²���:" << endl;
	cout << "----1.��ʼ��һ������" << endl;
	cout << "----b.��������" << endl;
	cout << "----3.����һ���ڵ�" << endl;
	cout << "----4.ɾ��һ���ڵ�" << endl;
	cout << "----0.��������" << endl;
	cout << "----a.�˳�ϵͳ" << endl;
	char option = cinmenu();
	switch (option) {
	case '1':cout << "1.��ʼ��һ������:\t"; function1(); break;
	case 'b':cout << "b.��������:\t"; functionb(); break;
	case '3':cout << "3.����һ���ڵ�:\t"; function3(); break;
	case '4':cout << "4.ɾ��һ���ڵ�:\t"; function4(); break;
	case '0':cout << "0.��������:\t"; function0(); break;
	case 'a':cout << "a.�˳�ϵͳ:\t"; functiona(); break;
	default:
		cout << "���������" << endl;
		cout << "---------" << endl;
		buffer();
	}
	buffer();
}

/*���� ʹ����ĳ�����ܺ� ����w��մ��ڻص��˵�... */
void buffer() {
	cout << "---------" << endl;
	cout << "����w�ص��˵�..." << endl;
	string a; cin >> a;
	system("cls");
	menu();
}



/*�ڲ˵���ʱ���õ����뺯�� 0-4��a,b,  */
char cinmenu() {
	cout << "������0,1,3,4,a��b:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~����������0,1,3,4,a��b:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '4'&&p!='2' || p == 'a' || p == 'b') {
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			cout << "-------" << endl;
			break;
		}
		cout << "���������~����������0,1,3,4,a��b:" << endl;
	}
	return p;
}

/*���뺯�� ֻ������0��9ֱ�ӵ����� ������ᱨ��*/
int cinnnum() {
	cout << "������һ��0��9֮�������:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~����������0��9֮�������:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '9') {
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			break;
		}
		cout << "���������~����������0��9֮�������:" << endl;
	}
	return int(p - '0');
}

/*�����ʼ��*/
void function1(){
	cout << endl;
	cout << "--------" << endl;
	cout << "��ʼ��ʼ������ing���Ͼͺ���~~" << endl;
	Status initListSuccess = InitList_DuL(&head);
	if (initListSuccess == SUCCESS) {
		cout << "��ʼ���ɹ�" << endl;
	}
}

//�½�����ͬʱ��������
//void function2(){}

void function3(){
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	//���� ��ֵ
	int p = cinnnum();
	//newһ���ڵ�
	DuLNode* q = new DuLNode;
	q->data = p;
	q->next = NULL;
	q->prior = NULL;
	InsertAfterList_DuL(head, q);
	cout << "����ڵ�ɹ�" << endl;
}

/*4.ɾ��һ���ڵ�*/
void function4() {
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	int e = cinnnum();
	Status ojbk = DeleteDuLNode(&head, e);
	if (ojbk == SUCCESS) {
		cout << "ɾ���ɹ�����" << endl;
	}
	else {
		cout << "ɾ��ʧ��~,������������û�и���ֵ" << endl;
	}
}


/*0.��������*/
void function0() {
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	cout << "-------" << endl;
	DestroyList_DuL(&head);
	cout << "���ٳɹ�����" << endl;
	cout << endl;
}

/*a.�˳�ϵͳ*/
void functiona() {
	cout << "ллʹ����~~"<<endl;
	exit(0);
}

/*b.��������*/
/*���ֱ������� һ���Զ����� һ���ֶ����������ֶ�ѡ�� ��һ�� ��һ����*/
void functionb() {
	cout << endl;
	cout << "-------" << endl;
	bool isnull = false;
	if (head == NULL) {
		isnull = true;
		cout << "�������ڻ��ǿյ��� ����ʧ��quq~" << endl;

	}
	if (!isnull) {
		cout << "��ѡ��:\n1.�Զ�����\t2.�ֶ�����\t0.����" << endl;
		int op = inputChoiceTraverseWay();
		switch(op)
		{
		case 1:
				cout << "��ʼ������������" << endl;
				TraverseList_DuL(head, visit);
				cout << endl;
				break;
		case 2:manualTraverseList_DuL(head, visit); break;
		case 0:return;
		}
	}
}


Status judgeIsInitList(DuLNode* L) {
	//���ж�head�Ƿ�ָ��NULL
	if (L == NULL) {
		cout << "sorry����û��ʼ���أ������Ƚ��г�ʼ������Ĳ�����~~" << endl;
		return ERROR;
	}
	else {
		return SUCCESS;
	}
}



/*ѡ�������ʽʱ �����õ�input����*/
int inputChoiceTraverseWay() {
	//���� 0 1 2 
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~������Ҫ��������:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '2') {
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			break;
		}
		cout << "���������~������Ҫ��������:" << endl;
	}
	//�����ַ� 1 2 0 ���� int��  1 2 0
	return int(p - '0');
}


/*�ֶ�����˫������ ��ѡ��һ����һ��*/
void manualTraverseList_DuL(DuLinkedList L, void (*visit)(ElemType e)) {

	//��մ��� ��ʼ����
	system("cls");
	DuLinkedList p = L;

	cout << "��һ���ڵ��ֵΪ��" << L->data << endl;
	cout << "��ѡ��:\n1.��һ��\t2.��һ��\t0.����" << endl;
	bool finished = false;
	while(!finished)
	{
		//system("cls");ԭ���������� ����������� ����ʲô��������
		int op = inputChoiceTraverseWay();
		system("cls");
		switch (op) {
			case 1: 
				//����һ��Ϊ��ʱ ������β ��������ѭ��
				if (L ->next== NULL) {
					cout << "�����Ѿ�������β����Ŷ~\t����ѡ��:2.��һ��\t0.����" << endl;
					continue;
				}
				//������һ�� ���ֵ
				L = L->next;
				cout << "�ýڵ��ֵΪ:"<<endl;
				visit(L->data);
				break;
			
			case 2: 
				//����һ��Ϊ��ʱ ������ͷ ��������ѭ��
				if (L->prior == NULL) {
					cout << "�����Ѿ�������ͷ����Ŷ~\t����ѡ��:1.��һ��\t0.����" << endl;
					continue;
				
				}
				//������һ�� ���ֵ
				L = L->prior;
				cout << "�ýڵ��ֵΪ:" << endl;
				visit(L->data);
				break;
			
			case 0: 
				//�˳�����
				cout << "�˳�����~" << endl;
				return;
			
		}
		cout << "��ѡ��:\n1.��һ��\t2.��һ��\t0.����" << endl;
	}
}



Status DeleteDuLNode(DuLinkedList* L, ElemType e) {

	//��֪ ��������head �ض���Ϊ��

	//���е�һ������e����� ɾ����һ���ڵ�
	if ((*L)->data == e) {
		DuLNode* aaa = *L;
		*L = (*L)->next;
		free(aaa);
		aaa = NULL;
		return SUCCESS;
	}

	//����Ϊ  ���ǵ�һ�������
	DuLNode* pprior = *L;
	DuLNode* temp = *L;

	bool found = false;
	while (temp != NULL) {


		if (temp->data == e) {
			found = true;
			break;
		}
		pprior = temp;
		//������һ���ڵ�
		temp = temp->next;
	}
	if (found) {//����ҵ��˸ýڵ�temp  ��ʼɾ��
		//����temp����һ���ڵ�pnext
		DuLNode* pnext = temp->next;
		//��temp��ǰһ���ڵ�ppriorָ��pnext ͬʱpnextָ��pprior
		pprior->next = pnext; pnext->prior = pprior;
		free(temp);
		temp = NULL;
		/*pprior = NULL;
		pnext == NULL;*/
	}

	return found ? SUCCESS : ERROR;
}

/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
