(ns eau-de-vie.core
  (:require [clojure.tools.cli :as c])) 

(load "constants")
(load "bench")
(load "screen")
(load "grid")
(load "life1")
(load "life2")
(load "life3")

(defn -main [& args]
  (let [[opts args banner] (c/cli args
                ["-v" "--version" "Version of life to run"]
                ["-s" "--size" "Size of grid to use" :default 50]
                ["-g" "--generations" "Number of generations to simulate" :default 1000]
                ["-p" "--print" "How to display the grid output" :default false])]
    (case (get opts :print) 
      false  (init-no-screen)
      "text" (init-text-screen)
             (init-swing-screen)) 
    (if (get opts :version)
      (do 
        (printf "Life version %s ran at %s ms per frame\n" (get opts :version)
          ((resolve (symbol (str "eau-de-vie.life" (get opts :version) "/life"))) 
                                                   (Integer. (get opts :size))  
                                                   (Integer. (get opts :generations))))
        (cleanup-screen))
      (println banner))))
