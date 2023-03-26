#include "../head/LQueue.h" 

/**
 *  @name        : void InitLQueue(LQueue *Q)
 *    @description : ��ʼ������
 *    @param         Q ����ָ��Q
 *  @notice      : None
 */
void InitLQueue(LQueue *q){
	q->front = NULL;
	q->rear = NULL;
	q->length = 0;
}

/**
 *  @name        : void DestoryLQueue(LQueue *Q)
 *    @description : ���ٶ���
 *    @param         Q ����ָ��Q
 *  @notice      : None
 */
void DestoryLQueue(LQueue *Q){

	if (!alreadyInited(Q)) {
		cout << "����ʧ��,���л�û�н��г�ʼ����" << endl;
		return;
	}

	if (IsEmptyLQueue(Q))
	{
		cout << "���ٳɹ���" << endl;
		return;
	}

	ClearLQueue(Q);
	Q = NULL;//û�����
	cout << "���ٳɹ���" << endl;
}

/**
 *  @name        : Status IsEmptyLQueue(const LQueue *Q)
 *    @description : �������Ƿ�Ϊ��
 *    @param         Q ����ָ��Q
 *    @return         : ��-TRUE; δ��-FALSE
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
 *    @description : �鿴��ͷԪ��
 *    @param         Q e ����ָ��Q,��������ָ��e
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : �����Ƿ��
 */
Status GetHeadLQueue(LQueue *Q, void *e){
	if (IsEmptyLQueue(Q)) {
		return FALSE;
	}
	cout<< "�鿴�ɹ�:" << Q->front->data<<endl;
	return TRUE;
}

/**
 *  @name        : int LengthLQueue(LQueue *Q)
 *    @description : ȷ�����г���
 *    @param         Q ����ָ��Q
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : None
 */
int LengthLQueue(LQueue *Q){
	return Q->length;
}

/**
 *  @name        : Status EnLQueue(LQueue *Q, void *data)
 *    @description : ��Ӳ���
 *    @param         Q ����ָ��Q,�������ָ��data
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : �����Ƿ�Ϊ��
 */
Status EnLQueue(LQueue *Q, void *data){
	Node* n = new Node;
	if (IsEmptyLQueue(Q)) {
		Q->front = n;
	}
	else{
		//�ǿ�
		
		//��� ԭ��βָ���¶�β
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
 *    @description : ���Ӳ���
 *    @param         Q ����ָ��Q
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : None
 */
Status DeLQueue(LQueue *Q, void* data){
	if (IsEmptyLQueue(Q)) {
		return FALSE;
	}
	//���зǿ�
	Node* n = Q->front;
	cout << "���гɹ�" << Q->front->data << endl;
	Q->front=Q->front->next;
	Q->length--;
	free(n);
	n = NULL;
	return TRUE;
}

/**
 *  @name        : void ClearLQueue(AQueue *Q)
 *    @description : ��ն���
 *    @param         Q ����ָ��Q
 *  @notice      : None
 */
void ClearLQueue(LQueue *Q){
	if (IsEmptyLQueue(Q)) {
		cout << "���ʧ��,��������Ϊ����ԭ����Ϊ��~" << endl;
		return;
	}

	cout << "������ն���~" << endl;
	int leng = Q->length;
	for (int i = 1; i <= leng;i++) {
		void* d = NULL;
		DeLQueue(Q, d);
	}
	
	cout << "��ճɹ���" << endl;
}

/**
 *  @name        : Status TraverseLQueue(const LQueue *Q, void (*foo)(void *q))
 *    @description : ������������
 *    @param         Q ����ָ��Q����������ָ��foo
 *    @return         : None
 *  @notice      : None
 */
Status TraverseLQueue(const LQueue *Q, void (*foo)(void *q)){
	//�ж��Ƿ��ʼ��
	if (Q == NULL) {
		return FALSE;
	}
	if (IsEmptyLQueue(Q)) {
		return FALSE;
	}

	//���зǿ�
	Node* n = Q->front;
	while (n) {
		foo(n->data);
		n = n->next;
	}
	return TRUE;
}

/**
 *  @name        : void LPrint(void *q)
 *    @description : ��������
 *    @param         q ָ��q
 
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

	//���зǿ�
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


