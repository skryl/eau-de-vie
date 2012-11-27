(ns eau-de-vie.life1)
(refer 'eau-de-vie.core)

(declare life next-grid count-neighbors fix-point next-state)

(defn life [size generations]
  (let [grid (randvec-2d size)]
    (bench generations next-grid grid)))

; core
;
(defn next-grid [grid] 
  (let [size (count grid) 
        size-rng (range size)
        points (for [x size-rng y size-rng] [y x])
        update-point #(let [ncount (count-neighbors grid %2) 
                            alive (= 1 (get-in grid %2))]
                        (assoc-in % %2 (next-state alive ncount))) ]
    (print-grid (flatten grid) size)
    (reduce update-point grid points)))

; (defn count-neighbors [grid yx]
;   (reduce #(+ %1 (get-in grid (fix-point (count grid) %2))) 0 (map #(map + yx %) neighbor-coords) )) ; <-- oops

(defn count-neighbors [grid yx]
  (reduce #(+ %1 (get-in grid (fix-point (count grid) %2))) 0 (map #(map + yx %) neighbor-coords) ))

(defn fix-point [size yx]
  (let [y (first yx) x (last yx) hsize (- size 1)
        fix-coord #(cond (<= 0 % hsize) %
                    (< % 0) (+ % size)
                    (> % hsize) (- % size))]
    [(fix-coord y) (fix-coord x)]))
  
(defn next-state [alive ncount]
   (if (true? alive)
        (case ncount (0 1) 0 (2 3) 1 0)
        (case ncount (3) 1 0) ))
