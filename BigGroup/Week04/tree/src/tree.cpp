#include"../inc/binary_sort_tree.h"

/**
 * BST initialize
 * @param BinarySortTreePtr BST
 * @return is complete
 */
Status BST_init(BinarySortTreePtr bst) {
	
	if (bst->root == NULL) {
		NodePtr p = new Node();
		p->left = NULL;
		p->right = NULL;
		p->value = 100;
		bst->root = p;
		//初始化成功 根节点默认value=100
		cout << "初始化成功 根节点默认value=100" << endl;
		return succeed;
	}
	else {
		//已经初始化
		return failed;
	}
}

/**
 * BST insert
 * @param BinarySortTreePtr BST
 * @param ElemType value to insert
 * @return is successful
 */
Status BST_insert(BinarySortTreePtr tree, ElemType e) {
	//默认认为tree和e均有效

	//处理tree->root==NULL的情况
	if (tree->root == NULL) {
		NodePtr p = new Node();
		p->value = e;
		p->left = NULL;
		p->right = NULL;
		tree->root = p;
	}


	//处理根节点
	if (tree->root->value == e) {
		return failed;
	}

	NodePtr cur = tree->root;
	NodePtr parent = tree->root;
	while (cur) {
		//cur不为空 则该节点存在value
		//遍历右
		if (e > cur->value) {
			parent = cur;
			cur = cur->right;
		}
		//遍历左
		else if (e < cur->value) {
			parent = cur;
			cur = cur->left;
		}
		//等于当前节点值 插入失败
		else if (e == cur->value) {
			return failed;
		}
		
	}
	//cur=NULL
	

	//此时说明树中没有该值 那就new一个节点然后插入
	cur = new Node();
	cur->value = e;
	cur->left = NULL;
	cur->right = NULL;

	//e<value 放左边
	if(parent->value>e){
		
		parent->left = cur;
	}
	//e>value 放右边
	else {
		
		parent->right = cur;
	}
	return succeed;
}

/**
 * BST delete
 * @param BinarySortTreePtr BST
 * @param ElemType the value for Node which will be deleted
 * @return is successful
 */
Status BST_delete(BinarySortTreePtr tree, ElemType e) {
	//假设tree和e均有效


	NodePtr cur = tree->root;
	NodePtr parent = tree->root;


	//特处理删除的是root且root无子树
	if (!cur->left && !cur->right&&cur->value==e) {
		tree->root = NULL;


		//删除节点
		delete(cur);
		cur = NULL;
		parent = NULL;

		return succeed;
	}

	//特处理删除的是root 且root只有一个子树
	if (tree->root->value == e&&(cur->left==NULL || cur->right==NULL)) {

		//存在左子树 无右子树
		if (cur->left) {
			
			tree->root = cur->left;
		}
		//存在右子树 无左子树
		else {
			tree->root = cur->right;
		}

		//删除节点
		delete(cur);
		cur = NULL;
		parent = NULL;
		return succeed;
	}
	



	int child = 0;//0表示左子树，1右
	while (cur) {
		
		if (cur->value == e) {
			//找到该值
			if (cur->left!=NULL && cur->right != NULL) {
				//左右子树都有  找右子树的最左(最小)temp1 (temp1在temp2前面)
				//temp1左子树为空时 temp1为最小 temp2为其父结点


				NodePtr temp1 = cur->right;
				NodePtr temp2 = cur;
				child = 1;//第一次往右走 其余往左走
				while (temp1->left) {
					//左子树不为空 往左走
					child = 0;
					temp2 = temp1;
					temp1 = temp1->left;
				}
				//找到  temp1为最左
				//判断temp1有无右子树(temp1必定无左子树) 有则接在temp2上 
				if (temp1->right) {
					//有右子树

					

					//接上temp1的右子树
					if (child) {
						//temp1为temp2的右子树 temp1的右子树接到temp2的右
						temp2->right = temp1 -> right;
					}
					else {
						//temp1为temp2的左子树 temp1的右子树接到temp2的左
						temp2->left = temp1->right;
					}
				}
				else {
					//无右子树 temp2的左or右要设为空
					if (child) {
						//temp1为temp2的右子树 temp2的右设为NULL
						temp2->right = NULL;
					}
					else {
						//temp1为temp2的左子树 temp2的左设为NULL
						temp2->left = NULL;
					}
				}
				//交换值
				swap(cur->value, temp1->value);

				//删除temp1内存
				delete(temp1);
				temp1 = NULL;
				temp2 = NULL;

			}
			else if (cur->left || cur->right) {
				//只有一个子树
				

				//存在左子树 无右子树
				if (cur->left) {
					//cur为parent的右子树
					if (child) {
						parent->right = cur->left;
					}
					//cur为parent的左子树
					else {
						parent->left = cur->left;
					}

				}
				//存在右子树 无左子树
				else {
					//cur为parent的右子树
					if (child) {
						parent->right = cur->right;
					}
					//cur为parent的左子树
					else {
						parent->left = cur->right;
					}
				}

				//删除节点
				delete(cur);
				
			}
			//无子树 直接删除
			else {

				//cur为parent的右子树
				if (child) {
					parent->right = NULL;
				}
				//cur为parent的左子树
				else {
					parent->left = NULL;
				}

				//删除节点
				delete(cur);
			}

			//删除成功 返回succeed
			return succeed;
		}
		//没找到 
		else {
			//往右
			if (cur->value < e) {
				child = 1;
				parent = cur;
				cur = cur->right;
			}
			//往左
			else {
				child = 0;
				parent = cur;
				cur = cur->left;
			}
		}

	}

	return failed;
}

/**
 * BST search
 * @param BinarySortTreePtr BST
 * @param ElemType the value to search
 * @return is exist
 */
Status BST_search(BinarySortTreePtr tree, ElemType e) {
	//假设tree和e均有效


	NodePtr p = tree->root;
	while (p) {

		//找到
		if (p->value == e) {
			return true;
		}

		
		//走左
		else if (p->value > e) {
			p = p->left;
		}

		//走右
		else if (p->value < e) {
			p = p->right;
		}
	}

	//没找到
	return failed;
}

/**
 * BST preorder traversal without recursion
 * @param BinarySortTreePtr BST
 * @param (*visit) callback
 * @return is successful
 */
Status BST_preorderI(BinarySortTreePtr tree, void (*visit)(NodePtr)) {
	//假设tree有效  已初始化

	//判断有无root
	if (tree->root == NULL)return failed;

	NodePtr p = tree->root;
	stack<NodePtr>s;
	vector<ElemType>vec;


	//p不为空
	s.push(p);

	//p不为空 且栈不为空
	while (p && !s.empty()) {


		//出栈 将数据存入vec
		p = s.top();
		s.pop();
		vec.push_back(p->value);

		//先右后左子树入栈   左孩先出栈再右孩
		if (p->right) {
			s.push(p->right);
		}
		if (p->left) {
			s.push(p->left);
		}
	}
	//输出
	for (int i = 0; i < vec.size(); i++)
		cout << vec[i] << " ";
}

/**
 * BST preorder traversal with recursion
 * @param BinarySortTreePtr BST
 * @param (*visit) callback
 * @return is successful
 */
Status BST_preorderR(NodePtr p, void (*visit)(NodePtr)) {
	//递归
	//假设tree有效  已初始化

	if (p == NULL) return succeed;
	
	visit(p);
	BST_preorderR(p->left,visit);
	BST_preorderR(p->right,visit);

}

/**
 * BST inorder traversal without recursion
 * @param BinarySortTreePtr BST
 * @param (*visit) callback
 * @return is successful
 */
Status BST_inorderI(BinarySortTreePtr tree, void (*visit)(NodePtr)) {
	//假设tree有效  已初始化

	if (tree->root == NULL)return failed;

	NodePtr p = tree->root;

	stack<NodePtr>s;
	vector<ElemType>vec;


	//p不为空
	

	//p不为空 或栈不为空
	while (p!=NULL || !s.empty()) {

		if (p!=NULL) {
			s.push(p);
			p = p->left;
		}
		else {
			p = s.top(); s.pop();
			vec.push_back(p->value);
			p = p->right;
		}

		////访问最左
		//while (p->left) {
		//	s.push(p->left);
		//	p = p->left;
		//}

		////输出当前节点
		//vec.push_back(p->value);

		////判断该节点 是否含有右孩
		////有右孩
		//if (p->right) {
		//	s.push(p->right);
		//}
		////右孩为空 栈弹出元素 加入到vec中
		//else {
		//	p = s.top();
		//	s.pop();//忘记弹出了quq
		//	vec.push_back(p->value);
		//}
	}


	//输出
	for (int i = 0; i < vec.size(); i++)
		cout << vec[i] << " ";
}

/**
 * BST inorder traversal with recursion
 * @param BinarySortTreePtr BST
 * @param (*visit) callback
 * @return is successful
 */
Status BST_inorderR(NodePtr p, void (*visit)(NodePtr)) {
	//递归
	//假设tree有效  已初始化

	if (p == NULL) return succeed;



	BST_inorderR(p->left, visit);
	visit(p);
	BST_inorderR(p->right, visit);
}

/**
 * BST preorder traversal without recursion
 * @param BinarySortTreePtr BST
 * @param (*visit) callback
 * @return is successful
 */
Status BST_postorderI(BinarySortTreePtr tree, void (*visit)(NodePtr)) {
	//假设tree有效  已初始化

	//判断有无root
	if (tree->root == NULL)return failed;

	NodePtr p = tree->root;
	stack<NodePtr>s;
	vector<ElemType>vec;


	//p不为空
	s.push(p);

	//p不为空 且栈不为空
	while (p && !s.empty()) {


		//出栈 将数据存入vec
		p = s.top();
		s.pop();
		vec.push_back(p->value);

		//先左再右入栈    vec中顺序为中右左
		if (p->left) {
			s.push(p->left);
		}
		if (p->right) {
			s.push(p->right);
		}
	}

	//vec翻转 左右中  后续遍历
	reverse(vec.begin(), vec.end());

	//输出
	for (int i = 0; i < vec.size(); i++)
		cout << vec[i] << " ";
}

/**
 * BST postorder traversal with recursion
 * @param BinarySortTreePtr BST
 * @param (*visit) callback
 * @return is successful
 */
Status BST_postorderR(NodePtr p, void (*visit)(NodePtr)) {
	//递归
	//假设tree有效  已初始化

	if (p == NULL) return succeed;



	BST_postorderR(p->left, visit);
	BST_postorderR(p->right, visit);
	visit(p);
}

/**
 * BST level order traversal
 * @param BinarySortTreePtr BST
 * @param (*visit) callback
 * @return is successful
 */
Status BST_levelOrder(BinarySortTreePtr tree, void (*visit)(NodePtr)) {
	//假设tree有效  已初始化

	//判断有无root
	if (tree->root == NULL)return failed;

	NodePtr p = tree->root;
	queue<NodePtr> q;
	vector<ElemType>vec;


	q.push(p);
	while (1&&!q.empty()) {
		//出队
		p=q.front();
		q.pop();

		//value存入vec
		vec.push_back(p->value);

		//左孩入队
		if (p->left) {
			q.push(p->left);
		}
		//右孩入队
		if (p->right) {
			q.push(p->right);
		}
	}

	//输出
	for (int i = 0; i < vec.size(); i++)
		cout << vec[i] << " ";
}



void visit(NodePtr node) {
	cout << node->value << " ";
	return;
}

