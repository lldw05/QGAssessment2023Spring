#include "../Headers/LinkStack.h"

// ��ջ

// ��ʼ��ջ
Status initLStack(LinkStack *s)
{
	// �Ѿ���ʼ����
	if (s->count != -1)
	{
		return ERROR;
	}

	// ��û���г�ʼ�� s->count=-1 s->top=NULL
	// ��ʼ���� ջ��Ϊ�� cntΪ0
	s->count = 0;
	return SUCCESS;
}

// �ж�ջ�Ƿ�Ϊ��
Status isEmptyLStack(LinkStack *s)
{
	if (s->top == NULL)
	{
		return SUCCESS;
	}
	else
	{
		return ERROR;
	}
}

// �õ�ջ��Ԫ��
Status getTopLStack(LinkStack *s, ElemType *e)
{
	// �ж�ջ�Ƿ�Ϊ��
	if (isEmptyLStack(s))
	{
		return ERROR;
	}
	*e = s->top->data;
	return SUCCESS;
}

// ���ջ
Status clearLStack(LinkStack *s)
{
	// �ж��Ƿ��Ѿ�Ϊ��
	if (isEmptyLStack(s))
	{
		return ERROR;
	}

	StackNode *p = s->top;
	while (s->top != NULL)
	{
		s->top = (s->top->next);
		s->count--;
		free(p);
		p = s->top;
	}

	// ��Ϊ�� ��cntΪ0ʱ ���سɹ� ��Ȼ����cnt������
	if (s->count == 0)
	{
		return SUCCESS;
	}
	return ERROR;
}

// ����ջ
Status destroyLStack(LinkStack *s)
{

	// ���ջ   clear��� s->count��ֵΪ0
	clearLStack(s);
	// cout << s->count << endl; ���0

	// ԭ���������� �����sָ��� ����������
	// s = NULL;

	// ʹջ��� δ��ʼ�� ������countΪ-1
	s->count = -1;

	return SUCCESS;
}

// ���ջ����
Status LStackLength(LinkStack *s, int *length)
{
	*length = s->count;
	return SUCCESS;
}

// ��ջ
Status pushLStack(LinkStack *s, ElemType data)
{
	// ��֪ ��������ջ�Ѿ���ʼ��
	StackNode *sn = new StackNode;
	sn->data = data;
	sn->next = s->top;
	s->top = sn;
	s->count++;
	return SUCCESS;
}

// ��ջ
Status popLStack(LinkStack *s, ElemType *data)
{
	// ��֪ ��������ջ�Ѿ���ʼ��

	// �ж�ջ�Ƿ�Ϊ�� Ϊ���򷵻�ERROR
	if (isEmptyLStack(s))
	{
		return ERROR;
	}

	// ջ��Ϊ��ʱ
	*data = s->top->data;

	// top�ƶ�
	StackNode *temp = s->top;
	s->top = temp->next;
	s->count--;

	// �ͷſռ�
	free(temp);
	temp = NULL;

	return SUCCESS;
}

// �ж��Ƿ��ʼ��
bool alreadyInited(LinkStack *s)
{
	if (s->count != -1)
	{
		return true;
	}
	else
	{
		return false;
	}
}

void showLStack(LinkStack *s)
{
	// ��û��ʼ�� ֱ���˳�
	if (!alreadyInited(s))
	{
		return;
	}

	int cnt = s->count;
	StackNode *p = s->top;
	cout << endl;
	// cout << "   top->|\t   " << "\t|" << endl;
	for (int i = 1; i <= cnt; i++)
	{
		if (i == 1)
		{
			cout << "   top->|\t" << +p->data << "\t|" << endl;
		}
		else
		{
			cout << "\t|\t" << +p->data << "\t|" << endl;
		}
		p = p->next;
	}
	cout << "\t_________________" << endl;
}