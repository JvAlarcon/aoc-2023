(ns aoc-2023.day02
  (:use [clojure pprint]))

(def cubes-game [{:game 1,
                  :sets [{:red 4, :blue 3},
                          {:red 1, :green 2, :blue 6},
                          {:green 2}]},
                  {:game 2,
                   :sets [{:green 2, :blue 1},
                          {:red 1, :green 3, :blue 4},
                          {:green 1, :blue 1}]},
                  {:game 3,
                   :sets [{:red 20, :green 8, :blue 6},
                          {:red 4, :green 13, :blue 5},
                          {:red 1, :green 5}]},
                  {:game 4,
                   :sets [{:red 3, :green 1, :blue 6},
                          {:red 6, :green 3},
                          {:red 14, :green 3, :blue 15}]},
                  {:game 5,
                   :sets [{:red 6, :green 3, :blue 1},
                          {:red 1, :green 2, :blue 2}]}])

;(pprint cubes-game)

; The Elf would first like to know which games would have been possible
; if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?
; Determine which games would have been possible if the bag had been
; loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes.
; What is the sum of the IDs of those games?


(defn sum-sets [value]
 )

(defn sum-cubes [color sets]
  ; I'm having trouble figuring out how to get the position from the vector
  ; In the end, i need to return the game and the total cubes by color
  (->> (nth sets 0)
       (map color)
       (remove nil?)
       (reduce +)))
(defn find-valid-sets [sets]
  (let [red (sum-cubes :red (map :sets sets))]
    (println red)))

(defn find-valid-games [games]
  (->> games
       (find-valid-sets)
       println))

(find-valid-games cubes-game)
(defn sum-ids []
  )