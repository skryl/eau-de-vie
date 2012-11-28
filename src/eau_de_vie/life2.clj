(ns eau-de-vie.life2)
(refer 'eau-de-vie.core)

(declare life step count-live-neighbors)

(defn life [size generations]
  (bench generations step (gen-1d-grid size) size))

(defn step [grid size]
  (print-grid grid size)
  (vec (map-indexed #(get-in transition-map [%2 (count-live-neighbors grid size %1)]) grid)))

(defn count-live-neighbors [grid size idx]
  (let [row    (int (/ idx size)) col (rem idx size)
        wrap   #(cond (< % 0) (+ % size) (> % (- size 1)) (- % size) :else %)
        trans  #(+ (wrap (+ %1 col)) (* (wrap (+ %2 row)) size) )]
  (reduce #(+ %1 (nth grid %2)) 0 (map #(apply trans %) neighbor-coords))))

; notes
;
; (map-indexed #(get-in transition-map [%2 (alive-neighbors grid size %1)]) grid)) ; <-- oops 30x slowdown
