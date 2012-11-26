; grid generation
;

(defn randvec [size] (vec (take size(repeatedly #(rand-int 2)))))
(defn randvec-2d [size] (vec (take size(repeatedly #(randvec size)))))
(defn gen-grid [size] (randvec (Math/pow size 2)))
