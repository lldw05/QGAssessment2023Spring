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
		//��ʼ���ɹ� ���ڵ�Ĭ��value=100
		cout << "��ʼ���ɹ� ���ڵ�Ĭ��value=100" << endl;
		return succeed;
	}
	else {
		//�Ѿ���ʼ��
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
	//Ĭ����Ϊtree��e����Ч

	//����tree->root==NULL�����
	if (tree->root == NULL) {
		NodePtr p = new Node();
		p->value = e;
		p->left = NULL;
		p->right = NULL;
		tree->root = p;
	}


	//������ڵ�
	if (tree->root->value == e) {
		return failed;
	}

	NodePtr cur = tree->root;
	NodePtr parent = tree->root;
	while (cur) {
		//cur��Ϊ�� ��ýڵ����value
		//������
		if (e > cur->value) {
			parent = cur;
			cur = cur->right;
		}
		//������
		else if (e < cur->value) {
			parent = cur;
			cur = cur->left;
		}
		//���ڵ�ǰ�ڵ�ֵ ����ʧ��
		else if (e == cur->value) {
			return failed;
		}
		
	}
	//cur=NULL
	

	//��ʱ˵������û�и�ֵ �Ǿ�newһ���ڵ�Ȼ�����
	cur = new Node();
	cur->value = e;
	cur->left = NULL;
	cur->right = NULL;

	//e<value �����
	if(parent->value>e){
		
		parent->left = cur;
	}
	//e>value ���ұ�
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
	//����tree��e����Ч


	NodePtr cur = tree->root;
	NodePtr parent = tree->root;


	//�ش���ɾ������root��root������
	if (!cur->left && !cur->right&&cur->value==e) {
		tree->root = NULL;


		//ɾ���ڵ�
		delete(cur);
		cur = NULL;
		parent = NULL;

		return succeed;
	}

	//�ش���ɾ������root ��rootֻ��һ������
	if (tree->root->value == e&&(cur->left==NULL || cur->right==NULL)) {

		//���������� ��������
		if (cur->left) {
			
			tree->root = cur->left;
		}
		//���������� ��������
		else {
			tree->root = cur->right;
		}

		//ɾ���ڵ�
		delete(cur);
		cur = NULL;
		parent = NULL;
		return succeed;
	}
	



	int child = 0;//0��ʾ��������1��
	while (cur) {
		
		if (cur->value == e) {
			//�ҵ���ֵ
			if (cur->left!=NULL && cur->right != NULL) {
				//������������  ��������������(��С)temp1 (temp1��temp2ǰ��)
				//temp1������Ϊ��ʱ temp1Ϊ��С temp2Ϊ�丸���


				NodePtr temp1 = cur->right;
				NodePtr temp2 = cur;
				child = 1;//��һ�������� ����������
				while (temp1->left) {
					//��������Ϊ�� ������
					child = 0;
					temp2 = temp1;
					temp1 = temp1->left;
				}
				//�ҵ�  temp1Ϊ����
				//�ж�temp1����������(temp1�ض���������) �������temp2�� 
				if (temp1->right) {
					//��������

					

					//����temp1��������
					if (child) {
						//temp1Ϊtemp2�������� temp1���������ӵ�temp2����
						temp2->right = temp1 -> right;
					}
					else {
						//temp1Ϊtemp2�������� temp1���������ӵ�temp2����
						temp2->left = temp1->right;
					}
				}
				else {
					//�������� temp2����or��Ҫ��Ϊ��
					if (child) {
						//temp1Ϊtemp2�������� temp2������ΪNULL
						temp2->right = NULL;
					}
					else {
						//temp1Ϊtemp2�������� temp2������ΪNULL
						temp2->left = NULL;
					}
				}
				//����ֵ
				swap(cur->value, temp1->value);

				//ɾ��temp1�ڴ�
				delete(temp1);
				temp1 = NULL;
				temp2 = NULL;

			}
			else if (cur->left || cur->right) {
				//ֻ��һ������
				

				//���������� ��������
				if (cur->left) {
					//curΪparent��������
					if (child) {
						parent->right = cur->left;
					}
					//curΪparent��������
					else {
						parent->left = cur->left;
					}

				}
				//���������� ��������
				else {
					//curΪparent��������
					if (child) {
						parent->right = cur->right;
					}
					//curΪparent��������
					else {
						parent->left = cur->right;
					}
				}

				//ɾ���ڵ�
				delete(cur);
				
			}
			//������ ֱ��ɾ��
			else {

				//curΪparent��������
				if (child) {
					parent->right = NULL;
				}
				//curΪparent��������
				else {
					parent->left = NULL;
				}

				//ɾ���ڵ�
				delete(cur);
			}

			//ɾ���ɹ� ����succeed
			return succeed;
		}
		//û�ҵ� 
		else {
			//����
			if (cur->value < e) {
				child = 1;
				parent = cur;
				cur = cur->right;
			}
			//����
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
	//����tree��e����Ч


	NodePtr p = tree->root;
	while (p) {

		//�ҵ�
		if (p->value == e) {
			return true;
		}

		
		//����
		else if (p->value > e) {
			p = p->left;
		}

		//����
		else if (p->value < e) {
			p = p->right;
		}
	}

	//û�ҵ�
	return failed;
}

/**
 * BST preorder traversal without recursion
 * @param BinarySortTreePtr BST
 * @param (*visit) callback
 * @return is successful
 */
Status BST_preorderI(BinarySortTreePtr tree, void (*visit)(NodePtr)) {
	//����tree��Ч  �ѳ�ʼ��

	//�ж�����root
	if (tree->root == NULL)return failed;

	NodePtr p = tree->root;
	stack<NodePtr>s;
	vector<ElemType>vec;


	//p��Ϊ��
	s.push(p);

	//p��Ϊ�� ��ջ��Ϊ��
	while (p && !s.empty()) {


		//��ջ �����ݴ���vec
		p = s.top();
		s.pop();
		vec.push_back(p->value);

		//���Һ���������ջ   ���ȳ�ջ���Һ�
		if (p->right) {
			s.push(p->right);
		}
		if (p->left) {
			s.push(p->left);
		}
	}
	//���
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
	//�ݹ�
	//����tree��Ч  �ѳ�ʼ��

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
	//����tree��Ч  �ѳ�ʼ��

	if (tree->root == NULL)return failed;

	NodePtr p = tree->root;

	stack<NodePtr>s;
	vector<ElemType>vec;


	//p��Ϊ��
	

	//p��Ϊ�� ��ջ��Ϊ��
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

		////��������
		//while (p->left) {
		//	s.push(p->left);
		//	p = p->left;
		//}

		////�����ǰ�ڵ�
		//vec.push_back(p->value);

		////�жϸýڵ� �Ƿ����Һ�
		////���Һ�
		//if (p->right) {
		//	s.push(p->right);
		//}
		////�Һ�Ϊ�� ջ����Ԫ�� ���뵽vec��
		//else {
		//	p = s.top();
		//	s.pop();//���ǵ�����quq
		//	vec.push_back(p->value);
		//}
	}


	//���
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
	//�ݹ�
	//����tree��Ч  �ѳ�ʼ��

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
	//����tree��Ч  �ѳ�ʼ��

	//�ж�����root
	if (tree->root == NULL)return failed;

	NodePtr p = tree->root;
	stack<NodePtr>s;
	vector<ElemType>vec;


	//p��Ϊ��
	s.push(p);

	//p��Ϊ�� ��ջ��Ϊ��
	while (p && !s.empty()) {


		//��ջ �����ݴ���vec
		p = s.top();
		s.pop();
		vec.push_back(p->value);

		//����������ջ    vec��˳��Ϊ������
		if (p->left) {
			s.push(p->left);
		}
		if (p->right) {
			s.push(p->right);
		}
	}

	//vec��ת ������  ��������
	reverse(vec.begin(), vec.end());

	//���
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
	//�ݹ�
	//����tree��Ч  �ѳ�ʼ��

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
	//����tree��Ч  �ѳ�ʼ��

	//�ж�����root
	if (tree->root == NULL)return failed;

	NodePtr p = tree->root;
	queue<NodePtr> q;
	vector<ElemType>vec;


	q.push(p);
	while (1&&!q.empty()) {
		//����
		p=q.front();
		q.pop();

		//value����vec
		vec.push_back(p->value);

		//�����
		if (p->left) {
			q.push(p->left);
		}
		//�Һ����
		if (p->right) {
			q.push(p->right);
		}
	}

	//���
	for (int i = 0; i < vec.size(); i++)
		cout << vec[i] << " ";
}



void visit(NodePtr node) {
	cout << node->value << " ";
	return;
}

