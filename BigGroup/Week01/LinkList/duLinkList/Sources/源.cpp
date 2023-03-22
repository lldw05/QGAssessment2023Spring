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
	
	//判断L是否为空或L指向空
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
	//一开始写成了 L=NULL head指向了不存在的位置 变成了野指针
	*L = NULL;
	//最后 L指向空
}

/**
 *  @name        : Status InsertBeforeList_DuL(DuLNode *p, LNode *q)
 *	@description : insert node q before node p
 *	@param		 : p, q
 *	@return		 : status
 *  @notice      : None
 */
Status InsertBeforeList_DuL(DuLNode* p, DuLNode* q) {
	//判断p q 是否为空
	if (p == NULL || q == NULL) {
		return ERROR;
	}

	//首先记录下p的前一个节点
	DuLNode* temp = p->prior;

	//将q放到p的前面
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
	//判断p q 是否为空
	if (p == NULL || q == NULL) {
		return ERROR;
	}

	//首先记录下p的后一个节点
	DuLNode* temp = p->next;

	//将q放到p的前面
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

	//特判p是否为尾结点或p是否为NULL 是则返回error
	if (p->next == NULL || p == NULL) {
		return ERROR;
	}

	//pnext 为p的下一个节点
	DuLNode* pnext = p->next;
	//e指向一块数据域   将pnext数据 存到e指向的数据域
	*e = pnext->data;

	//让p的指针域指向pnext的下一个节点（为NULL也记下，不为NULL也记下）
	p->next = pnext->next;

	//删除pnext
	free(pnext); pnext = NULL;


	if (p->next != NULL)
	{//当p的下一个不为空时 让下一个的节点的prior指向p
		p->next->prior = p;
	}
	else {//p的下一个为空 

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
	//判断L是否为NULL，是则结束函数
	if (L == NULL) {
		return;
	}

	DuLinkedList temp = L;

	while (temp != NULL) {

		//调用visit函数
		visit(temp->data);

		//跳到下一个节点
		temp = temp->next;
	}
}

/*
* visit遍历
*/
void visit(ElemType e) {
	cout << e << " ";
}
/*菜单*/
void menu() {
	cout << "------欢迎来到我的双向链表作业展示------" << endl;
	cout << "-------------------------------" << endl;
	cout << "----你现在有一个DuLNode类型的空指针head" << endl;
	cout << "----您可以进行以下操作:" << endl;
	cout << "----1.初始化一个链表" << endl;
	cout << "----b.遍历链表" << endl;
	cout << "----3.插入一个节点" << endl;
	cout << "----4.删除一个节点" << endl;
	cout << "----0.销毁链表" << endl;
	cout << "----a.退出系统" << endl;
	char option = cinmenu();
	switch (option) {
	case '1':cout << "1.初始化一个链表:\t"; function1(); break;
	case 'b':cout << "b.遍历链表:\t"; functionb(); break;
	case '3':cout << "3.插入一个节点:\t"; function3(); break;
	case '4':cout << "4.删除一个节点:\t"; function4(); break;
	case '0':cout << "0.销毁链表:\t"; function0(); break;
	case 'a':cout << "a.退出系统:\t"; functiona(); break;
	default:
		cout << "输入错误喵" << endl;
		cout << "---------" << endl;
		buffer();
	}
	buffer();
}

/*缓冲 使用完某个功能后 输入w清空窗口回到菜单... */
void buffer() {
	cout << "---------" << endl;
	cout << "输入w回到菜单..." << endl;
	string a; cin >> a;
	system("cls");
	menu();
}



/*在菜单栏时调用的输入函数 0-4，a,b,  */
char cinmenu() {
	cout << "请输入0,1,3,4,a和b:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~请重新输入0,1,3,4,a和b:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '4'&&p!='2' || p == 'a' || p == 'b') {
			ok = 1;
			cout << "输入成功:" << p << endl;
			cout << "-------" << endl;
			break;
		}
		cout << "输入错误喵~请重新输入0,1,3,4,a和b:" << endl;
	}
	return p;
}

/*输入函数 只能输入0到9直接的数字 其他则会报错*/
int cinnnum() {
	cout << "请输入一个0到9之间的数字:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~请重新输入0到9之间的数字:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '9') {
			ok = 1;
			cout << "输入成功:" << p << endl;
			break;
		}
		cout << "输入错误喵~请重新输入0到9之间的数字:" << endl;
	}
	return int(p - '0');
}

/*链表初始化*/
void function1(){
	cout << endl;
	cout << "--------" << endl;
	cout << "开始初始化链表ing马上就好喵~~" << endl;
	Status initListSuccess = InitList_DuL(&head);
	if (initListSuccess == SUCCESS) {
		cout << "初始化成功" << endl;
	}
}

//新建链表同时存入数据
//void function2(){}

void function3(){
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	//输入 数值
	int p = cinnnum();
	//new一个节点
	DuLNode* q = new DuLNode;
	q->data = p;
	q->next = NULL;
	q->prior = NULL;
	InsertAfterList_DuL(head, q);
	cout << "插入节点成功" << endl;
}

/*4.删除一个节点*/
void function4() {
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	int e = cinnnum();
	Status ojbk = DeleteDuLNode(&head, e);
	if (ojbk == SUCCESS) {
		cout << "删除成功喵！" << endl;
	}
	else {
		cout << "删除失败~,可能是链表内没有该数值" << endl;
	}
}


/*0.销毁链表*/
void function0() {
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	cout << "-------" << endl;
	DestroyList_DuL(&head);
	cout << "销毁成功喵！" << endl;
	cout << endl;
}

/*a.退出系统*/
void functiona() {
	cout << "谢谢使用喵~~"<<endl;
	exit(0);
}

/*b.遍历链表*/
/*两种遍历方法 一种自动遍历 一种手动遍历（可手动选择 上一个 下一个）*/
void functionb() {
	cout << endl;
	cout << "-------" << endl;
	bool isnull = false;
	if (head == NULL) {
		isnull = true;
		cout << "链表现在还是空的呢 遍历失败quq~" << endl;

	}
	if (!isnull) {
		cout << "请选择:\n1.自动遍历\t2.手动遍历\t0.返回" << endl;
		int op = inputChoiceTraverseWay();
		switch(op)
		{
		case 1:
				cout << "开始遍历链表了噢" << endl;
				TraverseList_DuL(head, visit);
				cout << endl;
				break;
		case 2:manualTraverseList_DuL(head, visit); break;
		case 0:return;
		}
	}
}


Status judgeIsInitList(DuLNode* L) {
	//先判断head是否指向NULL
	if (L == NULL) {
		cout << "sorry链表还没初始化呢，请您先进行初始化链表的操作喵~~" << endl;
		return ERROR;
	}
	else {
		return SUCCESS;
	}
}



/*选择遍历方式时 所调用的input方法*/
int inputChoiceTraverseWay() {
	//输入 0 1 2 
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~请您按要求输入喵:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '2') {
			ok = 1;
			cout << "输入成功:" << p << endl;
			break;
		}
		cout << "输入错误喵~请您按要求输入喵:" << endl;
	}
	//输入字符 1 2 0 返回 int型  1 2 0
	return int(p - '0');
}


/*手动遍历双向链表 可选上一个下一个*/
void manualTraverseList_DuL(DuLinkedList L, void (*visit)(ElemType e)) {

	//清空窗口 开始遍历
	system("cls");
	DuLinkedList p = L;

	cout << "第一个节点的值为：" << L->data << endl;
	cout << "请选择:\n1.下一个\t2.上一个\t0.返回" << endl;
	bool finished = false;
	while(!finished)
	{
		//system("cls");原本放在这里 如果放在这里 将会什么都看不见
		int op = inputChoiceTraverseWay();
		system("cls");
		switch (op) {
			case 1: 
				//当下一个为空时 遍历到尾 结束本次循环
				if (L ->next== NULL) {
					cout << "链表已经遍历到尾部了哦~\t请您选择:2.上一个\t0.返回" << endl;
					continue;
				}
				//跳到下一个 输出值
				L = L->next;
				cout << "该节点的值为:"<<endl;
				visit(L->data);
				break;
			
			case 2: 
				//当上一个为空时 遍历到头 结束本次循环
				if (L->prior == NULL) {
					cout << "链表已经遍历到头部了哦~\t请您选择:1.下一个\t0.返回" << endl;
					continue;
				
				}
				//跳到上一个 输出值
				L = L->prior;
				cout << "该节点的值为:" << endl;
				visit(L->data);
				break;
			
			case 0: 
				//退出遍历
				cout << "退出遍历~" << endl;
				return;
			
		}
		cout << "请选择:\n1.下一个\t2.上一个\t0.返回" << endl;
	}
}



Status DeleteDuLNode(DuLinkedList* L, ElemType e) {

	//已知 传过来的head 必定不为空

	//特判第一个就是e的情况 删除第一个节点
	if ((*L)->data == e) {
		DuLNode* aaa = *L;
		*L = (*L)->next;
		free(aaa);
		aaa = NULL;
		return SUCCESS;
	}

	//下面为  不是第一个的情况
	DuLNode* pprior = *L;
	DuLNode* temp = *L;

	bool found = false;
	while (temp != NULL) {


		if (temp->data == e) {
			found = true;
			break;
		}
		pprior = temp;
		//跳到下一个节点
		temp = temp->next;
	}
	if (found) {//如果找到了该节点temp  开始删除
		//记下temp的下一个节点pnext
		DuLNode* pnext = temp->next;
		//让temp的前一个节点pprior指向pnext 同时pnext指向pprior
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
