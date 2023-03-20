#include"linkedList.h"
#include<iostream>
using namespace std;

LinkedList head=NULL;
int main() {

	menu();
}
/**
 *  @name        : Status InitList(LinkList *L);
 *	@description : initialize an empty linked list with only the head node without value
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status InitList(LinkedList* L) {
	//LinkedList = LNode*
	//�˴�LΪ ָ��LNode���� ��ָ�� ��ָ��
	LNode *newhead = new LNode;
	newhead->data = NULL;
	newhead->next = NULL;
	*L = newhead;
	return SUCCESS;
}

/**
 *  @name        : void DestroyList(LinkedList *L)
 *	@description : destroy a linked list, free all the nodes
 *	@param		 : L(the head node)
 *	@return		 : None
 *  @notice      : None
 */
void DestroyList(LinkedList* L) {
	//Lָ��ͷ���
	//�˴�LΪ ָ��LNode���� ��ָ�� ��ָ��

	//LΪ�ջ�Lָ���Ϊ��ʱ ��������
	if (L == NULL ) {
		return;
	}
	if (*L == NULL) {
		L = NULL;
		return;
	}

	//��������ָ�� ����L
	LinkedList p1 =*L;
	LinkedList p2 =*L;

	//p2����p1 p1��ǰ ɾ��p2 ֱ��p1ָ��NULL
	while (p1 != NULL) {

		p2 = p1;
		p1 = p1->next;
		delete p2;
	}
	p2 = NULL;
	*L = NULL;
	
}

/**
 *  @name        : Status InsertList(LNode *p, LNode *q)
 *	@description : insert node q after node p
 *	@param		 : p, q
 *	@return		 : Status
 *  @notice      : None
 */
Status InsertList(LNode* p, LNode* q) {

	//����q p Ϊ�յ���� ����error
	if (p == NULL || q == NULL) {
		return ERROR;
	}


	//��¼p����һ���ڵ��λ��(����pΪβ�������)
	LNode* temp = p->next;

	//��p��ָ����ָ��q
	p->next = q;

	//��q��ָ����ָ�� ԭ p����һ���ڵ�
	q->next = temp;

	return SUCCESS;
}

/**
 *  @name        : Status DeleteList(LNode *p, ElemType *e)
 *	@description : delete the first node after the node p and assign its value to e
 *	@param		 : p, e
 *	@return		 : Status
 *  @notice      : None
 */
Status DeleteList(LNode* p, ElemType* e) {

	//����p�Ƿ�Ϊβ����p�Ƿ�ΪNULL ���򷵻�error
	if (p->next == NULL || p == NULL) {
		return ERROR;
	}
	
	//pnext Ϊp����һ���ڵ�
	LNode* pnext = p->next;

	//eָ��һ��������   ��pnext���� �浽eָ���������
	*e = pnext->data;

	//��p��ָ����ָ��pnext����һ���ڵ㣨ΪNULLҲ���£���ΪNULLҲ���£�
	p->next = pnext->next;

	//ɾ��pnext
	free(pnext); pnext = NULL;

	return SUCCESS;

}
/*����һ��ֵ ɾ�������е�һ�������ֵ��ȵĽڵ�*/
Status DeleteLNode(LNode* head, ElemType e) {

	//���е�һ������e�����
	if (head->data == e) {
		LNode* aaa = head;
		head = head->next;
		free(aaa);
		aaa = NULL;
	}
	
	
	LNode* pprior = head;
	LNode* temp = head;

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
	if (found) {//����ҵ��˸ýڵ� ��ʼɾ��
		//����temp����һ���ڵ�pnext
		LNode* pnext = temp->next;
		//��temp��ǰһ���ڵ�ppriorָ��pnext
		pprior->next = pnext;
		free(temp);
		temp = NULL;
		/*pprior = NULL;
		pnext == NULL;*/
	}

	
	return found?SUCCESS:ERROR;

}

/**
 *  @name        : void TraverseList(LinkedList L, void (*visit)(ElemType e))
 *	@description : traverse the linked list and call the funtion visit
 *	@param		 : L(the head node), visit
 *	@return		 : None
 *  @notice      : None
 */
void TraverseList(LinkedList L, void (*visit)(ElemType e)) {

	//�ж�L�Ƿ�ΪNULL�������������
	if (L == NULL) {
		return;
	}

	Status alreadyLooped = IsLoopList(head);
	if (alreadyLooped) {
		cout << "�������Ѿ��ɻ�,ֻչʾһ����������" << endl;
		//����һ��bool��������¼�Ƿ�ɻ�
		LNode* temp = L;

		LNode* p1 = L;
		LNode* p2 = L;
		int cnt = 0;
		//p1 p2 �������ʱ ˵������ɻ�
		while (cnt < 10) {
			cout << p1->data;
			p1 = p1->next;
			p2 = p2->next->next;
			
			//�ж�p1p2�Ƿ����� ����˵���ɻ� 
			if (p1 == p2) {
				cnt++;
				cout << " ";
			}
		}
	}
	else{
		LinkedList temp = L;

		while (temp != NULL) {

			//����visit����
			visit(temp->data);

			//������һ���ڵ�
			temp = temp->next;
		}
	}

}

/**
 *  @name        : Status SearchList(LinkedList L, ElemType e)
 *	@description : find the first node in the linked list according to e
 *	@param		 : L(the head node), e
 *	@return		 : Status
 *  @notice      : None
 */
Status SearchList(LinkedList L, ElemType e) {

	//�ж�L�Ƿ�Ϊ�� ���򷵻�error
	if (L == NULL) {
		return ERROR;
	}

	//ok���� ��¼ �Ƿ��ҵ� ��������eһ�µĽڵ� �ҵ�����Ϊtrue û�ҵ���Ϊfalse
	bool ok = false;

	//��������
	LinkedList temp = L; 
	while (temp != NULL) {

		//�ж�temp�ڵ��ֵ�Ƿ����e
		if (temp->data == e) {
			//�ҵ��� ��ok��Ϊtrue
			ok = true;
			break;
		}
		//������һ���ڵ�
		temp = temp->next;
	}

	//�����Ƿ��ҵ� ����SUCCESS��ERROR
	return ok ? SUCCESS : ERROR;
}

/**
 *  @name        : Status ReverseList(LinkedList *L)
 *	@description : reverse the linked list
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */

Status ReverseList(LinkedList* L) {
	
	//�ж�L�Ƿ�Ϊ�� 
	if (L == NULL) {
		return ERROR;
	}

	//�õ����ķ���
	
	//��������ָ�� ǰ�к�  �м��ΪnewHead ͬʱnewHeadҲΪ��ת������ͷ���
	
	//һ��ʼ�� �м�ָ��ָ��ͷ��� ��ָ������ΪNULL 
	LNode* newHead = *L;
	LNode* behind =NULL;
	LNode* front = newHead->next;
	
	while (front!=NULL) {
		//�м��ָ��ָ��Ľڵ� ��ָ���� ָ���һ���ڵ�
		newHead->next = behind;

		//����ָ�����ǰ��һ��
		behind = newHead;
		newHead = front;
		front = front->next;
	}
	//��ʱ��newHeadΪԭβ��� ָ����ָ��NULL ����������ָ����ָ��behind
	newHead->next = behind;

	*L = newHead;
	return SUCCESS;
}

/**
 *  @name        : Status IsLoopList(LinkedList L)
 *	@description : judge whether the linked list is looped
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status IsLoopList(LinkedList L) {

	//all in all ��������ǰ���ٶȲ�һ����ָ���Ƿ��������ж��Ƿ�ɻ�
	

	//�ж�L�Ƿ�Ϊ��
	if (L == NULL||L->next==NULL) {
		return ERROR;
	}

	//����һ��bool��������¼�Ƿ�ɻ�
	bool IsLoopList = false;
	LNode* temp = L;

	LNode* p1 = L;
	LNode* p2 = L;
	int cnt = 0;
	//p1 p2 �������ʱ ˵������ɻ�
	while (cnt<5) {

		p1 = p1->next;
		//��p2����һ���ڵ㲻ΪNULL p2��������
		if (p2->next != NULL) {
			p2 = p2->next->next;
		}
		else {
			//����˵�� �����ǳɻ���
			IsLoopList = false;
			break;
		}
		

		// ����p1 ���ÿ���ڵ��ֵ
		//cout << p1->data;

		//�ж�p1p2�Ƿ����� ����˵���ɻ� 
		if (p1 == p2) {
			IsLoopList = true;
			cnt++;
			cout << " ";
		}
		if (p1 == NULL || p2 == NULL) {
			IsLoopList = false;
			break;
		}
	}

	
	return IsLoopList ? SUCCESS : ERROR;
}

/**
 *  @name        : LNode* ReverseEvenList(LinkedList *L)
 *	@description : reverse the nodes which value is an even number in the linked list, input: 1 -> 2 -> 3 -> 4  output: 2 -> 1 -> 4 -> 3
 *	@param		 : L(the head node)
 *	@return		 : LNode(the new head node)
 *  @notice      : choose to finish
 */
LNode* ReverseEvenList(LinkedList* L) {
	if (L == NULL) {
		return NULL;
	}

	ElemType temp;
	LNode* p = *L;
	LNode* pnext = p->next;
	////����ֻ��һ���ڵ�����			���������� whileѭ��������
	//if (pnext == NULL) {
	//	return *L;
	//}

	
	//pһֱΪ���������ڵ�  pnextΪ��ż�����ڵ�
	while (p != NULL) {

		//����������
		pnext = p->next;
		temp = p->data;
		if (pnext != NULL)
		{
			p->data = pnext->data;
			pnext->data = temp;
		}
		else {
			//��ʱ ����ڵ�Ϊ������ pΪβ��� pnextָ��NULL
			break;
		}

		////���� pǰ�������ڵ�
		////���ж�pnext����һ���Ƿ�� ��Ϊ����pǰ�������ڵ�
		//if (pnext->next != NULL) {
		//	p = pnext->next;
		//}
		//else {
		//	//��ʱ ����Ϊż���� pnextΪβ���
		//	break;
		//}
		//  �������һ�� ֱ��ǰ������ ��pָ��� ��while��������
		p = pnext->next;
	}

	return *L;
}

/**
 *  @name        : LNode* FindMidNode(LinkedList *L)
 *	@description : find the middle node in the linked list
 *	@param		 : L(the head node)
 *	@return		 : LNode
 *  @notice      : choose to finish
 */
LNode* FindMidNode(LinkedList* L) {

	//�ж�L�Ƿ�ΪNULL��*L�Ƿ�ΪNULL
	if (L == NULL || *L == NULL){
		return NULL;
	}

	//�ж�L�Ƿ�ɻ�,���򷵻ؿսڵ�
	if (IsLoopList(*L) == SUCCESS) {
		return NULL;
	}

	//��֪�����ɻ� ���ҵ��е�
	// 
	//p1��һ�� p2������ ��p2��������βʱ��p1Ҳ�������е�
	LNode* p1 = *L;
	LNode* p2 = *L;
	LNode* ass = NULL;

	while (p2!=NULL) {
		//������Ϊż�� 2n+1  ���ص�n+1��
		if (p2->next != NULL) {
			p2 = p2->next->next;
		}
		else {
			//˵����ʱp2�Ѿ���������β  ������Ϊ����
			ass = p1;
			break;
		}
		p1 = p1->next;

		//������Ϊż�� 2n  ���ص�n+1��
		//��ʱp2�Ѿ�����β��� ָ����NULL
		if (p2 == NULL) {
			ass = p1;
			break;
		}
	}
	return ass;
}








/*
	չʾ��ת������
*/
void showReleaseList() {

	//����׼��
	LNode* node1 = new LNode;
	LNode* node2 = new LNode;
	LNode* node3 = new LNode;

	node1->data = 1;
	node2->data = 2;
	node3->data = 3;

	node1->next = node2;
	node2->next = node3;
	node3->next = NULL;
	LinkedList* L = &node1;


	//չʾδ��תǰ������
	cout << "δ��תǰ������:";
	LNode* temp2 = *L;
	while (temp2 != NULL) {
		cout << temp2->data <<" ";
		temp2 = temp2->next;
	}
	cout << endl;


	//��ת����չʾ��չ�������
	ReverseList(L);
	LNode* temp = *L;
	cout << "��ת�������:";
	while (temp != NULL) {
		cout << temp->data << " ";
		temp = temp->next;
	}

}


/*
���ɰ���6����λ�����ֵ����� ����ָ��ͷ����ָ��
*/
LinkedList createLinkedList() {
	cout << "��������ʼ��������" << endl<<"-------"<<endl;
	cout << "������6����������6������ȡǰ6������ֵ��Χ��0~9֮��" << endl;
	char a[10];
	char num[6];
	bool ok = 0;
	int p = 0;
	while (p<6) {
		cin >> a[p++];
		if (!(a[p - 1] >= '0' && a[p - 1] <= '9'))
		{
			p--;
			cout << "���������~ ������" << 6 - p << "��0~9֮�ڵ�����:";
		}
	}
	cout << "���ո��������Ϊ:";
	for (int i = 0; i < 6; i++) {
		num[i] = a[i];
		cout<<num[i]<<" ";
	}

	//�ֶ�newͷ���
	LNode* head=new LNode;
	head->data = int(num[0]-'0');
	head->next = NULL;

	LNode* temp =head;

	
	//new5���ڵ� ����num�������±�1��5���ַ�
	for (int i = 1; i < 6; i++) {
		LNode* p = new LNode;
		//char����תint
		p->data = int(num[i] - '0');
		InsertList(temp, p);
		temp = temp->next;
	}
	temp = NULL;
	return head;
}



/*vist����*/
/*vist����*/
/*vist����*/
void visit(ElemType e) {
	cout << e <<" ";
}

void menu() {
	cout << "------��ӭ�����ҵĵ�������ҵչʾ------" << endl;
	cout << "-------------------------------" << endl;
	cout << "----��������һ��LNode���͵Ŀ�ָ��head" << endl;
	cout << "----�����Խ������²���:" << endl;
	cout << "----1.��ʼ��һ������" << endl;
	cout << "----2.����һ��������(���������ʼ��Ŷ~)" << endl;
	cout << "----b.��������" << endl;
	cout << "----3.����һ���ڵ�" << endl;
	cout << "----4.ɾ��һ���ڵ�" << endl;
	cout << "----5.ʹ����ɻ�" << endl;
	cout << "----6.�ж������Ƿ�ɻ�" << endl;
	cout << "----7.��������ż����" << endl;
	cout << "----8.�ҵ�������е�" << endl;
	cout << "----9.��ת����" << endl;
	cout << "----0.��������" << endl;
	cout << "----a.�˳�ϵͳ" << endl;
	char option = cinmenu();
	switch (option) {
	case '1':cout << "1.��ʼ��һ������:\t"; function1(); break;
	case '2':cout << "2.����һ��������\t"; function2(); break;
	case 'b':cout << "b.��������:\t"; functionb(); break;
	case '3':cout << "3.����һ���ڵ�:\t"; function3(); break;
	case '4':cout << "4.ɾ��һ���ڵ�:\t"; function4(); break;
	case '5':cout << "5.ʹ����ɻ�:\t"; function5(); break;
	case '6':cout << "6.�ж������Ƿ�ɻ�:\t"; function6(); break;
	case '7':cout << "7.��������ż����:\t"; function7(); break;
	case '8':cout << "8.�ҵ�������е�:\t"; function8(); break;
	case '9':cout << "9.��ת����:\t"; function9(); break;
	case '0':cout << "0.��������:\t"; function0(); break;
	case 'a':cout << "a.�˳�ϵͳ:\t"; functiona(); break;
	default:
		cout << "���������" << endl;
		cout << "---------" << endl;
		buffer();
	}
	buffer();
}
/*1.��ʼ��һ������
*/
void function1() {
	cout << endl;
	cout << "--------" << endl;
	cout << "��ʼ��ʼ������ing���Ͼͺ���~~" << endl;
	Status initListSuccess = InitList(&head);
	if (initListSuccess == SUCCESS) {
		cout << "��ʼ���ɹ�" << endl;
	}
}

/*2.����һ��������*/
void function2() {
	head = createLinkedList();
	cout << "�����ɹ�������" << endl;
}

/*b.��������*/
void functionb() {
	cout << "-------" << endl;
	bool isnull = false;
	if (head == NULL) {
		isnull = true;
		cout << "�������ڻ��ǿյ��� ����ʧ��quq~" << endl;
		
	}
	if(!isnull) {
		cout << "��ʼ������������" << endl;
		TraverseList(head, visit);
		cout << endl;
	}
}
/*3.����һ���ڵ�*/
void function3() {
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	int p = cinnnum();
	LNode* q = new LNode;
	q->data = p;
	InsertList(head, q);
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
	Status ojbk = DeleteLNode(head, e);
	if (ojbk == SUCCESS) {
		cout << "ɾ���ɹ�����" << endl;
	}
	else {
		cout << "ɾ��ʧ��~,������������û�и���ֵ" << endl;
	}
}

/*5.ʹ����ɻ�*/
void function5(){
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	Status ojbk = makelinkedLishLooped(head);
	if (ojbk == SUCCESS) {
		cout << "�ɻ��ɹ�����" << endl;
	}
	else {
		cout << "�ɻ�ʧ��qwq���������Ѿ��ɻ��˻�����ֻ��һ���ڵ�"<<endl;
	}
}

/*6.�ж������Ƿ�ɻ�*/
void function6(){
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	Status alreadyLooped = IsLoopList(head);
	cout << alreadyLooped ? "����ɻ�" : "�����ɻ�";

}

/*7.��������ż����*/
void function7(){
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	//�ж������Ƿ�ɻ�
	Status alreadyLooped = IsLoopList(head);
	if (alreadyLooped) {
		cout << "����ʧ����~����������Ϊ����ɻ���" << endl;
	}
	ReverseEvenList(&head);
	cout << "�����ɹ���~";

}

/*8.�ҵ�������е�*/
void function8(){
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	LNode* mid = FindMidNode(&head);
	if (mid != NULL) {
		cout << "�ɹ��ҵ�������е�:"<<mid->data<< endl;
	}
	else {
		cout <<"��������,��������Ϊ����ɻ�����~"<< mid << endl;
	}
}


/*9.��ת����*/
void function9(){
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	//�ж������Ƿ�ɻ�
	Status alreadyLooped = IsLoopList(head);
	if (alreadyLooped) {
		cout << "��תʧ����~����������Ϊ����ɻ���" << endl;
	}

	ReverseList(&head);
	cout << "��ת�ɹ���~" << endl;

}

/*0.��������*/
void function0(){
	//���ж��Ƿ��ʼ��
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	DestroyList(&head);
}


/*a.�˳�ϵͳ*/
void functiona(){
	cout << "ллʹ����~~";
	exit(0);

}

void buffer() {
	cout << "---------" << endl;
	cout << "����w�ص��˵�..." << endl;
	char a; cin >> a;
	system("cls");
	menu();
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
/*�ڲ˵���ʱ���õ����뺯�� 0-9��a,b,  */
char cinmenu() {
	cout << "������0-9,a��b:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "���������~����������0-9,a��b:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '9'||p=='a'||p=='b') {
			ok = 1;
			cout << "����ɹ�:" << p << endl;
			cout << "-------"<<endl;
			break;
		}
		cout << "���������~����������0-9,a��b:" << endl;
	}
	return p;
}

/*ʹ����ɻ�����*///ʹ����β���ӵ������е�
Status makelinkedLishLooped(LinkedList head) {

	//����Ѿ��ɻ� ����ERROR
	if (IsLoopList(head) == SUCCESS) {
		return ERROR;
	}
	//�ж������Ƿ�ֻ��һ���ڵ�
	//�ҵ��е�
	LNode* mid = FindMidNode(&head);

	LinkedList temp = head;
	bool ojbk = false;
	while (1) {
		if (temp->next==NULL) {
			temp->next = mid;
			ojbk = true;
			break;
		}
		//������һ���ڵ�
		temp = temp->next;
	}
	return ojbk ? SUCCESS : ERROR;
}


Status judgeIsInitList(LNode* L) {
	//���ж�head�Ƿ�ָ��NULL
	if (head == NULL) {
		cout << "sorry����û��ʼ���أ������Ƚ��г�ʼ������Ĳ�����~~" << endl;
		return ERROR;
	}
	else {
		return SUCCESS;
	}
}