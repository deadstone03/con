(ns HelloWorld)
(+ 12 30)
(max 12 3)
(def meaning-of-life 42)
(+ meaning-of-life 2)
(if (< meaning-of-life 0) "negativate" "non-negativate")
(println meaning-of-life)
(def droids ["Huey" "Dewey" "Louie"])
(println (count droids))
(println (droids 0))
(def me {:name "paul" :age 45 :sex :male})
(println (:age me))
(println me)
(defn percentage
  "percentage"
  [x p]
      (* x (/ p 100)))
(println (percentage 100 10))