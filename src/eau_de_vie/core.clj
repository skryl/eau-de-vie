(ns eau-de-vie.core)

(load "constants")
(load "bench")
(load "screen")
(load "grid")
(load "life1")
(load "life2")
(load "life3")

(defn -main
  "I play life. Just tell me what version to run, how big to make it, and how many times to run it."
  [ver size generations]
  (printf "Life version %s runs at %s ms per frame\n" ver
    ((resolve (symbol (str "eau-de-vie.life" ver "/life"))) (Integer. size) (Integer. generations))) )
