; http://clj-me.cgrand.net/2011/08/19/conways-game-of-life/

(ns eau-de-vie.life4)
(refer 'eau-de-vie.core)

(declare life step neighbors normalize)

(defn life [size generations]
  (let [grid (gen-sparse-grid size)]
    (bench generations step grid size)))

(defn step [cells size]
  (print-grid cells size :parser sparse-to-1d) 
  (set (for [[loc n] (frequencies (mapcat #(neighbors % size) cells))
             :when (or (= n 3) (and (= n 2) (cells loc)))]
         loc)))

(defn neighbors [[x y] size]
  (for [dx [-1 0 1] dy (if (zero? dx) [-1 1] [-1 0 1])]
    (normalize [(+ dx x) (+ dy y)] size)))

(defn normalize [[x y] size]
  (let [norm-coord #(cond (< % 0) (+ % size) (>= % size) (- % size) :else %)]
    [(norm-coord x) (norm-coord y)]))
