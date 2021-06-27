#include <bits/stdc++.h>
using namespace std;

void print_vector(){
    vector<int> arr;
    	arr.push_back(1); //push_back O(1)
    	arr.push_back(2);
    	arr.push_back(3);

    	//arr.pop_back(); //pop_back O(1)

    	for(auto itr = arr.begin(); itr!=arr.end(); itr++){
    	    cout<<*itr<<" ";
    	}
    	cout<<endl;

    	arr[2] =
    	for(auto itr = arr.rbegin(); itr!=arr.rend(); itr++){
    	    cout<<*itr<<" ";
    	}

        cout<<endl;
    	vector<int>::iterator it = arr.begin() + 1;
    	arr.erase(it);
    	for(int x: arr) cout<<x<<" ";
}

void print_set(){
    	set<int> x;
    	/*
    	    Lets say set contains X elements
    	    Properties of a set:
    	        1. Insertion of elements is O(log|X|)
    	        2. Removing any element is O(log|X|)
    	        3. Finding an element is O(log|X|)
    	        4. Removing by iterator is O(1)
    	        5. The set is ordered
    	        6. Only contains unique values
    	*/

    	x.insert(2);
    	x.insert(1);
    	x.insert(3);

    	for(int val: x){
    	    cout<<val<<" ";
    	}

    	cout<<endl;
    	x.insert(2);
    	for(int val: x){
    	    cout<<val<<" ";
    	}


        cout<<endl;

        cout<<*x.rbegin()<<endl;

        x.erase(2);

        for(int val: x){
    	    cout<<val<<" ";
    	}



        cout<<endl;

        cout<<*x.lower_bound(2)<<endl;
}

void print_map(){
        map<string, int> x;

        x["Soham"] = 10;
        x["Rajesh"] = 20;

        cout<<x["Soham"]<<endl;

        x["Soham"] += 20;

        cout<<x["Soham"]<<endl;

        for(pair<string, int> el: x){
            cout<<el.first<<" "<<el.second<<endl;
        }

        x.erase("Soham");

        for(pair<string, int> el: x){
            cout<<el.first<<" "<<el.second<<endl;
        }


        cout<<(x.find("Soham") != x.end())<<endl;

        cout<<(x.find("Rajesh") != x.end())<<endl;
}

int main() {
	// your code goes here
	//print_vector();
    //print_set();
    print_map();
	return 0;
}
