; http://clj-me.cgrand.net/2011/08/19/conways-game-of-life/

; (declare life neighbours)

; (defn step [cells]
;   (set (for [[loc n] (frequencies (mapcat neighbours cells))
;              :when (or (= n 3) (and (= n 2) (cells loc)))]
;          loc)))

; (defn neighbours [[x y]]
;   (for [dx [-1 0 1] dy (if (zero? dx) [-1 1] [-1 0 1])]
;     [(+ dx x) (+ dy y)]))
