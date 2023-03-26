#include"标头.h"
string infix;
string postfix;
char c[1000];

int main() {
	int cnt = 0;
	while (1) {

		if (cnt != 0) {
			//如删除 则每次循环会读取一个回车符号
			getchar();
		}

		//输入
		infix = cininfix();



		//中转后
		postfix = infixToPostfix(infix);
		
		//后计算
		double ans = calculatePostfix(postfix);
		if(ans!=-1000) {
			cout << "后缀表达式:" << postfix << endl;
			cout << "计算结果为:" << ans << endl;
		}
		else {
			cout << "计算式输入错误" << endl;
		}
		/*cin >> infix;
		for (int i = 0; i < infix.size(); i++) {
			cout << infix[i] << " ";
		}*/
		buffer();
		cnt++;
	}
	
	
}
/*输入中缀表达式*/
string cininfix() {
	char c;
	string s ;
	string ans;
	//输入四则运算表达式
	bool ok = false;
	while (!ok) {

		cout << "请输入0到9之内的整数四则运算式:";

		getline(cin,s);
		if (valid(s)) {
			ok = 1;
		}
		else {
			cout << "输入错误喵~" << endl;
		}

		
	}
	////去空格
	////cout << "正在为运算式去空格" << endl;
	for (int i = 0; i < s.size(); i++) {
		if (s[i] != ' ') {
			ans += s[i];
		}
	}
	////cout << "去除成功！" << endl;
	return ans;
}
/*用于判断输入的运算式是否有效*/
bool valid(string s) {
	//cout << "正在判断是否有效..." << endl;
	int  pnumbers = 0;
	//遍历一遍 查看是否有 数字以及加减乘除括号空格 之外的字符
	for (int i = 0; i < s.size(); i++) {
		if (!('0' <= s[i] && s[i] <= '9' || s[i] == '+'
			|| s[i] == '-' || s[i] == '*' || s[i] == '/' 
			|| s[i] == '(' || s[i] == ')'||s[i]==' ')) {
			return false;
		}
		if (s[i] == '(') pnumbers++;
		if (s[i] == ')') pnumbers--;
	}

	//括号数 是否成对
	if (pnumbers != 0) {
		return false;
	}

	//是否输入了非个位数
	int temp = 0;
	for (int k = 0; k < s.size(); k++) {

		if ('0' <= s[k] && s[k] <= '9'&&temp==1) {
			return false;
		}
		if ('0' <= s[k] && s[k] <= '9' ) {
			temp=1;
		}
		else {
			temp = 0;
		}

	}

	//特处理 输入 22 12 58 该类十位数但无运算符时
	if (s.size() == 2 && '0' <= s[0] && s[0] <= '9' && '0' <= s[1] && s[1] <= '9') {
		return false;
	}
	

	//特处理 输入 -56 +11 该类符号在前 两个数字在后的情况
	if (s.size() == 3 && !('0' <= s[0] && s[0] <= '9')) {
		return false;
	}


	return true;
	
}

/*中缀转后缀*/
string infixToPostfix(string s) {
	//cout << "将中缀表达式转换成后缀表达式中......" << endl;
	stack<char> stack;
	string ans = "";
	for (int i = 0; i < s.size(); i++) {
		//cout << s[i] << " ";
		if ('0' <= s[i] && s[i] <= '9') {
			//数字
			ans += s[i];
		}
		else {
			if (s[i] == '(') {
				//左括号
				stack.push(s[i]);
			}
			else if (s[i] == ')') {
				//右括号
				while (stack.top()!= '(' ) {
					ans += stack.top();
					stack.pop();
				}
				//左括号出栈 但不入ans
				stack.pop();
			}
			else {
				
				//运算符

				bool ok = 0;

				while(!ok) {
					if (stack.empty()|| stack.top() == '('||grade(s[i]) > grade(stack.top())) {
						//优先级大于栈顶运算符 ||栈顶为左括号  入栈；
						stack.push(s[i]);
						ok = 1;
					}
					else {
						//小于等于栈顶
						/*栈顶的运算符出栈；
							继续比较新的栈顶运算符(一直递归下去) 直到当前运算符大于栈顶运算符为止；
							最后将当前运算符入栈；*/
						ans += stack.top();
						stack.pop();
					}
				}
			}
		}
	}
	//遍历完 将栈里的并入string
	while (!stack.empty()) {
		ans += stack.top();
		stack.pop();
	}
	return ans;
}

/*运算符优先级*/
int grade(char c) {
	int grade = 0;
	if (c == '+' || c == '-') {
		grade = 1;
	}
	else if(c=='*'||c=='/') {
		grade = 2;
	}
	/*else if (c == '(' || c == ')') {
		grade = 0;
	}*/
	return grade;
}

void buffer() {
	cout << "------------" << endl;
	cout << "输入任意继续......" << endl;
	string s; cin >> s;
	system("cls");
}

double calculation(double a, double b, char tmd) {
	if (b == 0 && tmd == '/') {
		return -1000;
	}
	switch (tmd) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
	}
}

double calculatePostfix(string s) {
	stack<double> stack;
	for (int i = 0; i < s.size(); i++) {
		if ('0' <= s[i] && s[i] <= '9') {
			//数字
			stack.push(1.0*(s[i] - '0'));
		}
		else {
			//运算符
			double a1, a2;
			if (!stack.empty()) {
				a2 = stack.top();
				stack.pop();
			}
			else {
				return -1000;
			}
			if(!stack.empty()) {
				a1 = stack.top();
				stack.pop();
			}
			else {
				return -1000;
			}

			double ans = calculation(a1, a2, s[i]);
			if (ans == -1000) {
				return -1000;
			}
			stack.push(ans);
		}
	}

	return stack.top();
}