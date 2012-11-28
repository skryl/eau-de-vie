; http://clj-me.cgrand.net/2011/08/19/conways-game-of-life/

(ns eau-de-vie.life4)
(refer 'eau-de-vie.core)

(declare life step neighbours normalize)

(defn life [size generations]
  (let [grid (gen-sparse-grid size)]
    (bench generations step grid size)))

(defn step [cells size]
  (print-grid (sparse-to-1d cells size) size)
  (set (for [[loc n] (frequencies (mapcat neighbours cells))
             :when (or (= n 3) (and (= n 2) (cells loc)))]
         (normalize loc size))))

(defn neighbours [[x y]]
  (for [dx [-1 0 1] dy (if (zero? dx) [-1 1] [-1 0 1])]
    [(+ dx x) (+ dy y)]))

(defn normalize [[x y] size]
  (let [norm-coord #(cond (< % 0) (+ % size) (>= % size) (- % size) :else %)]
    [(norm-coord x) (norm-coord y)]))
