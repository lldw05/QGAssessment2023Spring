#ifndef STACK_H_INCLUDED
#define STACK_H_INCLUDED
#include<iostream>
using namespace std;
typedef enum Status 
{
    ERROR = 0, 
	SUCCESS = 1
} Status;

typedef int ElemType;

typedef  struct StackNode
{
	ElemType data;
	struct StackNode *next;
}StackNode, *LinkStackPtr;

typedef  struct  LinkStack
{
	LinkStackPtr top;
	int	count;
}LinkStack;



//链栈
Status initLStack(LinkStack *s);//初始化栈
Status isEmptyLStack(LinkStack *s);//判断栈是否为空
Status getTopLStack(LinkStack *s,ElemType *e);//得到栈顶元素
Status clearLStack(LinkStack *s);//清空栈
Status destroyLStack(LinkStack *s);//销毁栈
Status LStackLength(LinkStack *s,int *length);//检测栈长度
Status pushLStack(LinkStack *s,ElemType data);//入栈
Status popLStack(LinkStack *s,ElemType *data);//出栈
bool alreadyInited(LinkStack* s);//判断是否初始化
void buffer();
void menu();
int cinnum();
char cinmenu();
void showLStack(LinkStack* s);



void InitLStackController();
void judgeIsEmptyController();
void pushLStackController();
void popLStackController();
void getTopLStackController();
void LStackLengthController();
void clearLStackController();
void destroyLStackController();
void xiabo();
void test(int *p);
#endif 
