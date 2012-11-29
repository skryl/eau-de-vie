(ns eau-de-vie.core
  (:require [clojure.tools.cli :as c]))

(load "constants")
(load "screen")
(load "bench")
(load "grid")
(load "life1")
(load "life2")
(load "life3")
(load "life4")
(load "life5")

(defn -main [& args]
  (let [[opts args banner] (c/cli args
                ["-v" "--version" "Version of life to run"]
                ["-s" "--size" "Size of grid to use" :default 50]
                ["-g" "--generations" "Number of generations to simulate" :default 1000]
                ["-p" "--print" "Display the game output" :default false])
        size (Integer. (get opts :size))
        gens (Integer. (get opts :generations))
        ver  (get opts :version)
        game (resolve (symbol (str "eau-de-vie.life" ver "/life")))]
    (case (get opts :print) 
      false  (init-no-screen)
      "text" (init-text-screen)
             (init-swing-screen size)) 
    (if (get opts :version)
      (do (printf "Life version %s ran at %s ms per frame\n" ver (game size gens))
        (cleanup-screen))
      (println banner))))
