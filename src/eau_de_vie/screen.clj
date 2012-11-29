(import '(java.awt Color Dimension))
(import '(java.awt.image BufferedImage))
(import '(javax.swing JFrame JPanel))

(declare gscreen text-printer swing-printer init-frame clear-term reset-term)

; printing
;
(defn print-grid [grid size & {:keys [parser]}] 
  (let [printer (:printer gscreen)] 
    (if parser 
      (printer (parser grid size) size)
      (parser grid size))))


; screen
;
(defn init-no-screen [] (def gscreen {:printer (fn [& args] nil)} ))
(defn init-text-screen [] (def gscreen {:printer #(text-printer %1 %2)} ))
(defn init-swing-screen [size] 
  (let [scale 5
        ssize (* size scale)
        image (new BufferedImage ssize ssize BufferedImage/TYPE_INT_RGB)
        panel (proxy [JPanel] [] (paint [g] (.drawImage g image 0 0 this)))
        frame (init-frame ssize panel)]
    (def gscreen  {:printer #(swing-printer %1 %2) 
                  :frame frame 
                  :panel panel 
                  :image image 
                  :scale scale})))


; prints 1d vector in grid form
;
(defn text-printer [grid size]
  (let [lineseq  (partition size (map #(nth [" " "#"] %) grid))
        strgrid  (clojure.string/join (apply concat (map #(conj % "\n") lineseq)))]
    (reset-term)
    (clear-term)
    (println strgrid)))


; prints 1d vector on swing panel
;
(defn swing-printer [grid size]
  (let [scale    (:scale gscreen)
        ssize    (* size scale)
        graphics (.createGraphics (:image gscreen))]
    (.setColor graphics (Color. 255 255 255))
    (.fillRect graphics 0 0 ssize ssize)
    (doseq [[x y cell] (map-indexed #(vec [(rem %1 size) (int (/ %1 size)) %2]) grid)]
      (.setColor graphics (Color. 0 0 0))
      (if (= 1 cell) 
        (let [x (* x scale) y (* y scale)]
          (.fillRect graphics x y (- scale 1) (- scale 1)))))
    (.repaint (:panel gscreen))
    (.dispose graphics)))


; helpers
;
(defn init-frame [size panel]
  (doto (JFrame.)
    (.setSize (new Dimension size size))
    (.add panel)
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE) 
    (.show)))

(defn cleanup-screen []
  (let [frame (:frame gscreen)]
    (when frame (.setVisible frame false) (.dispose frame))))

(def clear-term #(println "\033[2J"))
(def reset-term #(println "\033[0;0H"))
