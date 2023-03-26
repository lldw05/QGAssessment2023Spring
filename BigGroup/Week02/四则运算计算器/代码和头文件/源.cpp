#include"��ͷ.h"
string infix;
string postfix;
char c[1000];

int main() {
	int cnt = 0;
	while (1) {

		if (cnt != 0) {
			//��ɾ�� ��ÿ��ѭ�����ȡһ���س�����
			getchar();
		}

		//����
		infix = cininfix();



		//��ת��
		postfix = infixToPostfix(infix);
		
		//�����
		double ans = calculatePostfix(postfix);
		if(ans!=-1000) {
			cout << "��׺���ʽ:" << postfix << endl;
			cout << "������Ϊ:" << ans << endl;
		}
		else {
			cout << "����ʽ�������" << endl;
		}
		/*cin >> infix;
		for (int i = 0; i < infix.size(); i++) {
			cout << infix[i] << " ";
		}*/
		buffer();
		cnt++;
	}
	
	
}
/*������׺���ʽ*/
string cininfix() {
	char c;
	string s ;
	string ans;
	//��������������ʽ
	bool ok = false;
	while (!ok) {

		cout << "������0��9֮�ڵ�������������ʽ:";

		getline(cin,s);
		if (valid(s)) {
			ok = 1;
		}
		else {
			cout << "���������~" << endl;
		}

		
	}
	////ȥ�ո�
	////cout << "����Ϊ����ʽȥ�ո�" << endl;
	for (int i = 0; i < s.size(); i++) {
		if (s[i] != ' ') {
			ans += s[i];
		}
	}
	////cout << "ȥ���ɹ���" << endl;
	return ans;
}
/*�����ж����������ʽ�Ƿ���Ч*/
bool valid(string s) {
	//cout << "�����ж��Ƿ���Ч..." << endl;
	int  pnumbers = 0;
	//����һ�� �鿴�Ƿ��� �����Լ��Ӽ��˳����ſո� ֮����ַ�
	for (int i = 0; i < s.size(); i++) {
		if (!('0' <= s[i] && s[i] <= '9' || s[i] == '+'
			|| s[i] == '-' || s[i] == '*' || s[i] == '/' 
			|| s[i] == '(' || s[i] == ')'||s[i]==' ')) {
			return false;
		}
		if (s[i] == '(') pnumbers++;
		if (s[i] == ')') pnumbers--;
	}

	//������ �Ƿ�ɶ�
	if (pnumbers != 0) {
		return false;
	}

	//�Ƿ������˷Ǹ�λ��
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

	//�ش��� ���� 22 12 58 ����ʮλ�����������ʱ
	if (s.size() == 2 && '0' <= s[0] && s[0] <= '9' && '0' <= s[1] && s[1] <= '9') {
		return false;
	}
	

	//�ش��� ���� -56 +11 ���������ǰ ���������ں�����
	if (s.size() == 3 && !('0' <= s[0] && s[0] <= '9')) {
		return false;
	}


	return true;
	
}

/*��׺ת��׺*/
string infixToPostfix(string s) {
	//cout << "����׺���ʽת���ɺ�׺���ʽ��......" << endl;
	stack<char> stack;
	string ans = "";
	for (int i = 0; i < s.size(); i++) {
		//cout << s[i] << " ";
		if ('0' <= s[i] && s[i] <= '9') {
			//����
			ans += s[i];
		}
		else {
			if (s[i] == '(') {
				//������
				stack.push(s[i]);
			}
			else if (s[i] == ')') {
				//������
				while (stack.top()!= '(' ) {
					ans += stack.top();
					stack.pop();
				}
				//�����ų�ջ ������ans
				stack.pop();
			}
			else {
				
				//�����

				bool ok = 0;

				while(!ok) {
					if (stack.empty()|| stack.top() == '('||grade(s[i]) > grade(stack.top())) {
						//���ȼ�����ջ������� ||ջ��Ϊ������  ��ջ��
						stack.push(s[i]);
						ok = 1;
					}
					else {
						//С�ڵ���ջ��
						/*ջ�����������ջ��
							�����Ƚ��µ�ջ�������(һֱ�ݹ���ȥ) ֱ����ǰ���������ջ�������Ϊֹ��
							��󽫵�ǰ�������ջ��*/
						ans += stack.top();
						stack.pop();
					}
				}
			}
		}
	}
	//������ ��ջ��Ĳ���string
	while (!stack.empty()) {
		ans += stack.top();
		stack.pop();
	}
	return ans;
}

/*��������ȼ�*/
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
	cout << "�����������......" << endl;
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
			//����
			stack.push(1.0*(s[i] - '0'));
		}
		else {
			//�����
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