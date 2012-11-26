; screen
;
(def clear-screen #(println "\033[2J"))
(def reset-screen #(println "\033[0;0H"))

; print 1d vector in grid form
;
(defn print-grid [grid size]
    (let [lineseq  (partition size (map #(nth [" " "#"] %) grid))
          strgrid  (clojure.string/join (apply concat (map #(conj % "\n") lineseq)))]
      (reset-screen)
      (clear-screen)
      (println strgrid)))

; print 2d vector in grid form
;
(defn print-grid-2d [grid]
  (reset-screen)
  (clear-screen)
  (doseq [x grid] 
    (doseq [c x] 
      (if (= c 1) (printf "#") (printf " ") ))
    (println))
  (Thread/sleep 20))
