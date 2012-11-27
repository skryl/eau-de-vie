(ns eau-de-vie.life3)
(refer 'eau-de-vie.core)

(declare 
  life next-grid alive-neighbors
  build-neighbor-map find-neighbor-indices
  transform-coord-pair normalize-coord)    

; core
;
(defn life [size generations]
  (let [grid      (gen-grid size)
        neighbors (build-neighbor-map grid size)]
    (bench generations next-grid grid neighbors size)))

(defn next-grid [grid neighbors size]
  (print-grid grid size)
  (vec (map-indexed #(get-in transition-map [%2 (alive-neighbors grid neighbors %1)]) grid)))

(defn alive-neighbors [grid neighbors idx] 
  (reduce #(+ %1 (nth grid %2)) 0 (get neighbors idx))) 


; neighbor map
;
(defn build-neighbor-map [grid size] 
  (reduce-kv (fn [coll idx val] (assoc coll idx (find-neighbor-indices idx size))) {} grid))

(defn find-neighbor-indices [idx size] 
  (map #(transform-coord-pair (first %) (last %) idx size) neighbor-coords))

(defn transform-coord-pair [x y idx size]
  (let [row  (int (/ idx size)) 
        col  (rem idx size)]
    (+ (normalize-coord (+ x col) size) (* (normalize-coord (+ y row) size) size) )))

(defn normalize-coord [c size] (cond (< c 0) (+ c size) (> c (- size 1)) (- c size) :else c))
