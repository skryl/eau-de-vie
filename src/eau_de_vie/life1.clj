(ns eau-de-vie.life1)
(refer 'eau-de-vie.core)

(declare life step count-live-neighbors)

(defn life [size generations]
  (let [grid (gen-2d-grid size)
        size-rng (range size)
        grid-coords (for [x size-rng y size-rng] [y x])]
    (bench generations step grid size grid-coords)))

(defn step [grid size grid-coords] 
  (let [update-cell (fn [grid cell-coords] (assoc-in grid cell-coords 
                       (get-in transition-map 
                         [(get-in grid cell-coords) (count-live-neighbors grid size cell-coords)] )))] 
    (print-grid (flatten grid) size)
    (reduce update-cell grid grid-coords)))

(defn count-live-neighbors [grid size cell-coords]
  (let [dsize      (- size 1)
        translate  #(map + cell-coords %)
        fix-coord  #(cond (<= 0 % dsize) % (< % 0) (+ % size) (> % dsize) (- % size))]
    (reduce 
      #(+ %1 (get-in grid [(fix-coord (first %2)) (fix-coord (last %2))])) 0 
        (map translate neighbor-coords))))
