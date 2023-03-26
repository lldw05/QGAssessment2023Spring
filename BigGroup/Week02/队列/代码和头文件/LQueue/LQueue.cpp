#include "../head/LQueue.h" 

/**
 *  @name        : void InitLQueue(LQueue *Q)
 *    @description : 初始化队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void InitLQueue(LQueue *q){
	q->front = NULL;
	q->rear = NULL;
	q->length = 0;
}

/**
 *  @name        : void DestoryLQueue(LQueue *Q)
 *    @description : 销毁队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void DestoryLQueue(LQueue *Q){

	if (!alreadyInited(Q)) {
		cout << "销毁失败,队列还没有进行初始化呢" << endl;
		return;
	}

	if (IsEmptyLQueue(Q))
	{
		cout << "销毁成功！" << endl;
		return;
	}

	ClearLQueue(Q);
	Q = NULL;//没用这句
	cout << "销毁成功！" << endl;
}

/**
 *  @name        : Status IsEmptyLQueue(const LQueue *Q)
 *    @description : 检查队列是否为空
 *    @param         Q 队列指针Q
 *    @return         : 空-TRUE; 未空-FALSE
 *  @notice      : None
 */
Status IsEmptyLQueue(const LQueue *Q){
	if (Q->front == NULL) {
		return TRUE;
	}
	else {
		return FALSE;
	}
}

/**
 *  @name        : Status GetHeadLQueue(LQueue *Q, void *e)
 *    @description : 查看队头元素
 *    @param         Q e 队列指针Q,返回数据指针e
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : 队列是否空
 */
Status GetHeadLQueue(LQueue *Q, void *e){
	if (IsEmptyLQueue(Q)) {
		return FALSE;
	}
	cout<< "查看成功:" << Q->front->data<<endl;
	return TRUE;
}

/**
 *  @name        : int LengthLQueue(LQueue *Q)
 *    @description : 确定队列长度
 *    @param         Q 队列指针Q
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : None
 */
int LengthLQueue(LQueue *Q){
	return Q->length;
}

/**
 *  @name        : Status EnLQueue(LQueue *Q, void *data)
 *    @description : 入队操作
 *    @param         Q 队列指针Q,入队数据指针data
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : 队列是否为空
 */
Status EnLQueue(LQueue *Q, void *data){
	Node* n = new Node;
	if (IsEmptyLQueue(Q)) {
		Q->front = n;
	}
	else{
		//非空
		
		//入队 原队尾指向新队尾
		Q->rear->next = n;

	}
	n->next = NULL;
	Q->rear = n;
	n->data = data;
	Q->length++;
	return TRUE;
}

/**
 *  @name        : Status DeLQueue(LQueue *Q)
 *    @description : 出队操作
 *    @param         Q 队列指针Q
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : None
 */
Status DeLQueue(LQueue *Q, void* data){
	if (IsEmptyLQueue(Q)) {
		return FALSE;
	}
	//队列非空
	Node* n = Q->front;
	cout << "出列成功" << Q->front->data << endl;
	Q->front=Q->front->next;
	Q->length--;
	free(n);
	n = NULL;
	return TRUE;
}

/**
 *  @name        : void ClearLQueue(AQueue *Q)
 *    @description : 清空队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void ClearLQueue(LQueue *Q){
	if (IsEmptyLQueue(Q)) {
		cout << "清空失败,可能是因为队列原本就为空~" << endl;
		return;
	}

	cout << "正在清空队列~" << endl;
	int leng = Q->length;
	for (int i = 1; i <= leng;i++) {
		void* d = NULL;
		DeLQueue(Q, d);
	}
	
	cout << "清空成功！" << endl;
}

/**
 *  @name        : Status TraverseLQueue(const LQueue *Q, void (*foo)(void *q))
 *    @description : 遍历函数操作
 *    @param         Q 队列指针Q，操作函数指针foo
 *    @return         : None
 *  @notice      : None
 */
Status TraverseLQueue(const LQueue *Q, void (*foo)(void *q)){
	//判断是否初始化
	if (Q == NULL) {
		return FALSE;
	}
	if (IsEmptyLQueue(Q)) {
		return FALSE;
	}

	//队列非空
	Node* n = Q->front;
	while (n) {
		foo(n->data);
		n = n->next;
	}
	return TRUE;
}

/**
 *  @name        : void LPrint(void *q)
 *    @description : 操作函数
 *    @param         q 指针q
 
 *  @notice      : None
 */
void LPrint(void *q){

	if (typeid(int) == typeid(q)) {
		cout << int(q) << " " << endl;
	}
	if (typeid(char) == typeid(q)) {
		cout << char(q) << " " << endl;
	}
	cout << (q) << " " << endl;
}

bool alreadyInited(LQueue* Q) {
	if (Q == NULL) {
		return false;
	}
	else {
		return true;
	}
}

void showQueue(LQueue* Q) {

	if (!alreadyInited(Q)) {
		return;
	}

	if (IsEmptyLQueue(Q)) {
		return;
	}

	//队列非空
	Node* n = Q->front;
	int cnt = Q->length;
	for (int i = 1; i <= cnt; i++) {
		if (i == 1) {
			cout << " front->|\t" << n->data << "\t|" << endl;
		}
		else if (i == cnt) {
			cout << "  rear->|\t" << n->data << "\t|" << endl;
		}
		else {
			cout << "\t|\t" << n->data << "\t|" << endl;
		}
		n = n->next;
	}

}
/**************************************************************
 *    End-Multi-Include-Prevent Section
 **************************************************************/
// LQUEUE_H_INCLUDED


