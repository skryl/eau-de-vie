; benchmarking
;

(defn bench [times f x & args]
  (let [nanotime #(. java.lang.System (clojure.core/nanoTime))]
    (loop [x x start-time (nanotime) ave-time 0 i times]
      (let [atime (/ (- (nanotime) start-time) 2) 
            stime (nanotime)] 
        (if (= i 0) 
          (float (/ atime 1000000)) 
          (recur (apply (partial f x) args) stime atime (- i 1)))))))
