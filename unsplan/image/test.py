import numpy as np

a=np.array(['s',2,3,4])
b=np.array([1,2,3,4])
print(a)
print(b)
c=np.vstack((a,b))
print(c)
d=c.T
print(d)
e=d.tolist()
print(e)

a=[1,2,3,4,5]
b=[1,'',3,4,5]
n=zip(a,b)
for _ in n :
    print(str(_))