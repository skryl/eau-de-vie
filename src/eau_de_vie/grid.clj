; grid generation
;

(defn gen-vec [size, f] (vec (take size (repeatedly f))))

(defn rand-bin-vec [size] (gen-vec size #(rand-int 2)))

(defn gen-2d-grid [size] (gen-vec size #(rand-bin-vec size)))

(defn gen-1d-grid [size] (rand-bin-vec (Math/pow size 2)))

(defn gen-sparse-grid [size] 
  (let [coords (for [x (range size) y (range size)] [x y])
        coords-size (Math/pow size 2)]
    (set (gen-vec (rand-int coords-size) 
         #(nth coords (rand-int coords-size))))))

(defn sparse-to-1d [cells size]
  (let [cells     (vec cells) 
        grid      (int-array (Math/pow size 2) 0)
        vectorize (fn [[x y]] (+ x (* y size)))]
    (doseq [i (range (count cells))] (aset-int grid (vectorize (nth cells i)) 1))
  (vec grid)))
