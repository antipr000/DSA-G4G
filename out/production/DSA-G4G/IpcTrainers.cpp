#include<iostream>
#include<vector>
#include<set>
#include<algorithm>

using namespace std;

//Problem Link: https://www.codechef.com/JULY17/problems/IPCTRAIN/

struct mytype
{

    long long int d,t,s;
};
bool cmp(struct mytype a, struct mytype b)
{
    if(a.s!=b.s)
    return a.s>b.s;

}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin>>t;
    while(t--)
    {
       long long int n,d;
        cin>>n>>d;
        vector<struct mytype>arr(n);
        for(long long int i=0;i<n;i++)
        {
            cin>>arr[i].d>>arr[i].t>>arr[i].s;
        }
        sort(arr.begin(),arr.end(),cmp);


        std::set<long long int>sadset;
        for(long long int i=1;i<=d;i++)
        {
            sadset.insert(i);
        }


        for(long long int i=0;i<n;i++)
        {
            if(sadset.empty()==true)
            break;

            long long int start=arr[i].d;
            std::set<long long int>::iterator itr=sadset.lower_bound(start);
            while(itr!=sadset.end())
            {
                    itr=sadset.erase(itr);

                   arr[i].t-=1;

                    if(arr[i].t==0)
                    break;


            }

        }
        long long int sad=0;
        for(long long int i=0;i<n;i++)
        {

            sad=sad+(arr[i].t*arr[i].s);
        }

        cout<<sad<<endl;

    }
    return 0;
}