(declare print-grid frame image graphics)


; ansi helpers
;
(def clear-screen #(println "\033[2J"))
(def reset-screen #(println "\033[0;0H"))


; print 1d vector in grid form
;
(defn print-grid-text [grid size]
  (let [lineseq  (partition size (map #(nth [" " "#"] %) grid))
        strgrid  (clojure.string/join (apply concat (map #(conj % "\n") lineseq)))]
    (reset-screen)
    (clear-screen)
    (println strgrid)))


; swing printer
;
(defn print-grid-swing [grid size]
  (let [scale 5
        scaledsize  (* size scale)]
    (.setSize  frame (java.awt.Dimension. scaledsize scaledsize))
    (.add frame (proxy [javax.swing.JPanel] [] (paint [g] (.drawImage g image 0 0 this))))
    (.setColor graphics (java.awt.Color. 255 255 255))
    (.fillRect graphics 0 0 scaledsize scaledsize)
    (doseq [[x y cell] (map-indexed #(vec [(rem %1 size) (int (/ %1 size)) %2]) grid)]
      (.setColor graphics (java.awt.Color. 0 0 0))
      (if (= 1 cell) 
        (let [x (* x scale) y (* y scale)]
          (.fillRect graphics x y (- scale 1) (- scale 1)))))
    (.show frame)))


; screen
;
(defn cleanup-screen    [] (when (= (class frame) javax.swing.JFrame) (.setVisible frame false) (.dispose frame)))
(defn init-no-screen    [] (defn print-grid [& args] nil)) 
(defn init-text-screen  [] (defn print-grid [& args] (apply print-grid-text args))) 
(defn init-swing-screen [] (defn print-grid [& args] (apply print-grid-swing args)) 
                           (def frame (javax.swing.JFrame.))
                           (def image (java.awt.image.BufferedImage. 5000 5000 java.awt.image.BufferedImage/TYPE_INT_RGB))
                           (def graphics (.createGraphics image))
                           (.setVisible frame true)) 
