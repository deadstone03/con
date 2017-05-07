 (ns PSum
   (:require [clojure.core.reducers :as r]))

 (defn parallel-sum
   "parallel sum"
   [alon]
   (r/fold + alon))

 (println (parallel-sum [1 2 3 4 5]))
