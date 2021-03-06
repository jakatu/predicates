(ns predicates)

(defn sum-f [f g x]
  (+ (f x) (g x)))

(defn less-than [n]
  (fn [k] (< k n)))

(defn equal-to [n]
  (fn [k] (== k n)))

(defn set->predicate [a-set]
  (fn [x] (contains? a-set x)))

(defn pred-and [pred1 pred2]
  (fn [x] (and (pred1 x) (pred2 x))))

(defn pred-or [pred1 pred2]
  (fn [x] (or (pred1 x) (pred2 x))))

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
   (or (= 0 (count string)) (= nil string) (every? (fn [s] (= true s)) (map whitespace? string))))

(defn has-award? [book award]
  (if (:awards book)
    (if (award (:awards book))
      true
      false)
    false))

(defn HAS-ALL-THE-AWARDS? [book awards]
  (let [aws (:awards book)
        has (fn [x] (contains? aws x))]
  (if (= (count awards) 0)
    true
    (if (:awards book)
      (every? (fn [x] (= x true)) (map has awards))
      false))))

(defn my-some [pred a-seq]
  (let [red (fn [a] (pred a))
        redd (filter red a-seq)]
    (if (> (count redd) 0) (pred (first redd)) false)))

(defn my-every? [pred a-seq]
  (let [test (fn [x] (filter pred a-seq))]
    (= (count a-seq) (count (test a-seq)))))

(defn prime? [n]
  (let [pred (fn [x] (== 0 (mod n x)))]
    (not (some pred (range 2 n)))))
;^^
