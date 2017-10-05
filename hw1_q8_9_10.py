a = [0,2, 7, -3, -4]

def f1(a):
    return [sum(a[:i+1]) for i in range(len(a))]
def f2(a):
    return [min(a[:i+1]) for i in range(len(a))]
def f3(a):
    return [max(a[:i+1]) for i in range(len(a))]
fs = [f1,f2,f3]
fs2 = [sum, min, max]
for i in range(3):
    for j in range(3):
        for k in range(3):
            L = (fs[i])(a)
            M = (fs[j])(L)
            D = [L[i]-M[i] for i in range(len(a))]
            R = fs2[k](D)
            print("choice: "+str(i)+str(j)+str(k)+" gives: "+str(R))
