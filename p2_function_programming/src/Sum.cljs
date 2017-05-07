 (ns Sum)

 (defn sum
   "sum of a list"
   [alon]
   (if (= (count alon) 0) 0 (+ (first alon) (sum (rest alon)))))

 (println (sum [1 2 3 4 5]))
