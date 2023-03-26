#include"../head/LQueue.h">

char type;
LQueue* Q = NULL;
int main(){
	
	getType(type);
	while (1) {
		menu();
		//TraverseLQueue(Q,LPrint); 什么垃圾玩意?
		showQueue(Q);
		char option;
		bool ok = false;

		//想选2到8之前必须先选1
		while (!ok) {
			//输入0到8
			option = cinmenu();
			bool isInited = alreadyInited(Q);
			if (option != '0' && option != '1' && !isInited) {
				//选2到8 且 未进行初始化时
				/*cout << s<<endl;*/
				cout << "队列还没有进行初始化哟~ 请您先进行初始化" << endl;
			}
			else {
				ok = 1;
			}
		}
		//0或1   或已初始化选2到8
		void* data = NULL;
		Status okk = FALSE;
		int leng = 0;
		switch (option) {

		case '1':
			if (alreadyInited(Q)) {
				cout << "队列已经初始化过了~" << endl;
				break;
			}
			cout << "1.初始化队列:\t" << endl;
			Q = new LQueue;
			InitLQueue(Q);
			cout << "初始化完成！" << endl;
			break;

		case '2':
			cout << "2.判断队列是否为空\t" << endl;
			okk = IsEmptyLQueue(Q);
			if (okk) {
				cout << "队列为空！" << endl;
			}
			else {
				cout << "队列不为空！" << endl;
			}
			break;

		case '3':
			cout << "3.入列:\t" << endl;
			//心情不好 不想写了
			cin >> data;
			EnLQueue(Q,data);
			break;


		case '4':
			cout << "4.出列:\t" << endl;
			okk = DeLQueue(Q,data);
			if (okk) {
				//cout << "出列成功:"<<data << endl;
			}
			else {
				cout << "出列失败，可能是因为队列为空" << endl;
			}
			break;


		case '5':
			cout << "5.查看队头元素:\t" << endl; 
			okk = GetHeadLQueue(Q,data);
			if (okk) {
				//cout << "查看成功:" << data << endl;
			}
			else {
				cout << "查看失败，可能是因为队列为空" << endl;
			}
			break;


		case '6':
			cout << "6.检测队列长度:\t" << endl; 
			leng = LengthLQueue(Q);
			cout << "获取成功，队列长度为：" << leng << endl;
			break;


		case '7':
			cout << "7.清空队列:\t" << endl; 
			ClearLQueue(Q);
			break;


		case '8':
			cout << "8.销毁队列:\t" << endl;
			DestoryLQueue(Q);
			Q = NULL;
			ok = 0;
			break;


		case '0':
			cout << "0.退出:\t" << endl;
			xiabo(); 
			break;

		}

		buffer();
	}
}


void menu() {
	cout << "------" << "欢迎来到我LQueue作业展示" << "------" << endl;
	cout << "------" << "您现在有一个LQueue指针q" << endl;
	cout << "------" << "您可以进行以下操作" << endl;
	cout << "------" << "1.初始化队列" << endl;
	cout << "------" << "2.判断队列是否为空" << endl;
	cout << "------" << "3.入列" << endl;
	cout << "------" << "4.出列" << endl;
	cout << "------" << "5.查看队头元素" << endl;
	cout << "------" << "6.检测队列长度" << endl;
	cout << "------" << "7.清空队列" << endl;
	cout << "------" << "8.销毁队列" << endl;
	cout << "------" << "0.退出" << endl;
}

//*在菜单栏时调用的输入函数 0-8  返回char字符
char cinmenu() {
	cout << "请输入0-8的数字:" << endl;
	string s;
	char p;
	bool ok = 0;
	while (!ok) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~请重新输入0-8的数字:" << endl;
			continue;
		}
		p = s[0];
		if ('0' <= p && p <= '8') {
			ok = 1;
			cout << "输入成功:" << p << endl;
			//cout << "-----------------------" << endl;
			cout << endl << endl;
			break;
		}
		//cout << "输入错误喵~请重新输入0-8的数字:" << endl;
	}
	return p;
}

void getType(char &type) {
	char p;
	string s = "";
	cout << "请选择数据类型：1.int\t2.char" << endl;
	while (1) {
		cin >> s;
		if (s.size() > 1) {
			cout << "输入错误喵~" << endl;
			cout << "请选择数据类型：1.int\t2.char" << endl;
			continue;
		}
		p = s[0];
		if ('1' <= p && p <= '2') {
			cout << "输入成功:" << p << endl;
			//cout << "-----------------------" << endl;
			cout << endl << endl;
			break;
		}

	}
	if (p == '1') {
		type = 'i';
	}
	if (p == '2') {
		type = 'c';
	}

}


/*缓冲 使用完某个功能后 输入w清空窗口回到菜单... */
void buffer() {
	cout << "------------" << endl;
	cout << "输入任意继续......" << endl;
	string s; cin >> s;
	system("cls");
}

void xiabo() {
	//下播！
	cout << "谢谢使用喵" << endl;
	exit(0);
}