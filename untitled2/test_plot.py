import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

data=pd.DataFrame(np.random.randn(10000,4),

                  columns=list("abcd")
                  )
print(data.head())
data=data.cumsum()
data.plot()
plt.show()

