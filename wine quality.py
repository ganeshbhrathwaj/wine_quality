import numpy as np
import pandas as pd

ds=pd.read_csv('wine quality polynomial reg.csv')
x=ds.iloc[:,0:11].values
y=ds.iloc[:,-1].values


from sklearn.linear_model  import LinearRegression

i=0
t=np.empty((1,11))
e=np.empty((1,11))
from sklearn.preprocessing import PolynomialFeatures
preg=PolynomialFeatures(degree=4)
xpoly=preg.fit_transform(x)
reg1=LinearRegression()
reg1.fit(xpoly,y)
ypred=reg1.predict(xpoly)

from firebase import firebase
fb=firebase.FirebaseApplication('https://winequality-1d50d.firebaseio.com/')
r=fb.get('/users',None)
q=['fa','va','ca','rs','ch','fsd','tsd','d','pH','sul','al']
for s in q:
    t[0][i]=r[s]
    i=i+1
i=0
tpoly=preg.fit_transform(t)
ypred1=reg1.predict(tpoly)
#ypred1.reshape(1,1)
fb.put('/users','qual',ypred1[0])

e=reg1.predict(xpoly[1,:].reshape(1,1365))
