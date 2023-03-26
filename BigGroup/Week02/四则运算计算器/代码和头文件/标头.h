#pragma once
#include<iostream>
#include<stack>
#include <string>
using namespace std;
bool valid(string s);
string cininfix();
string infixToPostfix(string s);
int grade(char c);
void buffer();
double calculatePostfix(string s);
double calculation(double a, double b, char tmd);