 (ns Sum)

 (defn sum
   "sum of a list"
   [alon]
   (if (= (count alon) 0) 0 (+ (first alon) (sum (rest alon)))))

 (println (sum [1 2 3 4 5]))


 (defn reduce-sum
   "use reduce to caculate sum"
   [alon]
   (reduce (fn [s e] (+ s e)) 0 alon))
 (println (reduce-sum [1 2 3 4 5]))