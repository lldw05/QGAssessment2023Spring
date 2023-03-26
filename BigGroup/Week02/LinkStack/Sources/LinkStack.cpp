#include "../Headers/LinkStack.h"

// 链栈

// 初始化栈
Status initLStack(LinkStack *s)
{
	// 已经初始化了
	if (s->count != -1)
	{
		return ERROR;
	}

	// 还没进行初始化 s->count=-1 s->top=NULL
	// 初始化完 栈顶为空 cnt为0
	s->count = 0;
	return SUCCESS;
}

// 判断栈是否为空
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

// 得到栈顶元素
Status getTopLStack(LinkStack *s, ElemType *e)
{
	// 判断栈是否为空
	if (isEmptyLStack(s))
	{
		return ERROR;
	}
	*e = s->top->data;
	return SUCCESS;
}

// 清空栈
Status clearLStack(LinkStack *s)
{
	// 判断是否已经为空
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

	// 顶为空 且cnt为0时 返回成功 不然就是cnt出错了
	if (s->count == 0)
	{
		return SUCCESS;
	}
	return ERROR;
}

// 销毁栈
Status destroyLStack(LinkStack *s)
{

	// 清空栈   clear完后 s->count的值为0
	clearLStack(s);
	// cout << s->count << endl; 输出0

	// 原来的做法↓ 想把让s指向空 但是做不到
	// s = NULL;

	// 使栈变成 未初始化 即设置count为-1
	s->count = -1;

	return SUCCESS;
}

// 检测栈长度
Status LStackLength(LinkStack *s, int *length)
{
	*length = s->count;
	return SUCCESS;
}

// 入栈
Status pushLStack(LinkStack *s, ElemType data)
{
	// 已知 传进来的栈已经初始化
	StackNode *sn = new StackNode;
	sn->data = data;
	sn->next = s->top;
	s->top = sn;
	s->count++;
	return SUCCESS;
}

// 出栈
Status popLStack(LinkStack *s, ElemType *data)
{
	// 已知 传进来的栈已经初始化

	// 判断栈是否为空 为空则返回ERROR
	if (isEmptyLStack(s))
	{
		return ERROR;
	}

	// 栈不为空时
	*data = s->top->data;

	// top移动
	StackNode *temp = s->top;
	s->top = temp->next;
	s->count--;

	// 释放空间
	free(temp);
	temp = NULL;

	return SUCCESS;
}

// 判断是否初始化
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
	// 若没初始化 直接退出
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