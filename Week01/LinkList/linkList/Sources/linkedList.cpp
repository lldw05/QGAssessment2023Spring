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
	//此处L为 指向LNode类型 的指针 的指针
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
	//L指向头结点
	//此处L为 指向LNode类型 的指针 的指针

	//L为空或L指向的为空时 函数结束
	if (L == NULL ) {
		return;
	}
	if (*L == NULL) {
		L = NULL;
		return;
	}

	//定义两个指针 不用L
	LinkedList p1 =*L;
	LinkedList p2 =*L;

	//p2跟上p1 p1往前 删除p2 直到p1指向NULL
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

	//特判q p 为空的情况 返回error
	if (p == NULL || q == NULL) {
		return ERROR;
	}


	//记录p的下一个节点的位置(包含p为尾结点的情况)
	LNode* temp = p->next;

	//让p的指针域指向q
	p->next = q;

	//让q的指针域指向 原 p的下一个节点
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

	//特判p是否为尾结点或p是否为NULL 是则返回error
	if (p->next == NULL || p == NULL) {
		return ERROR;
	}
	
	//pnext 为p的下一个节点
	LNode* pnext = p->next;

	//e指向一块数据域   将pnext数据 存到e指向的数据域
	*e = pnext->data;

	//让p的指针域指向pnext的下一个节点（为NULL也记下，不为NULL也记下）
	p->next = pnext->next;

	//删除pnext
	free(pnext); pnext = NULL;

	return SUCCESS;

}
/*输入一个值 删除链表中第一个与这个值相等的节点*/
Status DeleteLNode(LNode* head, ElemType e) {

	//特判第一个就是e的情况
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
		//跳到下一个节点
		temp = temp->next;
	}
	if (found) {//如果找到了该节点 开始删除
		//记下temp的下一个节点pnext
		LNode* pnext = temp->next;
		//让temp的前一个节点pprior指向pnext
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

	//判断L是否为NULL，是则结束函数
	if (L == NULL) {
		return;
	}

	Status alreadyLooped = IsLoopList(head);
	if (alreadyLooped) {
		cout << "该链表已经成环,只展示一部分数据捏" << endl;
		//定义一个bool变量来记录是否成环
		LNode* temp = L;

		LNode* p1 = L;
		LNode* p2 = L;
		int cnt = 0;
		//p1 p2 相遇多次时 说明链表成环
		while (cnt < 10) {
			cout << p1->data;
			p1 = p1->next;
			p2 = p2->next->next;
			
			//判断p1p2是否相遇 是则说明成环 
			if (p1 == p2) {
				cnt++;
				cout << " ";
			}
		}
	}
	else{
		LinkedList temp = L;

		while (temp != NULL) {

			//调用visit函数
			visit(temp->data);

			//跳到下一个节点
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

	//判断L是否为空 是则返回error
	if (L == NULL) {
		return ERROR;
	}

	//ok用于 记录 是否找到 数据域与e一致的节点 找到了则为true 没找到则为false
	bool ok = false;

	//遍历链表
	LinkedList temp = L; 
	while (temp != NULL) {

		//判断temp节点的值是否等于e
		if (temp->data == e) {
			//找到了 将ok设为true
			ok = true;
			break;
		}
		//跳到下一个节点
		temp = temp->next;
	}

	//根据是否找到 返回SUCCESS或ERROR
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
	
	//判断L是否为空 
	if (L == NULL) {
		return ERROR;
	}

	//用迭代的方法
	
	//定义三个指针 前中后  中间的为newHead 同时newHead也为反转完后的新头结点
	
	//一开始将 中间指针指向头结点 后指针设置为NULL 
	LNode* newHead = *L;
	LNode* behind =NULL;
	LNode* front = newHead->next;
	
	while (front!=NULL) {
		//中间的指针指向的节点 的指针域 指向后一个节点
		newHead->next = behind;

		//三个指针各往前走一步
		behind = newHead;
		newHead = front;
		front = front->next;
	}
	//此时的newHead为原尾结点 指针域指向NULL 我们须让其指针域指向behind
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

	//all in all 利用两个前进速度不一样的指针是否相遇来判断是否成环
	

	//判断L是否为空
	if (L == NULL||L->next==NULL) {
		return ERROR;
	}

	//定义一个bool变量来记录是否成环
	bool IsLoopList = false;
	LNode* temp = L;

	LNode* p1 = L;
	LNode* p2 = L;
	int cnt = 0;
	//p1 p2 相遇多次时 说明链表成环
	while (cnt<5) {

		p1 = p1->next;
		//当p2的下一个节点不为NULL p2才跳两下
		if (p2->next != NULL) {
			p2 = p2->next->next;
		}
		else {
			//否则说明 链表不是成环的
			IsLoopList = false;
			break;
		}
		

		// 利用p1 输出每个节点的值
		//cout << p1->data;

		//判断p1p2是否相遇 是则说明成环 
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
	////特判只有一个节点的情况			无需特判了 while循环包含了
	//if (pnext == NULL) {
	//	return *L;
	//}

	
	//p一直为第奇数个节点  pnext为第偶数个节点
	while (p != NULL) {

		//交换数据域
		pnext = p->next;
		temp = p->data;
		if (pnext != NULL)
		{
			p->data = pnext->data;
			pnext->data = temp;
		}
		else {
			//此时 链表节点为奇数个 p为尾结点 pnext指向NULL
			break;
		}

		////下面 p前进两个节点
		////先判断pnext的下一个是否空 不为空则p前进两个节点
		//if (pnext->next != NULL) {
		//	p = pnext->next;
		//}
		//else {
		//	//此时 链表为偶数个 pnext为尾结点
		//	break;
		//}
		//  上述多此一举 直接前进两个 若p指向空 则while会跳出来
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

	//判断L是否为NULL或*L是否为NULL
	if (L == NULL || *L == NULL){
		return NULL;
	}

	//判断L是否成环,是则返回空节点
	if (IsLoopList(*L) == SUCCESS) {
		return NULL;
	}

	//已知链表不成环 能找到中点
	// 
	//p1走一步 p2走两步 当p2到达链表尾时，p1也到达了中点
	LNode* p1 = *L;
	LNode* p2 = *L;
	LNode* ass = NULL;

	while (p2!=NULL) {
		//链表长度为偶数 2n+1  返回第n+1个
		if (p2->next != NULL) {
			p2 = p2->next->next;
		}
		else {
			//说明此时p2已经到达链表尾  链表长度为奇数
			ass = p1;
			break;
		}
		p1 = p1->next;

		//链表长度为偶数 2n  返回第n+1个
		//此时p2已经过了尾结点 指向了NULL
		if (p2 == NULL) {
			ass = p1;
			break;
		}
	}
	return ass;
}








/*
	展示反转链表函数
*/
void showReleaseList() {

	//数据准备
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


	//展示未反转前的链表
	cout << "未反转前的链表:";
	LNode* temp2 = *L;
	while (temp2 != NULL) {
		cout << temp2->data <<" ";
		temp2 = temp2->next;
	}
	cout << endl;


	//反转链表并展示发展后的链表
	ReverseList(L);
	LNode* temp = *L;
	cout << "反转后的链表:";
	while (temp != NULL) {
		cout << temp->data << " ";
		temp = temp->next;
	}

}


/*
生成包含6个个位数数字的链表 返回指向头结点的指针
*/
LinkedList createLinkedList() {
	cout << "接下来开始创建链表：" << endl<<"-------"<<endl;
	cout << "请输入6个数，超过6个数则取前6个，数值范围在0~9之间" << endl;
	char a[10];
	char num[6];
	bool ok = 0;
	int p = 0;
	while (p<6) {
		cin >> a[p++];
		if (!(a[p - 1] >= '0' && a[p - 1] <= '9'))
		{
			p--;
			cout << "输入错误喵~ 请输入" << 6 - p << "个0~9之内的数字:";
		}
	}
	cout << "您刚刚输入的数为:";
	for (int i = 0; i < 6; i++) {
		num[i] = a[i];
		cout<<num[i]<<" ";
	}

	//手动new头结点
	LNode* head=new LNode;
	head->data = int(num[0]-'0');
	head->next = NULL;

	LNode* temp =head;

	
	//new5个节点 存入num数组中下标1到5的字符
	for (int i = 1; i < 6; i++) {
		LNode* p = new LNode;
		//char数字转int
		p->data = int(num[i] - '0');
		InsertList(temp, p);
		temp = temp->next;
	}
	temp = NULL;
	return head;
}



/*vist函数*/
/*vist函数*/
/*vist函数*/
void visit(ElemType e) {
	cout << e <<" ";
}

void menu() {
	cout << "------欢迎来到我的单链表作业展示------" << endl;
	cout << "-------------------------------" << endl;
	cout << "----你现在有一个LNode类型的空指针head" << endl;
	cout << "----您可以进行以下操作:" << endl;
	cout << "----1.初始化一个链表" << endl;
	cout << "----2.创建一个新链表(可以无需初始化哦~)" << endl;
	cout << "----b.遍历链表" << endl;
	cout << "----3.插入一个节点" << endl;
	cout << "----4.删除一个节点" << endl;
	cout << "----5.使链表成环" << endl;
	cout << "----6.判断链表是否成环" << endl;
	cout << "----7.单链表奇偶调换" << endl;
	cout << "----8.找到链表的中点" << endl;
	cout << "----9.反转链表" << endl;
	cout << "----0.销毁链表" << endl;
	cout << "----a.退出系统" << endl;
	char option = cinmenu();
	switch (option) {
	case '1':cout << "1.初始化一个链表:\t"; function1(); break;
	case '2':cout << "2.创建一个新链表\t"; function2(); break;
	case 'b':cout << "b.遍历链表:\t"; functionb(); break;
	case '3':cout << "3.插入一个节点:\t"; function3(); break;
	case '4':cout << "4.删除一个节点:\t"; function4(); break;
	case '5':cout << "5.使链表成环:\t"; function5(); break;
	case '6':cout << "6.判断链表是否成环:\t"; function6(); break;
	case '7':cout << "7.单链表奇偶调换:\t"; function7(); break;
	case '8':cout << "8.找到链表的中点:\t"; function8(); break;
	case '9':cout << "9.反转链表:\t"; function9(); break;
	case '0':cout << "0.销毁链表:\t"; function0(); break;
	case 'a':cout << "a.退出系统:\t"; functiona(); break;
	default:
		cout << "输入错误喵" << endl;
		cout << "---------" << endl;
		buffer();
	}
	buffer();
}
/*1.初始化一个链表
*/
void function1() {
	cout << endl;
	cout << "--------" << endl;
	cout << "开始初始化链表ing马上就好喵~~" << endl;
	Status initListSuccess = InitList(&head);
	if (initListSuccess == SUCCESS) {
		cout << "初始化成功" << endl;
	}
}

/*2.创建一个新链表*/
void function2() {
	head = createLinkedList();
	cout << "创建成功喵！！" << endl;
}

/*b.遍历链表*/
void functionb() {
	cout << "-------" << endl;
	bool isnull = false;
	if (head == NULL) {
		isnull = true;
		cout << "链表现在还是空的呢 遍历失败quq~" << endl;
		
	}
	if(!isnull) {
		cout << "开始遍历链表了噢" << endl;
		TraverseList(head, visit);
		cout << endl;
	}
}
/*3.插入一个节点*/
void function3() {
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	int p = cinnnum();
	LNode* q = new LNode;
	q->data = p;
	InsertList(head, q);
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
	Status ojbk = DeleteLNode(head, e);
	if (ojbk == SUCCESS) {
		cout << "删除成功喵！" << endl;
	}
	else {
		cout << "删除失败~,可能是链表内没有该数值" << endl;
	}
}

/*5.使链表成环*/
void function5(){
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	Status ojbk = makelinkedLishLooped(head);
	if (ojbk == SUCCESS) {
		cout << "成环成功！！" << endl;
	}
	else {
		cout << "成环失败qwq，可能是已经成环了或链表只有一个节点"<<endl;
	}
}

/*6.判断链表是否成环*/
void function6(){
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	Status alreadyLooped = IsLoopList(head);
	cout << alreadyLooped ? "链表成环" : "链表不成环";

}

/*7.单链表奇偶调换*/
void function7(){
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	//判断链表是否成环
	Status alreadyLooped = IsLoopList(head);
	if (alreadyLooped) {
		cout << "调换失败喵~，可能是因为链表成环了" << endl;
	}
	ReverseEvenList(&head);
	cout << "调换成功喵~";

}

/*8.找到链表的中点*/
void function8(){
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}

	LNode* mid = FindMidNode(&head);
	if (mid != NULL) {
		cout << "成功找到链表的中点:"<<mid->data<< endl;
	}
	else {
		cout <<"出错误啦,可能是因为链表成环了捏~"<< mid << endl;
	}
}


/*9.反转链表*/
void function9(){
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	//判断链表是否成环
	Status alreadyLooped = IsLoopList(head);
	if (alreadyLooped) {
		cout << "反转失败喵~，可能是因为链表成环了" << endl;
	}

	ReverseList(&head);
	cout << "反转成功喵~" << endl;

}

/*0.销毁链表*/
void function0(){
	//先判断是否初始化
	Status alreadyInit = judgeIsInitList(head);
	if (!alreadyInit) {
		return;
	}
	DestroyList(&head);
}


/*a.退出系统*/
void functiona(){
	cout << "谢谢使用喵~~";
	exit(0);

}

void buffer() {
	cout << "---------" << endl;
	cout << "输入w回到菜单..." << endl;
	char a; cin >> a;
	system("cls");
	menu();
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
/*在菜单栏时调用的输入函数 0-9，a,b,  */
char cinmenu() {
	cout << "请输入0-9,a和b:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~请重新输入0-9,a和b:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '9'||p=='a'||p=='b') {
			ok = 1;
			cout << "输入成功:" << p << endl;
			cout << "-------"<<endl;
			break;
		}
		cout << "输入错误喵~请重新输入0-9,a和b:" << endl;
	}
	return p;
}

/*使链表成环函数*///使链表尾连接到链表中点
Status makelinkedLishLooped(LinkedList head) {

	//如果已经成环 返回ERROR
	if (IsLoopList(head) == SUCCESS) {
		return ERROR;
	}
	//判断链表是否只有一个节点
	//找到中点
	LNode* mid = FindMidNode(&head);

	LinkedList temp = head;
	bool ojbk = false;
	while (1) {
		if (temp->next==NULL) {
			temp->next = mid;
			ojbk = true;
			break;
		}
		//跳到下一个节点
		temp = temp->next;
	}
	return ojbk ? SUCCESS : ERROR;
}


Status judgeIsInitList(LNode* L) {
	//先判断head是否指向NULL
	if (head == NULL) {
		cout << "sorry链表还没初始化呢，请您先进行初始化链表的操作喵~~" << endl;
		return ERROR;
	}
	else {
		return SUCCESS;
	}
}